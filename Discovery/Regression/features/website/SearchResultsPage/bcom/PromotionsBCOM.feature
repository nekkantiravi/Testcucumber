#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Search Results Page Promotions in DOMESTIC, ISHIP and REGISTRY mode

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the promotions on thumbnail in all mode
    Given I am on SearchResultsPage for "socks" in <Site_Mode> mode
    When I select a product having badge text
    Then I verify that promotional message is displayed under product thumbnail
    Examples:
      | Site_Mode |
      | Domestic  |
     #| Registry  | no promotion is displayed for registry need test data
      | Iship     |