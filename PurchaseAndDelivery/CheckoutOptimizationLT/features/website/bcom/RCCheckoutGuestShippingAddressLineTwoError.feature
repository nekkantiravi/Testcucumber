Feature: Guest checkout regression scenarios for Purchase and Delivery

  @b-88987 @optimization_lab @domain_purchase_and_delivery @guest @checkout
  Scenario: Verify Addressline 2 messaging when clicks Continue without address line 2 in guest checkout.
    Given I visit the web site as a guest user
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I add shipping address with addressline2 missing on guest checkout
    When I select continue button on guest shipping page
    Then validate shipping section for addressLine2 validation messages