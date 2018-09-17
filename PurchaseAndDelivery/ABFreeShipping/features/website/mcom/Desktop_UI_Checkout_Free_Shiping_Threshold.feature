Feature: Free shipping threshold experimentation UI checkout scenarios
# Pre-requisite
  #1. FST promotions should be active in respective environment
  #2. FST products test data should be available
  #3. SUPC Free shipping promotion(19860888) should be active and applicable for the FST product

# ********************* Checkout with normal items more than threshold **********************************
  @Desktop_Checkout_FST @scenario_1 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_2 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_3 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_4 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items -- Signed In user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_5 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
#      | 1614          | 4   |
      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_6 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

# ********************* Checkout with normal items less than threshold **********************************
  @Desktop_Checkout_FST @scenario_7 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "guest" user
    Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should not see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 4   |
      | 1614          | 2   |
      | 1615          | 1   |


  @Desktop_Checkout_FST @scenario_8 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    Then I should not see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should not see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 4   |
#      | 1614          | 2   |
      | 1615          | 1   |


  @Desktop_Checkout_FST @scenario_9 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should not see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 4   |
#      | 1614          | 2   |
      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_10 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items -- Signed In user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should not see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |
#      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_11 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should not see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should not see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
#      | 1614          | 2   |
#      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_12 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is not met for normal items in express paypal flow -- Signed In flow
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    Then I should not see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should not see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 4   |
#      | 1614          | 2   |
      | 1615          | 1   |


# ********************* Checkout with normal items more than threshold + Premium shipping method  **********************************

  @Desktop_Checkout_FST @scenario_13 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with premium charges is applied when threshold is met -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I select premium in shipping methods
    And I checkout until I reach the payment page as a "guest" user
    Then I should see premium shipping cost in order summary section
    When I checkout until I reach the order review page as a "guest" user
    Then I should see premium shipping cost in order summary section
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_14 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with premium charges is applied when threshold is met in standard Paypal flow-- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I select premium in shipping methods
    And I checkout until I reach the payment page as a "guest" user
    Then I should see premium shipping cost in order summary section
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see premium shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
      | 1616          | 1   |


  @Desktop_Checkout_FST @scenario_15 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with premium charges is applied when threshold is met -- Signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select premium in shipping method on responsive checkout sign in page
    Then I should see premium shipping cost in order summary section
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see premium shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_16 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with premium charges is applied when threshold is met in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select premium in shipping method on responsive checkout sign in page
    Then I should see premium shipping cost in order summary section
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see premium shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
#      | 1614          | 4   |
      | 1615          | 2   |
#      | 1616          | 1   |

 # ********************* Checkout with normal items more than threshold + Express shipping method  **********************************

  @Desktop_Checkout_FST @scenario_17 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with express charges is applied when threshold is met -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I select express in shipping methods
    And I checkout until I reach the payment page as a "guest" user
    Then I should see express shipping cost in order summary section
    When I checkout until I reach the order review page as a "guest" user
    Then I should see express shipping cost in order summary section
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_18 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with express charges is applied when threshold is met in standard Paypal flow-- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I select express in shipping methods
    And I checkout until I reach the payment page as a "guest" user
    Then I should see express shipping cost in order summary section
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see express shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_19 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with express charges is applied when threshold is met -- Signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select express in shipping method on responsive checkout sign in page
    Then I should see express shipping cost in order summary section
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see express shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
#      | 1614          | 4   |
      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_20 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion with express charges is applied when threshold is met in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select express in shipping method on responsive checkout sign in page
    Then I should see express shipping cost in order summary section
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see express shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

