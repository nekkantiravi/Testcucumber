Feature: Checkout Optimization LT Checkout SignedIn Shipping Method display scenarios

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: verify user is able to see the shipping Methods section in active state
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "registered" user
    Then I should see the shipping Methods section in active state

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: verify user is not able to see the save and cancel button
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "registered" user
    Then I should not see the Change button in the shipping Method section
    Then I should not see the Save button in the shipping Method section
    Then I should not see the Cancel button in the shipping Method section



