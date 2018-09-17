Feature: Signedin checkout regression scenarios for Purchase and Delivery

  Background:
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I navigate to my address book page
    And I update the address in my address book with addressline2 missing
    When I add a "available and orderable" product to my bag

  @b-88987 @optimization_lab @domain_purchase_and_delivery @signedin @checkout
  Scenario: Verify Addressline 2 messaging when user saves address without address line 2 in signedIn checkout.
    And I navigate to checkout sign in page from shopping bag page
    And As a signed in user I edit the shipping address
    Then validate shipping section for addressLine2 validation messages

  @b-88987 @optimization_lab @domain_purchase_and_delivery @signedin @checkout
  Scenario: Verify Addressline 2 messaging when user edit shipping address without address line 2 and save in signedIn checkout.
    And I navigate to checkout sign in page from shopping bag page
    And I edit my selected shipping addressLine2 for signed in user
    Then validate shipping section for addressLine2 validation messages

  @b-88987 @optimization_lab @domain_purchase_and_delivery @signedin @checkout
  Scenario: Verify addressLine2 Error Scenario on Place Order
    And I navigate to checkout sign in page from shopping bag page
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see order confirmation section displayed with order details
    And I click on place order button for shipping address error
    Then validate shipping section for addressLine2 validation messages