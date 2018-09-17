#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Sep.07,2017
#---------------------------------------------------


Feature: MEW_PDP Validation & Verification


  @use_regression_mew @mcom_mew_pdp @mew_pdp_breadcrumbs
  Scenario: I verify BreadCrumbs on random member PDP Site mode
    Given I goto Mobile Home page
    When I navigate the global navigation menu as follows:
      | SHOP         |
      | MEN          |
      | ALL CLOTHING |
      | JEANS        |
    And I select any random member product on browse page
    And I save the productId on PDP page
    Then I verify BreadCrumbs links on member PDP site mode


  @use_regression_mew @mcom_mew_pdp @mew_pdp_breadcrumbs
  Scenario Outline: I verify BreadCrumbs links on member PDP Site mode
    Given I goto Mobile Home page
    When I navigate to PDP with PID "<productId>" in site mode
    And I save the category & subCategory of the BreadCrumbs on member PDP site mode
    Then I verify BreadCrumbs links on member PDP site mode
    Examples:
      |productId |
      |3706693   |
      |643418    |
      |4404918   |
      |4674175   |
      |74579     |
      |4930726   |
      |4810511   |


  @use_regression_mew @mcom_mew_pdp @mew_pdp_breadcrumbs
  Scenario Outline: I verify navigation of BreadCrumbs rootCategory & homeCategory links on PDP Site mode
    Given I goto Mobile Home page
    When I navigate to PDP with PID "<productId>" in site mode
    And I verify the "<rootCategory>" and "<homeCategory>" of the BreadCrumbs on "<productType>" PDP site mode
    Then I verify navigation of BreadCrumbs "<rootCategory>" and "<homeCategory>" links on "<productType>" PDP site mode
    Examples:
      |productType      |productId |rootCategory          |homeCategory          |
      |member           |22804     |Dining & Entertaining |Flatware & Silverware |
      |member           |4596477   |Kids & Baby           |Dresses               |
      |member           |1310      |Fine China            |None                  |
      |master           |19942     |Dining & Entertaining |Flatware & Silverware |
      |master           |3030453   |Bed & Bath            |Bath Towels           |
      |member BigTicket |1346302   |Furniture             |None                  |
      |master BigTicket |1808979   |Furniture             |None                  |
      |member CHANEL    |4682732   |Beauty                |Gifts & Value Sets    |
      |master CHANEL    |669039    |Beauty                |Skin Care             |
      |E-Gift Card      |4391440   |Gift Cards            |None                  |
      |E-Gift Card      |4391437   |Gift Cards            |None                  |


  @use_regression_mew @mcom_mew_pdp @mew_pdp_breadcrumbs
  Scenario Outline: I verify navigation of BreadCrumbs rootCategory & homeCategory links on PDP iShip mode
    Given I visit the mobile web site as a guest user in iship mode
    And I navigate to PDP with PID "<productId>" in iship mode
    And I verify the "<rootCategory>" and "<homeCategory>" of the BreadCrumbs on "<productType>" PDP iship mode
    Then I verify navigation of BreadCrumbs "<rootCategory>" and "<homeCategory>" links on "<productType>" PDP iship mode
    Examples:
      |productType      |productId |rootCategory          |homeCategory           |
      |member           |22804     |Dining & Entertaining |Flatware & Silverware  |
      |member           |4596477   |Kids & Baby           |Dresses                |
      |member           |2370305   |For The Home          |Cleaning & Organization|
      |member           |1310      |Fine China            |None                   |
      |master           |19942     |Dining & Entertaining |Flatware & Silverware  |
      |master           |3030453   |Bed & Bath            |Bath Towels            |


  @use_regression_mew @mcom_mew_pdp @mew_pdp_breadcrumbs
  Scenario Outline: I verify navigation of BreadCrumbs rootCategory & homeCategory links on PDP Registry mode
    Given I visit mobile site as a guest user in registry mode
    And I navigate to PDP with PID "<productId>" in registry mode
    And I verify the "<rootCategory>" and "<homeCategory>" of the BreadCrumbs on "<productType>" PDP registry mode
    Then I verify navigation of BreadCrumbs "<rootCategory>" and "<homeCategory>" links on "<productType>" PDP registry mode
    Examples:
      |productType      |productId |rootCategory          |homeCategory           |
      |member           |22804     |Dining & Entertaining |Flatware & Silverware  |
      |member           |2602860   |Kitchen               |Coffee, Tea & Espresso |
      |member           |1310      |Fine China            |None                   |
      |member           |4667826   |Bed & Bath            |Personal Care          |
      |master           |19942     |Dining & Entertaining |Flatware & Silverware  |
      |master           |3030453   |Bed & Bath            |Bath Towels            |
