Feature: Verify basic Pages being served from DALLAS

  @dsv_dal
  Scenario: HomePage is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I should see dynamic top navigation in "domestic" mode
    Then I should see new header and footer section in "domestic" mode
    Then I verify the display of GNA and GFA

  @dsv_dal
  Scenario: HomePage is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I should see dynamic top navigation in "iship" mode
    Then I should see new header and footer section in "iship" mode
    Then I verify the display of GNA and GFA

  @dsv_dal
  Scenario: HomePage is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I should see dynamic top navigation in "registry" mode
    Then I should see new header and footer section in "registry" mode
    Then I verify the display of GNA and GFA

    # Below scenario is N/A for registry mode
  @dsv_dal
  Scenario Outline: Verify FOBs(4-5) are served from DALLAS in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to "<category>" category page
    Then I should be navigated to "<category>" category page
    And I verify the basic attributes of cat splash page
    And I verify that page is served from "DAL" server

    Examples:
      | category |
      | home     |
      | women    |
      | men      |
      | beauty   |
      | kids     |

  @dsv_dal
  Scenario Outline: Verify FOBs(4-5) are served from DALLAS in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to "<category>" category page
    Then I should be navigated to "<category>" category page
    And I verify the basic attributes of cat splash page
    And I verify that page is served from "DAL" server

    Examples:
      | category |
      | home     |
      | women    |
      | men      |
      | kids     |

  @dsv_dal
  Scenario: Verify browse page Facets in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    And I select any random facet in facet panel and verify the count with filtered products
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify browse page Facets in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    And I select any random facet in facet panel and verify the count with filtered products
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify browse page Facets in iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    And I select any random facet in facet panel and verify the count with filtered products
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Browse PDP page is served from DALLAS in domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I select a random member product
    Then I verify that page is served from "DAL" server
    And I verify all attributes of PDP in "domestic" mode
    And I should see vertical recommendation is displayed on PDP page

  @dsv_dal
  Scenario: Verify Browse PDP page is served from DALLAS in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I select a random member product
    Then I verify that page is served from "DAL" server
    And I verify all attributes of PDP in "iship" mode
    And I should see vertical recommendation is displayed on PDP page

  @dsv_dal
  Scenario: Verify Browse PDP page is served from DALLAS in registry
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to "LUGGAGE" category page
    And I navigate to sub categories from Left hand nav links
    And I select a random member product
    Then I verify that page is served from "DAL" server
    And I verify all attributes of PDP in "registry" mode
    And I should see vertical recommendation is displayed on PDP page

  @dsv_dal
  Scenario: Verify Search Page is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode domestic
    Then I should be in Search Landing page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search Page is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode iship
    Then I should be in Search Landing page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search Page is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode registry
    Then I should be in Search Landing page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search Page is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode domestic
    Then I should be in Search Landing page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search Page is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode iship
    Then I should be in Search Landing page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search Page is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode registry
    Then I should be in Search Landing page
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search- Top Pagination is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode domestic
    Then I should be in Search Landing page
    And I waited for Search Page to load
    Then I navigate to first page from top pagination
    Then I waited for Search Page to load
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search- Top Pagination is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode iship
    Then I should be in Search Landing page
    And I waited for Search Page to load
    Then I navigate to first page from top pagination
    Then I waited for Search Page to load
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search- Top Pagination is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode registry
    Then I should be in Search Landing page
    And I waited for Search Page to load
    Then I navigate to first page from top pagination
    Then I waited for Search Page to load
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search PDP page is served from DALLAS in domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I verify that page is served from "DAL" server
    Then I search a random product for mode domestic
    And I select a random member product
    Then I verify that page is served from "DAL" server
    And I verify all attributes of PDP in "domestic" mode
    And I should see vertical recommendation is displayed on PDP page

  @dsv_dal
  Scenario: Verify Search PDP page is served from DALLAS in iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    When I verify that page is served from "DAL" server
    Then I search a random product for mode iship
    And I select a random member product
    Then I verify that page is served from "DAL" server
    And I verify all attributes of PDP in "iship" mode
    And I should see vertical recommendation is displayed on PDP page

  @dsv_dal
  Scenario: Verify Search PDP page is served from DALLAS in registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    When I verify that page is served from "DAL" server
    Then I search a random product for mode registry
    And I select a random member product
    Then I verify that page is served from "DAL" server
    And I verify all attributes of PDP in "registry" mode
    And I should see vertical recommendation is displayed on PDP page

  @dsv_dal
  Scenario: Verify Search page Facets is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I search a random product for mode domestic
    And I verify that page is served from "DAL" server
    And I select any random facet in facet panel and verify the count with filtered products
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search page Facets is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I search a random product for mode iship
    And I verify that page is served from "DAL" server
    And I select any random facet in facet panel and verify the count with filtered products
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search page Facets is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I search a random product for mode registry
    And I verify that page is served from "DAL" server
    And I select any random facet in facet panel and verify the count with filtered products
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Browse-Top Pagination is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    Then I navigate to first page from top pagination
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Browse-Top Pagination is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    Then I navigate to first page from top pagination
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Browse-Top Pagination is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    Then I navigate to first page from top pagination
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Browse-Bottom Pagination is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    Then I navigate to first page from bottom pagination
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Browse-Bottom Pagination is served from Dallas in Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    Then I navigate to first page from bottom pagination
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Browse-Bottom Pagination is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    And I verify that page is served from "DAL" server
    Then I navigate to first page from bottom pagination
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: To verify Browse Quick view is served from Dallas for Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    Then I verify that page is served from "DAL" server
    When I select quick view of random product for mode
    Then I should see quick peek is displayed
    And I verify basic attributes of quick view dialog in "domestic" mode
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: To verify Browse Quick view is served from Dallas for Iship mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    Then I verify that page is served from "DAL" server
    When I select quick view of random product for mode
    Then I should see quick peek is displayed
    And I verify basic attributes of quick view dialog in "domestic" mode
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: To verify Browse Quick view is served from Dallas for Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I navigate to random category browse page
    Then I verify that page is served from "DAL" server
    When I select quick view of random product for mode
    Then I should see quick peek is displayed
    And I verify basic attributes of quick view dialog in "registry" mode
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: To verify Search Quick view is served from Dallas for Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    When I search a random product for mode domestic
    Then I verify that page is served from "DAL" server
    When I select quick view of random product for mode
    Then I should see quick peek is displayed
    And I verify basic attributes of quick view dialog in "domestic" mode
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: To verify Search Quick view is served from Dallas for Iship mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    When I search a random product for mode iship
    Then I verify that page is served from "DAL" server
    When I select quick view of random product for mode
    Then I should see quick peek is displayed
    And I verify basic attributes of quick view dialog in "iship" mode
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: To verify Search Quick view is served from Dallas for Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    When I search a random product for mode registry
    Then I verify that page is served from "DAL" server
    When I select quick view of random product for mode
    Then I should see quick peek is displayed
    And I verify basic attributes of quick view dialog in "registry" mode
    And I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search-Bottom Pagination is served from Dallas in Domestic mode
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode domestic
    Then I should be in Search Landing page
    And I waited for Search Page to load
    Then I navigate to first page from bottom pagination
    Then I waited for Search Page to load
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search-Bottom Pagination is served from Dallas in Iship  mode
    Given I visit the web site as a guest user in iship mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode iship
    Then I should be in Search Landing page
    And I waited for Search Page to load
    Then I navigate to first page from bottom pagination
    Then I waited for Search Page to load
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify Search-Bottom Pagination is served from Dallas in Registry mode
    Given I visit the web site as a guest user in registry mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    Then I search a random product for mode registry
    Then I should be in Search Landing page
    And I waited for Search Page to load
    Then I navigate to first page from bottom pagination
    Then I waited for Search Page to load
    Then I verify that page is served from "DAL" server

  @dsv_dal
  Scenario: Verify order review page with a member product as a guest user with dca cookie as DAL
    Given I visit the web site as a guest user in domestic mode with dca cookie as DAL
    Then I verify that page is served from "DAL" server
    When I add an "available and orderable" product to my bag
    Then I checkout until I reach the order review page as an "guest" user
    And I verify that page is served from "DAL" server