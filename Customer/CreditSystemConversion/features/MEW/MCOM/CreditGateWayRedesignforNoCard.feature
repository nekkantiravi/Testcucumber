#####################################################################################
# Story             : B-76955 : BUS::UI::MEW::MCOM::Comp Story-Credit Gateway Redesign::No Card
# Author            : Credit Systems Responsive Pages :: QE Team
#####################################################################################

Feature: Credit Service Gateway Responsive Page for signed in user
  As a signed in customer on the credit gateway page, I need to see information about Other Ways to Pay, Activation,
  and Apply, so I can take the necessary actions.

#  Kill switch - creditResponsiveGatewayEnabled should be true

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI of gateway page along with fields/content for signed in user without card
    Given I visit the mobile web site as a registered user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should reach on the "/my-credit/gateway" page
    Then I should see below elements in credit service gateway page:
      | credit_card_headline      |
      | add_card_section          |
      | other_ways_to_pay_content |
      | apply_now_box             |
      | activation_box            |
      | marketing_banner          |
      | agreement_privacy_notice  |
      | amex_card_disclaimer_text |
      | credit_card_faq           |
      | contact_us                |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: : Verify Add Card, Activate Card\Apply Now button functionality on gateway page for Signed in user without card
    Given I visit the mobile web site as a registered user without add CC
    When  I navigate to credit service gateway page on MEW
    And I select "<citi_page>" link in gateway sign page
    Then I should reach on the "<landing_page>" page
    Examples:
      | citi_page        | landing_page                |
      | add_card_button  | /RSnextgen/svc/launch/      |
      | activate_card    | /RSnextgen/svc/registration |
      | apply_now_button | /CRS/acq/launch/            |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI in other ways to pay box in gateway page for signed in user for MCOM & BCOM
    Given I visit the mobile web site as a registered user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should see below text in "other_ways_to_pay_content" section:
      | Don't want to pay your bill online?          |
      | You can also pay in store, by mail or phone. |
      | Find Out More                                |
    And I select "Find_Out_More_link" link in gateway sign page
    Then I should see "other_ways_to_pay_overlay" on gateway page


  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI elements in apply now section
    Given I visit the mobile web site as a registered user without add CC
    When  I navigate to credit service gateway page on MEW
    Then I should see below text in "apply_now_box" section:
      | Learn More           |
      | Exclusions & Details |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI for learn more link for signed in user
    Given I visit the mobile web site as a registered user without add CC
    When  I navigate to credit service gateway page on MEW
    And I select "learn_more" link in gateway sign page
    Then I should reach on the "/ce/creditservice/marketing/main" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the UI for Exclusions and Details link for signed in user
    Given I visit the mobile web site as a registered user without add CC
    When  I navigate to credit service gateway page on MEW
    And I select "exclusions_and_details" link in gateway sign page
    Then I should reach on the "/cms/slp/3/060914strrwrdscrdt20excl" page
#
##### Other ways to pay link is covered in story B-78313
  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify other ways to pay overlay display when user not having card
    Given I visit the mobile web site as a registered user without add CC
    When I navigate to credit service gateway page on MEW
    And I tap on "other_ways_to_pay_link"
    Then I should see other ways to pay overlay
    And  I should see below information:
      | other_ways_to_pay_content |
      | close_other_ways_to_pay   |
      | find_store_button         |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify find store button on other ways to pay overlay when user not having card
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | Macy's Credit Card |
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "find_store_button"
    Then  I should reach on the "shop/store/search" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify close icon on other ways to pay overlay when user not having card
    Given I visit the mobile web site as a registered user
    When I navigate to credit service gateway page on MEW
    And I tap on "Find_Out_More_link"
    Then I should see other ways to pay overlay
    And  I tap on "close_other_ways_to_pay"
    Then I should not see other ways to pay overlay

##### Macys card agreements are covered in story B-78315
  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verification of Credit Agreements & Privacy Notice link and it's expansion in Credit Gateway Page as a registered user
    Given I visit the mobile web site as a registered user
    When I navigate to credit service gateway page on MEW
    Then I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol
    When I tap on "expand_credit_agreement_and_privacy_notice"
    Then I should see below links under Credit Agreements & Privacy Notice link
      | link_text                              | credit_link              |
      | Macy's Credit Card Agreement           | credit_card_agreement    |
      | Macy's American Express Card Agreement | amex_card_agreement      |
      | Privacy Notice                         | goto_privacy_policy_link |


  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: To verify if all the links of Credit Agreements & Privacy Notice as a registered user
    Given I visit the mobile web site as a registered user
    When I navigate to credit service gateway page on MEW
    And I tap on "expand_credit_agreement_and_privacy_notice"
    And I tap on "<link>"
    Then I should reach on the "<target>" page
    Examples:
      | link                     | target                                                                                                                              |
      | credit_card_agreement    | dyn_img/banners/Macys_Credit_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-capn-_-macys_credit_card_agreement         |
      | amex_card_agreement      | dyn_img/banners/Macys_American_Express_Card_Agreement.pdf?cm_sp=creditcardlanding_credit_gateway-_-capn-_-macys_amex_card_agreement |
      | goto_privacy_policy_link | dyn_img/banners/Department_Stores_National_Bank_Privacy_Notice.pdf?cm_sp=creditcardlanding_credit_gateway-_-capn-_-privacy_notice   |
