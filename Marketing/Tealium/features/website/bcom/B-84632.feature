Feature: Implement Environment awareness in Tealium script(s) for non-MEW applications for BCOM

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call on My Account page for BCOM guest user
    Given I visit the web site as a guest user
    When I click on "MY ACCOUNT" link in the header
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source and network call on Browse page in BCOM
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded  in page source and network call on Search Results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded is in page source and network call on Shopping bag page for BCOM for guest user
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call on order confirmation page for BCOM for guest user
    Given I visit the web site as a guest user
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call on order confirmation page for BCOM for SignedIn user
    Given I visit the web site as a registered user with checkout eligible address
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario:Verify that tealium tags script is loaded in in page source and network call on PDP page for guest user
    Given I visit the web site as a guest user in "domestic" mode
    When I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I select a random chanel product from browse page
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario: Verify that tealium tags script is present in page source and network call on registry browse page as guest user
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "KITCHEN" category from top navigation
    And I select "Baking Dishes" subcategory from flyout menu
    Then I verify tealium tags script is available in page source
      |  //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      |  //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source on the Search Results page on registry mode as a guest user in bcom
    Given I visit the web site as a guest user in "registry" mode
    When I search for "lenox"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|