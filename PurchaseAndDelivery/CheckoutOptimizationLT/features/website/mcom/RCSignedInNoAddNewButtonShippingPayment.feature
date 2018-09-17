Feature: Checkout Optimization LT Checkout SignedIn Shipping Method display scenarios

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: Verify when user has no shipping address ,user is able to see the shipping Address form in active state.
    Given I create a new profile
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping page as a "signed in" user
    Then I should see the shipping address section in active state
    Then I should not see the Cancel button in the shipping Address section


  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: Verify when user has no credit cards in wallet, user should be able to see the credit card section in active state
    Given I create a new profile
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping page as a "signed in" user
    And I add a valid shipping address on shipping page for signed in user
    Then I should see the payment section in active state
    Then I should not see the Cancel button in the payment section





