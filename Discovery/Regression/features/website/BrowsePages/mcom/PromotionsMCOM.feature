#Author: Discovery QE

Feature: Verify Category Browse Page Promotions - Badge Text

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high @discovery_daily_run @xbrowser_browse
  Scenario Outline: CategoryBrowsePage - Domestic|Registry - Verify that the PROMO text and promo popup on thumbnail in all mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select a product having badge text
    Then I verify that promo text and promo popup is displayed under product thumbnail
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 30077       |
      | Registry      | 70242       |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @priority_high
  Scenario Outline: CategoryBrowsePage - Iship - Verify that the PROMO text and promo popup on thumbnail in all mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select a product having badge text
    Then I verify that promo text and promo popup is displayed under product thumbnail
    Examples:
      | shopping_mode | Category_id |
      | Iship         | 10835       |