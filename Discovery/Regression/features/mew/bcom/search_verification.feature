Feature: Component tests for search

  @dsv_mew_sev2 @domain_discovery
  Scenario: Verify user is navigated to correct page if no results were found for keyword
    Given I visit the mobile web site as a guest user
    When I type "Jjjkkll" in mew search box
    Then I should not see auto complete suggestions in mew
    When I click on search button
    Then I should be on the zero search results page
