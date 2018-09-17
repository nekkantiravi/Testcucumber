# Author: SEO Optimization Regression
# Date Created: 10/24/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: QE Only : Add Automation for SEO MeW Regression
# Versionone story numbers::B-98674
########################################################################################################################################################
Feature: In this, we will address all the SEO functionality Automation.
####################################### MEW - Browse page - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the page title functionality in Browse page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should see browser page title matches with Stella page title
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should see selected facet value "Black" in title tag
    And I click filter of search and browse page
    When I select facet name "Price"
    And I select facet value "$50-$100"
    And I click on Apply button
    Then I should see browser page title does not contain facet value "$50-$100"

  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the copy block functionality in Browse page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Shoes   |
      | Women's Shoes |
      | Boots    |
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I select 2 page on browse page using mobile website
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the link module functionality in Browse page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    Then I should see the tag cloud above the footer in the navigated page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page
    And I should see keywords in the seo tag cloud section one after the another in the navigated page
    And  I should see six links in the tag cloud in the navigated page
    And I should see all the keywords are underlined in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

     #################################### SLP page - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the page title functionality in SLP page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see browser page title matches with Stella page title
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should see selected facet value "Black" in title tag
    And I click filter of search and browse page
    When I select facet name "Price"
    And I select facet value "$50-$100"
    And I click on Apply button
    Then I should see browser page title does not contain facet value "$50-$100"

  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the copy block functionality in SLP page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I select 2 page on browse page using mobile website
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the link module functionality in SLP page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the "COPY_BLOCK_ALWAYS_EXPAND_MEW_TRUE" SLP category
    Then I should see the tag cloud above the footer in the navigated page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page
    And I should see keywords in the seo tag cloud section one after the another in the navigated page
    And  I should see six links in the tag cloud in the navigated page
    And I should see all the keywords are underlined in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed

    #################################### Search page - Site Mode ##################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the copy block functionality in Onsite search page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "August hats"
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI

    #################################### DLP page - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the copy block functionality in DLP page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigates DLP page through Brand in mobile site
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI

    #################################### Subsplash page - Site Mode ##################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the copy block functionality in Subsplash page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      |Shop|
      |Men|
      |Activewear   |
    Then I should see copyblock displayed in UI
    And  I should see copy blocks displayed higher up in the page view source
    When I refresh current page
    Then I should see copy blocks displayed higher up in the page view source
    When I click filter of search and browse page
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    Then I should not see copy block on the page
    When I deselect all facet values in facet panel
    Then I should see copyblock displayed in UI
    When I select 2 page on browse page using mobile website
    Then I should not see copy block on the page
    When I click first page in pagination
    Then I should see copyblock displayed in UI
    And I select "Price: High to Low" in sort by drop down in mew
    Then I should not see copy block on the page
    When I click on the list view button in the toggle panel
    Then I should see copyblock displayed in UI

  @domain_marketing @artifact_MeW2.0 @project_SEO_Regression @release_17W @priority_high @artifact_xapi
  Scenario: Verify the link module functionality in Subsplash page in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      |Shop|
      |Men|
      |Activewear   |
    Then I should see the tag cloud above the footer in the navigated page
    And I should see tag cloud title font size is eighteen pixels in the navigated page
    And I should see tag cloud title font weight is Bold in the navigated page
    And I should see keywords in the seo tag cloud section one after the another in the navigated page
    And I should see six links in the tag cloud in the navigated page
    And I should see all the keywords are underlined in the navigated page
    And I should see tag cloud links font size is 14px in the navigated page
    When I click on any keyword in the link module in the navigated page
    Then I should see the correct page is displayed