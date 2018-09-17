Feature: Implement Tealium Script for Authweb for Environment Aware for BCOM

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in Forgot password Auth page for BCOM
    Given I visit the web site as a guest user
    When I click on "MY ACCOUNT" link in the header
    Then I click on forgot password link for bcom
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|