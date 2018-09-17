Feature: Place order scenarios with different payment types and items

  @scenario1 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place an order by selecting Gift options (Gift wrap, gift message)
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture"
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I enter shipping address on guest shipping page using mobile website
    And I select gift options on shipping page
    And I select continue button on guest shipping page using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario2 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order by selecting Use my shipping address checkbox on Payment page
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture"
    And I checkout until I reach the payment page using mobile website as a "guest" user
    And I fill in payment information on guest payment page using mobile website
    And I select use my shipping address checkbox on payment page
    And I select continue button on guest payment page
    Then I place an order

  @scenario3 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew @wip @deprecated
  Scenario: As a Guest USL user, I should see redeemed points on Order Confirmation Page if user opts for USL redemption, when USL ID added in shopping bag page
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I add fully_enrolled_usl id on shopping bag page using mobile website
    And I checkout until I reach the payment page using mobile website as an "guest" user
    And I pay whole transaction amount with usl on payment page and place order
    Then I should see USL information on order confirmation page

  @scenario4 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew @wip @deprecated
  Scenario: As a Registered USL user, I should see redeemed points on Order Confirmation Page if user opts for USL redemption, when USL ID added in shopping bag page
    Given I visit the mobile web site as a registered user
    When I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I add fully_enrolled_usl id on shopping bag page using mobile website
    And I checkout until I reach the shipping & payment page using mobile website as an "signed in" user
    And I add shipping address if not present on shipping page using mobile website for signed in user
    And I pay whole transaction amount with usl on payment page and place order
    Then I should see USL information on order confirmation page

  @scenario5 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when guest user selects express paypal as tender type
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario6 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verifying the Merge bag scenario
    Given I visit the mobile web site as a registered user without add CC
    When I add an "available and orderable and member_product" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign out from my current mobile site profile
    And I clear all the cookies
    And I visit the mobile web site as a guest user
    And I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign in during checkout using mobile website
    Then I verify the functionality of merge bag using mobile website

  @scenario7 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order as a registrant after adding registry item from BVR page
    Given I visit the mobile web site as a registry user
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @scenario8 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page
    Given I visit the mobile web site as a registry user
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario9 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario: Place order with a member product as a signed in user
    Given I visit the mobile web site as a registered user
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @scenario10 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario: Place order with a member product as a guest user
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario11 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verification of registrant and co registrant name on shopping bag page for Signed in user
    Given I visit the mobile web site as a registry user
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I verify the registrant & co registrant name on mobile shopping bag page

  @scenario12 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when guest user selects paypal as tender type from payment page
    Given I visit the mobile web site as a guest user
    When I add a "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I checkout until I reach the payment page using mobile website as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario13 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verification of registrant and co registrant name on shopping bag page for Guest user(GVR flow)
    Given I visit the mobile web site as a registry user
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    Then I verify the registrant & co registrant name on mobile shopping bag page

  @scenario14 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order with a master product as a signed in user
    Given I visit the mobile web site as a registered user
    And I add an "available and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @scenario15 @xbrowser_tablet @xbrowser_mew @domain_purchase_and_delivery
  Scenario: Place order with a master product as a guest user
    Given I visit the mobile web site as a guest user
    When I add an "available and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario16 @xbrowser_tablet @domain_purchase_and_delivery @product_store_dependency @xbrowser_mew
  Scenario: Verification of bops pick up for store button selection on bag page for guest user
    Given I visit the mobile web site as a guest user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page

  @scenario17 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when signed in user selects express paypal
    Given I visit the mobile web site as a registered user
    When I add a "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and 'select' checkout
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario18 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when signed in user selects paypal from payment page
    Given I visit the mobile web site as a registered user
    When I add a "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I checkout until I reach the shipping & payment page using mobile website as an "signed in" user
    And I add shipping address if not present on shipping page using mobile website for signed in user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario19 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario Outline: Place an order as a guest and signed in user with SDD selected as shipping method
    Given I visit the mobile web site as a <user_type> user
    When I add a "available_sdd and orderable" product to my bag using mobile website
    And I checkout until I reach the <page> page using mobile website as a "<user>" user
    And I enter sdd_eligible address on shipping page using mobile website for <user> user
    When I select sdd_shipping in shipping methods
    When I checkout until I reach the order confirmation page using mobile website as a "<user>" user
    Then I should see SDD on order confirmation page

    Examples:
      | user_type  | page                 | user      |
      | guest      | shipping             | guest     |
      | registered | shipping & payment   | signed in |

  @scenario20 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew @involves_iframe @wip
  Scenario: Place order in Iship Mode
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "iship" user from "India"
    Then I should see order number on order receipt page

  @scenario21 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify user can navigate iship checkout page in Iship Mode
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    When I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the shipping & payment page using mobile website as a "iship" user from "India"
    Then I should see following elements on "iship_checkout" page:
      | shipping_iFrame  |

  @scenario21 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario: Place order with bops item as a guest user
    Given I visit the mobile web site as a guest user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page

  @scenario22 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario: Place order with bops item as a signed in user
    Given I visit the mobile web site as a registered user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page

  @scenario23 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario: Place order with bops and normal item as a guest user
    Given I visit the mobile web site as a guest user
    When I add an "available_bops" product to my bag using mobile website
    And I add a "available and orderable" product to my bag using mobile website that is not an "BT_furniture and available_bops" and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page using mobile website as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I checkout until I reach the payment page using mobile website as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops" user
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page

  @scenario24 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place an order during mMoney earn period as a guest user
    Given I visit the web site as a guest user in mMoney earn period
    When I add a "orderable and mbmoney_eligible" product to my bag using mobile website and "select" checkout
    Then I should see mMoney earn section in shopping bag page using mobile website
    When I increment product quantity on shopping bage page using mobile website
    Then I should see estimated mMoney amount on shopping bag page using mobile website
    When I checkout until I reach the shipping page using mobile website as a "guest" user
    Then I should see estimated mMoney amount on checkout page using mobile website
    When I checkout until I reach the order review page using mobile website as a "guest" user
    Then I should see estimated mMoney amount on checkout page using mobile website
    And I place an order

  @scenario25 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario Outline: Remove one item from mixed bag contains normal and registry item
    Given I visit the mobile web site as a registry user
    And I add "registrable" product to my bag from BVR page using mobile website
    And I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture and registrable" and "select" checkout
    When I remove <item_one> item from mobile shopping bag page
    Then I should see only <item_two> item is present in mobile shopping bag page
  Examples:
    | item_one | item_two |
    | registry | normal   |
    | normal   | registry |

  @scenario26 @domain_purchase_and_delivery @xbrowser_mew @promo_data_dependency
  Scenario: Place order after applying promo code on shopping bag page as a guest user
    Given I visit the mobile web site as a guest user
#    When I add an "orderable and promo_code_eligible" product to my bag using mobile website and "select" checkout
    When I add an "predefined_promo_code_eligible" product to my bag using mobile website and "select" checkout
    And I apply promo code on shopping bag page using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario27 @domain_purchase_and_delivery @xbrowser_mew @promo_data_dependency
  Scenario: Place order after applying promo code on shopping bag page as a registered user
    Given I visit the mobile web site as a registered user
#    When I add an "orderable and promo_code_eligible" product to my bag using mobile website and "select" checkout
    When I add an "predefined_promo_code_eligible" product to my bag using mobile website and "select" checkout
    And I apply promo code on shopping bag page using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as a "signed in" user
