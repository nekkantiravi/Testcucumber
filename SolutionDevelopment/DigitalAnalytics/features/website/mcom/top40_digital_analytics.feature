Feature: Marketing top 40 high priority Digital Analytics test scenario

  @da_top40 @scenario1
  Scenario: Digital Analytics Top 40 - Scenario 1
    Given I visit the web site as a guest user
    When I mouse over "HANDBAGS" category from top navigation
    And I select "COACH" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page

  @da_top40 @scenario2
  Scenario: Digital Analytics Top 40 - Scenario 2
    Given I visit the web site as a guest user
    When I search for "watches"
    Then I should be in Search Landing page
    When I select "Citizen" item from "Brand" facet on left nav
    And I select "Seiko" item from "BRAND" facet on left nav
    And I "collapse" the "Gender" facet on left nav
    Then the "Gender" facet should be "collapsed" on left nav
    When I "expand" the "Gender" facet on left nav
    Then the "Gender" facet should be "expanded" on left nav
    When I select "Men" item from "Gender" facet on left nav
    And I select a random product in a quickview dialog
    And I select 'see full product details' link from the quickview dialog

  @da_top40 @scenario3 @singleton
  Scenario: Digital Analytics Top 40 - Scenario 3
    Given I visit the web site as a guest user
    When I type "lugg" in search box
    Then I select "Samsonite Luggage" from autocomplete suggestions
    When I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page

  @da_top40 @scenario4 @singleton @batch @defect_DS-50802 @defect_DS-50027
  Scenario: Digital Analytics Top 40 - Scenario 4
    Given I visit the web site as a guest user
    When I mouse over "HOME" category from top navigation
    And I select "Cookware & Cookware Sets" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario5 @singleton @defect_DS-50802 @batch
  Scenario: Digital Analytics Top 40 - Scenario 5
    Given I visit the web site as a registered user
    When I mouse over "HOME" category from top navigation
    And I select "Cutlery & Knives" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I checkout on batch mode until I reach the order confirmation page as a "signed in" user

  @da_top40 @scenario6 @defect_DS-56778 @defect_DS-50802
  Scenario: Digital Analytics Top 40 - Scenario 6
    Given I visit the web site as a guest user
    And I go to US site
    When I navigate to international context page
    And I change country to "Germany"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I mouse over "HOME" category from top navigation
    And I select "Fine China" subcategory from flyout menu
    And I go to US site

  @da_top40 @scenario7 @singleton @defect_DS-50802 @batch
  Scenario: Digital Analytics Top 40 - Scenario 7
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I mouse over "MEN" category from top navigation
    And I select "T-Shirts" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB overlay
    When I checkout on add to bag overlay
    And I checkout on batch mode until I reach the order review page as a "iship" user from "Canada"

  @da_top40 @scenario8
  Scenario: Digital Analytics Top 40 - Scenario 8
    Given I visit the web site as a guest user
    And I sign in to my existing profile
    When I delete all lists in wishlist page
    And I create a list "Wishlist1" from wishlist page
    And I create a list "Wishlist2" from wishlist page
    And I navigate to the "Polos" browse page under "Men"
    And I select a random product in a quickview dialog
    And I add the item to wishlist from QV
    And I close the quickview dialog
    And I select a random member product
    Then I should be redirected to PDP page
    When I select product related attributes from PDP
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a registered user
    When I add product to my bag from wishlist page and continue shopping
    Then I navigate to a random product PDP from wish list page
    Then I should be redirected to PDP page

  @da_top40 @scenario9 @defect_DS-50092
  Scenario: Digital Analytics Top 40 - Scenario 9
    Given I visit the web site as a guest user
    When I click on "sign in" link in the header
    And I sign in to my existing profile
    Then I should be navigated to domestic home page as a sign in user
    When I navigate to my account page
    And I navigate to my profile page

  @da_top40 @scenario10
  Scenario: Digital Analytics Top 40 - Scenario 10
    Given I visit the web site as a guest user
    When I navigate to the "Polos" browse page under "MEN"
    And I select a random member product
    Then I should be redirected to PDP page
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a recently viewed product
    And I add product to my bag from standard PDP Page

  @da_top40 @scenario11 @singleton @batch
  Scenario: Digital Analytics Top 40 - Scenario 11
    Given I visit the web site as a guest user
    When I search for "asdasdasd" on home page for "vertical" panel
    Then I should see "horizontal" recommendation panel on zsr page
    When I select a random product from "horizontal" recommendation panel
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario12
  Scenario: Digital Analytics Top 40 - Scenario 12
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Create Your Registry" subcategory from flyout menu
    And I start to create a new registry from registry sign in page
    And I create a new registry
    Then I should be navigated to the registry manager page

  @da_top40 @scenario13 @defect_DS-50092
  Scenario: Digital Analytics Top 40 - Scenario 13
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile

  @da_top40 @scenario14
  Scenario: Digital Analytics Top 40 - Scenario 14
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    And I sign out from my current profile
    And I navigate to registry home page
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Create Your Registry" subcategory from flyout menu
    And I sign in with existing profile on capture email page
    And I continue creating registry from create registry page
    Then I should be navigated to the registry manager page

  @da_top40 @scenario15
  Scenario: Digital Analytics Top 40 - Scenario 15
    Given I visit the web site as a guest user
    When I mouse over "WOMEN" category from top navigation
    And I select "Activewear" subcategory from flyout menu
    And I select a random member product
    When I select "pinterest" social icon on PDP Page
    Then I should see "pinterest" title in the popup window
    When I select "email" social icon on PDP Page
    Then I should see "email" title in the popup window

  @da_top40 @scenario16
  Scenario: Digital Analytics Top 40 - Scenario 16
    Given I visit the web site as a registry user
    When I mouse over "DINING" category from top navigation
    And I select "Flatware & Silverware" subcategory from flyout menu
    When I select "Lenox" item from "Brand" facet on left nav
    And I select a predefined orderable random product
    Then I should be redirected to PDP page
    When I search for a product in a nearby store
    And I close the instore Availability popUp
    And I add the product to a registry

  @da_top40 @scenario17 @defect_DS-50092 @defect_DS-50802
  Scenario: Digital Analytics Top 40 - Scenario 17
    Given I visit the web site as a guest user
    When I click on "sign in" link in the header
    And I sign in to my existing profile
    And I click on "my account" link in the header
    And I navigate back to "Home" page
    And I navigate to the "Shorts" browse page under "Men"
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page

  @da_top40 @scenario18 @singleton @batch @defect_DS-50802
  Scenario: Digital Analytics Top 40 - Scenario 18
    Given I visit the web site as a guest user
    When I navigate to the "Bedding Collections" browse page under "BED & BATH"
    And I select a random master product
    Then I should be redirected to PDP page
    And I should see member products listed
    When I add member product from PDP and select "checkout"
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario19 @singleton @batch
  Scenario: Digital Analytics Top 40 - Scenario 19
    Given I visit the web site as a guest user
    When I search for "nike women"
    Then I should be in Search Landing page
    When I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario20 @singleton @batch @defect_DS-63665
  Scenario: Digital Analytics Top 40 - Scenario 20
    Given I visit the web site as a guest user
    When I type "clin" in search box
    And I select "Clinique" from autocomplete suggestions
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario21 @singleton @batch
  Scenario: Digital Analytics Top 40 - Scenario 21
    Given I visit the web site as a guest user
    When I type "bedd" in search box
    And I select "Hotel Collection Bedding" from autocomplete suggestions
    And I select a random master product
    Then I should be redirected to PDP page
    When I should see member products listed
    And I add member product from PDP and select "checkout"
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario22 @batch
  Scenario: Digital Analytics Top 40 - Scenario 22
    Given I visit the web site as a guest user
    When I search for "under armour"
    Then I should be in Search Landing page
    And I select a random product in a quickview dialog
    And I add the item to the bag from quick view
    And I navigate to shopping bag page from quick view dialog
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario23 @singleton @batch @defect_DS-55641 @defect_B-37432
  Scenario: Digital Analytics Top 40 - Scenario 23
    Given I visit the web site as a guest user
    When I search for "golf polo"
    Then I should be in Search Landing page
    When I select a random product in a quickview dialog
    And I select 'see full product details' link from the quickview dialog
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario24 @singleton @batch @defect_DS-54483 @defect_DS-54484 @defect_DS-72489
  Scenario: Digital Analytics Top 40 - Scenario 24
    Given I visit the web site as a guest user
    When I navigate to the "T-Shirts" browse page under "Men"
    And I "expand" the "Size" facet on left nav
    And I select "L" item from "Size" facet on left nav
    And I "expand" the "Price" facet on left nav
    And I select "$20-$40" item from "Price" facet on left nav
    And I "expand" the "Color" facet on left nav
    And I select "Black" item from "Color" facet on left nav
    And I select a random member product
    And I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario25 @singleton @batch
  Scenario: Digital Analytics Top 40 - Scenario 25
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Polos" subcategory from flyout menu
    And I select "Polo Ralph Lauren" item from "Brand" facet on left nav
    And I select "Short Sleeve" item from "Sleeve Length" facet on left nav
    And I select "3 stars & up" item from "Customers Top Rated" facet on left nav
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario26 @defect_DS-50802
  Scenario: Digital Analytics Top 40 - Scenario 26
    Given I visit the web site as a guest user
    When I mouse over "JUNIORS" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I search for "bracelets"
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page

  @da_top40 @scenario27 @singleton @batch @defect_DS-55641
  Scenario: Digital Analytics Top 40 - Scenario 27
    Given I visit the web site as a guest user
    When I mouse over "SHOES" category from top navigation
    And I select "All Women's Shoes" subcategory from flyout menu
    And I select a random member_alternate_image product
    Then I should be redirected to PDP page
    When I select a random alternative image
    And I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order review page as a "guest" user

  @da_top40 @scenario28 @singleton @batch @defect_DS-66975
  Scenario: Digital Analytics Top 40 - Scenario 28
    Given I visit the web site as a guest user
    When I navigate to the "Makeup" browse page under "Beauty"
    Then I click on pick up
