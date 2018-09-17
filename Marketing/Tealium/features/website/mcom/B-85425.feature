Feature: Implement Tealium Script Into Polaris for My Account for MCOM

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call on My Account page for registered user
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call on My Account page for guest user
    Given I visit the web site as a guest user
    When I navigate to my account page
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |