@Profile

Feature: View/Edit Profile

  As a Registered User of the application

  I want to view and update my profile




  Background: User is logged in and is on Homepage

    Given I am a logged in user




  @ViewProfile

  Scenario: Successful View Profile

    When I click "Profile" link

    Then I should land on the "Profile" page




  @EdiProfile

  Scenario Outline: Successful Edit Profile

    When I click "Profile" link

    And I click on "Modify info" button

    And I fill in "email" with "<email>"

    And I fill in "username" with "<username>"

    And I click on the "Modify" button

    Then I should land on the "Profile" page

    Examples:

      | email     | username |

      | test@test | test     |