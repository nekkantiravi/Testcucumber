Feature: Verify the functionality of Create Registry, Edit Registry, Find Registry, Add items to Registry through Tab

  @scenario1 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Create registry
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
    And I select "create your registry" from mobile registry home page
    And I start to create a new registry from mobile registry capture email page
    And I create a new registry using mobile website
    Then I should be navigated to the mobile registry welcome page

  @scenario2 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Update Registry
    Given I visit the mobile web site as a registry user
    And I navigate to the mobile registry manager page
    When I click on edit profile link on mobile registry manager page
    And I update co_registrant_first_name in mobile edit registry page
    And I navigate to the mobile registry manager page
    Then I should see updated co_registrant_first_name in mobile registry manager page

  @scenario3 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Find registry
    Given I visit the mobile web site as a registry user
    And I sign out from my current mobile site profile
    And I navigate to wedding registry page
    And I select find registry on registry home page
    When I search for the existing couple's registry using mobile site
    Then I should find the couple's registry using mobile site

  @scenario4 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: Add a product to registry
    Given I visit the mobile web site as a registry user
    And I navigate the global navigation menu as follows:
      | Shop           |
      | For The Home   |
      | Kitchen        |
      | Bakeware       |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    And I add product to my registry from standard PDP Page using mobile site

  @scenario5 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario: As a registry signed in user, I should able to apply the registry promotion code
    Given I visit the mobile web site as a guest user
    When I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option on mobile site
    Then I navigate to the mobile registry manager page
    When I save promocode displayed on mobile registry manager page
    And I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    And I apply registry promo code on mobile shopping bag page
    Then I should see registry promocode is applied on mobile shopping bag page