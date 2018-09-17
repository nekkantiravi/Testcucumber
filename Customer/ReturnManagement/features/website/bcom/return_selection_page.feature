Feature: Verify Return Selection Page

  @scenario1 @domain_customer_management @xbrowser @xbrowser_one @data_dependency @high @migrated_to_sdt
  Scenario: Email address is prefilled for Signed-in users on the Return Selection page
    Given I visit the web site as a guest user
    When I navigate to return selection page using "intransit" order as a "signed in" user
    And I select return items button in "OH" page
    Then I should see contact information section
    And I should see email address fields are pre-filled

  @scenario1 @domain_customer_management @xbrowser @xbrowser_one @data_dependency @high @migrated_to_sdt
  Scenario: Email address is not prefilled for Guest users on the Return Selection page
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    Then I should see contact information section
    And I verify email address fields are blank


  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_5 @shopapp_4 @s4a_stable @domain_customer @feature_returnselection @use_domain_qual
  Scenario:To verify that return submit page displays when selecting all mandatory fields as guest user
    Given I visit the web site as a guest user
    When I navigate to return selection page using "intransit" order as a "guest" user
    And I select items and continue to submit page
    Then I should navigate to return submit page

  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @domain_customer @feature_returnselection @use_domain_qual @migrated_to_sdt
  Scenario: To verify that user should be displayed with order number,order date and order total in the "Return Selection" page when navigates as signed user
    Given I visit the web site as a guest user
    When I navigate to OD page using "intransit" order as a "signed in" user
    Then I fetch order details from OD page
    When I select return items button in "OH" page
    Then I should navigate to return selection page
    And I verify order header in return selection page


  @14A @credit-5586 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_5 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:As a signed user verify the Btag header text and Btag instruction text in the Btag section
    Given I visit the web site as a guest user
    When I navigate to OD page using "submitted" order as a "signed in" user
    Then I keep the order details from OD page
    When I select return items button
    Then I should navigate to return selection page
    And I should see below in the btag section
      | heading_btag_text     | IMPORTANT NOTES ABOUT B-TAG                                                                           |
      | instruction_btag_text | Instructions for removal will be attached to the tag. If tag is removed, the item cannot be returned. |


  @14A @credit-5586 @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_1 @shopapp_3 @s4a_stable @domain_customer  @migrated_to_sdt
  Scenario:As a guest user should validate BTag Notes model functionality ie. Btag model text displayed in the Btag model overlay when user clicks on Btag help icon and model close button in the return selection page
    Given I visit order history page as a guest user
    When I navigate to return selection page using "submitted" order as a "guest" user
    Then I should navigate to return selection page
    And I should see model text in the model on clicking help icon in return selection page
      | model_text | Certain items are delivered with a black b-tag attached, with instructions for removal included. If the b-tag is removed, the item cannot be returned. |
    And I should not see model on selecting close

  @returns_regression @15B @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @use_regression @mode_domestic @feature_returnselection @migrated_to_sdt
  Scenario:Verify the line item check box behaviour on landing selection page as guest user
    Given I visit the web site as a guest user
    When I navigate to return selection page using "submitted" order as a "guest" user
    Then I should see all line items should be unchecked by default
    And I should see all line items upcs should be unique
    And I should see reason for return should be disabled with default value
    And I should see reason for return should contain all options as specified in reasons
      | Gift no longer wanted            |
      | Better price elsewhere           |
      | Not as pictured                  |
      | Not as described                 |
      | Shipping box/bag arrived damaged |
      | Sent wrong item                  |
      | Missed delivery date             |
      | Item no longer needed/wanted     |
      | Item Damaged                     |
      | Size did not fit                 |
      | Quality not as expected          |
      | Bought multiple sizes/colors     |
    And I should see page is displayed with all the quantity fields with default value one for item having more than one quantity
    And I should not see write a review option for any line item

          # @credit-5835 # @credit-5855# @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2
  @shopapp_4 @s4a_stable @domain_customer @feature_returnselection @migrated_to_sdt
  Scenario: Return Selection should redirect to the Order History page when a user hits the back button
    Given I visit the web site as a guest user
    When I select "intransit" order as a "signed" user
    And I navigate to order history page
    And I select return items button in "OH" page
    Then I should navigate to return selection page
    And I navigate to previous page from existing page
    And I should be navigated to OH page

          # @credit-5802 # @credit-5594 # @14A
  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_1
  @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify that error message displayed when email_address and confirm_email_address does not match and clicks continue button as signed user
    Given I visit the web site as a guest user
    When I navigate to return selection page using "intransit" order as a "signed in" user
    And I attempt to continue with different emails in selection page
    Then I should see error message above the order details header
      |Please complete all the required fields. Email Address|

         # @credit-5802 # @credit-5594 # @14A
  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_1 @shopapp_4
  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify that an error message displayed if "reason for return" dropdown value is empty or default value "Select a Reason for Return" exists for any return item as guest user
    Given I visit the web site as a guest user
    When I navigate to return selection page using "intransit" order as a "guest" user
    And I attempt to continue without selecting reason for return in selection page
    Then I should see error message above the order details header
      |Please complete all the required fields. Reason For Return|

  @returns_regression @14A @priority_low  @use_regression @mode_domestic
  @artifact_shopapp @returns_mgt_1 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Header and Footer are correct for the Return Selection page as a signed in user
    Given I visit the web site as a guest user
    When I select "submitted" order as a "signed" user
    And I navigate to order history page
    And I select return items button in "OH" page
    Then I should navigate to return selection page
    And I should see return "SELECTION" page title
    And I should see the meta tag on Return "SELECTION" page
    And I should see new header and footer elements in section

          # @credit-5929 # @credit-5930
  @returns_regression @14A @artifact_shopapp @s4a_stable @use_regression @mode_domestic
  @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario:Verify that for signed in user is able to navigate Order Status page when clicks browser back button after session expires on the return selection page
    Given I visit the web site as a guest user
    When I navigate to selection page using "shipping_and_billing_address_same_for_creditcard" order as a "signed" user
    And I set cookie value
    Then I navigate to previous page from existing page
    And I should be navigated to OH page

           # @credit-5929 # @credit-5930
  @returns_regression @artifact_shopapp @14A @s4a_stable @use_regression @mode_domestic @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario:Verify that for signed in user is able to navigate SignIn page when clicks clicking on Continue button after session expires on the return selection page
    Given I visit the web site as a guest user
    When I navigate to return selection page using "shipping_and_billing_address_same_for_creditcard" order as a "signed in" user
    And I set cookie value
    Then I select mandatory fields and continue
    And I should be navigated to OH page

  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp
  @returns_mgt_2 @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: To verify that on selecting YES in modal overlay user should be redirected to order details page as signed user
    Given I visit the web site as a guest user
    When I navigate to selection page using "submitted" order as a "signed" user
    And I select Yes in cancel return overlay
    Then I should navigate to order details page

           ########################################################################################################################

  #Refund Method selection should be visible if any part of Ship To and Bill To name or address mismatches.

  #Mingle Story: http://mingle/projects/customer_management/cards/6496
  #http://mingle/projects/customer_management/cards/6494

  # @credit-6496 # @credit-6494 #Code-Optimization
  @returns_regression @14F @artifact_shopapp @use_regression @mode_domestic @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario Outline: Verify the display of refund section in return selection page in different cases as a signed user
    Given I visit order history page as a guest user
    When I select "<order_type>" order as a "signed" user
    And I navigate to order details page as a "signed" user
    And I select return items button in "OD" page
    Then I should navigate to return selection page
    And I should verify that refund method section "<visibility>" in page
    Examples:
      | order_type                                                             | visibility  |
      | shipping_and_billing_address_same_for_creditcard                       | not visible |
      | shipping_and_billing_address_different_for_creditcard                  | visible     |
      | shipping_and_billing_address_same_for_credit_and_giftcard              | not visible |
      | shipping_and_billing_address_different_for_credit_and_giftcard         | visible     |
      | shipping_and_billing_address_different_for_giftcard                    | visible     |
      | shipping_and_billing_address_same_for_giftcard                         | not visible |
      | bops_with_creditcard                                                   | not visible |
      | bops_with_giftcard                                                     | not visible |
      | bops_with_creditcard_and_giftcard                                      | not visible |
      | mixed_order_with_creditcard_and_same_shipping_and_billing_address      | not visible |
      | mixed_order_with_creditcard_and_different_shipping_and_billing_address | visible     |
      | mixed_order_with_giftcard_and_same_shipping_and_billing_address        | not visible |
      | mixed_order_with_gift_card_and_different_shipping_and_billing_address  | visible     |
      | order_with_first_name_mismatch                                         | visible     |
      | order_with_city_mismatch                                               | visible     |
      | order_with_zipcode_mismatch                                            | visible     |
      | order_with_phone_number_mismatch                                       | not visible |
      | order_with_email_address_mismatch                                      | not visible |

  @returns_regression @14A @credit-5718 @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_5 @shopapp_3 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Contact us link is correct on the return selection page
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    Then I should navigate to return selection page
    And I should see the "contact us" link on the bottom of the page
    And I navigate to "contact us" window page using contact us link in return selection page

          # @credit-5582 # @credit-5612 # @14A
  @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the Order History link in breadcrumb for the return items page as guest user.
    Given I visit order history page as a guest user
    When I select "submitted" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    Then I should navigate to return selection page
    And I see "Return Items" is not a link in breadcrumb in "SELECTION" page
    And I should see order history page using "Order History|Order Status & History" link in breadcrumb in "SELECTION" page

         # @credit-5717 # @credit-5593 # @14A
  @returns_regression @use_regression @mode_domestic @priority_medium @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify that on selecting NO in modal overlay user should be return selection page as guest user
    Given I visit the web site as a guest user
    When I navigate to return selection page using "submitted" order as a "guest" user
    And I select No in cancel return overlay
    Then I should navigate to return selection page

        # @credit-5929  # @credit-5930 @backlog
  @returns_regression @artifact_shopapp @14A @s4a_stable @domain_customer @use_regression @mode_domestic @returns_mgt_5 @migrated_to_sdt
  Scenario:Verify that for signed in user is able to navigate OH page when clicks clicking on Cancel button after session expires on the return selection page
    Given I visit the web site as a guest user
    When I navigate to selection page using "shipping_and_billing_address_same_for_creditcard" order as a "signed" user
    And I set cookie value
    Then I select Yes in cancel return overlay
    And I should be navigated to OH page

 @returns_regression @use_regression @mode_domestic @priority_low  @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @migrated_to_sdt
 Scenario:Verify previously selected line item values as default as signed user
  Given I visit the web site as a guest user
  When I navigate to selection page using "submitted" order as a "signed" user
  And I select line item
  And I deselect selected line item
  And I select same line item
  Then I should see previous selected line item values as default

   # @credit-5800 # @credit-5585 # @14A
  @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @domain_customer @feature_returnselection @use_domain_qual @migrated_to_sdt
  Scenario:Verify reason for return and quantity fields are able to select on selection of related line item only as guest user
    Given I visit the web site as a guest user
    When I navigate to selection page using "submitted" order as a "guest" user
    And I select line item
    Then I should able to select reason for return
    And I should able to select quantity when quantity is more than one
