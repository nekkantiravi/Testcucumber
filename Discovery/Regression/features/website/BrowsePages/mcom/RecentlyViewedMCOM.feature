#Author: Discovery SNBC QE
#Date Created: 24/08/2017


Feature: Verify Recently View Items Panel on Category Splash Page

  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp
  Scenario: BrowsePage - Verify the RVI functionality for max products, recent items and products move after removing items
    Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
    When I construct RVI cookie with 30 products and reload the page
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    Then I verify maximum of 30 products displayed in RVI panel
    And I verify 6 Recent products displayed at one time in RVI panel
    And I should see the navigation arrow buttons
    When I click on "right" arrow key inside RVI panel
    Then I verify that next set of products are displayed
    When I click on "left" arrow key inside RVI panel
    Then I verify that previous set of products are displayed
    When I click "edit" button in RVI panel
    Then I should see highlighted Remove button on each product and Edit button displayed as Done in RVI panel
    When I click on "Remove" button on any product
    Then I verify product is removed temporally
    When I click outside of RVI panel
    Then I verify removed product displayed again in RVI Panel
    When I click "edit" button in RVI panel
    And I click on "Remove" button on any product
    And I click "done" button in RVI panel
    Then I should see product is removed from RVI panel
    # Notes:
    # Things to verify:
    # RVI panel should display at the bottom of browse page with Edit button on top left
    # Total 30 products should be displayed, with 6 products in a row.
    # Latest 30 products need to be there in the RVI
    # Example: 35th viewed product to 5th viewed products should be displayed, 35th product being first product.
    # (Assume we have navigated to 35 PDPs previously)
    # Verify whether left and right arrow keys are available to view next viewed or previously viewed products on click.
    # Verify the tester can navigate between the recently viewed products by clicking on left and right keys.
    # Clicking on Edit button in RV Items panel should be highlighted Remove button on each product in RV items panel.
    # Clicking on remove button should be removed Product temporally from the list in RV items panel and remaining
    # products should move in forward sequence. And Edit button should be changed as Done
    # Clicking on Done Button should be permanently removed Product

  @use_regression @domain_discovery @priority_medium @mode_domestic
  Scenario: BrowsePage - Verify Edit button is displayed on RVI Panel on Browse Page
    Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  @use_regression @domain_discovery @priority_high @use_dsv @mode_domestic @snbc_comp
  Scenario: Multiple pages - Recently viewed items should display in all pages in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
    When I navigate to a product having "orderable and available" attributes
    And I navigate to a product having "customer_top_rated and orderable" attributes
    And I navigate to main category menu on home page
    And I navigate to "JUNIORS" category page
    And I modify the url to get in to snbc experiment
    And I navigate to bottom of page
    Then I see the display of RVI in "category Splash" page
    When I navigate to the "Jeans" browse page under "WOMEN"
    And I modify the url to get in to snbc experiment
    And I navigate to bottom of page
    Then I see the display of RVI in "category browse" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I select a product from Refactored RVI
    Then I verify that landed on respective product display page
    And I navigate to bottom of page
    Then I see the display of RVI in "product display" page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I navigate to bottom of page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items
    # Notes:
    # Description: Recently viewed items should display in all pages
    # 2. Navigate to PDP of 3-4 products
    # 3. Navigate to category page, category browse page, PDP, Shopping Bag page
    # 3. Recently viewed items should display in category page, category browse page, PDP, Shopping Bag page


  @domain_discovery @priority_medium @use_regression @mode_domestic
  Scenario: BrowsePage - Verify Edit button is displayed on RVI Panel on brand index page in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
    When I select random product from thumbnail grid
    And I verify that landed on respective product display page
    And I select browse 'back' button
    Then I verify that "Brand" facet is listed on left nav
    When I select the first brand in the Brand facet
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    Then I see the display of RVI in "category browse" page
    And I verify that edit option inside Recently Viewed panel is displayed
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  @domain_discovery @priority_medium @use_regression @mode_domestic
  Scenario: SLP page - Verify Edit button is displayed on RVI Panel in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    And I select browse 'back' button
    And I search for "jeans"
    Then I see the display of RVI in "search result" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items

  @domain_discovery @priority_medium @use_regression @mode_domestic
  Scenario: BrowsePage - Verify Edit button is displayed on RVI Panel on bag page  in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
     When I add a "available and orderable" product to my bag
     Then I verify that edit option inside Recently Viewed panel is displayed
     When I click remove button on any Recently viewed items
     Then I verify that the item is removed from Recently viewed items

  @domain_discovery @priority_medium @use_regression @mode_domestic
  Scenario: PDP page - Verify Edit button is displayed on RVI Panel in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Vacuums & Steam Cleaners" under "HOME" in Domestic mode
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    Then I select browse 'back' button
    And I navigate to random category splash page
    And I modify the url to get in to snbc experiment
    And I navigate to sub categories from Left hand nav links
    And I modify the url to get in to snbc experiment
    Then I should be on category splash page
    When I select random product from thumbnail grid
    And I verify that landed on respective product display page
    And I navigate to bottom of page
    Then I see the display of RVI in "product display" page
    Then I verify that edit option inside Recently Viewed panel is displayed
    When I click remove button on any Recently viewed items
    Then I verify that the item is removed from Recently viewed items






