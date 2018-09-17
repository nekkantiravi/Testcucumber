Feature: To verify coremetric tags in dlp page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify Page view tags for dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then  I navigate to dynamic landing page in "<mode_name>" mode
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify Product view tags for dlp page product selection
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page in "<mode_name>" mode
    Then I select 4 th product from thumbnail grid
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify Element tags for sort by in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page in "<mode_name>" mode
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
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify Element tags for facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page in "<mode_name>" mode
    Then I select 'single' facet value from 'any' facet section
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify Element tags for back to top button in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page in "<mode_name>" mode
    And I navigate to bottom of page
    Then  I select 'back to top' button
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify Element tags for color swatch selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page with color swatch products in  "<mode_name>"
    And  I find a random product having color swatches
    Then  I select any color in color swatches section
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify Element tags for color swatches more button selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page with color swatch products in  "<mode_name>"
    And  I find a random product having color swatches with more button
    Then  I select 'More' button in color swatch section
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |


  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: DLP Page - Domestic|Iship|Registry - Verify attribute37 is reserved for testing on dlp page and shop 5 actions
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to dynamic landing page in "<mode_name>" mode
    Then I select 4 th product from thumbnail grid
    And I add product to my bag from standard PDP
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |