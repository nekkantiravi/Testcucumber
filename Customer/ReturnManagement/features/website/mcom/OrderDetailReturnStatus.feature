#Author: Prashanth Kumar
#Date Created: October 9, 2013
#Date Signed Off:

Feature: Verify high level status on every return user have submitted

    # @credit-5562 # @credit-5405 # @14A
  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_5 @shopapp_4 @domain_customer @feature_orderdetails_return @saucelab_C @use_domain_qual @migrated_to_sdt
  Scenario: Return status can be incomplete as a Guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "transit" order as a "guest" user
    And I set the return "incomplete" status for "transit" order in db
    And I navigate to order details page as a "guest" user
    Then I should see return status as "incomplete" with updated date

    # @credit-5562 # @credit-5405# @14A
  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_4 @domain_customer @feature_orderdetails_return @saucelab_C @use_domain_qual @migrated_to_sdt
  Scenario: Return status can be complete as a Signed-in user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "return_submit" order as a "signed in" user
    And I set the return "complete" status for "return_submit" order in db
    And I navigate to order details page as a "signed" user
    Then I should see return status as "complete" with updated date

  # @credit-5562 # @credit-5405 # @14A
  @returns_regression @use_regression @mode_domestic  @priority_high @artifact_shopapp @returns_mgt_5 @shopapp_4 @domain_customer @feature_orderdetails_return @saucelab_C @cm_dsv_high_10 @migrated_to_sdt
  Scenario: Return status can change from intransit to incomplete after 45 days as a Guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "transit" order as a "guest" user
    And I set the return "intransit" status for "transit" order in db
    And I set the return "incomplete" status for "transit" order in db
    And I navigate to order details page as a "guest" user
    Then I should see return status as "incomplete" with updated date

  # @credit-5562  # @credit-5405 # @14A
  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_1 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Return status can be submitted as a Signed-in user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "submitted" order as a "signed in" user
    And I navigate to order details page as a "signed" user
    Then I should see return status as "submitted" with updated date

  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_5 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Return status can be intransit as a Guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "transit" order as a "guest" user
    And I set the return "intransit" status for "transit" order in db
    And I navigate to order details page as a "guest" user
    Then I should see return status as "in transit" with updated date

  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_1 @shopapp_4 @domain_customer @saucelab_C @saucelab_C_F1 @migrated_to_sdt
  Scenario: Return status can be recieved as a Signed-in user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "transit" order as a "signed in" user
    And I set the return "received" status for "transit" order in db
    And I navigate to order details page as a "signed" user
    Then I should see return status as "delivered" with updated date