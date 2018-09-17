# Author: SEO Team
# Date Created: 03/16/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM : SLP : QE Only : Back log Automation for SLP pages.
# Version One story numbers:: B-63715
#########################################################################################################################

Feature: As a QE, We would like to update the Automation on SLP pages to the new Java SDT framework

  ###################################################### SLP Domestic Mode #########################################################################################################
  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify the SORT functionality on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate to SLP browse category page
    And I select "Price: High to Low" in sort by drop down
    Then I verify product display order for "Price: High to Low" option
    
  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify the Show options functionality for column 4 on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "4" Column Grid icon
    And I select "40" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "100" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "All" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify the Show options functionality for column 3 on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "3" Column Grid icon
    And I select "60" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "120" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "All" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify the Pagination functionality for 4 columns on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "4" Column Grid icon
    And I select "40" item count option
    And I navigate to next page of thumbnail grid
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify the Pagination functionality for 3 columns on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "3" Column Grid icon
    And I select "60" item count option
    And I navigate to next page of thumbnail grid
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify selects a facet and see if the results are filtered on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I select any random facet in facet panel
    Then I should see product assortment is updated after "selected"
    When I deselect all facet values in facet panel
    Then I should see product assortment is updated after "deselectAll"

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Select Clear all facet values selections at once  on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    When I select any random facet in facet panel
    Then I should see clear all button displayed on facet panel
    And I should see product assortment is updated after "selected"
    When I deselect all facet values in facet panel
    Then I should see facet values from all facets are removed
    And I should see product assortment is updated after "deselectAll"
    And I should see clear all button is removed
    When I select two facet values in facet panel
    Then I should see clear all button displayed on facet panel
    When I deselect all facet values in facet panel
    Then I should see facet values from all facets are removed
    And I should see product assortment is updated after "deselectAll"
    And I should see clear all button is removed

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify display of Copy Block based on selection and removal of facet values on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select any random facet in facet panel
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select two facet values in facet panel
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see "COPY_BLOCK" on the page in "101,0" rows

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify facets display and behavior using clear and breadcrumb removal on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I select two facet values in facet panel
    And I should see product assortment is updated after "selected"
    And I should see selected facets in breadcrumb
    And I should see clear all button displayed on facet panel
    And I click Clear link for any facet type
    Then I should see that facet values get unselected and removed from breadcrumb
    And I should see product assortment is updated after "deselectOne"
    When I close Breadcrumb of any selected facet
    Then I should see that facet values get unselected and removed from breadcrumb
    And I should see product assortment is updated after "deselectAll"

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify facets display and behavior using clear all on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I select two facet values in facet panel
    Then I should see product assortment is updated after "selected"
    And I should see Breadcrumbs of all selected facets
    And I should see clear all button displayed on facet panel
    When I deselect all facet values in facet panel
    Then I should see Breadcrumbs are removed from pagination section
    And I should see that facet values are removed from breadcrumb
    And I should see facet values from all facets are removed
    And I should see product assortment is updated after "deselectAll"

   @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Selecting multiple facet values results in URL with all facet values on SLP page in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select multiple values of a single facet from the left nav
    And I select any random facet in facet panel
    Then I should see Breadcrumbs of all selected facets
    And I should see resulting url with all selected facet values
   Then I verify that facet breadcrumb are listed as per faceted url
    And The canonical tag should have single facet value for each facet selected

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify rel="canonical" tag when user selected a single facet(crawlable) on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select one facet value in facet panel with crawlable facets
    Then I should see selected facet value in canonical tag

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify the title tag and Page title when single facet value is selected from same facet type on SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select one facet value in facet panel with crawlable facets
    Then I should see every facet value selected is included in the view source title tag

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression
  Scenario: Verify that rel tag should display as <rel="alternate"> in view page source of SLP pages in Domestic mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select one facet value in facet panel with crawlable facets
    Then I should see every facet value selected is included in the view source title tag
    And I should see rel tag with alternate in view page source

