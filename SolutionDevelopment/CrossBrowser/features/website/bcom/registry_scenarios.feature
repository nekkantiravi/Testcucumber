Feature: Verify the functionality of Create Registry, Edit Registry, Find Registry, Add items to Registry

  
  @scenario1 @domain_selection @xbrowser @xbrowser_two @high
  Scenario: Create registry
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Create a Registry" subcategory from flyout menu
    And I start to create a new registry from registry sign in page
    And I create a new registry
    Then I should see the registry created successfully

  @scenario1 @domain_selection @xbrowser @xbrowser_two @high
  Scenario: Create registry for user with bcom profile
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Create a Registry" subcategory from flyout menu
    And I create a new registry for user with existing bcom profile
    Then I should see the registry created successfully


  @scenario2 @domain_selection @xbrowser @xbrowser_two
  Scenario: Update Registry
    Given I visit the web site as a registry user
    When I click on edit profile link on registry BVR page
    Then I should see update registry page
    When I update "co_registrant_first_name" in update registry page
    Then I should see updated "co_registrant_first_name" in registry manager page

  @scenario3 @domain_selection @xbrowser @xbrowser_two
  Scenario: Find registry
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    When I search for the existing couple's registry
    Then I should find the couple's registry

  @scenario4 @domain_selection @xbrowser @xbrowser_two
  Scenario: As a registry signed in user, I should able to apply the registry promotion code
    Given I visit the web site as a guest user
    When I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option
    Then I should land on BVR
    When I save promocode displayed on registry BVR page
    And I add "registrable and orderable" product to my bag from BVR page
    And I apply registry promo code on the shopping bag page
    Then I should see updated order total on the shopping bag page