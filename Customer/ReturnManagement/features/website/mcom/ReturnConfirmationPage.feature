# Author: Suma Dathrik
# Date Created: October 31st, 2013
# Date Signed Off:

Feature: Verify Return Confirmation Page

  @returns_regression @14A @credit-5793 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_3 @creative @domain_customer @migrated_to_sdt
  Scenario: Print mailing label button exists on the Return Confirmation page
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "creditcard and giftcard" order as a "signed in" user
    Then I should see print_mailing_label buttons

    ##############To verify the CRL number and barcode on the return confirmation page######################################

#Mingle Story: http://mingle/projects/customer_management/cards/6400
#http://mingle/projects/customer_management/cards/6401

  # @credit-6400 # @credit-6401
  @returns_regression @14C @priority_high @use_regression @mode_domestic @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @feature_returnconfirmation @use_domain_qual @migrated_to_sdt
  Scenario: CRL number and Barcode exist on Return Confirmation page as a Guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "submitted" order as a "guest" user
    Then I should see barcode and crl number

     # @credit-6400 # @credit-6401
  @returns_regression @14C @priority_high @use_regression @mode_domestic @returns_mgt_2 @shopapp_4 @artifact_shopapp @domain_customer @feature_returnconfirmation @saucelab_C @saucelab_C_F1 @migrated_to_sdt
  Scenario: Barcode is displayed on the Return Confirmation page and is the same as the In Store barcode on the Order Details page as a Signed-in user
    Given I visit order history page as a guest user
    When I select "submitted" order as a "guest" user
    Then I navigate to order details page as a "guest" user
    And I get crl number and barcode
    And I select return items button in "OD" page
    And I select items and continue to submit page
    When I navigate to confirmation page
   Then I should see barcode in the header which is same as the instore return barcode shown on the Order Details Page
    And I should see crl number as fedfil reservation number

    ########################################################################################################################

######  Verify the details of the estimated refund under the return section in return confirmation page. ###############

  #Mingle Story: http://mingle/projects/credit/cards/5778
  #http://mingle/projects/credit/cards/5791

  # @credit-5778 # @credit-5791 # @14A
  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @feature_returnconfirmation @use_domain_qual @migrated_to_sdt
  Scenario: Estimated refund is correct on the Return Confirmation page as a Signed-in user
    Given I visit the web site as a guest user
    When I select "creditcard and giftcard" order as a "signed" user
    And I navigate to order history page
    And I select return items button in "OH" page
    And I select items and continue to submit page
    When I navigate to confirmation page
    Then I verify return credit section

    ########################################################################################################################
###########################Verify order information section in return confirmation page. ###############################

#Mingle Story:	http://mingle/projects/credit/cards/5788
#http://mingle/projects/credit/cards/5775

# @credit-5788 # @credit-5775 # @14A
  @use_regression @mode_domestic @returns_mgt_1 @priority_high @shopapp_4 @artifact_shopapp @domain_customer @feature_returnconfirmation @use_domain_qual @migrated_to_sdt
  Scenario: Email address is displayed correctly on the Return Submit page and is the same as the Return Selection page as a Signed-in user
    Given I visit the web site as a guest user
    When I select "shipping and billing same" order as a "signed" user
    Then I navigate to order details page as a "signed" user
    When I select return items button in "OD" page
    And I select items and continue to submit page
    Then I should see same email address on submit page which user has provided in the return selection page
    When I navigate to confirmation page
    Then I should see same email address on confirmation page which user has provided in the return selection page

  @14A @credit-5774 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_5 @shopapp_3 @domain_customer @migrated_to_sdt
  Scenario: Print Mailing Label is correct on the Return Confirmation page
    Given I visit order history page as a guest user
    When I select "creditcard and giftcard" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    And I select items and continue to submit page
    And I select return submit from submit page
    Then I should see two print_mailing_label buttons

