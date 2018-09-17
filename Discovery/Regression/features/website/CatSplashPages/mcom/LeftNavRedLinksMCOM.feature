# Author: DISCOVERY QE
# Date Created: 06/10/2015


Feature: Verify CategorySplashPages - Left Nav Red Links

  #Test Case ID: MCOM-60291
  #Testlink-MCOM-60291 Vone - RT-07325
  @domain_discovery @feature_catsplash @use_regression @mode_domestic @priority_high @migrated_to_sdt
  Scenario: CategorySplashPage - Verify Red links for CatSplash Pages in DOMESTIC mode in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I navigate to all category pages and I verify left nav red link is clickable
      | WOMEN      |
      | MEN        |
      | JUNIORS    |
      | KIDS       |
      | BEAUTY     |
      | SHOES      |
      | HANDBAGS   |
      | JEWELRY    |
      | WATCHES    |
    # Notes
    # Click on Special offers, sale and clearence etc.. in red and verify the appropriate page loads

  #Test Case ID: MCOM-60291
  #Testlink-MCOM-60291 Vone - RT-07325
  @domain_discovery @feature_catsplash @use_regression @mode_domestic @priority_high @migrated_to_sdt @discovery_daily_run
  Scenario: CategorySplashPage - Verify Red links for HOME, BED & BATH CatSplash Pages in DOMESTIC mode in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I navigate to all category pages and I verify left nav red link is clickable
      | HOME       |
      | BED & BATH |