Feature: Sign in as a user

  @creative
  Scenario Outline: sign in validation
    Given I visit the web site as a <user_type> user
    And I click on "logo" link in the header
    And I verify sign in landing page
    Examples:
      | user_type  |
      | registered |


  @creative
  Scenario: verify create profile
    Given I visit the web site as a guest user
    And I click on "sign in" link in the header
    And I click on create profile button
    And I create a new profile