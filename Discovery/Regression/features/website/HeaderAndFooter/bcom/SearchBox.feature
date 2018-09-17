Feature: BCOM :: Search box filed scenarios

  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic
  Scenario Outline: CategorySplashPage - Domestic|Iship|Registry - Verify accessibility label on global search field
    Given I am on CatSplash Page for "<category>" on "<shopping_mode>" mode
    Then the search field should have a label with the text "Enter Keyword or Web ID"
    Examples:
      | shopping_mode | category |
      | domestic      | HANDBAGS |
      | iship         | WOMEN    |
      | registry      | KITCHEN  |


  @use_regression @priority_high @artifact_navapp @domain_discovery @bat_next @bat_refactored_cd_next @release_13J @use_regression_1 @mode_domestic
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify accessibility label on global search field
    Given I visit the web site as a "guest" user in "<mode>" mode
    When I navigate to the "<subcategory>" browse page under "<category>"
    Then the search field should have a label with the text "Enter Keyword or Web ID"
    Examples:
      | mode     | subcategory | category   |
      | site     | Shorts      | MEN        |
      | iship    | Shorts      | MEN        |
      | registry | Lighting    | HOME DECOR |