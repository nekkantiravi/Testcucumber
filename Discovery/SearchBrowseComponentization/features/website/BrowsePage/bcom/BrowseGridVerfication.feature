# Author: BGV QE Team

Feature:C2 :: P2 :: BCOM :: Browse Grid Variations automation scenarios Search Result page - for Search Results Page


  @use_regression @domain_discovery @mode_domestic @mode_registry @mode_iship @priority_medium @snbc_comp @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify breadcrumb, static quick peek icon, alt image
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that the breadcrumb is not displayed in search result page
    And I verify that the quick peek icon on product image without hovering on product image
    And I verify that the default product thumbnail and alt image
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @use_regression @priority_medium @mode_domestic @mode_registry @mode_iship @migrated_to_sdt @snbc_comp
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify Quick Peek button selection and overlay close button selection
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    And I verify that default attributes on new quick peek overlay
    When I select X button on new quick peek overlay
    Then I verify that quick peek is not displayed
    When I select 'multiple' facet value from 'any' facet sections
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
    And I verify that default attributes on new quick peek overlay
    When I select anywhere outside of new quick peek overlay
    Then I verify that quick peek is not displayed
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @use_regression @priority_medium @mode_domestic @mode_registry @mode_iship @migrated_to_sdt @snbc_comp
  Scenario Outline:: CategoryBrowsePage - Domestic|Iship|Registry - Verify quick peek tool tip is displaying when hovered on quick peek icon page in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I verify that the quick peek icon on product image without hovering on product image
    Then I should see quick peek tool tip "Quick Peek" message on page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |

  @domain_discovery @use_regression @priority_medium @mode_domestic @mode_registry @mode_iship @migrated_to_sdt @snbc_comp
  Scenario Outline:: CategoryBrowsePage - Domestic|Iship|Registry -Verify video icon is suppressed below the product thumbnail page in ALL mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I verify that the quick peek icon on product image without hovering on product image
    Then I Verify video icon is not displayed below product thumbnail on page
    Examples:
      | shopping_mode | Category_id|
      | Domestic      | 21683      |
      | Registry      | 8203       |
      | Iship         | 21683      |