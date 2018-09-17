Feature: SECURE-M (MEW) MCOM : Create Kill switch for Tealium tag

  @Tealium_proxy
  Scenario: Verify that tealium tags script  and bright tag script is present in network call on my account page as a guest user in MEW
    Given I visit the mobile web site as a guest user
    When I navigate back to "my account" page using mobile website
    And I select create account button
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the kill switch for MEW tealium tags on my account page as guest user
    Given I visit the mobile web site as a guest user
    When I navigate back to "my account" page using mobile website
    And I select create account button
    Then I verify the kill switch toggle
      | on |

  @Tealium_proxy
  Scenario: Verify that tealium tags script and bright tag script is loaded in network call on wallet page as guest user
    Given I visit the mobile web site as a guest user
    When I navigate to the wallet page using mobile website
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the KS value for MEW tealium tags on wallet page as a guest user
    Given I visit the mobile web site as a guest user
    When I navigate to the wallet page using mobile website
    Then I verify the kill switch toggle
      | on |