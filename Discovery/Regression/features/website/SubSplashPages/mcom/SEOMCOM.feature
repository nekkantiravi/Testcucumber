#Author: Discovery QE
#Date Created: 10/04/2017

Feature: Verify SEO on SubSplash Page

  #Test Case ID: MCOM-77966
  @use_regression @domain_discovery @priority_high @mode_domestic @mode_iship @feature_subsplash_page @discovery_daily_run
  Scenario Outline: CategorySubSplashPage - Verify Tag Cloud is suppressed on Chanel & Coach Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "<page_type>" browse page under "<cat_type>"
    Then I should not see the Popular Searches section above footer
    Examples:
      | page_type | cat_type |
      | CHANEL    | BEAUTY   |
      | COACH     | HANDBAGS |

  #Testlink-MCOM-96562 Vone - RT-07331
  @use_regression @domain_discovery @priority_medium @mode_domestic @feature_subsplash_page @discovery_daily_run @xbrowser_subsplash
  Scenario: SubSplashPage - Verify Tag Cloud display in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When  I navigate to the "Activewear" sub splash page under "WOMEN"
    Then I should see the Popular Searches section above footer
