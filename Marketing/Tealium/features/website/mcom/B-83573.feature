Feature: Create new tiles JSPs, new tile defs, and new jsp

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on home page as a guest user
    Given I visit the web site as a guest user
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the KS value for tealium tags for the Home page as guest user
    Given I visit the web site as a guest user
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on shopapp page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on shopapp page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on bagapp page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the KS value for tealium tags on Bagapp page for the guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on PDP page as a guest user
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I select a random member product
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the kill switch value is on for tealium tags on member PDP pages
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I select a random member product
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on Browse/Search Polaris App page
    Given I visit the web site as a guest user
    When  I search for "jeans"
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on Browse/Search Polaris App page
    Given I visit the web site as a guest user
    When  I search for "jeans"
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |