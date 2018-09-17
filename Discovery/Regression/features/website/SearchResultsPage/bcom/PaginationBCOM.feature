Feature: Pagination verification on Search Landing Page

  @domain_discovery @high @project_snb @snbc_comp @use_regression @migrated_to_sdt
  Scenario: SearchResultsPage - Verify pagination in DOMESTIC mode
    Given I am on SearchResultsPage for "shoes" in Domestic mode
    Then I verify that pagination works

  @use_regression @domain_discovery @project_snb @mode_domestic @priority_medium @snbc_comp @migrated_to_sdt
  Scenario: SearchResultsPage - Verify Without Pagination in DOMESTIC mode
    Given I am on SearchResultsPage for "maker" in Domestic mode
    Then I should be in Search Landing page
    And I verify that pagination is not displayed

  @domain_discovery @priority_medium @mode_domestic @project_snb @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Verify Pagination using Next / Previous button in all mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    Examples:
      | shopping_mode  |keyword |
      | Domestic       |dress   |
      | Registry       |bedding |
      | Iship          |dress   |

  @use_regression @registry @priority_high @mode_registry @project_snb @snbc_comp @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Verify Pagination and products using Next / Previous button in all Mode
    Given I am on SearchResultsPage for "bedding" in <shopping_mode> mode
    And I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Search Landing page
    Examples:
      | shopping_mode  |
      | Domestic       |
      | Registry       |
      | Iship          |