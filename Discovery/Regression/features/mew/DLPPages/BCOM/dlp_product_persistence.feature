Feature: Product persistence on designer page when navigating back from PDP

### Domestic and Iship ###

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence on designer page when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I scroll to 18th product on DLP page
    And I click on 18th product on DLP page
    Then I should be redirected to PDP page using mobile website
    When I click on back button on PDP page and navigate to DLP page
    And I should be returned to the same product position on DLP page
    Examples:
      | mode     | designer |
      | domestic | Adidas   |
      | iship    | 1.STATE  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence by pagination on designer page when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And pagination should be displayed and defaulted to first page
    When I navigate to next page on DLP page
    And I scroll to 13th product on DLP page
    And I click on 13th product on DLP page
    Then I should be redirected to PDP page using mobile website
    And I click on back button on PDP page and navigate to DLP page
    Then I should be returned to the same product position on DLP page
    Examples:
      | designer          | mode     |
      | Adidas            | domestic |
      | 7 For All Mankind | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence on designer page when navigating back from PDP and refresh the page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "DESIGNERS" page
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I scroll to 18th product on DLP page
    And I click on 18th product on DLP page
    Then I should be redirected to PDP page using mobile website
    When I click on back button on PDP page and navigate to DLP page
    And I should be returned to the same product position on DLP page
    And I refresh the page
    Then I should be returned to the same product position on DLP page
    Examples:
      | designer          | mode     |
      | 1.STATE           | domestic |
      | 7 For All Mankind | iship    |

  ## Registry

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence on designer page when navigating back from PDP in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | All Brands            |
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I scroll to 18th product on DLP page
    And I click on 18th product on DLP page
    Then I should be redirected to PDP page using mobile website
    When I click on back button on PDP page and navigate to DLP page
    And I should be returned to the same product position on DLP page
    Examples:
      | designer            |
      | kate spade new york |
      | Amalia              |
      | Bloomingdale's      |


  @domain_mew_discovery @use_mew_regression
  Scenario: Verify product persistence by pagination on designer page when navigating back from PDP
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | All Brands            |
    And I select a "Bloomingdale's" from the list
    Then I should be on DLP page
    And pagination should be displayed and defaulted to first page
    When I navigate to next page on DLP page
    And I scroll to 13th product on DLP page
    And I click on 13th product on DLP page
    Then I should be redirected to PDP page using mobile website
    And I click on back button on PDP page and navigate to DLP page
    Then I should be returned to the same product position on DLP page


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify product persistence on designer page when navigating back from PDP and refresh the page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Add Gifts to Registry |
      | All Brands            |
    And I select a "<designer>" from the list
    Then I should be on DLP page
    And I scroll to 18th product on DLP page
    And I click on 18th product on DLP page
    Then I should be redirected to PDP page using mobile website
    When I click on back button on PDP page and navigate to DLP page
    And I should be returned to the same product position on DLP page
    Examples:
      | designer            |
      | Bloomingdale's      |
      | kate spade new york |
      | Amalia              |