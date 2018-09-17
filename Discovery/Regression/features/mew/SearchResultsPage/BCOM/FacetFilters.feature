Feature: Filter products by facets

  ### Domestic and Iship modes ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a single facet category on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I tap on show more for all filters
    And I select "<facet1>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    Then I should not see the facets as selected
    And I select "<facet1>" facet from panel
    Then I should not see the facets as selected
    Examples:
      | facet          | facet1         |
      | Sales & Offers | Designer       |
      | Item Type      | Sales & Offers |
      | Designer       | Color          |
      | Price          | Item Type      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies facet values within a single facet category on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies multiple facet values on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |
      | Price          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies single facet value on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select random "<facet>" facet from the selected category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BCOM SearchResultsPage - User applies multiple size facet values on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "<facet>" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword
    Examples:
      | facet          |
      | Color          |
      | Sales & Offers |
      | Item Type      |
      | Designer       |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies facet values within a single facet category on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies multiple facet values on search page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies facet values within a single facet category on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select random "Size" facet from the selected category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I clear the breadcrumbs selected
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM SearchResultsPage - User applies multiple facet values on search page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "Shirts" in mew search and click enter
    Then I should be in Search Landing page using mobile website
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    Then I should be in Search Landing page using mobile website
    And I should see the breadcrumbs displayed
    And I should see only products on search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I verify search page data is displayed correctly for "Shirts" keyword