# Author: QE Team
# Date Created: 1/10/2018
# Story B-99260

Feature: Verifying the KS for Banner Machine on different modes

  #KS ON scenarios
  Scenario Outline: Verify Banner Machine media is displaying on catspalsh page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate the global navigation menu as follows:
      | MEN |
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  Scenario Outline: Verify Banner Machine media is displaying on browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  Scenario Outline: Verify Banner Machine media is displaying on search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "jeans"
    Then I verify bannerMachineDisplayEnabled KS is set to "true"
    And I verify banner machine is displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |

    #KS OFF scenarios
  Scenario Outline: Verify Banner Machine media is not displaying on catsplash page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate the global navigation menu as follows:
      | MEN |
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  Scenario Outline: Verify Banner Machine media is displaying on browse page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate the global navigation menu as follows:
      | MEN   |
      | Jeans |
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |


  Scenario Outline: Verify Banner Machine media is displaying on search page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "jeans"
    Then I verify bannerMachineDisplayEnabled KS is set to "false"
    And I verify banner machine is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |