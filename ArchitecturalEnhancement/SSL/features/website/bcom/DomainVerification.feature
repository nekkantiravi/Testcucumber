Feature: Verify the domains of network calls in all Navapp pages

    @bcom_ssl
    Scenario Outline: Verify the domains are SSL certified in network calls in home page
        Given I visit the web site as a guest user in "<mode_name>" mode
        Then I verify the domain call is in http/https
            |assets|
        Examples:
            |mode_name|
            |domestic|
            |iship|

    @bcom_ssl
    Scenario Outline: Verify the domains are SSL certified in network calls search results page
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



    @bcom_ssl
    Scenario Outline: Verify the domains are SSL certified in network calls zero search results page
        Given I visit the web site as a guest user in "<mode_name>" mode
        When I search for "abcd"
        Then I should see the "search zero results" page
        And I verify the domain call is in http/https
            |assets|
            |images|
        Examples:
            |mode_name|
            |domestic|
            |iship|
            |registry|


    @bcom_ssl
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


    @bcom_ssl
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
            |registry|

    @bcom_ssl
    Scenario Outline: Verify the domains are SSL certified in network calls in brand index page
        Given I visit the web site as a guest user in "<mode_name>" mode
        When I navigate to brand index page in "<mode_name>" mode
        Then I should be navigated to brand index page
        And I verify the domain call is in http/https
            |assets|
        Examples:
            |mode_name|
            |domestic|
            |iship|
            |registry|

    @bcom_ssl
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

    @bcom_ssl
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

    @bcom_ssl
    Scenario: Verify the domains are SSL certified in network calls in Store events page
        Given I visit the web site as a guest user
        When I click on "stores" link in the header
        Then I verify the domain call is in http/https
            |assets|

    @bcom_ssl
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


#-----------------------------------------------------------------------------------------------------------
#
#    ShopApp feature
#
#-------------------------------------------------------------------------------------------------------------

    @bcom_ssl
    Scenario: Verify the domains are SSL certified in network calls in LoyalList-Top of the list page
        Given I visit the web site as a guest user
        When I navigate to my account page
        Then I click on "member benefits" link in my account page
        Then I navigate to top of the list page
        And I verify the domain call is in http/https
            |assets|
            |images|




#-----------------------------------------------------------------------------------------------------------------------------------------
#
#   Bright Tags feature
#-----------------------------------------------------------------------------------------------------------------------------------------

    @bcom_ssl_bright_tag
    Scenario: Verify the Bright tags are SSL certified in network calls in in home page
        Given I visit the web site as a guest user
        Then I verify the bright tag call is in https
            |https://20520087p.rfihub.com/ca.gif?rb=2635&ca=20520087&ra=%n|
            |https://static.criteo.net/js/ld/ld.js|
            |https://d.agkn.com/pixel/2367/|
            |https://b.collective-media.net/seg/cm/w1jj|


    @bcom_ssl_bright_tag
    Scenario Outline: Verify the bright tags are SSL certified in network calls in all designers page
        Given I visit the web site as a guest user in "<mode_name>" mode
        When I navigate to brand index page in "<mode_name>" mode
        Then I should be navigated to brand index page
        And I verify the bright tag call is in https
            |https://20520087p.rfihub.com/ca.gif?rb=2635&ca=20520087&ra=%n|
            |https://static.criteo.net/js/ld/ld.js|
            |https://d.agkn.com/pixel/2367/|
            |https://d.agkn.com/pixel/2367/|
        Examples:
            |mode_name|
            |domestic|
            |iship|


    @bcom_ssl_bright_tag
    Scenario Outline: Verify the bright tags are SSL certified in network calls in cat splash page
        Given I visit the web site as a guest user in "<mode_name>" mode
        When I navigate to random category splash page
        Then I should see the "category splash" page
        And I verify the bright tag call is in https
            |https://20520087p.rfihub.com/ca.gif?rb=2635&ca=20520087&ra=%n|
            |https://static.criteo.net/js/ld/ld.js|
            |https://d.agkn.com/pixel/2367/|
        Examples:
            |mode_name|
            |domestic|
            |iship|


    @bcom_ssl_bright_tag
    Scenario Outline: Verify the bright tags are SSL certified in network calls in Shopping bag page
        Given I visit the web site as a guest user in "<mode_name>" mode
        When I add a random product to bag
        And I navigate to shopping bag page from add to bag page
        Then I should see the "shopping bag" page
        And I verify the bright tag call is in https
            |https://static.criteo.net/js/ld/ld.js|
            |https://b.collective-media.net/seg/cm/w1jj|
        Examples:
            |mode_name|
            |domestic|
            |iship|


    @bcom_ssl_bright_tag
    Scenario Outline: Verify the bright tags are SSL certified in network calls in PDP
        Given I visit the web site as a guest user in "<mode_name>" mode
        When I navigate to a random product
        Then I should be redirected to PDP page
        And I verify the bright tag call is in https
            |https://static.criteo.net/js/ld/ld.js|
        Examples:
            |mode_name|
            |domestic|
            |iship|


    @bcom_ssl_bright_tag
    Scenario: Verify the bright tags are SSL certified in network calls in registry cat splash page
        Given I visit the web site as a guest user
        #When I navigate to the "Benefits & Perks" browse page under "The Registry"
        And I mouse over "The Registry" category from top navigation
        And I select "Benefits & Perks" subcategory from flyout menu
        Then I should see the "registry_benefits" page
        Then I verify the bright tag call is in https
            |https://www.googleadservices.com/pagead/conversion/1033743585/|
            |https://www.googleadservices.com/pagead/conversion.js|

