Feature: Verify the search persist accurately when customers select a suggestion from the autocomplete zone in Mcom mobile

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: HomePage - Verify the selected term should persist accurately in search result page when a user selects a text suggestion in automcomplete zone in domestic mode on home page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    Then I should see the selected AC text in the search box
    Examples:
      |keyword|
      |po     |
      |ji     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: CatSplashPage - Verify the selected term should persist accurately in search result page when a user selects a text suggestion in automcomplete zone in domestic mode on Category splash page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    Then I should see the selected AC text in the search box
    Examples:
      |keyword|
      |se     |
      |ma     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: PDPPage - Verify the selected term should persist accurately in search result page when a user selects a text suggestion in automcomplete zone in domestic mode PDP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I navigate to a random PDP page on browse page
    Then I should be on PDP page
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    Then I should see the selected AC text in the search box
    Examples:
      |keyword|
      |va     |
      |dr     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: ShoppingBagPage - Verify the selected term should persist accurately in search result page when a user selects a text suggestion in automcomplete zone in domestic mode on shopping bag page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Jeans |
    And I navigate to a random PDP page on search results page
    Then I scroll to add bag button
    When I add product to my bag from standard PDP Page using mobile site
    When I click on continue checkout button
    When I type "<keyword>" in mew search
    Then I should see auto complete suggestions in mew
    When I select random autocomplete suggestion in mew
    Then I should see the selected AC text in the search box
    Examples:
      |keyword  |
      |Celebrity|
      |lipstick |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: HomePage - AutoSuggetions should not be displayed for invalid keyword
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      |keyword|
      |$@#232@|
      |@(})@23|

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: PDPPage - Verify the selected term should not persist accurately for invalid keywords in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I navigate to a random PDP page on search results page
    When I type "<keyword>" in mew search
    Then I should not see auto complete suggestions in mew
    Examples:
      |keyword|
      |Litchi |
      |Kint   |
