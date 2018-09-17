Feature: BCOM - Verify recent search functionality

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Recent Searches text in recent search panel in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I search with below keywords
      | Jeans           |
      | Shirts          |
      | Pants           |
      | red dresses     |
      | Clearance shoes |
      | Electrics       |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Recent Searches text in recent search panel in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I search with below keywords
      | plates  |
      | kitchen |
      | lenox   |
      | beds    |
      | cups    |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify Recent Searches text in recent search panel in international mode
    Given I visit the mobile web site as a guest user in iship mode
    When I search with below keywords
      | Jeans           |
      | Shirts          |
      | Pants           |
      | red dresses     |
      | Clearance shoes |
    And I type single character in mew search box
    Then I should see the "recent searches" text in the panel
    And I should see recent searches in recent search panel