###########################################################################################################################
# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/04/2017
# Story Titles: Mcom :: Desktop :: Show recent searches in search bar
# Versionone story number:: B-59065 &&  B-61799
#############################################################################################################################

Feature: Verify the recent searches in search bar in desktop

  @domain_discovery @use_regression @mode_domestic @discovery_daily_run
  Scenario: SubSplashPage - Verify recent searches panel below search bar in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword
    When I click on any random recent search keyword
    Then I should be in Search Landing page
    And I should see the selected autosuggestion keyword persist in search box on search results page

  @domain_discovery @use_regression @mode_domestic
  Scenario: SubSplashPage - Verify recent search panel disappeared when we enter two characters in search field in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    Then I should see "Recent Searches" text in recent search panel
    When I type "j" in search box
    Then I should see "Recent Searches" text in recent search panel
    When I type "je" in search box
    Then I should see autocomplete suggestions
    And I should not see "Recent Searches" text in recent search panel

  @domain_discovery @use_regression @mode_domestic
  Scenario Outline: SubSplashPage - Verify old search keyword moving to top when we make another search with existing keyword in recent panel in DOMESTIC mode
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
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

  @domain_discovery @use_regression @mode_iship
  Scenario: SubSplashPage - Verify recent searches panel below search bar in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS & ACCESSORIES"
    Then I should see "Recent Searches" text in recent search panel
    And I should see recent search panel with max 5 last searches keywords
    And I should see search symbol infront of recently searched keyword
    When I click on any random recent search keyword
    Then I should be in Search Landing page
    And I should see the selected autosuggestion keyword persist in search box on search results page

  @domain_discovery @use_regression @mode_iship
  Scenario: SubSplashPage - Verify recent search panel disappeared when we enter two characters in search field in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS & ACCESSORIES"
    Then I should see "Recent Searches" text in recent search panel
    When I type "j" in search box
    Then I should see "Recent Searches" text in recent search panel
    When I type "je" in search box
    Then I should see autocomplete suggestions
    And I should not see "Recent Searches" text in recent search panel

  @domain_discovery @use_regression @mode_iship
  Scenario Outline: SubSplashPage - Verify old search keyword moving to top when we make another search with existing keyword in recent panel in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
      | puma    |
      | shirts  |
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS & ACCESSORIES"
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