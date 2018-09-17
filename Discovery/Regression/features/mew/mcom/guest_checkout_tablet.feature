Feature: Verify guest checkout on Tablet

  @dsv_tablet_sev1
  Scenario: Verify guest checkout functionality
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user
    