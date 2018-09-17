Feature: Verify Chanel Browse Pages

  #B-72255
  @domain_discovery @use_regression
  Scenario: Verify breadcrumbs exists on Chanel pages
    Given I visit the web site as a guest user
    And I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "MAKEUP" facet listed on left nav
    And I select "FACE" facet listed on left nav
    And I select "MAKEUP" from breadcrumb
    Then I should be on "MAKEUP" subsplash page
    And I verify the title for "Chanel Makeup" category page
    When I select "EYES" facet listed on left nav
    Then I should be on "EYES" subsplash page
    And I verify the title for "Eyes" category page

  #B-72255
  @domain_discovery @use_regression
  Scenario Outline: Back Button on Chanel sub category pages should not go to All Beauty
    Given I visit the web site as a guest user
    And I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "<subcategory>" facet listed on left nav
    And I select "SHOP ALL CHANEL" facet listed on left nav
    Then I should be on "CHANEL" subsplash page
    And I verify the title for "CHANEL" category page
    Examples:
      | subcategory |
      | WOMEN'S FRAGRANCE |
      | MAKEUP |