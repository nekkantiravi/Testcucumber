Feature: DLP page sorting and applying filters

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on DLP page in domestic mode for featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "1.STATE" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "1.STATE" DLP page
    And I select "Featured" sort by drop down
    Then I should see products sorted by "Featured" on the "1.STATE" DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select random "Item Type" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "1.STATE" dlp page are shown according to "Featured" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for multiple facets and featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Villeroy & Boch" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Villeroy & Boch" DLP page
    When I select "Featured" sort by drop down
    Then I should see products sorted by "Featured" on the "Villeroy & Boch" DLP page
    And I tap on show more for all filters
    And I select "Price" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Villeroy & Boch" dlp page are shown according to "Featured" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Price" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Miu Miu" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Miu Miu" DLP page
    When I select "Best Sellers" sort by drop down
    Then I should see products sorted by "Best Sellers" on the "Miu Miu" DLP page
    And I tap on show more for all filters
    And I select "Color" facet from panel
    And I select random "Color" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Miu Miu" dlp page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Color" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for multiple facets and best sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Best Sellers" sort by drop down
    Then I should see products sorted by "Best Sellers" on the "Calvin Klein" DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Calvin Klein" dlp page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Newest" sort by drop down
    Then I should see products sorted by "Newest" on the "Calvin Klein" DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select random "Item Type" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Calvin Klein" dlp page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for multiple facets and newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Villeroy & Boch" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Villeroy & Boch" DLP page
    When I select "Newest" sort by drop down
    Then I should see products sorted by "Newest" on the "Villeroy & Boch" DLP page
    And I tap on show more for all filters
    And I select "Price" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Villeroy & Boch" dlp page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Price" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Abyss" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Abyss" DLP page
    When I select "Price: High to Low" sort by drop down
    Then I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "Color" facet from panel
    And I select random "Color" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: High to Low" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Color" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for multiple facets and high to low sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Wedgwood" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Wedgwood" DLP page
    When I select "Price: High to Low" sort by drop down
    Then I verify products sorted by "Price: High to Low" on UI
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: High to Low" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected


  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for low to high sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "7 For All Mankind" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "7 For All Mankind" DLP page
    When I select "Price: Low to High" sort by drop down
    Then I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select random "Item Type" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: Low to High" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify sort by functionality on dlp page in domestic mode for multiple facets and low to high sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Price: Low to High" sort by drop down
    Then I verify products sorted by "Price: Low to High" on UI
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I verify products sorted by "Price: Low to High" on UI
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on dlp page in domestic mode for featured sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Featured" sort by drop down
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Calvin Klein" dlp page are shown according to "Featured" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Department     |
      | Item Type      |
      | Designer       |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on dlp page in domestic mode for Best Sellers sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Best Sellers" sort by drop down
    Then I should see products sorted by "Best Sellers" on the "Calvin Klein" DLP page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Calvin Klein" dlp page are shown according to "Best Sellers" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Department     |
      | Item Type      |
      | Designer       |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on dlp page in domestic mode for Newest sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Newest" sort by drop down
    Then I should see products sorted by "Newest" on the "Calvin Klein" DLP page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Calvin Klein" dlp page are shown according to "Newest" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Department     |
      | Item Type      |
      | Designer       |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on dlp page in domestic mode for Price:High to Low  sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Price: High to Low" sort by drop down
    Then I should see products sorted by "Price: High to Low" on the "Calvin Klein" DLP page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Calvin Klein" dlp page are shown according to "Price: High to Low" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Department     |
      | Item Type      |
      | Designer       |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on dlp page in domestic mode for Price: Low to High sort option
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select "Calvin Klein" brand from the list
    Then I should be on DLP page
    When I should see "Featured" option selected as default on DLP page
    Then I should see products sorted by "Featured" on the "Calvin Klein" DLP page
    When I select "Price: Low to High" sort by drop down
    Then I should see products sorted by "Price: Low to High" on the "Calvin Klein" DLP page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on DLP page
    And I should see the breadcrumbs displayed
    Then I should see only products on "Calvin Klein" dlp page are shown according to "Price: Low to High" order and selected filters
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Department     |
      | Item Type      |
      | Designer       |
      | Sales & Offers |
      | Price          |
