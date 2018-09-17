Feature: Navigating on the Global Navigation Button

  @dsv_mew_sev1 @domain_discovery
  Scenario: Navigating on the Global Navigation Button on Home Page
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    Then I should see global navigation panel