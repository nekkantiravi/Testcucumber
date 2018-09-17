Feature: Verify that the user navigates from search page to pdp

  @dsv_mew_sev1 @domain_discovery
  Scenario: Verify navigation to a PDP page from search results page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "Boots"
    When I select a random member product using mobile website
    Then I should be redirected to PDP page
    And I should see Add to Bag button on PDP page