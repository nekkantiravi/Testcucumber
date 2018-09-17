Feature:Implement Tealium Script Into Polaris : Customer Preferences for MCOM

  @Tealium
  Scenario Outline: Verify the utag.js and utag.sync.js in page source for My Account page
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I navigate to my account page
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js    |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |