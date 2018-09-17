# Author: Vamsi Sundara
# Date Created: October 30, 2013
# Date Signed Off:

Feature: Verify Return Button in Order history and Order Details Page

######################################### Return Button in Order Details Page ##########################################

  #Verify the "Return Items" button so that I can start my return process by selecting the button in the Order Details page.

  #Mingle Story: http://mingle/projects/credit/cards/5383
  #http://mingle/projects/credit/cards/5565

  # @credit-5383 # @credit-5565 # @14A
  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @use_domain_qual @migrated_to_sdt
  Scenario: Returns button is displayed on the Order Details page when a Guest user has a Delivered, BOPS Picked up or Intransit Order
    Given I visit order history page as a guest user
    When I select "submitted" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    Then I verify return button as per shipment details in the order "details" page

    ######################################### Return Button in Order History Page ##########################################

#Verify "Return Items" button at the order level, if a minimum of "1" shipment has been delivered (or picked up if BOPS) within
#the order. I should be taken to the flow to initiate a return for the current order when I click on the "Return Items" button.

#Mingle Story: http://mingle/projects/credit/cards/4920
#http://mingle/projects/credit/cards/4919

# @credit-4920 # @credit-4919 # @14A
  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Returns button is displayed on the Order History page when a Guest user has a Delivered, BOPS Picked up or Intransit Order
    Given I visit the web site as a guest user
    When I select "submitted" order as a "signed" user
    And I navigate to order history page
    Then I verify return button as per shipment details in the order "history" page
