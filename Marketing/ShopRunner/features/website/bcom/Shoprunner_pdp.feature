Feature: ShopRunner feature on PDP pages

  @domain_marketing @project_ShopRunner @priority_high @mode_domestic @use_regression
 Scenario: Verify the Shoprunner eligibility on PDP page of standard product
   Given I visit the web site as a guest user
   When I navigate to the "Booties" browse page under "SHOES"
   And I select a random member product
   Then I should be redirected to PDP page
   And I should see the "FREE 2-Day Shipping & Free Returns" Shoprunner eligibility text

  @domain_marketing @project_ShopRunner @priority_high @mode_domestic @use_regression
  Scenario: Verify the Shoprunner eligibility on PDP page of member product
    Given I visit the web site as a guest user
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    And I should see the "FREE 2-Day Shipping & Free Returns" Shoprunner eligibility text

  @domain_marketing @project_ShopRunner @priority_high @mode_domestic @use_regression
  Scenario: Verify the Shoprunner eligibility on master PDP page
    Given I visit the web site as a guest user
    When I mouse over "Home" category from top navigation
    And I select "Bedding Collections" subcategory from flyout menu
    And I select a random master product
    Then I should be redirected to PDP page
    And I should see member products listed
    And I should see the "FREE 2-Day Shipping & Free Returns" Shoprunner eligibility text
    And I should see the shoprunner eligible logo on member products

  @domain_marketing @project_ShopRunner @priority_high @mode_domestic @use_regression
  Scenario: Verify the shoprunner eligibility should not display on Chanel PDP
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" browse page under "BEAUTY"
    Then I should be on "CHANEL" subsplash page
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I select a random chanel product from browse page
    And I should be redirected to PDP page
    Then I should not see the FREE two Day Shipping Free Returns Shoprunner eligibility text

  @domain_marketing @project_ShopRunner @priority_high @mode_domestic @use_regression
  Scenario: Verify the shoprunner eligibility not displayed on registry products pdp
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "HOME CARE & TECH" category from top navigation
    And I select "Home Tech" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    And I should not see the FREE two Day Shipping Free Returns Shoprunner eligibility text

  @domain_marketing @project_ShopRunner @priority_high @mode_domestic @manual
  Scenario: Verify the shoprunner eligibility not displayed on ISHIP product PDP
    Given I visit the web site as a registered user
    And I navigate to Iship mode
    When  I navigate to Iship PDP page
    Then I should not see the FREE two Day Shipping Free Returns Shoprunner eligibility text