 #Author: Discovery QE

 Feature: Verifying Item Type Facets in Search Results Page

   @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
   Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet breadcrumb for item type facet selection in all modes
     Given I am on SearchResultsPage for "Kitchen Gadgets & Tools" in <Site_Mode> mode
     And I clear existing class variable data to avoid data issues
     When I select "single" facet value from "Item Type" facet section
     Then I verify that products are filtered as per selected facet values
     And I verify that applied facet value is displayed
     When I select another facet value from "Item Type" facet
     Then I verify that products are filtered as per selected facet values
     And I verify that applied facet value is displayed
     Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |
   # Notes:
   # Select single facet from item type facet section.
   # Verify facet breadcrumb with selected facet and 'CLEAR ALL' button not displayed.
   # Verify products are updated as per selected facet values.
   # Select another facet from item type facet section.
   # Verify facet breadcrumb with two selected facets and 'CLEAR ALL' button displayed.
   # Verify products are updated as per selected facet values.

   @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
   Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence for item type facet selection in all modes
     Given I am on SearchResultsPage for "Kitchen Gadgets & Tools" in <Site_Mode> mode
     And I clear existing class variable data to avoid data issues
     When I select "single" facet value from "Item Type" facet section
     Then I verify that products are filtered as per selected facet value
     When I select random product from thumbnail grid
     Then I verify that landed on respective product display page
     When I select browse 'back' button
     Then I verify that previously selected facets persists in breadcrumb
     When I select another facet value from "Item Type" facet
     Then I verify that products are filtered as per selected facet value
     When I select random product from thumbnail grid
     Then I verify that landed on respective product display page
     When I select browse 'back' button
     Then I verify that previously selected facets persists in breadcrumb
     Examples:
       | Site_Mode |
       | Domestic  |
       | Registry  |
       | Iship     |
   # Notes:
   # Select single facet from item type facet
   # Select any product from thumbnail grid and navigate back from pdp to results page
   # Verify selected item type facet is persisted on results page and in breadcrumb and also in URL.
   # Select another facet from item type facet
   # Select any product from thumbnail grid and navigate back from pdp to results page
   # Verify two selected item type facets are persisted on results page and in breadcrumb and also in URL.

   @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt
   Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet persistence after sot by and pagination for item type facet selection
     Given I am on SearchResultsPage for "Kitchen Gadgets & Tools" in <Site_Mode> mode
     And I clear existing class variable data to avoid data issues
     When I select "multiple" facet value from "Item Type" facet section
     Then I verify that products are filtered as per selected facet value
     When I select "New Arrivals" in sort by drop down
     Then I verify that the product count is displayed
     And I verify that the Sort By "New Arrivals" functionality
     And I verify that previously selected facets persists in breadcrumb
     When I select 'random' page number from pagination
     Then I verify that products are filtered as per selected facet values
     When I select random product from thumbnail grid
     Then I verify that landed on respective product display page
     When I select browse 'back' button
     Then I verify that landed on SearchResultsPage on same product grid point
     And I navigate to top of page
     Then I verify that previously selected facets persists in breadcrumb
     And I verify that previous pagination selection persist
     Examples:
       | Site_Mode     |
       | Domestic      |
       | Registry      |
       | Iship         |
   # Notes:
   # Select single/multiple item type facet from facet section
   # Select any sort by option
   # Verify selected item type facets are persisted after sort by also.
   # Select pagination next arrow.
   # Verify selected item type facets are persisted after pagination also.
   # Select any product from thumbnail grid.
   # Select browser back button
   # Verify selected item type, sort by and pagination options are persisted.


   #below scenarios are invalid because once any facet value in item_type is selected then item type facet will not
   #be displayed on the page so we cant select multiple facet or we cant verify that selected facet value is displayed inside facet

   @domain_discovery @priority_high @mode_domestic @migrated_to_sdt @mode_registry @mode_iship @use_regression
   Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that facet deselection from item type facet
     Given I am on SearchResultsPage for "dinning" in <Site_Mode> mode
     And I clear existing class variable data to avoid data issues
     When I select "single" facet value from "Item Type" facet section
     Then I verify that products are filtered as per selected facet value
     And I verify that the product count is updated
     And I verify that previously selected facets persists in breadcrumb
     When I remove the selected facet from the breadcrumb
     Then I verify that the product count returns to original
     When I select "single" facet value from "Item Type" facet section
     Then I verify that products are filtered as per selected facet value
     And I verify that the product count is updated
     When I deselect the selected facet from the overlay
     Then I verify that the product count returns to original
     When I select "multiple" facet value from "Item Type" facet section
     Then I verify that products are filtered as per selected facet value
     And I verify that the product count is updated
     When I click on clear all button
     Then I verify that the product count returns to original
     Examples:
       | Site_Mode     |
       | Domestic      |
       | Registry      |
       | Iship         |

   @domain_discovery @priority_high @mode_domestic @migrated_to_sdt @mode_registry @mode_iship @use_regression
   Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that selected facets are separated in facet panel for item type facet selection
     Given I am on SearchResultsPage for "Kitchen Gadgets & Tools" in <Site_Mode> mode
     And I clear existing class variable data to avoid data issues
     When I select "single" facet value from "Item Type" facet section
     Then I verify that products are filtered as per selected facet values
     And I verify that the selected Item Type appears on top
     Examples:
       | Site_Mode |
       | Domestic  |
       | Registry  |
       | Iship     |
    # Notes:
    # Select single item type facet from item type facet section
    # Verify that selected item type facets are displayed separately under facet panel.