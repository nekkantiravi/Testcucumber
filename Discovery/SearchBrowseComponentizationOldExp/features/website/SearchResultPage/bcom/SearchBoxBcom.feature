# Author: Discovery QE

Feature: Verify Search Box functionality on SLP in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @artifact_navapp @high @bcom @please_automate
  Scenario: SearchResultsPage - Domestic - Verify autocomplete works
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I enter "Le" keyword in search field
    Then I verify that autocomplete suggestions list is populated
# Notes: Verifies that autocomplete works in SRP

  @artifact_navapp @domain_discovery @priority_low @bat_refactored @un_search @use_regression @use_regression_2 @mode_domestic @please_automate
  Scenario Outline: SearchResultsPage - Domestic - Verify that meta keywords tag is correctly populated on search landing page, zero result page verify meta keywords tag is correctly populated on Search Zero Results Page
    Given I am on SearchResultsPage for "<keyword>" in Domestic mode
    Then I verify that meta "keywords" tag as "<meta_keywords>"
    Examples:
      | keyword | meta_keywords                                                                                            |
      | Jeans   | [Shop Jeans at Bloomingdales.com. Free Shipping and Free Returns for Loyallists or Any Order Over $150!] |
      | xtyz    | [Shop xtyz at Bloomingdales.com. Free Shipping and Free Returns for Loyallists or Any Order Over $150!]  |

  @unifiednavigation_2583 @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_1 @mode_Domestic @please_automate
  Scenario Outline: SearchResultsPage - Domestic - Verify Three levels of pricing on thumbnail for member and Master product
    Given I am on SearchResultsPage for "<Keyword>" in Domestic mode
    And I verify that "<type>" products are displayed with three levels of pricing information
    Examples:
      | Keyword            | type   |
      | red dress          | Member |
      | bedding collection | Master |

  @use_regression @priority_medium @artifact_navapp @domain_discovery @mode_Domestic @please_automate
  Scenario Outline: SearchResultsPage - Domestic - Verify that we recognize scripts and do not submit a search in such cases
    Given I visit the web site as a guest user in "<shopping_mode>" mode
    When I search for "<script>alert('hi there')</script>"
    Then I verify that "zero search result" page is not displayed
    Then I verify that main search field cleared
    Examples:
      |shopping_mode|
      |domestic     |
      |iship        |
      |registry     |

  @use_regression  @artifact_navapp @domain_discovery @priority_medium @use_regression_2 @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify that searches with product ID/WebID bypasses the search results page and go to PDP for that Product ID
    Given I visit the web site as a guest user
    When I search for an available product ID or WebID having "productid only"
    Then I should be redirected to PDP page

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_2 @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify that extraneous spaces, leading zeroes, or other characters in the keyword search, gets ignored and executes the search
    Given I visit the web site as a guest user
    When I search for an available product ID or WebID having "productid with preceding zeros and space"
    Then I should be redirected to PDP page
    When I search for an available product ID or WebID having "product id with other characters"
    Then I should be redirected to PDP page

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_2 @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify that a  numerical search with no results, show standard zero results page - or customize with message about checking number if possible in Phase 1
    Given I visit the web site as a guest user
    When I search for an available product ID or WebID having "invalid productid"
    Then I verify that "zero search result" page is displayed

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_2 @mode_domestic @please_automate
  Scenario Outline: SearchResultsPage - Domestic - Verify on all browsers autocomplete suggestions appear correctly
  special characters | or * or ? or _ or ^ or @ or ! or ~ entered as the second character in the search box
    Given I visit the web site as a guest user
    When I enter "<keyword>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that suggestions are not displayed jibberish
    Examples:
      |keyword|
      |J\|	  |
      |W*	  |
      |s?	  |
      |p_	  |
      |a^	  |
      |b@	  |
      |d~	  |

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_regression_2 @mode_domestic @use_preview @use_browser @please_automate
  Scenario: SearchResultsPage - Domestic - Verify that user sees 10 words or phrases that are pertinent to the characters typed
  when user types in a search query, Autocomplete suggestions to display after the first two characters are typed in.
    Given I visit the web site as a guest user
    When I enter "WO" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that 10 words or phrases Pertinent to the characters typed


  @artifact_navapp @domain_discovery @wip @mode_domestic @priority_medium @please_automate
  Scenario: SearchResultsPage - verify that the part of the search query that is in the search box displays in bold in the Autosuggest list
