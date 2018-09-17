Feature: Verify copy block functionality in browse page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the copy block functionality in Boots Browse page in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop          |
      | Shoes         |
      | Women's Shoes |
      | Boots         |
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I select 2 page on browse page using mobile website
    Then I should not see copy block on the page
    When I select 1 page on browse page using mobile website
    Then I should see copyblock displayed in UI
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the copy block functionality in Pants Browse page in DOMESTIC and Iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Shoes |
      | Men   |
      | Pants |
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I select 2 page on browse page using mobile website
    Then I should not see copy block on the page
    When I select 1 page on browse page using mobile website
    Then I should see copyblock displayed in UI
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    Examples:
      | mode     |
      | domestic |
      | iship    |