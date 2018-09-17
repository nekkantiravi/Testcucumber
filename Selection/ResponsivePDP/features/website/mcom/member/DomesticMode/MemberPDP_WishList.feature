#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.18,2018
#---------------------------------------------------

Feature: MemberPDP_WishList Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_wishlist
  Scenario Outline: Verify AddToList functionality on member PDP Site mode
    Given I visit the home page on <device> as a <user> user
    When I navigate directly to "member" PDP site mode on <device>
    And I click the A2L button on member PDP site mode on <device>
    Then I verify AddToList popup for the <user> user on member PDP site mode on <device>
    And I click the <user> List link on AddToList popup on member PDP site mode on <device>
    Then I verify that the product is added to list in site mode on <device>
    Examples:
      |device  |user       |
      |desktop |guest      |
      |desktop |registered |
      |mobile  |guest      |
      |mobile  |registered |
      |tablet  |guest      |
      |tablet  |registered |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_wishlist
  Scenario Outline: Verify navigation to member PDP by clicking on product image on WishList page
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "member" PDP site mode on <device>
    And I click the A2L button on member PDP site mode on <device>
    Then I verify AddToList popup for the guest user on member PDP site mode on <device>
    And I click the guest List link on AddToList popup on member PDP site mode on <device>
    And I click the Product Image on list page in site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |
