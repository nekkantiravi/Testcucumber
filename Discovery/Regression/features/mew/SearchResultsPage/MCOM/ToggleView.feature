Feature: As a user I should be able to see products in different views (grid/list/gallery) in Search pages in all modes

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify the grid, list and gallery view buttons selection in all modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should see toggle view button
    Then I should see the grid view of products in the search page
    When I click on the list view button in the toggle panel
    Then I should see the list view of products in the search page
    When I click on the gallery view button in the toggle panel
    Then I should see the gallery view of products in the search page
    When I click on the grid view button in the toggle panel
    Then I should see the grid view of products in the search page
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | jeans     |
      | registry | Electrics |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify the view user has selected stays even if the user goes to some other page and visits Search page again
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should see toggle view button
    Then I should see the grid view of products in the search page
    When I click on the list view button in the toggle panel
    When I type "<secondkeyword>" in mew search and click enter
    Then I should see the list view as selected
    And I should see the list view of products in the search page
    When I click on the gallery view button in the toggle panel
    And I type "<keyword>" in mew search and click enter
    Then I should see the gallery view of products in the search page
    Examples:
      | mode     | keyword   | secondkeyword |
      | domestic | jeans     | shoes         |
      | iship    | jeans     | shoes         |
      | registry | bathrobes | Electrics     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort order persistence after toggling the views on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    And I select "Price: Low to High" in sort by drop down in mew
    Then I should see sorting order depends on Maximum UPC in a colorway priced product
    When I click on the list view button in the toggle panel
    Then I should see the list view of products in the search page
    And I should see default sort is "Price: Low to High"
    When I click on the gallery view button in the toggle panel
    Then I should see the gallery view of products in the search page
    And I should see default sort is "Price: Low to High"
    When I click on the grid view button in the toggle panel
    Then I should see the grid view of products in the search page
    And I should see default sort is "Price: Low to High"
    Examples:
      | mode     | keyword   |
      | domestic | jeans     |
      | iship    | jeans     |
      | registry | bathrobes |
