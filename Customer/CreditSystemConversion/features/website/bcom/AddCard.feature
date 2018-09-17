Feature: Add Card navigation

  @domain_customer @use_regression
  Scenario: Verify Add Card citi navigation
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I clicked on Add Card button
    Then I should see citi fusion page
