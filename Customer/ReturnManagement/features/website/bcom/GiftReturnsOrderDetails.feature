# Author: Anagha Patil
# Date  : May 26, 2014

Feature: Gift Returns - Order Details Page

#########  As a gift recipient, I should not be able to see the purchaser's information on the order details page. #####

  #Mingle Story :  http://mingle/projects/customer_management/cards/6542
  #http://mingle/projects/customer_management/cards/6541

  @returns_regression @14H @use_regression @mode_domestic @returns_mgt_4 @credit-6542 @credit-6541 @priority_medium @artifact_shopapp @domain_customer @migrated_to_sdt
  Scenario Outline: To verify that Payment Information and billing address section are not visible in Order Details page as a signed user.
    Given I visit order history page as a signed user
    When I lookup with "<order_type>" order with gift return
    And I verify details related to payment and billing address should not be displayed

    Examples:
      | order_type                                                                           |
      | mixed_order_with_credit_card_gift_card_and_different_shipping_and_billing_address    |
      | EGC                                                                                  |
      | shipping_and_billing_address_different_for_creditcard                                |

  @returns_regression @14H @use_regression @mode_domestic @returns_mgt_4 @artifact_shopapp @credit-6543 @credit-6544 @priority_medium @domain_customer @migrated_to_sdt
  Scenario: Line Item Price and Sub-Total are not visible for Incomplete Orders on the Gift Returns Order Details page as a Guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "intransit" order with gift return
    And I set the return "incomplete" status for "intransit" order in db
    And I navigate to order details page with gift return
    Then I verify price and total for "return : incomplete|RETURN STATUS Incomplete" shipment

  @returns_regression @14H @artifact_shopapp @credit-6543 @credit-6544 @priority_medium @use_regression @mode_domestic @domain_customer @feature_giftreturn_orderdetails @migrated_to_sdt
  Scenario: Line Item Price and Sub-Total are not visible for Submitted Orders on the Gift Returns Order Details page as a Signed-in user
    Given I visit order history page as a signed user
    When I navigate to confirmation page using "submitted" order with gift return
    And I navigate to order details page with gift return
    Then I verify price and total for "return : submitted|RETURN STATUS Submitted" shipment