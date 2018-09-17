Feature: As a Chanel stakeholder, I expect to see all of the necessary content suppressed on member and master PDPs.

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify that the chanel page have a banner
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "CHANEL" from the list
    Then I should see a chanel banner

  @domain_mew_discovery @use_mew_regression
  Scenario: Chanel category should be in capitalized and black background and white letters when selected
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "CHANEL" from the list
    Then I should see the chanel category capitalized
    And I should see the chanel in white and in black background
    And I should see the chanel sub categories capitalized
    Then I should see the chanel sub categories in white and in black background when selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify that the following pages have a chanel banner
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "CHANEL" from the list
    And I navigate to "<page>" chanel category page
    Then I should see a chanel banner
    Examples:
      | page              |
      | WOMEN'S FRAGRANCE |
      | MEN'S FRAGRANCE   |
      | MAKEUP            |
      | SKIN CARE         |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on chanel UI page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "chanel" in mew search and click enter
    Then I should be on the browse page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on chanel search results page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "chanel" in mew search and click enter
    Then I should be on the browse page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on the category browse page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |
      | Featured           |
      | Best Sellers       |
      | Newest             |
