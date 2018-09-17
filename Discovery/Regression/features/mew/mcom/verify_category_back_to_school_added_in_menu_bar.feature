Feature: As a producer, I would like to add back to school category in the mobile menu bar.

  @Mew_UFT @release_17M @domain_discovery @project_UFT @mew_regression
  Scenario: verify that back to school category in the mobile menu bar is added
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      |   Shop          |
      | Back To School  |
    Then I Should see the correct URL "social/back-to-school/"