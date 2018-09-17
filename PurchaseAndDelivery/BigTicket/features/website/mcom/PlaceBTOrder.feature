Feature: Big ticket PDP and Place order scenarios

  @project_BT @domain_purchase_and_delivery @scenario1
  Scenario: Verify the BT available message, on PDP page
    Given I visit the web site as a guest user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "ONHAND" availability message on BT PDP page as:
      | In stock and available for delivery to <zip_code>. |

  @project_BT @domain_purchase_and_delivery @scenario2
  Scenario: Verify the BT message for not eligible state, on PDP page
    Given I visit the web site as a guest user
    When I navigate to "BT_furniture and NOT_ELIGIBLE_STATE" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "NOT_ELIGIBLE_STATE" availability message on BT PDP page as:
      | For availability and pricing in Hawaii and Puerto Rico, please call 1-800-289-6229. |

  @project_BT @domain_purchase_and_delivery @scenario3
  Scenario: Verify the BT unavailable error message, on PDP page
    Given I visit the web site as a guest user
    When I navigate to "BT_furniture and NOT_AVAILABLE" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "NOT_AVAILABLE" availability message on BT PDP page as:
      | This item is not available for delivery in your area. |

  @project_BT @domain_purchase_and_delivery @scenario4
  Scenario: Verify the BT backorder message, on PDP page
    Given I visit the web site as a guest user
    When I navigate to "BT_furniture and BACKORDER" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    Then I should see "BACKORDER" availability message on BT PDP page as:
      | Order now. This item is estimated to be in stock by |

  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario5
  Scenario Outline: As a guest/signed verify schedule delivery information and place an order with BT item without selecting WNM plan
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_indoor_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I continue checkout until I reach the order review page as an "BT <user_type>" user
    Then I verify the schedule delivery information in schedule delivery section:
      | Your delivery is scheduled for: <schedule_date> |
    When I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario6
  Scenario Outline: As a guest/signed Place order with BT furniture BACKORDERED item, without selecting WNM plan & verify order details
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_indoor_furniture and BACKORDER" product to bag
    And I navigate to shopping bag page
    And I continue checkout until I reach the order review page as an "BT <user_type>" user
    Then I verify the schedule delivery information in schedule delivery section:
      | Some of your items are on order and estimated to be in stock for delivery |
    When I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |

  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario7
  Scenario Outline: As a guest/signed in user place an order with BT mattress by checking "COI" in White glove special instructions Questionnaire
    Given I visit the web site as a <user_type> user
    And I add a big ticket "BT_mattress and ONHAND" product to bag
    And I navigate to shopping bag page
    When I checkout until I reach the services & fees page as an "BT <user_type>" user
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


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario8
  Scenario Outline: As a guest/signed Place order with BT furniture available item, by selecting WNM plans & verify order details
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    When I checkout until I reach the services & fees page as a "BT <user_type>" user
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


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario9
  Scenario Outline: As a guest/signed place an order with outdoor furniture adding product from master PDP with "Delivery up more than 3 flights of stairs" in White glove special instructions Questionnaire
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_outdoor_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I checkout until I reach the services & fees page as a "BT <user_type>" user
    And I select "Delivery up more than 3 flights of stairs"
    And I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario10 @xbrowser
  Scenario Outline: As a guest/signed in user place an order with BT Onhand + BT Backorder and verify order details
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_indoor_furniture and ONHAND" product to bag
    And I add a big ticket "BT_outdoor_furniture and BACKORDER" product to bag
    And I navigate to shopping bag page
    And I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario11 @xbrowser
  Scenario Outline: As a guest/signed in user place an order an order with BT + ST, with WNM plan & schedule date
    Given I visit the web site as a <user_type> user
    When I add a "available and orderable" product to my bag
    And I add a big ticket "BT_indoor_furniture and ONHAND" product to bag
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


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario12
  Scenario Outline: As a guest/signed in user place an order an order with BT + BOPS, without WNM plan & schedule date
    Given I visit the web site as a <user_type> user
    When I add a "available_bops and orderable" product to my bag
    And I add a big ticket "BT_mattress_with_no_wnm and ONHAND" product to bag
    And I navigate to shopping bag page
    And I select pick up option for bops item after selecting available store
    And I checkout until I reach the services & fees page as an "BT bops <user_type>" user
    Then I verify wnm protection plans are not displayed in service & fees section
    When I continue checkout until I reach the order review page as an "BT <user_type>" user
    And I verify big ticket order summary:
      | WNM | false |
    And I tap "place order" button in "order review" section
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario13
  Scenario Outline: As a guest/signed in user place an order with Furniture, Outdoor furniture and mattress and verify user able to earn plenti points for BT order
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add a big ticket "BT_outdoor_furniture and ONHAND" product to bag
    And I add a big ticket "BT_mattress and ONHAND" product to bag
    And I checkout until I reach the order review page as an "BT <user_type>" user
    And I lookup plenti id using valid usl plenti id on payment page
    And I continue checkout until I reach the order confirmation page as an "<user_type>" user
    Then I should see big ticket order confirmation page with order details
    Examples:
      | user_type |
      | guest     |
      | signed in |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario14
  Scenario Outline: As a guest/signed in user, verify user is unable to navigate to checkout page with BT + VGC
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I navigate to checkout page as a "BT <user_type>" user
    Then I verify user sees error message for added VGC item in a bag
    Examples:
      | user_type |
      | guest     |
      | signed in |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario15
  Scenario Outline: As a guest/signed in user, verify user is unable to navigate to checkout page with BT + EGC
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I navigate to checkout page as a "BT <user_type>" user
    Then I verify user sees error message for added EGC item in a bag
    Examples:
      | user_type |
      | guest     |
      | signed in |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario16
  Scenario Outline: As a guest/signed in user, verify user is unable to navigate to checkout page with BT + Registry
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add registry item to my bag using rest service
    And I navigate to checkout page as a "BT <user_type>" user
    Then I verify user sees error message for added registry item in a bag
    Examples:
      | user_type |
      | guest     |
      | signed in |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario17
  Scenario Outline: As a guest/signed in user, verify suppression of paypal, gift card and plenti redeem for BT orders
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I checkout until I reach the <checkout_section> page as a "BT <user_type>" user
    Then I verify user able to pay with card only
    Examples:
      | user_type | checkout_section |
      | guest     | payment          |
      | signed in | shipping&payment |


  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario18
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

  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario19 @manual @wip
  Scenario Outline: As a guest/Signedin user, verify the BT order details in orderDetails page
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_indoor_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    When I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    When I save order details from confirmation page
    And I lookup with order number and emailAddress in OrderHistory page after navigating to OH page
    Then I verify order details in OD page

    Examples:
      | user_type |
      | guest     |
      | signed in |

  @project_BT @domain_purchase_and_delivery @bt_checkout @scenario20 @manual @wip
  Scenario Outline: As a guest/Signedin user, verify user able to add the BT product from Master PDP page and place an order
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_collection_master and ONHAND" from master product to bag
    And I navigate to shopping bag page
    When I continue checkout until I reach the order confirmation page as an "BT <user_type>" user
    Then I should see big ticket order confirmation page with order details
    When I save order details from confirmation page
    And I lookup with order number and emailAddress in OrderHistory page after navigating to OH page
    Then I verify order details in OD page

    Examples:
      | user_type |
      | guest     |
      | signed in |