Feature:DataLayer : Shopping Bag (JSP) for BCOM

  @Tealium @Datalayer
  Scenario Outline: Verify  data attributes for tealium tag on Shopping Bag page as a guest user for BCOM
    Given I visit the web site as a guest user in "<mode_name>" mode
    And I remove all items from the shopping bag
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I should be redirected to Shopping Bag page in "<mode_name>" mode
    And I verify the data attributes for tealium tags from utagData for Shopping Bag page
      | order_sub_total         |
      | product_id              |
      | product_price           |
      | product_upc             |
    Then I verify the data layer attributes for tealium tags on pagesource from utagData for Shopping Bag page
      | order_sub_total         |
      | product_id              |
      | product_price           |
      | product_upc             |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium @Datalayer
  Scenario Outline: Verify  data attributes for tealium tag on Shopping Bag page for BCOM as a registered user in BCOM
    Given I visit the web site as a registered user in "<mode_name>" mode
    And I remove all items from the shopping bag
    When I mouse over "MEN" category from top navigation
    And I select "Jeans" subcategory from flyout menu
    And I add a random product to bag
    And I click the checkout button on the overlay
    Then I should be redirected to Shopping Bag page in "<mode_name>" mode
    And I verify the data attributes for tealium tags from utagData for Shopping Bag page
      | order_sub_total         |
      | product_id              |
      | product_price           |
      | product_upc             |
    Then I verify the data attributes for tealium tags on pagesource from utagData for Shopping Bag page
      | order_sub_total         |
      | product_id              |
      | product_price           |
      | product_upc             |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |