Feature: Verify Back To Top functionality in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15F @mode_domestic @chanel_regression @priority_medium @project_snb
  Scenario: SearchResultsPage - Domestic - Verify back to top button is excluded for chanel pages in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "chanel makeup"
    And I navigate to bottom of page
    Then I verify that back to top button is not displayed on page

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15F @mode_registry @priority_medium @project_snb
  Scenario: SearchResultsPage - Domestic - Verify back to top button appears when bottom edge of sub nav passes viewable screen in REGISTRY mode
    Given I am on SearchResultsPage for "living room" in REGISTRY mode
    When I navigate to bottom of left navigation panel
    Then I verify that back to top button is displayed on page

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15F @mode_domestic @chanel_regression @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic - Verify back to top button is displayed on Search results page
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
    |shopping_mode|
    |domestic|
    |iship   |
    |registry|
