Feature: Home Page
  @domain_Experimentation
  Scenario: To verify HomePage functionality
    Given I visit the web site as a guest user
    Then  I verify the Fob's displayed:
      |HOME                 |
      |BED & BATH           |
      |WOMEN                |
      |MEN                  |
      |JUNIORS              |
      |KIDS                 |
      |SHOES                |
      |HANDBAGS & ACCESSORIES|
      |JEWELRY              |
      |WATCHES              |
      |BRANDS               |


  Scenario: To verify if page is navigating to selected random FOB's
    Given I visit the web site as a guest user
    And  I navigate to the "Dresses" browse page under "WOMEN"
    Then I should navigate to "Dresses" sub category page

  Scenario: To create user profile
    Given I visit the web site as a guest user
    When  I click on SignIn link on home page
    Then I click on create button on sign in page
    And  I click on Create button after filling all the required details
    Then  I should navigate to Home page

  Scenario: To verify sign in with valid credentials
    Given I visit the web site as a guest user
    When  I click on SignIn link on home page
    And I enter valid email-id and password
    And I click on SignIn button
   Then I should navigate to Home page


