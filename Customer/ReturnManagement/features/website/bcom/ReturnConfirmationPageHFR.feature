# Author: Suma Dathrik
# Date Created: September 11, 2013
# Date Signed Off:

Feature: Verify the header and footer in the "Return Confirmation" page same as in My Account page where all the links can be used to navigate to the corresponding pages.


#Mingle Story: http://mingle/projects/credit/cards/5770
#http://mingle/projects/credit/cards/5784

 # @credit-5770 # @credit-5784 # @14A
  @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @returns_regression @migrated_to_sdt
  Scenario: Return Confirmation page has same header as My Account page as a signed user
    Given I visit the web site as a guest user
    When I select "intransit" order as a "signed" user
    Then I navigate to order history page
    When I select return items button in "OH" page
    And I select items and continue to submit page
    Then I should navigate to return submit page
    When I select return submit from submit page
    Then I should see header section in return CONFIRMATION page which is same as site home page

        # @credit-5783 # @credit-5795 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Return Confirmation page returns no errors when a Signed-in user navigates through the FOB header to another page
    Given I visit the web site as a guest user
    When I select "submitted" order as a "signed" user
    Then I navigate to order history page
    When I select return items button in "OH" page
    And I select items and continue to submit page
    And I select return submit from submit page
    And I select any fob from the header
    Then I should be navigated to corresponding page

          # @credit-5783 # @credit-5795 # @14A
  @returns_regression @priority_low  @artifact_shopapp @shopapp_4 @defect_SSTREG-279 @use_regression @mode_domestic @returns_mgt_3 @domain_customer @feature_returnconfirmation @migrated_to_sdt
  Scenario: Return Confirmation page has same header and footer as My Account page as a guest user
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    Then I should navigate to return selection page
    When I select items and continue to submit page
    And I select return submit from submit page
    Then I should see header section in return "CONFIRMATION" page which is same as site home page
    Then I should see footer section which is same as my_account page
