# Author: DISCOVERY QE  

Feature: Verify CopyBlock and TagCloud In SLP Page - DOMESTIC

  @use_regression @artifact_discoveryPagesUI @domain_discovery @priority_high @use_seo @snbc_comp @feature_slp
  Scenario Outline: SLP - Verify CopyBlock display in DOMESTIC mode
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then I verify that copy block is displayed 
      Examples:
        | category_id |
        | 75686       |
        | 73693       |

  @use_regression @domain_discovery @priority_medium @mode_domestic @feature_subsplash_page @discovery_daily_run @feature_slp
  Scenario Outline: SLP - Verify Tag Cloud display in DOMESTIC mode
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then I should see the Popular Searches section above footer
    Examples:
      | category_id |
      | 75686       |
      | 73693       |
