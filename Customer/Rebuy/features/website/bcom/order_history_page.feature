## Project: REBUY
## EPIC:
## Date updated: 16th August 2017

Feature: Verify Rebuy button on Order History

  @scenario1 @domain_customer  @project_rebuy @rebuy_da @use_project
  Scenario Outline: Verify "buy again" button is not visible on Order History page for non-eligible line items.
    Given I visit the web site as a guest user
    When I select "<order_type>" order as a "signed" user
    And I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should not see buy again button for "<order_type>" on "OH" page
    Examples:
      | order_type                        |
      | rebuy_upc_unavailable             |
      | rebuy_upc_available_not_available |
      | rebuy_processing                  |
      | rebuy_egc                         |
      | rebuy_gwp                         |


  @scenario2 @domain_customer @project_rebuy @rebuy_da @use_project
  Scenario Outline: Verify rebuy button appears on Order History for eligible orders
    Given I visit the web site as a guest user
    When I select "<order_type>" order as a "signed" user
    And I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should see "buy this again" button on "OH"
    When I select rebuy button on "OH" page
    And I select add to bag button on quick view page
    Then I should see item getting added to bag successfully
    Examples:
      | order_type          |
      | rebuy_delivered     |
      | rebuy_bops_pickedup |
      | rebuy_intransit     |


  @scenario3 @domain_customer @project_rebuy @use_project @rebuy_da
  Scenario: Verify quickpeek has product description and price for product
    Given I visit order history page as a guest user
    When I select "rebuy_delivered_with_size_color" order as a "signed" user
    When I navigate to the "order status" page from footer
    And I go to "rebuy_delivered_with_size_color" order using page navigation in oh page
    And I verify product attribute on quick view with line item detail on "OH" page
    And I select add to bag button on quick view page
    Then I should see item getting added to bag successfully


  @scenario4 @domain_customer @project_rebuy @b-84354 @use_project @rebuy_da @use_regression
  Scenario Outline: Verify navigeted PDP page by clicking on product image link on Order History page for eligible orders
    Given I visit order history page as a guest user
    When I select "<order_type>" order as a "signed" user
    When I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should able to navigate to PDP page by clicking on "<link>" on "OH" page for rebuy line item

    Examples:
      | order_type      | link          |
      | rebuy_delivered | product_image |

  @scenario5 @domain_customer @project_rebuy @b-84354 @use_project @rebuy_da @use_regression
  Scenario Outline: Verify navigeted PDP page by clicking on product name link on Order History page for eligible orders
    Given I visit order history page as a guest user
    When I select "<order_type>" order as a "signed" user
    When I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should able to navigate to PDP page by clicking on "<link>" on "OH" page for rebuy line item

    Examples:
      | order_type      | link         |
      | rebuy_delivered | product_name |