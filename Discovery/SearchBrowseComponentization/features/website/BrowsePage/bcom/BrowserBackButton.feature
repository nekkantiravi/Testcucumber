Feature: Verify browser back button verification on Category Browse Page 

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '90' item count option in 3 column grid view in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that item count buttons in page
    When I filter the result set to show "90" items per page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '180' item count option in 3 column grid view in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that item count buttons in page
    When I filter the result set to show "180" items per page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '90' item count option and pagination in 3 column grid view in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that item count buttons in page
    When I filter the result set to show "90" items per page
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    And I verify that navigated to page 2 on search result page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify customer view persistence with '180' item count option and pagination in 3 column grid view in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that item count buttons in page
    When I filter the result set to show "180" items per page
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    And I verify that navigated to page 2 on search result page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |