Feature: Component tests for Search page

  ### Domestic and Iship modes ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a single facet category on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies size facet value on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I reselect "Size" facet from the filters section
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a single facet category on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies size facet value on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I reselect "Size" facet from the filters section
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a single facet category on search page and refresh the page in domestic
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    When I refresh the page
    Then I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies size facet value on search page in domestic mode and refresh the page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    When I refresh the page
    Then I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I reselect "Size" facet from the filters section
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a single facet category on search page and refresh the page in iship
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    When I refresh the page
    Then I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies size facet value on search page in iship mode and refresh the page
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    When I refresh the page
    Then I should see the breadcrumbs displayed
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I reselect "Size" facet from the filters section
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a multiple facet category on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies size facet values within a multiple facet category on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I reselect "Size" facet from the filters section
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a multiple facet category on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies size facet values within a multiple facet category on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I reselect "Size" facet from the filters section
    Then I should not see the facets as selected
