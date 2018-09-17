Feature: Verify return confirmation page changes regarding virtual returns

  @release_15K @returns_mgt_7 @domain_customer_management @use_regression @return_management @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @submit_page @migrated_to_sdt
  Scenario: Verify credit section on confirmation page header for vr item only on page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "line_items_vr" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I navigated to "confirmation" page with "vr" items in selection
    Then I should see previous credit on confirmation page header
    And  I should see estimated credit on confirmation page header
    And I should see previous credit on confirmation page payment section
    And  I should see estimated credit on confirmation page payment section

  @release_15K @returns_mgt_7 @domain_customer_management @use_regression @use_regression @return_management @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @submit_page @migrated_to_sdt
  Scenario: Verify credit section on confirmation page header for vr with items only on page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "line_items_vr" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I navigated to "confirmation" page with "with_pr" items in selection
    Then I should see previous credit on confirmation page header
    And  I should see estimated credit on confirmation page header
    And I should see previous credit on confirmation page payment section
    And  I should see estimated credit on confirmation page payment section

  @release_15K @returns_mgt_7 @domain_customer_management @use_regression @return_management @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @submit_page @migrated_to_sdt
  Scenario: Verify credit section on confirmation page header for ve with pr item only on page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "line_items_ve" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I navigated to "confirmation" page with "with_pr" items in selection
    Then I should not see previous credit on confirmation page header
    And  I should not see estimated credit on confirmation page header
    And I should not see previous credit on confirmation page payment section
    And  I should see estimated credit on confirmation page payment section