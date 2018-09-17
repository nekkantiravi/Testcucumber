#####################################################################################
# Author : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: Lost or Stolen

  # Kill switch :: creditLostRStolenEnabled should be TRUE

############ B-65568 :: G2::BUS::UI::Desktop::BCOM::Message LS1 (My Account) **Dual-line** E2E click thru and message removal (TEST ONLY) ################
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