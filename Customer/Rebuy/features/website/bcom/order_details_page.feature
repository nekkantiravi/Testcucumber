## Project: REBUY
## EPIC:
## Date updated: 23rd August 2017

Feature: Verify the functionality of rebuy button for eligible products in order history page and order details page

  @scenario1 @domain_customer @use_project @rebuy_da @use_regression
  Scenario Outline: Verify "buy again" button visible on Order details page as a guest user
    Given I visit order history page as a guest user
    When I select "<order_status>" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    Then I should see "buy again" button on "OD"
    When I select rebuy button on "OD" page
    Then I should see repurchase dialog opens on "OD"
    When I select add to bag button on quick view page
    Then I should see item getting added to bag successfully
    Examples:
      | order_status        |
      | rebuy_bops_pickedup |
      | rebuy_delivered     |
      | rebuy_intransit     |

  @scenario2 @domain_customer @use_project @rebuy_da
  Scenario Outline: Verify "buy again" button visible on Order details page as a signed user
    Given I visit the web site as a guest user
    When I select "<order_status>" order as a "signed" user
    When I navigate to "<order_status>" order details page as a "signed" user
    Then I should see "buy again" button on "OD"
    When I select rebuy button on "OD" page
    Then I should see repurchase dialog opens on "OD"
    When I select add to bag button on quick view page
    Then I should see item getting added to bag successfully
    Examples:
      | order_status        |
      | rebuy_bops_pickedup |
      | rebuy_delivered     |
      | rebuy_intransit     |

  @scenario3 @domain_customer @use_project @rebuy_da @use_regression
  Scenario: Verify "buy again" button visible on order details page as a guest user with zipcode lookup on order history page
    Given I visit order history page as a guest user
    When I select "rebuy_delivered" order as a "guest" user
    And I lookup with order number with zipcode in OH page
    Then I should see "buy again" button on "OD"
    When I select rebuy button on "OD" page
    And I should see repurchase dialog opens on "OD"

  @scenario4 @domain_customer @use_project @rebuy_da
  Scenario: Verify product details of rebuy product on rebuy window as a signed user
    Given I visit the web site as a guest user
    When I select "rebuy_delivered_with_size_color" order as a "signed" user
    When I navigate to "rebuy_delivered_with_size_color" order details page as a "signed" user
    And I verify product attribute on quick view with line item detail on "OD" page

  @scenario5 @domain_customer @use_project @rebuy_da
  Scenario: Verify product details of rebuy product on rebuy window as a guest user
    Given I visit order history page as a guest user
    When I select "rebuy_delivered_with_size_color" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I verify product attribute on quick view with line item detail on "OD" page

  @scenario6 @domain_customer @use_project @rebuy_da @use_regression
  Scenario Outline: Verify "buy again" button is not visible on Order Details page for non-eligible line items as a guest user
    Given I visit order history page as a guest user
    When I select "<shipment_status>" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    Then I should not see buy again button for "<shipment_status>" on "OD" page
    Examples:
      | shipment_status                   |
      | rebuy_processing                  |
      | rebuy_egc                         |
      | rebuy_upc_unavailable             |
      | rebuy_upc_available_not_available |
      | rebuy_gwp                         |


  @scenario7 @domain_customer @use_project @use_regression
  Scenario Outline: Verify item is getting added to bag when user select more than 1 product to bag using Buy Again in OD page
    Given I visit order history page as a guest user
    When I select "<order_status>" order as a "guest" user
    And I lookup with order number with zipcode in OH page
    Then I should see "buy again" button on "OD"
    When I select rebuy button on "OD" page
    Then I should see repurchase dialog opens on "OD"
    When I select add to bag button on quick view page
    Then I should see item getting added to bag successfully
    When I select second rebuy button on "OD" page
    Then I should see repurchase dialog opens on "OD"
    When I select add to bag button on quick view page
    Then I should see item getting added to bag successfully
    Examples:
      | order_status             |
      | multiple_buy_again_items |

  @scenario8 @domain_customer @use_project
  Scenario Outline: Verify item is getting added to bag when user changes the product default attributes and added to bag on OD page
    Given I visit the web site as a guest user
    When I select "<order_status>" order as a "signed" user
    When I navigate to "<order_status>" order details page as a "signed" user
    Then I should see "buy again" button on "OD"
    When I select rebuy button on "OD" page
    Then I should see repurchase dialog opens on "OD"
    When I modify product attributes on quick view
    And I select add to bag button on quick view page
    Then I should see item getting added to bag successfully
    Examples:
      | order_status                    |
      | rebuy_delivered_with_size_color |

  @scenario9 @domain_customer @project_rebuy @b-84354 @use_project @rebuy_da @use_regression
  Scenario Outline: Verify navigeted PDP page by clicking on product image link on Order Details page for eligible orders
    Given I visit the web site as a guest user
    When I select "<order_status>" order as a "signed" user
    When I navigate to "<order_status>" order details page as a "signed" user
    Then I should able to navigate to PDP page by clicking on "<link>" on "OD" page for rebuy line item

    Examples:
      | order_status    | link          |
      | rebuy_delivered | product_image |

  @scenario10 @domain_customer @project_rebuy @b-84354 @use_project @rebuy_da @use_regression
  Scenario Outline: Verify navigeted PDP page by clicking on product name link on Order Details page for eligible orders
    Given I visit the web site as a guest user
    When I select "<order_status>" order as a "signed" user
    When I navigate to "<order_status>" order details page as a "signed" user
    Then I should able to navigate to PDP page by clicking on "<link>" on "OD" page for rebuy line item

    Examples:
      | order_status    | link         |
      | rebuy_delivered | product_name |