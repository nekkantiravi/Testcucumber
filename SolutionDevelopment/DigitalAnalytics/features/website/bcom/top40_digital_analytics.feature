Feature: BCOM Marketing top 40 high priority coremetrics test scenario

  @da_top40 @scenario1
  Scenario: Digital Analytics Top 40 - Scenario 1
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Active & Workout" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    And I add product to my bag from standard PDP Page
    Then I should be redirected to ATB overlay

  @da_top40 @scenario2 @defect_B-48205 @defect_DS-52175 @defect_DS-62922 @defect_DS-63056 @defect_DS-67557
  @defect_DS-63071
  Scenario: Digital Analytics Top 40 - Scenario 2
    Given I visit the web site as a guest user
    When I search for "watches"
    Then I should be in Search Landing page
    When I "expand" the "Designer" facet on left nav
    And the "Designer" facet should be "expanded" on left nav
    Then I select "Nixon" item from "Designer" facet on left nav
    When I "expand" the "Designer" facet on left nav
    And the "Designer" facet should be "expanded" on left nav
    Then I select "Shinola" item from "Designer" facet on left nav
    When I "expand" the "Gender" facet on left nav
    Then the "Gender" facet should be "expanded" on left nav
    When I select "Male" item from "Gender" facet on left nav
    And I select a random product in a quickview dialog
    When I select 'see full product details' link from the quickview dialog
    And I add product to my bag from standard PDP Page

  @da_top40 @scenario3
  Scenario: Digital Analytics Top 40 - Scenario 3
    Given I visit the web site as a guest user
    When I type "black" in search box
    And I select "black halo" from autocomplete suggestions
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page

  @da_top40 @scenario4 @batch
  Scenario: Digital Analytics Top 40 - Scenario 4
    Given I visit the web site as a guest user
    When I mouse over "Women" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    And I select a random member product
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario5 @batch
  Scenario: Digital Analytics Top 40 - Scenario 5
    Given I visit the web site as a registered user
    When I add a random product to bag
    And I checkout on batch mode until I reach the order confirmation page as a "signed in" user

  @da_top40 @scenario6 @defect_DS-63061
  Scenario: Digital Analytics Top 40 - Scenario 6
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Germany"
    And I close the welcome mat if it's visible
    When I mouse over "Home" category from top navigation
    And I select "Dinnerware" subcategory from flyout menu

  @da_top40 @scenario7 @defect_DS-63061 @defect_DS-66739
  Scenario: Digital Analytics Top 40 - Scenario 7
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Canada"
    And I close the welcome mat if it's visible
    And I mouse over "MEN" category from top navigation
    And I select "Shorts" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    Then I checkout on batch mode until I reach the order confirmation page as a "iship" user from "Canada"

  @da_top40 @scenario8 @defect_DS-63071 @defect_DS-63072 @defect_DS-65924
  Scenario: Digital Analytics Top 40 - Scenario 8
    Given I visit the web site as a registered user
    When  I delete all lists in wishlist page
    And I create a list "Wishlist1" from wishlist page
    And I create a list "Wishlist2" from wishlist page
    And I navigate to the "Polos" browse page under "Men"
    And I select a random product in a quickview dialog
    And I add the item to wishlist from QV
    When I close the quickview dialog
    And I select a random member product
    Then I should be redirected to PDP page
    And I select product related attributes from PDP
    And I add the product to wishlist
    When I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a registered user
    When I add product to my bag from wishlist page and continue shopping
    When I navigate to a random product PDP from wish list page

  @da_top40 @scenario9 @defect_DS-65925
  Scenario: Digital Analytics Top 40 - Scenario 9
    Given I visit the web site as a guest user
    When I mouse over "Women" category from top navigation
    And I select "Tops & Tees" subcategory from flyout menu
    When I select "New Arrivals" in sort by drop down
    When I select previous page category from breadcrumb

  @da_top40 @scenario10 @defect_DS-63158
  Scenario: Digital Analytics Top 40 - Scenario 10
    Given I visit the web site as a guest user
    When I mouse over "MEN" category from top navigation
    And I select "Polos" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I mouse over "WOMEN" category from top navigation
    And I select "Dresses" subcategory from flyout menu
    And I select a random member product
    And I select a recently viewed product
    And I add product to my bag from standard PDP Page

  @da_top40 @scenario11 @batch
  Scenario: Digital Analytics Top 40 - Scenario 11
    Given I visit the web site as a guest user
    When I search for "dadaasda" on home page for "horizontal" panel
    When I select a random product from "horizontal" recommendation panel
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    When I checkout on add to bag overlay
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario12 @defect_DS-45787
  Scenario: Digital Analytics Top 40 - Scenario 12
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Create a Registry" subcategory from flyout menu
    And I start to create a new registry from registry sign in page
    And I create a new registry
    Then I should be navigated to the registry manager page

  @da_top40 @scenario13 @batch
  Scenario: Digital Analytics Top 40 - Scenario 13
    Given I visit the web site as a guest user
    When I navigate to the "Booties" browse page under "SHOES"
    And I select a random member product
    Then I should be redirected to PDP page
    When I select a random product from "vertical" recommendation panel
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario14 @defect_DS-52277 @defect_DS-45787 @defect_DS-72579
  Scenario: Digital Analytics Top 40 - Scenario 14
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I navigate to my profile page
    And I update profile details on my profile
    And I sign out from my current profile
    And I navigate to registry home page
    And I navigate to registry sign in page
    And I sign in with existing profile on capture email page
    And I continue creating registry from create registry page
    Then I should be navigated to the registry manager page

  @da_top40 @scenario15 @batch
  Scenario: Digital Analytics Top 40 - Scenario 15
    Given I visit the web site as a guest user
    When I navigate to the "Women" browse page under "WHAT'S NEW"
    And I select a random member product with customer ratings
    Then I should see "horizontal" recommendation panel on pdp page
    When I select a random product from "horizontal" recommendation panel
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario16 @defect_DS-57107 @defect_DS-52319
  Scenario: Digital Analytics Top 40 - Scenario 16
    Given I visit the web site as a registry user
    When I navigate to the "Cookware" browse page under "KITCHEN"
    And  I select a random member product
    Then I should be redirected to registry PDP page
    When I replace product ID with available "399242" product ID
    And I search for a product in a nearby store
    And I select available bops store and save details
    And I add the product to a registry

  @da_top40 @scenario17
  Scenario: Digital Analytics Top 40 - Scenario 17
    Given I visit the web site as a guest user
    When I select random asset from home page
    And I navigate back to "home" page
    When I click on INFO and EXCLUSIONS link
    When I select footer Ad banner

  @da_top40 @scenario18 @batch
  Scenario: Digital Analytics Top 40 - Scenario 18
    Given I visit the web site as a guest user
    When I mouse over "Home" category from top navigation
    And I select "Bedding Collections" subcategory from flyout menu
    And I select a random master product
    Then I should be redirected to PDP page
    And I should see member products listed
    When I add member product from PDP and select "checkout"
    When I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario19 @batch
  Scenario: Digital Analytics Top 40 - Scenario 19
    Given I visit the web site as a guest user
    When I search for "nike women"
    And I select a random member product
    Then I should be redirected to PDP page
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario20 @batch @defect_DS-50631
  Scenario: Digital Analytics Top 40 - Scenario 20
    Given I visit the web site as a guest user
    When I type "clin" in search box
    And I select "clinique skin care" from autocomplete suggestions
    And I select a random member product with customer ratings
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB overlay
    When I checkout on add to bag overlay
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario21 @batch @defect_DS-62916 @defect_DS-62917
  Scenario: Digital Analytics Top 40 - Scenario 21
    Given I visit the web site as a guest user
    When I type "ralph" in search box and select "ralph lauren sheets" from autocomplete suggestions
    And I select a random master product
    Then I should be redirected to PDP page
    And I should see member products listed
    When I add member product from PDP and select "checkout"
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario22 @batch @defect_DS-59487 @defect_DS-61687 @defect_DS-62926
  Scenario: Digital Analytics Top 40 - Scenario 22
    Given I visit the web site as a guest user
    When I search for "under armour"
    And I select a random product in a quickview dialog
    And I add the item to the bag from quick view
    And I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario23 @batch @defect_DS-61687 @defect_DS-62926 @defect_DS-62927 @defect_DS-54781
  Scenario: Digital Analytics Top 40 - Scenario 23
    Given I visit the web site as a guest user
    When I search for "baublebar earrings"
    Then I should be in Search Landing page
    And I select a random product in a quickview dialog
    And I select 'see full product details' link from the quickview dialog
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario24 @batch @defect_DS-63051 @defect_DS-56011
  Scenario: Digital Analytics Top 40 - Scenario 24
    Given I visit the web site as a guest user
    When I mouse over "Women" category from top navigation
    And I select "Tops & Tees" subcategory from flyout menu
    When I "expand" the "Size" facet on left nav
    And I select "4, S" item from "Size" facet on left nav
    And I "expand" the "Price" facet on left nav
    And I select "$50-$100" item from "Price" facet on left nav
    And I "expand" the "Color" facet on left nav
    And I select "Black" item from "Color" facet on left nav
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario25 @batch @defect_DS-54781 @defect_DS-56004 @defect_DS-63056
  Scenario: Digital Analytics Top 40 - Scenario 25
    Given I visit the web site as a guest user
    When I mouse over "Women" category from top navigation
    And I select "Pants" subcategory from flyout menu
    When I "expand" the "Designer" facet on left nav
    And I select "Ralph Lauren" item from "Designer" facet on left nav
    When I "expand" the "Sales & Offers" facet on left nav
    And I select "Sales & Offers" item from "Sales & Offers" facet on left nav
    And I select "Customer Top Rated" in sort by drop down
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    When I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario26 @batch
  Scenario: Digital Analytics Top 40 - Scenario 26
    Given I visit the web site as a guest user
    When I mouse over "BEAUTY" category from top navigation
    And I select "All Face" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I add member product from PDP and select "continue shopping"
    And I search for "bracelets"
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario27 @batch
  Scenario: Digital Analytics Top 40 - Scenario 27
    Given I visit the web site as a guest user
    When I mouse over "HANDBAGS" category from top navigation
    And I select "All Handbags" subcategory from flyout menu
    And I select a random member_alternate_image product with customer ratings
    And I select second alternative image on PDP Page
    When I add "4" quantity to my bag from standard PDP Page
    Then I should be redirected to ATB overlay
    When I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario28 @batch @defect_DS-56331 @defect_DS-54777
  Scenario: Digital Analytics Top 40 - Scenario 28
    Given I visit the web site as a registered user
    When I mouse over "WOMEN" category from top navigation
    And I select "Tops & Tees" subcategory from flyout menu
    When I "expand" the "Pick Up In Store" facet on left nav
    And I select any bops store
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I select pick up option for bops item
    And I checkout on batch mode until I reach the order confirmation page as a "signed in bops" user

  @da_top40 @scenario29 @batch
  Scenario: Digital Analytics Top 40 - Scenario 29
    Given I visit the web site as a guest user
    When I navigate to registry home page
    When I navigate to the "Electrics" browse page under "KITCHEN"
    And I select a random member product
    And I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario30
  Scenario: Digital Analytics Top 40 - Scenario 30
    Given I visit the web site as a guest user
    When I sign in to my existing profile
    Then I navigate to My Wallet page from My Account page
    And I click on add offer on wallet page
    And I add an invalid offer to wallet
    And I add omnichannel offer "PASSBOOK32438" having more than one promo code in wallet
    And I click "add credit card" button on my bwallet page
    And I submit credit card without filling the details
    And I add a credit card from my bwallet page
    And I navigate to my account page
    And I navigate to the loyalty landing page as a "signed_in" user
    Then I should be able to enroll in to the loyalty program as a "signed_in" user
    When I navigate to my account page
    And I navigate to My Wallet page from My Account page
    And I click "view more details" button on my bwallet page
    And I directly add an available and orderable product "3037" to my bag
    And I select the bwallet offer in shopping bag

  @da_top40 @scenario31
  Scenario: Digital Analytics Top 40 - Scenario 31
    Given I visit the web site as a registered user
    When I mouse over "BEAUTY" category from top navigation
    And I select "Dior" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I checkout until I reach the shipping & payment page as a "signed in" user

  @da_top40 @scenario32
  Scenario: Digital Analytics Top 40 - Scenario 32
    Given I visit the web site as a guest user
    When I mouse over "BEAUTY" category from top navigation
    And I select "Perfume" subcategory from flyout menu
    And I select a random member product
    Then I should be redirected to PDP page

  @da_top40 @scenario33
  Scenario: Digital Analytics Top 40 - Scenario 33
    Given I visit the web site as a registered user
    When I navigate to the loyallist account association page
    And I should be able to associate my account by loyallist number using "top_tier" details

  @da_top40 @scenario34
  Scenario: Digital Analytics Top 40 - Scenario 34
    Given I visit the web site as a registered user
    When I navigate to the loyalty landing page as a "signed_in" user
    Then I should be able to enroll in to the loyalty program as a "signed_in" user

  @da_top40 @scenario35 @defect_DS-68554
  Scenario: Digital Analytics Top 40 - Scenario 35
    Given I visit the web site as a guest user
    When I navigate to the loyalty landing page as a "guest" user
    And I navigate to the loyalty enrollment page
    Then I should be able to enroll in to the loyalty program as a "guest" user

  @da_top40 @scenario36 @batch
  Scenario: Digital Analytics Top 40 - Scenario 36
    Given I visit the web site as a registered user with checkout eligible address
    When I add an "orderable and available_sdd" product to my bag that is not "master" and select checkout
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I enter sdd_eligible address on shipping page for signed in user
    When I select sdd_shipping in shipping methods
    And I checkout on batch mode until I reach the order confirmation page as a "signed in" user

  @da_top40 @scenario37 @batch
  Scenario: Digital Analytics Top 40 - Scenario 37
    Given I visit the web site as a guest user
    When I navigate to the "Burberry" browse page under "SHOES"
    And I view 7 random products
    And I select a random member product
    And I click on "right" arrow key inside Recently Viewed panel
    And I select a recently viewed product in product display page
    And I add product to my bag from standard PDP Page
    And I navigate to shopping bag page from add to bag page
    And I checkout on batch mode until I reach the order confirmation page as a "guest" user

  @da_top40 @scenario38 @defect_DS-55790
  Scenario: Digital Analytics Top 40 - Scenario 38
    Given I visit the web site as a registered user
    When I navigate to the "ways to shop" page from footer
    And I navigate to the "stores" page from footer
    And I navigate to the "outlet" page from footer
    And I navigate to the "order status" page from footer
    And I navigate to the "pay bill" page from footer
    And I navigate to the "apply & learn more" page from footer

  @da_top40 @scenario39
  Scenario: Digital Analytics Top 40 - Scenario 39
    Given I visit the web site as a guest user
    When I navigate to "DESIGNERS" category page
    And I navigate to "WOMEN" FOB on the left navigation
    And I navigate to "HOME" FOB on the left navigation
    And I move to "Calvin Klein" from the available brands
    And I select "Best Sellers" in sort by drop down
    And I scroll 'down' the page until reach bottom of footer panel
    And I select 'back to top' button
    And I select "Price (low-high)" in sort by drop down
    And I select random color swatch for the given product

  @da_top40 @scenario40 @defect_DS-63671
  Scenario: Digital Analytics Top 40 - Scenario 40
    Given I visit the web site as a guest user
    When  I directly add an available and orderable product "3048" to my bag
    And I select checkout with paypal
    When I login into paypal account
    And I checkout from paypal review page
    Then I should see the "order confirmation" page
    When I place an order
