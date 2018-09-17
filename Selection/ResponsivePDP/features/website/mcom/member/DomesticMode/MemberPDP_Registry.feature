#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.18,2018
#---------------------------------------------------

Feature: MemberPDP_Registry Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_registry
  Scenario Outline: Verify Add2Registry functionality on member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2R button on member PDP site mode on <device>
    Then I verify the AddToRegistry drawer as the "member" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |
