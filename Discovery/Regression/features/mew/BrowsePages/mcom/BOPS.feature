Feature: As a product owner, I would like to ensure BOPS search results are updated properly for the second time when searching.

  @Mew_UFT @release_17G @domain_discovery @project_UFT
  Scenario: verify bops search results are updated properly for the second time when searching.
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I click on Pick Up In-Store
    And I enter zip code "10001" in pick up in-store
    And I click on find store search button
    And I enter zip code "11030" in pick up in-store
    And I click on find store search button
    And again I click on search button and verify first and second time search result
