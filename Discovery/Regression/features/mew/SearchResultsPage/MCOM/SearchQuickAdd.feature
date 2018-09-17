Feature: As a user I should be able to use quick add feature in search pages to add a product to the bag

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Go to Category search page and verify quick add buttons
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    Then I should see quick add button for every member product on search page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the search page
    And I should see the quick add overlay with all elements as per the response on <mode> mode
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | jeans   |
      | registry | plates  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Go to Search page and verify quick add overlay to close after adding item to bag
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    Then I should see quick add button for every member product on search page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the search page
    And I should see the quick add overlay with all elements as per the response on <mode> mode
    When I tap on add to bag button in quick add overlay
    Then I should not see the quick add overlay on the browse page
    And I should see product is added to bag
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | jeans   |
      | registry | plates  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify  page is navigating to PDP when user selects see product details link on quick add
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    Then I should see quick add button for every member product on search page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the search page
    When I tap on see product details link
    Then I should be on PDP page
    Examples:
      | mode     | keyword |
      | domestic | plates  |
      | iship    | jeans   |

  @domain_mew_discovery @use_mew_regression
  Scenario: SearchResultsPage - Verify  page is navigating to PDP when user selects see product details link on quick add in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "plates" in mew search and click enter
    Then I should be on the search results page
    Then I should see quick add button for every member product on search page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the search page
    When I tap on see product details link
    Then I should be on registry PDP page
