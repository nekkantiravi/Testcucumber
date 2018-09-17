Feature: Verifying Left Nav Media based on KS - "seoSlpEnhancementsEnabled"

  # Either one scenario should pass
  @B-95651 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario: SLP - Domestic - Verify Left Nav Media is displayed in LHN for media associated category & when KS - "seoSlpEnhancementsEnabled" is enabled
    Given I am on SLP for "112746" category id in Domestic mode
    When I verify KS "seoSLPEnhancementsEnabled" is enabled
    Then I should see Left Nav Media on SLP page
    
  @B-95651 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario: SLP - Domestic - Verify Left Nav Media is not displayed in LHN for media associated category & when KS - "seoSlpEnhancementsEnabled" is disabled
    Given I am on SLP for "112746" category id in Domestic mode
    When I verify KS "seoSlpEnhancementsEnabled" is disabled
    Then I should not see default Left Nav on SLP page