Feature: Implement BrightTag Kill Switch for Secure-M pages for MCOM

#  Bright tag KS off scenarios
  @Tealium_proxy @BrightKsoff
  Scenario:Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for MY bWALLET page for MCOM
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | WALLET |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify kill switch toggle and tag.js script is loaded in network call for My Account page for MCOM
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | My Account |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify kill switch toggle and  tag.js script is loaded in network call for Loyality page for MCOM
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | More                    |
      | Apply: Macy's Credit Card|
    And I click MacysCreditCard link
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js script is not loaded in network call for Wishlist page for MCOM
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call