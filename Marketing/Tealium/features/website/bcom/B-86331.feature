Feature: Verify the data layer attributes for PDP(NavApp) page

#  Guest User
  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for member product for guest user
    Given I visit the web site as a guest user in "domestic" mode
    When I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I select a random chanel product from browse page
    Then I should be redirected to PDP page
    #And I add "&targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for master product for guest user
    Given I visit the web site as a guest user in "domestic" mode
    When I mouse over "HOME" category from top navigation
    And I select "Bedding Collections" subcategory from flyout menu
    And I select a random master product from browse page
    Then I should be redirected to PDP page
    #And I add "&targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|


 #  Registered User
  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for member product for Registered user
    Given I visit the web site as a registered user in "domestic" mode
    When I mouse over "BEAUTY" category from top navigation
    And I select "CHANEL" subcategory from flyout menu
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I select a random chanel product from browse page
    Then I should be redirected to PDP page
    #And I add "targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for master product for Registered user
    Given I visit the web site as a registered user in "domestic" mode
    When I mouse over "HOME" category from top navigation
    And I select "Bedding Collections" subcategory from flyout menu
    And I select a random master product from browse page
    Then I should be redirected to PDP page
    #And I add "&targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|

 # ------------------------Registry Scenarios-------------------------------------------#
 #  Guest User
  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for registry member product for guest user
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "KITCHEN" category from top navigation
    And I select "Blenders" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    #And I add "&targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for registry master product for guest user
    Given I visit the web site as a guest user in "registry" mode
    When I mouse over "HOME CARE & TECH" category from top navigation
    And I select "Home Tech" subcategory from flyout menu
    And I select a random master product
    Then I should be redirected to PDP page
    #And I add "&targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|


 #  Registered User
  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for registry member product for Registered user
    Given I visit the web site as a registered user in "registry" mode
    When I mouse over "HOME CARE & TECH" category from top navigation
    And I select "Home Tech" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
   # And I add "&targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|

  @Tealium @Datalayer
  Scenario: Verify the data layer attributes for PDP(NavApp) page for registry master product for Registered user
    Given I visit the web site as a registered user in "registry" mode
    When I mouse over "HOME CARE & TECH" category from top navigation
    And I select "Home Tech" subcategory from flyout menu
    And I select a random master product
    Then I should be redirected to PDP page
    #And I add "&targetenv=legacy" to the current url
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
      |page_type|
      |lean_template|
      |order_by_phone|
      |product_id|
      |product_name|
      |product_category_id|
      |product_category_name|
      |product_type|
      |true_fit_size|
      |product_video|