Feature: Product persistence on browse page when navigating back from PDP

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence on browse pages
    Given I visit the mobile web site as a guest user in <mode> mode
    And I navigate the global navigation menu as follows:
      | MEN   |
      | Pants |
    Then I should be on the browse page
    And I scroll to 18th product on browse page
    And I click on 18th product on browse page
    Then I should be redirected to PDP page using mobile website
    When I click on back button on PDP page and navigate to browse page
    And I should be returned to the same product position on browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: BCOM BrowsePage - Verify product persistence on browse pages in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    And I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | Kitchen               |
    Then I should be on the browse page
    And I scroll to 18th product on browse page
    And I click on 18th product on browse page
    Then I should be redirected to PDP page using mobile website
    When I click on back button on PDP page and navigate to browse page
    And I should be returned to the same product position on browse page
