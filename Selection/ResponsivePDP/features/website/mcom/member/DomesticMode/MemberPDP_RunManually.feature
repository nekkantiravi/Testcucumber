#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.24,2018
#---------------------------------------------------

Feature: MemberPDP_RunManually Validation & Verification Scenarios

  @MemberPDP_RunManually
  Scenario Outline: Verify the zoomer & altimages on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "memberWithAltImages" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify the zoomer & altimages on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |