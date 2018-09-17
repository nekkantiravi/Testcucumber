Feature: Auto Complete functionality in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify user types two characters in search box
    Given I visit the web site as a guest user
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that 10 words or phrases Pertinent to the characters typed

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify first letter of suggestions are capitalized
    Given I visit the web site as a guest user
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that first letter of each word in autocomplete suggestions should be capitalized

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario: SearchResultsPage - Domestic - Verify that the key created on Browser/Akamai is case insensitive and when a customer type "ck" or "CK" or "cK" or "Ck" the key should remain the same
    Given I visit the web site as a guest user
    When I enter "wo" keyword in search field and get the first suggestion lists
    And I remove text from the search box
    And I enter "WO" keyword in search field and get the second suggestion lists
    Then I verify that autocomplete suggestions list is populated
    And I verify that both first and second autocomplete suggestions are same

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Type in a search prefix in the search box
    Given I visit the web site as a guest user
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is populated

  @domain_discovery @priority_medium @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Registry - Verify registry page and type in a search prefix
    Given I visit the web site as a guest user in "registry" mode
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is not populated

  @domain_discovery @priority_low @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify autocomplete suggestions length in search box should be max 72 characters
    Given I visit the web site as a guest user
    When I enter "DK" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that characters of suggestions not more than 72 characters

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic - Verify on all browsers autocomplete suggestions appear correctly when special characters | or * or ? or _ or ^ or @ or ! or ~ entered as the second character in the search box
    Given I visit the web site as a guest user
    When I enter "<keyword>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that suggestions are not displayed jibberish
    Examples:
      | keyword |
      | J\|     |
      | W*      |
      | sw?     |
      | p_      |
      | as^     |
      | b@      |
      | en!     |
      | d~      |

  @domain_discovery @priority_high @mode_domestic @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship - Verify that user types in First few characters of search term in search box and ensure that it makes bold that are not matching the character that user typed
    Given I visit the web site as a guest user in "<mode>" mode
    When I enter "Mic" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that autocomplete suggestions displayed with letters that do not match the user typed term in bold
    Examples:
      |mode|
      |domestic|
      |iship   |

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - verify Search landing page by selecting any of the autocomplete suggestions
    Given I visit the web site as a guest user
    When I enter "Mic" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Michael Kors" from autocomplete suggestions
    Then I should be in Search Landing page