Feature: Create Kill switch for BrowseApp for Tealium script

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on browse page
    Given I visit the web site as a guest user
    When I navigate to the "Dresses" browse page under "WOMEN"
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on browse page
    Given I visit the web site as a guest user
    When I navigate to the "Dresses" browse page under "WOMEN"
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |