Feature: Free shipping threshold experimentation MEW Update Bag Scenarios
# Pre-requisite
  #1. FST promotions should be active in respective environment
  #2. FST products test data should be available

  @scenario_1 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion should be removed when we reduce item quantity so that threshold is not met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page using mobile website as a "guest" user
   # Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page using mobile website as a "guest" user
    Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page using mobile website as a "guest" user
    Then I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should not see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 5     |
      | 1614          | 4     | 3     |
      | 1615          | 2     | 1     |
     # | 1616          |1    |4    |

  @scenario_2 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion should be removed when we reduce item quantity so that  threshold is not met -- signed in user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should not see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 5     |
      | 1614          | 4     | 3     |
      | 1615          | 2     | 1     |
     # | 1616          |1    |4    |

  @scenario_3 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion should be applied when we increase item quantity so that threshold is met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page using mobile website as a "guest" user
   # Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page using mobile website as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page using mobile website as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 5     | 6     |
      | 1614          | 3     | 4     |
      | 1615          | 1     | 2     |
     #| 1616          |1    |4    |

  @scenario_4 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion should be applied when we increase item quantity so that threshold is met -- signed in user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 5     | 6     |
      | 1614          | 3     | 4     |
      | 1615          | 1     | 2     |
     #| 1616          |1    |4    |

  @scenario_5 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion should be applied when we increase item quantity -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page using mobile website as a "guest" user
   # Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page using mobile website as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page using mobile website as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 1     |
      | 1614          | 4     | 1     |
      | 1615          | 2     | 1     |
     # | 1616          |1    |1    |

  @scenario_6 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion should be applied when we increase item quantity -- signed in user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 1     |
      | 1614          | 4     | 1     |
      | 1615          | 2     | 1     |
     # | 1616          |1    |1    |

  @scenario_7 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is removed when we remove current bag items so that threshold is not met merge bag
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my existing profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_2>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I remove current bag items
    Then I should not see free shipping promotion applied in "shopping bag"

    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 6     |
      | 1614          | 4     | 4     |
      | 1615          | 2     | 2     |
     # | 1616          |1    |1    |

  # ********************* Reduce item quantity **********************************
  @ab_free_shipping @scenario_8
  Scenario Outline: Verify that free shipping promotion is removed when we reduce the item quantity so that threshold is not met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I set "<qty_2>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 4     |
      | 1614          | 4     | 2     |
      | 1615          | 2     | 1     |

  @ab_free_shipping @scenario_9
  Scenario Outline: Verify that free shipping promotion is removed when we reduce the item quantity so that threshold is not met -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I set "<qty_2>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 4     |
      | 1614          | 4     | 2     |
      | 1615          | 2     | 1     |

    # ********************* Increase item quantity **********************************
  @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when we increase the item quantity so that threshold is met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I set "<qty_2>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free ship @scenario_10ping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 4     | 6     |
      | 1614          | 2     | 4     |
      | 1615          | 1     | 2     |

  @ab_free_shipping @scenario_11
  Scenario Outline: Verify that free shipping promotion is applied when we increase the item quantity so that threshold is met -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I set "<qty_2>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 4     | 6     |
      | 1614          | 2     | 4     |
      | 1615          | 1     | 2     |

    # ********************* Increase item quantity after threshold is met **********************************
  @ab_free_shipping @scenario_12
  Scenario Outline: Verify that free shipping promotion is applied when we increase the item quantity after threshold is met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I set "<qty_2>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 4     | 6     |
      | 1614          | 2     | 4     |
      | 1615          | 1     | 2     |

  @ab_free_shipping @scenario_13
  Scenario Outline: Verify that free shipping promotion is applied when we increase the item quantity after threshold is met -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I set "<qty_2>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 4     | 6     |
      | 1614          | 2     | 4     |
      | 1615          | 1     | 2     |

    # ********************* Add another item more than threshold **********************************
  @ab_free_shipping @scenario_14
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met after adding another item -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 3   |
      | 1615          | 1   |

  @ab_free_shipping @scenario_15
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met after adding another item -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 3   |
      | 1615          | 1   |


