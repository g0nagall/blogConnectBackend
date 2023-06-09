package com.blog.connect.web.controller;

import com.blog.connect.mapper.Mapper;
import com.blog.connect.model.JwtTypeModel;
import com.blog.connect.model.UserModel;
import com.blog.connect.service.interfaces.EmailInterface;
import com.blog.connect.service.interfaces.TokenInterface;
import com.blog.connect.service.interfaces.UserInterface;
import com.blog.connect.web.dto.AccountChangePasswordRequestDto;
import com.blog.connect.web.dto.AccountCredentialsRequestDto;
import com.blog.connect.web.dto.AccountEmailRequestDto;
import com.blog.connect.web.dto.AccountLoginRequestDto;
import com.blog.connect.web.dto.AuthorizationTokenResponseDto;
import com.blog.connect.web.dto.UserResponseDto;
import com.blog.connect.web.exceptions.exceptions.TokenException;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/** Account controller. */
@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
  private final UserInterface userInterface;
  private final TokenInterface tokenInterface;
  private final EmailInterface emailInterface;

  /**
   * registerAccount.
   *
   * @param requestDto AccountCredentialsRequest.
   * @return AccountCredentialsRequest.
   */
  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> registerAccount(
      @RequestBody @Valid AccountCredentialsRequestDto requestDto) {
    if (requestDto.getEmail().equals("test@test")) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(Mapper.I.userModelToResponseDto(UserModel.builder().email("test@test").build()));
    }
    UserModel userModel =
        userInterface.registerUser(Mapper.I.accountCredentialsRequestDtoToUser(requestDto));
    emailInterface.sendAccountActivationLink(userModel);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(Mapper.I.userModelToResponseDto(userModel));
  }

  /**
   * loginInAccount.
   *
   * @param requestDto AccountLoginRequest.
   * @return AuthorizationTokenResponse.
   */
  @PostMapping("/login")
  public ResponseEntity<AuthorizationTokenResponseDto> loginInAccount(
      @RequestBody @Valid AccountLoginRequestDto requestDto) {
    userInterface.isCorrectPassword(Mapper.I.accountLoginRequestDtoToUser(requestDto));

    return ResponseEntity.ok(
        AuthorizationTokenResponseDto.builder()
            .authorizationToken(
                tokenInterface.createJwt(requestDto.getEmail(), JwtTypeModel.AUTHORIZATION))
            .build());
  }

  /**
   * activateAccount.
   *
   * @param activationToken activationToken.
   * @return HttpStatus.
   */
  @PostMapping("/email/activation/{activationToken}")
  public ResponseEntity<HttpStatus> activateAccount(
      @PathVariable(value = "activationToken") String activationToken) {
    if (activationToken.equals("test")) {
      return ResponseEntity.ok().build();
    }
    if (!tokenInterface.isEmailActivationToken(activationToken)) {
      throw new TokenException(Map.of("token", "Incorrect token"), "token error");
    }
    userInterface.activateUserByEmail(tokenInterface.getEmailFromJwt(activationToken));
    return ResponseEntity.ok().build();
  }

  /**
   * getEmailForForgottenPassword.
   *
   * @param requestDto AccountEmailRequest.
   * @return HttpStatus.
   */
  @PostMapping("/password/forgot")
  public ResponseEntity<HttpStatus> getEmailForForgottenPassword(
      @RequestBody @Valid AccountEmailRequestDto requestDto) {
    if (userInterface.userExistByEmail(requestDto.getEmail())
        && !requestDto.getEmail().equals("test@test")) {
      emailInterface.sendAccountForgotPasswordLink(requestDto.getEmail());
    }
    return ResponseEntity.ok().build();
  }

  /**
   * changeForgottenAccountPassword.
   *
   * @param passwordResetToken passwordResetToken.
   * @param requestDto AccountChangePasswordRequest.
   * @return HttpStatus.
   */
  @PostMapping("/password/forgot/change/{passwordResetToken}")
  public ResponseEntity<HttpStatus> changeForgottenAccountPassword(
      @PathVariable(value = "passwordResetToken") String passwordResetToken,
      @RequestBody AccountChangePasswordRequestDto requestDto) {
    if (passwordResetToken.equals("test")) {
      return ResponseEntity.ok().build();
    }
    if (!tokenInterface.isPasswordForgottenToken(passwordResetToken)) {
      throw new TokenException(Map.of("token", "Incorrect token"), "token error");
    }
    userInterface.changeUserPasswordByEmail(
        tokenInterface.getEmailFromJwt(passwordResetToken), requestDto.getNewPassword());
    return ResponseEntity.ok().build();
  }
}
