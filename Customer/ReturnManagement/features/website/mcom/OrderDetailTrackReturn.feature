#Author: Prashanth Kumar
#Date Created: October 9, 2013
#Date Signed Off:

Feature: Verify that user is able to track the delivery status of return directly in Order Detail page.

  #Mingle Story: http://mingle/projects/credit/cards/5566
  #http://mingle/projects/credit/cards/5407

  # @credit-5566 # @credit-5407 # @14A
  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_5 @shopapp_4 @domain_customer @feature_orderdetails_return @use_domain_qual @migrated_to_sdt
  Scenario: Track My Return button is displayed for In-Transit orders as a Guest user
    Given I visit the web site as a guest user
    And I navigate to confirmation page using "transit" order as a "guest" user
    When I set the return "intransit" status for "transit" order in db
    And I navigate to order details page as a "guest" user
    Then I should "see" Track My Return button with details for "in transit" order

  # @credit-5566 # @credit-5407 # @14A
  @returns_regression @use_regression @mode_domestic  @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_4 @domain_customer @feature_orderdetails_return @use_domain_qual @migrated_to_sdt
  Scenario: Track My Return button displays correctly for recieved orders as a Signed-in user
    Given I visit the web site as a guest user
    And I navigate to confirmation page using "transit" order as a "signed in" user
    When I set the return "received" status for "transit" order in db
    And I navigate to order details page as a "signed" user
    Then I should "see" Track My Return button with details for "received" order
    When I set Track My Return after 119 days of return creation for "transit" order
    And I navigate to order details page as a "signed" user
    Then I should "see" Track My Return button with details for "received" order
    When I set Track My Return after 120 days of return creation for "transit" order
    And I navigate to order details page as a "signed" user
    Then I should "not see" Track My Return button with details for "received" order

  # @credit-5566 # @credit-5407 # @14A
  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_5 @shopapp_4 @s4a_stable @domain_customer @feature_orderdetails_return @migrated_to_sdt
  Scenario: Track My Return button is not displayed for Completed orders as a Guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "return_submit" order as a "guest" user
    And I set the return "complete" status for "return_submit" order in db
    And I navigate to order details page as a "guest" user
    Then I should "not see" Track My Return button with details for "completed" order

  # @credit-5566 # @credit-5407  @14A
  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_4 @domain_customer @feature_orderdetails_return @migrated_to_sdt
  Scenario: Track My Return button displays correctly for incomplete orders as a Signed-in user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "transit" order as a "signed in" user
    And I set Track My Return after 119 days of return creation for "transit" order
    And I navigate to order details page as a "signed" user
    Then I should "see" Track My Return button with details for "incomplete" order
    When I set Track My Return after 120 days of return creation for "transit" order
    And I navigate to order details page as a "signed" user
    Then I should "not see" Track My Return button with details for "incomplete" order

  @returns_regression @credit-6549 @credit-6550 @artifact_shopapp @14F @priority_medium @use_regression @mode_domestic @returns_mgt_5 @domain_customer @migrated_to_sdt
  Scenario: Track My Return button displays correctly for in-transit orders for Guest users
    Given I visit the web site as a guest user
    And I navigate to confirmation page using "intransit" order as a "guest" user
    When I set the return "intransit" status for "intransit" order in db
    And I set valid tracking number in db
    And I navigate to order details page as a "guest" user
    Then I should "see" Track My Return button with details for "intransit" order
    And I should not see status field in tracking details for below status
      |return : in transit|

  @returns_regression @credit-6549 @credit-6550 @artifact_shopapp @14F @priority_medium @use_regression @mode_domestic @returns_mgt_5 @domain_customer @migrated_to_sdt
  Scenario: Track My Return button displays correctly for completed orders for Signed In users
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "return_submit" order as a "signed in" user
    And I set the return "received" status for "return_submit" order in db
    And I set valid tracking number in db
    And I set the return "complete" status for "return_submit" order in db
    And I navigate to order details page as a "signed" user
    Then I should "see" Track My Return button with details for "return_submit" order
    And I should not see status field in tracking details for below status
      |return : completed|

  @returns_regression @14H @artifact_shopapp @credit-6655 @credit-6654 @priority_medium @use_regression @mode_domestic @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario: Track my Return displays delivery date for guest users
    Given I visit the web site as a guest user
    And I navigate to confirmation page using "intransit" order as a "guest" user
    When I set the return "intransit" status for "intransit" order in db
    And I set valid tracking number in db
    And I navigate to order details page as a "guest" user
    Then I should "see" Track My Return button with details for "intransit" order
    And I should see delivery date
      | return : in transit |

  @returns_regression @14H @artifact_shopapp @credit-6655 @credit-6654 @priority_medium @use_regression @mode_domestic @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario: Track my Return displays delivery date for gift orders for guest users
    Given I visit order history page as a guest user
    When I lookup with "transit" order with gift return
    Then I select return items button in "OD" page
    And I select items and continue to submit page
    And I select return submit from submit page
    When I set the return "received" status for "transit" order in db
    And I set valid tracking number in db
    And I navigate to order details page as a "guest" user
    Then I should "see" Track My Return button with details for "transit" order
    And I should see delivery date
      | return : received |