#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.18,2018
#---------------------------------------------------

Feature: MemberPDP_Add2Bag_E2E Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @pdp_add2bag_e2e
  Scenario Outline: Verify A2B E2E functionality on Member PDP Site mode on desktop
    Given I visit the home page on desktop as a <user> user
    When I navigate directly to "member" PDP site mode on desktop
    Then I verify the basic elements on member PDP site mode on desktop
    And I verify product prices on member PDP site mode on desktop
    And I select random quantity on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    Then I verify the AddToBag drawer as the "member" product being added in site mode on desktop
    And I click the Checkout button on member PDP site mode on desktop
    Then I verify the ShoppingBag page as the "member" product being added in site mode on desktop
    And I click the Product Image on ShoppingBag page site mode on desktop
    Then I verify the navigation to member PDP site mode on desktop
    And I verify the elements on QuickBag overlay on desktop
    And I click the Remove button on QuickBag overlay on desktop
    Then I verify the QB count is updating with 0 quantity items in bag on desktop
    Examples:
      |user       |
      |guest      |
      |registered |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @pdp_add2bag_e2e
  Scenario Outline: Verify A2B E2E functionality on MemberWithColor PDP Site mode on desktop
    Given I visit the home page on desktop as a <user> user
    When I navigate directly to "memberWithColor" PDP site mode on desktop
    Then I verify the basic elements on member PDP site mode on desktop
    And I verify product prices on member PDP site mode on desktop
    And I randomly select an available color on member PDP site mode on desktop
    And I select random quantity on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    Then I verify the AddToBag drawer as the "memberWithColor" product being added in site mode on desktop
    And I click the Checkout button on member PDP site mode on desktop
    Then I verify the ShoppingBag page as the "memberWithColor" product being added in site mode on desktop
    And I click the Product Image on ShoppingBag page site mode on desktop
    Then I verify the navigation to member PDP site mode on desktop
    And I verify the elements on QuickBag overlay on desktop
    And I click the Remove button on QuickBag overlay on desktop
    Then I verify the QB count is updating with 0 quantity items in bag on desktop
    Examples:
      |user       |
      |guest      |
      |registered |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @pdp_add2bag_e2e
  Scenario Outline: Verify A2B E2E functionality on MemberWithSizeColor PDP Site mode on desktop
    Given I visit the home page on desktop as a <user> user
    When I navigate directly to "memberWithSizeColor" PDP site mode on desktop
    And I randomly select an available color on member PDP site mode on desktop
    And I randomly select an available size on member PDP site mode on desktop
    And I select random quantity on member PDP site mode on desktop
    Then I verify the basic elements on member PDP site mode on desktop
    And I verify product prices on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    Then I verify the AddToBag drawer as the "memberWithSizeColor" product being added in site mode on desktop
    And I click the Checkout button on member PDP site mode on desktop
    Then I verify the ShoppingBag page as the "memberWithSizeColor" product being added in site mode on desktop
    And I click the Product Image on ShoppingBag page site mode on desktop
    Then I verify the navigation to member PDP site mode on desktop
    And I verify the elements on QuickBag overlay on desktop
    And I click the Remove button on QuickBag overlay on desktop
    Then I verify the QB count is updating with 0 quantity items in bag on desktop
    Examples:
      |user       |
      |guest      |
      |registered |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @pdp_add2bag_e2e
  Scenario Outline: Verify A2B E2E functionality on Member PDP Site mode
    Given I visit the home page on <device> as a <user> user
    When I navigate directly to "member" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "member" product being added in site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "member" product being added in site mode on <device>
    And I click the Product Image on ShoppingBag page site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    And I click the QuickBag Icon on QuickBag overlay on <device>
    And I click the Remove button on ShoppingBag page site mode on <device>
    Then I verify the ShoppingBag page in site mode for bag empty message & BagID removed on <device>
    Examples:
      |device  |user       |
      |desktop |guest      |
      |mobile  |guest      |
      |tablet  |guest      |
      |desktop |registered |
#      |mobile  |registered |
#      |tablet  |registered |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @pdp_add2bag_e2e
  Scenario Outline: Verify A2B E2E functionality on MemberWithColor PDP Site mode
    Given I visit the home page on <device> as a <user> user
    When I navigate directly to "memberWithColor" PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithColor" product being added in site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "memberWithColor" product being added in site mode on <device>
    And I click the Product Image on ShoppingBag page site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    And I click the QuickBag Icon on QuickBag overlay on <device>
    And I click the Remove button on ShoppingBag page site mode on <device>
    Then I verify the ShoppingBag page in site mode for bag empty message & BagID removed on <device>
    Examples:
      |device  |user       |
      |desktop |guest      |
      |mobile  |guest      |
      |tablet  |guest      |
      |desktop |registered |
#      |mobile  |registered |
#      |tablet  |registered |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @pdp_add2bag_e2e
  Scenario Outline: Verify A2B E2E functionality on MemberWithSizeColor PDP Site mode
    Given I visit the home page on <device> as a <user> user
    When I navigate directly to "memberWithSizeColor" PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I randomly select an available size on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithSizeColor" product being added in site mode on <device>
    And I click the Checkout button on member PDP site mode on <device>
    Then I verify the ShoppingBag page as the "memberWithSizeColor" product being added in site mode on <device>
    And I click the Product Image on ShoppingBag page site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    And I click the QuickBag Icon on QuickBag overlay on <device>
    And I click the Remove button on ShoppingBag page site mode on <device>
    Then I verify the ShoppingBag page in site mode for bag empty message & BagID removed on <device>
    Examples:
      |device  |user       |
      |desktop |guest      |
      |mobile  |guest      |
      |tablet  |guest      |
      |desktop |registered |
#      |mobile  |registered |
#      |tablet  |registered |
