Feature: Implement Tealium script for ShopApp for BCOM

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call for Shopping bag page
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js     |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call for order confirmation page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "guest" user
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js     |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call for Shopping bag page for registered user
    Given I visit the web site as a registered user
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js     |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call for checkout page for Registered user
    Given I visit the web site as a registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js     |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|


  Scenario: Verify that tealium tags script is loaded in page source and network call for order confirmation page for Registered user
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "signedIn" user
    Then I should see order confirmation section displayed with order details
    And I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js     |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|


  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call on Registry shopping bag page
    Given I visit the web site as a guest user in "registry" mode
    When I add an "available and orderable" product to my bag
    And I click the checkout button on the overlay
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy @WIP
  Scenario: Verify that tealium tags script is loaded in page source and network call for order confirmation page in registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "lenox"
    And I select a random member product
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js     |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|