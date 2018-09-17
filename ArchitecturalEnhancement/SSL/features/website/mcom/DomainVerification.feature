Feature: Verify the domains of network calls in all Navapp pages

  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in home page
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I verify the domain call is in http/https
      |assets|
    Examples:
      |mode_name|
      |domestic|
      |iship|

  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in search results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I should be in Search Landing page
    And I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |registry|
      |iship|

  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in zero search results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "abcd"
    Then I should see the "search result" page
    And I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|


  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in PDP page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to a random product
    Then I should be redirected to PDP page in "<mode_name>" mode
    And I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|

  @mcom_ssl
  Scenario: Verify the domains are SSL certified in network calls in Store events page
    Given I visit the web site as a guest user
    When I click on "stores" link in the header
    Then I verify the domain call is in http/https
      |assets|

  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|


  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in DLP page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to brand index page in "<mode_name>" mode
    Then I navigate to dynamic landing page
    Then I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|

  @mcom_ssl
  Scenario: Verify the domains are SSL certified in network calls in DLP page for registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "DINING" category page
    Then I select "See All Brands" subcategory from flyout menu
    Then I should be navigated to brand index page
    Then I navigate to dynamic landing page
    Then I verify the domain call is in http/https
      |assets|
      |images|


  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in brand index page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to brand index page in "<mode_name>" mode
    Then I should be navigated to brand index page
    And I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl
  Scenario: Verify the domains are SSL certified in network calls in brand index page for registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "DINING" category page
    Then I select "See All Brands" subcategory from flyout menu
    Then I should be navigated to brand index page
    And I verify the domain call is in http/https
      |assets|
      |images|


  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in cat splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to random category splash page
    Then I should see the "category splash" page
    And I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|

  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in Shopping bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to a random product
    Then I should be redirected to PDP page in "<mode_name>" mode
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    And I navigate to shopping bag page from add to bag page
    Then I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|

  @mcom_ssl
  Scenario Outline: Verify the domains are SSL certified in network calls in Add To bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to a random product
    Then I should be redirected to PDP page in "<mode_name>" mode
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    Then I verify the domain call is in http/https
      |assets|
      |images|
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|

  @mcom_ssl
  Scenario: Verify the domains are SSL certified in network calls in SubSplash page
    Given I visit the web site as a guest user
    When I mouse over "Women" category from top navigation
    And I select "Activewear" subcategory from flyout menu
    Then I should be on "Women" subsplash page
    And I verify the domain call is in http/https
      |assets|
      |images|


#-----------------------------------------------------------------------------------------------------------------------------------------
#
#   Bright Tags feature
#-----------------------------------------------------------------------------------------------------------------------------------------


  @mcom_ssl_bright_tag
  Scenario: Verify the Bright tags are SSL certified in network calls in in home page
    Given I visit the web site as a guest user
    Then I verify the bright tag call is in https
      |https://rc.rlcdn.com/397296.gif|
      |https://macys.ugc.bazaarvoice.com/bvstaging/static/7129aa/bvapi.js|
      |https://www.macys.com/web20/assets/script/macys/international/welcomeMat.js|
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|
      |https://bs.serving-sys.com/BurstingPipe?|
      |https://d.agkn.com/pixel/2616/|


  @mcom_ssl_bright_tag
  Scenario Outline: Verify the bright tags are SSL certified in network calls in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I verify the bright tag call is in https
      |https://rc.rlcdn.com/397296.gif|
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|
      |https://bs.serving-sys.com/BurstingPipe?|
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl_bright_tag
  Scenario Outline: Verify the bright tags are SSL certified in network calls in search results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I search for "nike women"
    Then I should be in Search Landing page
    And I verify the bright tag call is in https
      |https://apis.google.com/js/plusone.js|
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|
      |https://bs.serving-sys.com/BurstingPipe?|
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl_bright_tag
  Scenario Outline: Verify the bright tags are SSL certified in network calls in cat splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to random category splash page
    Then I should see the "category splash" page
    And I verify the bright tag call is in https
      |https://rc.rlcdn.com/397296.gif|
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl_bright_tag
  Scenario Outline: Verify the bright tags are SSL certified in network calls in subsplash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "Women" category from top navigation
    And I select "Activewear" subcategory from flyout menu
    Then I should be on "Women" subsplash page
    And I verify the bright tag call is in https
      |https://rc.rlcdn.com/397296.gif|
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl_bright_tag
  Scenario Outline: Verify the bright tags are SSL certified in network calls in PDP page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to a random product
    Then I should be redirected to PDP page
    And I verify the bright tag call is in https
      |https://rc.rlcdn.com/397296.gif |
      |https://apis.google.com/js/plusone.js|
      |https://www.google.com/adsense/search/ads.js|
      |https://as00.estara.com/as/InitiateCall2.php?accountid=552751413&amp;|
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|
      |https://mcy-uat-cdn.truefitcorp.com/fitrec/mcy/js/fitrec.js|
      |https://mcy-uat-cdn.truefitcorp.com/fitrec/mcy/css/fitrec.css|
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl_bright_tag
  Scenario Outline: Verify the bright tags are SSL certified in network calls in Shopping bag page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I add a random product to bag
    #Then I should be redirected to ATB page
    And I navigate to shopping bag page from add to bag page
    Then I should see the "shopping bag" page
    Then I verify the bright tag call is in https
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl_bright_tag
  Scenario: Verify the bright tags are SSL certified in network calls in Registry PDP page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to a random product
    Then I should be redirected to PDP page
    And I verify the bright tag call is in https
      |https://macys.ugc.bazaarvoice.com/bvstaging/static/7129aa/bvapi.js|
      |https://apis.google.com/js/plusone.js|
      |https://www.google.com/adsense/search/ads.js|
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|


  @mcom_ssl_bright_tag
  Scenario: Verify the bright tags are SSL certified in network calls in Registry category browse page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to browse page in "registry" mode
    Then I verify the bright tag call is in https
      |https://macys.ugc.bazaarvoice.com/bvstaging/static/7129aa/bvapi.js|
      |https://apis.google.com/js/plusone.js|
      |https://www.google.com/adsense/search/ads.js|
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|


  @mcom_ssl_bright_tag
  Scenario: Verify the bright tags are SSL certified in network calls in Registry cat splash page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to random category splash page
    Then I should see the "category splash" page
    And I verify the bright tag call is in https
      |https://macys.ugc.bazaarvoice.com/bvstaging/static/7129aa/bvapi.js|
      |https://apis.google.com/js/plusone.js|
      |https://www.google.com/adsense/search/ads.js|
      |https://ds.serving-sys.com/SemiCachedScripts/ebOneTag.js|
