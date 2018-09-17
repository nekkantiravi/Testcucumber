# Author: QE Team
# Date Created: 12/29/2017
# Story B-97871

Feature: Verifying Designer Index Back To Top button functionality

  @SNBC_Phase4
  Scenario Outline: Verify back to top button is not displayed for Designer index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify back to top button is  displayed after navigating to bottom for Designer index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @SNBC_Phase4
  Scenario Outline: Verify back to top button is  displayed after clicking on a letter on the A-Z index for Designer index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    And I click "<index>" letter from A-Z index
    Then I verify that back to top button is displayed on page
    Examples:
      | shopping_mode | index |
      | domestic      | c     |
      | iship         | d     |
      | registry      | c     |

  @SNBC_Phase4
  Scenario Outline: Verify back to top button would function similarly on all brand categories for Designer index page on different modes
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I navigate to Designers index page
    And I select "<brand>" category from left navigation
    Then I verify that back to top button is not displayed on page
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I select back to top button
    And I click "<index>" letter from A-Z index
    Then I verify that back to top button is displayed on page
    Examples:
      | shopping_mode | brand   | index |
      | domestic      | women   | c     |
      | iship         | women   | c     |
      | registry      | kitchen | d     |