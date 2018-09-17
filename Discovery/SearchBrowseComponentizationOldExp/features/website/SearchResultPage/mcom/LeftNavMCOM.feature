Feature: Verify browser back button verification on Search Landing Page

  @use_regression @artifact_navapp @domain_discovery @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Verify LEFT NAV LINKS are displayed in All modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that search result page Facets displayed match with production
    Examples:
      | shopping_mode |keyword    |
      | Domestic      |jeans      |
      | Registry      |dinning    |
      | Iship         |jeans      |

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb @invalid @use_manual
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Veify that we recognize scripts and do not submit a search in such cases
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I add "MISCGCs" zipcode cookie from cookie list
    Then I verify that "Pick Up In Store" facet is listed on left nav
    And I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay with map and stores list
    When I search for zipcode "<script>alert('hi there')</script>" in bops change store dialog
  #Then I verify that City input field should be cleared
    Examples:
      | shopping_mode |
      | Domestic      |
     #| Registry      |
     #| Iship         |