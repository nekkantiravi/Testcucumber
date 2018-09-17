###########################################################################
          # Story: B-90756 ::SDT:UI::Desktop::BCOM::Comp-Credit Gateway Redesign:: Left navigation
          # Story: B-90742 ::SDT: Desktop : BCOM Automation for No card view
          # Author: QE Team
          ############################################################################

Feature: As a user visiting bloomingdales.com, I want to see a link to the Credit Gateway, Learn More & Apply, and Cardholder Benefits pages in the left navigation so that I may easily access each of these pages.

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify left navigation in credit gateway page for registered user without any Card added to profile
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I should see below credit card links in Responsive page left navigation:
      | link_text                     | credit_link                   |
      | My Bloomingdale's Credit Card | goto_my_credit_card           |
      | Wallet                        | goto_my_wallet_link           |
      | Profile                       | goto_my_profile               |
      | Preferences                   | goto_my_preferences_link      |
      | Address Book                  | goto_my_address_book_link     |
      | My Wish List                  | goto_my_wish_list             |
      | Points                        | goto_my_points                |
      | My Reward Card Balance        | goto_reward_card_balance      |
      | Bonus Offers                  | goto_bonus_offers             |
      | My Perks                      | goto_my_perks                 |
      | FAQ                           | goto_faq                      |
      | Order Status                  | goto_order_status_and_history |
      | Cardholder Benefits           | goto_cardholder_benefits      |
      | Learn More & Apply            | goto_apply_and_learn_more     |


  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: Verify the left navigation links functionality in gateway page
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    When I select "<new_link>" link
    Then I should reach on the "<target>" page
    Examples:
      | new_link                      | target                            |
      | goto_my_credit_card           | /my-credit/gateway                |
      | goto_my_wallet_link           | /account/wallet                   |
      | goto_my_profile               | /account/profile                  |
      | goto_my_preferences_link      | /account/preferences              |
      | goto_my_address_book_link     | /account/addressbook              |
      | goto_my_wish_list             | /wishlist/mylist                  |
      | goto_my_points                | /loyallist/accountassociation     |
      | goto_reward_card_balance      | /loyallist/accountassociation     |
      | goto_bonus_offers             | /shop/coupons-sales-promotions    |
      | goto_my_perks                 | /loyallist/benefits/              |
      | goto_faq                      | /app/answers/detail/a_id/615      |
      | goto_order_status_and_history | /service/order-status             |
      | goto_cardholder_benefits      | /creditservice/marketing/benefits |
      | goto_apply_and_learn_more     | /creditservice/marketing/main     |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
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
      | phone_service             |
      | chat_service              |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
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

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI in other ways to pay box in gateway page for signed in user for MCOM & BCOM
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    Then I should see below text in "other_ways_to_pay_content" section:
      | Don't want to pay your bill online?                 |
      | You can pay your bill in store or by mail or phone. |
      | More Details                                        |
    And I select "more_details" link in gateway sign page
    Then I should see "other_ways_to_pay_overlay" on gateway page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI elements in apply now section
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    Then I should see below text in "apply_now_box" section:
      | Learn More      |
      | Info/Exclusions |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI for learn more link for signed in user
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    And I select "learn_more" link in gateway sign page
    Then I should reach on the "/creditservice/marketing/main" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI for Exclusions and Details link for signed in user
    Given I visit the web site as a registered user
    When  I navigate to credit card page from footer
    And I select "info_exclusions" link in gateway sign page
    Then I should see "info_exclusion_overlay" on gateway page


  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify find store button on other ways to pay overlay when user not having card
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page
    And  I tap on "find_store_button"
    Then I should reach on the "/store-locator" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify close icon on other ways to pay overlay when user not having card
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page
    And  I tap on "close_other_ways_to_pay"
    Then I should not see below elements in credit service gateway page:
      |  other_ways_to_pay_overlay  |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verification of Credit Agreements & Privacy Notice link and it's expansion in Credit Gateway Page as a registered user
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    Then I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol
    When I tap on "expand_credit_agreement_and_privacy_notice"
    Then I should see below links under Credit Agreements & Privacy Notice link
      | link_text                                      | credit_link              |
      | Bloomingdale's Credit Card Agreement           | credit_card_agreement    |
      | Bloomingdale's American Express Card Agreement | amex_card_agreement      |
      | Privacy Notice                                 | goto_privacy_policy_link |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: To verify if all the links of Credit Agreements & Privacy Notice as a registered user
    Given I visit the web site as a registered user
    When I navigate to credit card page from footer
    And I tap on "expand_credit_agreement_and_privacy_notice"
    And I tap on "<link>"
    Then I should reach on the "<target>" page
    Examples:
      | link                     | target                                                              |
      | credit_card_agreement    | dyn_img/site_ads/Bloomingdales_Credit_Card_Agreement.pdf            |
      | amex_card_agreement      | dyn_img/site_ads/Bloomingdales_American_Express_Card_Agreement.pdf  |
      | goto_privacy_policy_link | dyn_img/site_ads/Department_Stores_National_Bank_Privacy_Notice.pdf |