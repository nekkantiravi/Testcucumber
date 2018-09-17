Feature: As a product owner, I would like to ensure expiring soon page works

  @Mew_UFT @release_17O @domain_marketing @project_UFT @use_regression @mew_regression
  Scenario:  verify expiring soon page post clicking expiring soon tab of Deal and promotions page.
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Deals    |
    And I click on expiring soon tab