Feature: Find Registry

  @dsv_desktop_sev2
  Scenario: Verify the canonical tag exist on Registry mode (for DSV)
    Given I visit the web site as a guest user
    When I visit the registry home page
    And I visit the KITCHEN category page from the registry
    Then I verify the "canonical" tag in HTML view source code

  @dsv_desktop_sev2
  Scenario: Verify the content and Linking rewards program of My Registry Page (for DSV)
    Given I visit the web site as a guest user in "domestic" mode
    When I click on "sign in" link in the header
    And I sign in as a registry user in production
    When I navigate to registry manager page
    Then I should see registry registrant names on registry manager page
    And I verify content on registry manager page
    When I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Registry Star Rewards" subcategory from flyout menu
    Then I verify user is navigated to registry "star rewards" page

  @dsv_desktop_sev2
  Scenario: Verify the content and Linking registry manager of My Registry Page (for DSV)
    Given I visit the web site as a guest user in "domestic" mode
    When I click on "sign in" link in the header
    And I sign in as a registry user in production
    When I navigate to registry manager page
    Then I should see registry registrant names on registry manager page
    And I should see registry event location on registry manager page
    And I should see our website icon on registry manager page
    Then I should be navigated to registry "Manager" page

  @dsv_desktop_sev2
  Scenario: Verify product price and currency symbol on pdp page
    Given I visit the web site as a guest user
    When I navigate to the "Activewear" browse page under "KIDS"
    Then I verify the price details in browse page continues to PDP

  @dsv_desktop_sev2
  Scenario: Verify Currency Symbol - Registry
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I navigate to find couple registry page
    And I click on view registry
    Then I should see GVR items in the page and add any product to bag
    Then I verify basic attributes of the ATB confirmation Overlay

  @dsv_desktop_sev2
  Scenario: As a user I should be able to search couples registry for dsv
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I navigate to find couple registry page
    And I click on view registry
    Then I should see GVR items in the page

  @dsv_desktop_sev2
  Scenario: Verify the basic attributes of the view registry page
    Given I visit the web site as a guest user in "domestic" mode
    When I click on "sign in" link in the header
    And I sign in as a registry user in production
    When I navigate to registry manager page
    When I click on the "view registry" under categories on "Registry Home Page" page
    Then I verify the basic attributes of the BVR page

  @dsv_desktop_sev2 @wip
  Scenario: Verify product description functionality in vertical recommendation panel on PDP page in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate PDP page that has recommendations in "vertical" panel in "registry" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I select a random product from vertical recommendation panel on member PDP registry mode
    Then I verify navigation to the corresponding PDP registry mode

  @dsv_desktop_sev2
  Scenario: Login to existing registry and Add to bag from BVR
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I login for the existing registry
    And I should see GVR items present in the page
    Then I add one product from BVR
    And I refresh current page
    Then I navigate to shopping bag page
    And I match product name
    Then I remove all items from the shopping bag