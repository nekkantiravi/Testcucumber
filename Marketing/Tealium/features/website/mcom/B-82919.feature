Feature: Create Kill switch for BagApp for Tealium script

  @Tealium_proxy
  Scenario: Verify that tealium tags script and bright tag is loaded in network call on a bagapp page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the tealiumScriptEnabled kill switch for tealium tags on bagapp page in mcom
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |