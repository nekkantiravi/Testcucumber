Feature: Product persistence on DLP page when navigating back from PDP

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Verify product persistence on DLP page when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    And I scroll to 18th product on DLP page
    And I click on 18th product on DLP page
    When I click on back button on PDP page and navigate to DLP page
    And I should be returned to the same product position on DLP page
    Examples:
      | mode     |
      | domestic |
      | iship    |