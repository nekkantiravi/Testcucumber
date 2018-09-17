Feature: MCOM Tag Migration Polaris Killswitch Subscription

  @Tealium
  Scenario Outline: Verify that tealium tags script is loaded in Subscription pages
    Given I visit the web site as a guest user
    And  I visit the "<subscription>" pages
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | subscription |
      | subscription/beauty-box  |
      | subscription/manageSubscriptions   |
      | subscription/beautybox/canceled |
      | subscription/beautybox/checkout |

  @Tealium
  Scenario Outline: Verify that tealium tags script is loaded in Subscription pages as a registered user
    Given I visit the web site as a registered user
    And  I visit the "<subscription>" pages
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | subscription |
      | subscription/beauty-box  |
      | subscription/manageSubscriptions   |
      | subscription/beautybox/canceled |
      | subscription/beautybox/checkout |