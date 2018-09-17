Feature: Gift cards, search, browse and PDP

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the search related to Gift cards is redirected to current gift card cat splash page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<card>" in mew search and click enter
    Then I should be redirected to current Gift Card Cat splash page
    Examples:
      | card                 |
      | gift card            |
      | e-gift card          |
      | electronic gift card |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify the Deep Link is also redirected to gift card cat splash page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                     |
      | Gift Guides & Gift Cards |
    Then  I should be redirected to current Gift Card Cat splash page

  @domain_mew_discovery @use_mew_regression
  Scenario: Go to VGC browse page and verify details
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                     |
      | Gift Guides & Gift Cards |
    Then  I should be redirected to current Gift Card Cat splash page
    And I tap the e-gift card button
    Then I should be on VGC Browse page
    And I should see Category Name with total quantity
    And I should see filter button
    And I should see sort button
    And I should see only product name associated to the product

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify the functionality of Filter by function
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                     |
      | Gift Guides & Gift Cards |
    Then  I should be redirected to current Gift Card Cat splash page
    And I tap the e-gift card button
    Then I should be on VGC Browse page
    When I click on filter link
    When I select facet name "Occasion"
    And I select multiple facets
    When I click on Apply button
    Then I should see only products in browse page with selected multiple facet values

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the functionality of Sort by function
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                     |
      | Gift Guides & Gift Cards |
    Then  I should be redirected to current Gift Card Cat splash page
    And I tap the e-gift card button
    Then I should be on VGC Browse page
    And I select "<sort_value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on browse page
    Examples:
      | sort_value           |
      | Price: Low to High   |
      | Price: High to Low   |
      | Featured Items       |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify fields for purchasing Gift Card are present
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                     |
      | Gift Guides & Gift Cards |
    Then  I should be redirected to current Gift Card Cat splash page
    And I tap the e-gift card button
    Then I should be on VGC Browse page
    And I navigate to a random PDP page on browse page
    Then I should see the following form fields for purchasing a gift card
      | Amount                 |
      | Recipient's Email      |
      | Your Message(optional) |