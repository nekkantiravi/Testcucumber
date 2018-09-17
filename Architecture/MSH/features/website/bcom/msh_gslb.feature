Feature: Verify MSH GSLB smoke functionality

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify user able to create profile and sign in on both DAL & RTP DC's
    Given I visit the web site as a guest user
    And I set dca cookie value to <site_name1>
    When I create a new profile
    And I navigate to My Wallet page from My Account page
    And I verify that dca cookie is <site_name1>
    And I close and reopen the browser
    And I set dca cookie value to <site_name2>
    When I sign in with previously credentials generated
    Then I should be redirected to home page with SIGN OUT link on header
    And I verify that dca cookie is <site_name2>
    Examples:
      | site_name1  |  site_name2 |
      | DAL         |     RTP     |
      | RTP         |     DAL     |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
    Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL for find registry
    Given I visit the web site as a guest user
   And I set dca cookie value to <site_value>
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "GETTING STARTED" category from top navigation
    When I select "Find a Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrysearch" request
    And I verify the page served from "<site_value>" data center
    Examples:
      |site_value|
      |DAL |
      |RTP |

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for find registry when RTP DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrysearch" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for find registry when DAL DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "GETTING STARTED" category from top navigation
    And I select "Find a Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrysearch" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL/RTP for the merge bag
    Given I visit the web site as a guest user
    And I set dca cookie value to <site_value>
    When I create a new profile
    Then I verify the page served from "<site_value>" data center
    When I add a "available and orderable and with_customer_ratings" product to my bag
    And I close and reopen the browser
    And I set dca cookie value to <site_value>
    And I add a "available and orderable and with_customer_ratings" product to my bag
    And I sign in during checkout
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/checkout/merge.ognc" request
    Then I verify the page served from "<site_value>" data center
    And I verify the functionality of merge bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/merge" request
    And I verify the page served from "<site_value>" data center
    Examples:
      | site_value |
      | DAL        |
      | RTP        |

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as  DAL when DAL DC is down for the merge bag
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    When I create a new profile
    And I verify the page served from "RTP" data center
    When I add a "available and orderable and with_customer_ratings" product to my bag
    And I close and reopen the browser
    And I set dca cookie value to DAL
    And I add a "available and orderable and with_customer_ratings" product to my bag
    When I sign in during checkout
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/checkout/merge.ognc" request
    And I verify the page served from "RTP" data center
    And I verify the functionality of merge bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/merge" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as  RTP when RTP DC is down for the merge bag
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    When I create a new profile
    Then I verify the page served from "DAL" data center
    When I add a "available and orderable and with_customer_ratings" product to my bag
    And I close and reopen the browser
    And I set dca cookie value to RTP
    And I add a "available and orderable and with_customer_ratings" product to my bag
    When I sign in during checkout
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/checkout/merge.ognc" request
    Then I verify the page served from "DAL" data center
    And I verify the functionality of merge bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/merge" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for all My account Links
    Given I visit the web site as a registered user
    And I set dca cookie value to RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account" request
    And I verify the page served from "RTP" data center
    When I select "Order Status & History" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service" request
    And I verify the page served from "RTP" data center
    When I select "Furniture Mattress Status" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service/furniture/index.ognc" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account/furniture" request
    And I verify the page served from "RTP" data center
    When I select "Cardholder Benefits" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/creditservice" request
    And I verify the page served from "RTP" data center
    When I select "My Points" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/loyallist" request
    And I verify the page served from "RTP" data center
    When I select "My Wish List" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wishlist" request
    And I verify the page served from "RTP" data center

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for all My account Links
    Given I visit the web site as a registered user
    And I set dca cookie value to DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account" request
    And I verify the page served from "DAL" data center
    When I select "Order Status & History" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service" request
    And I verify the page served from "DAL" data center
    When I select "Furniture Mattress Status" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service/furniture/index.ognc" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account/furniture" request
    And I verify the page served from "DAL" data center
    When I select "Cardholder Benefits" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/creditservice" request
    And I verify the page served from "DAL" data center
    When I select "My Points" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/loyallist" request
    And I verify the page served from "DAL" data center
    When I select "My Wish List" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wishlist" request
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages for all My account Links when DAL DC is down
    Given I visit the web site as a registered user
    And I set dca cookie value to DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account" request
    And I verify the page served from "RTP" data center
    When I select "Order Status & History" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service" request
    And I verify the page served from "RTP" data center
    When I select "Furniture Mattress Status" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service/furniture/index.ognc" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account/furniture" request
    And I verify the page served from "RTP" data center
    When I select "Cardholder Benefits" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/creditservice" request
    And I verify the page served from "RTP" data center
    When I select "My Points" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/loyallist" request
    And I verify the page served from "RTP" data center
    When I select "My Wish List" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wishlist" request
    And I verify the page served from "RTP" data center

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages for all My account Links when RTP DC is down
    Given I visit the web site as a registered user
    And I set dca cookie value to RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account" request
    And I verify the page served from "DAL" data center
    When I select "Order Status & History" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service" request
    And I verify the page served from "DAL" data center
    When I select "Furniture Mattress Status" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/service/furniture/index.ognc" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/account/furniture" request
    And I verify the page served from "DAL" data center
    When I select "Cardholder Benefits" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/creditservice" request
    And I verify the page served from "DAL" data center
    When I select "My Points" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/loyallist" request
    And I verify the page served from "DAL" data center
    When I select "My Wish List" link from MyAccount page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wishlist" request
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario Outline: Verify the page served from akamai for cached pages for browse paths when RTP DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    When I navigate to <page> page in "domestic" mode
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    And I verify the page served from "DAL" data center
    Examples:
      | page            |   path         |
      | browse          |   /shop/       |
      | search results  |   /shop/search |
      | dynamic landing |   /buy         |

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario Outline: Verify the page served from akamai for cached pages for browse paths when DAL DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    When I navigate to <page> page in "domestic" mode
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    And I verify the page served from "RTP" data center
    Examples:
      | page            |   path         |
      | browse          |   /shop/       |
      | search results  |   /shop/search |
      | dynamic landing |   /buy         |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline:  Verify the page served from akamai for cached pages when DCA cookies value as RTP/DAL for Search URIs
    Given I visit the web site as a guest user
    And I set dca cookie value to <site_value>
    When I navigate to "SALE" category page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/sale" request
    And I verify the page served from "<site_value>" data center
    When I navigate to international context page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/international/context" request
    And I verify the page served from "<site_value>" data center
    When I navigate to the "ways to shop" page from footer
    Then I verify that the X-Cache key value as "TCP_MISS" for "/ways-to-shop/index.jsp" request
    Examples:
      | site_value |
      | DAL        |
      | RTP        |

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario:  Verify the page served from akamai for cached pages for Search URIs when RTP DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    When I navigate to "SALE" category page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/sale" request
    And I verify the page served from "DAL" data center
    When I navigate to international context page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/international/context" request
    And I verify the page served from "DAL" data center
    When I navigate to the "ways to shop" page from footer
    Then I verify that the X-Cache key value as "TCP_MISS" for "/ways-to-shop/index.jsp" request

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario:  Verify the page served from akamai for cached pages for Search URIs when DAL DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    When I navigate to "SALE" category page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/sale" request
    And I verify the page served from "RTP" data center
    When I navigate to international context page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/international/context" request
    And I verify the page served from "RTP" data center
    When I navigate to the "ways to shop" page from footer
    Then I verify that the X-Cache key value as "TCP_MISS" for "/ways-to-shop/index.jsp" request

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages for both DAL & RTP DC's
    Given I visit the web site as a guest user in "registry" mode
    And I select "<link>" in header
    And I set dca cookie value to <site_value>
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "<site_value>" DC
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | link                 | path                               | site_value|
      | registry_PDP         | /shop/registry/wedding/product     | RTP       |
      | registry catsplash   | /shop/wedding-registry             | RTP       |
      | registry_PDP         | /shop/registry/wedding/product     | DAL       |
      | registry catsplash   | /shop/wedding-registry             | DAL       |

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as RTP when DAL DC is down
    Given I visit the web site as a guest user in "registry" mode
    And I select "<link>" in header
    And I set dca cookie value to DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "RTP" DC
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | link                 | path                               |
      | registry_PDP         | /shop/registry/wedding/product     |
      | registry catsplash   | /shop/wedding-registry             |

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL when RTP DC is down
    Given I visit the web site as a guest user in "registry" mode
    And I select "<link>" in header
    And I set dca cookie value to RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "DAL" DC
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | link                 | path                               |
      | registry_PDP         | /shop/registry/wedding/product     |
      | registry catsplash   | /shop/wedding-registry             |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for trans paths
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    And I verify that page is served from "DAL" DC
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "GETTING STARTED" category from top navigation
    When I select "Registry Manager" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registrysignin" request
    And I verify that page is served from "DAL" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for trans paths
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    And I navigate to registry home page
    And I verify that page is served from "RTP" DC
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "GETTING STARTED" category from top navigation
    When I select "Registry Manager" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registrysignin" request
    And I verify that page is served from "RTP" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages for trans paths when RTP DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    And I navigate to registry home page
    And I verify that page is served from "DAL" DC
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "GETTING STARTED" category from top navigation
    When I select "Registry Manager" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registrysignin" request
    And I verify that page is served from "DAL" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages for trans paths when DAL DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    And I verify that page is served from "RTP" DC
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "GETTING STARTED" category from top navigation
    When I select "Registry Manager" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registrysignin" request
    And I verify that page is served from "RTP" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "RTP" DC
	
	@project_MSH_Trans @domain_foundation @prod @both_DC_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for Add to Bag Request
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    When I hover over the quick bag
    Then I should see quick bag overlay
    And I should see "Your brown bag is empty." and X button is displayed in quick bag
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/view" request
    And I verify that page is served from "RTP" DC
    When I navigate to pdp page in domestic mode with DCA cookie as RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I navigate to shopping bag page from add to bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "RTP" DC
    When I click on estimated Shipping link from the Order Summary section of shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/shippingfees" request
    And I verify that page is served from "RTP" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @both_DC_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for Add to Bag Request
    Given I visit the web site as a guest user
	And I set dca cookie value to DAL
    When I hover over the quick bag
    Then I should see quick bag overlay
    And I should see "Your brown bag is empty." and X button is displayed in quick bag
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/view" request
    And I verify that page is served from "DAL" DC
    When I navigate to pdp page in domestic mode with DCA cookie as DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I navigate to shopping bag page from add to bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "DAL" DC
    When I click on estimated Shipping link from the Order Summary section of shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/shippingfees" request
    And I verify that page is served from "DAL" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for Add to Bag Request when DAL DC is down
    Given I visit the web site as a guest user
	And I set dca cookie value to DAL
    When I hover over the quick bag
    Then I should see quick bag overlay
    And I should see "Your brown bag is empty." and X button is displayed in quick bag
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/view" request
    And I verify that page is served from "RTP" DC
    When I navigate to pdp page in domestic mode with DCA cookie as DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I navigate to shopping bag page from add to bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "RTP" DC
    When I click on estimated Shipping link from the Order Summary section of shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/shippingfees" request
    And I verify that page is served from "RTP" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for Add to Bag Request when RTP DC is down
    Given I visit the web site as a guest user
	And I set dca cookie value to RTP
    When I hover over the quick bag
    Then I should see quick bag overlay
    And I should see "Your brown bag is empty." and X button is displayed in quick bag
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/view" request
    And I verify that page is served from "DAL" DC
    When I navigate to pdp page in domestic mode with DCA cookie as RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I navigate to shopping bag page from add to bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "DAL" DC
    When I click on estimated Shipping link from the Order Summary section of shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/shippingfees" request
    And I verify that page is served from "DAL" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_DC_up
  Scenario Outline: Verify the page served from akamai for trans URIs when both DC up
    Given I visit the web site as a guest user
    And I set dca cookie value to <site_value>
    When I navigate Beauty catsplash page and click on freeshipping image
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/popup?" request
    And I verify the page served from "<site_value>" data center
    When I navigate shopping bag page page and click on bonus offer product
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/gift" request
    And I verify the page served from "<site_value>" data center
    When I navigate PDP page and click on email icon
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/email" request
    And I verify the page served from "<site_value>" data center
    When I navigate recently viewed section
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/recentlyViewed" request
    And I verify the page served from "<site_value>" data center
    Examples:
      | site_value |
      | DAL        |
      | RTP        |

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for trans URIs when RTP DC is Down and DCA cookie as RTP
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    When I navigate Beauty catsplash page and click on freeshipping image
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/popup?" request
    And I verify the page served from "DAL" data center
    When I navigate shopping bag page page and click on bonus offer product
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/gift" request
    And I verify the page served from "DAL" data center
    When I navigate PDP page and click on email icon
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/email" request
    And I verify the page served from "DAL" data center
    When I navigate recently viewed section
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/recentlyViewed" request
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for trans URIs when DAL DC is Down and DCA cookie as DAL
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    When I navigate Beauty catsplash page and click on freeshipping image
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/popup?" request
    And I verify the page served from "RTP" data center
    When I navigate shopping bag page page and click on bonus offer product
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/gift" request
    And I verify the page served from "RTP" data center
    When I navigate PDP page and click on email icon
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/email" request
    And I verify the page served from "RTP" data center
    When I navigate recently viewed section
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/catalog/product/recentlyViewed" request
    And I verify the page served from "RTP" data center

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages for browse paths when DCA cookies value as RTP
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    When I navigate to <page> page in "domestic" mode
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    And I verify the page served from "RTP" data center
    Examples:
      | page            |   path         |
      | browse          |   /shop/       |
      | search results  |   /shop/search |
      | dynamic landing |   /buy         |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages for browse paths when DCA cookies value as DAL
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    When I navigate to <page> page in "domestic" mode
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    And I verify the page served from "DAL" data center
    Examples:
      | page            |   path         |
      | browse          |   /shop/       |
      | search results  |   /shop/search |
      | dynamic landing |   /buy         |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify user able to create Registry profile and sign in on both DAL & RTP DC's
    Given I visit the web site as a registry user
    And I set dca cookie value to DAL
    And I should be navigated to the registry manager page
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrymanager" request
    When I close and reopen the browser
    And I set dca cookie value to RTP
    When I navigate to registry home page
    And I login for the existing registry
    And I should be navigated to the registry manager page
    And I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrymanager" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL/RTP for home page from logo
    Given I visit the web site as a guest user
    And I set dca cookie value to <site_value>
    Then verify that Bloomingdales logo takes to Home Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index?" request
    And I verify the page served from "<site_value>" data center
    Examples:
      |site_value|
      |DAL|
      |RTP|

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL and DAL DC is down for home page from logo
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    Then verify that Bloomingdales logo takes to Home Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index?" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP and RTP DC is down for home page from logo
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    Then verify that Bloomingdales logo takes to Home Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index?" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL/RTP for Edit Registry profile
    Given I visit the web site as a guest user
    When I set dca cookie value to <site_value>
    And I navigate to registry home page
    When I select "Create Your Registry"
    When I start to create a new registry from registry sign in page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registrycreateaccount" request
    When I create a new registry
    When I should land on Registry Manager
    When I click on edit profile link on registry manager page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registryeditaccount" request
    And I update email with already exist mail Id
    Then click on save changes button
    Then I sign out from my current profile
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index.ognc" request
    And I verify the page served from "<site_value>" data center
    Examples:
      | site_value |
      | RTP  |
      | DAL  |

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for Edit Registry profile when DAL DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    When I select "Create Your Registry"
    When I start to create a new registry from registry sign in page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registrycreateaccount" request
    When I create a new registry
    When I should land on Registry Manager
    When I click on edit profile link on registry manager page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registryeditaccount" request
    And I update email with already exist mail Id
    Then click on save changes button
    Then I sign out from my current profile
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index.ognc" request
    And I verify the page served from "RTP" data center

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for Edit Registry profile when RTP DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    And I navigate to registry home page
    When I select "Create Your Registry"
    When I start to create a new registry from registry sign in page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registrycreateaccount" request
    When I create a new registry
    When I should land on Registry Manager
    When I click on edit profile link on registry manager page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/registryeditaccount" request
    And I update email with already exist mail Id
    Then click on save changes button
    Then I sign out from my current profile
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index.ognc" request
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when both DC up for PriceData and Index URIs
    Given I visit the web site as a guest user
    And I set dca cookie value to <site_value>
    When I navigate directly to /catalog/product/email/index.ognc URI
    Then I verify that the X-Cache key value as "TCP_MISS" for "catalog/product/email/index.ognc" request
    And I verify the page served from "<site_value>" data center
    When I navigate directly to Price Data URI
    Then I verify that the X-Cache key value as "TCP_MISS" for "shop/international/priceData.js" request
    And I verify the page served from "<site_value>" data center
    Examples:
      |site_value|
      |DAL|
      |RTP|

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for Quick bag functionality
    Given I visit the web site as a guest user
    When I navigate to search results page in "domestic" mode
    And I set dca cookie value to DAL
    Then I should be in Search Landing page
    When I select a quick view of random product
    And I add the item to the bag from quick view
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/add" request
    When I close the addtobag dialog
    Then I should navigate to Search Results page
    And I hover over the quick bag
    And I should see the added products in quick bag
    And I update quantity to 2 on shopping bag page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/update" request
    When I remove all items from the shopping bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/remove" request
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for Quick bag functionality
    Given I visit the web site as a guest user
    When I navigate to search results page in "domestic" mode
    And I set dca cookie value to RTP
    Then I should be in Search Landing page
    When I select a quick view of random product
    And I add the item to the bag from quick view
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/add" request
    When I close the addtobag dialog
    Then I should navigate to Search Results page
    And I hover over the quick bag
    And I should see the added products in quick bag
    And I update quantity to 2 on shopping bag page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/update" request
    When I remove all items from the shopping bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/remove" request
    And I verify the page served from "RTP" data center

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP when RTP DC is down for Quick bag functionality
    Given I visit the web site as a guest user
    When I navigate to search results page in "domestic" mode
    And I set dca cookie value to RTP
    Then I should be in Search Landing page
    When I select a quick view of random product
    And I add the item to the bag from quick view
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/add" request
    When I close the addtobag dialog
    Then I should navigate to Search Results page
    And I hover over the quick bag
    And I should see the added products in quick bag
    And I update quantity to 2 on shopping bag page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/update" request
    When I remove all items from the shopping bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/remove" request
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL when DAL DC is down for Quick bag functionality
    Given I visit the web site as a guest user
    When I navigate to search results page in "domestic" mode
    And I set dca cookie value to DAL
    Then I should be in Search Landing page
    When I select a quick view of random product
    And I add the item to the bag from quick view
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/add" request
    When I close the addtobag dialog
    Then I should navigate to Search Results page
    And I hover over the quick bag
    And I should see the added products in quick bag
    And I update quantity to 2 on shopping bag page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/update" request
    When I remove all items from the shopping bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/remove" request
    And I verify the page served from "RTP" data center