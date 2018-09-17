# Author: QE Team
# Date Created: 12/28/2017
# Story B-100954

Feature: Verifying Brand Index Left Navigation functionality

  @SNBC_Phase4
  Scenario Outline: Verify header should display 'All Brands' on page load for Brand index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    Then I verify header is displaying "All Brands" category in left navigation
    And I verify selected "All Brands" category is getting highlighted
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify header should display selected category for Brand index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<brand>" category from left navigation
    Then I verify header is displaying "<brand>" category in left navigation
    And I verify selected "<brand>" category is getting highlighted
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining  |

  @SNBC_Phase4
  Scenario Outline:  Verify brands on the page should be filtered by selected category for Brand index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<brand>" category from left navigation
    Then I verify brands is getting filtered by selected "<brand>" category
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining  |

  @SNBC_Phase4
  Scenario Outline: Verify Breadcrumbs leads to parent category for Brand index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<brand>" category from left navigation
    Then  I verify that Breadcrumbs is getting displayed
    And I click on Breadcrumbs link
    And I verify selected "All Brands" category is getting highlighted
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining |