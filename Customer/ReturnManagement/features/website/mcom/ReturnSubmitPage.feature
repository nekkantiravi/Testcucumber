# Author: Suma Dathrik
# Date Created: October 31, 2013
# Date Signed Off:

Feature: Verify Return Submit Page

########################################################################################################################
# @credit-5849 # @credit-5825 # @14A
  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @feature_returnsubmit @cm_dsv_high_10 @migrated_to_sdt
  Scenario: Estimated Refund is displayed on the Return Submit page
    Given I visit the web site as a guest user
    When I select "intransit" order as a "signed" user
    And I navigate to order history page
    And I select return items button in "OH" page
    When I select items and continue to submit page
    Then I verify return credit section


    # @credit-5829 # @credit-5852 # @14A
  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @feature_returnsubmit @use_domain_qual @migrated_to_sdt
  Scenario: Submit button redirect to the Return Confirmation page
    Given I visit the web site as a guest user
    When I navigate to return selection page using "submitted" order as a "guest" user
    And I select items and continue to submit page
    And I select return submit from submit page
    Then I should see no warning should be displayed
    And I should see a record should be created in DB
    And I should be navigated to return confirmation page

  # @credit-5821 # @credit-5846 # @14A
  @returns_regression @use_regression @mode_domestic @returns_mgt_2 @priority_high @shopapp_4 @artifact_shopapp @domain_customer @migrated_to_sdt
  Scenario: Email address is is the same as the provided Email page on the Return Submit page
    Given I visit the web site as a guest user
    When I select "shipping and billing same" order as a "signed" user
    Then I navigate to order details page as a "signed" user
    When I select return items button in "OD" page
    And I select items and continue to submit page
    Then I should see same email address which user has provided in the return selection page

  @returns_regression @migrated_to_sdt
  Scenario: Validations on Cancel return overlay redirects to the Return Submit page
    Given I visit the web site as a guest user
    When I navigate to submit page using "submitted" order as a "guest" user
    Then I should see the overlay text
      | Return for Order #<order_number> will be canceled.                 |
    And I select "No" in return cancel overlay
    Then I should navigate to return submit page

      # @credit-5939 # @credit-5940
  @returns_regression @artifact_shopapp @14A @s4a_stable @use_regression @mode_domestic @returns_mgt_2 @domain_customer @migrated_to_sdt
  Scenario: Cancel button redirects to Order History page from the Return Submit page when the session expires
    Given I visit the web site as a guest user
    When I navigate to submit page using "submitted" order as a "signed" user
    And I set cookie value
    And I select "Yes" in return cancel overlay
    And I should be navigated to OH page

  # @credit-5939 # @credit-5940
  @returns_regression @artifact_shopapp @14A @artifact_shopapp @use_regression @mode_domestic @returns_mgt_2 @domain_customer @migrated_to_sdt
  Scenario: Continue button redirects to Order History page from the Return Submit page when the session expires
    Given I visit the web site as a guest user
    When I navigate to submit page using "submitted" order as a "signed" user
    And I set cookie value
    Then I navigate to return confirmation page after the session expires
    And I should be navigated to OH page

  # @credit-5939 # @credit-5940
  @returns_regression @artifact_shopapp @14A @s4a_stable @use_regression @mode_domestic @returns_mgt_2 @domain_customer @migrated_to_sdt
  Scenario: Refreshing the browser redirects to Order History page from the Return Submit page when the session expires
    Given I visit the web site as a guest user
    When I navigate to submit page using "submitted" order as a "signed" user
    And I set cookie value
    Then I refresh current page
    And I should be navigated to OH page

  @returns_regression @14A @credit-5830 @use_regression @mode_domestic @priority_low
  @artifact_shopapp @returns_mgt_1 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Let us help link works on the Return Submit page for Signed-in user
    Given I visit the web site as a guest user
    When I navigate to return selection page using "submitted" order as a "signed in" user
    And I select items and continue to submit page
    And I should see the "let us help" link on the bottom of the page
    Then I navigate to "let us help" window page using let us help link in return submit page

    #############################################################################################################################
