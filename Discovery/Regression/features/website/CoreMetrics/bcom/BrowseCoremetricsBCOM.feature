Feature: To verify coremetric tags in browse page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Page view tags for category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    Then I navigate to browse page in "<mode_name>" mode
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |


  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Product view tags for category browse page member product
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I select random product from thumbnail grid
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for sort by in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I select "Customer Top Rated" in sort by drop down
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for facet selection in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I select 'single' facet value from 'any' facet section
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for back to top button in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    And I navigate to bottom of page
    Then  I select 'back to top' button
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for color swatch selection in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    And  I find a random product having color swatches
    Then  I select any color in color swatches section
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics @wip @please_automate
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for for color swatches more button selection in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    And   I find a random product having color swatches with more button
    Then  I select 'More' button in color swatch section
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify attribute37 is reserved for testing on browse page and shop 5 actions
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    And I select 4 th product from thumbnail grid
    Then I add product to my bag from standard PDP
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic - Verify Element tags for for media in category browse page
    Given I visit the web site as a guest user
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    And I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media     |
      | 101      | IMAGE_MAP |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Iship - Verify Element tags for for media in category browse page
    Given I visit the web site as a guest user
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    And I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media     |
      | 101      | IMAGE_MAP |