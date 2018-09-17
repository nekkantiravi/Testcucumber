Feature: Product persistence on browse page when navigating back from PDP

  ### Domestic and Iship ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence on browse page when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I should be on the browse page
    And I scroll to 18th product on browse page
    And I click on 18th product on browse page
    When I click on back button on PDP page and navigate to browse page
    And I should be returned to the same product position on browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |

    ### Registry ###

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify product persistence on registry browse page when navigating back from PDP
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    And I should be on the browse page
    And I scroll to 18th product on browse page
    And I click on 18th product on browse page
    When I click on back button on PDP page and navigate to browse page
    And I should be returned to the same product position on browse page