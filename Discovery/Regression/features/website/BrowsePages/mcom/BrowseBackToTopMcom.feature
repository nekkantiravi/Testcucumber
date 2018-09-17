Feature: Verify Back To Top functionality in Category Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @mode_domestic @snbc_comp @use_regression @feature_browse_page
  Scenario: CategoryBrowsePage - Domestic - Verify back to top button is excluded for chanel pages in DOMESTIC mode
    Given I am on CategoryBrowsePage for "61916" category id in Domestic mode
    And I navigate to bottom of page
    Then I verify that back to top button is not displayed on page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic - Verify back to top button appears when bottom edge of sub nav passes viewable screen in REGISTRY mode
    Given I am on CategoryBrowsePage for "53629" category id in Registry mode
    When I navigate to bottom of left navigation panel
    Then I verify that back to top button is displayed on page

  @domain_discovery @mode_domestic @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify back to top button is displayed on browse page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry -  Verify back to top button is not displayed immediately on category browse page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @v1 @xbrowser_browse
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify category browse page is navigated to top on click back to top button
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I navigate to bottom of page
    And I select back to top button
    Then I verify that Browse page navigated to top of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |
	  
  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify back to top button is not displayed after navigating from bottom to top of category browse page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I navigate to top of page
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 5449        |
      | Registry      | 53629       |
      | Iship         | 5449        |