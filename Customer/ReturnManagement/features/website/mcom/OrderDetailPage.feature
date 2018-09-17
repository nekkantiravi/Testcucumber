Feature: Verify Order Details Page

  @returns_regression @14A @credit-5572 @priority_high @artifact_shopapp @shopapp_3 @use_regression @mode_domestic @returns_mgt_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the price for each UPC is displayed as the single unit price in the Order Detail page.
    Given I visit order history page as a guest user
    When I select "submitted" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    Then I should see unit price for line item
