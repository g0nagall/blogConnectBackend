package com.blog.connect.service.implementations;


import com.blog.connect.mapper.Mapper;
import com.blog.connect.model.SecurityUserModel;
import com.blog.connect.model.UserModel;
import com.blog.connect.persistence.entity.UserEntity;
import com.blog.connect.persistence.repository.UserRepository;
import com.blog.connect.service.interfaces.UserInterface;
import com.blog.connect.web.exceptions.exceptions.CredentialsException;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** UserServiceImpl. */
@Service
@AllArgsConstructor
public class UserImpl implements UserInterface {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /** Test user init. */
  @PostConstruct
  private void init() {
    UserEntity testEntity =
        UserEntity.builder()
            .email("test@test")
            .password(passwordEncoder.encode("test"))
            .username("test")
            .createdOn(LocalDateTime.now())
            .enabled(true)
            .build();
    if (userRepository.getUserEntityByEmail("test@test").isEmpty()) {
      userRepository.save(testEntity);
    }
  }

  @Override
  public SecurityUserModel getUserByEmail(String email) {
    return Mapper.I.userEntityToSecurityUserModel(
        userRepository
            .getUserEntityByEmail(email)
            .orElseThrow(
                () ->
                    new CredentialsException(
                        Map.of("email", String.format("User, with email '%s', not found", email)),
                        "Incorrect email")));
  }

  @Override
  public UserModel registerUser(UserModel userModel) {
    userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
    userModel.setCreatedOn(LocalDateTime.now());
    userModel.setEnabled(false);

    return Mapper.I.userEntityToModel(
        Optional.of(userRepository.save(Mapper.I.userModelToEntity(userModel)))
            .orElseThrow(() -> new RuntimeException("Error while trying to save the user")));
  }

  @Override
  public UserModel updateUser(UserModel userModel) {
    return Mapper.I.userEntityToModel(
        userRepository.save(
            Mapper.I.updateUserEntityFromModel(
                userModel,
                userRepository.findById(getAuthenticatedUser().getId().toString()).orElseThrow())));
  }

  /**
   * isCorrectPassword.
   *
   * @param userModel userModel with credentials.
   * @return boolean.
   */
  public boolean isCorrectPassword(UserModel userModel) {
    if (!passwordEncoder.matches(
        userModel.getPassword(), getUserByEmail(userModel.getEmail()).getPassword())) {
      throw new CredentialsException(
          Map.of("password", "Please enter correct password"), "Incorrect password");
    }
    return true;
  }

  @Override
  public void activateUserByEmail(String email) {
    userRepository.findByEmailAndEnable(email);
  }

  @Override
  public void changeUserPasswordByEmail(String email, String newPassword) {
    userRepository.findByEmailAndChangePassword(email, passwordEncoder.encode(newPassword));
  }

  @Override
  public boolean userExistByEmail(String email) {
    return userRepository.getUserEntityByEmail(email).isPresent();
  }

  @Override
  public UserModel getAuthenticatedUser() {
    return Mapper.I.securityUserModelToModel(
        (SecurityUserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }
}
