Feature: Verifying Page title and SOASTA Page Info

  Scenario Outline: Brand Index Page - Domestic|Iship|Registry - Verify page title of Brand Index Page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    Then I verify page title should be "<title>"
    Examples:
      | shopping_mode | title          |
      | domestic      | Featured Designers for All Brands  |
      | iship         | Featured Designers for All Brands  |
      | registry      | Featured Designers for All Brands  |

  Scenario Outline: Brand Index Page - Domestic|Iship|Registry - Verify soasta info of Brand Index Page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    Then I verify the "soasta_info" in HTML view source code
    Examples:
      |shopping_mode|
      |Domestic     |
      |iship        |
      |registry     |

  Scenario Outline: Brand Index Page - Domestic|Iship|Registry - Verify page title of Sub Brand of Brand Index Page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    And I select "<brand>" category from left navigation
    Then I verify page title should be "<title>"
    Examples:
    Examples:
      | shopping_mode | brand   | title   |
      | domestic      | home   | Featured Designers for Home   |
      | iship         | home   | Featured Designers for Home   |
      | registry      | kitchen | Featured Designers for Kitchen |


  Scenario Outline: Brand Index Page - Domestic|Iship|Registry - Verify soasta info of Sub Brand of Brand Index Page
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    And I select "<brand>" category from left navigation
    Then I verify the "soasta_info" in HTML view source code
    Examples:
      |shopping_mode|
      |Domestic     |
      |iship        |
      |registry     |