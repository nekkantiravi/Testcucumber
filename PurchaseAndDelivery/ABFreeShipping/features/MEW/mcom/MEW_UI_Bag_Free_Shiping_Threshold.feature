Feature: Free shipping threshold experimentation on MEW shopping Bag
# Pre-requisite
  #1. FST promotions should be active in respective environment
  #2. FST products test data should be available
  #3. SUPC Free shipping promotion(19860888) should be active and applicable for the FST product


# ********************* Normal items more than threshold **********************************
  @MEW_Bag_FST @scenario_1 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @MEW_Bag_FST @scenario_2 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

# ********************* Add another item more than threshold **********************************
  @MEW_Bag_FST @scenario_3 @ab_free_shipping
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

  @MEW_Bag_FST @scenario_4 @ab_free_shipping
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

  # ********************* Normal items less than threshold **********************************
  @MEW_Bag_FST @scenario_5 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |
      | 1615          | 1   |

  @MEW_Bag_FST @scenario_6 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |
      | 1615          | 1   |

    # ********************* VGC items more than threshold **********************************
  @MEW_Bag_FST @scenario_7 @ab_free_shipping
  Scenario Outline: Verify free shipping for VGC items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page from add to bag page
    Then I should see free shipping in shopping bag
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 80.00  | ab@testing.com |
      | 1614          | 50.00  | ab@testing.com |
      | 1615          | 27.00  | ab@testing.com |
      | 1616          | 15.00  | ab@testing.com |

  @MEW_Bag_FST @scenario_8 @ab_free_shipping
  Scenario Outline: Verify free shipping for VGC items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page from add to bag page
    Then I should see free shipping in shopping bag
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 75.00  | ab@testing.com |
      | 1614          | 60.00  | ab@testing.com |
      | 1615          | 25.00  | ab@testing.com |
      | 1616          | 10.00  | ab@testing.com |

  # ********************* EGC items more than threshold **********************************
  @MEW_Bag_FST @scenario_9 @ab_free_shipping
  Scenario Outline: Verify free shipping for EGC items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page using mobile website
    Then I should see free shipping in shopping bag
    Examples:
      | segment_value | amount | EGC_item |
      | 1490          | 80.00  | 35352360 |
      | 1614          | 50.00  | 35352360 |
      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |

  @MEW_Bag_FST @scenario_10 @ab_free_shipping
  Scenario Outline: Verify free shipping for EGC items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page using mobile website
    Then I should see free shipping in shopping bag
    Examples:
      | segment_value | amount | EGC_item |
      | 1490          | 80.00  | 35352360 |
      | 1614          | 50.00  | 35352360 |
      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |

   # ********************* Personalized EGC items more than threshold **********************************
  @MEW_Bag_FST @scenario_11 @ab_free_shipping
  Scenario Outline: Verify free shipping for personalized EGC items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page using mobile website
    Then I should see free shipping in shopping bag
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |

  @MEW_Bag_FST @scenario_12 @ab_free_shipping
  Scenario Outline: Verify free shipping for personalized EGC items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page using mobile website
    Then I should see free shipping in shopping bag
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |

# ********************* EGC + VGC + Normal(less than threshold) items more than threshold **********************************
  @MEW_Bag_FST @scenario_13 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when bag contains normal item less than threshold and EGC and VGC items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I verify order subtotal is less than "<segment_value>"
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page using mobile website
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | amount | EGC_item | email_ID       |
      | 1490          | 80.00  | 35352360 | ab@testing.com |
      | 1614          | 50.00  | 35352360 | ab@testing.com |
      | 1615          | 27.00  | 35352360 | ab@testing.com |

  @MEW_Bag_FST @scenario_14 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when bag contains normal item less than threshold and EGC and VGC items -- Signed In user
    Given I visit the mobile web site as a registered user
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I verify order subtotal is less than "<segment_value>"
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page using mobile website
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | amount | EGC_item | email_ID       |
      | 1490          | 80.00  | 35352360 | ab@testing.com |
      | 1614          | 50.00  | 35352360 | ab@testing.com |
      | 1615          | 27.00  | 35352360 | ab@testing.com |

    # ********************* EGC + VGC + Normal(more than threshold) items more than threshold **********************************
  @MEW_Bag_FST @scenario_15 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is  applied when bag contains normal item more than threshold and EGC and VGC items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page from add to bag page
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | amount | EGC_item | email_ID       | qty |
      | 1490          | 80.00  | 35352360 | ab@testing.com | 6   |
      | 1614          | 50.00  | 35352360 | ab@testing.com | 4   |
      | 1615          | 27.00  | 35352360 | ab@testing.com | 2   |
      | 1616          | 15.00  | 35352360 | ab@testing.com | 1   |

  @MEW_Bag_FST @scenario_16 @ab_free_shipping
  Scenario Outline: Verify that that free shipping promotion is applied when bag contains normal item more than threshold and EGC and VGC items -- Signed In user
    Given I visit the mobile web site as a registered user
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page from add to bag page
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | amount | EGC_item | email_ID       | qty |
      | 1490          | 80.00  | 35352360 | ab@testing.com | 6   |
      | 1614          | 50.00  | 35352360 | ab@testing.com | 4   |
      | 1615          | 27.00  | 35352360 | ab@testing.com | 2   |
      | 1616          | 15.00  | 35352360 | ab@testing.com | 1   |

