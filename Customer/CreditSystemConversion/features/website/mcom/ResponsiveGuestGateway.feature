##########################################################################
# Story: B-86984 :: SDT:UI::Desktop::MCOM::Comp-Credit Gateway Redesign:: Left navigation
#        B-86968 :: SDT: Desktop : MCOM Automation for Guest view
# Author: QE Team
############################################################################

Feature: As a user visiting macys.com, I want to see a link to the Credit Gateway, Learn More & Apply, and Cardholder Benefits pages in the left navigation so that I may easily access each of these pages.

  domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verify the left navigation link names in credit gateway page for Guest user
    Given I visit the web site as a guest user
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

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario Outline: Verify the navigation for left navigation links in credit gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    When I select "<new_link>" link
    Then I should reach on the "<target>" page
    Examples:
      | new_link                       | target                               |
      | goto_my_credit_card            | /my-credit/gateway                   |
      | goto_cardholder_benefits       | /ce/creditservice/marketing/benefits |
      | goto_apply_and_learn_more      | /ce/creditservice/marketing/         |
      | goto_furniture_mattress_status | /account/furniture                   |
      | goto_my_wallet_link            | /account/signin                      |
      | goto_my_profile                | /account/profile                     |
      | goto_my_preferences_link       | /account/signin                      |
      | goto_my_address_book_link      | /account/signin                      |
      | goto_my_wish_list              | /wishlist/mylist                     |
      | goto_my_plenti                 | /account/signin                      |
      | goto_gift_card_balance         | /account/giftcardbalance             |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verify the Sign-in page fields on Redesigned gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    Then I should see below information:
      | credit_card_headline         |
      | sign_in_create_profile_box   |
      | sign_in_button               |
      | create_profile_button        |
      | other_ways_to_pay_section    |
      | goto_learn_more_link         |
      | goto_exclusive_benefits_link |
      | apply_now_button             |
      | activate_card                |
      | Legal_link                   |
      | amex_card_disclaimer_text    |
      | faq_link                     |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario Outline: Verify the navigations for Sign In fields on Redesigned gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "<Field>"
    Then I should reach on the "<Target>" page
    Examples:
      | Field                 | Target                           |
      | sign_in_button        | /account/signin                  |
      | create_profile_button | /account/profile                 |
      | apply_now_button      | /CRS/acq/launch/                 |
      | goto_learn_more_link  | /ce/creditservice/marketing/main |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verify Find Out More link functionality in Redesigned guest gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verify AmexCard disclaimer text in Redesigned guest gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    Then I should see AmexCard disclaimer text

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verify other ways to pay overlay display for guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I should see below information:
      | other_ways_to_pay_content |
      | close_other_ways_to_pay   |
      | find_store_button         |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verify find store button on other ways to pay overlay for guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "find_store_button"
    Then I should reach on the "shop/store/search" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verify close icon on other ways to pay overlay for guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "close_other_ways_to_pay"
    Then I should not see other ways to pay overlay

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario: Verification of Credit Agreements & Privacy Notice link and it's expansion in Credit Gateway Page as a guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    Then I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol
    When I tap on "expand_credit_agreement_and_privacy_notice"
    Then I should see below links under Credit Agreements & Privacy Notice link
      | link_text                              | credit_link              |
      | Macy's Credit Card Agreement           | credit_card_agreement    |
      | Macy's American Express Card Agreement | amex_card_agreement      |
      | Privacy Notice                         | goto_privacy_policy_link |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17T
  Scenario Outline: To verify if all the links of Credit Agreements & Privacy Notice as a guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "expand_credit_agreement_and_privacy_notice"
    And I tap on "<link>"
    Then I should reach on the "<target>" page
    Examples:
      | link                     | target                                                             |
      | credit_card_agreement    | dyn_img/banners/Macys_Credit_Card_Agreement.pdf                    |
      | amex_card_agreement      | dyn_img/banners/Macys_American_Express_Card_Agreement.pdf          |
      | goto_privacy_policy_link | dyn_img/banners/Department_Stores_National_Bank_Privacy_Notice.pdf |