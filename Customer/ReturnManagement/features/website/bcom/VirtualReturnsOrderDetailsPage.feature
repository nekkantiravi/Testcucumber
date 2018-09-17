Feature: Order Details Scenarios to verify virtual shipments

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario Outline: verify the replacement initiated shipments in Order details page
    Given I visit the web site as a guest user
    And I have "<mos_type>" order for shipment type "replacement" with "initiated" status
    When I navigated to "OD" page as a "guest" user
    Then I should see shipment on page with status on header with expected date for shipment
    And I should see shipment address as expected
    And I should see label for amount at for shipment as expected
    Examples:
      | mos_type |
      | mos_y    |
      | mos_n    |

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: verify the replacement processing virtual shipments in Order details page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "replacement" with "processing" status
    When I navigated to "OD" page as a "guest" user
    Then I should see shipment on page with status on header with expected date for shipment
    And I should see shipment address as expected

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: verify the replacement initiated shipments call to action on OD page for MOS-N
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "replacement" with "initiated" status
    When I navigated to "OD" page as a "guest" user
    Then I should see call to action as below on header "Reminder: Please send your original item(s) back or return in store see below"
    And I should see reason code for virtual line item
    And I should see shipment level call to action as below

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: verify the replacement initiated shipments call to action on OD page for MOS-Y
    Given I visit the web site as a guest user
    And I have "mos_y" order for shipment type "replacement" with "initiated" status
    When I navigated to "OD" page as a "guest" user
    Then I should not see call to action as below on header "Reminder: Please send your original item(s) back or return in store see below"
    And I should see reason code for virtual line item
    And I should see shipment level call to action as below

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: Verify return items button is not displaying for single line item in mos y case
    Given I visit the web site as a guest user
    And I have "mos_y" order for shipment type "return_submit" with "single_line_item" status
    When I navigated to "OD" page as a "guest" user
    Then I should not see return items button for order

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: Verify shipment payment section with -ve credit to customer
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "negitive_credit" status
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I click on "yes" button to schedule return pickup
    Then I should see default address as pickup address
    When I continue to submit page using "default" address
    Then I navigate to return confirmation page
    Then I navigated to "OD" page as a "guest" user
    Then I should see credit section as expected

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: verify the return initiated address is changing when user submit his return
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return" with "initiated" status
    When I navigated to "OD" page as a "guest" user
    Then I should see shipment address as expected
    And I select return items button in "OD" page
    And I navigated to "confirmation" page with "with_pr" items in selection
    Then I navigated to "OD" page as a "guest" user
    Then I should see "return" "initiated" address as dc address

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: verify the return initiated shipment in Order Details page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return" with "initiated" status
    When I navigated to "OD" page as a "guest" user
    Then I should see call to action as below on header "Reminder: Please send your original item(s) back or return in store see below"
    And I should see reason code for virtual line item
    And I should see shipment level call to action as below
    And I should see label for amount at for shipment as expected
    And I should see shipment on page with status on header with expected date for shipment
    And I should see shipment address as expected

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page @migrated_to_sdt
  Scenario: verify the replacement initiated address is changing when user submit his return
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "replacement" with "initiated" status
    When I navigated to "OD" page as a "guest" user
    Then I should see shipment address as expected
    And I select return items button in "OD" page
    And I navigated to "confirmation" page with "with_pr" items in selection
    And I navigated to "OD" page as a "guest" user
    Then I should see "return" "initiated" address as dc address
    And I should see "replacement" "initiated" address as dc address

  @migrated_to_sdt
  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page
  Scenario: verify the return completed shipment in OD page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return" with "completed" status
    When I navigated to "OD" page as a "guest" user
  #And I should not see call at action at line item level
    And I should see label for amount at for shipment as expected
    And I should see shipment on page with status on header with expected date for shipment
    And I should see shipment address as expected

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page @migrated_to_sdt
  Scenario: verify the return credited virtual shipments for mos y in Order details page
    Given I visit the web site as a guest user
    And I have "mos_y" order for shipment type "return" with "credited" status
    When I navigated to "OD" page as a "guest" user
    Then I should see shipment on page with status on header with expected date for shipment
    And I should see shipment address as expected
    And I should not see call to action as below on header "Reminder: Please send your original item(s) back or return in store see below"
    And I should see reason code for virtual line item
    And I should see shipment level call to action as below
    And I should see label for amount at for shipment as expected
    And I should see updated order total for shipment

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page @migrated_to_sdt
  Scenario Outline: verify the replacement delivered virtual shipments in Order details page
    Given I visit the web site as a guest user
    And I have "<mos_type>" order for shipment type "replacement" with "delivered" status
    When I navigated to "OD" page as a "guest" user
    Then I should see shipment on page with status on header with expected date for shipment
    And I should see shipment address as expected
    And I should not see call at action at line item level
    Examples:
      | mos_type |
      | mos_y    |
      | mos_n    |

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @od_page @migrated_to_sdt
  Scenario Outline: verify the replacement intransit virtual shipments in Order details page
    Given I visit the web site as a guest user
    And I have "<mos_type>" order for shipment type "replacement" with "intransit" status
    When I navigated to "OD" page as a "guest" user
    Then I should see shipment on page with status on header with expected date for shipment
    And I should see shipment address as expected
    And I should not see call at action at line item level
    And I should see track shipment button on shipment
    Examples:
      | mos_type |
      | mos_y    |
      | mos_n    |