Feature: Verify search for a keyword results

  @dsv_mew_sev1
  Scenario: Verify tapping on autocomplete takes user to the search landing page using the autocomplete suggestion as the search keyword
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "jea" in mew search box
    Then I should see "Jea" in mew autocomplete suggestions
    When I select "Jeans" from mew autocomplete suggestions
    Then I should be in Search Landing page using mobile website

  @dsv_mew_sev1 @domain_discovery
  Scenario: Verify navigation to a PDP page from search results page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Boots" in mew search box
    And I click on search go button
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    And I should see Add to Bag button on PDP page
