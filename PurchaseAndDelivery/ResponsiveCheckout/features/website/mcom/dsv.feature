Feature: DSV guest checkout scenarios for Purchase and Delivery

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs  @dsv_desktop_sev2
  Scenario: Verify RC payment section using VISA card and leaving CVV field empty
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I continue checkout until I reach the payment page as an "guest" user
    And I continue checkout without adding a CVV number

  @domain_purchase_and_delivery @dsv_desktop_sev1  @dsv_desktop_sev2 @ifs
  Scenario: Verify BOPs Checkout until Order Review and bops shipping edit functionality
    Given I visit the web site as a guest user
    When I add a "available_bops and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I click edit in shipping section
    Then I should see saved information in bops shipping section

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs  @dsv_desktop_sev2
  Scenario: Verify Price and corresponding Standard shipping method changed based on free shipping threshold
    Given I visit the web site as a guest user
    When I add a "free_shipping and prod_available" product to my bag that is not "beauty"
    And I continue checkout until I reach the shipping page as an "guest" user
    Then I should see default shipping method as "Everyday Free Shipping" and price as "FREE"

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs
  Scenario: Verify Price and corresponding Standard shipping method changed based on free shipping threshold in shipping method section as a signed in user
    Given I visit the production web site as a "prod_user" registered user
    When I add a "prod_available and free_shipping" product to my bag that is not "beauty" and select checkout
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see default shipping method as "Everyday Free Shipping" and price as "FREE"

  @domain_purchase_and_delivery @dsv_desktop_sev1 @ifs @use_bat  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify whether Place Order button is enabled when both Shipping & Payment section are showing in the Summary state
    Given I visit the web site as a guest user
    And I add a "available and orderable" product to my bag
    When I checkout until I reach the order review page as a "guest" user
    Then I should see shipping section in summary state
    And I should see payment section in summary state
    And I should see place order button in enabled state

  @domain_purchase_and_delivery @dsv_desktop_sev1
  Scenario: Verify premium or express shipping on given address in RC shipping section
    Given I visit the web site as a guest user
    When I add a "prod_available and premium_shipping" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I select premium shipping method
    And I continue checkout with "premium_ineligible" shipping address
    Then I should see error message as:
      | MCOM | We're sorry, we are unable to expedite shipments to the address selected. Please select Standard shipping or provide an alternate shipping address. |

  @domain_purchase_and_delivery @dsv_desktop_sev1  @dsv_desktop_sev2
  Scenario: Verify order summary is updated when there is any change in the Shipping details section
    Given I visit the web site as a guest user
    And I add a "available and premium_shipping" product to my bag
    When I checkout until I reach the payment page as a "guest" user
    And I should see shipping section in summary state
    And I click edit in shipping section
    And I select premium shipping method
    And I select continue button on guest shipping page
    Then I should see selected shipping charge amount in order summary

  @domain_purchase_and_delivery @dsv_desktop_sev1  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify Quick view Checkout
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    Then I close the welcome mat if it's visible
    When I navigate to the "Jeans" browse page under "MEN"
    And I add a random item to bag from quick view
    And I navigate to shopping bag page from quick view dialog
    And I add a "iship_eligible and available and orderable" product to my bag and select checkout
    And I remove first item from shopping bag
    And I  click on continue checkout button on shoppping bag page
    Then I should see envoy checkout page

  @domain_purchase_and_delivery @dsv_desktop_sev1  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify Multi facet Checkout
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    Then I close the welcome mat if it's visible
    When I navigate to the "Jeans" browse page under "MEN"
    And I select the first two brands in the Brand facet
    And I select a random member product
    And I add "2" quantity to my bag from standard PDP Page
    And I add a "iship_eligible and available" product to my bag and select checkout
    And I remove first item from shopping bag
    And I  click on continue checkout button on shoppping bag page
    Then I should see envoy checkout page

  @domain_purchase_and_delivery
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

  @domain_purchase_and_delivery  @dsv_desktop_sev2
  Scenario: Verify shipping is free with only E-gift cards item in Bag
    Given I visit the web site as a guest user
    When I add a "electronic_gift_card and orderable" product to my bag and "continue" checkout
    And I checkout until I reach the payment page as a "guest" user
    Then I should see default shipping text and shipping price as "FREE"

  @domain_purchase_and_delivery  @dsv_desktop_sev2
  Scenario: Color label should display for line items having only one color
    Given I visit the web site as a guest user
    When I add a "with_color and orderable" product to my bag and select checkout
    Then I should see color of item in shopping bag page

  @domain_purchase_and_delivery  @dsv_desktop_sev2
  Scenario: Verify the display of sections when user have both VGC and normal items in bag on shipping page
    Given I visit the web site as a guest user
    When I add a "virtual_gift_card and available" product to my bag
    And I add a "orderable and available" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see standard shipping address fields
    And I should see vgc shippping copy in checkout page

  @domain_purchase_and_delivery  @dsv_desktop_sev2 @ee
  Scenario: Verify RC payment section when order contains only VGC items
    Given I visit the web site as a guest user
    When I add a "virtual_gift_card and available" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should not see standard shipping address fields
    And I should see vgc shippping copy in checkout page
    And I should not see use my shipping address checkbox in payment section

  @domain_purchase_and_delivery  @dsv_desktop_sev2
  Scenario: Merge Bag scenario
    Given I visit the web site as a registered user
    When I add a "orderable and available" product to my bag
    And I sign out from my current profile
    And I clear all the cookies
    And I click on signIn link
    And I add a "orderable and orderable" product to my bag
    And I sign in during checkout
    Then I verify the functionality of merge bag

  @domain_purchase_and_delivery
  Scenario: SDD checkout till order Review
    Given I visit the web site as a guest user
    And I add a "orderable and available" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    When I enter sdd_eligible address on shipping page for guest user
    And I select sdd_shipping in shipping methods
    Then I checkout until I reach the order review page as a "guest" user

  @domain_purchase_and_delivery  @dsv_desktop_sev2
  Scenario: Verify Iship checkout for dsv
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I mouse over "MEN" category from top navigation
    And I select "Shorts" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    And I verify the iship specific attributes of PDP Page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I remove all items from the shopping bag
    And I add a "available and orderable and iship_eligible" product to my bag and continue checkout
    And I checkout until I reach the shipping & payment page as a "iship" user from "India"
    Then I verify the basic attributes in Border Free Page

  @domain_purchase_and_delivery  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify Checkout for registry products with Sign in
    Given I visit the web site as a DSV registry user
    When I add "registrable and orderable" product to my bag from BVR page
    Then I should see only registry item is present in shopping bag page
    And I checkout until I reach the shipping & payment page as an "signed in" user

  @domain_purchase_and_delivery  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify sign in checkout until order review
    Given I visit the production web site as a "prod_user" registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    Then I should see shipping section in summary state
    And I should see payment section in summary state
    And I should see place order button in enabled state

  @domain_purchase_and_delivery  @dsv_desktop_sev2
  Scenario: Paypal Checkout (Verify it redirects to PayPal Page)
    Given I visit the website as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as an "guest" user with "paypal_address"
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    Then I should be on order review page

  @domain_purchase_and_delivery  @dsv_desktop_sev2
  Scenario: Color label should display for line items having only one color (Item Qty choosing from PDP only)
    Given I visit the web site as a guest user
    When I navigate to "gift_with_purchase and orderable" product PDP page
    And I add "2" quantity to my bag from standard PDP Page
    And I navigate to shopping bag page
    Then I should see color for bonus items

  @dsv_desktop_sev2
  Scenario: Verify BOPs Checkout until Order Review and bops shipping from category browse page
    Given I visit the web site as a guest user
    When I navigate to the "Hobo Bags" browse page under "HANDBAGS"
    And I select random product from thumbnail grid
    And I click add to bag button on PDP page
    And I click on checkout button on add to bag page
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I click edit in shipping section
    Then I should see saved information in bops shipping section
