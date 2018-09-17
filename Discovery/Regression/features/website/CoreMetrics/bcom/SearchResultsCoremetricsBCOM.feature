Feature: To verify coremetric tags in search results page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Page view tags for search results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I navigate to search results page in "<mode_name>" mode
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Product view tags for search results page product selection
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I select 4 th product from thumbnail grid
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for sort by in search results
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I should see sort by functionality with below options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (low-high)   |
      | Price (high-low)   |
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for facet selection in search results
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    Then I select 'single' facet value from 'any' facet section
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for back to top button in search results page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to search results page in "<mode_name>" mode
    And I navigate to bottom of page
    Then  I select 'back to top' button
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for color swatch selection in search results page
    Given I am on SearchResultsPage for "bedding collections" in <shopping_mode> mode
    When  I find a random product having color swatches
    Then  I select any color in color swatches section
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify Element tags for color swatches more button selection in search results page
    Given I am on SearchResultsPage for "bedding collections" in <shopping_mode> mode
    When  I find a random product having color swatches with more button
    Then  I select 'More' button in color swatch section
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify attribute37 is reserved for testing on search results page and shop 5 actions
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select 4 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |