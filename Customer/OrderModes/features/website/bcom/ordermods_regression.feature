# Author: Ranganath Ellur
# Date Created: 06/26/2017

Feature: Verify order mode features

  @scenario3 @domain_customer_management @xbrowser @xbrowser_one @data_dependency @migrated_to_sdt
  Scenario: To verify Processing items display in OH and OD Pages for the first order and first item.
    Given I visit the web site as a guest user
    When I associate "intransit" order in db
    And I navigate to the "order status" page from footer
    Then I verify order details for "processing|PROCESSING" items in OH page
    And I verify order details in OD page

@prod @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @s4a_stable @launch_call @domain_customer @migrated_to_sdt
  Scenario:To verify the 301 redirections to OH page
  Given I visit the web site as a guest user
  When I navigate with order history legacy url
  Then I should be navigated to OH page

  @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify the zero orders display in the order history page
    Given I visit the web site as a guest user
    And I create a new profile
    And I navigate to order history page
    Then I should see zero orders

  @CD_BAT_NEXT @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @use_domain_qual @migrated_to_sdt
  Scenario:To verify the Guest sign-in functionality using - already signed in user has profile in site
    Given I visit the web site as a guest user
    When I create a new profile
    And I sign out from my current profile
    And I navigate to order history page
    When I click on "goto_order_history_sign_in" in "order_status" page
    And I sign in to my existing profile and close security question
    Then I should be navigated to OH page

  @use_regression @mode_domestic  @priority_medium  @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @use_domain_qual @migrated_to_sdt
  Scenario:To verify the Guest sign-in functionality using create profile
    Given I visit order history page as a guest user
    When I click on "goto_order_history_sign_in" in "order_status" page
    And I create a new profile
    Then I should be navigated to My Account Page

  @CD_BAT_NEXT @use_regression @mode_domestic @priority_low  @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @use_domain_qual  @migrated_to_sdt
  Scenario:To verify the breadcrumb functionality in OH page as a guest user
    Given I visit order history page as a guest user
    Then I see "Order Status & History" is not a link in breadcrumb in "OH" page
    When I navigate to my account page using "My Account" link in breadcrumb in "OH" page for "guest" user
    Then I create a new profile

  @priority_low  @use_regression @mode_domestic @artifact_shopapp @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the breadcrumb functionality in OH page as a signed in user
    Given I visit the web site as a registered user
    And I navigate to the "order status" page from footer
    Then I see "Order Status & History" is not a link in breadcrumb in "OH" page
    And I navigate to my account page using "My Account" link in breadcrumb in "OH" page for "signin" user

  @prod  @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @domain_customer @release_16A @migrated_to_sdt
  Scenario: To verify error messages upto 4 attempts for order number field using email address
    Given I visit order history page as a guest user
    When I lookup with incorrect order number and valid emailaddress in OH page
      |order_number   | email               |
      |incorrect_order| gerajend@in.ibm.com |
    Then I verify error messages upto 4 attempts
      | We couldn't find an order with the information you provided. Please double check it and re-enter. |

  @prod @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @domain_customer @release_16A @migrated_to_sdt
  Scenario: To verify error message upto 4 attempts for order number field using phone number
    Given I visit order history page as a guest user
    When I lookup with incorrect order number and valid phonenumber in OH page
      |order_number   | phone_area_code | phone_exchange | phone_subscriber |
      |incorrect_order| 123             | 123            | 1234             |
    Then I verify error messages upto 4 attempts
      |We couldn't find an order with the information you provided. Please double check it and re-enter.|

  @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @release_16A @migrated_to_sdt
  Scenario:To verify error message upto 4 attempts for email address field
    Given I visit order history page as a guest user
    When I lookup with valid order number and incorrect emailaddress in OH page
      |order_number    | email        |
      |free_ship_order | sss@test.com |
    Then I verify error messages upto 4 attempts
      | At this time we are unable to retrieve your order information. Please try again later or contact Customer Service at 1-800-777-0000|

  @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @release_16A  @migrated_to_sdt
  Scenario:To verify error message upto 4 attempts for phone number field
    Given I visit order history page as a guest user
    When I lookup with valid order number and incorrect phonenumber in OH page
      |order_number   | phone_area_code | phone_exchange | phone_subscriber |
      |free_ship_order| 123             | 123            | 1234             |
    Then I verify error messages upto 4 attempts
      | We couldn't find an order with the information you provided. Please double check it and re-enter.|
    
  @prod @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
   Scenario:To verify the lock out error message for order number field
    Given I visit order history page as a guest user
    When I lookup with incorrect order number and valid emailaddress in OH page
      |order_number   | email          |
      |incorrect_order| TEST@MACYS.COM |
    Then I verify lockout error message after 4 attempts
      | We're still unable to locate your order. Please call our Customer Service Department at 1-800-777-0000, available 24 hours a day, 7 days a week.|

  @prod @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the lock out error message for Email Address field
    Given I visit order history page as a guest user
    When I lookup with valid order number and incorrect emailaddress in OH page
      |order_number   | email        |
      |free_ship_order| sss@test.com |
    Then I verify lockout error message after 4 attempts
     | We're still unable to locate your order. Please call our Customer Service Department at 1-800-777-0000, available 24 hours a day, 7 days a week. |

  @prod @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the lock out error message for Phone Number field
    Given I visit order history page as a guest user
    When I lookup with valid order number and incorrect phonenumber in OH page
      |order_number   | phone_area_code | phone_exchange | phone_subscriber |
      |free_ship_order| 123             | 123            | 1234             |
    Then I verify lockout error message after 4 attempts
      | We're still unable to locate your order. Please call our Customer Service Department at 1-800-777-0000, available 24 hours a day, 7 days a week.|

  #MCOM-92016
  @prod @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @launch_call @mustpass @health_check @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the FAQ widget on order history page for guest user.
    Given I visit the web site as a guest user
    When I navigate to order history page
    Then I should see FAQ widget with below content:
      | POPULAR ORDER QUESTIONS                         |
      | STILL HAVE A QUESTION?                          |
      | and we'll be happy to assist you with questions about your order|
    And I verify the faq links in FAQ widget

  @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the counter resets upon successful submission and not reset if user navigates away from the page and then navigates back.
    Given I visit order history page as a guest user
    When I lookup with valid order number and incorrect emailaddress in OH page
      |order_number   | email        |
      |free_ship_order| sss@test.com |
    Then I verify error messages upto 4 attempts
      | We couldn't find an order with the information you provided. Please double check it and re-enter.|
    When I lookup with valid order number and valid emailaddress in OH page
      |order_number   | email          |
      |free_ship_order|  TDM@MACYS.COM |
    Then I navigate to order history page from order details page
    When I lookup with valid order number and incorrect phonenumber in OH page
      |order_number     | phone_area_code | phone_exchange | phone_subscriber |
      |free_ship_order  | 123             | 123            | 1234             |
    Then I verify error messages upto 4 attempts
      | We couldn't find an order with the information you provided. Please double check it and re-enter. |

  @prod @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @launch_call @s4a_stable @domain_customer @feature_orderhistory @migrated_to_sdt
  Scenario:To verify the Miscellaneous links on the Order History page
    Given I visit the web site as a guest user
    When I navigate to order history page
    Then I should see misc links navigate to corresponding pages

  @priority_low  @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @migrated_to_sdt
  Scenario: To verify the breadcrumb functionality in OD page as a signed in user
    Given I visit order history page as a signed user
    When I lookup with valid order number and valid emailaddress in OH page
      |order_number                                          | email         |
      |shipping_and_billing_address_different_for_creditcard | TDM@MACYS.COM |
    And I see "Order Details" is not a link in breadcrumb in "OD" page
    Then I navigate to my account page using "My Account" link in breadcrumb in "OD" page for "signin" user
    And I navigate to order history page
    And I lookup with valid order number and valid emailaddress in OH page
      |order_number                                          | email         |
      |shipping_and_billing_address_different_for_creditcard | TDM@MACYS.COM |
    Then I navigate to order history page using link in breadcrumb in OD page

  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @feature_orderhistory @feature_orderdetails @migrated_to_sdt
  Scenario:To verify hierarchy for normal items in OH page
    Given I visit order history page as a guest user
    When I associate "normal_hierarchy" order in db
    And I navigate to order history page
    Then I verify order of displaying order level details section for "normal" orders in OH page

  @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @migrated_to_sdt
  Scenario: To verify the display of Additional fee in order details page
    Given I visit the web site as a guest user
    When I associate "surcharge_order" order in db
    And I navigate to the order details page as a signed in user
    Then I should see additional fee

  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @feature_orderhistory @feature_orderdetails @migrated_to_sdt
  Scenario: To verify High level tracking details in OH and OD pages
    Given I visit the web site as a guest user
    When I associate "Highlevel_tracking" order in db
    And I navigate to order history page
    Then I verify "Highlevel" tracking details in "OH" page
    And I verify "Highlevel" tracking details in "OD" page
      # Note: Expected to fail due to unavailable service at MST endpoints - AUTO-1171

  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @feature_orderhistory @feature_orderdetails @cm_dsv_high_10 @migrated_to_sdt
  Scenario: To verify multiple tracking details in OH and OD pages
    Given I visit the web site as a guest user
    When I associate "Multiple_tracking" order in db
    And I navigate to order history page
    Then I verify "Multiple" tracking details in "OH" page
    And I verify "Multiple" tracking details in "OD" page
  # Note: Expected to fail due to unavailable service at MST endpoints - AUTO-1171

  @priority_low   @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @health_check @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify the breadcrumb functionality in OD page as a guest user
    Given I visit order history page as a guest user
    When I lookup with valid order number and valid emailaddress in OH page
      |order_number                                          | email         |
      |shipping_and_billing_address_different_for_creditcard | TDM@MACYS.COM |
    And I see "Order Details" is not a link in breadcrumb in "OD" page
    Then I navigate to my account page using "My Account" link in breadcrumb in "OD" page for "guest" user
    And I navigate to OH page by creating a new profile
    And I lookup with valid order number and valid emailaddress in OH page
      |order_number                                          | email         |
      |shipping_and_billing_address_different_for_creditcard | TDM@MACYS.COM |
    Then I navigate to order history page using link in breadcrumb in OD page

  @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify the orders display as per selected date range in the order history page
    Given I visit the web site as a guest user
    When I associate "cancel" order in db
    And I navigate to order history page
    #Then I should verify orders displayed for "current_year" date range
    Then I should verify orders displayed for "last 60 days" date range

      #BLCOM-77132
  #MCOM-86622
  @domain_customer @use_e2e @use_regression @mode_domestic @migrated_to_sdt
  Scenario: Order Details - cancelled as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "guest" user
    And I should see order confirmation section displayed with order details
    Then I capture order number and email address from order_confirmation page
    When I navigate to order details page from footer
    And I lookup with order number and emailaddress in Order Status page
    Then I verify the ability to cancel the order in order history page

  @priority_low  @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @launch_call @s4a_stable @domain_customer @use_domain_qual @migrated_to_sdt
  Scenario:Verify the page header in order history page as a signed user
    Given I visit the web site as a registered user
    And I navigate to the "order status" page from footer
    Then I verify the page header in OH page


  @scenario5 @domain_customer_management @xbrowser @xbrowser_one @data_dependency  @migrated_to_sdt
  Scenario:To verify BOPS Picked up items display in OH and OD page
    Given I visit the web site as a guest user
    When I associate "BOPS_PU" order in db
    And I navigate to the "order status" page from footer
    Then I verify order details for "picked up|IN-STORE PICK UP Items Picked Up" items in OH page
    And I verify order details in OD page

  @scenario4 @domain_customer_management @xbrowser @xbrowser_one @data_dependency @migrated_to_sdt
  Scenario: To verify returned items display in OH and OD pages
    Given I visit the web site as a guest user
    When I associate "return" order in db
    And I navigate to the "order status" page from footer
    Then I verify order details for "returned|RETURNED" items in OH page
    And I verify order details in OD page

  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @feature_orderhistory @feature_orderdetails @migrated_to_sdt
  Scenario: To verify order details for backordered items in OH and OD page
    Given I visit the web site as a guest user
    When I associate "backordered" order in db
    And I navigate to the "order status" page from footer
    Then I verify order details for "backordered|BACKORDERED" items in OH page
    And I verify order details in OD page

  @scenario1 @domain_customer_management @xbrowser @xbrowser_one @product_data_dependency @migrated_to_sdt
  Scenario: Order History - cancelled as a Signed in user
    Given I visit the web site as a registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I capture order number from order_confirmation page
    And I navigate to order details page from footer
    Then I verify the ability to cancel the order in order history page

  @scenario6 @domain_customer_management @xbrowser @xbrowser_one @data_dependency @migrated_to_sdt
  Scenario: Order Details - Order Mod cancelled as a singed in user
    Given I visit the web site as a registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I capture order number from order_confirmation page
    And I navigate to order details page from footer
    Then I verify the ability to cancel the order in order details page

  @prod @use_regression @mode_domestic @priority_medium @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario Outline:To verify front end validations for Order Lookup View Order Status Button Functionality
    Given I visit order history page as a guest user
    Then I verify validations for order number "<order_number>", email address "<email>", area code "<phone_area_code>", exchange number "<phone_exchange>", subscriber number "<phone_subscriber>", error message "<error_msg>" and combination "<order_email_combination>"
    Examples:
      |order_number  | email             | phone_area_code | phone_exchange | phone_subscriber | error_msg                                                                                | order_email_combination |
      |invalid_order | samplemailaddress |                 |                |                  | Your Email Address must be entered in this format: jane@company.com. Please try again.   | Y                       |
      |invalid_order | !@#!@#!@#!@#      |                 |                |                  | Your Email Address must be entered in this format: jane@company.com. Please try again.   | Y                       |
      |invalid_order |                   | 1               | 1              | 2                | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   |                 | 123            | 1234             | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | 123             |                | 1234             | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | 123             | 123            |                  | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | !@#             | 123            | 1234             | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | 123             | !@#            | 1234             | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | 123             | 123            | !@#$             | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | abc             | 123            | 1234             | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | 123             | abc            | !@#$             | Your phone number must be 10-digits, including area code                                 | N                       |
      |invalid_order |                   | 123             | 123            | abcd             | Your phone number must be 10-digits, including area code                                 | N                       |

  @priority_medium @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @launch_call @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify the navigation for left nav links in order history page as a signed user
    Given I visit the web site as a registered user
    And I navigate to the "order status" page from footer
    Then I verify left navigation link names shown as following:
      | link_name                      | left_nav_links                                                                               |
      | goto_my_profile                | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_my_address_book_link      | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_my_wallet_link            | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_furniture_mattress_status | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_order_status              | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_my_credit_card            | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Orders, Macy's Credit Card |

  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @feature_orderhistory @feature_orderdetails @migrated_to_sdt
  Scenario: To verify Delivered items display in OH and OD Pages for the first order and first item.
    Given I visit the web site as a guest user
    When I associate "delivered" order in db
    And I navigate to order history page
    Then I verify order details for "delivered:|DELIVERED" items in OH page
    And I verify order details in OD page
  # Note: Expected to fail due to unavailable service at MST endpoints - AUTO-1171

  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @feature_orderhistory @feature_orderdetails @use_domain_qual  @migrated_to_sdt
  Scenario:To verify Cancelled items display in OH and OD pages
    Given I visit the web site as a guest user
    When I associate "cancel" order in db
    And I navigate to order history page
    Then I verify order details for "canceled|CANCELLED" items in OH page
    And I verify order details in OD page

    #Test : BOPS - processing for store pick-up - 27
  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @use_domain_qual  @migrated_to_sdt
  Scenario:To verify BOPS Processing for store Pick-up items display in OH and OD pages
    Given I visit the web site as a guest user
    When I associate "BOPS_processing" order in db
    And I navigate to order history page
    Then I verify order details for "processing for store pick-up|IN-STORE PICK UP Processing" items in OH page
    And I verify order details in OD page

          # Test : BOPS ready for pick-up - 28
  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @domain_customer @feature_orderhistory @feature_orderdetails @migrated_to_sdt
  Scenario: To verify BOPS Ready for Pick-up items display in OH and OD page
    Given I visit the web site as a guest user
    When I associate "BOPS_RFP" order in db
    And I navigate to order history page
    Then I verify order details for "ready for pick-up|IN-STORE PICK UP Ready for pick up" items in OH page
    And I verify order details in OD page

  @priority_high @use_regression @mode_domestic @artifact_shopapp @shopapp_2 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:To verify BOPS Abandoned(Failure to Pick-up) items display in OH and OD page
    Given I visit the web site as a guest user
    When I associate "BOPS_FTP" order in db
    And I navigate to order history page
    Then I verify order details for "refunded by store|IN-STORE PICK UP Cancelled" items in OH page
    And I verify order details in OD page

  @zat_backlog @domain_customer @use_regression @mode_domestic @migrated_to_sdt
  Scenario: Order link redirection from My Account page
    Given I visit the web site as a registered user
    And I associate 3 orders to the profile
    When I navigate to my account page
    Then I verify the order link redirection in my account page