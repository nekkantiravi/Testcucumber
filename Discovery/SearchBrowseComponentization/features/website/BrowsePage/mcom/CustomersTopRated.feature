#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify SubCategory Pages - Customers Top Rated

  # Customers Top Rated section data not available for 'Home -> Kitchen'. Hence we are adding @wip tag for this as backup.
  @snbc_backlog @snbc_descope @dsv_category_17 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2 @wip @use_wip
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Kitchen
    Given I am on Browse Page for "Kitchen" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_1 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Dining & Entertaining
    Given I am on Browse Page for "Dining & Entertaining" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_16 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Furniture
    Given I am on Browse Page for "Furniture" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_17 @use_regression @artifact_navapp @domain_discovery @priority_high @defect_ECOMSST-42102 @wip @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Apartment Living
    Given I am on Browse Page for "Apartment Living" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_1 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Mattresses
    Given I am on Browse Page for "Mattresses" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_16 @use_regression @artifact_navapp @domain_discovery @priority_high @defect_ECOMSST-42102 @wip @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Outdoor Furniture
    Given I am on Browse Page for "Outdoor Furniture" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

   # Added wip tag because expected Customer's Top Rated section is not available in production site
  @snbc_backlog @snbc_descope @dsv_category_17 @artifact_navapp @domain_discovery @priority_high @wip
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Rugs
    Given I am on Browse Page for "Rugs" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_1 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Window Treatments
    Given I am on Browse Page for "Window Treatments" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_16 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Luggage & Backpacks
    Given I am on Browse Page for "Luggage & Backpacks" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_17 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Lighting & Lamps
    Given I am on Browse Page for "Lighting & Lamps" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_1 @use_regression @artifact_navapp @domain_discovery @priority_high @defect_ECOMSST-42102 @wip @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Cleaning & Organizing
    Given I am on Browse Page for "Cleaning & Organizing" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_16 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - Home - Furniture
    Given I am on Browse Page for "Furniture" under "HOME"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_17 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2 @review_stability
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - WOMEN - Plus Sizes
    Given I am on Browse Page for "All Plus Sizes" under "WOMEN"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_1 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2 @review_stability
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - WOMEN - Juniors
    Given I am on Browse Page for "Juniors" under "WOMEN"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_16 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2 @review_stability
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - WOMEN - Lingerie
    Given I am on Browse Page for "Bras, Panties & Shapewear" under "WOMEN"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  # Added wip tag because expected Customer's Top Rated section is not available in production site
  @snbc_backlog @snbc_descope @dsv_category_17 @artifact_navapp @domain_discovery @priority_high @wip
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - MEN - Activewear
    Given I am on Browse Page for "Activewear" under "MEN"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_1 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - MEN - Guys
    Given I am on Browse Page for "Guys" under "MEN"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_16 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - MEN - Shoes
    Given I am on Browse Page for "All Men's Shoes" under "MEN"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  @snbc_backlog @snbc_descope @dsv_category_17 @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - KIDS - Shop All Baby
    Given I am on Browse Page for "All Baby" under "KIDS"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.

  # Added wip tag because expected Customer's Top Rated section is not available in production site
  @snbc_backlog @snbc_descope @dsv_category_1 @artifact_navapp @domain_discovery @priority_high @wip
  Scenario: BrowsePage - Verify Shop Customer's Top Rated - HANDBAGS - Luggage
    Given I am on Browse Page for "Designer Handbags" under "HANDBAGS"
    Then I verify Customer's Top Rated section
    # Notes:
    # Do full validation - should not display any broken image or link
    # Two products should be present in Customers' top rated panel
    # Rating date should be latest & should be within past 6 months.
    # Star rating should be 4 or more.