# ********************* Checkout with SDD items more than threshold **********************************

  @Desktop_Checkout_FST @scenario_21 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion with SDD charges is applied when threshold is met -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_sdd" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
   # Then I should see free shipping promotion applied in "order summary"
    When I enter sdd_eligible address on shipping page for responsive guest user
    And I select sdd_shipping in shipping methods
    And I checkout until I reach the payment page as a "guest" user
    Then I should see sdd shipping cost in order summary section
    When I checkout until I reach the order review page as a "guest" user
    Then I should see sdd shipping cost in order summary section
    When I checkout until I reach the order confirmation page as a "guest" user
   # Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_22 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion with SDD charges is applied when threshold is met in paypal standard flow-- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_sdd" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I enter sdd_eligible address on shipping page for responsive guest user
    And I select sdd_shipping in shipping methods
    And I checkout until I reach the payment page as a "guest" user
    Then I should see sdd shipping cost in order summary section
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see sdd shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |


  @Desktop_Checkout_FST @scenario_23 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion with SDD charges is applied when threshold is met -- Signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_sdd" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I enter sdd_eligible address on shipping page for responsive signed in user
    And I select sdd_shipping in shipping method on responsive checkout sign in page
    Then I should see sdd shipping cost in order summary section
    When I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see sdd shipping cost in order summary section
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see sdd shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_24 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion with sdd shipping charges is applied when threshold is met in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_sdd" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping page as a "responsive singed in" user
    Then I should see free shipping promotion applied in "order summary"
    And I enter sdd_eligible address on shipping page for responsive signed in user
    And I select sdd_shipping in shipping method on responsive checkout sign in page
    Then I should see sdd shipping cost in order summary section
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see sdd shipping cost in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |


# ********************* Checkout with BOPS items more than threshold **********************************
  @Desktop_Checkout_FST @scenario_25 @ab_free_shipping @use_regression
  Scenario Outline: Verify bops shipping when threshold is met -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_26 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is met in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page as a "bops" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_27 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is met in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_28 @ab_free_shipping @use_regression
  Scenario Outline: Verify bops shipping when threshold is met -- Signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the order review page as a "responsive signed in bops" user
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_29 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is met in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in bops" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |


  @Desktop_Checkout_FST @scenario_30 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is met in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

# ********************* Checkout with BOPS items less than threshold **********************************

  @Desktop_Checkout_FST @scenario_31 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_32 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page as a "bops" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_33 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_34 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met -- Signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the order review page as a "responsive signed in bops" user
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_35 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in bops" user
    Then I should see bops shipping in order summary section
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |

  @Desktop_Checkout_FST @scenario_36 @ab_free_shipping
  Scenario Outline: Verify bops shipping when threshold is not met in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is less than "<segment_value>"
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 3   |
      | 1615          | 1   |

# ********************* Checkout with BOPS + normal items more than threshold **********************************
  @Desktop_Checkout_FST @scenario_37 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping for bops and normal items when threshold is met -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    And I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "bops" user
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_38 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is met in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |


  @Desktop_Checkout_FST @scenario_39 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is met in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see bops shipping in order summary section
    And I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_40 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping for bops and normal items when threshold is met  -- Signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in bops" user
    Then I should see bops shipping in order summary section
    And I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive singed in bops" user
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 5   |
      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_41 @ab_free_shipping
  Scenario Outline: Verify that shipping for bops and normal items when threshold is met in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in bops" user
    Then I should see bops shipping in order summary section
    And I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_42 @ab_free_shipping
  Scenario Outline: Verify that shipping for bops and normal items when threshold is met in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    And I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see bops shipping in order summary section
    And I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  # ********************* Checkout with BOPS + normal items less than threshold **********************************
  @Desktop_Checkout_FST @scenario_43 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    And I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "bops" user
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |

  @Desktop_Checkout_FST @scenario_44 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the payment page as a "bops" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |


  @Desktop_Checkout_FST @scenario_45 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |

  @Desktop_Checkout_FST @scenario_46 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met  -- Signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in bops" user
    Then I should see bops shipping in order summary section
    And I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive singed in bops" user
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |

  @Desktop_Checkout_FST @scenario_47 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive singed in bops" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |

  @Desktop_Checkout_FST @scenario_48 @ab_free_shipping
  Scenario Outline: Verify free shipping for bops and normal items when threshold is not met in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_bops" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I select pick up option for bops item after selecting available store
    And I add an "FST_product" product to my bag and select checkout
    And I verify order subtotal is less than "<segment_value>"
    Then I should see bops shipping in order summary on shopping bag page
    And I should not see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see bops shipping in order summary section
    And I should not see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | qty |
      | 1490          | 4   |
      | 1614          | 2   |

# ********************* Checkout with beauty items **********************************
  @Desktop_Checkout_FST @scenario_49 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping promotion for beauty items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_beauty" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see beauty free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 8   |
