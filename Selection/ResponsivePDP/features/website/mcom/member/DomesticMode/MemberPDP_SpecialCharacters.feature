#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.02,2018
#---------------------------------------------------

Feature: MemberPDP_SpecialCharacters Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_specialcharacters
  Scenario Outline: Verify A2B functionality for product with NO BrandName on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify the QB count is updating with 0 quantity items in bag on <device>
    And I select a random size on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    Then I verify the QB count is updating with default quantity items in bag on <device>
    Examples:
      |device  |PID     |
      |desktop |5395119 |
      |desktop |676544  |
      |mobile  |5395119 |
      |mobile  |676544  |
      |tablet  |5395119 |
      |tablet  |676544  |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_specialcharacters
  Scenario Outline: Verify A2B functionality for special character BrandName on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select a random size on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "member" product being added in site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    Then I verify the QB count is updating with default quantity items in bag on <device>
    Examples:
      |device  |PID     |
      |desktop |1098374 |
      |desktop |4981658 |
      |desktop |2489557 |
      |desktop |2916226 |
      |desktop |2758044 |
      |desktop |4823083 |
      |desktop |4365683 |
      |desktop |4875561 |
      |desktop |4532075 |
      |desktop |4645028 |
      |desktop |2921180 |
      |mobile  |1098374 |
      |mobile  |4981658 |
      |mobile  |2489557 |
      |mobile  |2916226 |
      |mobile  |2758044 |
      |mobile  |4823083 |
      |mobile  |4365683 |
      |mobile  |4875561 |
      |mobile  |4532075 |
      |mobile  |4645028 |
      |mobile  |2921180 |
      |tablet  |1098374 |
      |tablet  |4981658 |
      |tablet  |2489557 |
      |tablet  |2916226 |
      |tablet  |2758044 |
      |tablet  |4823083 |
      |tablet  |4365683 |
      |tablet  |4875561 |
      |tablet  |4532075 |
      |tablet  |4645028 |
      |tablet  |2921180 |

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_specialcharacters
  Scenario Outline: Verify A2B functionality for special character ProductName on Member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate to member PDP site mode with PID "<PID>" on <device>
    Then I verify the basic elements on member PDP site mode on <device>
    And I verify product prices on member PDP site mode on <device>
    And I select a random size on member PDP site mode on <device>
    And I click the A2B button on member PDP site mode on <device>
    Then I verify the AddToBag drawer as the "member" product being added in site mode on <device>
    And I click the ContinueShopping button on member PDP site mode on <device>
    Then I verify the QB count is updating with default quantity items in bag on <device>
    Examples:
      |device  |PID     |
      |desktop |2952690 |
      |desktop |1399357 |
      |desktop |258471  |
      |desktop |2895687 |
      |desktop |2182074 |
      |desktop |4916290 |
      |desktop |561227  |
      |desktop |1627956 |
      |mobile  |2952690 |
      |mobile  |1399357 |
      |mobile  |258471  |
      |mobile  |2895687 |
      |mobile  |2182074 |
      |mobile  |4916290 |
      |mobile  |561227  |
      |mobile  |1627956 |
      |tablet  |2952690 |
      |tablet  |1399357 |
      |tablet  |258471  |
      |tablet  |2895687 |
      |tablet  |2182074 |
      |tablet  |4916290 |
      |tablet  |561227  |
      |tablet  |1627956 |
