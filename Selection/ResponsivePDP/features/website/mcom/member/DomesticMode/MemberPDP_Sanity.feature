#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Feb.05,2018
#---------------------------------------------------

Feature: MemberPDP_Sanity Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify A2B functionality & A2B drawer on Member PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "member" product being added in site mode on <device>
    Examples:
      |device  |PID     |
      |desktop |4801648 |
#      |desktop |5712027 |
      |mobile  |4801648 |
      |tablet  |4801648 |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify A2B functionality & A2B drawer on MemberWithColor PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithColor" product being added in site mode on <device>
    Examples:
      |device  |PID     |
      |desktop |366203  |
      |mobile  |366203  |
      |tablet  |366203  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify A2B functionality & A2B drawer on MemberWithSize PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select the first available size on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "memberWithColor" product being added in site mode on <device>
    Examples:
      |device  |PID     |
      |desktop |5281908 |
      |mobile  |5281908 |
      |tablet  |5281908 |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify A2B functionality on MemberWithSizeColor PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    And I verify the QB count is updating with 0 quantity items in bag on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I select the first available size on member PDP site mode on <device>
    And I select random quantity on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    Then I verify the QB count is updating with random quantity items in bag on <device>
    Examples:
      |device  |PID     |
      |desktop |5247361 |
      |mobile  |5247361 |
      |tablet  |5247361 |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify A2L & navigation to Member PDP by clicking on product image on WishList page -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    And I click the A2L button on member PDP site mode on <device>
    Then I verify AddToList popup for the guest user on member PDP site mode on <device>
    And I click the guest List link on AddToList popup on member PDP site mode on <device>
    And I click the Product Image on list page in site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    Examples:
      |device  |PID     |
      |desktop |4801648 |
      |mobile  |4801648 |
      |tablet  |4801648 |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify A2L & navigation to MemberWithColor PDP by clicking on product image on WishList page -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    And I randomly select an available color on member PDP site mode on <device>
    And I click the A2L button on member PDP site mode on <device>
    Then I verify AddToList popup for the guest user on member PDP site mode on <device>
    And I click the guest List link on AddToList popup on member PDP site mode on <device>
    And I click the Product Image on list page in site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    Examples:
      |device  |PID     |
      |desktop |366203  |
      |mobile  |366203  |
      |tablet  |366203  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify A2L & navigation to MemberWithSizeColor PDP by clicking on product image on WishList page -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    And I select the first available size on member PDP site mode on <device>
    And I click the A2L button on member PDP site mode on <device>
    Then I verify AddToList popup for the guest user on member PDP site mode on <device>
    And I click the guest List link on AddToList popup on member PDP site mode on <device>
    And I click the Product Image on list page in site mode on <device>
    Then I verify the navigation to member PDP site mode on <device>
    Examples:
      |device  |PID     |
      |desktop |5281908 |
      |mobile  |5281908 |
      |tablet  |5281908 |
      |desktop |5247361 |
      |mobile  |5247361 |
      |tablet  |5247361 |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify Product Details section on Member PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "productionTestData" PDP site mode on <device>
    And I click the Product Details button on member PDP site mode on <device>
    Then I verify elements under Product Details section on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify Price Details section on Member PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "productionTestData" PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I click the Price Details button on member PDP site mode on <device>
    Then  I verify elements under Price Details section on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify navigation of all links under Shipping&Returens section on Member PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "productionTestData" PDP site mode on <device>
    And I click the Shipping & Returns button on member PDP site mode on <device>
    Then I verify the navigation of all links under Shipping&Returns on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_sanity
  Scenario Outline: Verify the Pinterest social icon on Member PDP Site mode -Sanity
    Given I visit the home page on <device> as a guest user
    When I navigate directly to "productionTestData" PDP site mode on <device>
    Then I verify the Pinterest social icon on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |
