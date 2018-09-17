Feature: Verify return submit page changes regarding virtual returns


  @release_15K @returns_mgt_7 @domain_customer_management @use_regression @return_management @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @submit_page @migrated_to_sdt
  Scenario: Verify credit section on submit page header for vr item only on page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "line_items_vr" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I navigated to "submit" page with "vr" items in selection
    Then I should see previous credit on submit page header
    And  I should see estimated credit on submit page header
    And I should see previous credit on submit page payment section
    And  I should see estimated credit on submit page payment section
    And I should see instruction message as below
      | Please review your information before clicking "Submit Return". You have already been credited <amount_credited> to <card_details> for this purchase. |


  @release_15K @returns_mgt_7 @domain_customer_management @use_regression @return_management @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @submit_page @migrated_to_sdt
  Scenario: Verify credit section on submit page header for vr with items only on page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "line_items_vr" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I navigated to "submit" page with "with_pr" items in selection
    Then I should see previous credit on submit page header
    And  I should see estimated credit on submit page header
    And I should see previous credit on submit page payment section
    And  I should see estimated credit on submit page payment section
    And I should see instruction message as below
      |  Please review your information before clicking "Submit Return". You have already been credited <amount_credited> to <card_details> for this purchase. |

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @use_regression @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @selection_page @migrated_to_sdt
  Scenario Outline: Verify order total and its elements on selection page for virtual exchange items in MOS-N case.
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "<shipment_status>" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    Then I should see virtual return line item checkbox, also see reason reason for return and expected call to action on selection page
    Examples:
      |   shipment_status    |
      |  line_items_ve_pr    |
      |  line_items_vr_pr    |

  @release_15K @returns_mgt_7 @domain_customer_management @return_management @use_regression @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @selection_page @migrated_to_sdt
  Scenario: Verify order total and its elements on selection page for virtual items in MOS-Y case.
    Given I visit the web site as a guest user
    And I have "mos_y" order for shipment type "return_submit" with "line_items_vr_pr" for selection page
    When I navigated to "OD" page as a "guest" user
    When I select return items button in "OD" page
    Then I should not see virtual return line item checkbox, also see reason reason for return and expected call to action on selection page

  @release_15K @returns_mgt_7 @domain_customer_management @use_regression @return_management @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @submit_page @migrated_to_sdt
  Scenario: Verify credit section on submit page header for ve with pr item only on page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "line_items_ve" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I navigated to "submit" page with "with_pr" items in selection
    Then I should not see previous credit on submit page header
    And  I should not see estimated credit on submit page header
    And I should not see previous credit on submit page payment section
    And  I should see estimated credit on submit page payment section
    And I should see instruction message as below
      |The items and quantities below are ready to be returned. You must select the submit button at the bottom of the page. Once your return has been received and processed, your expected refund amount will be <amount_credited> credited to <card_details> |

  @release_15K @returns_mgt_7 @domain_customer_management @use_regression @return_management @priority_medium @mode_domestic @artifact_shopapp @feature_virtual_returns @submit_page @migrated_to_sdt
  Scenario: Verify credit section on submit page header for ve item only on page
    Given I visit the web site as a guest user
    And I have "mos_n" order for shipment type "return_submit" with "line_items_ve" for selection page
    When I navigated to "OD" page as a "guest" user
    And I select return items button in "OD" page
    And I navigated to "submit" page with "ve" items in selection
    Then I should not see previous credit on submit page header
    And  I should not see estimated credit on submit page header
    And I should not see previous credit on submit page payment section
    And I should see the return initiated date on submit page header
    And I should see instruction message as below
      | Please review your information and click "Submit Return" to continue. |