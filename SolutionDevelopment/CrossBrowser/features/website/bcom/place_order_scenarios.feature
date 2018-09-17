Feature: Place order scenarios with different payment types and items

  @scenario1 @domain_purchase_and_delivery @xbrowser @xbrowser_one @high
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

  @scenario2 @domain_purchase_and_delivery @xbrowser @xbrowser_one
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
  Scenario: Verify user should be able to place order with loyalty id
    Given I visit the web site as a registered user with checkout eligible address
    When I navigate to the loyalty landing page as a "signed_in" user
    And I should be able to enroll in to the loyalty program as a "signed_in" user
    And I add an "orderable" product to my bag
    Then I checkout until I reach the order confirmation page as a "signed in" user
    And I should see loyalty points section on order conformation page

  @scenario4 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Verifying the Merge bag scenario
    Given I visit the web site as a registered user
    When I add an "orderable" product to my bag
    And I sign out from my current profile
    And I clear all the cookies
    And I visit the web site as a guest user
    And I add an "available and orderable" product to my bag
    And I sign in during checkout
    Then I verify the functionality of merge bag

  @scenario5 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Place order as a registrant after adding registry item from BVR page
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @scenario6 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order confirmation page as an "guest" user

  @scenario7 @domain_purchase_and_delivery @xbrowser @xbrowser_one @product_data_dependency
  Scenario: Place order with a member product as a signed in user
    Given I visit the web site as a registered user with checkout eligible address
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @scenario8 @domain_purchase_and_delivery @xbrowser @xbrowser_one @product_data_dependency
  Scenario: Place order with a member product as a guest user
    Given I visit the web site as a guest user
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user

  @scenario9 @domain_purchase_and_delivery @xbrowser @xbrowser_one @use_dsv
  Scenario: Verification of registrant and co registrant name on shopping bag page for Signed in user
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    Then I verify the registrant & co registrant name details

  @scenario10 @domain_purchase_and_delivery @xbrowser @xbrowser_one
  Scenario: Verification of registrant and co registrant name on shopping bag page for Guest user(GVR flow)
    Given I visit the web site as a registry user
    When I add "registrable and orderable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I verify the registrant & co registrant name details

  @scenario11 @domain_purchase_and_delivery @xbrowser @xbrowser_one @product_data_dependency
  Scenario: Place order with a master product as a signed in user
    Given I visit the web site as a registered user with checkout eligible address
    When I add an "available and master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @scenario12 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency
  Scenario: Place order with a master product as a guest user
    Given I visit the web site as a guest user
    When I add an "available and master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user

  @scenario13 @domain_purchase_and_delivery @xbrowser @xbrowser_two
  Scenario: Verify place order when guest user selects express paypal as tender type
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout on add to bag overlay
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario14 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency
  Scenario: Verify place order when guest user selects paypal as tender type from payment page
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as an "guest" user with "paypal_address"
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page
    When I place an order
    Then I should see paypal icon and email on order confirmation page

  @scenario15 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
  Scenario: Verification of bops pick up for store button selection on bag page for guest user
    Given I visit the web site as a guest user
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I click store pickup availability link on bag page
    And I select "100 Miles" in bops change store dialog
    And I search for zipcode "10022" in bops change store dialog
    And I select bops store button in change pickup store dialog
    And I save & close the bops change store dialog
    And I select pick up option for bops item
    Then I should see bops shipping in order summary on shopping bag page

  @scenario16 @domain_purchase_and_delivery @xbrowser @xbrowser_two @high
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

  @scenario17 @domain_purchase_and_delivery @xbrowser @xbrowser_two
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

  @scenario18 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
  Scenario Outline: Place an order as a guest and signed in user with SDD selected as shipping method
    Given I visit the web site as a <user_type>
    When I add a "available_sdd and orderable" product to my bag
    And I checkout until I reach the <page> page as a "<user>" user
    And I enter sdd_eligible address on shipping page for <user> user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | false |
      | express_shipping  | false |
      | sdd_shipping      | true  |
    When I select sdd_shipping in shipping methods
    And I checkout until I reach the order review page as a "<user>" user
    Then I should see following estimated delivery message on "order review" page:
      | responsive_or_non_responsive_page | sdd_estimated_delivery                                                                                      |
      | Non-Responsive                    | Today (if placed by 1PM Mon-Sat; 11AM Sun). Orders placed after these times will be delivered the next day. |
      | Responsive                        | Today (if placed by 1PM Mon-Sat; 11AM Sun). Orders placed after these times will be delivered the next day. |
    When I checkout until I reach the order confirmation page as a "<user>" user
    Then I should see following alert message to schedule delivery on order confirmation page:
      | Please check for a separate follow-up email to schedule a specific delivery window. |

    Examples:
      | user_type                                      | page               | user      |
      | guest user                                     | shipping           | guest     |
      | registered user with checkout eligible address | shipping & payment | signed in |

  @scenario19 @domain_purchase_and_delivery @xbrowser @xbrowser_two @high
  Scenario: Place order in Iship Mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page

  @scenario20 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
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

  @scenario21 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
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

  @scenario22 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency @product_store_dependency
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
    Then I should see bops shipping and normal shipping in order summary section
    When I place an order
    Then I should see bops shipping and normal shipping section on order confirmation page

  @scenario23 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency
  Scenario Outline: Place order using 3D secure Functionality as responsive guest and signed in
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
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

  @scenario24 @xbrowser @xbrowser_two @product_data_dependency @wip
  Scenario: Place an order during bMoney earn period as a registered user
    Given I visit the web site as a registered user in bMoney earn period
    When I add a "mbmoney and orderable and mbmoney_eligible" product to my bag
    And I navigate to shopping bag page from add to bag page
    And I update the bag to meet the mbMoney earn threshold if the bag not met threshold
    And I save the bMoney eligible amount information in bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see bMoney earn information is displayed on shipping & payment page
    When I checkout until I reach the order review page as a "signed in" user
    Then I should see bMoney earn information is displayed on order review page

  @scenario25 @domain_purchase_and_delivery @xbrowser @xbrowser_two @product_data_dependency
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