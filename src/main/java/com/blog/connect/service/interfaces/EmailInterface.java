package com.blog.connect.service.interfaces;

import com.blog.connect.model.UserModel;

/**
 * GmailService.
 */
public interface EmailInterface {
  void sendAccountActivationLink(UserModel userModel);

  void sendAccountForgotPasswordLink(String email);
}
