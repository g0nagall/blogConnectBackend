package com.blog.connect.service.interfaces;

import com.blog.connect.model.SecurityUserModel;
import com.blog.connect.model.UserModel;

/** Interface for user operations. */
public interface UserInterface {
  /**
   * Get SecurityUserModel by email.
   *
   * @param email email.
   * @return SecurityUser.
   */
  SecurityUserModel getUserByEmail(String email);

  /**
   * Register user.
   *
   * @param userModel UserModel with credentials.
   * @return UserModel of created user.
   */
  UserModel registerUser(UserModel userModel);

  /**
   * Update user.
   *
   * @param userModel UserModel with credentials.
   * @return UserModel of updated user.
   */
  UserModel updateUser(UserModel userModel);

  /**
   * Check if password is correct.
   *
   * @param userModel userModel with credentials.
   * @return true if correct.
   */
  boolean isCorrectPassword(UserModel userModel);

  /**
   * Activate user by email.
   *
   * @param email user email.
   */
  void activateUserByEmail(String email);

  /**
   * Change user password by email.
   *
   * @param email user email.
   * @param newPassword user new password.
   */
  void changeUserPasswordByEmail(String email, String newPassword);

  /**
   * Check if user exist by email.
   *
   * @param email user email.
   * @return true or false.
   */
  boolean userExistByEmail(String email);

  /**
   * Get authenticated user from spring context.
   *
   * @return UserModel.
   */
  UserModel getAuthenticatedUser();
}
