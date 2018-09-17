Feature: Place order scenarios with different payment types and items

  @scenario1 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place an order by selecting Gift options (Gift wrap, gift message)
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I enter shipping address on guest shipping page using mobile website
    And I select gift options on shipping page using mobile website
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario2 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order by selecting Use my shipping address checkbox on Payment page
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable" product to my bag using mobile website
    And I checkout until I reach the payment page using mobile website as a "guest" user
    And I fill in payment information on guest payment page using mobile website
    And I select use my shipping address checkbox on payment page using mobile website
    And I select continue button on guest payment page
    Then I place an order

  @scenario3 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify user should be able to place order with loyalty id
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | My Loyallist       |
      | Become a Loyallist |
    Then I enroll in to the loyalty program using mobile website as a signed in user
    When I add an "orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "signed in" user
    Then I should see loyalty points section on mobile order conformation page

  @scenario4 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verifying the Merge bag scenario
    Given I visit the mobile web site as a registered user without add CC
    When I add an "orderable" product to my bag using mobile website and "select" checkout
    And I sign out from my current mobile site profile
    And I clear all the cookies
    And I visit the mobile web site as a guest user
    And I add an "available" product to my bag using mobile website and "select" checkout
    And I sign in during checkout using mobile website
    Then I verify the functionality of merge bag using mobile website

  @scenario5 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order as a registrant after adding registry item from BVR page
    Given I visit the mobile web site as a registry user
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @scenario6 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page
    Given I visit the mobile web site as a registry user
    When I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    Then I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario7 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario: Place order with a member product as a signed in user
    Given I visit the mobile web site as a registered user
    When I add an "available and member_product" product to my bag using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @scenario8 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario: Place order with a member product as a guest user
    Given I visit the mobile web site as a guest user
    When I add an "available and member_product" product to my bag using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as an "guest" user

  @scenario9 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verification of registrant and co registrant name on shopping bag page for Signed in user
    Given I visit the mobile web site as a registry user
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I verify the registrant & co registrant name on mobile shopping bag page

  @scenario10 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verification of registrant and co registrant name on shopping bag page for Guest user(GVR flow)
    Given I visit the mobile web site as a registry user
    When I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    Then I verify the registrant & co registrant name on mobile shopping bag page

  @scenario11 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario: Place order with a master product as a signed in user
    Given I visit the mobile web site as a registered user
    When I add an "available and master_product" product to my bag using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @scenario12 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario: Place order with a master product as a guest user
    Given I visit the mobile web site as a guest user
    When I add an "available and master_product" product to my bag using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as an "guest" user

  @scenario13 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when guest user selects express paypal as tender type
    Given I visit the mobile web site as a guest user
    When I add a "available and orderable" product to my bag using mobile website
    And I checkout on add to bag overlay
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario14 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when guest user selects paypal as tender type from payment page
    Given I visit the mobile web site as a guest user
    When I add a "available and orderable" product to my bag using mobile website
    And I checkout until I reach the payment page using mobile website as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario15 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario: Verification of bops pick up for store button selection on bag page for guest user
    Given I visit the mobile web site as a guest user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page

  @scenario16 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when signed in user selects express paypal
    Given I visit the mobile web site as a registered user
    When I add a "available and orderable" product to my bag using mobile website and "select" checkout
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario17 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify place order when signed in user selects paypal from payment page
    Given I visit the mobile web site as a registered user without add CC
    When I add a "available and orderable" product to my bag using mobile website
    And I checkout until I reach the shipping & payment page using mobile website as an "signed in" user
    And I add shipping address if not present on shipping page using mobile website for signed in user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    When I click on "place_order" on "responsive_checkout_signed_in" page
    Then I should see paypal icon and email on order confirmation page

  @scenario18 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario Outline: Place an order as a guest and signed in user with SDD selected as shipping method
    Given I visit the mobile web site as a <user_type> user
    When I add a "available_sdd and orderable" product to my bag using mobile website
    And I checkout until I reach the <page> page using mobile website as a "<user>" user
    And I enter sdd_eligible address on shipping page using mobile website for <user> user
    When I select sdd_shipping in shipping methods
    And I checkout until I reach the order confirmation page using mobile website as a "<user>" user
    Then I should see SDD on order confirmation page

    Examples:
      | user_type   | page               | user      |
      | guest       | shipping           | guest     |
      | registered  | shipping & payment | signed in |

  @scenario19 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew @involves_iframe @wip
  Scenario: Place order in Iship Mode
    Given I visit the mobile web site as a guest user in iship mode
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "iship" user from "India"
    Then I should see order number on order receipt page

  @scenario20 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify user can navigate iship checkout page in Iship Mode
    Given I visit the mobile web site as a guest user in iship mode
    When I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the shipping & payment page using mobile website as a "iship" user from "India"
    Then I should see following elements on "iship_checkout" page:
      | shipping_iFrame  |

  @scenario20 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
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

  @scenario21 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario: Place order with bops item as a signed in user
    Given I visit the mobile web site as a registered user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page using mobile website as a "bops signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page

  @scenario22 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @product_store_dependency @xbrowser_mew
  Scenario: Place order with bops and normal item as a guest user
    Given I visit the mobile web site as a guest user
    When I add an "available_bops" product to my bag using mobile website
    And I add a "available and orderable" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping and normal shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page using mobile website as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    And I checkout until I reach the payment page using mobile website as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops" user
    Then I should see bops shipping and normal shipping in order summary section
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page

  @scenario23 @xbrowser_tablet @domain_purchase_and_delivery @product_data_dependency @xbrowser_mew
  Scenario: Place an order during bMoney earn period as a registered user
    Given I visit the web site as a guest user in bMoney earn period
    And I visit the mobile web site as a registered user
    When I add a "orderable and mbmoney_eligible" product to my bag using mobile website and "select" checkout
    Then I should see bMoney earn section in shopping bag page using mobile website
    And I should see estimated bMoney amount on shopping bag page using mobile website
    When I checkout until I reach the shipping page using mobile website as a "signed in" user
    Then I should see estimated bMoney amount on checkout page using mobile website
    When I checkout until I reach the order review page using mobile website as a "signed in" user
    Then I should see estimated bMoney amount on checkout page using mobile website
    And I place an order

  @scenario24 @xbrowser_tablet @domain_purchase_and_delivery @xbrowser_mew
  Scenario Outline: Remove one item from mixed bag contains normal and registry item
    Given I visit the mobile web site as a registry user
    When I add "registrable" product to my bag from BVR page using mobile website
    And I add an "available and orderable" product to my bag using mobile website and "select" checkout
    And I remove <item_one> item from mobile shopping bag page
    Then I should see only <item_two> item is present in mobile shopping bag page
    Examples:
      | item_one | item_two |
      | registry | normal   |
      | normal   | registry |

  @scenario25 @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order with a Gift Card product as a guest user
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
    | GIFTS      |
    | Gift Cards |
    When I navigate to "electronic_gift_card" product PDP page
    And I add product to my bag from standard PDP Page using mobile site
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario26 @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order with a Gift Card product as a registered user
    Given I visit the mobile web site as a registered user
    And I navigate the global navigation menu as follows:
      | GIFTS      |
      | Gift Cards |
    When I navigate to "electronic_gift_card" product PDP page
    And I add product to my bag from standard PDP Page using mobile site
    Then I checkout until I reach the order confirmation page using mobile website as a "signed in" user

  @scenario27 @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order with a E-Gift Cards product as a guest user
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | GIFTS        |
      | E-Gift Cards |
    When I navigate to "virtual_gift_card" product PDP page
    And I enter gift card amount and recipient email on PDP page
    And I click on "add_to_bag_button" on "product_display" page
    Then I should see the "add_to_bag" Page
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario28 @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Place order with a E-Gift Cards product as a registered user
    Given I visit the mobile web site as a registered user
    And I navigate the global navigation menu as follows:
      | GIFTS        |
      | E-Gift Cards |
    When I navigate to "virtual_gift_card" product PDP page
    And I enter gift card amount and recipient email on PDP page
    And I click on "add_to_bag_button" on "product_display" page
    Then I should see the "add_to_bag" Page
    And I checkout until I reach the order confirmation page using mobile website as a "signed in" user

  @scenario29 @domain_purchase_and_delivery @xbrowser_mew @promo_data_dependency
  Scenario: Place order after applying promo code on shopping bag page as a guest user
    Given I visit the mobile web site as a guest user
#    When I add an "orderable and promo_code_eligible" product to my bag using mobile website and "select" checkout
    When I add an "predefined_promo_code_eligible" product to my bag using mobile website and "select" checkout
    And I apply promo code on shopping bag page using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @scenario30 @domain_purchase_and_delivery @xbrowser_mew @promo_data_dependency
  Scenario: Place order after applying promo code on shopping bag page as a registered user
    Given I visit the mobile web site as a registered user
#    When I add an "orderable and promo_code_eligible" product to my bag using mobile website and "select" checkout
    When I add an "predefined_promo_code_eligible" product to my bag using mobile website and "select" checkout
    And I apply promo code on shopping bag page using mobile website
    Then I checkout until I reach the order confirmation page using mobile website as a "signed in" user

  @scenario31 @domain_purchase_and_delivery @xbrowser_mew
  Scenario: Verify bag displays promo details for soft-sign in
    Given I visit the mobile web site as a registered user without add CC
    And I navigate the global navigation menu as follows:
      | MY bWALLET |
    And I add an offer to my wallet using mobile website
    And I add an "available" product to my bag using mobile website and "select" checkout
    When I change my state from Signed In to Soft Signed In
    Then I should see wallet offer details on shoping bag page using mobile website
