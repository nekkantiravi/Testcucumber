Feature: Marketing top 40 high priority mcom mew coremetrics test scenario

  @mew_da_top40 @scenario1 @prod @defect_DS-56120
  Scenario: Mew Coremetrics Top 40 - Scenario 1
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | Activewear     |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    Then I should be in mobile shopping bag

  @mew_da_top40 @scenario2 @prod @defect_DS-50157
  Scenario: Mew Coremetrics Top 40 - Scenario 2
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "watches"
    And I select "Brand" facet on left nav using mobile website
    And I select "Bulova" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select "Brand" facet on left nav using mobile website
    And I select "Armitron" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select "Band Color" facet on left nav using mobile website
    And I select "Black" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website

  @mew_da_top40 @scenario3 @prod @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 3
    Given I visit the mobile web site as a guest user
    When I type "act" in mew search box
    Then I select "Activewear" from mew autocomplete suggestions
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site

  @mew_da_top40 @scenario4 @batch @defect_DS-50152 @defect_DS-50027
  Scenario: Mew Coremetrics Top 40 - Scenario 4
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop       |
      | Men        |
      | Activewear |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario5 @batch @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 5
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | Shop                               |
      | Women                              |
      | Clothing or All Women's Clothing   |
      | Activewear                         |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "signed in" user

  @mew_da_top40 @scenario6 @prod @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 6
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    When I navigate the global navigation menu as follows:
      | Shop                 |
      | For The Home         |
      | More For The Home    |
      | Home Decor           |
    Then I should be on mobile browse page

  @mew_da_top40 @scenario7 @batch @defect_DS-54879
  Scenario: Mew Coremetrics Top 40 - Scenario 7
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    When I navigate the global navigation menu as follows:
      | Shop                              |
      | Women                             |
      | All Women's Clothing or Clothing  |
      | Skirts                            |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    And I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    When I checkout on batch mode until I reach the order review page using mobile website as a "iship" user from "Australia"

  @mew_da_top40 @scenario8
  Scenario: Mew Coremetrics Top 40 - Scenario 8
    Given I visit the mobile web site as a registered user
    And I select view all lists on my account page using mobile website
    When I delete all lists in wishlist page using mobile website
    And I navigate back to home page using mobile website
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Jeans          |
    And I select a random member product using mobile website
    When I select product related attributes from PDP using mobile website
    And I click Add to Wish List button on PDP using mobile website
    When I click on view list in ATW overlay from PDP using mobile website
    When I add product to my bag from wishlist page using mobile website and continue shopping
    And I navigate to a random product PDP from wish list page using mobile website
    Then I should be redirected to wishlist PDP using mobile website

  @mew_da_top40 @scenario9 @defect_DS-50750 @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 9
    Given I visit the mobile web site as a registered user without add CC
    And I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to my profile page using mobile website

  @mew_da_top40 @scenario10 @prod
  Scenario: Mew Coremetrics Top 40 - Scenario 10
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                        |
      | Men                         |
      | Cologne & Grooming          |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I navigate back to home page using mobile website
    And I navigate the global navigation menu as follows:
      | Shop                                       |
      | Juniors & Guys                             |
      | All Juniors' Clothing or Juniors' Clothing |
      | Dresses                                    |
    And I select a random member product using mobile website
    And I select a recently viewed product using mobile website
    When I add product to my bag from standard PDP Page using mobile site

  @mew_da_top40 @scenario11 @prod @defect_DS-61886
  Scenario: Mew Coremetrics Top 40 - Scenario 11
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "asdasdasd"

  @mew_da_top40 @scenario12
  Scenario: Mew Coremetrics Top 40 - Scenario 12
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Registry or Wedding Registry |
    And I select "create your registry" from mobile registry home page
    And I start to create a new registry from mobile registry capture email page
    And I create a new registry using mobile website
    Then I should be navigated to the mobile registry welcome page

  @mew_da_top40 @scenario13 @defect_DS-50152 @defect_DS-63389
  Scenario: Mew Coremetrics Top 40 - Scenario 13
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site
    Then I should be navigated to the mobile my account page

  @mew_da_top40 @scenario14
  Scenario: Mew Coremetrics Top 40 - Scenario 14
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to the create profile page
    And I create a new profile in mobile site
    And I sign out from my current mobile site profile
    And I navigate the global navigation menu as follows:
      | Registry or Wedding Registry|
    And I select "create your registry" from mobile registry home page
    And I sign in with existing profile on mobile capture email page
    And I continue creating registry from mobile create registry page
    Then I should be navigated to the mobile registry welcome page

  @mew_da_top40 @scenario15 @prod
  Scenario: Mew Coremetrics Top 40 - Scenario 15
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                              |
      | Women                             |
      | Clothing or All Women's Clothing  |
      | Activewear                        |
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I select "pinterest" social icon on PDP Page using mobile website
    Then I should see "pinterest" title in the popup window using mobile website
    When I select "email" social icon on PDP Page using mobile website
    Then I should see "email" title in the popup window using mobile website

  @mew_da_top40 @scenario16
  Scenario: Mew Coremetrics Top 40 - Scenario 16
    Given I visit the mobile web site as a registry user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | For The Home   |
      | Dining & Entertaining  |
      | Flatware & Silverware  |
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    And I replace product ID with available "22805" product ID using mobile website
    When I search for a product in a nearby store using mobile website
    When I navigate back to pdp page from pick up store page
    And I add the product to a registry using mobile website

  @mew_da_top40 @scenario17 @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 17
    Given I visit the mobile web site as a guest user
    When I sign in to my existing profile using mobile website
    When I search using mobile website for "men's watches"
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    When I navigate to shopping bag page from add to bag page using mobile website

  @mew_da_top40 @scenario18 @batch @defect_DS-54962
  Scenario: Mew Coremetrics Top 40 - Scenario 18
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                |
      | For The Home        |
      | More for the Home   |
      | Home Decor          |
    And I select a random master product using mobile website
    Then I should be redirected to master PDP page in mobile website
    And I should see member products listed in mobile website
    When I add member product from PDP and select "checkout" using mobile website
    Then I should be in mobile shopping bag
    When I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario19 @defect_DS-50152 @batch
  Scenario: Mew Coremetrics Top 40 - Scenario 19
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "Lenox Eternal Serving Set"
    Then I should be in Search Landing page using mobile website
    When I select a predefined orderable random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario20 @batch
  Scenario: Mew Coremetrics Top 40 - Scenario 20
    Given I visit the mobile web site as a guest user
    When I type "jea" in mew search box
    Then I select "Jeans" from mew autocomplete suggestions
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario21 @batch @defect_DS-50152 @defect_DS-54962
  Scenario: Mew Coremetrics Top 40 - Scenario 21
    Given I visit the mobile web site as a guest user
    When I type "bedd" in mew search box
    Then I select "calvin klein bedding" from mew autocomplete suggestions
    When I select a random master product using mobile website
    Then I should be redirected to master PDP page in mobile website
    And I should see member products listed in mobile website
    When I add member product from PDP and select "checkout" using mobile website
    Then I should be in mobile shopping bag
    When I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario22 @batch @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 22
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "lenox vintage jewel 5"
    Then I should be in Search Landing page using mobile website
    When I select a predefined orderable random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario23 @batch
  Scenario: Mew Coremetrics Top 40 - Scenario 23
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "golf polo"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario24 @batch @defect_DS-54962 @defect_DS-54483 @defect_DS-54484 @defect_DS-72117 @defect_DS-72123
  Scenario: Mew Coremetrics Top 40 - Scenario 24
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                  |
      | Men                   |
      | Activewear            |
    And I select "Size" facet on left nav using mobile website
    And I select "XS" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select "Price" facet on left nav using mobile website
    And I select "$20-$40" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select "Color" facet on left nav using mobile website
    And I select "Black" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario25 @batch @defect_DS-54962 @defect_DS-72118
  Scenario: Mew Coremetrics Top 40 - Scenario 25
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                              |
      | Women                             |
      | All Women's Clothing or Clothing  |
      | Skirts                            |
    And I select "Brand" facet on left nav using mobile website
    And I select "Alfani" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select "Color" facet on left nav using mobile website
    And I select "Black" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select "Customers' Top Rated" facet on left nav using mobile website
    And I select "4 stars & up" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario26 @prod @defect_DS-54962 @defect_DS-72119
  Scenario: Mew Coremetrics Top 40 - Scenario 26
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                                        |
      | Juniors & Guys                              |
      | All Juniors' Clothing or Juniors' Clothing  |
      | Dresses                                     |
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    Then I visit the mobile web site as a guest user
    When I search using mobile website for "bracelets"
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website

  @mew_da_top40 @scenario27 @batch @defect_54962
  Scenario: Mew Coremetrics Top 40 - Scenario 27
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop              |
      | Shoes             |
      | Women's Shoes     |
      | All Women's Shoes |
    When I select a random member_alternate_image product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I select a random alternative image using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario28 @batch @defect_DS-72117 @defect_DS-54962
  Scenario: Mew Coremetrics Top 40 - Scenario 28
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | Pants          |
    And I click on Free pick up
    And I input "10001" as zip code in bops facet and select a store
    And I confirm selected facets using mobile website
    When I select a random member product using mobile website
    Then I add bops available product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page using mobile website
    And I select pick up option for bops item using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "signed in bops" user

  @mew_da_top40 @scenario29 @defect_DS-54962 @batch @defect_DS-72123
  Scenario: Mew Coremetrics Top 40 - Scenario 29
    Given I visit the mobile web site as a guest user
    When I navigate to wedding registry page
    When I navigate the global navigation menu as follows:
      | Gift Categories                 |
      | Kitchen                         |
      | Cookware & Cookware Sets        |
    When I select a random member product using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario30 @defect_DS-54960
  Scenario: Mew Coremetrics Top 40 - Scenario 30
    Given I visit the mobile web site as a registered user
    And I navigate the global navigation menu as follows:
      | Deals |
    Then I should be redirected to deals page using mobile website
    When I select a random "offer" from deals & promotions page using mobile website
    And I select "Add To Wallet" link from offers and details panel using mobile website
    And I select a random "coupon" from deals & promotions page using mobile website
    And I select "shop now" link from offers and details panel using mobile website

  @mew_da_top40 @scenario31 @defect_DS-50152 @defect_DS-54959 @defect_DS-52135
  Scenario: Mew Coremetrics Top 40 - Scenario 31
    Given I visit the mobile web site as a registered user without add CC
    And I navigate the global navigation menu as follows:
      | My Account |
    And I add a credit card from my wallet page using mobile website
    And I add a offer "X1A001IEZB17" to my wallet using mobile website
    And I remove a valid offer to my wallet using mobile website

  @mew_da_top40 @scenario32 @batch @defect_DS-50152 @defect_DS-55637
  Scenario: Mew Coremetrics Top 40 - Scenario 32
    Given I visit the mobile web site as a guest user
    When  I directly add an available and orderable product "22804" to my bag
    And I apply invalid promo code "asdasdasd" using mobile website
    Then I verify the promo code validation error message appeared in mobile website
    When I apply valid promo code "VALPAK10" using mobile website
    And I remove the promo code using mobile website
    And I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site
    And I navigate to shopping bag page using mobile website
    And I apply invalid promo code "asdasdasd" using mobile website
    And I apply valid promo code "VALPAK10" using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "responsive signed in" user

  @mew_da_top40 @scenario33 @batch
  Scenario: Mew Coremetrics Top 40 - Scenario 33
    Given I visit the mobile web site as a registered user
    And I navigate the global navigation menu as follows:
      | My Account |
