Feature: Add To Wish List functionality in browse page.

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify Add to wish list in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Jeans |
    Then I should be on the browse page
    And I should see Heart Icon for every member product on browse page
    And I click on Heart Icon on product to add to list on browse page
    Then I should see my Heart painted on browse page
    When I click on Heart Icon to remove product from list on browse page

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify Add to wish list in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    Then I should be on the browse page
    And I should see Heart Icon for every member product on browse page
    And I click on Heart Icon on product to add to list on browse page
    Then I should see my Heart painted on browse page
    When I click on Heart Icon to remove product from list on browse page

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify selected wish list persisting after toggle the views in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Jeans |
    Then I should be on the browse page
    And I click on Heart Icon on product to add to list on browse page
    Then I should see my Heart painted on browse page
    And I click on the list view button in the toggle panel
    Then I should see selected wish list is persisted
    And I click on the gallery view button in the toggle panel

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify selected wish list persisting after toggle the views in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Kitchen Gadgets |
    Then I should be on the browse page
    And I click on Heart Icon on product to add to list on browse page
    Then I should see my Heart painted on browse page
    And I click on the list view button in the toggle panel
    Then I should see selected wish list is persisted
    And I click on the gallery view button in the toggle panel