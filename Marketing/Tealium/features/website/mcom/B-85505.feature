Feature: Verify the data layer attributes for Shopping Bag for MCOM

  @Tealium @Datalayer
  Scenario Outline: Verify  the data attributes for tealium tags on Shopping Bag page as guest user in mcom
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to the "Jeans" browse page under "MEN"
    And I add a random product to bag
    And I click the checkout button
    Then I should be redirected to checkout page
    And I verify the data attributes for tealium tags on Shopping bag page from utagData for Shopping Bag page
      | order_sub_total         |
      | order_discount          |
      | order_promocode         |
      | order_total_items       |
      | product_id              |
      | product_name            |
      | product_category_id     |
      | product_color           |
      | product_size            |
      | product_price           |
      | product_quantity        |
      | product_sub_total       |
      | product_upc             |
      | product_registry_flag   |
      | product_registry_number |
    Then I verify the data attributes for tealium tags on pagesource from utagData for Shopping Bag page
      | order_sub_total         |
      | order_discount          |
      | order_promocode         |
      | order_total_items       |
      | product_id              |
      | product_name            |
      | product_category_id     |
      | product_color           |
      | product_size            |
      | product_price           |
      | product_quantity        |
      | product_sub_total       |
      | product_upc             |
      | product_registry_flag   |
      | product_registry_number |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @Tealium @Datalayer
  Scenario Outline: Verify  data attributes for tealium tags on Shopping Bag page as registered user in mcom
    Given I visit the web site as a registered user in "<mode_name>" mode
    When I navigate to the "Jeans" browse page under "MEN"
    And I add a random product to bag
    And I click the checkout button
    Then I should be redirected to checkout page
    And I verify the data attributes for tealium tags on Shopping bag page from utagData for Shopping Bag page
      | order_sub_total         |
      | order_discount          |
      | order_promocode         |
      | order_total_items       |
      | product_id              |
      | product_name            |
      | product_category_id     |
      | product_color           |
      | product_size            |
      | product_price           |
      | product_quantity        |
      | product_sub_total       |
      | product_upc             |
      | product_registry_flag   |
      | product_registry_number |
    Then I verify the data attributes for tealium tags on pagesource from utagData for Shopping Bag page
      | order_sub_total         |
      | order_discount          |
      | order_promocode         |
      | order_total_items       |
      | product_id              |
      | product_name            |
      | product_category_id     |
      | product_color           |
      | product_size            |
      | product_price           |
      | product_quantity        |
      | product_sub_total       |
      | product_upc             |
      | product_registry_flag   |
      | product_registry_number |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |