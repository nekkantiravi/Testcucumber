Feature: Implement Tealium Script for macys-node

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call and page source on Create Registry page as a guest user in mcom
    Given I visit the web site as a guest user
    When I navigate to home page for creating registry
    And I select "Create Your Registry"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js    |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call on Welcome Registry and Registrant page as guest user in registry mode
    Given I visit the web site as a guest user
    When I navigate to home page for creating registry
    And I select "Create Your Registry"
    And I start to create a new registry from registry sign in page
    And I create a new registry
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Then I click on view registry link on hover registry guide
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call on Registrant for Guest user in registry mode in mcom
    Given I visit the web site as a guest user
    When I navigate to home page for creating registry
    Then I click on view registry link on hover registry guide
    And I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js    |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call and page source on Create Registry page for registered user in mcom
    Given I visit the web site as a registered user
    When I navigate to home page for creating registry
    And I select "Create Your Registry"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js    |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |