Feature: Place order scenarios with different payment types and items

  @scenario1 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Place an order by selecting Gift options (Gift wrap, gift message)
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select gift options on shipping page
    And I select continue button on guest shipping page
    And I add a credit card during checkout
    And I select continue button on guest payment page
    Then I place an order

  @scenario2 @domain_purchase_and_delivery @xbrowser @xbrowser_one @high
  Scenario: Place order by selecting Use my shipping address checkbox on Payment page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I enter payment details on payment page
    And I select use my shipping address checkbox on payment page
    And I enter contact details on payment page
    And I select continue button on guest payment page
    Then I place an order

  @scenario3 @domain_purchase_and_delivery @xbrowser @xbrowser_one @product_data_dependency
  Scenario: As a Guest USL user, I should see redeemed points on Order Confirmation Page if user opts for USL redemption, when USL ID added in shopping bag page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag that is not a "master_product"
    And I checkout on add to bag overlay
    And I lookup plenti id using valid usl phone number on shopping bag page
    And I checkout until I reach the payment page as an "guest" user
    And I pay whole transaction amount with usl on payment page and place order
    Then I should see USL information on order confirmation page

  @scenario4 @domain_purchase_and_delivery @xbrowser @xbrowser_one @product_data_dependency
  Scenario: As a Registered USL user, I should see redeemed points on Order Confirmation Page if user opts for USL redemption, when USL ID added in shopping bag page
    Given I visit the web site as a registered user with checkout eligible address
    When I add a "available and orderable" product to my bag that is not a "master_product"
    And I checkout on add to bag overlay
    And I lookup plenti id using valid usl phone number on shopping bag page
    And I checkout until I reach the shipping & payment page as an "signed in" user
    And I pay whole transaction amount with usl on payment page and place order
    Then I should see USL information on order confirmation page

  @scenario5 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Verify place order when guest user selects express paypal as tender type
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout on add to bag overlay
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario6 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Verifying the Merge bag scenario
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I sign out from my current profile
    And I clear all the cookies
    And I visit the web site as a guest user
    And I add an "available and orderable" product to my bag
    And I sign in during checkout
    Then I verify the functionality of merge bag

  @scenario7 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Place order as a registrant after adding registry item from BVR page
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @scenario8 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order confirmation page as an "guest" user

  @scenario9 @domain_purchase_and_delivery @xbrowser @xbrowser_one @product_data_dependency
  Scenario: Place order with a member product as a signed in user
    Given I visit the web site as a registered user with checkout eligible address
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @scenario10 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency
  Scenario: Place order with a member product as a guest user
    Given I visit the web site as a guest user
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user

  @scenario11 @domain_purchase_and_delivery @xbrowser @xbrowser_two @use_dsv
  Scenario: Verification of registrant and co registrant name on shopping bag page for Signed in user
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    Then I verify the registrant & co registrant name details

  @scenario12 @domain_purchase_and_delivery @xbrowser @xbrowser_two
  Scenario: Verify place order when guest user selects paypal as tender type from payment page
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping page as an "guest" user
    And I enter paypal_eligible address on shipping page for guest user
    And I checkout until I reach the payment page as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario13 @domain_purchase_and_delivery @xbrowser @xbrowser_two
  Scenario: Verification of registrant and co registrant name on shopping bag page for Guest user(GVR flow)
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I verify the registrant & co registrant name details

  @scenario14 @domain_purchase_and_delivery @xbrowser @xbrowser_two @high
  Scenario: Place order with a master product as a signed in user
    Given I visit the web site as a registered user with checkout eligible address
    When I navigate to My Wallet page from My Account page
    And I add credit card to my wallet
    And I add an "available and master_product" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @scenario15 @domain_purchase_and_delivery @xbrowser @xbrowser_two
  Scenario: Place order with a master product as a guest user
    Given I visit the web site as a guest user
    When I add an "available and master_product" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user

  @scenario16 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_store_dependency
  Scenario: Verification of bops pick up for store button selection on bag page for guest user
    Given I visit the web site as a guest user
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I click store pickup availability link on bag page
    And I select "100 Miles" in bops change store dialog
    And I search for zipcode "22102" in bops change store dialog
    And I select bops store button in change pickup store dialog
    And I save & close the bops change store dialog
    And I select pick up option for bops item
    Then I should see bops shipping in order summary on shopping bag page

  @scenario17 @domain_purchase_and_delivery @xbrowser @xbrowser_two @high
  Scenario: Verify place order when signed in user selects express paypal
    Given I visit the web site as a registered user with checkout eligible address
    When I add a "available and orderable" product to my bag
    And I checkout on add to bag overlay
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario18 @domain_purchase_and_delivery @xbrowser @xbrowser_two
  Scenario: Verify place order when signed in user selects paypal from payment page
    Given I visit the web site as a registered user with checkout eligible address
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed in" user
    And I enter paypal_eligible address on shipping page for responsive signed in user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario19 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
  Scenario Outline: Place an order as a guest and signed in user with SDD selected as shipping method
    Given I visit the web site as a <user_type>
    When I add a "sdd_eligible and orderable" product to my bag
    And I checkout until I reach the <page> page as a "<user>" user
    And I enter sdd_eligible address on shipping page for <user> user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | false |
      | express_shipping  | false |
      | sdd_shipping      | true  |
    When I select sdd_shipping in shipping methods
    Then I should see following delivery ship note for same day delivery shipping method on "<page>" page:
      | sdd_shipping_method                                      |
      | Note: We'll send you an email to schedule your delivery. |
    When I checkout until I reach the order review page as a "<user>" user
    Then I should see following estimated delivery message on "order review" page:
      | sdd_estimated_delivery                                                      |
      | Order by 1pm & get it today. Order by 11am for Same-Day Delivery on Sundays |
    And I should see following delivery ship note for same day delivery shipping method on "order review" page:
      | sdd_shipping_method                                      |
      | Note: We'll send you an email to schedule your delivery. |
    When I checkout until I reach the order confirmation page as a "<user>" user
    Then I should see following estimated delivery message on "order confirmation" page:
      | sdd_estimated_delivery                                                      |
      | Order by 1pm & get it today. Order by 11am for Same-Day Delivery on Sundays |
    And I should see following delivery ship note for same day delivery shipping method on "order confirmation" page:
      | sdd_shipping_method                                                                     |
      | Note: Same-Day Delivery orders over $500 require a signature when the delivery arrives. |

    Examples:
      | user_type                                      | page               | user      |
      | guest user                                     | shipping           | guest     |
      | registered user with checkout eligible address | shipping & payment | signed in |

  @scenario20 @domain_purchase_and_delivery @xbrowser @xbrowser_two @high
  Scenario: Place order in Iship Mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page

  @scenario21 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
  Scenario: Place order with bops item as a guest user
    Given I visit the web site as a guest user
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page

  @scenario22 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
  Scenario: Place order with bops item as a signed in user
    Given I visit the web site as a registered user with checkout eligible address
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page

  @scenario23 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
  Scenario: Place order with bops and normal item as a guest user
    Given I visit the web site as a guest user
    When I add an "available_bops and orderable" product to my bag
    And I add a "available and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page

  @scenario24 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency
  Scenario Outline: Place order using 3D secure Functionality as responsive guest and signed in
    Given I visit the web site as a <user_type>
    When I add a "available and orderable" product to my bag that is not a "master_product"
    And I checkout until I reach the <page_name> page as a "<user>" user
    And I add 3d secure "<card_type>" as my card type and checkout
    And I enter the 3d secure credentials
    Then Order should be placed successfully

    Examples:
      | user_type                                      | card_type  | page_name          | user      |
      | guest user                                     | Visa       | payment            | guest     |
      | guest user                                     | MasterCard | payment            | guest     |
      | registered user with checkout eligible address | Visa       | shipping & payment | signed in |
      | registered user with checkout eligible address | MasterCard | shipping & payment | signed in |

  @scenario25 @xbrowser @xbrowser_two @product_data_dependency @wip
  Scenario: Place an order during mMoney earn period as a guest user
    Given I visit the web site as a guest user in mMoney earn period
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I navigate to shopping bag page from add to bag page
    And I update the bag to meet the mbMoney earn threshold if the bag not met threshold
    Then I should see estimated mMoney earned section in the shopping bag page
    When I checkout until I reach the shipping page as a "guest" user
    Then I should see the same estimated mMoney amount is displayed on RC page till place order

  @scenario26 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency
  Scenario Outline: Remove one item from mixed bag contains normal and registry item
    Given I visit the web site as a registry user
    And I add "registrable and orderable" product to my bag from BVR page
    And I add an "available and orderable" product to my bag
    And I checkout on add to bag overlay
    When I remove <item_one> item from shopping bag page
    Then I should see <item_one> item is removed from shopping bag page
    And I should see only <item_two> item is present in shopping bag page
    Examples:
      | item_one | item_two |
      | registry | normal   |
      | normal   | registry |

  @scenario27 @domain_purchase_and_delivery @xbrowser @xbrowser_two @wip
  Scenario: Place an order with gift card as payment type
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I add valid EGC gift card as payment option on payment page
    Then I checkout until I reach the order confirmation page as an "guest" user

  @wip @test
  Scenario: Verify product unavailability check at MST
    Given I visit the web site as a guest user
    Then I verify product unavailability check at MST
