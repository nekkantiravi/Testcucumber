Feature: Implement Tealium script for NavApp pages for BCOM

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source and network call for Home page
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is present in page source and network call is captured on Browse page as a guest user in bcom
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "WOMEN" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline: Verify that tealium tags script is loaded in page source and network call is captured on Search Results page in bcom as a guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium_proxy
  Scenario Outline:  Verify that tealium tags script is loaded in page source and network call on PDP page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    And I select a random member product
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    Examples:
      | mode_name |
      | domestic  |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call on Browse page on registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "KITCHEN" category from top navigation
    And I select "Baking Dishes" subcategory from flyout menu
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario: Verify that tealium tags script is loaded in page source and network call on Search Results page on registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "lenox"
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |

  @Tealium_proxy
  Scenario:  Verify that tealium tags script is loaded in page source and network call on Registry PDP page
    Given I visit the web site as a guest user in "registry" mode
    When I search for "lenox"
    And I select a random member product
    Then I verify tealium tags script is available in page source
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | //tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
    And I verify tealium tags script is available in network call
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.js      |
      | https://tags.tiqcdn.com/utag/bcom/main/qa/utag.sync.js |
