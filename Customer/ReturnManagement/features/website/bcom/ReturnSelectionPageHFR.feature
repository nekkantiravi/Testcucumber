# Author: Irfan Ur Rahaman Mohmmad
# Date Created: September 3, 2013
# Date Signed Off:

Feature: Verify the header and footer in the return selection page same as My account page.

########################################################################################################################

  # @credit-5720 # @credit-5721 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Return Selection page should not retain user information when a user navigates away from the page
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    And I select line item
    Then I navigate to previous page from existing page after selecting any fob from header
    And I verify that the information shall not persist