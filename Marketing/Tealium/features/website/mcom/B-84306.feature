Feature: Implement Environment awareness in Tealium script for non-MEW applications for NavApp, ShopApp, BagApp and PDPApp page

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on home page
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on Browse page for guest user in mcom
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to the "Dresses" browse page under "WOMEN"
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on Category splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to "WOMEN" category page
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on Bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I add an "available and orderable" product to my bag
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on PDP page for guest user in mcom
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on Search Results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |