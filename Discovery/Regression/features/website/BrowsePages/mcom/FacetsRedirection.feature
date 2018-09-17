#Domain: Discovery

Feature: As a producer I would like to make sure that the category pages are not stripping facets when being redirected.

  @use_regression @domain_discovery @mode_domestic @priority_medium @snbc_comp
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify selected facet values should persist when we update portion of URL in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that previously selected facets persists in breadcrumb
    When I alter and navigate to the current URL
    Then I verify that previously selected facets persists in breadcrumb
  Examples:
  | shopping_mode | Category_id |
  | Domestic      | 5449        |
  | Registry      | 17799       |
  | Iship         | 5449        |
