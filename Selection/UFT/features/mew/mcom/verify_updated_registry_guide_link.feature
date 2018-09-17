Feature: As a product owner, I would like to update the left-hand navigation in MEW for Registry.

  @Mew_UFT @release_17G @priority_medium @domain_selection @project_UFT
  Scenario: verify that the left-hand navigation is updated in MEW for Registry.
    Given I visit the mobile web site as a guest user
    And I navigate to wedding registry page
    And I open the global navigation menu
    Then I should see "Registry Guide" category under Gift Category

  @Mew_UFT @release_17G @priority_medium @domain_selection @project_UFT
  Scenario: verify that the user is navigated to correct New URL for Registry Guide.
    Given I visit the mobile web site as a guest user
    And I navigate to wedding registry page
    And I select "Registry Guide" tab in the left nav
    Then I should see correct URL "https://m.macys.com/social/registry-guide/" on Registry Guide page