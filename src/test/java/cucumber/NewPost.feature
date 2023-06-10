@BlogPost
Feature: Add Blog/Post
  As a Logged in User of the application
  I want to write a Blog/Post successfully

  Background: User is logged in and is on Homepage
    Given I am a logged in user

  @SuccessfulLandOnAddBlogPost
  Scenario: Successful landing on Add a Blog/Post
    When I click on the "Create Post" button
    Then I should land on the "Create Post" page

  @SuccessfulAddBlogPost
  Scenario Outline: Successful creation of a Blog/Post
    When I click on the "Create Post" button
    And I fill in "postTheme" with "<theme>"
    And I fill in "postContent" with "<content>"
    And I click on the "Create" button
    Then I should land on the "All Posts" page
    And I should see the new blog listing on the Homepage
    Examples:
      | theme      | content                |
      | CucumberTheme | CucumberContent |