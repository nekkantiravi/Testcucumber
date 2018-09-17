# Author: SEO Link Optimization
# Date Created: 03/09/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::UI: Address multiple copy blocks on Facet Selection in SLP pages for SEO
# Versionone story number:: B-73111
#########################################################################################################################

Feature:  As an SEO Business Manager, I would like to address multiple copy blocks on a Facet selection in the MCOM SLP pages as applicable for SEO.

  # Data requirement : Set up multiple 2-3 copy blocks on some SLP Categories
###################################################### SLP Page - site mode #########################################################################################################
  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that when facet is selected multiple COPY Blocks in the SLP page should not be displayed in UI and view source in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate "Browse_Multiple_Copy_Blocks" SLP category which is under "71233" parent category with COPY_BLOCK media in one zero one row
    Then  I should see facets listed on left nav
    When I select any random facet in facet panel
    Then I should not see multiple copy blocks on the page in UI
    And I should not see copy block container in view page source
    And I refresh current page
    Then I should not see multiple copy blocks on the page in UI
    And I should not see copy block container in view page source


  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify multiple copyblocks in UI and view source when user unselects the selected facet values in SLP page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate "Browse_Multiple_Copy_Blocks" SLP category which is under "71233" parent category with COPY_BLOCK media in one zero one row
    Then  I should see facets listed on left nav
    When I select any random facet in facet panel
    Then I should not see multiple copy blocks on the page in UI
    And I should not see copy block container in view page source
    When I click Clear All link
    Then  I should see multiple copy blocks displayed in UI
    And I should see multiple copy blocks displayed higher up in the page view source same in the order of the UI
    And I refresh current page
    Then I should see multiple copy blocks on the page in UI
    And I should see multiple copy blocks displayed higher up in the page view source same in the order of the UI

    ###################################################### SLP Page - Iship mode #########################################################################################################
  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that when facet is selected multiple COPY Blocks in the SLP page should not be displayed in UI and view source in Iship mode
    Given I visit the web site as a guest user
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate "Browse_Multiple_Copy_Blocks" SLP category which is under "71233" parent category with COPY_BLOCK media in one zero one row
    Then  I should see facets listed on left nav
    When I select any random facet in facet panel
    Then I should not see multiple copy blocks on the page in UI
    And I should not see copy block container in view page source
    And I refresh current page
    Then I should not see multiple copy blocks on the page in UI
    And I should not see copy block container in view page source



  @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify multiple copyblocks in UI and view source when user unselects the selected facet values in SLP page in Iship mode
    Given I visit the web site as a guest user
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate "Browse_Multiple_Copy_Blocks" SLP category which is under "71233" parent category with COPY_BLOCK media in one zero one row
    Then  I should see facets listed on left nav
    When I select any random facet in facet panel
    Then I should not see multiple copy blocks on the page in UI
    And I should not see copy block container in view page source
    When I click Clear All link
    Then  I should see multiple copy blocks displayed in UI
    And I should see multiple copy blocks displayed higher up in the page view source same in the order of the UI
    And I refresh current page
    Then I should see multiple copy blocks on the page in UI
    And I should see multiple copy blocks displayed higher up in the page view source same in the order of the UI


