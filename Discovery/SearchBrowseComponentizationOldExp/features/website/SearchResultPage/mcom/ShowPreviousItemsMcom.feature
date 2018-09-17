#Author: Discovery QE
#Date Created: 10/31/2016

Feature: Verify Show Previous Items functionality in Search Results Page contents in DOMESTIC, ISHIP and REGISTRY mode

###############################################################################################################
# Show Previous Items button functionality with 'All' item count option.
###############################################################################################################

  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify 'Show Previous Items' button with 'All' item count option in 4 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "Cookware" in <shopping_mode> mode
    And I verify that item count buttons in page
    When I select "4" Column Grid icon
    And I filter the result set to show "All" items
    And I select a product from section '4' on thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same section
    When I navigate to top of page
    Then I verify that 'Show Previous Items' button is displayed at top of all product rows
    When I select 'Show Previous Items' button
    Then I verify that previous section of products are loaded on thumbnail grid
    Then I verify that 'Show Previous Items' button is displayed at top of all product rows
    When I select 'Show Previous Items' button
    Then I verify that previous section of products are loaded on thumbnail grid
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |


  @use_regression @feature_bgv @artifact_navapp @domain_discovery @bgv_2 @release_15H @mode_domestic @priority_medium @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify 'Show Previous Items' button with 'All' item count option in 3 column grid view in DOMESTIC mode
    Given I am on SearchResultsPage for "Cookware" in <shopping_mode> mode
    And I verify that item count buttons in page
    When I select "3" Column Grid icon
    And I filter the result set to show "All" items
    And I select a product from section '4' on thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same section
    When I navigate to top of page
    Then I verify that 'Show Previous Items' button is displayed at top of all product rows
    When I select 'Show Previous Items' button
    Then I verify that previous section of products are loaded on thumbnail grid
    Then I verify that 'Show Previous Items' button is displayed at top of all product rows
    When I select 'Show Previous Items' button
    Then I verify that previous section of products are loaded on thumbnail grid
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
