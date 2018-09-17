Feature: Verify CatSplash Pages - Sub Ads

  @preview_desktop
  Scenario Outline: CategorySplashPage - Verify Sub Ads validation for each categories DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "<category>" category page
    Then I verify that Sub Ads of "<category>" should be displayed on cat splash page
    Examples:
      | category   |
      | WOMEN      |
      | MEN        |
      | HOME       |
      | BED & BATH |
      | SHOES      |
      | HANDBAGS   |
      | KIDS       |
      | JUNIORS    |
      | JEWELRY    |
      | WATCHES    |
      | BEAUTY     |