# ********************* BOPS items more than threshold **********************************
  @MEW_Bag_FST @scenario_17 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @MEW_Bag_FST @scenario_18 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is met -- Signed in user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

# ********************* Checkout with BOPS items less than threshold **********************************
  @MEW_Bag_FST @scenario_19 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |


  @MEW_Bag_FST @scenario_20 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met -- Signed in user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |

# ********************* BOPS + normal items more than threshold **********************************
  @MEW_Bag_FST @scenario_21 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @MEW_Bag_FST @scenario_22 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is met  -- Signed in user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  # ********************* BOPS + normal items less than threshold **********************************
  @MEW_Bag_FST @scenario_23 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |

  @MEW_Bag_FST @scenario_24 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met  -- Signed in user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I add an "FST_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |

# ********************* Beauty item **********************************
  @MEW_Bag_FST @scenario_25 @ab_free_shipping
  Scenario Outline: Verify free shipping promotion for beauty items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_beauty" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 8   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @MEW_Bag_FST @scenario_26 @ab_free_shipping
  Scenario Outline: Verify free shipping promotion for beauty items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_beauty" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

    # ********************* Beauty item + Normal item  **********************************
  @MEW_Bag_FST @scenario_27 @ab_free_shipping
  Scenario Outline: Verify free shipping promotion for beauty + normal items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_beauty" product to my bag using mobile website and select checkout
    And I add an "FST_product" product to my bag using mobile website and select checkout
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value |
      | 1490          |
      | 1614          |
      | 1615          |
      | 1616          |

  @MEW_Bag_FST @scenario_28 @ab_free_shipping
  Scenario Outline: Verify free shipping promotion for beauty + normal items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_beauty" product to my bag using mobile website and select checkout
    And I add an "FST_product" product to my bag using mobile website and select checkout
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value |
      | 1490          |
      | 1614          |
      | 1615          |
      | 1616          |

  # ********************* Registry items **********************************
  @MEW_Bag_FST @scenario_29 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for registry items -- Signed In user
    Given I visit the mobile web site as a registry user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "FST_registrable" product to my bag from BVR page using mobile website and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value |
      | 1616          |

    # ********************* Registry items and completion promocode **********************************
  @MEW_Bag_FST @scenario_30 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion along with completion promocode is applied when threshold is met for registry items -- Signed in user
    Given I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option on mobile site
    And I navigate to the mobile registry manager page
    And I save promocode displayed on mobile registry manager page
    And I have "<segment_value>" for SEGMENT cookie
    When I add "FST_registrable" product to my bag from BVR page using mobile website and select checkout
    And I apply registry promo code on mobile shopping bag page
    Then I should see registry promocode is applied on mobile shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value |
      | 1616          |

  # ********************* Normal items more than threshold and promocode **********************************
  @MEW_Bag_FST @scenario_31 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 7   |
      | 1614          | 6   |
      | 1615          | 6   |
      | 1616          | 6   |

  @MEW_Bag_FST @scenario_32 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 7   |
      | 1614          | 6   |
      | 1615          | 6   |
      | 1616          | 6   |

    # ********************* Normal items and promocode **********************************
  @MEW_Bag_FST @scenario_33 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is reduced after applying promocode for normal items -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |

  @MEW_Bag_FST @scenario_34 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is reduced after applying promocode for normal items -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |

  # ********************* Normal items more than threshold and SUPC Free Shipping promo code **********************************
  @MEW_Bag_FST @scenario_35 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promotion is applied -- Guest user
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @MEW_Bag_FST @scenario_36 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promotion is applied -- Signed In user
    Given I visit the mobile web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  # ********************* Normal items more than threshold in iship **********************************
  @MEW_Bag_FST @scenario_37 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for normal items in Ishipmode
    Given I visit the mobile web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag using mobile website and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |
