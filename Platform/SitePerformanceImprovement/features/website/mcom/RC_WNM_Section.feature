Feature: Validate Worry No More section view in Responsive checkout page

  @domain_Site_performance_Optimization @RC_BT_Checkout
  Scenario Outline: As a guest/signed in user place an order with BT item having NO WNM plan
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout
  Scenario Outline: As a guest/signed in user place an order with BT mattress by checking "COI" in White glove special instructions Questionnaire
    Given I visit the web site as a <user_type> user
    And I add a big ticket "BT_mattress and ONHAND" product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the services & fees page as an "BT <user_type>" user
    And I select "Building requires Certificate of Insurance for deliveries"
    And I select default WNM plan
    And I select default Mattress plan
    And I continue checkout until I reach the schedule delivery page as an "BT <user_type>" user
    Then I see "Call to Schedule" message in schedule delivery section
    And I verify big ticket order summary:
      | WNM        | true |
      | delivery   | true |
      | removalFee | true |
    And I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    And I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout
  Scenario Outline: As a guest/signed Place order with BT furniture available item, by selecting WNM plans & verify order details
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the services & fees page as a "BT <user_type>" user
    And I select default WNM plan
    And I continue checkout until I reach the order review page as an "BT <user_type>" user
    And I verify big ticket order summary:
      | WNM | true |
    And I tap "place order" button in "order review" section
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout
  Scenario Outline: As a guest/signed place an order with outdoor furniture adding product from master PDP with "Delivery up more than 3 flights of stairs" in White glove special instructions Questionnaire
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the services & fees page as a "BT <user_type>" user
    And I select "Delivery up more than 3 flights of stairs"
    And I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout
  Scenario Outline: As a guest/signed in user place an order an order with BT + ST, with WNM plan & schedule date
    Given I visit the web site as a <user_type> user
    When I add a "ST_product and available and orderable" product to my bag
    And I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the services & fees page as an "BT <user_type>" user
    And I select default WNM plan
    And I continue checkout until I reach the order review page as an "BT <user_type>" user
    And I verify big ticket order summary:
      | WNM | true |
    And I tap "place order" button in "order review" section
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout
  Scenario Outline: As a guest/signed in user place an order an order with BT + BOPS, without WNM plan & schedule date
    Given I visit the web site as a <user_type> user
    When I add a "available_bops and orderable" product to my bag
    And I select pick up option for bops item after selecting available store
    And I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I continue checkout until I reach the order review page as an "BT <user_type>" user
    And I verify big ticket order summary:
      | WNM | false |
    And I tap "place order" button in "order review" section
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout
  Scenario Outline: As a guest/signed in user place an order with Mattresses item and bedding removal and recycling fees included
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_mattress and recycling_fee and ONHAND" product to bag
    And I checkout until I reach the order review page as an "BT <user_type>" user
    And I verify big ticket order summary:
      | recyclingFee | true  |
      | delivery     | true  |
      | WNM          | false |
    And I tap "place order" button in "order review" section
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  #------------------------ GCE senarios to validate the View----------------------------------------

  @domain_Site_performance_Optimization @RC_BT_Checkout @GCE
  Scenario Outline: As a guest/signed Validate the first view of Protection plan section of WNM view
    Given I visit the web site as a <user_type> user
    Then I Should see WNM Protection plan section
    And I should see rc wnm text
    And I should see View Plan Details link
    And I should see VNM plan and price
    And I should verify the product description
    And I select default WNM plan
    And I select default Mattress plan
    And I select "Delivery up more than 3 flights of stairs"
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout @GCE
  Scenario Outline: As a guest/signed Validate the first view of Mattress fees section of WNM view
    Given I visit the web site as a <user_type> user
    Then I Should see WNM Mattress fees section
    And I should see Mattress removal radio check box
    And I should see quantity dropdown box
    And I should see mattress removal price
    Then I should validate mandated recycling fee
    And I select mattress removal
    And I validate the mattress removal fee is added in the order
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @domain_Site_performance_Optimization @RC_BT_Checkout @GCE
  Scenario Outline: As a guest/signed Validate the first view of Furniture/mattress special instructions section of WNM view
    Given I visit the web site as a <user_type> user
    Then I Should see WNM Furniture/mattress special instructions section
    And I should see "Delivery to a military base" option
    And I should see "Delivery through a security gate" option
    And I should see "Delivery up more than 3 flights of stairs" option
    Then I should see "Building requires Certificate of Insurance for deliveries" option
     And I should see "None of above" option
    And I select "Delivery through a security gate"
    And I validate the special instructions added in the order
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

#--------------------GC environment scenarios -----------------------------------------------------
  @domain_Site_performance_Optimization @RC_BT_Checkout @GCE
  Scenario: As a guest/signed Validate the first view of uchk page
    Given I visit the GCE site as a guest user
    Then I Should see Continue shopping button
    And I should see shopping bag icon on the page
    And I Should see Continue shopping button