#      | 1614          | 4   |
      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_50 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping promotion for beauty items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_beauty" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the payment page as a "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see beauty free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 5   |
#      | 1614          | 4   |
#      | 1615          | 2   |
      | 1616          | 1   |


  @Desktop_Checkout_FST @scenario_51 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping promotion for beauty items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_beauty" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see beauty free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 5   |
      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_52 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping promotion for beauty items -- Signed In user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_beauty" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "signed in" user
    And I place an order
    Then I should see beauty free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 5   |
#      | 1614          | 4   |
      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_53 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping promotion for beauty items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_beauty" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see beauty free shipping promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 5   |
      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_54 @ab_free_shipping @use_regression
  Scenario Outline: Verify free shipping promotion for beauty items in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_beauty" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see beauty free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 5   |
#      | 1614          | 4   |
#      | 1615          | 2   |
#      | 1616          | 1   |


  # ********************* Checkout with registry items **********************************
  @Desktop_Checkout_FST @scenario_55 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for registry items -- Guest user
    Given I visit the web site as a registry user
    When I add "FST_registrable" product to my bag from BVR page
    And I sign out from my current profile
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to shopping bag page
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_56 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for registry items in standard paypal flow -- Guest user
    Given I visit the web site as a registry user
    When I add "FST_registrable" product to my bag from BVR page
    And I sign out from my current profile
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to shopping bag page
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the payment page as a "guest" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_57 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for registry items in express paypal flow -- Guest user
    Given I visit the web site as a registry user
    When I add "FST_registrable" product to my bag from BVR page
    And I sign out from my current profile
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to shopping bag page
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_58 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for registry items -- Signed In user
    Given I visit the web site as a registry user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I add "FST_registrable" product to my bag from BVR page
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_59 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for registry items in standard paypal flow -- Signed in user
    Given I visit the web site as a registry user
    And I have "<segment_value>" for SEGMENT cookie
    When I add "FST_registrable" product to my bag from BVR page
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_60 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for registry items in express paypal flow -- Signed in user
    Given I visit the web site as a registry user
    And I have "<segment_value>" for SEGMENT cookie
    When I add "FST_registrable" product to my bag from BVR page
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

    # ********************* Checkout with registry items and completion promocode **********************************
  @Desktop_Checkout_FST @scenario_61 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion along with completion promocode is applied when threshold is met for registry items -- Signed in user
    Given I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option
    And I should be navigated to the registry manager page
    And I save promocode displayed on registry manager page
    When I add "FST_registrable" product to my bag from BVR page
    And I have "<segment_value>" for SEGMENT cookie
    And I set "<qty>" in the shopping bag page
    And I apply registry promo code on the shopping bag page
    Then I should see updated order total on the shopping bag page
    When I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    And I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see registry completion promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 7   |
      | 1614          | 5   |
      | 1615          | 3   |
      | 1616          | 2   |

  @Desktop_Checkout_FST @scenario_62 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion along with completion promocode is applied when threshold is met for registry items in standard paypal -- Signed in user
    Given I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option
    And I should be navigated to the registry manager page
    And I save promocode displayed on registry manager page
    When I add "FST_registrable" product to my bag from BVR page
    And I have "<segment_value>" for SEGMENT cookie
    And I set "<qty>" in the shopping bag page
    And I apply registry promo code on the shopping bag page
    Then I should see updated order total on the shopping bag page
    When I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see registry completion promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 7   |
      | 1614          | 5   |
      | 1615          | 3   |
      | 1616          | 2   |

  @Desktop_Checkout_FST @scenario_63 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion along with completion promocode is applied when threshold is met for registry items in express paypal -- Signed in user
    Given I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option
    And I should be navigated to the registry manager page
    And I save promocode displayed on registry manager page
    When I add "FST_registrable" product to my bag from BVR page
    And I have "<segment_value>" for SEGMENT cookie
    And I set "<qty>" in the shopping bag page
    And I apply registry promo code on the shopping bag page
    Then I should see updated order total on the shopping bag page
    When I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see registry completion promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 7   |
      | 1614          | 5   |
      | 1615          | 3   |
      | 1616          | 2   |

  # ********************* Checkout with normal items more than threshold and promocode **********************************
  @Desktop_Checkout_FST @scenario_64 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_product " product to my bag
    And I navigate to shopping bag page
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see "VALPAK10" promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 7   |
#      | 1614          | 6   |
#      | 1615          | 6   |
#      | 1616          | 6   |

  @Desktop_Checkout_FST @scenario_65 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see "VALPAK10" promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 7   |
