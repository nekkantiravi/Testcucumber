Feature: Identify and inject tealium script

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on my account page as a guest user in MEW
    Given I visit the mobile web site as a guest user
    When I navigate back to "my account" page using mobile website
    And I select create account button
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the kill switch is on for MEW for tealium tags on my account page
    Given I visit the mobile web site as a guest user
    When I navigate back to "my account" page using mobile website
    And I select create account button
    Then I verify the kill switch toggle
      | on |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on wallet page as guest user in MEW
    Given I visit the mobile web site as a guest user
    When I navigate to the wallet page using mobile website
    Then I verify tealium tags script is available in network call for mew
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |

  @Tealium
  Scenario: To verify the MEW KS value for tealium tags on wallet page
    Given I visit the mobile web site as a guest user
    When I navigate to the wallet page using mobile website
    Then I verify the kill switch toggle
      | on |