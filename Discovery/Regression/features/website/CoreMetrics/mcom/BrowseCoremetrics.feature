Feature: To verify coremetric tags in browse page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Page view tags for category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    Then  I navigate to random category browse page
    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship - Verify Page view tags for faceted category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    And I navigate to the "Cardigans" browse page under "WOMEN"
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Product view tags for category browse page member product
    Given I visit the web site as a guest user in "<mode>" mode
    Then  I navigate to random category browse page
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for back to top button in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to random category browse page
    And   I scroll 'down' the page until reach bottom of footer panel
    Then I verify that back to top button is displayed on page
    And  I select 'back to top' button
    Examples:
      | mode     |
      | domestic |
      | iship    |
      | registry |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for 3 column grid selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    Then  I navigate to the "<subcategory>" browse page under "<category>"
    And I select "3" Column Grid icon
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for 4 column grid selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    Then  I navigate to the "<subcategory>" browse page under "<category>"
    And I select "4" Column Grid icon
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Page view tags for grid persistance in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    Then  I navigate to the "<subcategory>" browse page under "<category>"
    And I select a random member product
    And I select browse 'back' button
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for show previous button selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    Then  I navigate to the "<subcategory>" browse page under "<category>"
    And I filter the result set to show "All" items
    And I select a product from section '4' on thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same section
    When I navigate to top of page
    Then I verify that 'Show Previous Items' button is displayed at top of all product rows
    And I select 'Show Previous Items' button
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for sort by in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" browse page under "<category>"
    Then I select "<sort by option>" in sort by drop down
    Examples:
      | mode     | subcategory | category | sort by option     |
      | domestic | Jeans       | MEN      | Price: Low to High |
      | iship    | Sandals     | SHOES    | Best Sellers       |
      | registry | Electrics   | KITCHEN  | New Arrivals       |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Element tags for facet selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" browse page under "<category>"
    Then  I select random facet value from any facet section in browse page
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship - Verify Product view tags when navigated to pdp with Customer Ratings facet selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" browse page under "<category>"
    Then I verify that "Customer Ratings" facet is listed on left nav
    And I select the random value in the "Customer Ratings" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Product view tags when navigated to pdp with Brand facet selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" browse page under "<category>"
    Then I verify that "Brand" facet is listed on left nav
    And  I select the random value in the "Brand" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |


  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Product view tags when navigated to pdp with Price facet selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" browse page under "<category>"
    Then I verify that "Price" facet is listed on left nav
    And  I select the random value in the "Price" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify Product view tags when navigated to pdp with Color facet selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" browse page under "<category>"
    Then I verify that "Color" facet is listed on left nav
    And I select the random value in the "Color" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | subcategory  | category |
      | domestic | Jeans        | MEN      |
      | iship    | Sandals      | SHOES    |
      | registry | Table Linens | DINING   |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Domestic|Iship - Verify Product view tags when navigated to pdp with Size facet selection in category browse page
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" browse page under "<category>"
    Then I verify that "Size" facet is listed on left nav
    And  I select the random value in the "Size" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode     | subcategory | category |
      | domestic | Jeans       | MEN      |
      | iship    | Sandals     | SHOES    |
      | registry | Electrics   | KITCHEN  |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetric
  Scenario Outline: BrowsePage - Domestic - Verify link click tags for c2 media in category browse page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media                                                                                    |
      | 101      | IMAGE_MAP                                                                                |
      | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Registry - Verify link click tags for c2 media in category browse page
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media         |
      | 101      | AD            |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: BrowsePage - Iship - Verify link click tags for c2 media in category browse page
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to "Browse" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I verify media is clickable and navigating to respective pages
    Examples:
      | row_type | media                                                                                    |
      | 101      | IMAGE_MAP                                                                                |
      | 106      | CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON, CATEGORY_ICON |