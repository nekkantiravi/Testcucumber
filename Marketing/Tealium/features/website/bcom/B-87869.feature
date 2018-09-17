Feature: Environment awareness story for BagApp page for BCOM

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source call and network call on BagApp page for Guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I add an "available and member_product and orderable" product to my bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|
    Examples:
      | mode_name |
      | domestic  |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source call on Browse pages in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "LUGGAGE" category from top navigation
    And I select "Uprights" subcategory from flyout menu
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source call and on network call on Search Results pages for Guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|
    Examples:
      |mode_name|
      |domestic|

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source call and network call on BagApp page for registered user
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I add an "available and member_product and orderable" product to my bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|
    Examples:
      | mode_name |
      | domestic  |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source call  and network call on Registry Browse page on registered user
    Given I visit the web site as a registered user in "registry" mode
    When I mouse over "LUGGAGE" category from top navigation
    And I select "Uprights" subcategory from flyout menu
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source call  and network call on Search Results page on registered user
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I search for "nike women"
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|
    Examples:
      |mode_name|
      |domestic|