# Author: QE Team
# Date Created: 1/30/2018
# Story B-103596

Feature: Verifying the UI changes on BrandIndex page on different modes

  @SNBC_Phase4
  Scenario Outline: Verify SEO section is not displayed on All Brands page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    Then I verify SEO section is not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify SEO section is not displayed on Subcategory pages on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<brand>" category from left navigation
    Then I verify SEO section is not displayed
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining |

  @SNBC_Phase4 @manual
  Scenario Outline: Verify borders are not displayed on All Brands page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    Then I verify all the borders are not displayed
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4 @manual
  Scenario Outline: Verify SEO section is not displayed on Subcategory pages on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Brands index page
    And I select "<brand>" category from left navigation
    Then I verify all the borders are not displayed
    Examples:
      | shopping_mode | brand  |
      | domestic      | Beauty |
      | iship         | Dining |
      | registry      | Dining |