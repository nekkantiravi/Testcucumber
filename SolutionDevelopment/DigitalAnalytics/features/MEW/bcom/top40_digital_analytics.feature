Feature: Marketing top 40 high priority bcom mew coremetrics test scenario

  @mew_da_top40 @scenario1 @defect_DS-67632 @defect_DS-67633 @defect_DS-67637 @defect_DS-67709
  Scenario: Mew Coremetrics Top 40 - Scenario 1
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Men                        |
      | Clothing or All Clothing   |
      | Active & Workout           |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website

  @mew_da_top40 @scenario2 @defect_DS-67627 @defect_DS-67632 @defect_DS-67633 @defect_DS-67709 @defect_DS-67825
  @defect_DS-67630
  Scenario: Mew Coremetrics Top 40 - Scenario 2
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "mens watches"
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    Then I should be in mobile shopping bag

  @mew_da_top40 @scenario3 @defect_DS-67627 @defect_DS-67632 @defect_DS-67633 @defect_DS-67709
  Scenario: Mew Coremetrics Top 40 - Scenario 3
    Given I visit the mobile web site as a guest user
    When I type "black" in mew search box
    Then I select "black halo" from mew autocomplete suggestions
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website

  @mew_da_top40 @scenario4 @batch @defect_DS-67827
  Scenario: Mew Coremetrics Top 40 - Scenario 4
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Men                      |
      | Clothing or All Clothing |
      | Active & Workout         |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

    @mew_da_top40 @scenario5 @batch @defect_DS-67771 @defect_DS-67772
    Scenario: Mew Coremetrics Top 40 - Scenario 5
      Given I visit the mobile web site as a registered user
      When I navigate the global navigation menu as follows:
        | Men                      |
        | Clothing or All Clothing |
        | Active & Workout         |
      And I select a random member product using mobile website
      Then I should be redirected to PDP page using mobile website
      When I add product to my bag from standard PDP page
      Then I should be redirected to ATB page using mobile website
      When I navigate to shopping bag page from add to bag page using mobile website
      And I checkout until I reach the shipping page using mobile website as a "signed in" user
      And I checkout on batch mode until I reach the order confirmation page using mobile website as a "signed in" user

   @mew_da_top40 @scenario6 @defect_DS-67776 @defect_DS-67777 @defect_DS-70525
     Scenario: Mew Coremetrics Top 40 - Scenario 6
     Given I visit the mobile web site as a guest user
     When I navigate to change country page using mobile website
     And I change country to "Germany" using mobile website
     And I close the welcome mat if it's visible using mobile website
     When I navigate the global navigation menu as follows:
       | HOME                  |
       | Dining & Entertaining |
       | Dinnerware            |
     Then I should be on mobile browse page

  @mew_da_top40 @scenario7 @batch @defect_DS-67776 @defect_DS-67709 @defect_DS-67828 @defect_DS-69091 @defect_DS-69092
  Scenario: Mew Coremetrics Top 40 - Scenario 7
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "Germany" using mobile website
    And I close the welcome mat if it's visible using mobile website
    When I navigate the global navigation menu as follows:
      | WOMEN            |
      | Dresses          |
      | Casual           |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    And I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    When I checkout on batch mode until I reach the order review page using mobile website as a "iship" user from "Germany"

  @mew_da_top40 @scenario8 @defect_DS-67918
  Scenario: Mew Coremetrics Top 40 - Scenario 8
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | MEN                      |
      | Clothing or All Clothing |
      | Polos                    |
    And I select a random member product using mobile website
    When I select product related attributes from PDP using mobile website
    And I click Add to Wish List button on PDP using mobile website
    When I click on view list in ATW overlay from PDP using mobile website
    When I add product to my bag from wishlist page using mobile website and continue shopping

  @mew_da_top40 @scenario9 @defect_DS-67921
  Scenario: Mew Coremetrics Top 40 - Scenario 9
    Given I visit the mobile web site as a registered user
    And I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    And I navigate to my profile page using mobile website

  @mew_da_top40 @scenario10 @defect_DS-67709
  Scenario: Mew Coremetrics Top 40 - Scenario 10
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | MEN                      |
      | Clothing or All Clothing |
      | Polos                    |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I navigate back to home page using mobile website
    And I navigate the global navigation menu as follows:
      | WOMEN            |
      | Dresses          |
      | Casual           |
    And I select a random member product using mobile website
    And I select a recently viewed product using mobile website
    When I add product to my bag from standard PDP Page using mobile site

  @mew_da_top40 @scenario11
  Scenario: Mew Coremetrics Top 40 - Scenario 11
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "asdasdasd"
    Then I should be in Search Landing page using mobile website

  @mew_da_top40 @scenario12 @defect_DS-68127
  Scenario: Mew Coremetrics Top 40 - Scenario 12
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | THE REGISTRY |
    When I select "create" from mobile registry home page
    And I start to create a new registry from mobile registry capture email page
    And I create a new registry using mobile website
    And I should be navigated to the mobile registry manager page

  @mew_da_top40 @scenario13
  Scenario: Mew Coremetrics Top 40 - Scenario 13
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site
    And I sign out from my current mobile site profile
    And I navigate the global navigation menu as follows:
      | THE REGISTRY |
    And I select "create" from mobile registry home page
    And I sign in with existing profile on mobile capture email page
    And I continue creating registry from mobile create registry page
    Then I should be navigated to the mobile registry manager page

  @mew_da_top40 @scenario14 @batch @defect_DS-67972 @defect_DS-67971 @defect_DS-67709
  Scenario: Mew Coremetrics Top 40 - Scenario 14
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | SHOES    |
      | Booties  |
    And I select a random member product using mobile website
    Then I should see recommendation panel on pdp page using mobile website
    When I select first recommended product from pdp page using mobile website
    And I add product to my bag from standard PDP Page using mobile site
    And I navigate to shopping bag page from add to bag page using mobile website
    Then I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

  @mew_da_top40 @scenario15
  Scenario: Mew Coremetrics Top 40 - Scenario 15
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | WOMEN             |
      | Active & Workout  |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I select share button on pdp page using mobile website
    And I select "twitter" social icon on PDP Page using mobile website
    And I select "pinterest" social icon on PDP Page using mobile website
