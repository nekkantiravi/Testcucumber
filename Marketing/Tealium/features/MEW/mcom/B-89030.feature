Feature: Implement BrightTag Kill Switch for MEW 2.0 for MCOM

  #  Bright tag KS off scenarios
  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for browse pages for MCOM
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for splash pages for MCOM
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Women |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for Stores page for MCOM
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Stores |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call


  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for PDP page for MCOM
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I select a random member product using mobile website
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call


  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for Search Results page for MCOM
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "mens watches"
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call