#    When I open bops change store dialog through "Free Pick Up In Store"
    And I search for zipcode "10022" in bops change store dialog
    Then I should see store values in bops change store dialog
    When I close the bops change store dialog
    And I select any bops facet value
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I select pick up option for bops item
    And I checkout on batch mode until I reach the order review page as a "guest bops" user

  @da_top40 @scenario29 @singleton @batch
  Scenario: Digital Analytics Top 40 - Scenario 29
    Given I visit the web site as a registry user
    When I navigate to the "JUICERS" browse page under "KITCHEN"
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB overlay
    When I checkout on add to bag overlay
    And I checkout on batch mode until I reach the order review page as a "signed in" user

  @da_top40 @scenario30
  Scenario: Digital Analytics Top 40 - Scenario 30
    Given I visit the web site as a registered user
    When I visit the deals and promotions page
    And I click on 'add to wallet' button for any offer in deals and promotions page
    And I select 'X' on details and exclusions overlay
    And I open the coupon window
    Then I should see a popup window with coupon code
    When I click on SHOP NOW button

  @da_top40 @scenario31 @defect_DS-50092 @defect_DS-52135
  Scenario: Digital Analytics Top 40 - Scenario 31
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I click on add offer on wallet page
    And I add a valid offer
    And I click delete on random offer and wait for confirmation overlay
    And I confirm offer remove
    And I click on add offer on wallet page
    And I add a valid offer

  @da_top40 @scenario32 @singleton @defect_DS-45446 @defect_DS-55637 @defect_DS-50152
  Scenario: Digital Analytics Top 40 - Scenario 32
    Given I visit the web site as a guest user
    When I directly add an available and orderable product "2112827" to my bag
    And I apply invalid promo code "asdasdasd" using website
    Then I verify the promo code validation error message appeared
    When I apply valid promo code "VALPAK10" using website
    And I remove the promo code
    And I click on "sign in" link in the header
    And I sign in to my existing profile
    And I navigate to my account page
    And I navigate to shopping bag page
    And I apply invalid promo code "asdasdasd" using website
    And I apply valid promo code "VALPAK10" using website
    And I checkout until I reach the order review page as a "responsive_signed in" user


  @da_top40 @scenario33 @singleton
  Scenario: Digital Analytics Top 40 - Scenario 33
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I add fully_enrolled_usl id on my account page
    When I navigate to My Wallet page from My Account page
    And I click ADD a NEW CARD button
    And I add a credit card to My Wallet as default card on My Wallet page
    And I directly add an available and orderable product "86279088208" to my bag
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    And I remove USL ID from shipping and payment page
    And I lookup plenti id using valid usl phone number on payment page
    And I add usl as payment on payment page
    And I select billing address same as shipping address on payment page and save it
    And I checkout until I reach the order confirmation page as a "responsive_signed in" user

  @da_top40 @scenario34
  Scenario: Digital Analytics Top 40 - Scenario 34
    Given I visit the web site as a guest user
    #since test environment is redirected to production, add below step to navigate to plenti enroll page
    And I goto plenty enroll page directly
    And I click on the 'Get Started' button
    And I opt for guest user enrollment from USL sign in page

  @da_top40 @scenario35 @defect_63164
  Scenario: Digital Analytics Top 40 - Scenario 35
    Given I visit the web site as a guest user
    When I click on "sign in" link in the header
    And I navigate to create profile page
    And I create a new profile
    And I navigate to my plenti page
    And I click on the 'Join For Free' button
    #since test environment is redirected to production, add below step to navigate to plenti enroll page
    And I goto plenty enroll page directly
    And I click on the 'Cancel' button
    And I click on the 'YES, CANCEL' button
    #since test environment is redirected to production, add below step to navigate to plenti enroll page
    And I goto plenty enroll page directly
    And I enter the 'Phone number'

  @da_top40 @scenario36 @singleton
  Scenario: Digital Analytics Top 40 - Scenario 36
    Given I visit the web site as a registered user
    When I add a "orderable and available_sdd" product to my bag
    And I checkout until I reach the shipping & payment page as a "responsive signed in" user
    And I enter sdd_eligible address on shipping page for signed in user
    And I select sdd_shipping in shipping methods
    And I checkout until I reach the order review page as a "responsive signed in" user

  @da_top40 @scenario37 @defect_DS-56777 @defect_DS-55548
  Scenario: Digital Analytics Top 40 - Scenario 37
    Given I visit the web site as a guest user
    When I click find out more link on header panel
    And I navigate to "JUNIORS" category page
    And I click on thumbnail "Dresses" on featured categories
    And I navigate to "JUNIORS" category page
    And I select a customer top rated product

  @da_top40 @scenario38
  Scenario: Digital Analytics Top 40 - Scenario 38
    Given I visit the web site as a guest user
    When I click on "stores" link in the header
    And I Navigate to "order tracking" footer links
    And I Navigate to "catalogs" footer links
    And I navigate back to "Home" page
    And I Navigate to "locations & hours" footer links
    And I search using "94102" as "zipcode" in "store locations" page
    And I click "more arrow" link in a store from store results
    And I click "Directions" link from a map popup

  @da_top40 @scenario39
  Scenario: Digital Analytics Top 40 - Scenario 39
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Pants" subcategory from flyout menu
    And I filter the result set to show "120" items
    And I select "Best Sellers" in sort by drop down
    And I select "4" Column Grid icon
    And I filter the result set to show "100" items
    And I mouse over "MEN" category from top navigation
    And I select "Sweaters" subcategory from flyout menu

  @da_top40 @scenario40 @singleton
  Scenario: Digital Analytics Top 40 - Scenario 40
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I enter invalid "invalid_email@gmail.com" and "12345"
    And I add an "orderable" product to my bag that is not a "master_product" and select checkout
    And I checkout until I reach the shipping page as a "guest" user
    And I select continue button on guest shipping page
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    And I select continue button on guest payment page