Feature: Component tests for Browse Page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
      | Electrics             |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Item Type      |
      | Brand          |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies facet values within a single facet category on browse page and refresh the page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
      | Electrics             |
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
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Item Type      |
      | Brand          |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies multiple facet values within a single facet category on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
      | Electrics             |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Item Type      |
      | Brand          |
      | Sales & Offers |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM BrowsePage - User applies multiple facet values within mulitple facets on browse page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
      | Electrics             |
    Then I should be on the browse page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I select "Brand" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the browse page
    And I should see the breadcrumbs displayed
    Then I should see only products on browse page with selected facet values
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies multiple facet values within a single facet category on browse page and refresh the page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
      | Electrics             |
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
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom browse page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Item Type      |
      | Brand          |
      | Sales & Offers |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies single facet value within a single facet category on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
      | Electrics             |
    Then I should be on the browse page
    When I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should see the breadcrumbs displayed
    When I tap on show more for all filters
    And I select "<facet1>" facet from panel
    And I select random "<facet1>" facet from the selected category
    And I click apply for the facet selected
    Then I should see the breadcrumbs displayed
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
      | facet     | facet1 |
      | Item Type | Brand  |
      | Brand     | Price  |
      | Item Type | Price  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM BrowsePage - User applies multiple facet value within a single facet category on browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
      | Electrics             |
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
      | facet     | facet1 |
      | Item Type | Brand  |
      | Brand     | Price  |
      | Item Type | Price  |


