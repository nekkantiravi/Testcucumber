Feature: Implement Tealium Script for Authweb for Environment Aware for MCOM

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in Forgot password Auth page
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    Then I click on forgot password link
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |