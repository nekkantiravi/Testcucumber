Feature:Implementation of Tealium script into applications

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on home page for guest user in mcom
    Given I visit the web site as a guest user
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the tealium kill switch value for tealium tags on home page
    Given I visit the web site as a guest user
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on shopapp page for guest user in mcom
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the kill switch on for tealium tags on shopapp page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |


  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on bagapp page for guest user in mcom
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify tealiumScriptEnabled KS for bagapp page in mcom
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |


  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in network call on PDP page
    Given I visit the web site as a guest user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the tealium kill switch on PDP pages in mcom
    Given I visit the web site as a guest user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on Browse/Search Polaris App page
    Given I visit the web site as a guest user
    When  I search for "<keyword>"
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
  Examples:
  | keyword   |
  | jeans     |


  @Tealium
  Scenario Outline: To verify the kill switch on for tealium tags on Browse/Search Polaris App page
    Given I visit the web site as a guest user
    When  I search for "<keyword>"
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
  Examples:
  | keyword   |
  | jeans     |