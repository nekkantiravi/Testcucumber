Feature: BCOM - Verify the search persist accurately when customers select a suggestion from the autocomplete zone in Mcom mobile

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: HomePage - Verify the user navigated to correct search result page when a user selects a text suggestion in auto complete zone in domestic mode on home page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    Then I should be in Search Landing page using mobile website
    And I verify user navigated to correct page
    Examples:
      |keyword|
      |po     |
      |ji     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - Verify the user navigated to correct search result page when a user selects a text suggestion in auto complete zone in domestic mode on cat splash page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | MEN           |
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    And I verify user navigated to correct page
    Examples:
      |keyword|
      |se     |
      |ma     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: PDPPage - Verify the selected term should persist accurately in search result page when a user selects a text suggestion in automcomplete zone in domestic mode PDP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Boots" in mew search and click enter
    And I navigate to a random PDP page on search results page
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    Then I should be in Search Landing page using mobile website
    And I verify user navigated to correct page
    Examples:
      |keyword|
      |va     |
      |dr     |
