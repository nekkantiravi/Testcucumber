#Version One: B-87193

Feature: If a brand has a GBS, we want the brand name to link to their respective GBS.

  @artifact_navapp @mode_domestic @release_17P @priority_medium @domain_selection @project_UFT @use_regression
  Scenario: Verify the updated brand link on PDP if the brand has GBS
    Given I visit the web site as a guest user
    When I search for "2610079"
    Then I should see updated redirection URL link
    When I select brand name link
    Then I should redirect to the updated URL