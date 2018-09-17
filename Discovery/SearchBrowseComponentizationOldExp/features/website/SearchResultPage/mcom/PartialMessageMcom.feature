#Author: Discovery QE

Feature: Verify Partial match messaging on Search Results Page - DOMESTIC, ISHIP and REGISTRY modes


  @release_15H @unifiednavigation_B-17070 @use_regression_3 @use_regression @artifact_navapp @domain_discovery @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry  - Verify partial match message in domestic search landing page when there is only one combination to the search term entered in DOMESTIC mode
    Given I am on SearchResultsPage for "asdasdfg <keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <keyword>" in search landing page header
    And I verify that search message "There were 0 matches" is displayed
  Examples:
  | shopping_mode | keyword |
  | Domestic      | nike    |
  | Iship         | nike    |
  | Registry      | plates  |

  @release_15H @unifiednavigation_B-17072 @use_regression_3 @use_regression @artifact_navapp @domain_discovery @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify partial match message on search landing page when there are multiple combinations to the search term entered
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I should be in Search Landing page
    And I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    And I verify that search message "There were 0 matches" is displayed
    When I select any search term link
    And I select browse 'back' button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    When I select 'single' facet value from 'any' facet section
    And I click on clear all button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
    When I select random product from thumbnail grid
    And I select browse 'back' button
    Then I verify that message "We found 'n' results for <partial_match_text>" in search landing page header
  Examples:
  | shopping_mode | keyword                     | partial_match_text                  |
  | Domestic      | red gucci belt              | red belt , red gucci                |
  | Iship         | dress lenox                 | dress , lenox                       |
  | Registry      | yellow glassware silverware | yellow glassware , yellow silverware|

  @release_15H @unifiednavigation_B-17073 @use_regression_3 @use_regression @artifact_navapp @domain_discovery @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify search message is not displayed with quotations
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <keyword>" in search landing page header
    And I verify that quotations are not displayed for search keyword in search message
  Examples:
  | shopping_mode | keyword |
  | Domestic      | jeans   |
  | Iship         | jeans   |
  | Registry      | cups    |

  @release_15H @unifiednavigation_B-17073 @use_regression_3 @use_regression @artifact_navapp @domain_discovery @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify search message in zero search results page
    Given I am on SearchResultsPage for "xytz" in <shopping_mode> mode
    Then I verify that Zero Search Result page is displayed
    And I verify that message "We found 'n' result for xytz" in search landing page header
    And I verify that quotations are not displayed for search keyword in search message
  Examples:
  | shopping_mode |
  | Domestic      |
  | Iship         |
  | Registry      |

  @release_15H @unifiednavigation_B-17074 @use_regression_3 @use_regression @artifact_navapp @domain_discovery @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify search message
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that message "We found 'n' results for <keyword>" in search landing page header
  Examples:
  | shopping_mode | keyword |
  | Domestic      | jeans   |
  | Iship         | jeans   |
  | Registry      | cups    |