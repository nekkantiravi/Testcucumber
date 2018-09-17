## Project: REBUY
## EPIC: https://www14.v1host.com/Macyscom/Default.aspx?menu=EpicsPortfolioPlanningPage&feat-nav=m1
## Date updated: 12th Sept 2017

Feature: Verify the functionality of rebuy button for eligible products in order details page

  @scenario1 @domain_customer @use_project
  Scenario Outline: Verify add to bag button is displaying on OD page for eligible line items
    Given I visit the mobile web site as a guest user
    When I select "<line_item_status>" order as a "signed" user
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should "see" add to bag button for "<line_item_status>" line item
    Examples:
      | line_item_status    |
      | rebuy_delivered     |
      | rebuy_intransit     |
      | rebuy_bops_pickedup |

  @scenario2 @domain_customer @use_project
  Scenario Outline: Verify item is getting added successfully to bag or not upon user selecting add to bag on OH page
    Given I visit the mobile web site as a guest user
    When I select "<line_item_status>" order as a "signed" user
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should be navigated to order details page
    When I tap on add to bag button of "<line_item_status>" on page
    Then I should see add to bag successful message on page
    Examples:
      | line_item_status    |
      | rebuy_delivered     |
      | rebuy_intransit     |
      | rebuy_bops_pickedup |

  @scenario3 @domain_customer @use_project
  Scenario Outline: Verify user able to add multiple items to bag from one multiple line item order on OD page
    Given I visit the mobile web site as a guest user
    When I select "<line_item_status>" order as a "signed" user
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should be navigated to order details page
    When I tap on add to bag button on page for multiple line items
    Then I should see add to bag successful message on page for each item
    Examples:
      | line_item_status         |
      | multiple_buy_again_items |

  @scenario4 @domain_customer @use_project
  Scenario Outline: Verify add to bag button is not displaying on OH page for non eligible line items
    Given I visit the mobile web site as a guest user
    When I select "<line_item_status>" order as a "signed" user
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should be navigated to order details page
    Then I should "not see" add to bag button for "<line_item_status>" line item
    Examples:
      | line_item_status                  |
      | rebuy_processing                  |
      | rebuy_egc                         |
      | rebuy_upc_unavailable             |
      | rebuy_upc_available_not_available |
      | rebuy_gwp                         |

  @scenario5 @domain_customer @use_project
  Scenario Outline: Verify error message on OD page when selects more than available items to bag
    Given I visit the mobile web site as a guest user
    When I select "<line_item_status>" order as a "signed" user
    And I navigate to oh page from my account page using mobile website
    And I tap order "<line_item_status>" on history page
    Then I should be navigated to order details page
    When I tap on add to bag button of "<line_item_status>" on OD page for maximum item availability
    Then I should see error message on page
    Examples:
      | line_item_status |
      | rebuy_delivered  |

