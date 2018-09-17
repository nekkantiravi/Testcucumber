Feature: Verify search for a keyword results

  @dsv_mew_sev1 @domain_discovery
  Scenario: Verify tapping on autocomplete takes user to the search landing page using the autocomplete suggestion as the search keyword
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "dre" in mew search box
    Then I should see "dre" in mew autocomplete suggestions
    When I select "dresses" from mew autocomplete suggestions
    Then I should be in Search Landing page using mobile website
