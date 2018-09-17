
Feature: Verify the return quantity (either selected or actually returned) displayed for each UPC user selected to return in order details page.

  # @credit-5454 # @credit-5571 # @14A
  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Return quantity is displayed correctly for submitted status on the order details page as a Signed-in user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "return_submit" order as a "signed in" user
    And I navigate to order history page
    And I navigate to order details page as a "signed" user
    Then I should see line item qty as "return_qty" for "return : submitted|RETURN STATUS Submitted" shipment

  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Return quantity is displayed correctly for in-transit status on the order details page as a Signed-in user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "transit" order as a "signed in" user
    And I navigate to order history page
    And I set the return "intransit" status for "transit" order in db
    And I navigate to order details page as a "signed" user
    Then I should see line item qty as "return_qty" for "return : in transit|RETURN STATUS In Transit" shipment

