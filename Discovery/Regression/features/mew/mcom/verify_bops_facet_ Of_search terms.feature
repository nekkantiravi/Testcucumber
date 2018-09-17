Feature: As a product owner, I would like to ensure that across all search terms, the BOPS facet is displaying across MEW 

  @Mew_UFT @release_17K @domain_discovery @project_UFT
  Scenario Outline: verify all search terms, the BOPS facet is displaying across MEW
    Given I visit the mobile web site as a guest user
    And I search using mobile website for "<search_terms>"
    And I click filter of search and browse page
    And I verify "Pick Up In-Store" on sort and filter page
  Examples:
    | search_terms |
    | alfani       |
    |Michael Kors  |

