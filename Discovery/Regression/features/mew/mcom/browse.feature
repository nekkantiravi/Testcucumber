Feature: Category browse page


  @dsv_mew_sev2 @domain_discovery
  Scenario: Verify navigating to boots browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Shoes   |
      | Women's Shoes |
      | Boots |
    Then I should see the "category_browse" Page
    Then I should see browse header label as boots on SRP page