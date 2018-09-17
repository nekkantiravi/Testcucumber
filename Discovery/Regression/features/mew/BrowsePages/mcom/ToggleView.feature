Feature: As a user I should be able to see products in different views (grid/list/gallery) in browse pages in all modes

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify the grid, list and gallery view buttons selection in all modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I should be on the browse page
    Then I should see toggle view button
    Then I should see the grid view of products in the browse page
    When I click on the list view button in the toggle panel
    Then I should see the list view of products in the browse page
    When I click on the gallery view button in the toggle panel
    Then I should see the gallery view of products in the browse page
    When I click on the grid view button in the toggle panel
    Then I should see the grid view of products in the browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify the view user has selected stays even if the user goes to some other page and visits Search page again
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I should be on the browse page
    Then I should see toggle view button
    Then I should see the grid view of products in the browse page
    When I click on the list view button in the toggle panel
    And I navigate back to home page using mobile website
    And I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Pants        |
    And I should be on the browse page
    Then I should see the list view as selected
    Then I should see the list view of products in the browse page
    When I click on the gallery view button in the toggle panel
    And I navigate back to home page using mobile website
    And I navigate the global navigation menu as follows:
      | Shop        |
      | Shoes       |
      | Men's Shoes |
      | Boots       |
    And I should be on the browse page
    Then I should see the gallery view as selected
    Then I should see the gallery view of products in the browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: BrowsePage - Verify sort order persistence after toggling the views on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should be on the browse page
    And I select "Price: Low to High" in sort by drop down in mew
    Then I should see sorting order depends on Maximum UPC in a colorway priced product
    When I click on the list view button in the toggle panel
    Then I should see the list view of products in the browse page
    And I should see default sort is "Price: Low to High"
    When I click on the gallery view button in the toggle panel
    Then I should see the gallery view of products in the browse page
    And I should see default sort is "Price: Low to High"
    When I click on the grid view button in the toggle panel
    Then I should see the grid view of products in the browse page
    And I should see default sort is "Price: Low to High"
    Examples:
      | mode     |
      | domestic |
      | iship    |

    ### Registry ###

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify the grid, list and gallery view buttons selection in all modes
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    And I should be on the registry browse page
    Then I should see toggle view button
    Then I should see the grid view of products in the browse page
    When I click on the list view button in the toggle panel
    Then I should see the list view of products in the browse page
    When I click on the gallery view button in the toggle panel
    Then I should see the gallery view of products in the browse page
    When I click on the grid view button in the toggle panel
    Then I should see the grid view of products in the browse page

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify the view user has selected stays even if the user goes to some other page and visits browse page again in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Bed & Bath      |
      | Bath            |
      | All Bath        |
    Then I should see toggle view button
    And I should see the grid view as selected
    Then I should see the grid view of products in the browse page
    When I click on the list view button in the toggle panel
    And I navigate the global navigation menu as follows:
      | Shower Curtains |
    Then I should see the list view as selected
    Then I should see the list view of products in the browse page

  @domain_mew_discovery @use_mew_regression
  Scenario: BrowsePage - Verify sort order persistence after toggling the views on browse page
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should be on the registry browse page
    And I select "Price: Low to High" in sort by drop down in mew
    Then I should see sorting order depends on Maximum UPC in a colorway priced product
    When I click on the list view button in the toggle panel
    Then I should see the list view of products in the browse page
    And I should see default sort is "Price: Low to High"
    When I click on the gallery view button in the toggle panel
    Then I should see the gallery view of products in the browse page
    And I should see default sort is "Price: Low to High"
    When I click on the grid view button in the toggle panel
    Then I should see the grid view of products in the browse page
    And I should see default sort is "Price: Low to High"
