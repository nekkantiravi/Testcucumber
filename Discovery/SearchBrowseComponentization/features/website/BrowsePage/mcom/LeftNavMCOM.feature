# Author: DISCOVERY QE

Feature: Verify Browse Pages - Left Navigation


  @snbc_comp @use_dsv @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HANDBAGS & ACCESSORIES - Tights, Socks, & Hosiery in DOMESTIC mode
    Given I am on CategoryBrowsePage for "40546" category id in Domestic mode
   #Given I am on Browse Page for "Tights, Socks, & Hosiery" under "HANDBAGS"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @snbc_comp @dsv_category_22 @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Dining & Entertaining in DOMESTIC mode
    Given I am on CategoryBrowsePage for "7498" category id in Domestic mode
    #Given  I am on Browse Page for "Dining & Entertaining" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @snbc_comp @dsv_category_22 @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Furniture in DOMESTIC mode
    Given I am on CategoryBrowsePage for "70056" category id in Domestic mode
    #Given  I am on Browse Page for "Furniture" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Mattresses in DOMESTIC mode
    Given I am on CategoryBrowsePage for "25931" category id in Domestic mode
    #Given  I am on Browse Page for "Mattresses" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Bed & Bath in DOMESTIC mode
    Given I am on CategoryBrowsePage for "7495" category id in Domestic mode
    #Given  I am on Browse Page for "Bed & Bath" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Rugs in DOMESTIC mode
    Given I am on CategoryBrowsePage for "35611" category id in Domestic mode
    #Given  I am on Browse Page for "Rugs" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - KitchenAid in DOMESTIC mode
    Given I am on CategoryBrowsePage for "29422" category id in Domestic mode
    #Given  I am on Browse Page for "KitchenAid" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

    # left nav links are not displayed in production also
  @snbc_comp @dsv_category_4 @artifact_navapp @domain_discovery @priority_high @wip @mode_domestic
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Charter Club in DOMESTIC mode
    Given I am on CategoryBrowsePage for "36896" category id in Domestic mode
    #Given I am on Browse Page for "Charter Club" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page
    # Notes:
    # Do full validation -
    # Under every parent heading child links should be displayed
    # Verify links navigate correctly

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Luggage & Backpacks in DOMESTIC mode
    Given I am on CategoryBrowsePage for "16908" category id in Domestic mode
    #Given  I am on Browse Page for "Luggage & Backpacks" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - HOME - Kitchen in DOMESTIC mode
    Given I am on CategoryBrowsePage for "7497" category id in Domestic mode
    #Given  I am on Browse Page for "Kitchen" under "HOME"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - WOMEN - Plus Sizes in DOMESTIC mode
    Given I am on CategoryBrowsePage for "32147" category id in Domestic mode
    #Given  I am on Browse Page for "All Plus Sizes" under "WOMEN"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - WOMEN - Petite in DOMESTIC mode
    Given I am on CategoryBrowsePage for "18579" category id in Domestic mode
    #Given  I am on Browse Page for "All Petites" under "WOMEN"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - WOMEN - Handbags & Accessories in DOMESTIC mode
    Given I am on CategoryBrowsePage for "26846" category id in Domestic mode
    #Given  I am on Browse Page for "Handbags & Accessories" under "WOMEN"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: Scenario: BrowsePage - Verify Left Hand Nav - MEN - Shirts in DOMESTIC mode
    Given I am on CategoryBrowsePage for "20626" category id in Domestic mode
    #Given  I am on Browse Page for "Shirts" under "MEN"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - MEN - Guys in DOMESTIC mode
    Given I am on CategoryBrowsePage for "43141" category id in Domestic mode
    #Given  I am on Browse Page for "Levi's" under "MEN"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - MEN - All Men's Shoes in DOMESTIC mode
    Given I am on CategoryBrowsePage for "65" category id in Domestic mode
    #Given  I am on Browse Page for "All Men's Shoes" under "MEN"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page

  @snbc_comp @mode_domestic @use_regression @migrated_to_sdt @priority_high @use_dsv @dsv_category_4 @domain_discovery  @dsv_desktop_sev2
  Scenario: BrowsePage - Verify Left Hand Nav - KIDS - Shop All Boys in DOMESTIC mode
    Given I am on CategoryBrowsePage for "61999" category id in Domestic mode
    #Given  I am on Browse Page for "All Boys" under "KIDS"
    And I navigate to random sub categories from Left hand nav links
    Then I verify that navigated to correct subcategory page
