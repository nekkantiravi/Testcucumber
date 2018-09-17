Feature: Verify browser back button verification on Category Browse Page

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Verify LEFT NAV LINKS are displayed in All modes
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I verify that search result page Facets displayed match with production
    Examples:
      | shopping_mode | Category_Name | FOB     |
      | Domestic      | Shorts        | Men     |
      | Registry      | Blenders      | Kitchen |
      | Iship         | Boots         | Shoes   |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page @wip
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify that we recognize scripts and do not submit a search in such cases
    Given I am on CategoryBrowsePage for "jeans" under "Men" in Domestic mode
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "<script>alert('hi there')</script>" in bops facet overlay
    Then I verify that "Sorry, we could not process your request. Please try again later." error message is displayed under bops change store dialog

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_browse_page
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify Brand facet recognize scripts and do not submit a search in such cases
    Given I am on CategoryBrowsePage for "<Category_Name>" under "<FOB>" in <shopping_mode> mode
    And I verify that "Brand" facet is listed on left nav
    And I type a character "<script>alert('hi there')</script>" in brand search box
    Then I verify that error message 'No brands match your search.' is displayed below search box
    Examples:
     | shopping_mode |Category_Name   |FOB        |
     | Domestic      |Shorts          |Men        |
     | Registry      |Sheets          |BED & BATH |
     | Iship         |Boots           |Shoes      |