Feature: Grid column & Items per page verification on Search Landing Page

  
  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '4 column' grid
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select "4" Column Grid icon
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous grid view selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |
 # Notes:
 # Verify facet selection persists when we navigate back from PDP page

  @domain_discovery @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '3 column' grid
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select "3" Column Grid icon
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous grid view selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |
 # Notes:
 # Verify facet selection persists when we navigate back from PDP page

  @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '120' item count
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select "3" Column Grid icon
    And I filter the result set to show "120" items
    And I select multiple facet value from "Pick Up In Store" facet section
    Then I verify that previous store facet removed and applied new store facet
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |

  @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '40' item count
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select "4" Column Grid icon
    And  I filter the result set to show "40" items
    When I select multiple facet value from "Color" facet section
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      |shopping_mode|keyword    |
      |Domestic     |Jeans      |
      |Registry     |Plates     |
      |Iship        |Jeans      |

  @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '100' item count
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I select "4" Column Grid icon
    And  I filter the result set to show "100" items
    When I select multiple facet value from "Brand" facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      |shopping_mode|keyword    |
      |Domestic     |Jeans      |
      |Registry     |Plates     |
      |Iship        |Jeans      |


  @mode_Domestic @mode_Registry @mode_Iship @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with '60' item count
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I select "3" Column Grid icon
    And  I filter the result set to show "60" items
    And I select multiple facet value from "Pick Up In Store" facet section
    Then I verify that previous store facet removed and applied new store facet
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |