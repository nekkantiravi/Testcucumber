Feature: Remove utag_data variable stub

  @Tealium
  Scenario: Verify the utag_data variable stub for PDPApp page is removed
    Given I visit the web site as a guest user
    When I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I select a random chanel product from browse page
    Then I should be redirected to PDP page
    Then I should verify "var utag_data = {};" is not present

  @Tealium
  Scenario: Verify the utag_data variable stub for ShopApp page is removed
    Given I visit the web site as a guest user
    When I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I select a random chanel product from browse page
    Then I should be redirected to PDP page
    When I add product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a guest user
    Then I should verify "var utag_data = {};" is not present

  @Tealium
  Scenario: Verify the utag_data variable stub for Browse(NavApp) page is removed
    Given I visit the web site as a guest user
    When I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    Then I should verify "var utag_data = {};" is not present


