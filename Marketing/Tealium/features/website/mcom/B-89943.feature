Feature: MCOM Tag Migration Killswitch Auth-web

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in Auth-web page for MCOM
    Given I visit the web site as a guest user
    When I click on signIn link
    Then SignIn page should get loaded
    And I verify the sign in page links:
      | forgot_password_link                      |
    And I should be navigated to below respective sign in pages:
      | forgot password                     |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js|