#    And I navigate to my plenti page using mobile website
    And I add fully_enrolled_usl id on my account page using mobile website
    When I add a predefined orderable product to my bag using mobile website
    And I add fully_enrolled_usl id on shopping bag page using mobile website
    And I checkout until I reach the shipping & payment page using mobile website as a "responsive_signed in" user
    And I remove USL ID from shipping and payment page using mobile website
    And I lookup plenti id using valid usl phone number on payment page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "responsive signed in" user

  @mew_da_top40 @scenario34 @defect_DS-63388
  Scenario: Mew Coremetrics Top 40 - Scenario 34
    Given I visit the mobile web site as a registered user
    And I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to my plenti page using mobile website
    And I click "join for free" button using mobile website
#    And I click "Join Now" button using mobile website
    And I opt for enrolment from plenti sign in page using mobile website

  @mew_da_top40 @scenario35 @defect_DS-66292 @defect_72192
  Scenario: Mew Coremetrics Top 40 - Scenario 35
    Given I visit the mobile web site as a registered user
    And I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to my plenti page using mobile website
    And I click "join for free" button using mobile website
#    And I click "Join Now" button using mobile website
    And I click "Enroll Cancel" button using mobile website
    And I click "YES, CANCEL" button using mobile website
    #And I click "join for free" button using mobile website
    And I click "Join for free" button using mobile website
    And I opt for enrolment from plenti sign in page using mobile website

  @mew_da_top40 @scenario36 @batch
  Scenario: Mew Coremetrics Top 40 - Scenario 36
    Given I visit the mobile web site as a registered user
    When I add a "orderable and available_sdd" product to my bag using mobile website that is not a "master_product" and select checkout
    And I checkout until I reach the shipping & payment page using mobile website as a "responsive signed in" user
    And I enter sdd_eligible address on shipping page using mobile website for signed in user
    And I select sdd_shipping in shipping methods using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "responsive signed in" user

  @mew_da_top40 @scenario37 @prod @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 37
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Shoes |
    And I close the global navigation menu
    And I navigate to "women's Heel" category browse page using mobile website
    And I navigate back to "category splash" page using mobile website
    When I navigate the global navigation menu as follows:
      | Women's Shoes  |
      | Boots  |
    And I select a random member product using mobile website
    And I navigate back to "category splash" page using mobile website
    And I select a random member product using mobile website
    And I select a recently viewed product using mobile website
    Then I should be redirected to PDP page using mobile website

  @mew_da_top40 @scenario38 @prod
  Scenario: Mew Coremetrics Top 40 - Scenario 38
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Stores |
    And I navigate back to home page using mobile website
    And I navigate to "customer service" footer links using mobile website
#    And I navigate to "emails or texts" footer links using mobile website
#    And I navigate back to home page using mobile website
    And I navigate to "find a store" footer links using mobile website
    Then I should be redirected to store page using mobile website
    When I type "94102" into the search bar of the stores page using mobile website
    Then I should see auto-complete suggestion store names
    When I select a auto-complete suggestion store name
    And I select "Macy's Union Square" store name
    Then I should be redirected to store details panel using mobile website
    When I select "Directions" from store details page using mobile website

  @mew_da_top40 @scenario39 @prod @defect_DS-72117 @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 39
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Pants          |
    And I select "Best Sellers" from "category browse" page using mobile website
    And I select "Brand" facet on left nav using mobile website
    And I select "Alfani" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website

  @mew_da_top40 @scenario40 @prod @defect_DS-50152
  Scenario: Mew Coremetrics Top 40 - Scenario 40
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I enter invalid "invalid_email@gmail.com" and "12345"
    When I add a predefined orderable product to my bag using mobile website
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I select continue button on guest shipping page using mobile website
    And I enter shipping address on guest shipping page using mobile website
    And I select continue button on guest payment page using mobile website
