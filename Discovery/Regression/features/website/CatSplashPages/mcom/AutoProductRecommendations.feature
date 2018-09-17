########################################################################################################################
#B-49709 Autocomplete Product Recommendation
#B-63511: Autocomplete Product Recommendation - Two tier pricing changes Desktop 16O
#B-56822 && B-61463: Mcom :: Desktop :: Include promo badges in Autocomplete Product recommendation && Promotions display backend logic
#B-59618 : MCOM :: Search term doesn't persist after clicking on product
########################################################################################################################

Feature: Verify Auto complete Product Recommendation Panel Details

######################################## DOMESTIC MODE ########################################################

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @migrated_to_sdt @discovery_daily_run
  Scenario Outline: CatSplashPage - Verify search keyword persist on PDP page after selecting any APR product in DOMESTIC mode
    Given I visit the web site as a guest user
    And I set the "2132" cookie
    When I navigate to category splash page
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    When I mouse over on the random auto suggestion text
    Then I should see APR panel updated for different suggestion text
    And I select random APR and navigate to PDP
    Examples:
      | keyword_to_search |
      | Lenox             |
      | Gold              |

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @migrated_to_sdt
  Scenario Outline: CatSplashPage - Domestic - Verify autocomplete panel & APR is not displayed with few keyword combinations
    Given I visit the web site as a guest user
    And I set the "2132" cookie
    When I navigate to category splash page
    When I enter "<keyword_to_search>" keyword in search field
    Then I should not see autocomplete suggestions
    And I should not see APR panel with "Best Sellers" header and "4" APR at max
    Examples:
      | keyword_to_search                                            |
      | Lenox Women                                                  |
      | adidas juniors                                               |
      | Under $50                                                    |
      | $100-$250                                                    |
      | $10.50 - 15.00                                               |
      | Hotel Collection 525 Thread Count Egyptian Cotton Sheet Sets |
      | $#                                                           |
      | ---+=                                                        |
      | 86800                                                        |

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @wip
  Scenario Outline: CatSplashPage - Verify the products recommendations with two characters in search filed in DOMESTIC mode
    Given I visit the web site as a guest user
    And I set the "2132" cookie
    When I navigate to category splash page
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I should see product recommendations same in UI and Service
    And I should see the review ratings and price for recommended products on autocomplete panel
    And I should see the one tier and two tier prices for recommended products should be same on UI and SERVICE
    And I should see the review ratings for recommended products should be same on UI and SERVICE
    And Product price should be in red when onsale is true
    And I should see SELECT ITEMS ON SALE is displayed in UI when upcOnSale or memberProductOnSale set to True in service
    Examples:
      | keyword_to_search |
      | pa                |
      | pl                |

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @wip
  Scenario Outline: CatSplashPage - Verify the products recommendations with uppercase characters in search filed in DOMESTIC mode
    Given I visit the web site as a guest user
    And I set the "2132" cookie
    When I navigate to category splash page
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I should see product recommendations same in UI and Service
    And I should see the review ratings and price for recommended products on autocomplete panel
    And I should see the one tier and two tier prices for recommended products should be same on UI and SERVICE
    And I should see the review ratings for recommended products should be same on UI and SERVICE
    And Product price should be in red when onsale is true
    And I should see SELECT ITEMS ON SALE is displayed in UI when upcOnSale or memberProductOnSale set to True in service
    Examples:
      | keyword_to_search |
      | PANTS             |
      | DRESSES           |

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @wip
  Scenario Outline: CatSplashPage - Verify the products recommendations with complete search keyword in DOMESTIC mode
    Given I visit the web site as a guest user
    And I set the "2132" cookie
    When I navigate to category splash page
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I should see product recommendations same in UI and Service
    And I should see the review ratings and price for recommended products on autocomplete panel
    And I should see the one tier and two tier prices for recommended products should be same on UI and SERVICE
    And I should see the review ratings for recommended products should be same on UI and SERVICE
    And Product price should be in red when onsale is true
    And I should see SELECT ITEMS ON SALE is displayed in UI when upcOnSale or memberProductOnSale set to True in service
    Examples:
      | keyword_to_search |
      | Sneakers          |
      | Tunics            |
      | Lenox plates      |
      | Nike              |
      | Blue              |
      | Brown             |
      | Clinique          |
      | Belts             |

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @wip
  Scenario Outline: PDP page - Verify APR product selection & APR panel appearance on PDP page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I set the "2132" cookie
    When I navigate to category splash page
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I should see the review ratings and price for recommended products on autocomplete panel
    And I select random APR and navigate to PDP
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I should see product recommendations same in UI and Service
    And I should see the review ratings and price for recommended products on autocomplete panel
    And I should see the one tier and two tier prices for recommended products should be same on UI and SERVICE
    And I should see the review ratings for recommended products should be same on UI and SERVICE
    And Product price should be in red when onsale is true
    And I should see SELECT ITEMS ON SALE is displayed in UI when upcOnSale or memberProductOnSale set to True in service
    Examples:
      | keyword_to_search |
      | Dresses           |
      | Hoodies           |

  @domain_discovery @feature_catsplash @use_regression @mode_domestic @wip
  Scenario Outline: CatSplashPage - Verify promotional badges for recommendation products in autocomplete panel in DOMESTIC mode
    Given I visit the web site as a guest user
    And I set the "2132" cookie
    When I navigate to category splash page
    When I enter "<keyword_to_search>" keyword in search field
    Then I should see autocomplete suggestions
    And I should see APR panel with "Best Sellers" header and "4" APR at max
    And I should see product recommendations same in UI and Service
    And I should see the review ratings and price for recommended products on autocomplete panel
    And I should see one promo text at a max in APR zone in UI
    And I should see the Autocomplete Product Recommendation-promotional Badges in service and UI
    Examples:
      | keyword_to_search |
      | Jack Black        |
      | Jew               |

