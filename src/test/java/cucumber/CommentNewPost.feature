@Comment
Feature: Add Comment on a Blog/Post
  As a Logged in User of the application
  I want to write a comment to a Blog/Post successfully

  Background: User sees a blog on Homepage and clicks on it
    Given I am a logged in user

  @SuccessfulAddComment
  Scenario Outline: Successful Add Comment to a Blog/Post
    When I click "View" link
    And I fill in "commentary" with "<message>"
    And I click on the "Comment" button
    Examples:
      | message |
      | TestCucumber    |