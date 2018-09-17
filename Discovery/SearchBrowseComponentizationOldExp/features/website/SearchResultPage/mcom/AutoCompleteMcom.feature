Feature: Auto Complete functionality in DOMESTIC, ISHIP and REGISTRY mode

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario: SearchResultsPage - Domestic - Verify user types two characters in search box
    Given I visit the web site as a guest user
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that 10 words or phrases Pertinent to the characters typed

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario: SearchResultsPage - Domestic - Verify first letter of suggestions are capitalized
    Given I visit the web site as a guest user
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that first letter of each word in autocomplete suggestions should be capitalized

  @unifiednavigation_12744 @use_regression @artifact_navapp @domain_discovery @priority_medium  @project_snb
  Scenario: SearchResultsPage - Domestic - Verify that the key created on Browser/Akamai is case insensitive and when a customer type "ck" or "CK" or "cK" or "Ck" the key should remain the same
    Given I visit the web site as a guest user
    When I enter "wo" keyword in search field and get the first suggestion lists
    And I remove text from the search box
    And I enter "WO" keyword in search field and get the second suggestion lists
    Then I verify that autocomplete suggestions list is populated
    And I verify that both first and second autocomplete suggestions are same

  @unifiednavigation_12744 @use_regression @artifact_navapp @domain_discovery @priority_medium @project_snb
  Scenario: SearchResultsPage - Domestic - Type in a search prefix in the search box
    Given I visit the web site as a guest user
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is populated

  @unifiednavigation_12744 @use_regression @artifact_navapp @domain_discovery @priority_medium @mode_registry @project_snb @use_browser
  Scenario: SearchResultsPage - Registry - Verify registry page and type in a search prefix
    Given I visit the web site as a guest user in "registry" mode
    When I enter "pa" keyword in search field
    Then I verify that autocomplete suggestions list is not populated

  @unifiednavigation_12744 @use_regression @artifact_navapp @domain_discovery @priority_low @project_snb
  Scenario: SearchResultsPage - Domestic - Verify autocomplete suggestions length in search box should be max 72 characters
    Given I visit the web site as a guest user
    When I enter "DK" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that characters of suggestions not more than 72 characters

  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic @project_snb
  Scenario Outline: SearchResultsPage - Domestic - Verify on all browsers autocomplete suggestions appear correctly when special characters | or * or ? or _ or ^ or @ or ! or ~ entered as the second character in the search box
    Given I visit the web site as a guest user
    When I enter "<keyword>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that suggestions are not displayed jibberish
    Examples:
      |keyword|
      |J\|      |
      |W*       |
      |s?       |
      |p_       |
      |a^       |
      |b@       |
      |e!       |
      |d~       |

  @release_14C @unified_navigation_17946 @artifact_navapp @domain_discovery @priority_high @use_regression  @mode_domestic @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that user types in First few characters of search term in search box and ensure that it makes bold that are not matching the character that user typed
    Given I visit the web site as a guest user in "<mode>" mode
    When I enter "Mic" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I verify that autocomplete suggestions displayed with letters that do not match the user typed term in bold
    Examples:
      |mode|
      |domestic|
      |iship   |
      #|registry|

  @release_14C @unified_navigation_17946 @artifact_navapp @domain_discovery @priority_high @use_regression @project_snb
  Scenario: SearchResultsPage - Domestic - verify Search landing page by selecting any of the autocomplete suggestions in DOMESTIC mode
    Given I visit the web site as a guest user
    When I enter "Mic" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I select "Michael Kors" from autocomplete suggestions
    Then I should be in Search Landing page