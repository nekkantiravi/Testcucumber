Feature: Guest checkout regression scenarios for Purchase and Delivery

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Verify empty shopping bag page after placing an order as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as an "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see shopping bag with no products

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page
    Given I visit the website as a gvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I get order details
    And I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Verify Warning msg for multiple registry items checkout in guest flow
    Given I visit the website as a gvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    And I add another registry item from different registry user to my bag using rest service
    And I checkout until I reach the shipping page as an "guest" user
    And I should see below warning message in checkout page:
      | Currently, you can pick up items at the store from only one registry at a time. Please place as separate registry orders for in-store pickup. |

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place an order by selecting Gift options (Gift wrap, gift message)
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I select continue button on guest shipping page
    And I add a credit card during checkout
    And I select continue button on guest payment page
    Then I place an order
    And I get order details
    And I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal
  Scenario: Place an order with Normal item in standard paypal flow as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as an "guest" user with "paypal_address"
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal
  Scenario: Place an order with Normal item in express paypal flow as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page
    And I get order details
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal
  Scenario: Place an order with Registry item in standard paypal flow as a guest user
    Given I visit the website as a gvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    Then I verify the registrant and co registrant name details in shopping bag
    And I checkout until I reach the payment page as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    And I select continue button on guest shipping page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page
    And I get order details
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal @xbrowser
  Scenario: Place an order with Registry item in express paypal flow as a guest user
    Given I visit the website as a gvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    Then I verify the registrant and co registrant name details in shopping bag
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I select continue button on guest shipping page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page
    And I get order details
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @bops
  Scenario: Place order with bops item as a guest user
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @guest @bops
  Scenario: Place order with registry bops item as a gvr user
    Given I visit the website as a gvr user using rest services
    When I add an available_bops and registrable product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @guest @bops
  Scenario: Place order with bops and normal items as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops"
    And I add an available_bops product to bag
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    And I check for bops item availability
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @guest @bops @ee
  Scenario: Place order with registry bops and normal items as a gvr user
    Given I visit the website as a gvr user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops"
    And I add an available_bops and registrable product to bag
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    And I check for bops item availability
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @guest @iship
  Scenario: Place order in Iship Mode
    Given I visit the website as a iship user from "IN" with currency "INR" using rest services
    When I add an "iship_eligible and orderable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place an order with VGC item as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal
  Scenario: Place an order with VGC item in standard paypal flow as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the payment page as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page
    And I get order details
    And I verify the order details in database
    And I should see 1 shipment with ship method type as E in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal
  Scenario: Place an order with VGC item in express paypal flow as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page
    And I get order details
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Verify error msg for unavailable items in guest flow
    Given I visit the website as a guest user using rest services
    When I add a product to my bag using rest service that is not "available"
    And I checkout until I reach the payment page as an "guest" user
    Then I should see error message as:
      | MCOM | Some of the items in your bag are unavailable. Please remove them and proceed to checkout.       |
      | BCOM | Some items in your bag are currently unavailable. Please remove them to proceed with your order. |

  @domain_purchase_and_delivery @guest @checkout @xbrowser
  Scenario: Place an order with EGC item as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place order with normal and VGC items as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 2 shipments in database
    And I should see 1 shipment with ship method type as E in database
    And I should see 1 shipment with ship method type as G in database

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place order with normal and EGC items as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see 2 shipments with ship method type as G in database

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place order with VGC and EGC items as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 2 shipments in database
    And I should see 1 shipments with ship method type as G in database
    And I should see 1 shipments with ship method type as E in database

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place order with normal, VGC and EGC items as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an "electronic_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 3 shipments in database
    And I should see 2 shipments with ship method type as G in database
    And I should see 1 shipments with ship method type as E in database

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place an order with normal and registry items as a guest user
    Given I visit the website as a gvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as an "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 1 shipments in database

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place an order with registry and VGC items as a guest user
    Given I visit the website as a gvr user using rest services
    When I add an "registrable and orderable" product to my bag using rest service that is not "available_bops"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the order confirmation page as an "guest" user
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls
    And I should see a total of 2 shipments in database
    And I should see 1 shipments with ship method type as G in database
    And I should see 1 shipments with ship method type as E in database

  @domain_purchase_and_delivery @guest @bops
  Scenario: Place order with bops item as a guest user by selecting pick up by some one else
    Given I visit the website as a guest user using rest services
    When I add an available_bops product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    And I select pick up by some one else option
    And I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I checkout until I reach the order review page as a "bops" user
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Verify device info of an order when order placed as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as an "guest" user
    And I get order details
    Then I verify the order details in database
    And I verify device info details in database

  @domain_purchase_and_delivery @guest @bops
  Scenario: Place order with registry bops and normal bops items as a gvr user
    Given I visit the website as a gvr user using rest services
    When I add an available_bops and registrable product to bag
    When I add an available_bops product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @guest @bops
  Scenario: Place order with bops and VGC items as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an available_bops product to bag
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    And I check for bops item availability
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I get order details

  @domain_purchase_and_delivery @guest @bops
  Scenario: Place order with registry bops and normal and vgc items as a gvr user
    Given I visit the website as a gvr user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I add an available_bops and registrable product to bag
    And I checkout until I reach the payment page as a "bops" user
    Then I check for bops item availability
    When I checkout until I reach the order confirmation page as a "bops" user
    And I get order details
    Then I verify the order details in database
    And I should see a total of 3 shipments in database
    And I should see 1 shipment with ship method type as G in database
    And I should see 1 shipment with ship method type as B in database
    And I should see 1 shipment with ship method type as E in database

  @domain_purchase_and_delivery @guest @paypal
  Scenario: Place an order with BOPS item in standard paypal flow as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available_bops and orderable" product to my bag using rest service that is not "registrable"
    And I checkout until I reach the payment page as an "bops" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I get order details
    And I should see paypal icon and email on order confirmation page
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal
  Scenario: Place an order with BOPS item in express paypal flow as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available_bops and orderable" product to my bag using rest service that is not "registrable"
    When I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    And I submit bops shipping section
    When I place an order
    Then I should see paypal icon and email on order confirmation page
    And I get order details
    And I verify the order details in database
    And I validate "paypal" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place a 3d secure order with visa card as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as an "guest" user
    And I add 3d secure "Visa" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

  @domain_purchase_and_delivery @guest @checkout
  Scenario: Place a 3d secure order with master card as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as an "guest" user
    And I add 3d secure "MasterCard" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify user able edit and update the shipping address
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as a "guest" user
    Then I should see shipping section in summary state
    When I click edit in shipping section
    And I update the customer name as "QA Testing"
    Then I should see "QA Testing" in shipping summary section

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify order summary is updated when there is any change in the shipping details section
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable and premium_shipping" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the payment page as a "guest" user
    Then I should see shipping section in summary state
    When I click edit in shipping section
    And I select premium shipping method
    And I select continue button on guest shipping page
    Then I should see selected shipping charge amount in order summary

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify gift option indicators and gift box fee when gift options are selected
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable and gift_wrappable and gift_messageable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I select gift box in gift options
    And I select continue button on guest shipping page
    Then I should see gift option indicators in checkout page
    When I continue checkout until I reach the order confirmation page as a "guest" user
    Then I should see gift option indicators in order confirmation page

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify the display of RC shipping sections when user have only VGC item in bag
    Given I visit the website as a guest user using rest services
    When I add an "virtual_gift_card and orderable" product to my bag using rest service
    And I checkout until I reach the shipping page as a "guest" user
    Then I should not see standard shipping address fields
    And I should see vgc shippping copy in checkout page

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify user should see a checkout page footer with links
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I get bag id in shopping bag page
    And I checkout until I reach the shipping page as a "guest" user
    Then I verify the footer section details in checkout page
    When I continue checkout until I reach the order confirmation page as a "guest" user
    Then I verify the footer section details in order confirmation page

  @domain_purchase_and_delivery @guest @shopping_bag @phase2
  Scenario: Verify back to bag functionality
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping page as a "guest" user
    And I click back to bag link in checkout page
    Then I should be redirected to "shopping_bag" page

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify the Base fee and adjusted base fee persisted in DB when order total is > $99
    Given I visit the website as a guest user using rest services
    When I add an "available and free_shipping" product to my bag using rest service that is not "beauty and registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I should see free base and adjusted base fee in DB

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify the Base fee and adjusted base fee persisted in DB when order total is < $99
    Given I visit the website as a guest user using rest services
    When I add an "available" product to my bag using rest service that is not "beauty and free_shipping and registrable and available_bops and BT_furniture"
    And I checkout until I reach the order confirmation page as a "guest" user
    And I get order details
    Then I should see standard base and adjusted base fee in DB

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify available shipping methods are displayed with name, transit time and the price
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see available shipping methods with name, transit time and the price

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: Verify prices in selected currency for Iship Mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I mouse over "MEN" category from top navigation
    And I select "Shorts" subcategory from flyout menu
    And I select a random member product
    And I add product to my bag from standard PDP Page
    Then I verify the prices on Add to Bag page or overlay is displayed as "INR" for selected country

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: International Context Page - Switch to Domestic mode using Ship to U.S Address
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I select 'shipping to' change country link
    And I select 'ship to US address' button on International Home Page
    Then I should be navigated to Domestic Home Page

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: Verify the user navigate to domestic mode from ISHIP registry page
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I navigate to registry page in iship mode
    Then I verify the ability to go to US site from International Context page

  @domain_purchase_and_delivery @guest @iship @phase2  @dsv_desktop_sev2
  Scenario: Verify Exclusion message on PDP & shopping bag
    Given I visit the web site as a guest user
    When I add an "available" product to my bag that is not "iship_eligible" and "continue" checkout
    And I navigate to international context page
    And I change country to "Australia" and stay on the current page
    And I close the welcome mat if it's visible
    And I navigate to shopping bag page
    Then I should see the item level error:
      | BCOM | This item cannot be shipped to Australia. Please remove it from your bag to proceed. |
      | MCOM | This item is not available for shipping to Australia                                 |
    When I navigate to the PDP page from shopping bag page
    Then an unavailability error message should be displayed on PDP Page

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: Verify Shipping to the United States - Continue option on shopping bag page in Iship Mode
    Given I visit the website as a iship user from "IN" with currency "INR" using rest services
    When I add an "iship_eligible and orderable" product to my bag using rest service that is not "registrable and available_bops"
    And I select ship to united states link on bag page
    And I should see "Continue Checkout" button on Ship To United States Overlay
    And I should see "Return To International Site" button on Ship To United States Overlay
    When I select "Continue Checkout" on Ship To United States Overlay
    Then I should see checkout sign in page

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: Verify Shipping to the United States - return to international site option on shopping bag page in Iship Mode
    Given I visit the website as a iship user from "IN" with currency "INR" using rest services
    When I add an "iship_eligible and orderable" product to my bag using rest service that is not "registrable and available_bops"
    And I select ship to united states link on bag page
    And I should see "Continue Checkout" button on Ship To United States Overlay
    And I should see "Return To International Site" button on Ship To United States Overlay
    When I select "Return To International Site" on Ship To United States Overlay
    Then I should see envoy checkout page
    And I verify the currency is "INR" on the Border Free page

  @domain_purchase_and_delivery @guest @shopping_bag @phase2
  Scenario: Verify Shopping bag update quantity in guest flow
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I update shopping bag items with quantity 2
    Then I should see updated total in shopping bag page for updated quantity 2

  @domain_purchase_and_delivery @guest @shopping_bag @phase2
  Scenario: Remove shopping Bag item in guest flow
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    When I remove all items in shopping bag
    Then I should see shopping bag with no products

  @domain_purchase_and_delivery @guest @shopping_bag @phase2
  Scenario: Verify shopping bag page with a product qualifying for Free Shipping in guest flow
    Given I visit the website as a guest user using rest services
    When I add an "available and free_shipping" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    Then I should see free shipping in shopping bag page

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify user is able to edit or modify contact information in responsive checkout page as a guest user
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "guest" user
    And I click edit in payment section
    And I update the contact phone as "(867) 921-2222"
    Then I should see contact phone as "(867) 921-2222" in payment summary

  @domain_purchase_and_delivery @guest @paypal @phase2
  Scenario: Verify contact information is displayed on order review page when user checkouts with PayPal as tender type in guest flow
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as an "guest" user with "paypal_address"
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see paypal information in payment summary
    And I should see contact information in payment summary

  @domain_purchase_and_delivery @guest @paypal @phase2
  Scenario: Verify contact information is displayed on order review page when user checkout using express PayPal in guest flow
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should see paypal information in payment summary
    And I should see contact information in payment summary

  @domain_purchase_and_delivery @guest @paypal @phase2
  Scenario: Verify user is able to place order with credit card after paypal authorization in guest flow
    Given I visit the website as a guest user using rest services
    And I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the payment page as an "guest" user with "paypal_address"
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I click edit in payment section
    Then I should not see paypal login disclaimer text in paypal section
    When I select credit card as tender type
    And I add a credit card during checkout
    And I select continue button on guest payment page
    And I place an order
    And I get order details
    Then I verify the order details in database
    And I validate "credit card" payment type in eps authorization calls

  @domain_purchase_and_delivery @guest @paypal @phase2
  Scenario: Verify user is able to edit or modify paypal contact information after paypal authentication in guest flow
    Given I visit the website as a guest user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I click edit in payment section
    Then I should not see paypal login disclaimer text in paypal section
    When I update the contact phone as "(867) 921-2222"
    Then I should see contact phone as "(867) 921-2222" in payment summary

  @domain_purchase_and_delivery @guest @iship @phase2
  Scenario: Verify shopping bag is empty after placing order in iship mode
    Given I visit the website as a iship user from "IN" with currency "INR" using rest services
    When I add an "iship_eligible and orderable" product to my bag using rest service that is not "registrable and available_bops"
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page
    And I should see shopping bag with no products

  @domain_purchase_and_delivery @guest @checkout @phase2
  Scenario: Verify apply and remove promo code in domestic guest shopping bag page
    Given I visit the website as a guest user using rest services
    When I add an "available and promo_eligible" product to my bag using rest service
    And I apply a valid promo code in shopping bag page
    When I remove the promo code
    Then I should not see the applied promo code

  @domain_purchase_and_delivery @guest @sdd @phase2
  Scenario: Verify availability of SDD option when user changes the shipping address of SDD to Non SDD shipping address in the guest checkout page
    Given I visit the website as a guest user using rest services
    When I add an available_sdd product to bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter sdd_eligible address on shipping page for guest user
    Then I should see sdd shipping option in enabled state
    When I enter sdd_ineligible address on shipping page for guest user
    Then I should see sdd shipping option in disabled state

  @domain_purchase_and_delivery @guest @sdd @phase2
  Scenario: Verify the selected gift options when guest user selects SDD as the shipping method for the order
    Given I visit the website as a guest user using rest services
    When I add an available_sdd product to bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see gift box option in enabled state
    When I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I select gift box in gift options
    And I select continue button on guest shipping page
    Then I should see gift box fee in order summary section
    And I should see gift box indicator in gift options summary
    When I click edit in shipping section
    And I enter sdd_eligible address on shipping page for guest user
    And I select sdd shipping method option
    When I select continue button on guest shipping page
    Then I should not see gift box fee in order summary section
    And I should see gift box option in disabled state