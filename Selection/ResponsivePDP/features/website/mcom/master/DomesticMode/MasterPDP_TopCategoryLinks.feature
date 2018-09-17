#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Oct.13,2017
#---------------------------------------------------

Feature: MasterPDP_TopCategoryLinks Validation & Verification Scenarios

  @domain_selection @use_regression @master_pdp_topcategorylinks @master_domestic_pdp @mcom_domestic_pdp @wip
  Scenario Outline: I verify functionality of TopCategory links on member PDP Site mode
    Given I visit the home page on <device> as a guest user
    When I navigate to <productType> PDP site mode with PID "<PID>" on <device>
    Then I verify the basic elements on master PDP site mode on <device>
    And I verify the "<rootCategory>" & "<homeCategory>" of the TopCategory links on <productType> PDP site mode on <device>
    Then I verify navigation of TopCategory "<rootCategory>" & "<homeCategory>" links on <productType> PDP site mode on <device>
    Examples:
      |productType      |PID       |rootCategory          |homeCategory          |device  |
      |master           |19942     |Dining & Entertaining |Flatware & Silverware |desktop |
      |master           |19942     |Dining & Entertaining |Flatware & Silverware |mobile  |
      |master           |19942     |Dining & Entertaining |Flatware & Silverware |tablet  |
      |master           |3030453   |Bed & Bath            |Bath Towels           |desktop |
      |master           |3030453   |Bed & Bath            |Bath Towels           |mobile  |
      |master           |3030453   |Bed & Bath            |Bath Towels           |tablet  |
      |master BigTicket |1808979   |Furniture             |None                  |desktop |
      |master BigTicket |1808979   |Furniture             |None                  |mobile  |
      |master BigTicket |1808979   |Furniture             |None                  |tablet  |
