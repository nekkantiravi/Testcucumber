#####################################################################################
# Story             : B-57914 :: QE: T-C::BUS::UI::Desktop::BCOM::Learn & Apply::Update page with final comps
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################



Feature: As a customer, I would like to see information about the Bloomingdale's Credit Cards, and be able to apply online
  for a card

  ##Pre-condition:
  #  kill switch: respLearnAndApplyEnabled = True

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario: Verifying responsive Learn More & Apply page element for guest user.
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario: Verifying responsive Learn More & Apply page element for registered user.
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    And I should see below fields on Learn More & Apply page
      | apply_now_top                |
      | info_exclusions_top_banner   |
      | info_exclusions_six_points   |
      | info_exclusions_three_points |
      | info_exclusions_even_more    |
      | info_exclusions_shipping     |
      | info_exclusions_rewards      |
      | apply_now_bottom             |
      | cc_faqs                      |
      | bank_privacy_notice          |
      | credit_card_agreement        |
      | amex_card_agreement          |

  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on info&exclusion links as a registered user
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    When I select "<exclusion_links>" link on apply and learn more page
    Then I should see disclosure copy of "<exclusion_links>" on Learn more & apply page
  Examples:
      | exclusion_links              |
      | info_exclusions_top_banner   |
      | info_exclusions_six_points   |
      | info_exclusions_three_points |
      | info_exclusions_even_more    |
      | info_exclusions_shipping     |
      | info_exclusions_rewards      |

  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on info&exclusion links as a guest user
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    When I select "<exclusion_links>" link on apply and learn more page
    Then I should see disclosure copy of "<exclusion_links>" on Learn more & apply page
  Examples:
      | exclusion_links              |
      | info_exclusions_top_banner   |
      | info_exclusions_six_points   |
      | info_exclusions_three_points |
      | info_exclusions_even_more    |
      | info_exclusions_shipping     |
      | info_exclusions_rewards      |

  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on apply now links as a registered user
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    When I select "<apply_link>" link on apply and learn more page
    Then I should redirect to "apply now" page

    Examples:
      | apply_link       |
      | apply_now_top    |
      | apply_now_bottom |

  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on apply now links as a guest user
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    When I select "<apply_link>" link on apply and learn more page
    Then I should redirect to "apply now" page

    Examples:
      | apply_link       |
      | apply_now_top    |
      | apply_now_bottom |


  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on information links as a registered user
    Given I visit the web site as a registered user
    And I navigate to the "Apply & Learn More" page from footer
    When I select "<new_link>" link on apply and learn more page
    Then I should reach on the "<target>" page
    Examples:
      | new_link              | target                                                                                                                                  |
      | cc_faqs               | /app/answers/list/session/L3RpbWUvMTMxMDY4MjI1MC9zaWQvSXpFekoteWs%3D/c/24                                                               |
      | credit_card_agreement | /dyn_img/site_ads/Bloomingdales_Credit_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-benefits-_-cc_aggreement             |
      | bank_privacy_notice   | /dyn_img/site_ads/Department_Stores_National_Bank_Privacy_Notice.pdf?cm_sp=creditcardlanding_credit_gateway-_-benefits-_-privacy_notice |
      | amex_card_agreement   | /dyn_img/site_ads/Bloomingdales_American_Express_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-benefits-_-amex_agreement  |

  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on information links as a guest user
    Given I visit the web site as a guest user
    And I navigate to the "Apply & Learn More" page from footer
    When I select "<new_link>" link on apply and learn more page
    Then I should reach on the "<target>" page
    Examples:
      | new_link              | target                                                                                                                                  |
      | cc_faqs               | /app/answers/list/session/L3RpbWUvMTMxMDY4MjI1MC9zaWQvSXpFekoteWs%3D/c/24                                                               |
      | credit_card_agreement | /dyn_img/site_ads/Bloomingdales_Credit_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-benefits-_-cc_aggreement             |
      | bank_privacy_notice   | /dyn_img/site_ads/Department_Stores_National_Bank_Privacy_Notice.pdf?cm_sp=creditcardlanding_credit_gateway-_-benefits-_-privacy_notice |
      | amex_card_agreement   | /dyn_img/site_ads/Bloomingdales_American_Express_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-benefits-_-amex_agreement  |

    # Based on the defect D-47692, updated bcom_cc_faqs target link for both registered and guest user scenarios.

  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on left navigation links as a registered user
    Given I visit the web site as a registered user
    And I navigate to the "Apply & Learn More" page from footer
    When I select "<new_link>" link on apply and learn more page
    Then I should reach on the "<target>" page
    Examples:
      | new_link                   | target                                                                                                              |
      | goto_my_account            | /account/myaccount                                                                                                  |
      | goto_my_profile            | /account/profile?cm_sp=my_account-_-account-_-my_profile                                                            |
      | goto_my_address_book_link  | /account/addressbook?cm_sp=my_account-_-account-_-my_address_book                                                   |
      | goto_my_wallet_link        | /account/wallet?ocwallet=true                                                                                       |
      | goto_my_wish_list          | /wishlist/mylist?cm_sp=my_account-_-account-_-my_wish_list                                                          |
      | goto_my_loyallist          | /loyallist/accountassociation                                                                                       |
      | goto_reward_card_balance   | /loyallist/accountassociation?from=rewardcardsdisplay                                                               |
      | goto_bonus_offers          | /shop/coupons-sales-promotions?cm_sp=NAVIGATION-_-TOP_NAV-_-3977-CATEGORY_ICON_IMAGE-See-All&bonus-offers-link=true |
      | goto_my_perks              | /loyallist/benefits/?cm_sp=my_account-_-loyallist-_-member_benefits                                                 |
      | goto_faq                   | /app/answers/detail/a_id/615?cm_sp=my_account-_-loyallist-_-faqs                                                    |
      | goto_order_status_left_nav | /service/order-status?cm_sp=my_account-_-order_history-_-my_order_status_history                                    |
      | goto_my_credit_card        | /creditservice/gateway?cm_sp=my_account-_-credit_services-_-my_credit_card                                          |
      | goto_cardholder_benefits   | /creditservice/marketing/benefits?cm_sp=my_account-_-credit_services-_-cardholder_benefits                          |
  @domain_customer @project_credit_service @artifact_shopapp @release_16S
  Scenario Outline: Verify Learn More and Apply page when user clicks on left navigation links as a guest user
    Given I visit the web site as a guest user
    And I navigate to the "Apply & Learn More" page from footer
    When I select "<new_link>" link on apply and learn more page
    Then I should reach on the "<target>" page
    Examples:
      | new_link                   | target                                                                                     |
      | goto_my_account            | /account/signin                                                                            |
      | goto_my_profile            | /account/profile?cm_sp=my_account-_-account-_-my_profile                                   |
      | goto_my_address_book_link  | /account/signin?cm_sp=my_account-_-account-_-my_address_book                               |
      | goto_my_wallet_link        | /account/signin?cm_sp=my_account-_-account-_-my_bwallet                                    |
      | goto_my_wish_list          | /wishlist/mylist                                                                           |
      | goto_reward_card_balance   | /account/signin                                                                            |
      | goto_my_loyallist          | /account/signin                                                                            |
      | goto_bonus_offers          | /shop/coupons-sales-promotions                                                             |
      | goto_my_perks              | /loyallist/benefits                                                                        |
      | goto_order_status_left_nav | /service/order-status?cm_sp=my_account-_-order_history-_-my_order_status_history           |
      | goto_my_credit_card        | /creditservice/gateway?cm_sp=my_account-_-credit_services-_-my_credit_card                 |
      | goto_cardholder_benefits   | /creditservice/marketing/benefits?cm_sp=my_account-_-credit_services-_-cardholder_benefits |
      | goto_apply_and_learn_more  | /creditservice/marketing/main?cm_sp=my_account-_-credit_services-_-apply_learn_more        |

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario: Verifying left nav options for signed in user of responsive Learn More & Apply page.
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should see below credit card links in Responsive page left navigation:
      | link_text                     | credit_link                |
      | My Profile                    | goto_my_profile            |
      | My Address Book               | goto_my_address_book_link  |
      | My bWallet                    | goto_my_wallet_link        |
      | My Points                     | goto_my_loyallist          |
      | Bonus Offers                  | goto_bonus_offers          |
      | My Reward Card Balance        | goto_reward_card_balance   |
      | My Wish List                  | goto_my_wish_list          |
      | My Order Status & History     | goto_order_status_left_nav |
      | My Perks                      | goto_my_perks              |
      | FAQs                          | goto_faq                   |
      | My Bloomingdale's Credit Card | goto_my_credit_card        |
      | Cardholder Benefits           | goto_cardholder_benefits   |
      | Learn More & Apply            | goto_apply_and_learn_more  |

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario: Verifying left nav options for signed in user of responsive Learn More & Apply page.
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should see below credit card links in Responsive page left navigation:
      | link_text                     | credit_link                |
      | My Profile                    | goto_my_profile            |
      | My Address Book               | goto_my_address_book_link  |
      | My bWallet                    | goto_my_wallet_link        |
      | My Points                     | goto_my_loyallist          |
      | Bonus Offers                  | goto_bonus_offers          |
      | My Reward Card Balance        | goto_reward_card_balance   |
      | My Wish List                  | goto_my_wish_list          |
      | My Order Status & History     | goto_order_status_left_nav |
      | My Perks                      | goto_my_perks              |
      | FAQs                          | goto_faq                   |
      | My Bloomingdale's Credit Card | goto_my_credit_card        |
      | Cardholder Benefits           | goto_cardholder_benefits   |
      | Learn More & Apply            | goto_apply_and_learn_more  |




