Feature: Category browse page

  @dsv_mew_sev2 @domain_discovery
  Scenario: Verify navigating to boots browse page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Shoes|
      |Boots|
    Then I should see the "category_browse" Page
    Then I should see boots on browse header label SRP page