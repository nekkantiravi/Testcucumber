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
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL for my account Request
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that dca cookie is DAL
    And I select "<link>" in header
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that dca cookie is DAL
    Examples:
      |   link                | path         |
      |   My Lists            |/wishlist     |
      |   My Account          |/account  |
      |   My Order History    |/service      |
      | My Macy's Credit Card |/creditservice|

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

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL for find registry
    Given I visit the web site as a guest user
    And I set dca cookie value to <site_value>
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Find A Couple's Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrysearch" request
    And I verify the page served from "<site_value>" data center
    Examples:
      |site_value|
      |DAL |
      |RTP |

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

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for find registry when DAL DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Find A Couple's Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrysearch" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for find registry when RTP DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Find A Couple's Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrysearch" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP when RTP DC is down and trans paths
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    And I navigate to gift card category splash page
    When I select "Personalized Gift Cards" category from left nav
    Then I verify that the X-Cache key value as "TCP_MISS" for "/catalog/syndicated/cardways/CreateGiftCard.asp" request
    And I verify that dca cookie is RTP
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the Personlaized gift card page served from akamai for cached pages when DCA cookies value as RTP when DAL DC is down and trans paths
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to gift card category splash page
    When I select "Personalized Gift Cards" category from left nav
    Then I verify that the X-Cache key value as "TCP_MISS" for "/catalog/syndicated/cardways/CreateGiftCard.asp" request
    And I verify that dca cookie is DAL
    And I verify the page served from "RTP" data center

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as RTP when DAL DC is down
    Given I visit the web site as a guest user in "registry" mode
    And I select "<link>" in header
    And I set dca cookie value to DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "RTP" DC
    And I set dca cookie value to <site_value>
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | link       		          | path                                 			 | site_value|
      | registry_PDP    		  | /shop/registry/wedding/product       	         | RTP       |
      | registry_catsplash 	      | /shop/wedding-registry         				     | RTP       |

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL when RTP DC is down
    Given I visit the web site as a guest user
    And I select "<link>" in header
    And I set dca cookie value to DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "DAL" DC
    And I set dca cookie value to <site_value>
    And I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | link      		        | path                                 			 | site_value|
      | registry_PDP    		| /shop/registry/wedding/product       			 | DAL       |
      | registry catsplash 	    | /shop/wedding-registry         				 | DAL       |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as RTP for my account Request
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that dca cookie is RTP
    And I select "<link>" in header
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that dca cookie is RTP
    Examples:
      |   link                | path         |
      |   My Lists            |/wishlist     |
      |   My Account          |/account      |
      |   My Order History    |/service      |
      | My Macy's Credit Card |/creditservice|

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Iship - Verify the page served from akamai for cached pages when DCA cookie value as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I navigate to international context page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/international/context" request
	And I change country to "a random country"
    When I go to US site
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index.ognc" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Iship - Verify the page served from akamai for cached pages when DCA cookie value as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I navigate to international context page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/international/context" request
	And I change country to "a random country"
    When I go to US site
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index.ognc" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Macys Sale and Our Store Footer link - Verify the page served from akamai for cached pages when DCA cookie value as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" DC
    When I search for "sale"
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/sale" request
    And I navigate to the "stores" page from footer
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/store" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Macys Sale and Our Store Footer link - Verify the page served from akamai for cached pages when DCA cookie value as RTP
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    Then I verify that page is served from "RTP" DC
    When I search for "sale"
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/sale" request
    And I navigate to the "stores" page from footer
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/store" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookie value as RTP for browse paths
    Given I visit the web site as a guest user
    When I navigate to <page_type> page in domestic mode with DCA cookie as RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "RTP" DC
    When I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | page_type       |       path      |
      | browse          | /shop/          |
      | pdp             | /shop/product/  |
      | search results  | /shop/featured/ |
      | dynamic landing | /shop/featured/ |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookie value as DAL for browse paths
    Given I visit the web site as a guest user
    When I navigate to <page_type> page in domestic mode with DCA cookie as DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "DAL" DC
    When I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | page_type       |       path      |
      | browse          | /shop/          |
      | pdp             | /shop/product/  |
      | search results  | /shop/featured/ |
      | dynamic landing | /shop/featured/ |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Registry - Verify the page served from akamai for cached pages for both DAL & RTP DC's
    Given I visit the web site as a guest user in "registry" mode
    And I set dca cookie value to <site_name>
    When I mouse over "KITCHEN" category from top navigation
    And I select "Cookware & Cookware Sets" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/wedding-registry" request
    And I select a random member product
    Then I should be redirected to PDP page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/registry/wedding/product" request
    Then I verify that page is served from "<site_name>" DC
    Examples:
      | site_name |
      |   DAL     |
      |   RTP     |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for Add to Bag Request
    Given I visit the web site as a guest user
    When I navigate to pdp page in domestic mode with DCA cookie as RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/atbpage" request
    And I verify that page is served from "RTP" DC
    When I navigate to shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "RTP" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for Add to Bag Request
    Given I visit the web site as a guest user
    When I navigate to pdp page in domestic mode with DCA cookie as DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/atbpage" request
    And I verify that page is served from "DAL" DC
    When I navigate to shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "DAL" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for Add to Bag Request when DAL DC is down
    Given I visit the web site as a guest user
    When I navigate to pdp page in domestic mode with DCA cookie as DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/atbpage" request
    And I verify that page is served from "RTP" DC
    When I navigate to shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "RTP" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for Add to Bag Request when RTP DC is down
    Given I visit the web site as a guest user
    When I navigate to pdp page in domestic mode with DCA cookie as RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/product/" request
    When I add an "available and orderable and member_product" product to my bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/atbpage" request
    And I verify that page is served from "DAL" DC
    When I navigate to shopping bag page
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/index.ognc" request
    And I verify that page is served from "DAL" DC
    And I checkout until I reach the order review page as a "guest" user
    And I verify that the X-Cache key value as "TCP_MISS" for "/bag/continuecheckout" request
    And I verify that the X-Cache key value as "TCP_MISS" for "/chkout" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify user able to create Registry profile and sign in on both DAL & RTP DC's
    Given I visit the web site as a registry user with dca cookie as DAL
    And I verify that page is served from "DAL" DC
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
  Scenario: Verify user able to create Registry profile and sign in on both RTP & DAL DC's
    Given I visit the web site as a registry user with dca cookie as RTP
    And I verify that page is served from "RTP" DC
    And I should be navigated to the registry manager page
    Then I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrymanager" request
    When I close and reopen the browser
    And I set dca cookie value to DAL
    When I navigate to registry home page
    And I login for the existing registry
    And I should be navigated to the registry manager page
    And I verify that the X-Cache key value as "TCP_MISS" for "registry/wedding/registrymanager" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP when RTP DC is down for Quick bag functionality
    Given I visit the web site as a guest user
    When I should navigate to Search Results page
    And I set dca cookie value to RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/featured/" request
    When I select a quick view of random product
    And I add the item to the bag from quick view
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/add" request
    When I close the quickview dialog
    Then I should navigate to Search Results page
    And I verify the page served from "DAL" data center
    When I hover over the quick bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/api/shopping-bag/v1/bags" request
    When I checkout on add to bag overlay
    And I should be able to change product quantity on PDP page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/update" request
    When I remove all items from the shopping bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/remove" request
    And I verify that dca cookie is DAL
    And I verify the page served from "DAL" data center

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL when DAL DC is down for Quick bag functionality
    Given I visit the web site as a guest user
    When I should navigate to Search Results page
    And I set dca cookie value to DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/featured/" request
    When I select a quick view of random product
    And I add the item to the bag from quick view
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/add" request
    When I close the quickview dialog
    Then I should navigate to Search Results page
    And I verify the page served from "RTP" data center
    When I hover over the quick bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/api/shopping-bag/v1/bags" request
    When I checkout on add to bag overlay
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/update" request
    When I remove all items from the shopping bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/remove" request
    And I verify that dca cookie is RTP
    And I verify the page served from "RTP" data center

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL/RTP for Quick bag functionality
    Given I visit the web site as a guest user
    When I should navigate to Search Results page
    And I set dca cookie value to <site_value>
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/featured/" request
    When I select a quick view of random product
    And I add the item to the bag from quick view
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/add" request
    When I close the quickview dialog
    Then I should navigate to Search Results page
    And I verify the page served from "<site_value>" data center
    When I hover over the quick bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/api/shopping-bag/v1/bags" request
    When I checkout on add to bag overlay
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/update" request
    When I remove all items from the shopping bag
    Then I verify that the X-Cache key value as "TCP_MISS" for "/bag/remove" request
    And I verify the page served from "<site_value>" data center
    Examples:
      |site_value|
      |DAL|
      |RTP|

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL for my account Request when DAL DC is down
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    And I select "<link>" in header
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that dca cookie is RTP
    Examples:
      |   link                | path         |
      |   My Lists            |/wishlist     |
      |   My Account          |/account      |
      |   My Order History    |/service      |
      | My Macy's Credit Card |/creditservice|

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as RTP for my account Request when RTP DC is down
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    And I select "<link>" in header
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that dca cookie is DAL
    Examples:
      |   link                | path         |
      |   My Lists            |/wishlist     |
      |   My Account          |/account      |
      |   My Order History    |/service      |
      | My Macy's Credit Card |/creditservice|

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Iship - Verify the page served from akamai for cached pages when RTP DC is down
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    When I navigate to international context page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/international/context" request
    And I change country to "a random country"
    When I go to US site
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index.ognc" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Macys Sale and Our Store Footer link - Verify the page served from akamai for cached pages when RTP DC is down
    Given I visit the web site as a guest user in domestic mode with dca cookie as RTP
    When I search for "sale"
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/sale" request
    And I navigate to the "stores" page from footer
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/store" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario Outline: Verify the page served from akamai for cached pages when RTP DC is down
    Given I visit the web site as a guest user
    When I navigate to <page_type> page in domestic mode with DCA cookie as RTP
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "DAL" DC
    When I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | page_type       |       path      |
      | browse          | /shop/          |
      | pdp             | /shop/product/  |
      | search results  | /shop/featured/ |
      | dynamic landing | /shop/featured/ |

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Iship - Verify the page served from akamai for cached pages when DAL DC is down
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I navigate to international context page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/international/context" request
    And I change country to "a random country"
    When I go to US site
    Then I verify that the X-Cache key value as "TCP_MISS" for "/index.ognc" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Macys Sale and Our Store Footer link - Verify the page served from akamai for cached pages when DAL DC is down
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I search for "sale"
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/sale" request
    And I navigate to the "stores" page from footer
    Then I verify that the X-Cache key value as "TCP_MISS" for "/shop/store" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario Outline: Verify the page served from akamai for cached pages when DAL DC is down
    Given I visit the web site as a guest user
    When I navigate to <page_type> page in domestic mode with DCA cookie as DAL
    Then I verify that the X-Cache key value as "TCP_MISS" for "<path>" request
    And I verify that page is served from "RTP" DC
    When I refresh current page
    Then I verify that the X-Cache key value as "TCP_HIT" for "<path>" request
    Examples:
      | page_type       |       path      |
      | browse          | /shop/          |
      | pdp             | /shop/product/  |
      | search results  | /shop/featured/ |
      | dynamic landing | /shop/featured/ |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario Outline: Verify the page served from akamai for cached pages when DCA cookies value as DAL for trans paths
    Given I visit the web site as a guest user in domestic mode with dca cookie as <site_value>
    And I navigate to gift card category splash page
    When I select "Personalized Gift Cards" category from left nav
    Then I verify that the X-Cache key value as "TCP_MISS" for "/catalog/syndicated/cardways/CreateGiftCard.asp" request
    And I verify that page is served from "<site_value>" DC
    Examples:
      |site_value|
      |DAL       |
      |RTP       |

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as DAL for trans paths
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Manage Your Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/regsignin" request
    And I verify that page is served from "DAL" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @both_Dc_up
  Scenario: Verify the page served from akamai for cached pages when DCA cookies value as RTP for trans paths
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Manage Your Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/regsignin" request
    And I verify that page is served from "RTP" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "RTP" DC

  @project_MSH_Trans @domain_foundation @prod @rtp_down
  Scenario: Verify the page served from akamai for cached pages for trans paths when RTP DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to RTP
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Manage Your Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/regsignin" request
    And I verify that page is served from "DAL" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "DAL" DC

  @project_MSH_Trans @domain_foundation @prod @dal_down
  Scenario: Verify the page served from akamai for cached pages for trans paths when DAL DC is down
    Given I visit the web site as a guest user
    And I set dca cookie value to DAL
    And I navigate to registry home page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/wedding-registry" request
    And I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Manage Your Registry" subcategory from flyout menu
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/regsignin" request
    And I verify that page is served from "RTP" DC
    When I navigate to registry claim Page
    Then I verify that the X-Cache key value as "TCP_MISS" for "/registry/wedding/claim" request
    And I verify that page is served from "RTP" DC