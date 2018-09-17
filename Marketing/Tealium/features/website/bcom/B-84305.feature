Feature: Implement Environment awareness in Tealium script for non-MEW applications for Browse and Search Results page

  @Tealium
  Scenario Outline: Verify that tealium tags script is loaded in page source on Browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "WOMEN" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium
  Scenario Outline: Verify that tealium tags script is loaded in page source on Search Results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium
  Scenario: Verify that tealium tags script is loaded in page source call on Browse page on registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "KITCHEN" category from top navigation
    And I select "Baking Dishes" subcategory from flyout menu
    Then I verify tealium tags script is available in page source
      |  //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      |  //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |

  @Tealium
  Scenario: Verify that tealium tags script is loaded in page source on Search Results page on registry mode in bcom
    Given I visit the web site as a guest user in "registry" mode
    When I search for "lenox"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |