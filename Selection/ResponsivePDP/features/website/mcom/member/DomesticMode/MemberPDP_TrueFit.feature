#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.02,2018
#---------------------------------------------------

Feature: MemberPDP_TrueFit Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_truefit
  Scenario Outline: Verify TrueFit functionality on Member PDP in Site mode
    Given I visit the home page on <device> as a guest user
#    When I search for a "memberTrueFit" product & navigate to PDP site mode on <device>
    When I navigate directly to "memberTrueFit" PDP site mode on <device>
    Then I verify TrueFit functionality on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |