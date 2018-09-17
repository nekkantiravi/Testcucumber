Feature: Create Kill switch for PDP App for Tealium script

  @Tealium_proxy
  Scenario: Verify that tealium tags script and brigt tag script is loaded in network call on PDP page for member product
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I select a random member product
    Then I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/macys/main/qa/utag.sync.js |

  @Tealium
  Scenario: To verify the tealium KS value on the PDP member pages
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I select a random member product
    Then I verify the kill switch toggle as "true"
      | tealiumScriptEnabled |