Feature: Verify Back To Top functionality in Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt
  Scenario: CategoryBrowsePage - Domestic - Verify back to top button is excluded for chanel pages in DOMESTIC mode
    Given I am on CategoryBrowsePage for "1001022" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    And I navigate to bottom of page
    Then I verify that back to top button is not displayed on page

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @migrated_to_sdt @use_regression
  Scenario Outline: CategoryBrowsePage - Domestic - Verify back to top button is displayed on Browse Page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry -  Verify back to top button is not displayed immediately on Browse Page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify Browse Page is navigated to top on click back to top button
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I select back to top button
    Then I verify that Browse page navigated to top of the page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify back to top button is not displayed after navigating from bottom to top of browse page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I navigate to top of page
    Then I verify that back to top button is not displayed on page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |