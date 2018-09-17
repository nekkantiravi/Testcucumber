###################################################################################################
#Story:: B-64484: QE:: T-C::BUS::UI::Desktop::MCOM::Responsive Learn & Apply::Test new HTTP page
#        B-79876: Desktop::M/BCOM::SDT Migration::Responsive Learn More and Apply
#Author:: QE Team
###################################################################################################

Feature: Testing new responsive Learn More & Apply page.

  "Customers are able to see information on macys.com desktop about credit card options, and can click
  an apply link to be directed to Fusion to complete a credit card application. The existing page will
  be rebuilt as responsive, allowing it to be displayed on any device (desktop, tablet, mobile)."

##Pre-condition:
#respLearnAndApplyEnabled = True

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
      | new_account_banner                             |
      | apply_now_1                                    |
      | macys_star_rewards                             |
      | macys_star_pass                                |
      | thanks_for_sharing                             |
      | plenti                                         |
      | american_express                               |
      | apply_now_2                                    |
      | macys_credit_card_faqs                         |
      | department_stores_national_bank_privacy_notice |
      | macys_credit_card_agreement                    |
      | macys_american_express_card_agreement          |

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario: Verifying left nav options for guest user of responsive Learn More & Apply page.
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    And I should see below credit card links in Responsive page left navigation:
      | link_text             | credit_link               |
      | Macy's Credit Card    | macys_credit_card_sub     |
      | My Macy's Credit Card | goto_my_credit_card       |
      | Cardholder Benefits   | goto_cardholder_benefits  |
      | Learn More & Apply    | goto_apply_and_learn_more |

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario: Verifying left nav options for signed in user of responsive Learn More & Apply page.
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    And I should see below credit card links in Responsive page left navigation:
      | link_text                   | credit_link                    |
      | My Profile                  | goto_my_profile                |
      | My Preferences              | goto_my_preferences_link       |
      | My Address Book             | goto_my_address_book_link      |
      | My Wallet                   | goto_my_wallet_link            |
      | My Plenti                   | goto_my_plenti                 |
      | My Lists                    | goto_my_wish_list              |
      | My Order History            | goto_order_status              |
      | Furniture & Mattress Status | goto_furniture_mattress_status |
      | MACY'S CREDIT CARD          | macys_credit_card_sub          |
      | My Macy's Credit Card       | goto_my_credit_card            |
      | Cardholder Benefits         | goto_cardholder_benefits       |
      | Learn More & Apply          | goto_apply_and_learn_more      |
      | Gift Card Balance           | goto_gift_card_balance         |


  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario Outline: Verify the functioning of responsive Learn More & Apply page left nav element as a signed in user.
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    And I select "<new_link>" link on apply and learn more page
    Then I should reach on the "<target>" page

    Examples:
      | new_link                       | target                            |
      | goto_my_account                | /account/myaccount                |
      | goto_my_profile                | /account/profile                  |
      | goto_my_preferences_link       | /account/preferences              |
      | goto_my_address_book_link      | /account/addressbook              |
      | goto_my_wallet_link            | /account/wallet?ocwallet=true     |
      | goto_my_plenti                 | /loyalty/enroll                   |
      | goto_my_wish_list              | /wishlist/mylist                  |
      | goto_order_status              | /service/order-status             |
      | goto_furniture_mattress_status | /account/furniture                |
      | goto_my_credit_card            | /creditservice/gateway            |
      | goto_cardholder_benefits       | /creditservice/marketing/benefits |
      | goto_gift_card_balance         | /account/giftcardbalance          |


  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario Outline: Verify the functioning of responsive Learn More & Apply page element.
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    When I select "<new_link>" link on apply and learn more page
    Then I should reach on the "<target>" page

    Examples:
      | new_link                                       | target                                                              |
      | find_out_more                                  | /ce/splash/thanks-for-sharing/index                                 |
      | learn_more                                     | /ce/splash/plenti/welcome                                           |
      | macys_credit_card_faqs                         | /app/answers/list/c/5                                               |
      | department_stores_national_bank_privacy_notice | /dyn_img/banners/Department_Stores_National_Bank_Privacy_Notice.pdf |
      | macys_credit_card_agreement                    | /dyn_img/banners/Macys_Credit_Card_Agreement.pdf                    |
      | macys_american_express_card_agreement          | /dyn_img/banners/Macys_American_Express_Card_Agreement.pdf          |

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario Outline: Verify the functioning of Apply now links on responsive Learn More & Apply page for guest user.
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    When I select "<apply_link>" link on apply and learn more page
    Then I should redirect to "apply now" page

    Examples:
      | apply_link  |
      | apply_now_1 |
      | apply_now_2 |

  @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario Outline: Verify the functioning of Apply now links on responsive Learn More & Apply page for registered user.
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    When I select "<apply_link>" link on apply and learn more page
    Then I should redirect to "apply now" page

    Examples:
      | apply_link  |
      | apply_now_1 |
      | apply_now_2 |

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario: Verify the landing page as credit gateway when i return from macys apply now page
    Given I visit the web site as a guest user
    And I reach to "Learn More & Apply now" page
    When I select Apply_now on apply and learn more page
    Then I should redirect to "<Apply_now>" page
    When I return from Apply now fusion page to macys page
    Then I should reach "credit gate way" page

  @wip @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario Outline: Verify the functioning of Exclusion & Details link on responsive Learn More & Apply page for guest user.
    Given I visit the web site as a guest user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    When I select "<exclusion_link>" link on apply and learn more page
    Then I should reach on the "<target>" page

    Examples:
      | exclusion_link          | target                              |
      | exclusion_and_details_1 | /cms/slp/3/080116TFSApplyExclusions |
      | exclusion_and_details_2 | /cms/slp/3/080116TFSApplyExclusions |
      | exclusion_and_details_3 | /cms/slp/3/080116TFSApplyExclusions |
      | exclusion_and_details_4 | /cms/slp/3/080116TFSApplyExclusions |


  @wip @domain_customer @project_credit_service @artifact_shopapp @release_16R
  Scenario Outline: Verify the functioning of Exclusion & Details link on responsive Learn More & Apply page for registered user.
    Given I visit the web site as a registered user
    When I navigate to the "Apply & Learn More" page from footer
    Then I should be redirected to "credit_service_marketing" page
    When I select "<exclusion_link>" link on apply and learn more page
    Then I should reach on the "<target>" page

    Examples:
      | exclusion_link          | target                              |
      | exclusion_and_details_1 | /cms/slp/3/080116TFSApplyExclusions |
      | exclusion_and_details_2 | /cms/slp/3/080116TFSApplyExclusions |
      | exclusion_and_details_3 | /cms/slp/3/080116TFSApplyExclusions |
      | exclusion_and_details_4 | /cms/slp/3/080116TFSApplyExclusions |