#    And I select "email" social icon on PDP Page using mobile website(removed D-55635)
    And I select "facebook" social icon on PDP Page using mobile website

  @mew_da_top40 @scenario16 @defect_DS-67976
  Scenario: Mew Coremetrics Top 40 - Scenario 16
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site
    Then I should be navigated to the mobile my account page

# QA environment home contains one asset
  @mew_da_top40 @scenario17 @defect_DS-67976
  Scenario: Mew Coremetrics Top 40 - Scenario 17
    Given I visit the mobile web site as a guest user
    When I select random asset from mew home page
    And I navigate back to home page using mobile website

  @mew_da_top40 @scenario18 @batch @defect_DS-68141 @defect_DS-68145 @defect_DS-68146
  Scenario: Mew Coremetrics Top 40 - Scenario 18
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | HOME                |
      | Bedding             |
      | Bedding Collections |
    And I select a random master product using mobile website with customer ratings
    Then I should be redirected to master PDP page in mobile website
    And I should see member products listed in mobile website
    When I add member product from PDP and select "checkout" using mobile website
    Then I should be in mobile shopping bag
    When I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario19 @batch @defect_DS-68292 @defect_DS-68298 @defect_DS-68307 @defect_DS-68308
  Scenario: Mew Coremetrics Top 40 - Scenario 19
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "pumps"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario20 @batch @defect_DS-68155 @defect_DS-68156 @defect_DS-68159 @defect_DS-67630
  Scenario: Mew Coremetrics Top 40 - Scenario 20
    Given I visit the mobile web site as a guest user
    When I type "clin" in mew search box
    And I select "Clinique Skin Care" from mew autocomplete suggestions
    Then I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

  @mew_da_top40 @scenario21 @batch @defect_DS-68155 @defect_DS-67627 @defect_DS-67632 @defect_DS-67630
  Scenario: Mew Coremetrics Top 40 - Scenario 21
    Given I visit the mobile web site as a guest user
    When I type "Ralph L" in mew search box
    And I select "Ralph Lauren Sheets" from mew autocomplete suggestions
    And I select a random master product using mobile website
    Then I should be redirected to master PDP page in mobile website
    And I should see member products listed in mobile website
    When I add member product from PDP and select "checkout" using mobile website
    Then I should be in mobile shopping bag
    When I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

  @mew_da_top40 @scenario22 @batch @defect_DS-68287 @defect_DS-68157 @defect_DS-68158
  Scenario: Mew Coremetrics Top 40 - Scenario 22
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "dress"
    Then I should be in Search Landing page using mobile website
    When I select "Department" facet on left nav using mobile website
    And I select "Women" sub facet on left nav using mobile website
    And I confirm selected facets using mobile website
    Then I click show more to expand facet panel using mobile website
    When I select "Designer" facet on left nav using mobile website
    And I select "BCBGMAXAZRIA" sub facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select "Size" facet on left nav using mobile website
    And I select "RTW_CLOTHING_SIZE_T#4, S" size facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select "Price" facet on left nav using mobile website
    And I select "$100 - $250" sub facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select "Color" facet on left nav using mobile website
    And I select "Black" sub facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select "Sales & Offers" facet on left nav using mobile website
    And I select "Sales & Offers" sub facet from left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

  @mew_da_top40 @scenario23 @batch @defect_DS-68289 @defect_DS-68146 @defect_DS-68291
  Scenario: Mew Coremetrics Top 40 - Scenario 23
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | WOMEN                     |
      | Lingerie, Bras & Panties  |
    When I select "Designer" facet on left nav using mobile website
    And I select "Hanky Panky" sub facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    And I click show more to expand facet panel using mobile website
    When I select "Size" facet on left nav using mobile website
    And I select "RTW_CLOTHING_SIZE_T#4, S" size facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select "Price" facet on left nav using mobile website
    And I select "$20 - $40" sub facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select "Color" facet on left nav using mobile website
    And I select "Black" sub facet on left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select "Sales & Offers" facet on left nav using mobile website
    And I select "Sales & Offers" sub facet from left nav using mobile website
    Then I confirm selected facets using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

  @mew_da_top40 @scenario24 @batch @defect_DS-68289 @defect_DS-68298 @defect_DS-68292 @defect_DS-68385 @defect_DS-67925
  @defect_DS-68367
  Scenario: Mew Coremetrics Top 40 - Scenario 24
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Beauty       |
      | Skin Care    |
      | Moisturizers |
    And I select a random member product using mobile website with customer ratings
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    And I click on the continue shopping button from ATB page using mobile website
    And I navigate back to home page using mobile website
    When I search using mobile website for "bracelets"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website with customer ratings
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

  @mew_da_top40 @scenario25 @batch @defect_DS-68287 @defect_DS-67630
  Scenario: Mew Coremetrics Top 40 - Scenario 25
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shoes     |
      | All Shoes |
    And I select a random member_alternate_image product using mobile website
    And I select a random alternative image using mobile website
    And I select product related attributes from PDP using mobile website
    When I add "4" quantity to my bag from mobile standard PDP Page
    Then I add product to my bag from standard PDP Page using mobile site
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "guest" user

  @mew_da_top40 @scenario26 @batch @defect_DS-67925 @defect_DS-68367
  Scenario: Mew Coremetrics Top 40 - Scenario 26
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | Home                         |
      | Dining & Entertaining        |
      | Dinnerware                   |
    When I select "Pick up in-store" facet on left nav using mobile website
    And I input "10021" as zip code in bops facet and select a store
    Then I confirm selected facets using mobile website
    When I select a random member product using mobile website with customer ratings
    Then I should be redirected to PDP page using mobile website
    And I replace product ID with available "399242" product ID using mobile website
    When I add product to my bag from standard PDP page
    When I navigate to shopping bag page from add to bag page using mobile website
    And I select pick up option for bops item using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "signed in bops" user

  @mew_da_top40 @scenario27 @defect_DS-68363 @defect_DS-69275
  Scenario: Mew Coremetrics Top 40 - Scenario 27
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | The Registry  |
      | Find Registry |
    And I navigate the global navigation menu as follows:
      | Shop         |
      | Kitchen      |
