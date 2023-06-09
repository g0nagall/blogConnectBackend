package com.blog.connect.service.implementations;

import com.blog.connect.model.JwtTypeModel;
import com.blog.connect.model.UserModel;
import com.blog.connect.service.interfaces.EmailInterface;
import com.blog.connect.service.interfaces.TokenInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** GmailServiceImpl. */
@Service
@RequiredArgsConstructor
public class EmailImpl implements EmailInterface {
  private final JavaMailSender emailSender;
  private final TokenInterface tokenInterface;

  private void sendMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }

  @Override
  public void sendAccountActivationLink(UserModel userModel) {
    sendMessage(
        userModel.getEmail(),
        "Activate your account",
        "Click here to activate your account:"
            + " http://localhost:3000/account/email/activation/"
            + tokenInterface.createJwt(userModel.getEmail(), JwtTypeModel.EMAIL_ACTIVATION));
  }

  @Override
  public void sendAccountForgotPasswordLink(String email) {
    sendMessage(
        email,
        "Change your password",
        "Click here to change your password:"
            + "http://localhost:3000/account/password/forget/"
            + tokenInterface.createJwt(email, JwtTypeModel.PASSWORD_FORGOT));
  }
}
