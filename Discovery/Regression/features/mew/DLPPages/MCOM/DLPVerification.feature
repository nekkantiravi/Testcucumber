Feature: Dynamic landing page verification

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify images are loading properly on DLP pages
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    And I should see all product images loaded properly
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify back to to functionality on DLP page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    And I navigate to bottom of page
    Then I should see back to top button
    And I navigate to top of page
    Then I should not see back to top button
    And I navigate to bottom of page
    And I tap on back to top button
    Then I should navigate to top of the page
    Examples:
      | mode     |
      | domestic |
      | iship    |