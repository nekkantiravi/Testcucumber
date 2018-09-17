#####################################################################################
# Story             : B-76961 : BUS::UI::MEW::MCOM::Comp Story-Credit Gateway Redesign::Sign-in Page
# Author            : Credit Systems Conversion Carryover :: QE Team
#Pre Condition:creditResponsiveGatewayEnabled killswitch should be True
#####################################################################################

Feature: As an online credit customer, I would like to be prompted to sign in so I can manage my credit card online

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify Redesigned gateway Sign-in page fields functionality
    Given I visit the web site as a guest user
    When I navigate to credit service gateway page on MEW
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

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: Verify Redesigned Sign-in page functionality
    Given I visit the web site as a guest user
    When I navigate to credit service gateway page on MEW
    And I tap on "<Field>"
    Then I should reach on the "<Target>" page
    Examples:
      | Field                 | Target                           |
      | sign_in_button        | /account/signin                  |
      | create_profile_button | /account/profile                 |
      | apply_now_button      | /CRS/acq/launch/                 |
      | goto_learn_more_link  | /ce/creditservice/marketing/main |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify Find Out More link functionality in Redesigned guest gateway page
    Given I visit the web site as a guest user
    When I navigate the global navigation menu as follows:
      | Macy's Credit Card |
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify AmexCard disclaimer text in Redesigned guest gateway page
    Given I visit the web site as a guest user
    When I navigate to credit service gateway page on MEW
    Then I should see AmexCard disclaimer text

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify other ways to pay overlay display for guest user
    Given I visit the web site as a guest user
    When I navigate to credit service gateway page on MEW
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I should see below information:
      | other_ways_to_pay_content |
      | close_other_ways_to_pay   |
      | find_store_button         |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify find store button on other ways to pay overlay for guest user
    Given I visit the web site as a guest user
    When I navigate to credit service gateway page on MEW
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "find_store_button"
    Then I should reach on the "shop/store/search" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify close icon on other ways to pay overlay for guest user
    Given I visit the web site as a guest user
    When I navigate to credit service gateway page on MEW
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "close_other_ways_to_pay"
    Then I should not see other ways to pay overlay

##### Macys card agreements are covered in story B-78315 ###############################
  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verification of Credit Agreements & Privacy Notice link and it's expansion in Credit Gateway Page as a guest user
    Given I visit the mobile web site as a guest user
    When I navigate to credit service gateway page on MEW
    Then I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol
    When I tap on "expand_credit_agreement_and_privacy_notice"
    Then I should see below links under Credit Agreements & Privacy Notice link
      | link_text                              | credit_link              |
      | Macy's Credit Card Agreement           | credit_card_agreement    |
      | Macy's American Express Card Agreement | amex_card_agreement      |
      | Privacy Notice                         | goto_privacy_policy_link |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: To verify if all the links of Credit Agreements & Privacy Notice as a guest user
    Given I visit the mobile web site as a guest user
    When I navigate to credit service gateway page on MEW
    And I tap on "expand_credit_agreement_and_privacy_notice"
    And I tap on "<link>"
    Then I should reach on the "<target>" page
    Examples:
      | link                     | target                                                                                                                              |
      | credit_card_agreement    | dyn_img/banners/Macys_Credit_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-capn-_-macys_credit_card_agreement         |
      | amex_card_agreement      | dyn_img/banners/Macys_American_Express_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-capn-_-macys_amex_card_agreement |
      | goto_privacy_policy_link | dyn_img/banners/Department_Stores_National_Bank_Privacy_Notice.pdf?cm_sp=creditcardlanding_credit_gateway-_-capn-_-privacy_notice   |