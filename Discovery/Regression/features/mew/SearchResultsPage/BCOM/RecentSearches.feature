Feature: BCOM - Verify recent search functionality

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify Recent Searches text in recent search panel in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search with below keywords
      | Jeans           |
      | Shirts          |
      | rings           |
      | Clearance shoes |
      | Electrics       |
      | lenox           |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify Recent Searches text in recent search panel in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I search with below keywords
      | plates   |
      | cookware |
      | kitchen  |
    Then I should be on the registry search results page
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel
