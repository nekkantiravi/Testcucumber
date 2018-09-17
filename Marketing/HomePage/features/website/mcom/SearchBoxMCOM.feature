#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page functionality in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page - Verify Search Box in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify Search Box is displayed with search icon
    When I search "cap" for auto-completion
    Then I see auto-suggestions for "cap"
    When I click the search icon
    Then I verify I land on Search Results Page for "cap"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery @defect_ECOMSST-48023 @wip
  Scenario: Home Page - Verify Search Box in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify Search Box is displayed with search icon
    When I search "cap" for auto-completion
    Then I see auto-suggestions for "cap"
    When I click the search icon
    Then I verify I land on Search Results Page for "cap"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text


  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery @use_domain_qual
  Scenario: Home Page - Verify Search Box on REGISTRY Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I verify Search Box is displayed with search icon
    When I search "cups" for auto-completion
    Then I should not see autocomplete suggestions
    When I click the search icon
    Then I verify I land on Search Results Page for "cups"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify Search Box in REGISTRY mode
    Given I visit the web site as a registry user
    And I verify Search Box is displayed with search icon
    When I search "cups" for auto-completion
    Then I should not see autocomplete suggestions
    When I click the search icon
    Then I verify I land on Search Results Page for "cups"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text


  #See also the related JavaScript-only story #19237.

  @use_regression @artifact_navapp @domain_marketing @priority_high @release_13H @mode_domestic @use_preview @use_domain_qual
  Scenario: Home Page - Search Box - AutoComplete - Verify user types two characters in search box to see autocomplete in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type first two characters "Pa" in search box
    Then I should see autocomplete suggestions
    And I should see "10" words or phrases Pertinent to the characters typed

  @use_regression @artifact_navapp @domain_marketing @priority_high @release_13H @mode_domestic
  Scenario: Home Page - Search Box - AutoComplete - Verify first letter of suggestions are capitalized in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type first two characters "Pa" in search box
    Then I should see autocomplete suggestions
    And the autocomplete suggestions should display the part of query in bold
    And the first letter of each word in autocomplete suggestions should be capitalized

  @use_regression @artifact_navapp @domain_marketing @priority_medium  @release_13H @mode_domestic
  Scenario: Home Page - Search Box - AutoComplete - Ensure the key created on Browser/Akamai is case insensitive and when a customer types ck or CK or cK or Ck the key should remain the same
    Given I visit the web site as a guest user
    When I type first two characters "wo" in search box
    Then I should see autocomplete suggestions
    When I clear the search text area
    And I type first two characters "WO" in search box
    Then I should see autocomplete suggestions
    And I should see both autocomplete suggestions as same

  @use_regression @artifact_navapp @domain_marketing @priority_medium @release_13H @mode_domestic @use_domain_qual
  Scenario: Home Page - Search Box - AutoComplete - Type in a search prefix in the search box in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type first two characters "Pa" in search box
    Then I should see autocomplete suggestions

  @use_regression @artifact_navapp @domain_marketing @priority_medium @release_13H @mode_registry
  Scenario: Home Page - Search Box - AutoComplete - Navigate to registry page and type in a search prefix in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
   # Given Navigate to The Registry Page
    When I type first two characters "Pa" in search box
    Then I should not see autocomplete suggestions

  @use_regression @artifact_navapp @domain_marketing @priority_low @release_13H @mode_domestic
  Scenario: Home Page - Search Box - AutoComplete - Verify autocomplete suggestions length in search box should be max 72 characters in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type first two characters "DK" in search box
    Then I should see autocomplete suggestions
    And I should see the characters of suggession not more than "72" characters


  @use_regression @artifact_navapp @domain_marketing @priority_low @release_13H @mode_domestic
  Scenario Outline: Home Page - Search Box - AutoComplete - Verify autocomplete suggestions appear correctly for special characters | or * or ? or _ or ^ or @ or ! or ~ entered as the second character in the search box in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type first two characters "<keyword>" in search box
    Then I should see autocomplete suggestions
    And I should not see suggestions displayed jibberish
  Examples:
    | keyword |
    | J\|     |
    | W*      |
   # | s?      |
    | p_      |
   # | a^      |
    | b@      |
   #	|e!		|
    | d~      |


  @release_14C @unified_navigation_17946 @artifact_navapp @domain_marketing @priority_high @use_regression @mode_domestic
  Scenario: Home Page - Search Box - AutoComplete - User types in First few characters of search term in search box and ensure that it makes bold that are not matching the character that user typed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type "mic" in search box
    Then I should see autocomplete suggestions
    And The autocomplete suggestions should display the letters that do not match the user typed term in bold

  @release_14C @unified_navigation_17946 @artifact_navapp @domain_marketing @priority_high @use_regression @mode_domestic
  Scenario: Home Page - Search Box - AutoComplete - User types in First few characters of search term in search box and ensure that it makes bold that are not matching the character that user typed in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I type "mic" in search box
    Then I should see autocomplete suggestions
    And The autocomplete suggestions should display the letters that do not match the user typed term in bold
