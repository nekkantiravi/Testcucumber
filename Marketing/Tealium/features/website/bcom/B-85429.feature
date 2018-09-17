Feature: Implement Tealium Script Into Polaris for Search and Browse pages

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source call on Browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "WOMEN" category from top navigation
    And I select "Dresses" subcategory from flyout menu
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
  Scenario: Verify that tealium tags script is loaded in page source call on Registry Browse page
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "LUGGAGE" category from top navigation
    And I select "Uprights" subcategory from flyout menu
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js|

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in Search polaris pages
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I visit the website for "<polaris>" page
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | polaris                                            |
      | ?EFCKEY={%22EXPERIMENT%22:[10172]}                 |