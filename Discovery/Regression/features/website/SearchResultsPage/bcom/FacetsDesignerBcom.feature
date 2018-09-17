Feature: Facet Brand verification on Search Landing Page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filtering products with brand facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | plates  |
      | Iship         | jackets |

   @domain_discovery @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify error message display with brand facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
     And I clear existing class variable data to avoid data issues
    When I search for "unavailable brand" keyword in brand facet section
    Then I verify that error message 'We couldn't find a match.' is displayed below search box
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | plates  |
      | Iship         | jackets |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with special characters facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I search for "<brand_name>" keyword in brand facet section
    And I select "<brand_name>" facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      |shopping_mode|keyword   |brand_name   |
      |Domestic     |eyes      | Estée Lauder|
      |Registry     |Plates    | Nambé       |
      |Iship        |Plates    | Nambé       |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify search box in brand facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I search for 'available brand' keyword in brand facet section
    And I select "available brand" facet value from Brand facet section
    And I remove the selected facet from the breadcrumb
    Then I verify that search box is displayed under Brand facet
    Then I verify that brand search box is empty
    When I search for 'available brand' keyword in brand facet section
    Then I verify that brands are filtered with entered search term or characters
    Examples:
      | shopping_mode |keyword    |
      | Domestic      |dresses    |
      | Registry      |Plates     |
      | Iship         |dresses    |

 @unifiednavigation_15947 @use_regression @domain_discovery @priority_medium @migrated_to_sdt @mode_domestic @use_regression @snbc_comp
  Scenario: SearchResultsPage - Domestic - Verify availability of search box under designer facet in search landing page in DOMESTIC mode
    Given I am on SearchResultsPage for "photo frames" in Domestic mode
   And I clear existing class variable data to avoid data issues
    Then I should be in Search Landing page
    And I should see "DESIGNER" facet listed on left nav
    Then I verify that search box is displayed under Brand facet

   @unifiednavigation_15947 @use_regression @domain_discovery @priority_medium @mode_registry @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage -Domestic|Iship|Registry - Verify availability of search box under designer facet in search landing page when no of brands is 15 or less than 15 in All mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
     And I clear existing class variable data to avoid data issues
    Then I should be in Search Landing page
    And I should see "Designer" facet listed on left nav
    And I verify that the "Designer" facet values less than 15
    Then I verify that search box is displayed under Brand facet
    Examples:
      | shopping_mode | keyword     |
      | Domestic      |green knife  |
      | Registry      | green knife |
      | Iship         | green knife |

  @unifiednavigation_15947 @use_regression @domain_discovery @priority_high  @mode_domestic @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify availability of search box under designer facet in search landing page when no of brands is more than 15 in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I should be in Search Landing page
    And I should see "DESIGNER" facet listed on left nav
    And I verify that the "Designer" facet values more than 15
    And I verify that search box is displayed under Brand facet
    And I verify that see default message "Find a Designer" in "Designer" facet
    When I type a character "m" in brand search box
    And I delete all characters from designer search box
    Then I verify that see default message "Find a Designer" in "Designer" facet
    Examples:
      | shopping_mode | keyword  |
      | Domestic      | jeans    |
      | Registry      | bags |
      | Iship         | jeans    |

   @unifiednavigation_15947 @use_regression @domain_discovery @priority_medium @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify error message is displayed when there are no designer facets for the entered term in search page in All mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
     And I clear existing class variable data to avoid data issues
    Then I should be in Search Landing page
    Then I should see "Designer" facet listed on left nav
    And I verify that the "Designer" facet values more than 15
    And I verify that search box is displayed under Brand facet
    When I type a character "xtyz" in brand search box
    Then I verify that error message 'We couldn't find a match.' is displayed below search box
    Examples:
      | shopping_mode | keyword  |
      | Domestic      | jeans   |
      | Registry      | bags |
      | Iship         | jeans    |

  @unifiednavigation_15947 @use_regression @domain_discovery @priority_medium @mode_domestic @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify only the designers that match with the entered character combination are displayed in search page in All mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I should be in Search Landing page
    And I should see "Designer" facet listed on left nav
    And I verify that the "Designer" facet values more than 15
    And I verify that search box is displayed under Brand facet
    When I type a character "<Brand_keyword>" in brand search box
    Then I verify that facet values are assorted as per the text entered in brand search box
    Examples:
      | shopping_mode | keyword  |Brand_keyword |
      | Domestic      | jeans    | ma           |
      | Registry      | bags     | br           |
      | Iship         | jeans    | ma           |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet breadcrumb for designer facet selection in all modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Designer" facet section
    Then I verify that products are filtered as per selected facet value
    When I select another facet value from "Designer" facet
    Then I verify that products are filtered as per selected facet values
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode | keyword  |
      | Domestic      | jeans    |
      | Registry      | rings |
      | Iship         | jeans    |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence for designer facet selection in all modes
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Department" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that products are filtered as per selected facet value
    When I select another facet value from "Designer" facet
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | shopping_mode | keyword  |
      | Domestic      | jeans    |
      | Registry      | rings |
      | Iship         | jeans    |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that selected facets are separated in facet panel for designer facet selection
    Given I am on SearchResultsPage for "cookware" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Brand" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that selected facets are separated from inactive facets
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence after sort by and pagination for designer facet selection
    Given I am on SearchResultsPage for "bags" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Designer" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random option in sort by drop down
    Then I verify that the product count is displayed
    And I verify that products are updated with selected sort option
    And I verify that previously selected facets persists in breadcrumb
    When I select 'random' page number from pagination
    Then I verify that products are filtered as per selected facet values
    And I verify that previously selected facets persists in breadcrumb
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous pagination selection persist
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet deselection from designer/brand facet
    Given I am on SearchResultsPage for "knife" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Designer" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select "single" facet value from "Designer" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I select "multiple" facet value from "Designer" facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that previously selected facets persists in breadcrumb
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | shopping_mode |
      | Domestic  |
      | Registry  |
      | Iship     |