Feature: Verify Item Count functionality in Category Browse Page contents in DOMESTIC, ISHIP and REGISTRY mode
 
#  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
#  Scenario Outline: CategoryBrowsePage - Verify item count selection for 3 column in DOMESTIC mode from top
#    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
#    And I verify that item count buttons in page
#    Then I verify that "60" item count option and respective number of products on page
#    When I filter the result set to show "120" products from "top"
#    Then I verify that "120" item count option and respective number of products on page
#    Examples:
#     | shopping_mode |Category_Name   |FOB     |
#     | Domestic      |Shorts          |Men     |
#     | Registry      |Bakeware        |Kitchen |
#     | Iship         |Boots           |Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify item count selection for 3 column in DOMESTIC mode from bottom
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I verify that item count buttons in page
    When I filter the result set to show "60" products from "bottom"
    Then I verify that "60" item count option and respective number of products on page
    When I filter the result set to show "120" products from "bottom"
    Then I verify that "120" item count option and respective number of products on page
    Examples:
      | shopping_mode | Category_Name | FOB     |
      | Domestic      | Shorts        | Men     |
      | Registry      | Bakeware      | Kitchen |
      | Iship         | Boots         | Shoes   |