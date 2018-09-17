Feature: Verify special characters on mobile website

  #B-90104
  @use_regression
  Scenario Outline: Clicking on a PDP with a special character in brand name should not navigate to ZSR page (MEW)
    Given I visit the mobile web site as a guest user
    And I search for "<brand>"
    And I click on a PDP
    When I click on the brand name
    Then I verify that "zero search result" page is not displayed
    Then I verify there are products on the page
    Examples:
      | brand   |
      | lancome |