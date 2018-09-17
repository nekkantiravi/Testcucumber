Feature: Search results page sorting in domestic

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify default sort by option on search results page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "jeans" in mew search and click enter
    Then I should be on the search results page
    And I should see "featured" option selected as default on search page
    And I should see products sorted by "Featured" on the search results page

  @dsv_mew_sev1 @domain_discovery
  Scenario: Verify sort by functionality on search results page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I search using mobile website for "cookware"
    Then I should be in Search Landing page using mobile website
    Then I should see sort by functionality with below options using mobile website:
      | Featured           |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |

  #B-84476
  @domain_mew_discovery @use_mew_regression
  Scenario Outline: On a search page, applying facet should retain the sort by
    Given I visit the mobile web site as a guest user
    And I search for "<keyword>"
    And I select "<sort_by>" sort by drop down
    And I select "<facet>" facet on left nav using mobile website
    And I select "<value>" sub facet from left nav using mobile website
    When I apply the facets
    Then I verify "<sort_by>" is selected in sort by drop down
    Examples:
      | keyword | sort_by            | facet    | value |
      | dresses | Price: Low to High | Occasion | Day   |
      | coats   | Best Sellers       | Color    | Black |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on the search results page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |
      | Featured           |
      | Best Sellers       |
      | Newest             |


  @domain_mew_discovery @use_mew_regression
  Scenario: Search page - Verify user is navigating back to first page when he select any sort functionality from different page other than first in dometic
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down
    And current page number should be equal 1


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | keyword | sort_by            |
      | Jeans   | Price: Low to High |
      | beds    | Price: High to Low |


   # iship mode

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on the search results page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |
      | Featured           |
      | Best Sellers       |
      | Newest             |


  @domain_mew_discovery @use_mew_regression
  Scenario: Search page - Verify user is navigating back to first page when he select any sort functionality from different page other than first in iship
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Jeans" in mew search and click enter
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down
    And current page number should be equal 1


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | keyword | sort_by            |
      | Jeans   | Price: Low to High |
      | beds    | Price: High to Low |

   #registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page
    Given I visit the mobile web site as a guest user in registry mode
    When I type "plates" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on the search results page
    Examples:
      | sort_by            |
      | Price: Low to High |
      | Price: High to Low |
      | Featured           |
      | Best Sellers       |
      | Newest             |


  @domain_mew_discovery @use_mew_regression
  Scenario: Search page - Verify user is navigating back to first page when he select any sort functionality from different page other than first in registry
    Given I visit the mobile web site as a guest user in registry mode
    When I type "cookware" in mew search and click enter
    When I navigate to a random page other than first page
    Then selected page should be displayed in pagination
    And I select "Price: High to Low" sort by drop down
    And current page number should be equal 1


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on UI page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I select "<sort_by>" sort by drop down
    Then I should see products sorted by "<sort_by>" on UI page
    Examples:
      | keyword  | sort_by            |
      | cookware | Price: Low to High |
      | beds     | Price: High to Low |