###################################################### SLP International Mode #########################################################################################################
  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify the SORT functionality on SLP pages in International  mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I select "Price: High to Low" in sort by drop down
    Then I verify product display order for "Price: High to Low" option

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify the Show options functionality for column 4 on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "4" Column Grid icon
    And I select "40" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "100" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "All" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify the Show options functionality for column 3 on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "3" Column Grid icon
    And I select "60" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "120" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location
    When I select "All" item count option
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page
    When I select browser back button on pdp page
    Then I should be navigated back to "Browse" page and same product location

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify the Pagination functionality for 4 columns on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "4" Column Grid icon
    And I select "40" item count option
    And I navigate to next page of thumbnail grid
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify the Pagination functionality for 3 columns on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see item count buttons in page
    When I select "3" Column Grid icon
    And I select "60" item count option
    And I navigate to next page of thumbnail grid
    And I select any product from thumbnail grid
    Then I should be navigated to respective product display page

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify selects a facet and see if the results are filtered on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I select any random facet in facet panel
    Then I should see product assortment is updated after "selected"
    And I should see check mark replaces the check box for the selected facet value
    When I deselect all facet values in facet panel
    Then I should see product assortment is updated after "deselectAll"

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Select Clear all facet values selections at once  on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    When I select any random facet in facet panel
    Then I should see clear all button displayed on facet panel
    And I should see product assortment is updated after "selected"
    When I deselect all facet values in facet panel
    Then I should see facet values from all facets are removed
    And I should see product assortment is updated after "deselectAll"
    And I should see clear all button is removed
    When I select two facet values in facet panel
    Then I should see clear all button displayed on facet panel
    When I deselect all facet values in facet panel
    Then I should see facet values from all facets are removed
    And I should see product assortment is updated after "deselectAll"
    And I should see clear all button is removed

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify display of Copy Block based on selection and removal of facet values on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select any random facet in facet panel
    Then I should see Copy Block "removed"
    When I click Clear link for any facet type
    Then I should see "COPY_BLOCK" on the page in "101,0" rows
    When I select two facet values in facet panel
    Then I should see Copy Block "removed"
    When I deselect all facet values in facet panel
    Then I should see Copy Block "removed"

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify facets display and behavior using clear and breadcrumb removal on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I select two facet values in facet panel
    And I should see product assortment is updated after "selected"
    And I should see Breadcrumbs of all selected facets
    And I should see clear all button displayed on facet panel
    And I click Clear link for any facet type
    Then I should see that facet values get unselected and removed from breadcrumb
    And I should see product assortment is updated after "deselectone"
    When I close Breadcrumb of any selected facet
    Then I should see that facet values get unselected and removed from breadcrumb
    And I should see product assortment is updated after "deselectAll"

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify facets display and behavior using clear all on SLP pages in International mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I select two facet values in facet panel
    Then I should see product assortment is updated after "selected"
    And I should see Breadcrumbs of all selected facets
    And I should see clear all button displayed on facet panel
    When I deselect all facet values in facet panel
    Then I should see Breadcrumbs are removed from pagination section
    And I should see that facet values get unselected and removed from breadcrumb
    And I should see facet values from all facets are removed

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Selecting multiple facet values results in URL with all facet values on SLP page in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select multiple values of a single facet from the left nav
    And I select any random facet in facet panel
    Then I should see Breadcrumbs of all selected facets
    And I should see resulting url with all selected facet values
    And URL should be in ".com/shop/b/brand,color/facetvalue1|facetValue2,FacetValue?id=" format after selecting facets
    And The canonical tag should have single facet value for each facet selected

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify rel="canonical" tag when user selected a single facet(crawlable) on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select one facet value in facet panel with crawlable facets
    Then I should see selected facet value in canonical tag

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify the title tag and Page title when single facet value is selected from same facet type on SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select one facet value in facet panel with crawlable facets
    Then I should see every facet value selected is included in the view source title tag

  @domain_marketing @artifact_navapp @project_SLP_Phase2 @priority_high @release_16T @slp_backlog @use_regression @manual
  Scenario: Verify that rel tag should display as <rel="alternate"> in view page source of SLP pages in International mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see facets listed on left nav
    When I select one facet value in facet panel with crawlable facets
    Then I should see every facet value selected is included in the view source title tag
    And I should see rel tag with alternate in view page source