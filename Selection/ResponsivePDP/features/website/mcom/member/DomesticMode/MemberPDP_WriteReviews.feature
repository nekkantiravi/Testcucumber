#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.24,2018
#---------------------------------------------------

Feature: MemberPDP_WriteReviews Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_reviews
  Scenario Outline: Verify reviews rating & count on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "randomMember" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify the reviews count and stars on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |
