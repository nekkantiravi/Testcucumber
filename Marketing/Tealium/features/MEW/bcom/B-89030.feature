Feature: Implement BrightTag Kill Switch for MEW 2.0 for BCOM

  #  Bright tag KS off scenarios
  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for browse pages
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | WOMEN  |
      | Jeans  |
      | Skinny |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for splash pages
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | WOMEN  |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for Wishlist page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Wish List |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for Stores page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Stores |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call


  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for PDP page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | WOMEN  |
      | Jeans  |
      | Skinny |
    And I select a random member product using mobile website
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for promotions page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Promotions |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for Search Results page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "mens watches"
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call

  @Tealium_proxy @BrightKsoff
  Scenario: Verify brighttag kill switch toggle is off and tag.js is not loaded in network call for Registry page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | The Registry |
    Then I verify the kill switch toggle for bright tag
      | off |
    And I verify bright tags script is not available in network call