Feature: Implement Tealium script for MyAccount page for BCOM

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in My Account page for BCOM for guest user
    Given I visit the web site as a guest user
    When I click on "MY ACCOUNT" link in the header
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in My Account page for BCOM for Registered user
    Given I visit the web site as a registered user
    When I click on "MY ACCOUNT" link in the header
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|