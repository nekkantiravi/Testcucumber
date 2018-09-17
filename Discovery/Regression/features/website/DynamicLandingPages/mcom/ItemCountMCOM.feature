Feature: Verify Item Count functionality in DynamicLanding Page contents in DOMESTIC and ISHIP mode

#  @domain_discovery @priority_medium @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
#  Scenario Outline: DynamicLandingPage - Verify item count selection in DOMESTIC & ISHIP mode from top
#    Given I am on DynamicLandingPage in "<shopping_mode>" mode
#    And I verify that item count buttons in page
#    Then I verify that "60" item count option and respective number of products on page
#    When I filter the result set to show "120" products from "top"
#    Then I verify that "120" item count option and respective number of products on page
#    Examples:
#      | shopping_mode |
#      | Domestic      |
#      | Iship         |

  @domain_discovery @priority_medium @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario Outline: DynamicLandingPage - Verify item count selection in DOMESTIC & ISHIP mode from bottom
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I verify that item count buttons in page
    When I filter the result set to show "60" products from "bottom"
    Then I verify that "60" item count option and respective number of products on page
    When I filter the result set to show "120" products from "bottom"
    Then I verify that "120" item count option and respective number of products on page
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
