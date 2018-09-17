# Author: Discovery QE

Feature: Verify Search Box functionality on SLP in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @snbc_comp @use_regression @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify autocomplete works
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I enter "Lev" keyword in search field
    Then I verify that autocomplete suggestions list is populated
# Notes: Verifies that autocomplete works in SRP

  @domain_discovery @priority_low @use_regression @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic - Verify that meta keywords tag is correctly populated on search landing page, zero result page verify meta keywords tag is correctly populated on Search Zero Results Page
    Given I visit the web site as a guest user
    When I search for "<keyword>"
    And I modify the url to get in to snbc experiment
    Then I verify that meta "description" tag as "<meta_keywords>"
    Examples:
      | keyword | meta_keywords                                                                                          |
      | jeans   | Shop jeans at Bloomingdales.com. Free Shipping and Free Returns for Loyallists or Any Order Over $150! |
      | xtyz    | Shop xtyz at Bloomingdales.com. Free Shipping and Free Returns for Loyallists or Any Order Over $150!  |

  @use_regression @priority_medium @domain_discovery @mode_domestic
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

  @use_regression @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify that searches with product ID/WebID bypasses the search results page and go to PDP for that Product ID
    Given I visit the web site as a guest user
    When I search for an available product ID or WebID having "productid only"
    Then I should be redirected to PDP page

  @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify that extraneous spaces, leading zeroes, or other characters in the keyword search, gets ignored and executes the search
    Given I visit the web site as a guest user
    When I search for an available product ID or WebID having "productid with preceding zeros and space"
    Then I should be redirected to PDP page
    When I search for an available product ID or WebID having "product id with other characters"
    Then I should be redirected to PDP page

  @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify that a  numerical search with no results, show standard zero results page - or customize with message about checking number if possible in Phase 1
    Given I visit the web site as a guest user
    When I search for an available product ID or WebID having "invalid productid"
    Then I verify that "zero search result" page is displayed

  @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic - Verify on all browsers autocomplete suggestions appear correctly
  special characters | or * or ? or _ or ^ or @ or ! or ~ entered as the second character in the search box
    Given I am on SearchResultsPage for "plates" in Domestic mode
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

  @use_regression @domain_discovery @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify that user sees 10 words or phrases that are pertinent to the characters typed
  when user types in a search query, Autocomplete suggestions to display after the first two characters are typed in.
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I enter "WO" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that 10 words or phrases Pertinent to the characters typed


  @domain_discovery @mode_domestic @priority_medium @snbc_comp @migrated_to_sdt @use_regression
  Scenario: SearchResultsPage - verify that the part of the search query that is in the search box displays in bold in the Autosuggest list
#Note :(For example, if end user types in Wo, and the suggestions have Womens Dresses, Womens Jackets, Womens Coats, then "Wo" should be bolded in all the suggestions.)
# In the suggestions, the first letter of each word should be lowercase.
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I enter "WO" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    #And the autocomplete suggestions should display the part of query in bold = UI verifcation
    And I verify that first letter of each word in autocomplete suggestions should be lower case

  @use_regression @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify that the key created on Browser/Akamai is case insensitive and when a customer type "ck" or "CK" or "cK" or "Ck" the key should remain the same
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I enter "wo" keyword in search field and get the first suggestion lists
    And I remove text from the search box
    And I enter "WO" keyword in search field and get the second suggestion lists
    Then I verify that autocomplete suggestions list is populated
    And I verify that both first and second autocomplete suggestions are same

  @unifiednavigation_12744 @use_regression @domain_discovery @priority_medium @mode_registry @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Registry - Verify registry page and type in a search prefix
    Given I am on SearchResultsPage for "plates" in Registry mode
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is not populated

  @use_dsv @domain_discovery @use_regression @mode_registry @priority_medium @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify junk word search in REGISTRY mode
    Given I am on SearchResultsPage for "plates" in Registry mode
    And I search for "sadafssdsddasdssSDSS"
    Then I verify that "zero search result" page is displayed

    #BLCOM-81356
  @domain_discovery @use_regression @priority_high @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify the keyword iship in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I search for "iship"
    Then I verify that "zero search result" page is displayed

  @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify user redirection to PDP by specifying a keyword trigger
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I search for a PDP redirect keyword
    Then I should be redirected to PDP page
    When I collect product information from PDP page
    And I collect PDP redirect information from SDP in "SITE" mode
    Then I verify that PDP redirect information should be same in both Navapp and SDP

  @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify user redirection to Category by specifying a keyword trigger
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I search for a Category redirect keyword
    And I collect Category redirect information from SDP in "SITE" mode
    Then I verify that Category redirect information should be same in both Navapp and SDP

  @use_regression @domain_discovery @priority_low @mode_domestic @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify user redirection to URL by specifying a keyword trigger
    Given I am on SearchResultsPage for "plates" in Domestic mode
    When I search for a URL redirect keyword
    And I collect URL redirect information from SDP in "SITE" mode
    Then I verify that URL redirect information should be same in both Navapp and SDP