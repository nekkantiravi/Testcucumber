Feature: Verification GNA and GFA in domestic, iship and registry modes

  @dsv_sev2 @preview_desktop @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: Verify 3 Footer Banner ads are displayed on Homepage
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify footer banner ads are displayed

  @dsv_sev2 @preview_desktop @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario Outline: Verify social links on Homepage footer in domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    And I verify that "<links>" social icons are displayed on homepage
    Examples:
      | links      |
      | facebook   |
      | twitter    |
      | pinterest  |
      | youtube    |
      | M blog     |
      | Mobile App |
      | Sign-up    |



