Feature: Verify the functionality of Create Registry, Edit Registry, Find Registry, Add items to Registry

  @scenario1 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Create registry
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | The Registry              |
      | Create or Create Registry |
    And I start to create a new registry from mobile registry capture email page
    And I create a new registry using mobile website
    Then I should be navigated to the mobile registry BVR page

  @scenario2 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Update Registry
    Given I visit the mobile web site as a registry user
    When I click on edit profile link on mobile registry BVR page
    And I update co_registrant_first_name in mobile update registry page
    Then I should see updated co_registrant_first_name in mobile registry manager page

  @scenario3 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Find registry
    Given I visit the mobile web site as a registry user
    And I sign out from my current mobile site profile
    When I navigate the global navigation menu as follows:
      | The Registry          |
      | Find or Find Registry |
    And I search for the existing couple's registry using mobile site
    Then I should find the couple's registry using mobile site

  @scenario4 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Add a product to registry
    Given I visit the mobile web site as a registry user
    When I navigate to "registrable" product PDP page
    Then I add product to my registry from standard PDP Page using mobile site

  @scenario5 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: As a registry signed in user, I should able to apply the registry promotion code
    Given I visit the mobile web site as a guest user
    When I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option on mobile site
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    When I save promocode displayed on mobile registry BVR page
    And I add "available and registrable" product to my bag from BVR page using mobile website and "select" checkout
    And I apply registry promo code on mobile shopping bag page
    Then I should see registry promocode is applied on mobile shopping bag page