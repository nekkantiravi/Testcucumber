## Project: REBUY
## EPIC: https://www14.v1host.com/Macyscom/Default.aspx?menu=EpicsPortfolioPlanningPage&feat-nav=m1
## Date updated: 9th May 2017

Feature: Verify the functionality of rebuy button for eligible products in order history page and order details page

  @scenario1 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify add to bag button is displaying on OH page for eligible line items
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should see add to bag button for "<line_item_status>" line item on "OH" page
    Examples:
      | line_item_status    |
      | rebuy_delivered     |
      | rebuy_intransit     |
      | rebuy_bops_pickedup |

  @scenario2 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify item is getting added successfully to bag or not upon user selecting add to bag on OH page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should see add to bag button for "<line_item_status>" line item on "OH" page
    When I tap on add to bag button on "OH" page
    Then I should see add to bag successful message on page
    Examples:
      | line_item_status    |
      | rebuy_delivered     |
      | rebuy_intransit     |
      | rebuy_bops_pickedup |

  @scenario3 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify add to bag button is displaying on OD page for eligible line items by tapping on order
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    And I tap order details button for selected order
    Then I should see add to bag button for "<line_item_status>" line item on "OD" page
    Examples:
      | line_item_status    |
      | rebuy_delivered     |
      | rebuy_intransit     |
      | rebuy_bops_pickedup |

  @scenario4 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify item is getting added successfully to bag or not upon user selecting add to bag on OH page by tapping on order
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should see add to bag button for "<line_item_status>" line item on "OH" page
    When I tap order details button for selected order
    And I tap on add to bag button on "OD" page
    Then I should see add to bag successful message on page
    Examples:
      | line_item_status    |
      | rebuy_delivered     |
      | rebuy_intransit     |
      | rebuy_bops_pickedup |

  @scenario5 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify user able to add multiple items to bag from one multiple line item order on OD page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page to validate add to bag selection
    And I tap order details button for selected order
    And I tap on add to bag button on "OD" page for multiple line items
    Then I should see add to bag successful message on page for each item
    Examples:
      | line_item_status         |
      | multiple_buy_again_items |


  @scenario6 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify user able to add multiple items to bag from one multiple line item order on OH page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page to validate add to bag selection
    And I tap on add to bag button on "OH" page for multiple line items
    Then I should see add to bag successful message on page for each item
    Examples:
      | line_item_status         |
      | multiple_buy_again_items |


  @scenario7 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify add to bag button is not displaying on OH page for non eligible line items
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should not see add to bag button for "<line_item_status>" line item on "OH" page
    Examples:
      | line_item_status                        |
      | rebuy_processing                        |
      | rebuy_egc                               |
      | rebuy_vr_delivered                      |
      | rebuy_upc_unavailable                   |
      | rebuy_upc_unavailable_product_available |
      | rebuy_beautybox                         |
      | rebuy_gwp                               |

  @scenario8 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify add to bag button is not displaying on OD page for non eligible line items by tapping on order from OH page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    And I tap order details button for selected order
    Then I should not see add to bag button for "<line_item_status>" line item on "OD" page
    Examples:
      | line_item_status                        |
      | rebuy_processing                        |
      | rebuy_egc                               |
      | rebuy_vr_delivered                      |
      | rebuy_upc_unavailable                   |
      | rebuy_upc_unavailable_product_available |
      | rebuy_beautybox                         |
      | rebuy_gwp                               |

  @scenario9 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify error message on OH page when selects more than available items to bag
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    And I tap on add to bag button of "<line_item_status>" on "OH" page for maximum item availability
    Then I should see error message on page
    Examples:
      | line_item_status |
      | rebuy_delivered  |

  @scenario10 @domain_customer @use_project @mew-84382 @use_regression
  Scenario Outline: Verify error message on OD page when selects more than available items to bag
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    And I tap order details button for selected order
    And I tap on add to bag button of "<line_item_status>" on "OD" page for maximum item availability
    Then I should see error message on page
    Examples:
      | line_item_status    |
      | rebuy_bops_pickedup |

  @scenario11 @domain_customer @mew-84382 @use_project @use_regression
  Scenario Outline: Verify user navigating to PDP page on OH page
    Given I visit the mobile web site as a guest user
    When I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    When I select product name on "OH" for rebuy line item
    Then I should be redirected to PDP page using mobile website
    Examples:
      | line_item_status |
      | rebuy_delivered  |

  @scenario12 @domain_customer @mew-84382 @use_project @use_regression
  Scenario Outline: Verify user navigating to PDP page on OD page
    Given I visit the mobile web site as a guest user
    When I associate "<line_item_status>" order in db
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    When I tap order details button for selected order
    And I select product name on "OD" for rebuy line item
    Then I should be redirected to PDP page using mobile website
    Examples:
      | line_item_status |
      | rebuy_delivered  |