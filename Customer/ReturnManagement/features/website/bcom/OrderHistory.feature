# Author: Preeti Gupta
# Date Created: 30 January,2014
# Date Signed Off:

Feature: Verify Order History

#####################################################################################################################
  #As a customer, I should be able to look up my order either by using the lowercase Order ID or Uppercase OrderID with my email/phone number combination

  # This part is taken from Order mods regression file. I have added the valid scenarios to that only.
  #Mingle story :mingle/projects/customer_management/cards/6453

    #####################################################################################################################

  # Mingle story :http://mingle/projects/customer_management/cards/6395
  # Mingle Story :http://mingle/projects/customer_management/cards/6394

  # As a customer, I should be able to look up my order either by using the OrderID or FEDFil Reservation number with my email/phone number combination

  @release_16A @domain_customer-6395 @domain_customer-6394 @use_project @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @feature_orderhistory @migrated_to_sdt
  Scenario Outline: Error counter resets after successful look up with email on the Order History page as a guest user
    Given I visit order history page as a guest user
    When I lookup with "submitted" order with gift return
    Then I navigate to order history page from order details page
    When I lookup with valid "<order_number>" and incorrect "<email>" in OH page
    Then I verify "<error_message>" upto "4" attempts for "<order_number>" and incorrect "<email>"
  Examples:
     |error_message | order_number | email        |
     | We couldn't find an order with the information you provided. Please double check it and re-enter. | 15637201738 | sss@test.com |

  @priority_high @domain_customer @dsv_sev2 @dsv_sev2_dryrun @use_regression @migrated_to_sdt
  Scenario: Verify successful look up with email on the Order History page as a guest user
    Given I visit order history page as a guest user
    When I lookup I lookup production order with "picked_up" status using email
    Then i verify order details for order with "picked_up" status

  @priority_high @domain_customer @dsv_sev2 @dsv_sev2_dryrun @use_regression @migrated_to_sdt
  Scenario: Verify order history as a signed-in prod user
    Given I visit the production web site as a "canceled" registered user
    And I navigate to order history page
    Then I navigate to order details page as a "canceled" user
    And I verify order details in OD page


  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Order Look up text is correct on the Order History page as a signed-in user through the My Account page
    Given I visit order history page as a signed user
    Then I should see the order history help modal
    And I verify the following text on the Order history page modal
      |Your Order Number is located on the top of your Order Confirmation email and the invoice you received with your shipment.|

          # @credit-5570 # @credit-5453 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Gift box value should be No for return items as a guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "return_hierarchy" order as a "guest" user
    And I set all the return status in db
    Then I navigate to order history page
    And I lookup with order number and emailaddress in OH page
    And I verify that return status items should not contain gift box

  @returns_regression @use_project @mode_domestic @domain_customer-34922  @priority_medium  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @release_16A @migrated_to_sdt
  Scenario: Error counter resets on successful look up with email on the Order History page as a guest user
    Given I visit order history page as a guest user
    When I lookup with valid order number and incorrect emailaddress in OH page
      | order_type                                           | email        |
      | shipping_and_billing_address_different_for_creditcard| sss@test.com |
    Then I verify error messages upto 4 attempts
      | We couldn't find an order with the information you provided. Please double check it and re-enter. |
    When I lookup with "pickup_eligible_location_chicago" order with gift return
    Then I navigate to order history page from order details page

  @returns_regression @credit-7360 @credit-7361 @artifact_shopapp @14K @use_regression @mode_domestic @returns_mgt_2 @domain_customer @feature_orderhistory @migrated_to_sdt
  Scenario: Order History page has correct text on the Drop Down as a Signed in user
    Given I visit order history page as a signed user
    When I associate 7 orders to the profile
    And I refresh the page
    And I select appropriate date range
    And I navigate to previous page from existing page
    And I navigate to order history page
    Then I verify drop down text on the order history page

