Feature: Filter products by facets on browse page

  ### Domestic and Iship modes ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowseResultsPage - User applies single facet value within a single facet category on browse page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I tap on show more for all filters
    And I select "<facet1>" facet from panel
    And I select random "<facet1>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    And I select "<facet1>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          | facet1         |
      | Color          | Price          |
      | Sales & Offers | Wash           |
      | Wash           | Sales & Offers |
      | Fit            | Color          |
      | Price          | Wash           |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowseResultsPage - User applies multiple facet values within a single facet category on browse page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I tap on show more for all filters
    And I select "<facet1>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    And I select "<facet1>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          | facet1         |
      | Sales & Offers | Designer       |
      | Wash           | Sales & Offers |
      | Designer       | Color          |
      | Price          | Wash           |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowseResultsPage - User applies single facet value within a single facet category on browse page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I tap on show more for all filters
    And I select "<facet1>" facet from panel
    And I select random "<facet1>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    And I select "<facet1>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          | facet1         |
      | Color          | Fit            |
      | Sales & Offers | Designer       |
      | Color          | Sales & Offers |
      | Designer       | Color          |
      | Sales & Offers | Color          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowseResultsPage - User applies multiple facet values within a single facet category on browse page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I tap on show more for all filters
    And I select "<facet1>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    And I select "<facet1>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          | facet1         |
      | Sales & Offers | Designer       |
      | Designer       | Sales & Offers |
      | Designer       | Color          |
      | Wash           | Designer       |
