Feature: Environment awareness for Credit Gateway for BCOM

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call on Credit Gateway page for guest user
    Given I visit the web site as a guest user
    When I click on credit gateway link for bcom
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in network call on Credit Gateway page for Registered user
    Given I visit the web site as a registered user
    When I click on credit gateway link for bcom
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|