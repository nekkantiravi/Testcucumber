Feature: MEW merge bag Free shipping threshold scenarios
 # Pre-requisite
  #1. FST promotions should be active in respective environment
  #2. FST products test data should be available

  @MEW_merge_bag @scenario_1 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied in merge bag if threshold is met
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I sign in to my same profile using mobile website
    And I navigate to shopping bag page using mobile website
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |


  @MEW_merge_bag @scenario_2 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied in merge bag if threshold is met in addition with current bag items
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty_2>" in the shopping bag page
    And I navigate to shopping bag page using mobile website
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 1     | 5     |
      | 1614          | 1     | 4     |
      | 1615          | 1     | 2     |
      | 1616          | 1     | 1     |

  @MEW_merge_bag @scenario_3 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when we add current bag items to current bag in addition with current bag items during checkout
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 1     | 6     |
      | 1614          | 1     | 4     |
      | 1615          | 1     | 2     |
      | 1616          | 1     | 1     |

  @MEW_merge_bag @scenario_4 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when we add current bag items to current bag along with current bag items
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @MEW_merge_bag @scenario_5 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when we remove current bag items with current bag items (met threshold)
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to shopping bag page using mobile website
    And I remove current bag items
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 7     |
      | 1614          | 4     | 5     |
      | 1615          | 2     | 3     |
      | 1616          | 1     | 2     |

  @MEW_merge_bag @scenario_6 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is removed when we remove current bag items so that threshold is not met
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to shopping bag page using mobile website
    And I remove current bag items
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 6     | 1     |
      | 1614          | 5     | 1     |
      | 1615          | 4     | 1     |


  @MEW_merge_bag @scenario_7 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied even when we increase current bag items quantity
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I navigate to shopping bag page using mobile website
    When I set "<qty_3>" in the shopping bag page
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 | qty_3 |
      | 1490          | 1     | 2     | 5     |
      | 1614          | 1     | 2     | 3     |

  @MEW_merge_bag @scenario_8 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when we increase current bag items quantity
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I navigate to shopping bag page using mobile website
    When I set "<qty_3>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 | qty_3 |
      | 1490          | 1     | 4     | 6     |
      | 1614          | 1     | 2     | 4     |
      | 1615          | 1     | 2     | 2     |

  @MEW_merge_bag @scenario_9 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is removed when we decrease current bag items quantity
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    And I navigate to shopping bag page using mobile website
    When I set "<qty_3>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 | qty_3 |
      | 1490          | 1     | 6     | 1     |
      | 1614          | 1     | 4     | 1     |
      | 1615          | 1     | 2     | 1     |

  # ----------------------    Promocode in merge bag    -------------------------------

  @MEW_merge_bag @scenario_10 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when order total reduces less than threshold values after applying promocode in merge bag
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I apply valid promo code "VALPAK10" on shopping bag
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 1     | 7     |
      | 1614          | 1     | 6     |
      | 1615          | 1     | 6     |
      | 1616          | 1     | 6     |

  @MEW_merge_bag @scenario_11 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when order total is not less than threshold values after applying promocode in merge bag
    Given I visit the mobile web site as a registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_1>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
    And I close and reopen the browser
    And I visit the mobile web site as a guest user
    And I sign in to my same profile using mobile website
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I navigate to shopping bag page using mobile website
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I apply valid promo code "VALPAK10" on shopping bag
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty_1 | qty_2 |
      | 1490          | 1     | 7     |
      | 1614          | 1     | 6     |
      | 1615          | 1     | 6     |



