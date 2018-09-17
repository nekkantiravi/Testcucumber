Feature: To verify coremetric tags in dlp page

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Page view tags for dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Product view tags for dlp page member product
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for back to top button in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And  I select 'back to top' button
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for sort by in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I select "<sort by option>" in sort by drop down
    Examples:
      | mode_name | brand           | sort by option     |
      | domestic  | Armani          | Price: Low to High |
      | iship     | EFFY Collection | Best Sellers       |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I select 'single' facet value from 'any' facet section
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Product view tags when navigated to pdp with Customer Ratings facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Customer Ratings" facet is listed on left nav
    And I select the random value in the "Customer Ratings" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Product view tags when navigated to pdp with Price facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Price" facet is listed on left nav
    And   I select the random value in the "Price" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Product view tags when navigated to pdp with Color facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Color" facet is listed on left nav
    And I select the random value in the "Color" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode_name | brand  |
      | domestic  | Armani |
      | iship     | Armani |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Product view tags when navigated to pdp with Size facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Size" facet is listed on left nav
    And  I select the random value in the "Size" facet
    And I select a random member product
    Then I should be redirected to PDP page
    Examples:
      | mode_name | brand                      |
      | domestic  | Armani                     |
      | iship     | INC International Concepts |


  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for 3 column grid selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    And I select "3" Column Grid icon
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for 4 column grid selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    And  I select "4" Column Grid icon
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Page view tags for grid persistance in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I select a random member product
    And I select browse 'back' button
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for show previous button selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I filter the result set to show "All" items
    And I select a product from section '4' on thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    And I navigate to top of page
    Then I verify that 'Show Previous Items' button is displayed at top of all product rows
    And I select 'Show Previous Items' button
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for Customer Ratings facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Customer Ratings" facet is listed on left nav
    And  I select the random value in the "Customer Ratings" facet
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for Price facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Price" facet is listed on left nav
    And  I select the random value in the "Price" facet
    Examples:
      | mode_name | brand           |
      | domestic  | Armani          |
      | iship     | EFFY Collection |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for Color facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Color" facet is listed on left nav
    And I select the random value in the "Color" facet
    Examples:
      | mode_name | brand  |
      | domestic  | Armani |
      | iship     | Armani |

  @use_regression @feature_coremetrics @domain_discovery @use_coremetrics
  Scenario Outline: DlpPage - Domestic|Iship - Verify Element tags for Size facet selection in dlp page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When  I navigate to brand index page in "<mode_name>" mode
    Then  I navigate to "<brand>" dlp page
    Then I verify that "Size" facet is listed on left nav
    And  I select the random value in the "Size" facet
    Examples:
      | mode_name | brand                      |
      | domestic  | Armani                     |
      | iship     | INC International Concepts |
