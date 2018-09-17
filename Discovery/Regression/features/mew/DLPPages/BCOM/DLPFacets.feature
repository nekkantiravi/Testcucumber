Feature: Component tests for DLP page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies Color facet values within a single facet category on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I tap on show more for all filters
    And I select "Color" facet from panel
    And I select random "Color" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Color" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies Price facet values within a single facet category on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I tap on show more for all filters
    And I select "Price" facet from panel
    And I select random "Price" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Price" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies Sales&Offers facet values within a single facet category on DLP page
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I tap on show more for all filters
    And I select "Sales & Offers" facet from panel
    And I select random "Sales & Offers" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Sales & Offers" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies Item Type facet values within a single facet category on DLP page
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select random "Item Type" facet from the selected category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies Department facet values within a single facet category on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I tap on show more for all filters
    And I select "Department" facet from panel
    And I select random "Department" facet from the selected category
    And I click apply for the facet selected
    And I should see all products displayed in DLP page
    Then I should see a facet values "DLP" page breadcrumb
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Department" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Selected facet values in breadcrumb should not clear after refreshing the page on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I tap on show more for all filters
    And I select "Size" facet from the filters section
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I refresh current page
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Size" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Selected facet values in breadcrumb should not clear after navigating to the next page on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    When I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select random "Item Type" facet from the selected category
    And I click apply for the facet selected
    And I should see all products displayed in DLP page
    And I should see a facet values "DLP" page breadcrumb
    When I navigate to next page on DLP page
    Then I should see a facet values "DLP" page breadcrumb
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline:  User applies multiple facet values within multiple facet categories on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select random "Item Type" facet from the selected category
    And I click apply for the facet selected
    And I tap on show more for all filters
    And I select "Price" facet from panel
    And I select random "Price" facet from the selected category
    And I click apply for the facet selected
    And I should see all products displayed in DLP page
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected
    And I select "Price" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies multiple facet values within a single facet category
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    And I should see all products displayed in DLP page
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies multiple facets in different categories on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I tap on show more for all filters
    And I select "Color" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I tap on show more for all filters
    And I select "Price" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    Then I should not see the facets on breadcrumb panel
    And I tap on show more for all filters
    And I select "Color" facet from panel
    Then I should not see the facets as selected
    And I select "Price" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User applies multiple facets from size facet category
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
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
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User clears facet values from the selected category and confirms the results update to the original number on DLP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I tap on show more for all filters
    And I select "Color" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I tap on show more for all filters
    And I select "Color" facet from panel
    When I tap on clear all button
    Then I should not see selected facet values in facet selection screen
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: User removes the multiple breadcrumbs from a single facet category
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I should see all products displayed in DLP page
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    And I select multiple facets within a single facet category
    And I click apply for the facet selected
    And I should see the breadcrumbs displayed
    When I remove the facets from the breadcrumb
    And I tap on show more for all filters
    And I select "Item Type" facet from panel
    Then I should not see the facets as selected
    Examples:
      | designer          |
      | 1.STATE           |
      | 7 For All Mankind |
      | Wedgwood          |
      | Adidas            |
