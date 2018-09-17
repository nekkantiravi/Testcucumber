#Author: Discovery SNBC QE
#Date Created: 05/09/2017

Feature: Verify SubCategory Pages - Top Banner Ad

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario:  SubCategoryPage - Verify Top Banner Ad - HOME - Kitchen in DOMESTIC mode
    Given I am on Browse Page for "Kitchen" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Kitchen" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario:   SubCategoryPage - Verify Top Banner Ad - HOME - Dining & Entertaining in DOMESTIC mode
    Given I am on Browse Page for "Dining & Entertaining" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Dining & Entertaining" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - Outdoor & Patio Furniture in DOMESTIC mode
    Given I am on Browse Page for "Outdoor & Patio Furniture" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Outdoor & Patio Furniture" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - Mattresses in DOMESTIC mode
    Given I am on Browse Page for "Mattresses" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Mattresses" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - SlipCovers in DOMESTIC mode
    Given I am on Browse Page for "SlipCovers" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "SlipCovers" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - Rugs in DOMESTIC mode
    Given I am on Browse Page for "Rugs" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Rugs" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - Window Treatments in DOMESTIC mode
    Given I am on Browse Page for "Window Treatments" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Window Treatments" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - Luggage & Backpacks in DOMESTIC mode
    Given I am on Browse Page for "Luggage & Backpacks" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Luggage & Backpacks" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - Lighting & Lamps in DOMESTIC mode
    Given I am on Browse Page for "Lighting & Lamps" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Lighting & Lamps" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  #Added @wip tag because expected category is not available in prduction site also
  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @priority_high @wip @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - KIDS - Skirts in DOMESTIC mode
    Given I am on Browse Page for "Skirts" under "KIDS"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Skirts" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  # Added wip tag data is not available in production site also
  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @wip @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HOME - Holiday Lane in DOMESTIC mode
    Given I am on Browse Page for "Holiday Lane" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Holiday Lane" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage- Verify Top Banner Ad - WOMEN - Plus Sizes in DOMESTIC mode
    Given I am on Browse Page for "All Plus Sizes" under "WOMEN"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "All Plus Sizes" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - WOMEN - Petite in DOMESTIC mode
    Given I am on Browse Page for "All Petites" under "WOMEN"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "All Petites" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_9 @mode_domestic @review_stability @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - WOMEN - Lingerie & Shapewear in DOMESTIC mode
    Given I am on Browse Page for "Lingerie & Shapewear" under "WOMEN"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Lingerie & Shapewear" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - MEN - Big & Tall in DOMESTIC mode
    Given I am on Browse Page for "Big & Tall" under "MEN"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Big & Tall" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - MEN - Guys in DOMESTIC mode
    Given I am on Browse Page for "Guys" under "MEN"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "Guys" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - MEN - All Men's Shoes in DOMESTIC mode
    Given I am on Browse Page for "All Men's Shoes" under "MEN"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "All Men's Shoes" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @use_regression @artifact_navapp @domain_discovery @use_prod @priority_high @use_regression_9 @mode_domestic @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - KIDS - Shop All Baby in DOMESTIC mode
    Given I am on Browse Page for "All Baby" under "KIDS"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "All Baby" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  @dsv_category_9 @artifact_navapp @domain_discovery @use_prod @priority_high @mode_domestic @use_regression_retired_16B @snbc_comp
  Scenario: SubCategoryPage - Verify Top Banner Ad - HANDBAGS - All Handbags in DOMESTIC mode
    Given I am on Browse Page for "All Handbags" under "HANDBAGS"
    And I modify the url to get in to snbc experiment
    Then I verify top banner is displayed on "All Handbags" catsplash page
    # Notes:
    # Do full top banner validation - should not display any broken image or link

  #MCOM-92035 MCOM-92031
  @domain_discovery @mustpass @use_regression @use_regression_9 @mode_domestic
  Scenario: Browse page - Verify the top banner on cat browse page in DOMESTIC mode
    Given I am on CategoryBrowsePage for "20640" category id in Domestic mode
    #And I navigate to "Polos" browse page from cat splash page
    Then I verify top banner is displayed on "Polos" browse page
    # Notes:
    # Description: Verify the top banner on cat browse page
    # Steps:
    # 1. Navigate to any Category Browse Page E.g.: 'Men' --> 'Polos'
    #
    # Expected Results:
    # 1. Verify that top banner should be displayed on cat browse page.

  #MCOM-92035 MCOM-92031
  @domain_discovery @mustpass @use_regression @use_regression_9 @mode_domestic
  Scenario: Browse page - Verify the top banner on cat browse page Dresses in DOMESTIC mode
    Given I am on CategoryBrowsePage for "5449" category id in Domestic mode
    #And I navigate to "Polos" browse page from cat splash page
    Then I verify top banner is displayed on "Polos" browse page
    # Notes:
    # Description: Verify the top banner on cat browse page
    # Steps:
    # 1. Navigate to any Category Browse Page E.g.: 'Women' --> 'Dresses'
    #
    # Expected Results:
    # 1. Verify that top banner should be displayed on cat browse page.