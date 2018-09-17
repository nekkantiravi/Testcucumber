#Author: Discovery QE

Feature: Verify Partial match messaging on Search Results Page - DOMESTIC, ISHIP and REGISTRY modes

  @unified_navigation_B18282 @release_15H @use_regression @artifact_navapp @domain_discovery @mode_domestic @please_automate
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the updated messaging when the user lands on a general search page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that message "We couldn't find an exact match for <keyword>" in search landing page header
  Examples:
  | shopping_mode | keyword                       |
  | Domestic      | red gucci belt                |
  | Registry      | silver color cookware         |
  | Iship         | engagement cocktail dresses   |

  @unified_navigation_B16208 @release_15H @use_regression @artifact_navapp @domain_discovery @mode_domestic @please_automate
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the partial message for the given keyword displayed to the user
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that message "We couldn't find an exact match for <keyword>" in search landing page header
    And I verify that search message "Showing results for <partial_match_text>" is displayed
    When I select any search term link
    And I select browse 'back' button
    Then I verify that message "We couldn't find an exact match for red gucci belt" in search landing page header
    When I select 'multiple' facet value from 'any' facet sections
    And I click on clear all button
    Then I verify that message "We couldn't find an exact match for red gucci belt" in search landing page header
    When I select random product from thumbnail grid
    And I select browse 'back' button
    Then I verify that message "We couldn't find an exact match for red gucci belt" in search landing page header
  Examples:
  | shopping_mode | keyword               | partial_match_text                            |
  | Domestic      | red gucci belt        | red gucci red belt                            |
  | Registry      | silver color cookware |  silver cookware silver color color cookware  |
  | Iship         | silver color cookware | silver cookware silver color color cookware   |