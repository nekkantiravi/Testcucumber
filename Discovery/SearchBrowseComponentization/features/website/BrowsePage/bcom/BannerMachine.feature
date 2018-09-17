# Author: QE
# Date Created : Aug 04, 2017


Feature: Rendering Banner image according to the contexts in browse mode

############################### DOMESTIC MODE ##########################################################
  @domain_discovery @use_regression @feature_bm @mode_domestic @priority_medium @snbc_comp @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry- Verify the rendering of Banner Machine banner on browse page on desktop in ALL modes
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I select 'single' facet value from 'any' facet section
    Then I should see banner machine data on browse page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |