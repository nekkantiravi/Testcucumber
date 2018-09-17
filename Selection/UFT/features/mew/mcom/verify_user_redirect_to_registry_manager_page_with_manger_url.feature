Feature: As a product owner, I would like to ensure users are redirected to registry manager page with registry manager page URL

  @Mew_UFT @release_17L @priority_medium @domain_selection @project_UFT
  Scenario: verify that users are redirected to registry manager page with registry manager page URL when user selects wedding registry via GNV.
    Given I visit the mobile web site as a registry user
    And I open the global navigation menu
    And I navigate to wedding registry page
    And URL should contains "registry/wedding/registrymanager" on registry manager page URL
