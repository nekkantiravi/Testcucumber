# Author: QE Team
# Date Created: 02/06/2018
# Story B-103072

Feature: Verifying the Designer Index page and its subcategories for Page Meta Tags

  @SNBC_Phase4_a
  Scenario Outline: Verify Page Meta Tags displaying on Designer Index page on all three modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    Then I verify the "soasta_info" in HTML view source code
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Page Title displaying on Designer Index page on all three modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    Then I verify that the page title contains "<title>"
    Examples:
      | shopping_mode | title          |
      | domestic      | ALL DESIGNERS  |
      | iship         | ALL DESIGNERS  |
      | registry      | ALL BRANDS     |

  @SNBC_Phase4
  Scenario Outline: Verify Page Title displaying on SubCategory page on all three modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    And I select "<brand>" category from left navigation
    Then I verify the "soasta_info" in HTML view source code
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify Page Title displaying on SubCategory page on all three modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    And I select "<brand>" category from left navigation
    Then I verify that the page title contains "<title>"
    Examples:
      | shopping_mode | brand   | title   |
      | domestic      | women   | WOMEN   |
      | iship         | women   | WOMEN   |
      | registry      | kitchen | KITCHEN |