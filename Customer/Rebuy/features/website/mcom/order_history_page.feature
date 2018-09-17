## Project: REBUY
## EPIC: https://www14.v1host.com/Macyscom/Default.aspx?menu=EpicsPortfolioPlanningPage&feat-nav=m1
## Date updated: 9th May 2017

Feature: Verify Rebuy button on Order History

  @scenario1 @domain_customer @project_rebuy @b_77173 @use_project @rebuy_da @use_regression
  Scenario Outline: Verify rebuy button appears on Order History for eligible orders
    Given I visit the web site as a guest user
    When I associate rebuy "<order_type>" order in db
    And I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should see "buy again" button on "OH"
    When I select rebuy button on "OH" page
    And I select add to bag button on quick view page
    Then I should see item getting added to bag successfully
    Examples:
      | order_type          |
      | rebuy_delivered     |
      | rebuy_bops_pickedup |
      | rebuy_intransit     |

  @scenario2 @domain_customer @use_project @rebuy_da @use_regression
  Scenario Outline: Verify "buy again" button is not visible on Order Details page for non-eligible line items.
    Given I visit the web site as a guest user
    And I associate rebuy "<order_type>" order in db
    And I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should not see buy again button for "<order_type>" on "OH" page
    Examples:
      | order_type                        |
      | rebuy_processing                  |
      | rebuy_egc                         |
      | rebuy_vr_delivered                |
      | rebuy_upc_unavailable             |
      | rebuy_upc_available_not_available |
      | rebuy_beautybox                   |
      | rebuy_gwp                         |

  @scenario3 @domain_customer @project_rebuy @b-77173 @use_project @rebuy_da @use_regression
  Scenario: Verify quickView has product description and price for product
    Given I visit order history page as a guest user
    And I associate rebuy "rebuy_delivered_with_size_color" order in db
    When I navigate to the "order status" page from footer
    And I go to "rebuy_delivered_with_size_color" order using page navigation in oh page
    And I verify product attribute on quick view with line item detail on "OH" page
    And I select add to bag button on quick view page
    Then I should see item getting added to bag successfully

  @scenario4 @domain_customer @project_rebuy @b-84354 @use_project @rebuy_da
  Scenario Outline: Verify navigeted PDP page by clicking on product image link on Order History page for eligible orders
    Given I visit order history page as a guest user
    When I select "<order_type>" order as a "signed" user
    When I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should able to navigate to PDP page by clicking on "<link>" on "OH" page for rebuy line item

    Examples:
      | order_type      | link          |
      | rebuy_delivered | product_image |

  @scenario5 @domain_customer @project_rebuy @b-84354 @use_project @rebuy_da
  Scenario Outline: Verify navigeted PDP page by clicking on product name link on Order History page for eligible orders
    Given I visit order history page as a guest user
    When I select "<order_type>" order as a "signed" user
    When I navigate to the "order status" page from footer
    And I go to "<order_type>" order using page navigation in oh page
    Then I should able to navigate to PDP page by clicking on "<link>" on "OH" page for rebuy line item

    Examples:
      | order_type      | link         |
      | rebuy_delivered | product_name |

