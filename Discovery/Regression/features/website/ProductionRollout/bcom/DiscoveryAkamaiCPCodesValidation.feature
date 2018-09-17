Feature: BCOM - Akamai CP Code Details Validation

  @domain_discovery @akamai_cp_codes_check
  Scenario: HomePage - Domestic - Verify the page served from akamai for cached pages
    Given I visit the web site as a guest user
    Then I verify akamai details for 'home' page in 'domestic' mode
