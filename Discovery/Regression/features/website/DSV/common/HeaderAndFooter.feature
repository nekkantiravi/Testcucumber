Feature: Verify header and footer sections dsv features in DOMESTIC, ISHIP and REGISTRY mode

  @dsv_desktop_sev1 @use_sev1_dryrun
  Scenario: HeaderAndFooter - Verify Registry FOBs from Registry Home page
    Given I visit the web site as a guest user in "registry" mode
    Then I should see dynamic top navigation in "registry" mode
    And I should see new header and footer elements section in registry mode
    And I navigate to all fob categories from registry home page