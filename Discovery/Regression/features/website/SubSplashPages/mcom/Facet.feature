# Author: Discovery Regression QE Team
# Created Date: 10/10/2017

Feature: Verifying Facets in Category Sub Splash pages

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_subsplash_page @discovery_daily_run
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify copy block media suppressed after facet selection
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I 'should' see Copy Block section on sub splash page
    When I select 1 facet value(s) from 'any' facet sections
    Then I 'should not' see Copy Block section on sub splash page
    When I deselect the selected facet from the overlay
    Then I 'should' see Copy Block section on sub splash page
    Examples:
      | mode     | subcategory          | category               |
      | domestic | MICHAEL Michael Kors | HANDBAGS               |
      | iship    | MICHAEL Michael Kors | HANDBAGS & ACCESSORIES |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_subsplash_page @discovery_daily_run
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify special characters facet selection persistence
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select "<facet_value>" facet value from '<facet_name>' facet section
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode     | subcategory | category | facet_value                | facet_name      |
      | domestic | Activewear  | MEN      | Apple Watch Nike+          | Brand           |
      | iship    | Activewear  | MEN      | Anti-Microbial & Anti Odor | Fabric Property |
