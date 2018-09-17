Feature: Free shipping threshold experimentation UI bag scenarios
# Pre-requisite
  #1. FST promotions should be active in respective environment
  #2. FST products test data should be available
  #3. SUPC Free shipping promotion(19860888) should be active and applicable for the FST product

  @Desktop_Bag_Free_Shipping @scenario_1 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items -- Guest user
    Given I visit the web site as a guest user
   #And I have "<segment_value>" for SEGMENT cookie
    And I add a VGC item "VGC_item" with "<amount>" and "<email ID>" to my bag
    And I add product to my bag from standard PDP Page
    And I navigate to shopping bag page
    And I set "<qty_2>" in the shopping bag page
    And I verify order sub total is less than "<segment_value>"
   # Then I should see free shipping promotion applied in "shopping bag"
    And I checkout until I reach the shipping page as a "guest" user
  # Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    When I checkout until I reach the order review page as a "guest" user
    When I checkout until I reach the order confirmation page as a "guest" user
   # Then I should not see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | amount | email ID        |
      | 1490          | 1     | $75.00 | abc@testing.com |
     # | 1614         | 1   |$45.00|abc@testing.com|
     # | 1615         |1    |$60.00|abc@testing.com|
     # | 1616         |1    |$70.00|abc@testing.com|


  @Desktop_Bag_Free_Shipping @scenario_2 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion is applied when threshold is met for normal items -- SignedIn user
    Given I visit the web site as a registered user
    And I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    When I search for "86800"
    When I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    Then I should not see free shipping promotion applied in "shopping" bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping & payment page as an "signed in" user
    Then I should not see free shipping promotion applied in "order summary" bag page
   # When I checkout until I reach the order review page as a "signed in" user
    #Then I should see free shipping in order summary section
    When I place an order
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | threshold    |
#      | 1490          |morethan_$75      |
#      | 1614          |morethan_$50      |
      | 1615          | morethan_$25 |
#      | 1616          |morethan_$0      |

  @Desktop_Bag_Free_Shipping @scenario_3 @ab_free_shipping
  Scenario Outline: Verify that shipping charges are not applied when bag contains BOPS items -- Guest user
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I search for "86800"
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    Then I should see free shipping promotion is applied
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should not see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | item_type         |
#      | 1490          |bops_morethan_$75      |
#      | 1614          |bops_morethan_$50      |
      | 1615          | bops_morethan_$25 |
#      | 1616          |bops_morethan_$0      |

  @scenario_4 @ab_free_shipping
  Scenario Outline: Verify that shipping charges are not applied when bag contains BOPS items -- signed in user
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I have "<segment_value>" for SEGMENT cookie
    And I add an "<item_type>" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I change the cookie to "responsive"
    And I checkout until I reach the shipping page as a "singed in" user
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | item_type         |
#      | 1490          |bops_morethan_$75      |
#      | 1614          |bops_morethan_$50      |
      | 1615          | bops_morethan_$25 |
#      | 1616          |bops_morethan_$0      |

  @scenario_5 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion with SDD charges is applied when threshold is met -- Guest user
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I add a "<item_type>" product to my bag
    And I navigate to shopping bag page
    Then I should see free shipping promotion is applied
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see free shipping in order summary section
    When I enter sdd_eligible address on shipping page for responsive guest user
    And I select sdd_shipping in shipping methods
    And I checkout until I reach the payment page as a "guest" user
    Then I should see sdd shipping cost in order summary section
    When I checkout until I reach the order review page as a "guest" user
    Then I should see sdd shipping cost in order summary section
    When I checkout until I reach the order confirmation page as a "guest" user
    Then I should see "<segment_value>" free shipping promotion in DB
    Examples:
      | segment_value | item_type        |
#      | 1490          |sdd_morethan_$75      |
#      | 1614          |sdd_morethan_$50      |
      | 1615          | sdd_morethan_$25 |
#      | 1616          |sdd_morethan_$0      |

  @a
  @scenario_6 @ab_free_shipping
  Scenario Outline: Verify that free shipping promotion should be removed when we reduce item quantity so that  threshold is not met -- Guest user
    Given I visit the web site as a guest user
   #And I have "<segment_value>" for SEGMENT cookie
    When I search for "VGC_item"
    And I enter "amount" and "email ID" to the VGC item
    And I add product to my bag from standard PDP Page
    And I navigate to shopping bag page
   # And I set "<qty_1>" to make order total more than "<segment_value>"
   # Then I should see free shipping promotion applied in "shopping bag"
    And I checkout until I reach the shipping page as a "guest" user
  # Then I should not see free shipping promotion applied in "order summary"
    When I checkout until I reach the payment page as a "guest" user
    When I checkout until I reach the order review page as a "guest" user
    When I checkout until I reach the order confirmation page as a "guest" user
   # Then I should not see "<segment_value>" free shipping promotion in DB

    Examples:
      | segment_value | qty_1 | amount | email ID        |
      | 1490          | 1     | $75.00 | abc@testing.com |
     # | 1614         | 1   |$45.00|abc@testing.com|
     # | 1615         |1    |$60.00|abc@testing.com|
     # | 1616         |1    |$70.00|abc@testing.com|
