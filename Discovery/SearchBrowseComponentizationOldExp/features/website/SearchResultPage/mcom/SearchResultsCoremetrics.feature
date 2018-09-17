# Author: Discovery QE
# Date Created: 02/02/2017

Feature: Verifying coremetrics tags populated in Search Results Page, DLP Page and PDP Page

# Search Result Page Coremetrics Scenarios:

  @srp_domestic_guest
  Scenario: SearchResultsPage - Domestic - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I verify that landed on "search_result" Page

  @srp_iship_guest
  Scenario: SearchResultsPage - iShip - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I verify that landed on "search_result" Page

  @srp_registry_guest
  Scenario: SearchResultsPage - Registry - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I search for "plates" in search box
    Then I verify that landed on "search_result" Page

  @srp_domestic_registered
  Scenario: SearchResultsPage - Domestic - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I verify that landed on "search_result" Page

  @srp_registry_registered
  Scenario: SearchResultsPage - Registry - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "plates" in search box
    Then I verify that landed on "search_result" Page

  # Dynamic Landing Page Coremetrics Scenarios:

  @dlp_domestic_guest
  Scenario: DynamicLanding Page - Domestic - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page

  @dlp_iship_guest
  Scenario: DynamicLanding Page - iship - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "iship" mode
    Then I verify that landed on "dynamic_landing" Page

  @dlp_registry_guest
  Scenario: DynamicLanding Page - Registry - Verify As a guest user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page

  @dlp_domestic_registered
  Scenario: DynamicLanding Page - Domestic - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page

  @dlp_registry_registered
  Scenario: DynamicLanding Page - Registry - Verify As a registered user, PageView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page

## PDP via SearchResultpage Coremetrics Scenarios:

  @PDP_srp_domestic_guest
  Scenario: ProductDisplayPage - SRP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_iship_guest
  Scenario: ProductDisplayPage - SRP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_registry_guest
  Scenario: ProductDisplayPage - SRP - registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I search for "plates" in search box
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_domestic_registered
  Scenario: ProductDisplayPage - SRP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_srp_registry_registered
  Scenario: ProductDisplayPage - SRP - registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "plates" in search box
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

   # PDP via Dynamic Landing Page Coremetrics Scenarios:

  @PDP_dlp_domestic_guest
  Scenario: ProductDisplayPage - DLP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_iship_guest
  Scenario: ProductDisplayPage - DLP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "iship" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_registry_guest
  Scenario: ProductDisplayPage - DLP - registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_domestic_registered
  Scenario: ProductDisplayPage - DLP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

  @PDP_dlp_registry_registered
  Scenario: ProductDisplayPage - DLP - Registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on respective product display page

## QuickView via SearchResultPage Coremetrics Scenarios:

  @QV_srp_domestic_guest
  Scenario: QuickView ProductDisplayPage  - SRP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I select the quick peek of 5 th product
    Then I verify that landed on "quick_view" Page

  @QV_srp_iship_guest
  Scenario: QuickView ProductDisplayPage - SRP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

  @QV_srp_registry_guest
  Scenario: QuickView ProductDisplayPage - SRP- registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I search for "plates" in search box
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

  @QV_srp_domestic_registered
  Scenario: QuickView ProductDisplayPage - SRP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "je" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Jeans" from autocomplete suggestions
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

  @QV_srp_registry_registered
  Scenario: QuickView ProductDisplayPage - SRP - registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "plates" in search box
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

   # QuickView via Dynamic Landing Page Coremetrics Scenarios:

  @QV_dlp_domestic_guest
  Scenario: QuickView ProductDisplayPage - DLP - Domestic - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

  @QV_dlp_iship_guest
  Scenario: QuickView ProductDisplayPage - DLP - iship - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "iship" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

  @QV_dlp_registry_guest
  Scenario: QuickView ProductDisplayPage - DLP - registry - Verify As a guest user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

  @QV_dlp_domestic_registered
  Scenario: ProductDisplayPage - DLP - Domestic - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Then I navigate to "BRANDS" category page
    Then I navigate to dynamic landing page in website in "domestic" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

  @QV_dlp_registry_registered
  Scenario: QuickView ProductDisplayPage - DLP - registry - Verify As a registered user, ProductView coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I navigate to "KITCHEN" category page
    Then I click on "See All Brands" label link
    Then I navigate to dynamic landing page in website in "registry" mode
    Then I verify that landed on "dynamic_landing" Page
    Then I select 5 th product from thumbnail grid
    Then I verify that landed on "quick_view" Page

