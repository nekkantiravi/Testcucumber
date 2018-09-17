Feature: Verify MSH basic smoke functionality

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order with a member product as a guest user with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order with a member product as a signed in user with dca cookie as DAL
    Given I visit the mobile web site as a registered user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order with a master product as a guest user with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order with a master product as a signed in user with dca cookie as DAL
    Given I visit the mobile web site as a registered user with dca cookie as DAL
    And I verify that dca cookie is DAL
    And I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order in Iship Mode with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "iship" user from "India"
    Then I should see order number on order receipt page
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order as a registrant after adding registry item from BVR page with dca cookie as DAL
    Given I visit the mobile web site as a registry user with dca cookie as DAL
    And I verify that dca cookie is DAL
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page with dca cookie as DAL
    Given I visit the mobile web site as a registry user with dca cookie as DAL
    And I verify that dca cookie is DAL
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order with bops item as a guest user with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
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
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_order @dca_dal
  Scenario: Place order with bops item as a signed in user with dca cookie as DAL
    Given I visit the mobile web site as a registered user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verifying the Merge bag scenario with dca cookie as DAL
    Given I visit the mobile web site as a registered user without add CC with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available and orderable and member_product" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign out from my current mobile site profile
    And I clear all the cookies
    And I visit the mobile web site as a guest user with dca cookie as DAL
    And I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign in during checkout using mobile website
    Then I verify the functionality of merge bag using mobile website
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario Outline: Verify PROS panel on PDP page in all mode with dca cookie as DAL
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as DAL
    When I navigate PDP page have PROS panel with DCA cookie as DAL
    And I verify that dca cookie is DAL
    Then I verify that "horizontal" recommendation panel is displayed on "pdp" page
    Examples:
      |mode        |
      |domestic    |
      |iship       |

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify PROS panel on registry PDP page with dca cookie as DAL
    Given I visit the mobile web site as a guest user in registry mode with DCA cookie as DAL
    And I verify that dca cookie is DAL
    When I search for "22805"
    Then I verify that "horizontal" recommendation panel is displayed on "registry PDP" page
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify user RVI panel is displayed with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I navigate to "member_product and available and with_color" product PDP page
    And I navigate to "with_customer_ratings and available" product PDP page
    Then I verify that RVI panel is displayed on pdp page for mobile
    And I verify that dca cookie is DAL

  @use_wip @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify zero search page is displayed with dca cookie as DAL
   Given I visit the mobile web site as a guest user with dca cookie as DAL
   And I verify that dca cookie is DAL
   When I search for "xytz"
   Then I verify that zero search result page is displayed using mobile website
   And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify user able to add a e-gift card product in bag as a guest user with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100.00" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "guest" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify user able to add a e-gift card product in bag as a registered user with dca cookie as DAL
    Given I visit the mobile web site as a registered user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100.00" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "signed in" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario Outline: Verify basic functionality of browse page with DCA cookie as DAL in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as DAL
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Jeans          |
    And I verify that dca cookie is DAL
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is DAL

    Examples:
      | mode     |
      | domestic |
      | iship    |


  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify basic functionality of browse page with DCA cookie as DAL in registry mode
    Given I visit the mobile web site as a guest user in registry mode with DCA cookie as DAL
    When I navigate the global navigation menu as follows:
      | Gift Categories        |
      | Dining & Entertaining  |
      | Dinnerware             |
    And I verify that dca cookie is DAL
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario Outline: Verify basic functionality of search results page with DCA cookie as DAL
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as DAL
    When I search using mobile website for "cookware"
    And I verify that dca cookie is DAL
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is DAL

    Examples:
      | mode      |
      | domestic  |
      | iship     |
      | registry  |

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario Outline: Verify basic functionality of dynamic landing page with DCA cookie as DAL in iship & domestic mode
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as DAL
    When I navigate the global navigation menu as follows:
      | Shop                                                     |
      | Handbags & Sunglasses                                    |
      | See All Brands or All Handbag Brands or Handbag Brands   |
    And I select random link to navigate to dynamic landing page using mobile website
    And I verify that dca cookie is DAL
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is DAL

    Examples:
      | mode     |
      | domestic |
      | iship    |

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify basic functionality of dynamic landing page with DCA cookie as DAL in registry mode
    Given I visit the mobile web site as a guest user in registry mode with DCA cookie as DAL
    When I navigate the global navigation menu as follows:
      | Gift Categories          |
      | Kitchen                  |
      | See All Brands or Brands |
    And I select random link to navigate to dynamic landing page using mobile website
    And I verify that dca cookie is DAL
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify GVR functionality with DCA cookie as DAL
    Given I visit the mobile web site as a registry user with dca cookie as DAL
    And I verify that dca cookie is DAL
    And I sign out from my current mobile site profile
    And I navigate to wedding registry page
    And I select find registry on registry home page
    When I search for the existing couple's registry using mobile site
    Then I should find the couple's registry using mobile site
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify autocomplete suggestions are displayed with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I type "mic" in mew search box
    Then I should see "mic" in mew autocomplete suggestions
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page with a member product as a guest user with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "guest" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page with a member product as a signed in user with dca cookie as DAL
    Given I visit the mobile web site as a registered user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page with a master product as a guest user with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "guest" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page with a master product as a signed in user with dca cookie as DAL
    Given I visit the mobile web site as a registered user with dca cookie as DAL
    And I verify that dca cookie is DAL
    And I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page in Iship Mode with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "iship" user from "India"
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page as a registrant after adding registry item from BVR page with dca cookie as DAL
    Given I visit the mobile web site as a registry user with dca cookie as DAL
    And I verify that dca cookie is DAL
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order review page using mobile website as an "signed in" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page as a guest user after adding registry item from GVR(guest registry) page with dca cookie as DAL
    Given I visit the mobile web site as a registry user with dca cookie as DAL
    And I verify that dca cookie is DAL
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order review page using mobile website as a "guest" user
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page with bops item as a guest user with dca cookie as DAL
    Given I visit the mobile web site as a guest user with dca cookie as DAL
    And I verify that dca cookie is DAL
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    And I verify that dca cookie is DAL

  @use_msh_mew_smoke @use_prod @dca_dal
  Scenario: Verify order review page with bops item as a signed in user with dca cookie as DAL
    Given I visit the mobile web site as a registered user with dca cookie as DAL
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I verify that dca cookie is DAL


  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order with a member product as a guest user with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order with a member product as a signed in user with dca cookie as RTP
    Given I visit the mobile web site as a registered user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order with a master product as a guest user with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order with a master product as a signed in user with dca cookie as RTP
    Given I visit the mobile web site as a registered user with dca cookie as RTP
    And I verify that dca cookie is RTP
    And I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order in Iship Mode with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "iship" user from "India"
    Then I should see order number on order receipt page
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order as a registrant after adding registry item from BVR page with dca cookie as RTP
    Given I visit the mobile web site as a registry user with dca cookie as RTP
    And I verify that dca cookie is RTP
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page with dca cookie as RTP
    Given I visit the mobile web site as a registry user with dca cookie as RTP
    And I verify that dca cookie is RTP
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order with bops item as a guest user with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
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
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @dca_rtp
  Scenario: Place order with bops item as a signed in user with dca cookie as RTP
    Given I visit the mobile web site as a registered user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verifying the Merge bag scenario with dca cookie as RTP
    Given I visit the mobile web site as a registered user without add CC with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available and orderable and member_product" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign out from my current mobile site profile
    And I clear all the cookies
    And I visit the mobile web site as a guest user with dca cookie as RTP
    And I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign in during checkout using mobile website
    Then I verify the functionality of merge bag using mobile website
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario Outline: Verify PROS panel on PDP page in all mode with dca cookie as RTP
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as RTP
    When I navigate PDP page have PROS panel with DCA cookie as RTP
    And I verify that dca cookie is RTP
    Then I verify that "horizontal" recommendation panel is displayed on "pdp" page
    And I verify that dca cookie is RTP
    Examples:
      |mode        |
      |domestic    |
      |iship       |

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify PROS panel on registry PDP page with dca cookie as RTP
    Given I visit the mobile web site as a guest user in registry mode with DCA cookie as RTP
    And I verify that dca cookie is RTP
    When I search for "22805"
    Then I verify that "horizontal" recommendation panel is displayed on "registry PDP" page
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify user RVI panel is displayed with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I navigate to "member_product and available and with_color" product PDP page
    And I navigate to "with_customer_ratings and available" product PDP page
    Then I verify that RVI panel is displayed on pdp page for mobile
    And I verify that dca cookie is RTP

  @use_wip @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify zero search page is displayed with dca cookie as RTP
   Given I visit the mobile web site as a guest user with dca cookie as RTP
   And I verify that dca cookie is RTP
   When I search for "xytz"
   Then I verify that zero search result page is displayed using mobile website
   And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify user able to add a e-gift card product in bag as a guest user with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100.00" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "guest" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify user able to add a e-gift card product in bag as a registered user with dca cookie as RTP
    Given I visit the mobile web site as a registered user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100.00" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "signed in" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario Outline: Verify basic functionality of browse page with DCA cookie as RTP in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as RTP
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Jeans          |
    And I verify that dca cookie is RTP
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is RTP

    Examples:
      | mode     |
      | domestic |
      | iship    |


  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify basic functionality of browse page with DCA cookie as RTP in registry mode
    Given I visit the mobile web site as a guest user in registry mode with DCA cookie as RTP
    When I navigate the global navigation menu as follows:
      | Gift Categories        |
      | Dining & Entertaining  |
      | Dinnerware             |
    And I verify that dca cookie is RTP
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario Outline: Verify basic functionality of search results page with DCA cookie as RTP
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as RTP
    When I search using mobile website for "cookware"
    And I verify that dca cookie is RTP
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is RTP

    Examples:
      | mode      |
      | domestic  |
      | iship     |
      | registry  |

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario Outline: Verify basic functionality of dynamic landing page with DCA cookie as RTP in iship & domestic mode
    Given I visit the mobile web site as a guest user in <mode> mode with DCA cookie as RTP
    When I navigate the global navigation menu as follows:
      | Shop                                                       |
      | Handbags & Sunglasses                                      |
      | See All Brands or All Handbag Brands or Handbag Brands     |
    And I select random link to navigate to dynamic landing page using mobile website
    And I verify that dca cookie is RTP
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is RTP

    Examples:
      | mode     |
      | domestic |
      | iship    |

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify basic functionality of dynamic landing page with DCA cookie as RTP in registry mode
    Given I visit the mobile web site as a guest user in registry mode with DCA cookie as RTP
    When I navigate the global navigation menu as follows:
      | Gift Categories          |
      | Kitchen                  |
      | See All Brands or Brands |
    And I select random link to navigate to dynamic landing page using mobile website
    And I verify that dca cookie is RTP
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify GVR functionality with DCA cookie as RTP
    Given I visit the mobile web site as a registry user with dca cookie as RTP
    And I verify that dca cookie is RTP
    And I sign out from my current mobile site profile
    And I navigate to wedding registry page
    And I select find registry on registry home page
    When I search for the existing couple's registry using mobile site
    Then I should find the couple's registry using mobile site
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify autocomplete suggestions are displayed with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I type "mic" in mew search box
    Then I should see "mic" in mew autocomplete suggestions
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page with a member product as a guest user with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "guest" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page with a member product as a signed in user with dca cookie as RTP
    Given I visit the mobile web site as a registered user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page with a master product as a guest user with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "guest" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page with a master product as a signed in user with dca cookie as RTP
    Given I visit the mobile web site as a registered user with dca cookie as RTP
    And I verify that dca cookie is RTP
    And I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page in Iship Mode with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "iship" user from "India"
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page as a registrant after adding registry item from BVR page with dca cookie as RTP
    Given I visit the mobile web site as a registry user with dca cookie as RTP
    And I verify that dca cookie is RTP
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order review page using mobile website as an "signed in" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page as a guest user after adding registry item from GVR(guest registry) page with dca cookie as RTP
    Given I visit the mobile web site as a registry user with dca cookie as RTP
    And I verify that dca cookie is RTP
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order review page using mobile website as a "guest" user
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page with bops item as a guest user with dca cookie as RTP
    Given I visit the mobile web site as a guest user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_prod @dca_rtp
  Scenario: Verify order review page with bops item as a signed in user with dca cookie as RTP
    Given I visit the mobile web site as a registered user with dca cookie as RTP
    And I verify that dca cookie is RTP
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I verify that dca cookie is RTP

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order with a member product as a guest user without dca cookie
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order with a member product as a signed in user without dca cookie
    Given I visit the mobile web site as a registered user
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order with a master product as a guest user without dca cookie
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order with a master product as a signed in user without dca cookie
    Given I visit the mobile web site as a registered user
    And I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order in Iship Mode without dca cookie
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order confirmation page using mobile website as a "iship" user from "India"
    Then I should see order number on order receipt page

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order as a registrant after adding registry item from BVR page without dca cookie
    Given I visit the mobile web site as a registry user
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order confirmation page using mobile website as an "signed in" user

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page without dca cookie
    Given I visit the mobile web site as a registry user
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order confirmation page using mobile website as a "guest" user

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order with bops item as a guest user without dca cookie
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

  @use_msh_mew_smoke @use_order @without_dca_cookie
  Scenario: Place order with bops item as a signed in user without dca cookie
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

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verifying the Merge bag scenario without dca cookie
    Given I visit the mobile web site as a registered user without add CC
    When I add an "available and orderable and member_product" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign out from my current mobile site profile
    And I clear all the cookies
    And I visit the mobile web site as a guest user
    And I add an "available and orderable" product to my bag using mobile website that is not an "BT_furniture" and "select" checkout
    And I sign in during checkout using mobile website
    Then I verify the functionality of merge bag using mobile website

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario Outline: Verify PROS panel on PDP page in all mode without dca cookie
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate PDP page have PROS panel without dca cookie
    Then I verify that "horizontal" recommendation panel is displayed on "pdp" page
    Examples:
      |mode        |
      |domestic    |
      |iship       |

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify PROS panel on registry PDP page without dca cookie
    Given I visit the mobile web site as a guest user in registry mode
    When I search for "22805"
    Then I verify that "horizontal" recommendation panel is displayed on "registry PDP" page

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify user RVI panel is displayed without dca cookie
    Given I visit the mobile web site as a guest user
    When I navigate to "member_product and available and with_color" product PDP page
    And I navigate to "with_customer_ratings and available" product PDP page
    Then I verify that RVI panel is displayed on pdp page for mobile

  @use_wip @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify zero search page is displayed without dca cookie
   Given I visit the mobile web site as a guest user
   When I search for "xytz"
   Then I verify that zero search result page is displayed using mobile website

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify user able to add a e-gift card product in bag as a guest user without dca cookie
    Given I visit the mobile web site as a guest user
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100.00" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "guest" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify user able to add a e-gift card product in bag as a registered user without dca cookie
    Given I visit the mobile web site as a registered user
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100.00" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "signed in" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario Outline: Verify basic functionality of browse page without dca cookie in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Jeans          |
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    Examples:
      | mode     |
      | domestic |
      | iship    |


  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify basic functionality of browse page without dca cookie in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories        |
      | Dining & Entertaining  |
      | Dinnerware             |
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario Outline: Verify basic functionality of search results page without dca cookie
    Given I visit the mobile web site as a guest user in <mode> mode
    When I search using mobile website for "cookware"
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price (low to high)  |
      | Price (high to low)  |
      | Customer Top Rated   |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    Examples:
      | mode      |
      | domestic  |
      | iship     |
      | registry  |

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario Outline: Verify basic functionality of dynamic landing page without dca cookie in iship & domestic mode
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate the global navigation menu as follows:
      | Shop                                                   |
      | Handbags & Sunglasses                                  |
      | See All Brands or All Handbag Brands or Handbag Brands |
    And I select random link to navigate to dynamic landing page using mobile website
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify basic functionality of dynamic landing page without dca cookie in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories          |
      | Kitchen                  |
      | See All Brands or Brands |
    And I select random link to navigate to dynamic landing page using mobile website
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    And I should be able to navigate using pagination functionality using mobile website

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify GVR functionality without dca cookie
    Given I visit the mobile web site as a registry user
    And I sign out from my current mobile site profile
    And I navigate to wedding registry page
    And I select find registry on registry home page
    When I search for the existing couple's registry using mobile site
    Then I should find the couple's registry using mobile site

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify autocomplete suggestions are displayed without dca cookie
    Given I visit the mobile web site as a guest user
    When I type "mic" in mew search box
    Then I should see "mic" in mew autocomplete suggestions

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page with a member product as a guest user without dca cookie
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "guest" user

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page with a member product as a signed in user without dca cookie
    Given I visit the mobile web site as a registered user
    When I add an "available and orderable and member_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page with a master product as a guest user without dca cookie
    Given I visit the mobile web site as a guest user
    When I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "guest" user

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page with a master product as a signed in user without dca cookie
    Given I visit the mobile web site as a registered user
    And I add an "available and orderable and master_product" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page in Iship Mode without dca cookie
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I add a "iship_eligible and available and orderable" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "iship" user from "India"

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page as a registrant after adding registry item from BVR page without dca cookie
    Given I visit the mobile web site as a registry user
    And I add a credit card from my wallet page using mobile website
    When I add "registrable" product to my bag from BVR page using mobile website and "select" checkout
    Then I checkout until I reach the order review page using mobile website as an "signed in" user

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page as a guest user after adding registry item from GVR(guest registry) page without dca cookie
    Given I visit the mobile web site as a registry user
    And I navigate to "registrable" product PDP page
    And I add product to my registry from standard PDP Page using mobile site
    And I navigate back to "home" page using mobile website
    And I sign out from my current mobile site profile
    And I add product to bag from GVR page using mobile website and select checkout
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I click on "continue_shipping_checkout_button" on "responsive_checkout" page
    Then I checkout until I reach the order review page using mobile website as a "guest" user

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page with bops item as a guest user without dca cookie
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

  @use_msh_mew_smoke @use_prod @without_dca_cookie
  Scenario: Verify order review page with bops item as a signed in user without dca cookie
    Given I visit the mobile web site as a registered user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops and signed in" user
    Then I should see bops shipping in order summary section






