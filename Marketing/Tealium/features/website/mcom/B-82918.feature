Feature: Create Kill switch for ShopApp for Tealium script

  @Tealium_proxy
  Scenario: To verify the kill switch on for tealium tags on Account SignIn page
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario: To verify the kill switch on for tealium tags on Wedding Registry page
    Given I visit the web site as a guest user in "registry" mode
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario: To verify the kill switch on for tealium tags on Wedding Registry Category pages in registry mode as a guest user
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "KITCHEN" category page
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario: To verify the kill switch on for tealium tags on Wedding Registry Category Browse page as a guest user in registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "KITCHEN" category from top navigation
    And I select "Electrics" subcategory from flyout menu
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |