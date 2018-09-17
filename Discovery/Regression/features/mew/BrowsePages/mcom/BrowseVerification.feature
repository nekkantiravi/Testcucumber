Feature: Browse page verification

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify all basic attributes in browse page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Jeans |
    Then I should be on the browse page
    And I verify the basic attributes of browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify all basic attributes on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should be on the registry browse page
    And I verify the basic attributes of browse page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify google ads are displaying in browse page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Jeans |
    Then I should be on the browse page
    And I should see google ads in browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify google ads are displaying in browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should be on the registry browse page
    And I should see google ads in browse page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify back to top functionality on browse page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Jeans |
    Then I should be on the browse page
    And I navigate to bottom of page
    Then I should see back to top button
    And I navigate to top of page
    Then I should not see back to top button
    And I navigate to bottom of page
    And I tap on back to top button
    Then I should navigate to top of the page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify back to top functionality on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should be on the registry browse page
    And I navigate to bottom of page
    Then I should see back to top button
    And I navigate to top of page
    Then I should not see back to top button
    And I navigate to bottom of page
    And I tap on back to top button
    Then I should navigate to top of the page