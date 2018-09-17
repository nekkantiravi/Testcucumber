###########################################################################
          # Story: B-86984 ::SDT:UI::Desktop::MCOM::Comp-Credit Gateway Redesign:: Left navigation
          # Author: QE Team
          ############################################################################

Feature: As a user visiting bloomingdales.com, I want to see a link to the Credit Gateway, Learn More & Apply, and Cardholder Benefits pages in the left navigation so that I may easily access each of these pages.

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify left navigation in Preferences page for registered user without any Card added to profile
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    When I navigate to My Preferences page from responsive gateway page
    And I should see below credit card links in Responsive page left navigation:
      | link_text                   | credit_link                    |
      | Macy's Credit Card          | goto_my_credit_card            |
      | Furniture & Mattress Status | goto_furniture_mattress_status |
      | Wallet                      | goto_my_wallet_link            |
      | Profile                     | goto_my_profile                |
      | Preferences                 | goto_my_preferences_link       |
      | Address Book                | goto_my_address_book_link      |
      | Lists                       | goto_my_wish_list              |
      | Plenti                      | goto_my_plenti                 |
      | Gift Card Balance           | goto_gift_card_balance         |
    But I should not see the following links in left navigation primary menu
      | goto_cardholder_benefits  |
      | goto_apply_and_learn_more |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify left navigation in credit gateway page for registered user without any Card added to profile
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I should see below credit card links in Responsive page left navigation:
      | link_text                   | credit_link                    |
      | Macy's Credit Card          | goto_my_credit_card            |
      | Cardholder Benefits         | goto_cardholder_benefits       |
      | Learn More & Apply          | goto_apply_and_learn_more      |
      | Furniture & Mattress Status | goto_furniture_mattress_status |
      | Wallet                      | goto_my_wallet_link            |
      | Profile                     | goto_my_profile                |
      | Preferences                 | goto_my_preferences_link       |
      | Address Book                | goto_my_address_book_link      |
      | Lists                       | goto_my_wish_list              |
      | Plenti                      | goto_my_plenti                 |
      | Gift Card Balance           | goto_gift_card_balance         |


  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: Verify the left navigation links functionality in preferences page
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    When I navigate to My Preferences page from responsive gateway page
    When I select "<new_link>" link
    Then I should reach on the "<target>" page
    Examples:
      | new_link                       | target                    |
      | goto_my_credit_card            | /my-credit/gateway        |
      | goto_furniture_mattress_status | /account/furniture        |
      | goto_my_wallet_link            | /account/wallet           |
      | goto_my_profile                | /account/profile          |
      | goto_my_preferences_link       | /account/preferences      |
      | goto_my_address_book_link      | /account/addressbook      |
      | goto_my_wish_list              | /wishlist/mylist          |
      | goto_my_plenti                 | /ce/splash/plenti/welcome |
      | goto_gift_card_balance         | /account/giftcardbalance  |
  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify the UI of gateway page along with fields/content for signed in user without card
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    Then I should see below elements in credit service gateway page:
      | credit_card_headline      |
      | add_card_section          |
      | other_ways_to_pay_content |
      | apply_now_box             |
      | activation_box            |
      | agreement_privacy_notice  |
      | amex_card_disclaimer_text |
      | credit_card_faq           |
      | contact_us                |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario Outline: : Verify Add Card, Activate Card\Apply Now button functionality on gateway page for Signed in user without card
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    And I select "<citi_page>" link in gateway sign page
    Then I should reach on the "<landing_page>" page
    Examples:
      | citi_page        | landing_page                |
      | add_card_button  | /RSnextgen/svc/launch/      |
      | activate_card    | /RSnextgen/svc/registration |
      | apply_now_button | /CRS/acq/launch/            |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify the UI in other ways to pay box in gateway page for signed in user for MCOM & BCOM
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    Then I should see below text in "other_ways_to_pay_content" section:
      | Don't want to pay your bill online?          |
      | You can also pay in store, by mail or phone. |
      | Find Out More                                |
    And I select "Find_Out_More_link" link in gateway sign page
    Then I should see "other_ways_to_pay_overlay" on gateway page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify the UI elements in apply now section
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    Then I should see below text in "apply_now_box" section:
      | Learn More           |
      | Exclusions & Details |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify the UI for learn more link for signed in user
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    And I select "learn_more" link in gateway sign page
    Then I should reach on the "/ce/creditservice/marketing/main" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify the UI for Exclusions and Details link for signed in user
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    And I select "exclusions_and_details" link in gateway sign page
    Then I should reach on the "/cms/slp/3/060914strrwrdscrdt20excl" page
#

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify other ways to pay overlay display when user not having card
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I tap on "other_ways_to_pay_link"
    Then I should see other ways to pay overlay
    And  I should see below information:
      | other_ways_to_pay_content |
      | close_other_ways_to_pay   |
      | find_store_button         |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify find store button on other ways to pay overlay when user not having card
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "find_store_button"
    Then I should reach on the "/shop/store/search" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verify close icon on other ways to pay overlay when user not having card
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "close_other_ways_to_pay"
    Then I should not see other ways to pay overlay

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario: Verification of Credit Agreements & Privacy Notice link and it's expansion in Credit Gateway Page as a registered user
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    Then I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol
    When I tap on "expand_credit_agreement_and_privacy_notice"
    Then I should see below links under Credit Agreements & Privacy Notice link
      | link_text                              | credit_link              |
      | Macy's Credit Card Agreement           | credit_card_agreement    |
      | Macy's American Express Card Agreement | amex_card_agreement      |
      | Privacy Notice                         | goto_privacy_policy_link |


  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17T
  Scenario Outline: To verify if all the links of Credit Agreements & Privacy Notice as a registered user
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I tap on "expand_credit_agreement_and_privacy_notice"
    And I tap on "<link>"
    Then I should reach on the "<target>" page
    Examples:
      | link                     | target                                                             |
      | credit_card_agreement    | dyn_img/banners/Macys_Credit_Card_Agreement.pdf                    |
      | amex_card_agreement      | dyn_img/banners/Macys_American_Express_Card_Agreement.pdf          |
      | goto_privacy_policy_link | dyn_img/banners/Department_Stores_National_Bank_Privacy_Notice.pdf |