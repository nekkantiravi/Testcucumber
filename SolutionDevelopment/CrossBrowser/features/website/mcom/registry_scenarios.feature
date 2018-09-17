Feature: Verify the functionality of Create Registry, Edit Registry, Find Registry, Add items to Registry

  @scenario1 @domain_selection @xbrowser @xbrowser_two @high @test
  Scenario: Create registry
    Given I visit the web site as a registry user
    Then I should see the registry created successfully

  @scenario2 @domain_selection @xbrowser @xbrowser_two @test
  Scenario: Update Registry
    Given I visit the web site as a registry user
    When I click on edit profile link on registry manager page
    Then I should see update registry page
    When I update "co_registrant_first_name" in update registry page
    Then I should see updated "co_registrant_first_name" in registry manager page

  @scenario3 @domain_selection @xbrowser @xbrowser_two
  Scenario: Find registry
    Given I visit the web site as a registry user
    And I sign out from my current profile
    And I navigate to registry home page
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Find A Couple's Registry" subcategory from flyout menu
    When I search for the existing couple's registry
    Then I should find the couple's registry

  @scenario4 @domain_selection @xbrowser @xbrowser_two
  Scenario: As a registry signed in user, I should able to apply the registry promotion code
    Given I visit the web site as a guest user
    When I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option
    Then I should be navigated to the registry manager page
    When I save promocode displayed on registry manager page
    And I add "registrable and orderable" product to my bag from BVR page
    And I apply registry promo code on the shopping bag page
    Then I should see updated order total on the shopping bag page