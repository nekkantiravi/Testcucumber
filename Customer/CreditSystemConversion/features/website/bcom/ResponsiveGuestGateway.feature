##########################################################################
# Story: B-90756 :: SDT:UI::Desktop::BCOM::Comp-Credit Gateway Redesign:: Left navigation
#        B-90740 :: SDT: Desktop : BCOM Automation for Guest view
# Author: QE Team
############################################################################

Feature: As a user visiting bloomingdales.com, I want to see a link to the Credit Gateway, Learn More & Apply, and Cardholder Benefits pages in the left navigation so that I may easily access each of these pages.

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify the left navigation link names in credit gateway page for Guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I should see below credit card links in Responsive page left navigation:
      | link_text                      | credit_link                    |
      | My Profile                     | goto_my_profile                |
      | My Preferences                 | goto_my_preferences_link       |
      | My Address Book                | goto_my_address_book_link      |
      | My bWallet                     | goto_my_wallet_link            |
      | My Wish List                   | goto_my_wish_list              |
      | My Points                      | goto_my_points                 |
      | My Reward Card Balance         | goto_reward_card_balance       |
      | Bonus Offers                   | goto_bonus_offers              |
      | My Perks                       | goto_my_perks                  |
      | FAQs                           | goto_faq                       |
      | My Order Status & History      | goto_order_status_and_history  |
      | My Bloomingdale's Credit Card  | goto_my_credit_card            |
      | Cardholder Benefits            | goto_cardholder_benefits       |
      | Learn More & Apply             | goto_apply_and_learn_more      |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario Outline: Verify the navigation for left navigation links in credit gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    When I select "<new_link>" link
    Then I should reach on the "<target>" page
    Examples:
      | new_link                          | target                            |
      | goto_my_profile                   | /account/profile                  |
      | goto_my_preferences_link          | /account/signin                   |
      | goto_my_address_book_link         | /account/signin                   |
      | goto_my_wallet_link               | /account/signin                   |
      | goto_my_wish_list                 | /wishlist/mylist                  |
      | goto_my_points                    | /account/signin                   |
      | goto_reward_card_balance          | /account/signin                   |
      | goto_bonus_offers                 | /shop/coupons-sales-promotions    |
      | goto_my_perks                     | /loyallist/benefits/              |
      | goto_faq                          | /app/answers/detail/a_id/615      |
      | goto_order_status_and_history     | /service/order-status             |
      | goto_my_credit_card               | /my-credit/gateway/guest          |
      | goto_cardholder_benefits          | /creditservice/marketing/benefits |
      | goto_apply_and_learn_more         | /creditservice/marketing/main     |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
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
      | legal_link                   |
      | amex_card_disclaimer_text    |
      | faq_link                     |
      | phone_service                |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
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
      | goto_learn_more_link  | /creditservice/marketing/main    |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify More Details link functionality in Redesigned guest gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    Then I should see below text in "other_ways_to_pay_content" section:
      | Don't want to pay your bill online?                 |
      | You can pay your bill in store or by mail or phone. |
      | More Details                                        |
    And I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify AmexCard disclaimer text in Redesigned guest gateway page
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    Then I should see AmexCard disclaimer text

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify find store button on other ways to pay overlay for guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page
    And  I tap on "find_store_button"
    Then I should reach on the "/store-locator" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify close icon on other ways to pay overlay for guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page
    And  I tap on "close_other_ways_to_pay"
    Then I should not see below elements in credit service gateway page:
      |  other_ways_to_pay_overlay  |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verification of Credit Agreements & Privacy Notice link and it's expansion in Credit Gateway Page as a guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    Then I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol
    When I tap on "expand_credit_agreement_and_privacy_notice"
    Then I should see below links under Credit Agreements & Privacy Notice link
      | link_text                                      | credit_link              |
      | Bloomingdale's Credit Card Agreement           | credit_card_agreement    |
      | Bloomingdale's American Express Card Agreement | amex_card_agreement      |
      | Privacy Notice                                 | goto_privacy_policy_link |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario Outline: To verify if all the links of Credit Agreements & Privacy Notice as a guest user
    Given I visit the web site as a guest user
    When I navigate to credit card page from footer
    And I tap on "expand_credit_agreement_and_privacy_notice"
    And I tap on "<link>"
    Then I should reach on the "<target>" page
    Examples:
      | link                     | target                                                              |
      | credit_card_agreement    | dyn_img/site_ads/Bloomingdales_Credit_Card_Agreement.pdf            |
      | amex_card_agreement      | dyn_img/site_ads/Bloomingdales_American_Express_Card_Agreement.pdf  |
      | goto_privacy_policy_link | dyn_img/site_ads/Department_Stores_National_Bank_Privacy_Notice.pdf |