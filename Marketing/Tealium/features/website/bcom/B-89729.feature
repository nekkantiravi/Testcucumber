Feature:  BCOM Tag Migration Polaris Killswitch My Account

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded on My Account page for BCOM
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|