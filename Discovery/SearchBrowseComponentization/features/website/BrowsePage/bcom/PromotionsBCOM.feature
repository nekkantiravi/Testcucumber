#Author: Discovery QE
#Date Created: 04/10/2017


Feature: Verify Category Browse Page Promotions in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the promotions on thumbnail in all mode
    Given I am on CategoryBrowsePage for "21683" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I select a product having badge text
    Then I verify that promotional message is displayed under product thumbnail
    Examples:
      | Site_Mode |
      | Domestic  |
     #| Registry  | no promotion is displayed for registry need test data
      | Iship     |