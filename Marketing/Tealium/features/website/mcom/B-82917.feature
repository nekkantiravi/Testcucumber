Feature: Create Kill switch for NavApp for Tealium script

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on Category Splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to "WOMEN" category page
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on Browse pages as guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "WOMEN" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on PDP master page as a guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "Home" category from top navigation
    And I select "Fine China" subcategory from flyout menu
    And I select a random master product from search results page
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in network call on PDP member page as a guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I select a random member product
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

