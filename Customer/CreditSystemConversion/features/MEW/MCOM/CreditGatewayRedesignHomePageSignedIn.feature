#####################################################################################
# Author : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As an online credit customer, I would like to access my non dual line credit card(s) on the mobile version of the credit gateway page.

# Pre-Requisite:1.Customer is signed in and has one or multiple credit card accounts added to their account.
#				2.Customer has navigated to the Credit Gateway Page on MEW.
#				3.Feature will be Wrapped under Kill Switch: creditResponsiveGatewayEnabled=true
# Story NO: B-77788 : CM4:: BUS::UI::MEW::MCOM::Comp-Credit Gateway Redesign::Home Page-Signed in
# Story NO: B-84609 : SDT: MCOM MEW Automation for Signed IN Single / Multi Card view

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: Verify the elements display & its navigation on gateway responsive page for Signed User having Single prop card
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I click "<element>" on credit gateway page
    Then I should redirect to "<fusion_page>" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | element                    | fusion_page          | expected_process_indicator |
      | make_a_payment             | citi_fusion          | makepaymentoptions         |
      | manage_auto_pay            | citi_fusion          | autopay                    |
      | statements_recent_activity | citi_fusion          | statements                 |
      | add_an_additional_card     | fusion_add_card      | linkadditionalcards        |
      | activate_card              | fusion_activate_card | verify                     |

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: Verify the elements display & its navigation on gateway responsive page for Signed User having dual line card
    Given I sign to mobile website with user having "dual_amex_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I click "<element>" on credit gateway page
    Then I should redirect to "<fusion_page>" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | element                    | fusion_page          | expected_process_indicator |
      | make_a_payment             | citi_fusion          | makepaymentoptions         |
      | manage_auto_pay            | citi_fusion          | autopay                    |
      | statements_recent_activity | citi_fusion          | statements                 |
      | add_an_additional_card     | fusion_add_card      | linkadditionalcards        |
      | activate_card              | fusion_activate_card | verify                     |

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: Verify the elements display & its navigation on gateway responsive page for Signed User having single amex card
    Given I sign to mobile website with user having "single_line_amex_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I click "<element>" on credit gateway page
    Then I should redirect to "<fusion_page>" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | element                    | fusion_page          | expected_process_indicator |
      | make_a_payment             | citi_fusion          | makepaymentoptions         |
      | manage_auto_pay            | citi_fusion          | autopay                    |
      | statements_recent_activity | citi_fusion          | statements                 |
      | add_an_additional_card     | fusion_add_card      | linkadditionalcards        |
      | activate_card              | fusion_activate_card | verify                     |

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the display of Carousel,Marketing Banner,Other ways to pay section,Card Agreements & Manage Credit Card section on responsive gateway page for Signed User having Single or Multiple cards.
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below sections on gateway page
      | marketing_banner                 |
      | other_ways_to_pay_link           |
      | credit_agreements_privacy_notice |

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the display of notice on responsive gateway page when user having card
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see notice text when user having card

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the display of American Express disclosure text on responsive gateway page when user having card
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see AmexCard disclaimer text

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the display of Macys Credit Card FAQ's functionality on responsive gateway page for Signed User having Single or Multiple cards.
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I tap on "credit_card_faq"
    Then I should reach on the "/app/answers/list" page

  @domain_customer @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify the display of service section display when user having card in profile
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below sections on gateway page
      |credit_card_faq            |
      |phone_service              |
      |chat_service               |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify other ways to pay overlay display when user having card in profile
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I tap on "other_ways_to_pay_link"
    Then I should see other ways to pay overlay
    And  I should see below information:
      | other_ways_to_pay_content |
      | close_other_ways_to_pay   |
      | find_store_button         |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify find store button on other ways to pay overlay when user having card in profile
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I tap on "other_ways_to_pay_link"
    Then I should see other ways to pay overlay
    And  I tap on "find_store_button"
    Then I should reach on the "shop/store/search" page

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify close icon on other ways to pay overlay when user having card in profile
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I tap on "other_ways_to_pay_link"
    Then I should see other ways to pay overlay
    And  I tap on "close_other_ways_to_pay"
    Then I should not see other ways to pay overlay

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify display of deep links in quick tools section for primary user on credit gateway page
    Given I sign to mobile website with user having "single_line_amex_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below elements in credit service gateway page:
      | manage_automatic_payments     |
      | update_credit_profile         |
      | manage_credit_alerts          |
      | paperless_settings            |
      | order_replacement_card        |
      | report_lost_or_stolen_card    |
      | dispute_transaction           |
      | request_credit_limit_increase |
      | add_an_authorized_user        |
      | edit_or_remove_card           |
      | add_an_additional_card        |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify suppression of links in quick tools section for Authorized user on credit gateway page
    Given I sign to mobile website with user having "authorized_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "authorized_indicator" on card image
    And I should not see below elements in credit service gateway page:
      | order_replacement_card        |
      | request_credit_limit_increase |
      | manage_automatic_payments     |
      | add_an_authorized_user        |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario Outline: Verify functioning of credit submenu links on gateway page for profile having card in profile
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    And I tap on "<quick_tool_link>"
    Then I should redirect to "citi_fusion" page
    Examples:
      | quick_tool_link               |
      | manage_automatic_payments     |
      | update_credit_profile         |
      | manage_credit_alerts          |
      | paperless_settings            |
      | order_replacement_card        |
      | report_lost_or_stolen_card    |
      | dispute_transaction           |
      | request_credit_limit_increase |
      | add_an_authorized_user        |
      | edit_or_remove_card           |
      | add_an_additional_card        |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify inline credit summary is displayed on gateway page for profile having one card
    Given I sign to mobile website with user having "new_prop_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below fields along with price on credit gateway page
      | current_balance_value        |
      | minimum_payment_due_value    |
      | payment_due_date_value       |
      | available_credit_value       |
      | credit_limit_value           |
      | last_statement_balance_value |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify inline credit summary is displayed on gateway page for profile having dual card
    Given I sign to mobile website with user having "dual_amex_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below fields along with price on credit gateway page
      | current_balance_value        |
      | minimum_payment_due_value    |
      | payment_due_date_value       |
      | available_credit_value       |
      | credit_limit_value           |
      | last_statement_balance_value |
    When I tap on "out_store_account"
    Then I should see below fields along with price on credit gateway page
      | current_balance_value        |
      | minimum_payment_due_value    |
      | payment_due_date_value       |
      | available_credit_value       |
      | credit_limit_value           |
      | last_statement_balance_value |

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify right carousel functionality on responsive gateway page
    Given I sign to mobile website with user having "multi_card_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below elements in credit service gateway page:
      | right_carousel  |
      | left_carousel   |
      | wallet_section  |
      | carousel_circle |
    And I should see next card using right carousel

  @domain_customer @artifact_shopapp @project_responsive_gateway @use_regression @release_17O
  Scenario: Verify left carousel functionality on responsive gateway page
    Given I sign to mobile website with user having "multi_card_user" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see below elements in credit service gateway page:
      | right_carousel  |
      | left_carousel   |
      | wallet_section  |
      | carousel_circle |
    And I should see previous card using left carousel





