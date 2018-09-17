Feature: Checkout Optimization LT Checkout useMyShippingAddress scenarios

  @project_responsive_checkout @domain_purchase_and_delivery @bcom_rc_guest @coo-ll
    Scenario:  Verify that I can place an order on the guest checkout page when useMyShipping is true
    Given I visit the web site as a guest user
    When I add a "available" product to my bag
    And  I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    And I add a Visa card during checkout as a guest
    Then I verify that useMyShipping is set to true
    When I select continue button on guest payment page
    Then I verify that the payment section is in summary state