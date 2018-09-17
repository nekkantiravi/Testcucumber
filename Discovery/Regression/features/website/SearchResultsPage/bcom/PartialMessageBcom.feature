#Author: Discovery QE

Feature: Verify Partial match messaging on Search Results Page - DOMESTIC, ISHIP and REGISTRY modes

  @unified_navigation_B18282 @use_regression @domain_discovery @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the updated messaging when the user lands on a general search page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that message "We couldn't find an exact match for <keyword>" in search landing page header
  Examples:
  | shopping_mode | keyword                       |
  | Domestic      | cream polo belts              |
  | Registry      | silver color cookware         |
  | Iship         | engagement cocktail dresses   |

  @unified_navigation_B16208 @use_regression @domain_discovery @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the partial message for the given keyword displayed to the user
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that message "We couldn't find an exact match for <keyword>" in search landing page header
    And I verify that search message "Showing results for <partial_match_text>" is displayed
    When I select any search term link
    And I select browse 'back' button
    Then I verify that message "We couldn't find an exact match for <keyword>" in search landing page header
    When I select 'single' facet value from 'any' facet section
    Then I verify that message "We couldn't find an exact match for <keyword>" in search landing page header
    When I select random product from thumbnail grid
    And I select browse 'back' button
    Then I verify that message "We couldn't find an exact match for <keyword>" in search landing page header
  Examples:
  | shopping_mode | keyword                     | partial_match_text                            |
  | Domestic      | cream polo belts            | polo belts cream polo                          |
  | Registry      | yellow glass pan          | Yellow Pan Yellow Glass                     |
  | Iship         | engagement cocktail dresses | Cocktail Dresses Engagement Cocktail          |