#      | 1614          | 6   |
#      | 1615          | 6   |
      | 1616          | 6   |

  @Desktop_Checkout_FST @scenario_66 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see "VALPAK10" promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 7   |
      | 1614          | 6   |
#      | 1615          | 6   |
#      | 1616          | 6   |

  @Desktop_Checkout_FST @scenario_67 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items -- Signed In user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see "VALPAK10" promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 7   |
#      | 1614          | 6   |
      | 1615          | 6   |
#      | 1616          | 6   |

  @Desktop_Checkout_FST @scenario_68 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see "VALPAK10" promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 7   |
#      | 1614          | 6   |
#      | 1615          | 6   |
#      | 1616          | 6   |

  @Desktop_Checkout_FST @scenario_69 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion along with promocode is applied when threshold is met for normal items in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "VALPAK10" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    And I should see "VALPAK10" promotion in DB
    Examples:
      | segment_value | qty |
#      | 1490          | 7   |
      | 1614          | 6   |
#      | 1615          | 6   |
#      | 1616          | 6   |

  # ********************* Checkout with normal items more than threshold and SUPC Free Shipping promo code **********************************


  @Desktop_Checkout_FST @scenario_70 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promocode is applied when threshold is met for normal items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_product " product to my bag
    And I navigate to shopping bag page
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order confirmation page as a "guest" user
    And I should see "X13823_2000000" promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_71 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promocode is applied when threshold is met for normal items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I checkout until I reach the payment page as a "guest" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    And I should see "X13823_2000000" promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_72 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promocode is applied when threshold is met for normal items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    And I should see "X13823_2000000" promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_73 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promocode is applied when threshold is met for normal items -- Signed In user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I checkout until I reach the order review page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    And I should see "X13823_2000000" promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_74 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promocode is applied when threshold is met for normal items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    Then I should see free shipping promotion applied in "order summary"
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    And I should see "X13823_2000000" promotion in DB
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

  @Desktop_Checkout_FST @scenario_75 @ab_free_shipping
  Scenario Outline: Verify SUPC free shipping promocode is applied when threshold is met for normal items in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    When I apply valid promo code "X13823_2000000" on shopping bag
    And I verify order subtotal is more than "<segment_value>"
    Then I should see free shipping promotion applied in "shopping bag"
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see free shipping promotion applied in "order summary"
    When I place an order
    And I should see "X13823_2000000" promotion in DB
    Examples:
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |

    # ********************* Checkout with VGC items more than threshold **********************************
  @Desktop_Checkout_FST @scenario_76 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for VGC items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 80.00  | ab@testing.com |
