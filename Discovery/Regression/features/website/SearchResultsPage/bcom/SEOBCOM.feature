#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page SEO in DOMESTIC, ISHIP and REGISTRY mode


  @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt @use_regression
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the copy block displayed on search results page all mode
    Given I am on SearchResultsPage for "<keyword>" in <Site_Mode> mode
    Then I verify that copy block is displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that copy block is not displayed
    Examples:
      | Site_Mode | keyword                |
      | Domestic  | converse kids sneakers |
      | Registry  | shirts                 |
      | Iship     | converse kids sneakers |