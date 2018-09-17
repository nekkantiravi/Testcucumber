Feature: Checkout Optimization LT Checkout SignedIn Add Edit Shipping Address scenarios.

  @project_responsive_checkout @domain_purchase_and_delivery @bcom_rc_signedin @coo-ll
  Scenario: verify user can add his first shipping address as signedIn user.
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    When I add a shipping address on shipping page for signed in user
    Then new address should be saved to my profile and order

  @project_responsive_checkout @domain_purchase_and_delivery @bcom_rc_signedin @coo-ll
  Scenario: verify user an edit a shipping address as a signedIn user.
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    When I add a shipping address on shipping page for signed in user
    And I edit my selected shipping-addressLine2 to "Suite 10000" for signed in user
    Then new address should be saved to my profile and order with "addressLine2" is "Suite 10000"

  @project_responsive_checkout @domain_purchase_and_delivery @bcom_rc_signedin @coo-ll
  Scenario: verify user can add additional shipping address as signedIn user.
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    When I add a shipping address on shipping page for signed in user
    And I add an additional address on shipping page for signed in user
    Then new address should be saved to my profile and order with "addressLine2" is "Suite 13000"

