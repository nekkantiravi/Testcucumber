Feature: Verify assets are displayed on home page

  @preview_mew @dsv_mew_sev1
  Scenario: Verify Main ads are displayed on HomePage
    Given I visit the mobile web site as a guest user in domestic mode
    And I verify that ads are clickable and should not navigate to error page for bcom

  @preview_mew @dsv_mew_sev1
  Scenario: Verify featured categories on Home page are clickable and navigate to the correct pages
    Given I visit the mobile web site as a guest user
    Then I verify FOB links on home page are clickable and navigate to the correct pages