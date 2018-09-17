# Author: Discovery QE

Feature: Verify Search Zero Results Page contents in DOMESTIC mode

  @use_regression @unifiednavigation_1517 @artifact_navapp @domain_discovery @priority_low @use_regression_3 @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify For a numerical search with no results, show standard zero results page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the search results should be in zero results page
  Examples:
  | shopping_mode | keyword |
  | Domestic      | 1145   |
  | Iship         | 1145   |
  | Registry      | 1145    |

  @project_snb @add_missing_scope @artifact_navapp @domain_discovery @priority_medium @mode_domestic @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the MCOM  zero results page with special character in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that the message "We found 0 result for <keyword>" in zero results page header
  Examples:
  | shopping_mode | keyword   |
  | Domestic      | qrs&fd    |
  | Iship         | xtyz&asdf |
  | Registry      | xtyz&asdf |


