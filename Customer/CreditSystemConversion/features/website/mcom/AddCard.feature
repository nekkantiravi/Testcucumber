Feature: Add Card

  @domain_customer @use_regression
  Scenario: Verify Add Card Functionality
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I clicked on Add Card button
    Then I should see citi fusion page

  @domain_customer @usea_regression
  Scenario Outline: Verify Add Card Functionality
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I clicked on Add Card button
    Then I should see citi fusion page
    Then I Enter "<card_type>" Information and add card
    And I complete security questions and return to macys
    Then I should be navigated to gateway page
    Examples:
      | card_type                              |
      | prop_card                              |
      | single_line_amex_card                  |
      | dual_amex_card                         |