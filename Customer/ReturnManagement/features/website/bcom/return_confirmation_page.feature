Feature: Verify Return Confirmation Page

  @returns_regression @use_regression @mode_domestic @returns_mgt_5 @priority_high @shopapp_4 @artifact_shopapp @domain_customer @feature_returnconfirmation @migrated_to_sdt
  Scenario: Estimated Credit is displayed correctly on the Return Submit page as a Guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "submitted" order as a "guest" user
    Then I should see estimated credit as per the credit section

  @priority_high
  Scenario: Print Shipping label only appears if the Order Status is submitted as a guest user
    Given I visit order history page as a guest user
    When I navigate to confirmation page using "intransit" order as a "guest" user
    #Then I see return order status as "RETURN STATUS Submitted" on page
    Then I should see return status as "submitted" with updated date
    And I should see print mailing label buttons

  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_4 @domain_customer @cm_dsv_high_10
  Scenario: Print Shipping label does not appear if the Order status is not submitted as a Signed-in user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "intransit" order as a "guest" user
    And I set the return "intransit" status for "submitted" order in db
    And I navigate to order details page as a "signed" user
    Then I should not see print mailing button


  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Return Confirmation page has correct selected items and headers for item list as a Signed-in user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "intransit" order as a "guest" user
    And I select items and continue to submit page
    And I navigate to return confirmation page
    Then I should see items selected in selection page in "CONFIRMATION" page
    And I verify column names
      | ITEM DESCRIPTION  |
      | REASON FOR RETURN |
      | RETURN QTY        |
      | PRICE             |
      | TOTAL             |


  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @feature_returnconfirmation @use_domain_qual  @migrated_to_sdt
  Scenario: Users are redirected to the Order History page as a guest user when using the Return Confirmation URL directly
    Given I visit the web site as a guest user
    When I navigate with return "confirmation" url
    Then I should be navigated to OH page

          # @credit-5947 # @credit-5948
  @returns_regression @artifact_shopapp @14A @use_regression @mode_domestic @returns_mgt_5 @domain_customer @migrated_to_sdt
  Scenario: Order Confirmation page directs to Order History page when a Signed-in user clicks refresh after the session expires
    Given I visit the web site as a guest user
    When I navigate to submit page using "shipping_and_billing_address_same_for_creditcard" order as a "signed" user
    And I navigate to return confirmation page
    Then I should be navigated to return confirmation page
    Then I set cookie value
    Then I refresh the page
    And I should be navigated to OH page

         # @credit-5926 # @credit-5951 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Browser opens UPS dropoff page when a Signed-in user clicks the UPS dropoff link from the Return Confirmation page
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "submitted" order as a "signed in" user
    Then I navigate to "ups drop off" window page using drop off link in return confirmation page

    # @credit-5926 # @credit-5951 # @14A
  @returns_regression @use_regression @mode_domestic  @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Browser opens UPS content page when a Signed-in user clicks the UPS content link from the Return Confirmation page
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "submitted" order as a "guest" user
    Then I navigate to "ups content" window page using ups content link in return confirmation page

        # @credit-5772 # @credit-5786 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Return Confirmation breadcrumbs are correct as a Guest User
    Given I visit order history page as a guest user
    When I select "submitted" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    Then I should navigate to return selection page
    When I select items and continue to submit page
    Then I should navigate to return submit page
    When I select return submit from submit page
    Then I see "Return Items" is not a link in breadcrumb in "CONFIRMATION" page
    And I should see order history page using "Order History|Order Status & History" link in breadcrumb in "CONFIRMATION" page

  @returns_regression @14A @credit-5794 @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_1 @shopapp_3  @domain_customer @migrated_to_sdt
  Scenario: Contact Us link is visible on the return submit page as a Guest User
    Given I visit the web site as a guest user
    When I navigate to return selection page using "submitted" order as a "guest" user
    And I select items and continue to submit page
    And I select return submit from submit page
    Then I should see the "contact us" link on the bottom of the page
    And I navigate to "contact us" window page using contact us link in return submit page

        ########################################################################################################################
  #when user navigates away from the return confirmation page and use the back button to return
  #to the return confirmation page then the user should navigate to the order status page.

  #Mingle Story: http://mingle/projects/credit/cards/5804
  #              http://mingle/projects/credit/cards/5805

  # @credit-5804 # @credit-5805# @14A
  @returns_regression @use_regression @mode_domestic  @priority_medium  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Going back from the Return Confirmation page returns to the Order History page as a Guest user
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    And I select items and continue to submit page
    And I select return submit from submit page
    Then I navigate to previous page from existing page
    And I should be navigated to OH page

########################################################################################################################

  @returns_regression @14A @credit-5789 @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_1 @shopapp_3  @domain_customer @migrated_to_sdt
  Scenario: Return Instructions should be shown on the return confirmation page when an email is used as a Signed in User
    Given I visit order history page as a guest user
    When I select "shipping and billing different" order as a "signed" user
    And I navigate to order history page
    When I select return items button in "OH" page
    Then I should navigate to return selection page
    When I select items and continue to submit page
    And I select return submit from submit page
    Then I should see following return instruction in confirmation page on each site:
      |Please download and print the confirmation page and shipping label. If you have trouble downloading, simply print this page.Pack your merchandise inside a single box and include the confirmation page.Affix the shipping label to the outside. Please note the shipping label can be used only once.We will email you a copy of your label and tracking number at <email_address>.Please retain that email for your record.|

  @returns_regression @use_regression @mode_domestic @priority_low @artifact_shopapp @returns_mgt_1 @shopapp_3 @domain_customer @migrated_to_sdt
  Scenario: Return Confirmation page has correct meta elements
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "intransit" order as a "signed in" user
    Then I should see return "CONFIRMATION" page title
    And I should see the meta tag on Return "CONFIRMATION" page
    And I should see new header and footer elements in section