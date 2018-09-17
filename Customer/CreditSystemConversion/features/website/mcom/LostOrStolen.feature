#####################################################################################
# Author : Credit Systems Conversion Carryover :: QE Team
# Story             : B-60360	G2::BUS::UI::Desktop::BCOM::Deliver Message LS1 on Credit Gateway page
#					: B-60365	G2::BUS::UI::Desktop::BCOM::Deliver Message LS1 on My Account page
#					: B-60239	G2::BUS::UI::Desktop::MCOM::Deliver Message LS1 on Credit Gateway page
#					: B-60371	G2::BUS::UI::Desktop::MCOM::Deliver Message LS1 on My Account page
#					: B-53064	G3::BUS::UI::Desktop::BCOM::Lost/Stolen Indicator added to My Accounts page, DUAL line card
#					: B-53065	G3::BUS::UI::Desktop::BCOM::Lost/Stolen Indicator added to My Accounts page, single line
#					: B-53051	G3::BUS::UI::Desktop::BCOM::Lost/Stolen Indicator on Credit Gateway page, Dual Line card
#					: B-52219	G3::BUS::UI::Desktop::BCOM::Lost/Stolen Indicator on Credit Gateway page, Single Line card
#					: B-53063	G3::BUS::UI::Desktop::MCOM::Lost/Stolen Indicator added to My Accounts page, dual line
#					: B-53066	G3::BUS::UI::Desktop::MCOM::Lost/Stolen Indicator added to My Accounts page, single line
#					: B-53069	G3::BUS::UI::Desktop::MCOM::Lost/Stolen Indicator on Credit Gateway page, Dual Line card
#					: B-53055	G3::BUS::UI::Desktop::MCOM::Lost/Stolen Indicator on Credit Gateway page, Single Line card
#					: B-60791	G4::BUS::UI::Desktop::BCOM::Lost/Stolen Indicator added to My Wallet page
#####################################################################################

Feature: Lost or Stolen

  # Kill switch :: creditLostRStolenEnabled should be TRUE

  @wip @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario Outline: Verify LS message display on myaccount when user added card to profile which is reported as lost/stolen
    Given I sign in with user having "<user_type>" added in profile
    When I navigate to my account page
    Then I should see "LS1" on my account page
    And I should see my account page with LS_Indicator in credit card section
    Examples:
      | user_type      |
      | prop_ls        |
      | amex_ls        |
      | dual_child_ls  |
      | dual_ls        |
      | dual_parent_ls |

  @wip @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario Outline: Verify LS message display on gateway page when user added card to profile which is reported as lost/stolen
    Given I sign in with user having "<user_type>" added in profile
    When I navigate to credit card page from footer
    Then I should see "LS1" on credit gateway page
    And I should see credit gateway page with LS_Indicator in credit card section
    Examples:
      | user_type      |
      | prop_ls        |
      | amex_ls        |
      | dual_child_ls  |
      | dual_ls        |
      | dual_parent_ls |

  @wip @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario: Verify LS message is not displaying when user not reported any card as lost/stolen
    Given I sign in with user having "new_prop_user" added in profile
    When I navigate to my account page
    Then I should not see "LS1" on my account page
    And I should not see my account page with LS_Indicator in credit card section
    When I navigate to credit card page from footer
    Then I should not see credit gateway page with LS_Indicator in credit card section
    And I should not see "LS1" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario: Verify LS message is displaying when user has multiple cards and default card is not reported as lost/stolen
    Given I sign in with user having "multiple_accounts_ls" added in profile
    When I navigate to my account page
    Then I should see "LS1" on my account page
    And I should not see my account page with LS_Indicator in credit card section
    When I navigate to credit card page from footer
    Then I should not see credit gateway page with LS_Indicator in credit card section
    And I should see "LS1" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario: Verify LS indicator is displaying along with message when user has multiple cards and default card is reported as lost/stolen
    Given I sign in with user having "multiple_accounts_ls_default" added in profile
    When I navigate to my account page
    Then I should see "LS1" on my account page
    And I should see my account page with LS_Indicator in credit card section
    When I navigate to credit card page from footer
    Then I should see credit gateway page with LS_Indicator in credit card section
    And I should see "LS1" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario: Verify if user having both AS1 and LS1 messages, only LS1 message is shown on UI
    Given I sign in with user having "user_ls_as" added in profile
    When I navigate to my account page
    Then I should see "LS1" on my account page
    When I navigate to credit card page from footer
    And I should see "LS1" on credit gateway page

  @wip @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario Outline: Verify LS indicator displaying on mywallet page when user has card which is reported as lost/stolen
    Given I sign in with user having "<user_type>" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see my wallet page with LS_Indicator in credit card section
    Examples:
      | user_type |
      | prop_ls   |
      | amex_ls   |
      | dual_ls   |

  @use_manual @domain_customer @artifact_shopapp @project_model2 @release_16P
  Scenario Outline: Verify user is able to click on replacement card link in LS1 message
    Given I sign in with user having "<user_type>" added in profile
    When I navigate to <notification_page> page
    Then I should see LS1 message on <notification_page> page with card
    And I should able to select add replacement card link in LS message
    Examples:
      | user_type | notification_page      |
      | prop_ls   | My Account             |
      | amex_ls   | My Account             |
      | prop_ls   | credit service gateway |
      | amex_ls   | credit service gateway |

############ B-65571 ::G2::BUS::UI::Desktop::MCOM::Message LS1 (My Account) **Dual-line** E2E click thru and message removal (TEST ONLY) ################
  @use_manual @domain_customer @project_lost_or_stolen @release_17I
  Scenario: Verify Dual-line E2E click thru and message removal when both lines are reported as LS
    Given I sign in with user having "dual_both_ls" added in profile
    When I navigate to my account page
    Then I should see LS1 notification message
    When I select credit summary page link on LS1 notification message
    Then I should navigate to Citi credit summary page
    When I navigate back to myaccount page
    Then I should see newly issued card on my account page
    And I should not see LS1 notification message

  @use_manual @domain_customer @project_lost_or_stolen @release_17I
  Scenario: Verify Dual-line E2E click thru and message removal when only child line is reported as LS
    Given I sign in with user having "dual_child_ls" added in profile
    When I navigate to my account page
    Then I should see LS1 notification message
    When I select credit summary page link on LS1 notification message
    Then I should navigate to Citi credit summary page
    When I navigate back to myaccount page
    Then I should see newly issued child card in dual line
    And I should not see LS1 notification message

  @use_manual @domain_customer @project_lost_or_stolen @release_17I
  Scenario: Verify Dual-line E2E click thru and message removal when only parent line is reported as LS
    Given I sign in with user having "dual_parent_ls" added in profile
    When I navigate to my account page
    Then I should see LS1 notification message
    When I select credit summary page link on LS1 notification message
    Then I should navigate to Citi credit summary page
    When I navigate back to myaccount page
    Then I should see newly issued parent card in dual line
    And I should not see LS1 notification message

  @use_manual @domain_customer @project_lost_or_stolen @release_17I
  Scenario: Verify LS1 notification message display after timeframe
    Given I sign in with user having "dual_ls_expired" added in profile
    When I navigate to my account page
    Then I should not see LS1 notification message
  # In above scenario login with profile having expiry date is set as acct_transfer_date + 90 calendar days