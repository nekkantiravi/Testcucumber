Feature: Verify the data layer attributes for PDP(NavApp) page

#  Guest User
  @Tealium @Datalayer
  Scenario Outline: Verify the data layer attributes for PDP(NavApp) page for member product for guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to the "Jeans" browse page under "MEN"
    And I select a random member product
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "domestic"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "member" product
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium @Datalayer
  Scenario Outline: Verify the data layer attributes for PDP(NavApp) page for master product for guest user
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "Home" category from top navigation
    And I select "Fine China" subcategory from flyout menu
    And I select a random master product from search results page
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "domestic"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "master" product
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |


 #  Registered User
  @Tealium @Datalayer
  Scenario Outline: Verify the data layer attributes for PDP(NavApp) page for member product for Registered user
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I navigate to the "Jeans" browse page under "MEN"
    And I select a random member product
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "domestic"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "member" product
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium @Datalayer
  Scenario Outline: Verify the data layer attributes for PDP(NavApp) page for master product for Registered user
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I mouse over "Home" category from top navigation
    And I select "Fine China" subcategory from flyout menu
    And I select a random master product from search results page
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "domestic"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "master" product
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

 # ------------------------Registry Scenarios-------------------------------------------#
#   Guest User
  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for member product for guest user in registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "LUGGAGE" category from top navigation
    And I select "Delsey" subcategory from flyout menu
    And I select a random member product
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "registry"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "member" product for registry
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for master product for guest user in registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "LUGGAGE" category from top navigation
    And I select "Delsey" subcategory from flyout menu
    And I select a random master product from search results page
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "registry"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "master" product for registry
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |


 #  Registered User
  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for member product for Registered user in registry mode
    Given I visit the web site as a registered user in "registry" mode
    When I mouse over "LUGGAGE" category from top navigation
    And I select "Delsey" subcategory from flyout menu
    And I select a random member product
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "registry"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "member" product for registry
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for master product for Registered user in registry mode
    Given I visit the web site as a registered user in "registry" mode
    When I mouse over "LUGGAGE" category from top navigation
    And I select "Delsey" subcategory from flyout menu
    And I select a random master product from search results page
    And I add "targetenv=legacy" to the current url
    Then I should be redirected to product details page for "registry"
    Then I verify the data attributes for tealium tags on "NavApp" page from utagData
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |
    Then I verify the data attributes for tealium tags on "PDPApp" page from pagesource for "master" product for registry
      | page_type             |
      | lean_template         |
      | order_by_phone        |
      | product_id            |
      | product_name          |
      | product_category_id   |
      | product_category_name |
      | product_type          |
      | true_fit_size         |
      | product_video         |