#Verify return credit information section in return submit page
#  Mingle Story: http://mingle/projects/credit/cards/5821

  @returns_regression @14A @credit-5821 @use_regression @mode_domestic @priority_low
  @artifact_shopapp @returns_mgt_1 @shopapp_3 @domain_customer @migrated_to_sdt
  Scenario: Help modal functionality verification
    Given I visit the web site as a guest user
    When I select "shipping and billing same" order as a "signed" user
    Then I navigate to order details page as a "signed" user
    When I select return items button in "OD" page
    And I select items and continue to submit page
    Then I should see overlay after selecting help icon in credit section
    And I should see help model shall disappear once user clicks on anywhere outside of the overlay

  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Selecting Yes on the cancel return overlay redirects to the Order Details page
    Given I visit the web site as a guest user
    When I navigate to submit page using "submitted" order as a "signed" user
    And I select Yes in cancel return overlay
    Then I should navigate to order details page

  #Mingle Story: http://mingle/projects/credit/cards/4898
  @returns_regression @14A @credit-4898 @use_regression @mode_domestic @defect_SSTREG-279 @priority_low  @artifact_shopapp @shopapp_3 @domain_customer @returns_mgt_2 @migrated_to_sdt
  Scenario: Header and Footer are correct for the Return Submit page for signed user
    Given I visit the web site as a guest user
    When I select "intransit" order as a "signed" user
    Then I navigate to order history page
    When I select return items button in "OH" page
    Then I should navigate to return selection page
    When I select items and continue to submit page
    Then I should see return "SUBMIT" page title
    And I should see header section in return "SUBMIT" page which is same as site home page
    And I should see new header and footer elements in section


  @returns_regression @14F @credit-6501 @artifact_shopapp @priority_medium @s4a_stable @use_regression @mode_domestic @returns_mgt_5 @domain_customer @migrated_to_sdt
  Scenario Outline: Refund method is correct when user selects gift payments
    Given I visit order history page as a guest user
    When I select "<order_type>" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    When I select line item
    And I select giftcard_payment option from refund section
    And I attempt to continue with emails in selection page
    Then I should be displayed with refund method "mail Gift Card to <Ship To Firstname Lastname>, <Address1>, <City>, <State> <Zipcode>" in return submit page
    Examples:
      | order_type                                                                        |
      | shipping_and_billing_address_different_for_creditcard                             |
      | shipping_and_billing_address_different_for_credit_and_giftcard                    |
      | shipping_and_billing_address_different_for_giftcard                               |
      | mixed_order_with_creditcard_and_different_shipping_and_billing_address            |
      | mixed_order_with_gift_card_and_different_shipping_and_billing_address             |
      | mixed_order_with_credit_card_gift_card_and_different_shipping_and_billing_address |

  @returns_regression @14A @credit-5822 @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_1 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Return instructions are correct for EGC and Credit Card orders
    Given I visit the web site as a guest user
    When I select "creditcard and giftcard" order as a "signed" user
    And I navigate to order history page
    When I select return items button in "OH" page
    Then I should navigate to return selection page
    When I select items and continue to submit page
    Then I should navigate to return submit page
    And I should see following return instruction in submit page
      | Please review your information and click "submit return" to continue.|

  @returns_regression @14A @credit-5822 @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_5 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Return instructions are correct for regular orders
    Given I visit the web site as a guest user
    When I select "shipping and billing same" order as a "signed" user
    Then I navigate to order history page
    When I select return items button in "OH" page
    Then I should navigate to return selection page
    When I select items and continue to submit page
    Then I should navigate to return submit page
    And I should see following return instruction in submit page
      | Please review your information and click "submit return" to continue.|

  #To verify the browser back button on return submit page
  #Mingle Story: http://mingle/projects/credit/cards/6034
  #http://mingle/projects/credit/cards/6035

  # @credit-6034 # @credit-6035 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Return Submit page redirects to Order History page when a Guest user clicks the back button
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    And I select items and continue to submit page
    Then I navigate to previous page from existing page
    And I should be navigated to OH page

       #Verify the order information section in return submit page.
  #Mingle Story: http://mingle/projects/credit/cards/5820
  @returns_regression @14A @credit-5820 @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_5 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Order Header and footer are correct on the Return Submit page for guest
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    Then I lookup with order number and emailaddress in OH page
    And I keep the order details from OD page
    When I select return items button in "OD" page
    And I select items and continue to submit page
    Then I verify order header in return "submit" page
    Then I should see footer section which is same as my_account page

      #To verify the return section on return submit page

  #Mingle Story:	http://mingle/projects/credit/cards/5824
  #http://mingle/projects/credit/cards/5848

  # @credit-5824 # @credit-5848 # @14A
  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @feature_returnsubmit @use_domain_qual @migrated_to_sdt
  Scenario: Verifying Item Column headers on the return submit page
    Given I visit the web site as a guest user
    When I navigate to return selection page using "submitted" order as a "guest" user
    And I select items and continue to submit page
    Then I should see items selected in selection page in "SUBMIT" page
    And I verify column names
      | item(s) for return |
      | return qty.        |
      | reason for return  |
      | price              |
      | credit subtotal    |

      # @credit-5941 # @credit-5942 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_1 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Return Submit URL redirects to Order History page as a Signed-in user
    Given I visit the web site as a registered user
    When I navigate with return "submit" url
    Then I should be navigated to OH page

      # @credit-5939  # @credit-5940
  @returns_regression @artifact_shopapp @14A @s4a_stable @use_regression @mode_domestic @returns_mgt_2 @domain_customer @migrated_to_sdt
  Scenario: Back button redirects to Order History page from the Return Submit page when the session expires
    Given I visit the web site as a guest user
    When I navigate to submit page using "shipping_and_billing_address_same_for_creditcard" order as a "signed" user
    And I set cookie value
    Then I navigate to previous page from existing page
    And I should be navigated to OH page

  @14H @artifact_shopapp @credit-6611 @priority_high @use_regression @mode_domestic @returns_mgt_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario Outline: Refund Method is correct on the Return Submit page
    Given I visit order history page as a guest user
    When I lookup with "<order_type>" order with gift return
    And I keep the order details from OD page
    Then I select return items button in "OD" page
    And I select items and continue to submit page
    Then I should be displayed with refund method "mail Gift Card to <Ship To Firstname Lastname>, <Address1>, <City>, <State> <Zipcode>" in return submit page
    Examples:
      | order_type                                                                       |
      | shipping_and_billing_address_different_for_creditcard                            |
      | shipping_and_billing_address_same_for_creditcard                                 |
      | shipping_and_billing_address_same_for_credit_and_giftcard                        |
      | shipping_and_billing_address_different_for_credit_and_giftcard                   |
      | shipping_and_billing_address_same_for_giftcard                                   |
      | shipping_and_billing_address_different_for_giftcard                              |
      | mixed_order_with_creditcard_and_same_shipping_and_billing_address                |
      | mixed_order_with_creditcard_and_different_shipping_and_billing_address           |
      | mixed_order_with_giftcard_and_same_shipping_and_billing_address                  |
      | mixed_order_with_gift_card_and_different_shipping_and_billing_address            |
      | mixed_order_with_credit_card_gift_card_and_same_shipping_and_billing_address     |
      | mixed_order_with_credit_card_gift_card_and_different_shipping_and_billing_address|