# Author: MSH - Operational Capabilities
# Date Created: May-18-2017


Feature: Verify MSH basic smoke functionality

  @use_msh_smoke @use_prod @dca_dal @msh_priority2
  Scenario: Verify zero search page is displayed with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I search for "xytz"
    Then I verify that zero search result page is displayed
    And I verify that page is served from "DAL" DC


  @use_msh_smoke @use_prod @dca_dal @msh_priority2
  Scenario: Verify autocomplete suggestions are displayed with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I type "wo" in search box
    Then I should see autocomplete suggestions
    Then I verify that page is served from "DAL" DC


  @use_msh_smoke @use_prod @dca_rtp @msh_priority3
  Scenario: Verify zero search page is displayed with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I search for "xytz"
    Then I verify that zero search result page is displayed
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority3
  Scenario: Verify autocomplete suggestions are displayed with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I type "wo" in search box
    Then I should see autocomplete suggestions
    Then I verify that page is served from "RTP" DC


  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario Outline: Verify SEO tag cloud displayed on browse/category splash and pdp pages with dca cookie as DAL
    Given I visit the web site as a guest user
    When I navigate to <page_type> page in domestic mode with DCA cookie as DAL
    Then I verify that page is served from "DAL" DC
    And I verify that SEO tag clouds are displayed
    And I verify that page is served from "DAL" DC
    Examples:
      | page_type       |
      | category splash |
      | browse          |
      | pdp             |

  @use_msh_smoke @use_prod @dca_rtp @msh_priority3
  Scenario Outline: Verify SEO tag cloud displayed on browse/category splash and pdp pages with dca cookie as RTP
    Given I visit the web site as a guest user
    When I navigate to <page_type> page in domestic mode with DCA cookie as RTP
    Then I verify that page is served from "RTP" DC
    And I verify that SEO tag clouds are displayed
    And I verify that page is served from "RTP" DC
    Examples:
      | page_type       |
      | category splash |
      | browse          |
      | pdp             |

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page with a member product as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "member_product and available" product to my bag
    Then I checkout until I reach the order review page as an "guest" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page with a master product as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "master_product and available" product to my bag
    Then I checkout until I reach the order review page as an "guest" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify zero search page is displayed without dca cookie
    Given I visit the web site as a guest user
    When I search for "xytz"
    Then I verify that zero search result page is displayed

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario: Verify autocomplete suggestions are displayed without dca cookie
    Given I visit the web site as a guest user
    When I type "wo" in search box
    Then I should see autocomplete suggestions

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario Outline: Verify SEO tag cloud displayed on browse/category splash and pdp pages without dca cookie
    Given I visit the web site as a guest user
    When I navigate to <page_type> page in domestic mode without dca cookie
    And I verify that SEO tag clouds are displayed
    Examples:
      | page_type       |
      | category splash |
      | browse          |
      | pdp             |

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page with a master product as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "master_product and available" product to my bag
    Then I checkout until I reach the order review page as an "guest" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario: Verify order review page with a master product as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I add an "master_product and available" product to my bag
    Then I checkout until I reach the order review page as an "guest" user

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario: Verify order review page with a member product as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I add an "member_product and available" product to my bag
    Then I checkout until I reach the order review page as an "guest" user

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page with a member product as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "member_product and available" product to my bag
    Then I checkout until I reach the order review page as an "guest" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order with a master product as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order in Iship Mode with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order in Iship Mode with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_dal @msh_priority2
  Scenario: Verify order review page in Iship Mode with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available" product to my bag
    And I checkout until I reach the order review page as a "iship" user from "India"
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario: Verify order review page in Iship Mode without dca cookie
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available" product to my bag
    And I checkout until I reach the order review page as a "iship" user from "India"

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page in Iship Mode with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available" product to my bag
    And I checkout until I reach the order review page as a "iship" user from "India"
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario Outline: Verify PROS panel on PDP page in all mode with dca cookie as DAL
    Given I visit the web site as a guest user in "<root>" mode
    When I navigate PDP page have PROS panel with DCA cookie as DAL
    And I verify that page is served from "DAL" DC
    Then I verify that "vertical" recommendation panel is displayed on pdp page
    Then I verify that "horizontal" recommendation panel is displayed on pdp page
    Examples:
      |root        |
      |domestic    |
      |registry |
      |iship    |

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario Outline: Verify PROS panel on PDP page in all mode with dca cookie as RTP
    Given I visit the web site as a guest user in "<root>" mode
    When I navigate PDP page have PROS panel with DCA cookie as RTP
    And I verify that page is served from "RTP" DC
    Then I verify that "vertical" recommendation panel is displayed on pdp page
    Then I verify that "horizontal" recommendation panel is displayed on pdp page
    Examples:
      |root        |
      |domestic    |
      |registry |
      |iship    |

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario Outline: Verify PROS panel on PDP page in all mode without dca cookie
    Given I visit the web site as a guest user in "<root>" mode
    When I navigate PDP page have PROS panel without dca cookie
    Then I verify that "vertical" recommendation panel is displayed on pdp page
    Then I verify that "horizontal" recommendation panel is displayed on pdp page
    Examples:
      |root        |
      |domestic    |
      |registry |
      |iship    |

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order with a member product as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order with a member product as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order with a member product as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order with a master product as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page with a member product as a signed in user with dca cookie as RTP
    Given I visit the web site as a registered user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "member_product and available" product to my bag
    Then I checkout until I reach the order review page as an "signed in" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page with a master product as a signed in user with dca cookie as RTP
    Given I visit the web site as a registered user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    And I add an "master_product and available" product to my bag
    Then I checkout until I reach the order review page as an "signed in" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page with a member product as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "member_product and available" product to my bag
    Then I checkout until I reach the order review page as an "signed in" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page with a master product as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    And I add an "master_product and available" product to my bag
    Then I checkout until I reach the order review page as an "signed in" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario Outline: Verify user able to add a product in bag as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in <root> mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add "available and with_customer_ratings" product from <root> mode to my bag and select checkout
    And I remove bonus item product from shopping bag
    Then I should see only normal item is present in shopping bag page
    And I verify that page is served from "RTP" DC
    Examples:
      | root     |
      | domestic |
      | registry |
      | iship    |

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario Outline: Verify user able to add a product in bag as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I add "available and with_customer_ratings" product from <root> mode to my bag and select checkout
    And I remove bonus item product from shopping bag
    Then I should see only normal item is present in shopping bag page
    Examples:
      | root     |
      | domestic |
      | registry |
      | iship    |

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario Outline: Verify user able to add a product in bag as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in <root> mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add "available and with_customer_ratings" product from <root> mode to my bag and select checkout
    And I remove bonus item product from shopping bag
    Then I should see only normal item is present in shopping bag page
    And I verify that page is served from "DAL" DC
    Examples:
      | root     |
      | domestic |
      | registry |
      | iship    |

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify user RVI panel is displayed without dca cookie
    Given I visit the web site as a guest user
    When I navigate to "member_product and available and with_color" product PDP page
    And I navigate to "with_customer_ratings and available" product PDP page
    Then I verify that RVI panel is displayed on pdp page

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify user RVI panel is displayed with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to "member_product and available and with_color" product PDP page
    And I navigate to "with_customer_ratings and available" product PDP page
    Then I verify that RVI panel is displayed on pdp page
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority3
  Scenario: Verify user RVI panel is displayed with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to "member_product and available and with_color" product PDP page
    And I navigate to "with_customer_ratings and available" product PDP page
    Then I verify that RVI panel is displayed on pdp page
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority2
  Scenario: Place order in Iship Mode without dca cookie
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I add a "iship_eligible and available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "iship" user from "India"
    Then I should see order number on order receipt page

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verifying the Merge bag scenario with dca cookie as DAL
    Given I visit the web site as a registered user with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "available and member_product" product to my bag
    And I sign out from my current profile
    And I clear all the cookies
    And I visit the web site as a guest user in domestic mode with dca cookie as DAL
    And I add an "available and member_product" product to my bag
    And I sign in during checkout with existing username and password
    Then I verify the functionality of merge bag
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verifying the Merge bag scenario with dca cookie as RTP
    Given I visit the web site as a registered user with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "available and member_product" product to my bag
    And I sign out from my current profile
    And I clear all the cookies
    And I visit the web site as a guest user in domestic mode with dca cookie as RTP
    And I add an "available and member_product" product to my bag
    And I sign in during checkout with existing username and password
    Then I verify the functionality of merge bag
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario: Verifying the Merge bag scenario without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I add an "available and member_product" product to my bag
    And I sign out from my current profile
    And I clear all the cookies
    And I visit the web site as a guest user
    And I add an "available and member_product" product to my bag
    And I sign in during checkout with existing username and password
    Then I verify the functionality of merge bag

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order with a member product as a signed in user with dca cookie as RTP
    Given I visit the web site as a registered user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order with a master product as a signed in user with dca cookie as RTP
    Given I visit the web site as a registered user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order with a master product as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority1
  Scenario: Verify order review page with a member product as a signed in user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I add an "member_product and available" product to my bag
    Then I checkout until I reach the order review page as an "signed in" user

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario: Verify order review page with a master product as a signed in user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I add an "master_product and available" product to my bag
    Then I checkout until I reach the order review page as an "signed in" user

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority2
  Scenario: Place order with a master product as a signed in user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I add an "master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify GVR functionality with DCA cookie as DAL
    Given I visit the web site as a registry user with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    And I sign out from my current profile
    And I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    When I search for an existing couple's registry
    Then I should see couple's registry is displayed
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify GVR functionality with DCA cookie as RTP
    Given I visit the web site as a registry user with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    And I sign out from my current profile
    And I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    When I search for an existing couple's registry
    Then I should see couple's registry is displayed
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify GVR functionality without dca cookie
    Given I visit the web site as a registry user without dca cookie
    And I sign out from my current profile
    And I navigate to registry home page
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    When I search for an existing couple's registry
    Then I should see couple's registry is displayed

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario Outline: Verify user able to add a product in bag as a registered user with dca cookie as DAL
    Given I visit the web site as a registered user in <root> mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add "member_product and available" product from <root> mode to my bag and select checkout
    And I remove bonus item product from shopping bag
    Then I should see normal shipping in order summary on shopping bag page
    And I verify that page is served from "DAL" DC
    Examples:
      | root     |
      | domestic |
      | registry |

  @use_msh_smoke @use_prod @dca_rtp @msh_priority1
  Scenario Outline: Verify user able to add a product in bag as a registered user with dca cookie as RTP
    Given I visit the web site as a registered user in <root> mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add "member_product and available" product from <root> mode to my bag and select checkout
    And I remove bonus item product from shopping bag
    Then I should see normal shipping in order summary on shopping bag page
    And I verify that page is served from "RTP" DC
    Examples:
      | root     |
      | domestic |
      | registry |

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority2
  Scenario Outline: Verify user able to add a product in bag as a registered user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I add "member_product and available" product from <root> mode to my bag and select checkout
    And I remove bonus item product from shopping bag
    Then I should see normal shipping in order summary on shopping bag page
    Examples:
      | root     |
      | domestic |
      | registry |

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Verify user able to add a e-gift card product in bag as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "guest" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Verify user able to add a e-gift card product in bag as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "guest" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify user able to add a e-gift card product in bag as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "guest" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Verify user able to add a e-gift card product in bag as a registered user with dca cookie as RTP
    Given I visit the web site as a registered user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "signed in" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Verify user able to add a e-gift card product in bag as a registered user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "signed in" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page as a registrant after adding registry item from BVR page with dca cookie as DAL
    Given I visit the web site as a registry user with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add "registrable and available" product to my bag from BVR page
    Then I checkout until I reach the order review page as an "signed in" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page as a registrant after adding registry item from BVR page with dca cookie as RTP
    Given I visit the web site as a registry user with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add "registrable and available" product to my bag from BVR page
    Then I checkout until I reach the order review page as an "signed in" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify order review page as a registrant after adding registry item from BVR page without dca cookie
    Given I visit the web site as a registry user without dca cookie
    When I add "registrable and available" product to my bag from BVR page
    Then I checkout until I reach the order review page as an "signed in" user

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario Outline: Verify basic functionality of browse page with DCA cookie as RTP
    Given I visit the web site as a guest user
    When I navigate to browse page in <root> mode with DCA cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    And I verify that page is served from "RTP" DC
    Examples:
      |root        |
      |domestic |
      |registry |
      |iship   |

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario Outline: Verify basic functionality of browse page with DCA cookie as DAL
    Given I visit the web site as a guest user
    When I navigate to browse page in <root> mode with DCA cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    And I verify that page is served from "DAL" DC
    Examples:
      |root        |
      |domestic |
      |registry |
      |iship   |

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario Outline: Verify basic functionality of browse page without dca cookie
    Given I visit the web site as a guest user
    When I navigate to browse page in <root> mode without dca cookie
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    Examples:
      |root        |
      |domestic |
      |registry |
      |iship   |

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario Outline: Verify basic functionality of search results page with DCA cookie as DAL
    Given I visit the web site as a guest user
    When I navigate to search results page in <root> mode with DCA cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    And I verify that page is served from "DAL" DC
    Examples:
      |root        |
      |domestic |
      |registry |
      |iship   |

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario Outline: Verify basic functionality of search results page with DCA cookie as RTP
    Given I visit the web site as a guest user
    When I navigate to search results page in <root> mode with DCA cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    And I verify that page is served from "RTP" DC
    Examples:
      |root        |
      |domestic |
      |registry |
      |iship   |

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario Outline: Verify basic functionality of search results page without dca cookie
    Given I visit the web site as a guest user
    When I navigate to search results page in <root> mode without dca cookie
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    Examples:
      |root        |
      |domestic |
      |registry |
      |iship   |

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Place order with a member product as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I add an "member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Place order with a master product as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I add an "master_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page with dca cookie as DAL
    Given I visit the web site as a registry user with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add "registrable and orderable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page with dca cookie as RTP
    Given I visit the web site as a registry user with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add "registrable and orderable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order confirmation page as an "guest" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Place order with a member product as a signed in user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I add an "member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify basic functionality of dynamic landing page with DCA cookie as DAL
    Given I visit the web site as a guest user
    When I navigate to dynamic landing page in domestic mode with DCA cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify basic functionality of dynamic landing page with DCA cookie as RTP
    Given I visit the web site as a guest user
    When I navigate to dynamic landing page in domestic mode with DCA cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify basic functionality of dynamic landing page without dca cookie
    Given I visit the web site as a guest user
    When I navigate to dynamic landing page in domestic mode without dca cookie
    When I select any random facet in facet panel
    Then I verify products are filtered per the selected facet
    When I select "Price (high-low)" in sort by drop down
    Then I verify that product display order modified
    And I should be able to navigate using pagination functionality
    When I select a quick view of random product
    Then I should see quick peek is displayed

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order with bops item as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order with bops item as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Place order with bops item as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order with bops item as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    And I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order with bops item as a signed in user with dca cookie as RTP
    Given I visit the web site as a registered user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page with bops item as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page with bops item as a guest user with dca cookie as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify order review page with bops item as a guest user without dca cookie
    Given I visit the web site as a guest user
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page with bops item as a signed in user with dca cookie as DAL
    Given I visit the web site as a registered user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    And I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify order review page with bops item as a signed in user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops and signed in" user
    Then I should see bops shipping in order summary section

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page with bops item as a signed in user with dca cookie as RTP
    Given I visit the web site as a registered user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @dca_dal @msh_priority1
  Scenario: Verify order review page as a guest user after adding registry item from GVR(guest registry) page with dca cookie as DAL
    Given I visit the web site as a registry user with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    And I add "registrable and available" product to my bag from BVR page
    And I navigate to shopping bag page
    And I refresh current page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order review page as an "guest" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_prod @dca_rtp @msh_priority2
  Scenario: Verify order review page as a guest user after adding registry item from GVR(guest registry) page with dca cookie as RTP
    Given I visit the web site as a registry user with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    And I add "registrable and available" product to my bag from BVR page
    And I navigate to shopping bag page
    And I refresh current page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order review page as an "guest" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_prod @without_dca_cookie @msh_priority3
  Scenario: Verify order review page as a guest user after adding registry item from GVR(guest registry) page without dca cookie
    Given I visit the web site as a registry user without dca cookie
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    And I add "registrable and available" product to my bag from BVR page
    And I navigate to shopping bag page
    And I refresh current page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order review page as an "guest" user

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Verify user able to add a e-gift card product in bag as a registered user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I navigate to "electronic_gift_card and prod_available" product PDP page
    And I add EGC item with "100" and "test@test.com" and continue checkout till shopping bag page
    Then I should see normal shipping in order summary on shopping bag page
    When I checkout until I reach the order review page as a "signed in" user
    Then I should see e-gift message in order summary section
    When I place an order
    Then I should see e-gift message section on order confirmation page

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Place order as a guest user after adding registry item from GVR(guest registry) page without dca cookie
    Given I visit the web site as a registry user without dca cookie
    When I add "registrable and orderable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    Then I checkout until I reach the order confirmation page as an "guest" user

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Place order as a registrant after adding registry item from BVR page without dca cookie
    Given I visit the web site as a registry user without dca cookie
    When I add "registrable and orderable" product to my bag from BVR page
    Then I checkout until I reach the order confirmation page as an "signed in" user

  @use_msh_smoke @use_order @dca_dal @msh_priority1
  Scenario: Place order as a registrant after adding registry item from BVR page with dca cookie as DAL
    Given I visit the web site as a registry user with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I add "registrable and orderable" product to my bag from BVR page
    Then I checkout until I reach the order confirmation page as an "signed in" user
    And I verify that page is served from "DAL" DC

  @use_msh_smoke @use_order @dca_rtp @msh_priority2
  Scenario: Place order as a registrant after adding registry item from BVR page with dca cookie as RTP
    Given I visit the web site as a registry user with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I add "registrable and orderable" product to my bag from BVR page
    Then I checkout until I reach the order confirmation page as an "signed in" user
    And I verify that page is served from "RTP" DC

  @use_msh_smoke @use_order @without_dca_cookie @msh_priority3
  Scenario: Place order with bops item as a signed in user without dca cookie
    Given I visit the web site as a registered user without dca cookie
    When I navigate to shopping bag page
    And I remove all items in shopping bag
    When I add an "available_bops and available and registrable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping & payment page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops and signed in" user
    Then I should see bops shipping in order summary section
    When I place an order
    Then I should see bops shipping section on order confirmation page

