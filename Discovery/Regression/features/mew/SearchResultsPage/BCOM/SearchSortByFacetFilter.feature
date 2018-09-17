Feature: Search results page sorting and applying filters

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    And I select "Featured" sort by drop down
    Then I should see products sorted by "Featured" on the search results page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select random "Department" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Featured" order and selected filters
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Featured" sort option on search page
    Then I should see only products on search page are shown according to "Featured" order and selected filters
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Featured" sort option on search page
    And I should see only products on search page are shown according to "Featured" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for multiple facets and featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Featured" sort by drop down
    Then I should see products sorted by "Featured" on the search results page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Featured" order and selected filters
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Featured" sort option on search page
    Then I should see only products on search page are shown according to "Featured" order and selected filters
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Featured" sort option on search page
    And I should see only products on search page are shown according to "Featured" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Best Sellers" sort by drop down
    Then I should see products sorted by "Best Sellers" on the search results page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select random "Department" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Best Sellers" order and selected filters
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Best Sellers" sort option on search page
    Then I should see only products on search page are shown according to "Best Sellers" order and selected filters
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Best Sellers" sort option on search page
    And I should see only products on search page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for multiple facets and best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Best Sellers" sort by drop down
    Then I should see products sorted by "Best Sellers" on the search results page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Best Sellers" order and selected filters
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Best Sellers" sort option on search page
    Then I should see only products on search page are shown according to "Best Sellers" order and selected filters
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Best Sellers" sort option on search page
    And I should see only products on search page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Newest" sort by drop down
    Then I should see products sorted by "Newest" on the search results page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select random "Department" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Newest" order and selected filters
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Newest" sort option on search page
    Then I should see only products on search page are shown according to "Newest" order and selected filters
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Newest" sort option on search page
    And I should see only products on search page are shown according to "Newest" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for multiple facets and newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Newest" sort by drop down
    Then I should see products sorted by "Newest" on the search results page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Newest" order and selected filters
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Newest" sort option on search page
    Then I should see only products on search page are shown according to "Newest" order and selected filters
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Newest" sort option on search page
    And I should see only products on search page are shown according to "Newest" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Price: High to Low" sort by drop down
    Then I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select random "Fit" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: High to Low" on UI
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Price: High to Low" sort option on search page
    Then I verify products sorted by "Price: High to Low" on UI
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Price: High to Low" sort option on search page
    Then I verify products sorted by "Price: High to Low" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for multiple facets and high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Price: High to Low" sort by drop down
    Then I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Price: High to Low" sort option on search page
    And I verify products sorted by "Price: High to Low" on UI
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Price: High to Low" sort option on search page
    And I verify products sorted by "Price: High to Low" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for featured low to high option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Price: Low to High" sort by drop down
    Then I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select random "Fit" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: Low to High" on UI
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Price: Low to High" sort option on search page
    Then I verify products sorted by "Price: Low to High" on UI
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Price: Low to High" sort option on search page
    Then I verify products sorted by "Price: Low to High" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for multiple facets and low to high sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "shirts" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Price: Low to High" sort by drop down
    Then I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    When I navigate to next page on search page
    Then current page number should be equal 2
    And I should see "Price: Low to High" sort option on search page
    And I verify products sorted by "Price: Low to High" on UI
    When I navigate to prev page on search
    Then current page number should be equal 1
    And I should see "Price: Low to High" sort option on search page
    And I verify products sorted by "Price: Low to High" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in domestic mode for featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    When I should see "Featured" option selected as default on search page
    Then I should see products sorted by "Featured" on the search results page
    When I select "Featured" sort by drop down
    Then I should see products sorted by "Featured" on the search results page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Featured" order and selected filters
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Fit            |
      | Wash           |
      | Designer       |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in domestic mode for best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I should see "Featured" option selected as default on search page
    And I should see products sorted by "Featured" on the search results page
    And I select "Best Sellers" sort by drop down
    And I should see products sorted by "Best Sellers" on the search results page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Best Sellers" order and selected filters
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Fit            |
      | Wash           |
      | Designer       |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in domestic mode for Newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I should see "Featured" option selected as default on search page
    And I should see products sorted by "Featured" on the search results page
    And I select "Newest" sort by drop down
    And I should see products sorted by "Newest" on the search results page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    Then I should see only products on search page are shown according to "Newest" order and selected filters
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Fit            |
      | Wash           |
      | Designer       |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in domestic mode for low to high sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I should see "Featured" option selected as default on search page
    And I should see products sorted by "Featured" on the search results page
    And I select "Price: High to Low" sort by drop down
    And I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    And I verify products sorted by "Price: High to Low" on UI
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Fit            |
      | Wash           |
      | Designer       |
      | Color          |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on search results page in domestic mode for high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I should see "Featured" option selected as default on search page
    And I should see products sorted by "Featured" on the search results page
    And I select "Price: Low to High" sort by drop down
    And I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    And I verify products sorted by "Price: Low to High" on UI
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Fit            |
      | Wash           |
      | Designer       |
      | Sales & Offers |
      | Price          |


  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on search results page in domestic mode for high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "cookware" in mew search and click enter
    Then I should be on the search results page
    And I should see "Featured" option selected as default on search page
    And I should see products sorted by "Featured" on the search results page
    And I select "Price: Low to High" sort by drop down
    And I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "Material" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    And I should see the breadcrumbs displayed
    And I verify products sorted by "Price: Low to High" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I should see the products without any facet values in bcom search page
    When I tap on show more for all filters
    And I select "Material" facet from panel
    Then I should not see the facets as selected
