Feature:  Tag Migration for Environment Awareness for Customer Preferences

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded on account preferences page for BCOM
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I click on account preferences link for bcom
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|