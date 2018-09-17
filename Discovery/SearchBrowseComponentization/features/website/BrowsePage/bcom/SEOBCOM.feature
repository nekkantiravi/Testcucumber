#Author: Discovery QE	


Feature: Verify Home Page SEO in DOMESTIC, ISHIP and REGISTRY mode


  @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt @use_regression
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify that the copy block displayed on browse page all mode
    When I am on CategoryBrowsePage for "1000238" category id in <Site_Mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that copy block is displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that copy block is not displayed
    When I deselect the selected facet from the overlay
    Then I verify that copy block is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Iship     |