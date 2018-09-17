#Author: Shiva Kodamunja
#Date Created: November 21, 2013
#Date Updated: June 2, 2016

Feature: Verify Order Details page's item details and date and status messages

  @14A  @credit-5500 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_3 @domain_customer @migrated_to_sdt
  Scenario: Order Details page has correct Unit Price, Received On Date, and Total Price for Received returns
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "received" order as a "guest" user
    And I navigate to order history page
    And I set the return "received" status for "received" order in db
    And I navigate to order details page as a "guest" user
    Then I verify total price for "RETURN STATUS Received" shipment
    Then I should see line item text as "Received on <line_item_date>" for "RETURN STATUS Received" shipment

  @14A @credit-5500 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_3 @domain_customer  @migrated_to_sdt
  Scenario: Order Details page has correct Unit Price, Submitted Date, and Total Price for Submitted returns
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "submitted" order as a "guest" user
    Then I navigate to order history page
    And I lookup with order number and emailaddress in OH page
    And I verify total price for "RETURN STATUS Submitted" shipment
    Then I should see line item text as "Submitted on <line_item_date>" for "RETURN STATUS Submitted" shipment

  @14A @credit-5500 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_3 @domain_customer @migrated_to_sdt
  Scenario: Order Details page has correct Unit Price and Total Price for Incomplete returns
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "submitted" order as a "guest" user
    And I navigate to order history page
    And I set the return "incomplete" status for "submitted" order in db
    And I lookup with order number and emailaddress in OH page
    Then I verify total price for "RETURN STATUS Incomplete" shipment
    Then I should see line item text as "Received on <line_item_date>" for "RETURN STATUS Incomplete" shipment


  @14A @credit-5500 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_3 @domain_customer @migrated_to_sdt
  Scenario: Order Details page has correct Unit Price and Total Price for In-Transit returns
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "submitted" order as a "signed in" user
    And I set the return "intransit" status for "submitted" order in db
    And I navigate to order details page as a "signed" user
    Then I verify total price for "RETURN STATUS In Transit" shipment

  @14A @credit-5500 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Order Details page has correct Unit Price and Total Price for Completed returns
    Given I visit order history page as a guest user
    When I select "submitted" order as a "signed" user
    And I navigate to order details page as a "signed" user
    Then I verify total price for "RETURN STATUS Completed" shipment
