###########################################################################################################################
# Story Titles: Mcom :: Desktop :: Show recent searches in search bar
# Versionone story number:: B-59065 &&  B-61799
#############################################################################################################################

Feature: Verify the recent searches in search bar in desktop

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @migrated_to_sdt @discovery_daily_run @xbrowser_catsplash
  Scenario: CatSplashPage - Verify recent searches panel below search bar in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to category splash page
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword
    When I click on any random recent search keyword
    Then I should be in Search Landing page
    And I should see the selected autosuggestion keyword persist in search box on search results page

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @migrated_to_sdt
  Scenario: CatSplashPage - Verify recent search panel disappeared when we enter two characters in search field in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to category splash page
    Then I should see "Recent Searches" text in recent search panel
    When I type "j" in search box
    Then I should see "Recent Searches" text in recent search panel
    When I type "je" in search box
    Then I should see autocomplete suggestions
    And I should not see "Recent Searches" text in recent search panel

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @migrated_to_sdt
  Scenario Outline: CatSplashPage - Verify old search keyword moving to top when we make another search with existing keyword in recent panel in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to category splash page
    Then I should see "Recent Searches" text in recent search panel
    When I search for "skirts"
    Then I should be in Search Landing page
    And I should see "Recent Searches" text in recent search panel
    When I search for "<previously_search_keyword>"
    And I navigate to category splash page
    Then I should see the keyword "<previously_search_keyword>" comes to top in recent searches
    Examples:
      | previously_search_keyword |
      | jeans                     |
      | shorts                    |

  @domain_discovery @feature_catsplash @use_regression @mode_iship @migrated_to_sdt
  Scenario: CatSplashPage - Verify recent searches panel below search bar in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to category splash page
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword
    When I click on any random recent search keyword
    Then I should be in Search Landing page
    And I should see the selected autosuggestion keyword persist in search box on search results page

  @domain_discovery @feature_catsplash @use_regression @mode_iship @migrated_to_sdt
  Scenario: CatSplashPage - Verify recent search panel disappeared when we enter two characters in search field in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to category splash page
    Then I should see "Recent Searches" text in recent search panel
    When I type "j" in search box
    Then I should see "Recent Searches" text in recent search panel
    When I type "je" in search box
    Then I should see autocomplete suggestions
    And I should not see "Recent Searches" text in recent search panel

  @domain_discovery @feature_catsplash @use_regression @mode_iship @migrated_to_sdt
  Scenario Outline: CatSplashPage - Verify old search keyword moving to top when we make another search with existing keyword in recent panel in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to category splash page
    Then I should see "Recent Searches" text in recent search panel
    When I search for "skirts"
    Then I should be in Search Landing page
    And I should see "Recent Searches" text in recent search panel
    When I search for "<previously_search_keyword>"
    And I navigate to category splash page
    Then I should see the keyword "<previously_search_keyword>" comes to top in recent searches
    Examples:
      | previously_search_keyword |
      | jeans                     |
      | shorts                    |