#      | 1614          | 50.00  | ab@testing.com |
#      | 1615          | 27.00  | ab@testing.com |
#      | 1616          | 15.00  | ab@testing.com |

  @Desktop_Checkout_FST @scenario_77 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for VGC items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I checkout until I reach the payment page as a "guest" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 75.00  | ab@testing.com |
      | 1614          | 60.00  | ab@testing.com |
      | 1615          | 25.00  | ab@testing.com |
      | 1616          | 10.00  | ab@testing.com |

  @Desktop_Checkout_FST @scenario_78 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for VGC items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 75.00  | ab@testing.com |
      | 1614          | 60.00  | ab@testing.com |
      | 1615          | 25.00  | ab@testing.com |
      | 1616          | 10.00  | ab@testing.com |

  @Desktop_Checkout_FST @scenario_79 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for VGC items -- Signed In user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I have "<segment_value>" for SEGMENT cookie
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I checkout until I reach the order confirmation page as a "responsive signed in" user
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 75.00  | ab@testing.com |
      | 1614          | 60.00  | ab@testing.com |
      | 1615          | 25.00  | ab@testing.com |
      | 1616          | 10.00  | ab@testing.com |

  @Desktop_Checkout_FST @scenario_80 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for VGC items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 75.00  | ab@testing.com |
      | 1614          | 60.00  | ab@testing.com |
      | 1615          | 25.00  | ab@testing.com |
      | 1616          | 10.00  | ab@testing.com |

  @Desktop_Checkout_FST @scenario_81 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for VGC items in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_product" product to my bag
    And I navigate directly to "VGC_item" page
    And I add VGC item with "<amount>" and "<email_ID>"
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | email_ID       |
      | 1490          | 75.00  | ab@testing.com |
      | 1614          | 60.00  | ab@testing.com |
      | 1615          | 25.00  | ab@testing.com |
      | 1616          | 10.00  | ab@testing.com |

    # ********************* Checkout with EGC items more than threshold **********************************

  @Desktop_Checkout_FST @scenario_82 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for EGC items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | EGC_item |
      | 1490          | 80.00  | 35352360 |
      | 1614          | 50.00  | 35352360 |
      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |

  @Desktop_Checkout_FST @scenario_83 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for EGC items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I checkout until I reach the payment page as a "guest" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | EGC_item |
      | 1490          | 80.00  | 35352360 |
      | 1614          | 50.00  | 35352360 |
      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |

  @Desktop_Checkout_FST @scenario_84 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for EGC items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | EGC_item |
      | 1490          | 80.00  | 35352360 |
      | 1614          | 50.00  | 35352360 |
      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |


  @Desktop_Checkout_FST @scenario_85 @ab_free_shipping @use_regression
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for EGC items -- Signed In user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I checkout until I reach the order confirmation page as a "responsive signed in" user
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | EGC_item |
#      | 1490          | 80.00  | 35352360 |
#      | 1614          | 50.00  | 35352360 |
#      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |

  @Desktop_Checkout_FST @scenario_86 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for EGC items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | EGC_item |
      | 1490          | 80.00  | 35352360 |
      | 1614          | 50.00  | 35352360 |
      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |

  @Desktop_Checkout_FST @scenario_87 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for EGC items in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_product" product to my bag
    And I add "<EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | EGC_item |
      | 1490          | 80.00  | 35352360 |
      | 1614          | 50.00  | 35352360 |
      | 1615          | 27.00  | 35352360 |
      | 1616          | 15.00  | 35352360 |

 # ********************* Checkout with personalized EGC items more than threshold **********************************

  @Desktop_Checkout_FST @scenario_88 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for personalized EGC items -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |

  @Desktop_Checkout_FST @scenario_89 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for personalized EGC items in standard paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I checkout until I reach the payment page as a "guest" user
    When I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |

  @Desktop_Checkout_FST @scenario_90 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for personalized EGC items in express paypal flow -- Guest user
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |

  @Desktop_Checkout_FST @scenario_91 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for personalized EGC items -- Signed In user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I checkout until I reach the order confirmation page as a "responsive signed in" user
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |

  @Desktop_Checkout_FST @scenario_92 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for personalized EGC items in standard paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |

  @Desktop_Checkout_FST @scenario_93 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for personalized EGC items in express paypal flow -- Signed in user
    Given I visit the web site as a registered user
    When I have "<segment_value>" for SEGMENT cookie
    And I add a "FST_product" product to my bag
    And I add "<personalized_EGC_item>" of "<amount>" to bag
    And I navigate to shopping bag page
    Then I should see free shipping in shopping bag
    When I change the cookie to "responsive"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see 0 base fee in DB
    And I should not see "<segment_value>" order note in DB
    Examples:
      | segment_value | amount | personalized_EGC_item |
      | 1490          | 80.00  | 35352391              |
      | 1614          | 50.00  | 35352391              |
      | 1615          | 27.00  | 35352391              |
      | 1616          | 15.00  | 35352391              |


  # ********************* Checkout with normal items more than threshold in iship **********************************
  @Desktop_Checkout_FST @scenario_94 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is not applied when threshold is met for normal items in Ishipmode
    Given I visit the web site as a guest user
    When I have "<segment_value>" for SEGMENT cookie
    And I add an "FST_product" product to my bag and select checkout
    And I set "<qty>" in the shopping bag page
    And I verify order subtotal is more than "<segment_value>"
    And I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page
    Examples:
      | segment_value | qty |
      | 1490          | 6   |
      | 1614          | 4   |
      | 1615          | 2   |
      | 1616          | 1   |