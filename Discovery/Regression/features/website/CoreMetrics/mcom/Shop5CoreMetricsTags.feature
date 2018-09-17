# Author: Discovery QE
# Date Created: 02/16/2017

Feature: Verifying Shop 5 Coremetrics tags populated from BAG page.

  # ***********************     GUEST     DOMESTIC         *******************************************

  @shop5_guest_regular_search_domestic @snbc_coremetrics
  Scenario: Checkout Page - Domestic - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I am on SearchResultsPage for "hangers" in domestic mode
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_autocomplete_domestic @snbc_coremetrics
  Scenario: Checkout Page - AutoComplete - Domestic - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "skir" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "skirts" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_autocomplete_QV_domestic @snbc_coremetrics
  Scenario: Checkout Page QuickView- Domestic - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "hand" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "handbags" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_regular_autocomplete_facet_PDP_domestic @snbc_coremetrics
  Scenario: Checkout Page - Facet with AutoComplete - Domestic - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "skir" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "skirts" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_regular_autocomplete_facet_QV_domestic @snbc_coremetrics
  Scenario: Checkout Page - Facet with QuickView - Domestic - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    When I enter "hand" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "handbags" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

 #************************************     REGISTERED     DOMESTIC         ******************************************************

  @shop5_registered_regular_search_domestic @snbc_coremetrics
  Scenario: Checkout Page - Domestic - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Given I search for "hangers" in search box
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_autocomplete_domestic @snbc_coremetrics
  Scenario: Checkout Page - AutoComplete - Domestic - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "skir" keyword in search field
    When I select "skirts" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_autocomplete_QV_domestic @snbc_coremetrics
  Scenario: Checkout Page QuickView - Domestic - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "hand" keyword in search field
    When I select "handbags" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_regular_autocomplete_facet_PDP_domestic @snbc_coremetrics
  Scenario: Checkout Page - Facet with AutoComplete - Domestic - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "skir" keyword in search field
    When I select "skirts" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_regular_autocomplete_facet_QV_domestic @snbc_coremetrics
  Scenario: Checkout Page - Facet with QuickView - Domestic - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    When I enter "hand" keyword in search field
    When I select "handbags" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

   #***********************        GUEST    ISHIP         *******************************************

  @shop5_guest_regular_search_iship @snbc_coremetrics
  Scenario: Checkout Page - ISHIP - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I am on SearchResultsPage for "hangers" in iship mode
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_autocomplete_iship @snbc_coremetrics
  Scenario: Checkout Page - AutoComplete - Iship - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "skir" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "skirts" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_autocomplete_QV_iship @snbc_coremetrics
  Scenario: Checkout Page QuickView- Iship - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "hand" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "handbags" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_regular_autocomplete_facet_PDP_iship @snbc_coremetrics
  Scenario: Checkout Page - Facet with AutoComplete - Iship - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "skir" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "skirts" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_regular_autocomplete_facet_QV_iship @snbc_coremetrics
  Scenario: Checkout Page - Facet with QuickView - Iship - As a guest user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "iship" mode
    When I enter "hand" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "handbags" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

#********************************             REGISTERED ISHIP    ****************************************************

  @shop5_registered_regular_search_iship @snbc_coremetrics @invalid @wip
  Scenario: Checkout Page - ISHIP - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "iship" mode
    Then I search for "hangers" in search box
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_autocomplete_iship @snbc_coremetrics @invalid @wip
  Scenario: Checkout Page - AutoComplete - Iship - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "iship" mode
    When I enter "skir" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "skirts" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_autocomplete_QV_iship @snbc_coremetrics @invalid @wip
  Scenario: Checkout Page QuickView- Iship - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "iship" mode
    When I enter "hand" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "handbags" from autocomplete suggestions
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_regular_autocomplete_facet_PDP_iship @snbc_coremetrics @invalid @wip
  Scenario: Checkout Page - Facet with AutoComplete - Iship - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "iship" mode
    When I enter "skir" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "skirts" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_regular_autocomplete_facet_QV_iship @snbc_coremetrics @invalid @wip
  Scenario: Checkout Page - Facet with QuickView - Iship - As a registered user, Shop 5 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "iship" mode
    When I enter "hand" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "handbags" from autocomplete suggestions
    Then I select "single" facet value from "Price" facet section
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

   #************************************  REGISTRY MODE  - REGULAR  *********************************************

  @shop5_guest_regular_search_registry @snbc_coremetrics
  Scenario: Checkout Page - REGISTRY - As a guest user, Shop 5 coremetrics tags are populated correctly from PDP
    Given I am on SearchResultsPage for "hangers" in registry mode
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_regular_search_registry @snbc_coremetrics
  Scenario: Checkout Page - REGISTRY - As a registered user, Shop 5 coremetrics tags are populated correctly from PDP
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "hangers" in search box
    Then I modify the url to get in to snbc experiment
    Then I select 1 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Then I click on checkout button on add to bag page
    Then I verify that landed on "shopping_bag" Page

    #************************************  REGISTRY MODE  - QuickView *********************************************

  @shop5_guest_regular_search_QV_registry @snbc_coremetrics
  Scenario: Checkout Page - REGISTRY - As a guest user, Shop 5 coremetrics tags are populated correctly from QV
    Given I am on SearchResultsPage for "hangers" in registry mode
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_regular_search_QV_registry @snbc_coremetrics
  Scenario: Checkout Page - REGISTRY - As a registered user, Shop 5 coremetrics tags are populated correctly from QV
    Given I visit the web site as a registered user in "registry" mode
    Then I search for "hangers" in search box
    Then I modify the url to get in to snbc experiment
    Then I select the quick peek of 1 th product
    Then I verify that landed on "quick_view" Page
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page