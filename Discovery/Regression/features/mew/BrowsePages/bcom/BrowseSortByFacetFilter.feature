Feature: Browse page sorting and applying filters

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Featured" sort by drop down on category browse page
    Then I should see products sorted by "Featured" on the category browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select random "Heel Height" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Featured" order and selected filters
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Featured" sort option on browse page
    Then I should see only products on browse page are shown according to "Featured" order and selected filters
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Featured" sort option on browse page
    And I should see only products on browse page are shown according to "Featured" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for multiple facets and featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Featured" sort by drop down on category browse page
    Then I should see products sorted by "Featured" on the category browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Featured" order and selected filters
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Featured" sort option on browse page
    Then I should see only products on browse page are shown according to "Featured" order and selected filters
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Featured" sort option on browse page
    And I should see only products on browse page are shown according to "Featured" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Best Sellers" sort by drop down on category browse page
    Then I should see products sorted by "Best Sellers" on the category browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select random "Heel Height" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Best Sellers" order and selected filters
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Best Sellers" sort option on browse page
    Then I should see only products on browse page are shown according to "Best Sellers" order and selected filters
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Best Sellers" sort option on browse page
    And I should see only products on browse page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for multiple facets and best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Best Sellers" sort by drop down on category browse page
    Then I should see products sorted by "Best Sellers" on the category browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Best Sellers" order and selected filters
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Best Sellers" sort option on browse page
    Then I should see only products on browse page are shown according to "Best Sellers" order and selected filters
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Best Sellers" sort option on browse page
    And I should see only products on browse page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Newest" sort by drop down on category browse page
    Then I should see products sorted by "Newest" on the category browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select random "Heel Height" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Newest" order and selected filters
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Newest" sort option on browse page
    Then I should see only products on browse page are shown according to "Newest" order and selected filters
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Newest" sort option on browse page
    And I should see only products on browse page are shown according to "Newest" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for multiple facets and newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Newest" sort by drop down on category browse page
    Then I should see products sorted by "Newest" on the category browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Newest" order and selected filters
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Newest" sort option on browse page
    Then I should see only products on browse page are shown according to "Newest" order and selected filters
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Newest" sort option on browse page
    And I should see only products on browse page are shown according to "Newest" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Price: High to Low" sort by drop down on category browse page
    Then I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select random "Heel Height" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: High to Low" on UI
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Price: High to Low" sort option on browse page
    Then I verify products sorted by "Price: High to Low" on UI
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Price: High to Low" sort option on browse page
    Then I verify products sorted by "Price: High to Low" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for multiple facets and high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Price: High to Low" sort by drop down on category browse page
    Then I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Price: High to Low" sort option on browse page
    And I verify products sorted by "Price: High to Low" on UI
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Price: High to Low" sort option on browse page
    And I verify products sorted by "Price: High to Low" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for featured low to high option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Price: Low to High" sort by drop down on category browse page
    Then I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select random "Heel Height" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: Low to High" on UI
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Price: Low to High" sort option on browse page
    Then I verify products sorted by "Price: Low to High" on UI
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Price: Low to High" sort option on browse page
    Then I verify products sorted by "Price: Low to High" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on browse results page in domestic mode for multiple facets and low to high sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | SHOES     |
      | All Shoes |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Price: Low to High" sort by drop down on category browse page
    Then I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    When I navigate to next page on browse page
    Then current browse page number should be equal to 2
    And I should see "Price: Low to High" sort option on browse page
    And I verify products sorted by "Price: Low to High" on UI
    When I navigate to prev page on browse
    Then current browse page number should be equal to 1
    And I should see "Price: Low to High" sort option on browse page
    And I verify products sorted by "Price: Low to High" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Heel Height" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on browse results page in domestic mode for featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    When I should see "Featured" option selected as default on browse page
    Then I should see products sorted by "Featured" on the category browse page
    When I select "Featured" sort by drop down on category browse page
    Then I should see products sorted by "Featured" on the category browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Featured" order and selected filters
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
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
  Scenario Outline: Verify sort by functionality on browse results page in domestic mode for best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    And I should see "Featured" option selected as default on browse page
    And I should see products sorted by "Featured" on the category browse page
    And I select "Best Sellers" sort by drop down on category browse page
    And I should see products sorted by "Best Sellers" on the category browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Best Sellers" order and selected filters
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
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
  Scenario Outline: Verify sort by functionality on browse results page in domestic mode for Newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    And I should see "Featured" option selected as default on browse page
    And I should see products sorted by "Featured" on the category browse page
    And I select "Newest" sort by drop down on category browse page
    And I should see products sorted by "Newest" on the category browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page are shown according to "Newest" order and selected filters
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
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
  Scenario Outline: Verify sort by functionality on browse results page in domestic mode for low to high sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    And I should see "Featured" option selected as default on browse page
    And I should see products sorted by "Featured" on the category browse page
    And I select "Price: High to Low" sort by drop down on category browse page
    And I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I verify products sorted by "Price: High to Low" on UI
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
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
  Scenario Outline: Verify sort by functionality on browse results page in domestic mode for high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should see the "category_browse" Page
    And I should see "Featured" option selected as default on browse page
    And I should see products sorted by "Featured" on the category browse page
    And I select "Price: Low to High" sort by drop down on category browse page
    And I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I verify products sorted by "Price: Low to High" on UI
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
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
