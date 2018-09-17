Feature: Order Details Scenarios to verify virtual shipments

  @returns_mgt_7 @release_15K @mode_domestic @feature_virtual_returns @artifact_shopapp @priority_medium @use_regression @domain_customer_management @od_page @return_management @run_this
  Scenario: verify the replacement initiated shipments call to action on OD page for MOS-N
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "replacement" with "initiated" status
    When I navigated to "OD" page as a "guest" user
    Then I should see call to action as below on header "Reminder: Please send your original item(s) back or return in store see below"
    And I should see reason code for virtual line item
    And I should see shipment level call to action as below
