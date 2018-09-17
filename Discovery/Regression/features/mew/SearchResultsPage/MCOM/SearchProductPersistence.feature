Feature: Product persistence on Search page when navigating back from PDP

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence on search page when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Plates" in mew search and click enter
    And I scroll to 18th product on search results page
    And I click on 18th product on search results page
    When I click on back button on PDP page and navigate to search page
    And I should be returned to the same product position on search page
    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |