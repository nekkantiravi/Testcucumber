Feature: Component tests for Browse Page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category and clear all button on browse page for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    When I tap on clear all button
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
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category and clear all button on browse page for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    When I tap on clear all button
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

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category on browse page for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
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
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category on browse page for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
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
      | Sales & Offers |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category on browse page and refresh the page for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    When I refresh the page
    Then I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
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
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category on browse page and refresh the page for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    When I refresh the page
    Then I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
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

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies mulitple facet values within a single facet category and clear all button on browse page for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    When I tap on clear all button
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
  Scenario Outline: BCOM BrowsePage - User applies mulitple facet values within a single facet category and clear all button on browse page for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    When I tap on clear all button
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
  Scenario Outline: BCOM BrowsePage - User applies mulitple facet values within a single facet category on browse page for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
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
  Scenario Outline: BCOM BrowsePage - User applies mulitple facet values within a single facet category on browse page for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
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

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies multiple facet values within a single facet category on browse page and refresh the page for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    When I refresh the page
    Then I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
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
  Scenario Outline: BCOM BrowsePage - User applies mulitple facet values within a single facet category on browse page and refresh the page for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    When I refresh the page
    Then I should see the breadcrumbs displayed
    And I should see only products on browse page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
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

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet on browse page and verify all facet values updated accordingly
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "Fit" facet from panel
    And I select random "Fit" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    When I tap on show more for all filters
    Then I should see facet values updated based on previous selection for browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |
