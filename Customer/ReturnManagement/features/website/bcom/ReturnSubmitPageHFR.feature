Feature: To verify the header and footer on return submit page

 # @credit-5832  # @credit-5854# @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Error message is not displayed when users navigates away from Return Submit page
    Given I visit order history page as a guest user
    When I select "submitted" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    Then I should navigate to return selection page
    When I select items and continue to submit page
    And I select any fob from the header
    Then I should be navigated to corresponding page