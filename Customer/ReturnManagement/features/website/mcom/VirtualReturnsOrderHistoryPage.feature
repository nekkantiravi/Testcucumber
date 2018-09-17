Feature: Verify virtual return changes in Order History Page

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns
  @oh_page @b-24538 @b-23754 @b-24544 @b-23760
  Scenario Outline: Verify call to action on OH page for initiated shipments in MOS-N
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "<exchange>" with "initiated" status
    When I navigated to "OH" page as a "signed" user
    Then I should see below call to action on "OH page" header
      |Reminder: Please send your original item(s) back or return in store:|
    And I should see below call to action on "shipment" header
      |Reminder: Please send your original item(s) back or return in store|
    Examples:
      |exchange     |
      | replacement |
      | return      |

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @migrated_to_sdt
  @oh_page @b-24527
  Scenario: Verify return items button on order header in OH page for single line item in MOS-Y
    Given I visit the web site as a guest user
    And I have "mos_y" order for shipment type "replacement" with "processing" status
    When I navigated to "OH" page as a "signed" user
    Then I should not see return items button for order

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @migrated_to_sdt
  @oh_page
  Scenario: Verify navigation to order details page upon selecting link provided on return initiated shipments order shipment CTA in OH page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return" with "initiated" status
    When I navigated to "OH" page as a "signed" user
    When I select link on cta in header
    Then I should be on "order details" page
    When I navigate to order history page
    And I select link on shipment header
    Then I should be on "order details" page

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @migrated_to_sdt
  @oh_page
  Scenario: Verify navigation to order details page upon selecting link provided on replacement initiated shipments order shipment CTA in OH page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "replacement" with "initiated" status
    When I navigated to "OH" page as a "signed" user
    When I select link on cta in header
    Then I should be on "order details" page
    When I navigate to order history page
    And I select link on shipment header
    Then I should be on "order details" page

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @migrated_to_sdt
  @oh_page @b-24527
  Scenario: Verify return credited shipment on OH page for mos y orders
    Given I visit the web site as a guest user
    And I have "mos_y" order for shipment type "return" with "credited" status
    When I navigated to "OH" page as a "signed" user
    Then I should see shipment date with status
    And I should see reason code with status
    And I should see total is updated to reflect the credit

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @migrated_to_sdt
  @oh_page @b-2375 @b-24539
  Scenario: Verify shipments dates for return completed Virtual Shipments on OH page for MOS N
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return" with "completed" status
    When I navigated to "OH" page as a "signed" user
    Then I should see shipment date with status
    And I should see reason code with status
    And I should see total is updated to reflect the credit

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @migrated_to_sdt
  @oh_page @b-2375 @b-24539
  Scenario Outline: Verify shipments dates for Virtual Shipments on OH page for replacement intransit shipments
    Given I visit the web site as a guest user
    And I have "<mos_type>" order for shipment type "replacement" with "intransit" status
    When I navigated to "OH" page as a "signed" user
    Then I should see shipment date with status
    Examples:
      | mos_type |
      | mos_y    |
      | mos_n    |

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns @migrated_to_sdt
  @oh_page @b-2375 @b-24539
  Scenario Outline: Verify shipments dates for Virtual Shipments on OH page for replcement processing shipments
    Given I visit the web site as a guest user
    And I have "<mos_type>" order for shipment type "replacement" with "processing" status
    When I navigated to "OH" page as a "signed" user
    Then I should see shipment date with status
    Examples:
      | mos_type |
      | mos_y    |
      | mos_n    |

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @priority_medium @mode_domestic @use_regression @artifact_shopapp @feature_virtual_returns
  @oh_page @b-2375 @b-24539 @migrated_to_sdt @run_this
  Scenario Outline: Verify shipments dates for Virtual Shipments on OH page for replacement delivered shipments
    Given I visit the web site as a guest user
    And I have "<mos_type>" order for shipment type "replacement" with "delivered" status
    When I navigated to "OH" page as a "signed" user
    Then I should see shipment date with status
    Examples:
      | mos_type |
      | mos_y    |
      | mos_n    |