#Author: UFT team
#Date Created: 05/10/2017
#Date Signed Off:
#Version One: B-78044

Feature: Remove word "My" from Account LHN

  @artifact_shopapp @domain_customer @release_17I @mode_domestic @project_UFT @use_regression @run_this
  Scenario: Verify that My wording is removed from left navigation of account pages
    Given I visit the web site as a signed in user
    Then I verify left navigation link names shown as following:
      | link_name                      | left_nav_links                                                                               |
      | goto_my_profile                | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_my_address_book_link      | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_my_wallet_link            | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_furniture_mattress_status | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_order_status              | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Order History, Macy's Credit Card |
      | goto_my_credit_card            | Profile, Preferences, Address Book, Wallet, Plenti, Lists, Orders, Macy's Credit Card        |

  @artifact_shopapp @domain_customer @release_17I @mode_domestic @project_UFT @use_regression
  Scenario: Verify that breadcrumb is updated in account pages
    Given I visit the web site as a signed in user
    Then I should see bread crumb is updated in following pages as following:
      | link_name                      | bread_crumb                                     |
      | goto_my_profile                | My Account>Profile                              |
      | goto_my_address_book_link      | My Account>Address Book                         |
      | goto_my_wallet_link            | My Account>Wallet                               |
      | goto_furniture_mattress_status | My Account>Furniture & Mattress Delivery Status |