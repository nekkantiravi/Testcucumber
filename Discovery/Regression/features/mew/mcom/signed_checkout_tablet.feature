Feature: Verify signed in checkout functionality on Tablet

  @dsv_tablet_sev1
  Scenario: Verify signed in checkout
    Given I visit the mobile web site as a registered user with registry account
    When I add an "available and orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  
  Scenario: Verify registry checkout functionality
    Given I visit the mobile web site as a registered user with registry account
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @dsv_tablet_sev1
  Scenario: Verify registry checkout
    Given I visit the mobile web site as a guest user
    And I navigate wedding registry page
    Then I navigate to manage your registry page and I sign in with registry credentials using mobile website
    And I select category from registry manager page using mobile website
    When I select random product
    Then I add the product to a registry and navigate to bvr using mobile website
    And I checkout until I reach the order review page using mobile website as a "signed in" user