#Note :(For example, if end user types in Wo, and the suggestions have Womens Dresses, Womens Jackets, Womens Coats, then "Wo" should be bolded in all the suggestions.)
# In the suggestions, the first letter of each word should be lowercase.
    Given I visit the web site as a guest user
    When I enter "WO" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    #And the autocomplete suggestions should display the part of query in bold = UI verifcation
    And I verify that first letter of each word in autocomplete suggestions should be lower case

  @use_regression @artifact_navapp @domain_discovery @priority_medium @use_regression_2 @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify that the key created on Browser/Akamai is case insensitive and when a customer type "ck" or "CK" or "cK" or "Ck" the key should remain the same
    Given I visit the web site as a guest user
    When I enter "wo" keyword in search field and get the first suggestion lists
    And I remove text from the search box
    And I enter "WO" keyword in search field and get the second suggestion lists
    Then I verify that autocomplete suggestions list is populated
    And I verify that both first and second autocomplete suggestions are same

  @unifiednavigation_12744 @use_regression @artifact_navapp @domain_discovery @priority_medium @mode_registry @please_automate @use_browser
  Scenario: SearchResultsPage - Registry - Verify registry page and type in a search prefix
    Given I visit the web site as a guest user in "registry" mode
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is not populated

  @use_dsv @domain_discovery @use_regression @bcom_regression @mode_registry @use_regression_2 @priority_medium @please_automate
  Scenario: SearchResultsPage - Domestic - Verify junk word search in REGISTRY mode
    Given I am on SearchResultsPage for "jeans" in Registry mode
    And I search for "sadafssdsddasdssSDSS"
    Then I verify that "zero search result" page is displayed

    #BLCOM-81356
  @use_dsv @domain_discovery @use_regression @s4a_stable @bcom_regression @priority_high @mode_domestic @use_regression_3 @mode_domestic
  Scenario: SearchResultsPage - Domestic - Verify the keyword iship in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search for "iship"
    Then I verify that "zero search result" page is displayed

  @use_dsv_severity1 @domain_discovery @use_regression @bcom_regression @use_regression_3 @mode_domestic @priority_medium @please_automate
  Scenario: SearchResultsPage - Domestic - Verify Search for a keyword
    Given I visit the web site as a guest user
    When I enter "WO" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that 10 words or phrases Pertinent to the characters typed
    When I select "wool coats" from autocomplete suggestions
    Then I should be in Search Landing page
    And I verify that facets are listed on left nav
    When I select 4 facet value(s) from 'any' facet sections
    Then I verify that clear all button is displayed
    When I click on clear all button
    Then I verify that all of the products are displayed
# Notes:
# autocomplete should  display 10 items and typed characters are displayed in bold in autocomplete items
# search result page should display. Verify the title of the page should be of the format <keyword used for search>| Bloomingdales


  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_3 @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify user redirection to PDP by specifying a keyword trigger
    Given I visit the web site as a guest user
    When I search for a PDP redirect keyword
    Then I should be redirected to PDP page
    When I collect product information from PDP page
    And I collect PDP redirect information from SDP in "SITE" mode
    Then I verify that PDP redirect information should be same in both Navapp and SDP

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_3 @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify user redirection to Category by specifying a keyword trigger
    Given I visit the web site as a guest user
    When I search for a Category redirect keyword
    And I collect Category redirect information from SDP in "SITE" mode
    Then I verify that Category redirect information should be same in both Navapp and SDP

  @use_regression @artifact_navapp @domain_discovery @priority_low @use_regression_3 @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify user redirection to URL by specifying a keyword trigger
    Given I visit the web site as a guest user
    When I search for a URL redirect keyword
    And I collect URL redirect information from SDP in "SITE" mode
    Then I verify that URL redirect information should be same in both Navapp and SDP