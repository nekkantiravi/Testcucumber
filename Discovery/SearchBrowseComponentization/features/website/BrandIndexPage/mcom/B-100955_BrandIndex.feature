# Author: QE Team
# Date Created: 12/28/2017
# Story B-100955

Feature: Verifying the Brand Index page and XAPI response on different modes

  @SNBC_Phase4
  Scenario Outline: Verify A-Z Index is displaying on Brand Index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    Then I verify A-Z Index is displaying on Brand Index page
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline:Verify letter is clickable and page scrolls to the applicable brand for selected letter on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I click letter in brand index
    Then I verify page scrolls to the applicable brand for selected letter and section is highlighted
    And I verify category is clickable under applicable brands section
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline:Verify letter is unclickable and disabled that has no applicable brands on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    Then I verify letter in displaying with grey color and is disabled and unclickable
    Examples:
      | shopping_mode |
      | registry      |
  #No such letter is available in domestic and Iship mode