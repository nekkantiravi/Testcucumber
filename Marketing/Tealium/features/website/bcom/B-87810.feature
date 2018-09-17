Feature: Implement Tealium scripts Homepage for BCOM

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source and network call on Home page
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|
    Examples:
      |mode_name|
      |domestic|
      |iship|