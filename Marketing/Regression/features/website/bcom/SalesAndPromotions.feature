Feature: To validate the sales and promotions links in flyouts

  @dsv_desktop_sev2
  Scenario Outline: Verify sales and promotions links in flyouts are navigating to valid destinations for all categories.
    Given I visit the web site as a guest user
    When I mouse over "<FOB>" category from top navigation
    Then I should validate Sales and Promotions links in flyouts navigate to valid destination
    Examples:
    | FOB |
    | WOMEN |
    | SHOES |
    | HANDBAGS |
    | JEWELRY & ACCESSORIES|
    | MEN                  |
    | KIDS                 |
    | HOME                 |

  @dsv_desktop_sev2
    Scenario: To validate all links in sales and promotions page
      Given I visit the web site as a guest user
      And I mouse over "SALE" category from top navigation
      And I select "See All Promotions" subcategory from flyout menu
      Then All sale links should be navigated to valid destination
