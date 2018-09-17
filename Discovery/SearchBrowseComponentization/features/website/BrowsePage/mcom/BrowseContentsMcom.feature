Feature: Contents verification on Search Landing Page

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: BrowsePage - Domestic - Verify header is displayed
    Given I am on CategoryBrowsePage for "11221" category id in Domestic mode
    Then I verify that logo is displayed and returns a 200 OK
    Then I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in Browse page

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: BrowsePage - Domestic - Verify footer is displayed
    Given I am on CategoryBrowsePage for "11221" category id in Domestic mode
    Then I verify that the footer elements are displayed
  # Notes: Verifies that footer links are all present in Browse page

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: BrowsePage - Domestic - Verify FOBs
    Given I am on CategoryBrowsePage for "11221" category id in Domestic mode
    And I verify that fobs are displayed and return a 200 OK

 @domain_discovery @artficat_navapp @priority_high @use_regression @artifact_navapp @use_preview @saturnrules @mode_domestic @use_domain_qual @mcom
  Scenario: BrowsePage -  Verify "Lancome" in Beauty category Splash Page in DOMESTIC mode
    Given I am on CategoryBrowsePage for "28688" category id in Domestic mode
    Then I verify that all the product thumbnails displayed properly on the Category Browse page