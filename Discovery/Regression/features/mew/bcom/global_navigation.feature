Feature: Navigating on the Global Navigation Button

  @dsv_mew_sev2 @domain_discovery
  Scenario: Navigating twice on the Global Navigation Button should collapse the Global Navigation Panel
    Given  I visit the mobile web site as a guest user in domestic mode
    When I open the global navigation
    Then I should see global navigation panel
    When I again tab on the global navigation
    Then I should not see global navigation panel