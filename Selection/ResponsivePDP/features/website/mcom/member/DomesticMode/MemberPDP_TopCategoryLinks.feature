#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Oct.13,2017
#---------------------------------------------------

Feature: MemberPDP_TopCategoryLinks Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_topcategorylinks
  Scenario Outline: I verify functionality of TopCategory links on member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate to <productType> PDP site mode with PID "<PID>" on <device>
    And I verify the "<rootCategory>" & "<homeCategory>" of the TopCategory links on <productType> PDP site mode on <device>
    Then I verify navigation of TopCategory "<rootCategory>" & "<homeCategory>" links on <productType> PDP site mode on <device>
    Examples:
      |productType     |PID       |rootCategory          |homeCategory          |device  |
      |member          |22804     |Dining & Entertaining |Flatware & Silverware |desktop |
      |member          |22804     |Dining & Entertaining |Flatware & Silverware |mobile  |
      |member          |22804     |Dining & Entertaining |Flatware & Silverware |tablet  |
      |member          |1399357   |Jewelry & Watches     |Bracelets             |desktop |
      |member          |1399357   |Jewelry & Watches     |Bracelets             |mobile  |
      |member          |1399357   |Jewelry & Watches     |Bracelets             |tablet  |
      |memberBigTicket |1346302   |Furniture             |None                  |desktop |
      |memberBigTicket |1346302   |Furniture             |None                  |mobile  |
      |memberBigTicket |1346302   |Furniture             |None                  |tablet  |
      |eGiftCard       |4391440   |Gift Cards            |None                  |desktop |
      |eGiftCard       |4391440   |Gift Cards            |None                  |mobile  |
      |eGiftCard       |4391440   |Gift Cards            |None                  |tablet  |
