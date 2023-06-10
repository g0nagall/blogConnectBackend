@ForgotPassword

Feature: Forgot Password

  As a Registered User of the application

  I want to reset/regenerate my password via Forgot Password functionality




  Background: A Registered User navigates to Login page

    Given I am a registered user

    And I navigate to the "Login" page

    And I click "Forgot Password" link




  @SuccessfulForgotPassword

  Scenario Outline: Successful submit of forgot password form with valid email

    When I fill in "email" with "<email>"

    And I click on "Change Password" button

    Then I should land on the "Login" page

    Examples:

      | email     |

      | test@test |




  @failedForgotPassword

  Scenario Outline: Failed submit of forgot password form with invalid email

    When I fill in "email" with "<email>"

    And I click on "Change Password" button

    Then I should see "<form error>" message for "<input field>" field on "Login" page

    Examples:

      | email | form error            | input field |

      |       | Email cannot be empty | email       |