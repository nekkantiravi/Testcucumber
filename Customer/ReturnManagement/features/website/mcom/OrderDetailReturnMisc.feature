# Author: Shiva Kodamunja
# Date Created: November 1st, 2013
# Date Signed Off:

Feature: Order Details - Verify Return information after submitting the return

 #Verify a column for item description under all my returns which includes every UPC being returned and it should be identical to the orders (order mods).

  #Mingle Story:	http://mingle/projects/credit/cards/5568
  #http://mingle/projects/credit/cards/5415

  # @credit-5568 # @credit-5415 # @14A
  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @feature_orderdetails_return @migrated_to_sdt
  Scenario: Item information is displayed correctly on the Order Details page as a Signed-in user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "submitted" order as a "signed in" user
    And I set the return "intransit" status for "submitted" order in db
    Then I navigate to order details page as a "signed" user
    And I should see item_description on the page for returns items
    And I should not see write_review button under return section

    # @credit-5564 # @credit-5406 # @14A
  @priority_high @artifact_shopapp @shopapp_4 @domain_customer @use_regression @mode_domestic @returns_mgt_2 @feature_orderdetails_return @use_domain_qual @migrated_to_sdt
  Scenario: Print Shipping label only appears if the Order Status is submitted as a guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "intransit" order as a "guest" user
    And I navigate to order details page as a "guest" user
    Then I see return order status as "submitted" on page
    And I should see Print Shipping label on page for submitted status


    # @credit-5564 # @credit-5406 # @14A
  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_4 @domain_customer @cm_dsv_high_10  @migrated_to_sdt
  Scenario: Print Shipping label does not appear if the Order status is not submitted as a Signed-in user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "submitted" order as a "signed in" user
    And I set the return "intransit" status for "submitted" order in db
    And I navigate to order details page as a "signed" user
    Then I should not see print mailing button for intransit status

    # @credit-5564  # @credit-5406   # @14A
  @returns_regression @use_regression @mode_domestic  @priority_medium @artifact_shopapp @returns_mgt_5 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Clicking the print mailing link allows a Guest user to print the return label
    Given I visit the web site as a guest user
    When I navigate to return selection page using "intransit" order as a "guest" user
    And I select items and continue to submit page
    And I navigate to return confirmation page
    And I navigate to order details page as a "guest" user
    And I navigate to new window after selecting print shipping label in order details page
    Then I should see button to reprint the confirmation page

# @credit-5414  # @credit-5567 # @14A
  @returns_regression @priority_medium  @artifact_shopapp @shopapp_4 @use_regression @mode_domestic @returns_mgt_3 @domain_customer @feature_orderdetails_return @migrated_to_sdt
  Scenario: Return Address is displayed on the Order Details page as a Guest user
    Given I visit order history page as a guest user
    When I select "submitted" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I verify return button as per shipment details in the order "details" page
    And I initiate return for 1 times and navigate to order details page as a "guest" use
    Then I verify return address on Order details
    Then I should not see print mailing button for intransit status

