Feature: Component tests for Search Page in registry mode

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchPage - User applies facet values within a single facet category on search page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    Then I should see only products on search page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Luggage Type   |
      | Features       |
      | Designer       |
      | Department     |
      | Color          |
      | Price          |
      | Sales & Offers |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchPage - User applies facet value within size facet category on search page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    Then I should see only products on search page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchPage - User applies facet values within a single facet category on search page and refresh the page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    When I refresh the page
    Then I should see only products on search page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Luggage Type   |
      | Features       |
      | Designer       |
      | Department     |
      | Color          |
      | Price          |
      | Sales & Offers |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchPage - User applies facet values within size facet category on search page and refresh the page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    Then I should be on the search results page
    When I refresh the page
    Then I should see only products on search page with selected facet values
    And I should see that selected facet and breadcrumb facet is same
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchPage - User applies multiple facet values within a single facet category on search page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    Then I should see only products on search page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Luggage Type   |
      | Features       |
      | Designer       |
      | Department     |
      | Color          |
      | Price          |
      | Sales & Offers |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchPage - User applies multiple facet values within size facet category on search page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    Then I should see only products on search page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    Then I should not see the facets as selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchPage - User applies multiple facet values within a single facet category on search page and refresh the page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    When I refresh the page
    Then I should see only products on search page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          |
      | Luggage Type   |
      | Features       |
      | Designer       |
      | Department     |
      | Color          |
      | Price          |
      | Sales & Offers |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchPage - User applies multiple facet values within size facet category on search page and refresh the page for registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "Luggage" in mew search and click enter
    Then I should be on the search results page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be on the search results page
    When I refresh the page
    Then I should see only products on search page with selected facet values
    And I should see that selected facets and breadcrumb facets are same
    When I clear all the breadcrumb values
    Then I should not see the facets on breadcrumb panel
    Then I should see the products without any facet values in bcom search page
    And I tap on show more for all filters
    And I select "Size" facet from panel
    Then I should not see the facets as selected