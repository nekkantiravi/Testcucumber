#####################################################################################
# Story             : B-76962 : BUS::UI::MEW::BCOM::Comp Story-Credit Gateway Redesign::Sign-in Page
# Author            : Credit Systems Conversion Carryover :: QE Team
#Pre Condition:creditResponsiveGatewayEnabled killswitch should be True
#####################################################################################

Feature: As an online credit customer, I would like to be prompted to sign in so I can manage my credit card online

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify the Sign-in page fields on Redesigned gateway page
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    And I should see below information:
      | credit_card_headline         |
      | sign_in_create_profile_box   |
      | sign_in_button               |
      | create_profile_button        |
      | other_ways_to_pay_section    |
      | goto_learn_more_link         |
      | apply_now_button             |
      | activate_card                |
      | legal_link                   |
      | amex_card_disclaimer_text    |
      | faq_link                     |
      | phone_service                |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario Outline: Verify the navigations for Sign In fields on Redesigned gateway page
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    When I tap on "<Field>"
    Then I should reach on the "<Target>" page
    Examples:
      | Field                 | Target                           |
      | sign_in_button        | /account/signin                  |
      | create_profile_button | /account/profile                 |
      | apply_now_button      | /CRS/acq/launch/                 |
      | goto_learn_more_link  | /creditservice/marketing/main    |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify More Details link functionality in Redesigned guest gateway page
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    Then I should see below text in "other_ways_to_pay_content" section:
      | Don't want to pay your bill online?                 |
      | You can pay your bill in store or by mail or phone. |
      | More Details                                        |
    And I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify AmexCard disclaimer text in Redesigned guest gateway page
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    Then I should see AmexCard disclaimer text

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify find store button on other ways to pay overlay for guest user
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    And I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page
    And  I tap on "find_store_button"
    Then I should reach on the "/store-locator" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verify close icon on other ways to pay overlay for guest user
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    When I tap on "more_details"
    Then I should see "other_ways_to_pay_overlay" on gateway page
    And  I tap on "close_other_ways_to_pay"
    Then I should not see below elements in credit service gateway page:
      |  other_ways_to_pay_overlay  |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario: Verification of Credit Agreements & Privacy Notice link and it's expansion in Credit Gateway Page as a guest user
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    And I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol
    When I tap on "expand_credit_agreement_and_privacy_notice"
    Then I should see below links under Credit Agreements & Privacy Notice link
      | link_text                                      | credit_link              |
      | Bloomingdale's Credit Card Agreement           | credit_card_agreement    |
      | Bloomingdale's American Express Card Agreement | amex_card_agreement      |
      | Privacy Notice                                 | goto_privacy_policy_link |

  @domain_customer @artifact_shopapp @project_responsive_gateway @release_17O
  Scenario Outline: To verify if all the links of Credit Agreements & Privacy Notice as a guest user
    Given I visit the mobile web site as a guest user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    And I tap on "expand_credit_agreement_and_privacy_notice"
    And I tap on "<link>"
    Then I should reach on the "<target>" page
    Examples:
      | link                     | target                                                              |
      | credit_card_agreement    | dyn_img/site_ads/Bloomingdales_Credit_Card_Agreement.pdf            |
      | amex_card_agreement      | dyn_img/site_ads/Bloomingdales_American_Express_Card_Agreement.pdf  |
      | goto_privacy_policy_link | dyn_img/site_ads/Department_Stores_National_Bank_Privacy_Notice.pdf |