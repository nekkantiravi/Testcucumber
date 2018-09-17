Feature: Implement BrightTag Kill Switch for Secure-M pages for BCOM

#  Bright tag KS off scenarios

  @Tealium_proxy @BrightKsoff
  Scenario:Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for MY bWALLET page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | MY bWALLET |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for for My Account page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for for Loyality page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | MY LOYALLIST |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call
