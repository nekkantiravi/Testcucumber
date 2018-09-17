Feature: Search results page verification

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify search can be initiated on a valid keyword by tapping on Search icon
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I should see the word "<keyword>" in the global search field
    And I should see clear all X symbol in the global search field
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | jeans   |
      | registry | plates  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify the category re-direct for mcom
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be navigated to the corresponding "<page>" in <mode> mode
    Examples:
      | mode     | keyword    | page         |
      | domestic | wrap       | category     |
      | domestic | 77589      | pdp          |
      | domestic | Contact Us | absolute url |
      | domestic | Lenox      | relative url |
      | iship    | wrap       | category     |
      | iship    | 86800      | pdp          |
      | iship    | Contact Us | absolute url |
      | iship    | Lenox      | relative url |
      | registry | Towels     | category     |
      | registry | 86800      | pdp          |
      | registry | Contact Us | absolute url |
      | registry | Lenox      | relative url |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify custom message on search landing page for mcom
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I should see keyword "<keyword>" on search header
    And I should see all product images loaded properly
    Examples:
      | mode     | keyword |
      | domestic | Uggs    |
      | iship    | pants   |
      | registry | Plates  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify user should navigate to search page when search with special character keywords in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I should see keyword "<keyword>" on search header
    And I should see all product images loaded properly
    Examples:
      | keyword                           |
      | 100%                              |
      | 100@                              |
      | 100$                              |
      | 10%                               |
      | 1000%                             |
      | blue jeans                        |
      | calvin klein blue jeans           |
      | calvin klein blue jeans womens    |
      | calvin klein blue jeans in womens |
      | calvin klein blue jeans (womens)  |
      | blue jeans mens                   |
      | polo ralph lauren men's           |
      | T-shirts                          |
      | burberry women's                  |
      | inc international concepts tops   |
      | iphone 6 cases                    |
      | jewelry                           |
      | michael kors rose gold bacelet    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify user should navigate to search page when search with special character keywords in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I should see keyword "<keyword>" on search header
    And I should see all product images loaded properly
    Examples:
      | keyword                         |
      | 100%                            |
      | 100@                            |
      | 100$                            |
      | 10%                             |
      | 1000%                           |
      | T-shirts                        |
      | inc international concepts tops |
      | iphone 6 cases                  |
      | jewelry                         |
      | michael kors rose gold bacelet  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify user should navigate to search page when search with special character keywords in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the registry search results page
    And I should see keyword "<keyword>" on search header
    And I should see all product images loaded properly
    Examples:
      | keyword                         |
      | 100%                            |
      | 100@                            |
      | 100$                            |
      | 10%                             |
      | 1000%                           |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify google ads are displaying in search page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I should see google ads in search page
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | jeans   |
      | registry | plates  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify back to to functionality on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    And I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I navigate to bottom of page
    Then I should see back to top button
    And I navigate to top of page
    Then I should not see back to top button
    And I navigate to bottom of page
    And I tap on back to top button
    Then I should navigate to top of the page
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | jeans   |
      | registry | plates  |