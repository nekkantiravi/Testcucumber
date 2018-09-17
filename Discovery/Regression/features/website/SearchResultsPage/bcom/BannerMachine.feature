# Author: QE
# Date Created : Aug 04, 2017


Feature: Rendering Banner image according to the contexts in search mode

############################### DOMESTIC MODE ##########################################################
  @domain_discovery @use_regression @feature_bm @mode_domestic @priority_medium @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry- Verify the rendering of Banner Machine banner on search page on desktop in ALL modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I select 'single' facet value from 'any' facet section
    Then I should see banner machine data on search results page
    Examples:
      |shopping_mode |
      | Domestic |
      | Iship |
      | Registry|