#    And I navigate to registry "Kitchen" browse page from site menu using mobile website
    When I select a random member product using mobile website
    When I add product to my registry from standard PDP Page using mobile site

  @mew_da_top40 @scenario28
  Scenario: Mew Coremetrics Top 40 - Scenario 28
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | MY bWALLET   |
    And I click on add offer on wallet page using mobile website
    And I add a offer "OMNISMART" to my wallet using mobile website
    And I add a credit card from my wallet page using mobile website
    And I directly add an available and orderable product "1705395" to my bag in mobile site
    And I select the bwallet offer in shopping bag using mobile website

  @mew_da_top40 @scenario29 @defect_DS-68379
  Scenario: Mew Coremetrics Top 40 - Scenario 29
    Given I visit the mobile web site as a registered user
    When I search using mobile website for "Perfume"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout until I reach the shipping & payment page using mobile website as a "responsive_signed in" user

  @mew_da_top40 @scenario30 @wip @defect_DS-68638
  Scenario: Mew Coremetrics Top 40 - Scenario 30
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | MY LOYALLIST   |
      | Become a Loyallist |
    Then I should be able to enroll in to the loyalty program as a "signed_in" user using mobile website
    When I navigate the global navigation menu as follows:
      | MENU           |
      | MY LOYALLIST   |
      | View My Points |

  @mew_da_top40 @scenario31
  Scenario: Mew Coremetrics Top 40 - Scenario 31
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT   |
    And I navigate to the loyallist account association page using mobile website
    And I should be able to associate my account by loyallist number using "sujath" details on mobile website
    
  @mew_da_top40 @scenario32
  Scenario: Mew Coremetrics Top 40 - Scenario 32
    Given I visit the mobile web site as a registered user
    When I navigate the global navigation menu as follows:
      | MY LOYALLIST   |
      | Become a Loyallist |
    Then I should be able to enroll in to the loyalty program as a "signed_in" user using mobile website

  @mew_da_top40 @scenario33
  Scenario: Mew Coremetrics Top 40 - Scenario 33
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | MY LOYALLIST   |
      | Become a Loyallist |
    And I navigate to the loyalty enrollment page using mobile website
    Then I should be able to enroll in to the loyalty program as a "guest" user using mobile website

  @mew_da_top40 @scenario34 @wip
  Scenario: Mew Coremetrics Top 40 - Scenario 34
    Given I visit the mobile web site as a registered user
    When I add a "orderable and available_sdd" product to my bag using mobile website that is not a "master_product" and select checkout
    And I checkout until I reach the shipping & payment page using mobile website as a "responsive signed in" user
    And I enter sdd_eligible address on shipping page using mobile website for signed in user
    And I select sdd_shipping in shipping methods using mobile website
    And I checkout on batch mode until I reach the order review page using mobile website as a "responsive signed in" user

  @mew_da_top40 @scenario35 @defect_DS-68292 @defect_DS-68648 @defect_DS-68649
  Scenario: Mew Coremetrics Top 40 - Scenario 35
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Women  |
      | Dresses |
    And I view 5 random member products using mobile website
    #And I click on "right" arrow key inside Recently Viewed panel using mobile website
    And I select a recently viewed product using mobile website
    Then I should be redirected to PDP page using mobile website
    When I add product to my bag from standard PDP page
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    And I checkout on batch mode until I reach the order confirmation page using mobile website as a "guest" user

  @mew_da_top40 @scenario36 @defect_DS-68741 @defect_DS-68743
  Scenario: Mew Coremetrics Top 40 - Scenario 36
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS or DESIGNERS|
      | WOMEN         |
    Then I should be redirected to designer page using mobile website
    When I navigate back to home page using mobile website
    When I navigate the global navigation menu as follows:
      | ALL DESIGNERS or DESIGNERS|
      | HOME          |
    And I navigate to "Calvin Klein" designer category browse page using mobile website
    Then I select "Best Sellers" in sort by drop down on designer page using mobile website
    When I scroll to bottom and select back to top arrow using mobile website
    And I select "Price: Low to High" in sort by drop down on designer page using mobile website

  @mew_da_top40 @scenario37 @defect_DS-70689
  Scenario: Mew Coremetrics Top 40 - Scenario 37
    Given I visit the mobile web site as a guest user
    When  I directly add an available and orderable product "1145398" to my bag in mobile site
    And I select checkout with paypal in mobile site
    When I login into Paypal account using mobile site
    And I checkout from Paypal review page using mobile site
    When I place an Order using mobile site
    Then I should see the "order_confirmation" Page
