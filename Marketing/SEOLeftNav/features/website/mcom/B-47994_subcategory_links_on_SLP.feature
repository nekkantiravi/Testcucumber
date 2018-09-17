# Author: SEO Auto Facet
# Date Created: 02/03/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:NavApp:Tech: SLP left navigation enhancement - Display links from Stella
# Versionone story number:: B-47994
#########################################################################################################################

Feature:  As an SEO manager, I would like to display the Sub-category links of a category id that is associated to SLP Category. Category id is defined in left navigation mediagroup in Astra for an SLP Category.

################################## SLP page - Site Mode ###################################################

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Subcategory links from STELLA should display on Left Nav of SLP Page
    Given I visit the web site as a guest user
    When I navigate the "Category_Link_Browse" SLP category which is under "71233" parent category
#    Then I should see subcategory links from ASTRA on left nav
    And I should see subcategory links from STELLA on left nav below the static links
    And I should see the links ordered in which they were entered in Stella
    And I should see the position of the sub-category links above clone category
    And I should see the position of the sub-category links above facets
#     If static links not present then the sub category links should be displayed between header and the facets
#    And I should see facets listed on left nav below the subcategory links
#    And I should see left nav struture same as browse page

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA, Facets in Left Nav and Left Nav is hidden
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Subcategory links from STELLA should not display on Left Nav of SLP Page, when Left Nav is hidden
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should not see static links from ASTRA on left nav
    And I should not see subcategory links from STELLA on left nav
    And I should not see facets listed on left nav
    And I should see empty left nav section

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Subcategory links from STELLA should display on Left Nav of SLP Page, when user perform the actions on SLP page
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Stella
    When I select "Price: High to Low" in sort by drop down
    Then I should see category links from STELLA on left nav
    When I select "4" Column Grid icon
    And  I filter the result set to show "40" items
    Then I should see category links from STELLA on left nav
    When I should be able to navigate using pagination functionality
    Then I should see category links from STELLA on left nav
    When I select any random facet in facet panel
    Then I should see category links from STELLA on left nav

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Subcategory links from STELLA should navigate to appropriate category links, when user selects the Subcategory links.
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Stella
    When I select subcategory links
    Then I should navigate to appropriate category links

  #pre condition :need a Subcategory link which has invalid category data associated to verify this Subcategory links scenario
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Subcategory link should not be displayed in Subcategory links, when invalid category data has been associated to subcategory link.
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I should see subcategory links from STELLA on left nav
    Then I should not see subcategory link which has invalid category data

  #pre condition :need a Subcategory link which has expired category data associated to verify this Subcategory links scenario
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Subcategory link should not be displayed in Subcategory links, when expired category data has been associated to subcategory link.
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I should see subcategory links from STELLA on left nav
    Then I should not see subcategory link which has expired category data


   ################################## SLP page - INTL Mode ###################################################

 #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav in INTL mode
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Subcategory links from STELLA should display on Left Nav of SLP Page
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
    #    Then I should see subcategory links from ASTRA on left nav
    And I should see subcategory links from STELLA on left nav below the static links
    And I should see the links ordered in which they were entered in Stella
    And I should see the position of the sub-category links above clone category
    And I should see the position of the sub-category links above facets
#     If static links not present then the sub category links should be displayed between header and the facets
#    And I should see facets listed on left nav below the subcategory links
#    And I should see left nav struture same as browse page

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA, Facets in Left Nav and Left Nav is hidden
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Subcategory links from STELLA should not display on Left Nav of SLP Page, when Left Nav is hidden in INTL mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should not see static links from ASTRA on left nav
    And I should not see subcategory links from STELLA on left nav
    And I should not see facets listed on left nav
    And I should see empty left nav section

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Subcategory links from STELLA should display on Left Nav of SLP Page, when user perform the actions on SLP page
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Stella
    When I select "Price: High to Low" in sort by drop down
    Then I should see category links from STELLA on left nav
    When I select "4" Column Grid icon
    And  I filter the result set to show "40" items
    Then I should see category links from STELLA on left nav
    When I should be able to navigate using pagination functionality
    Then I should see category links from STELLA on left nav
    When I select any random facet in facet panel
    Then I should see category links from STELLA on left nav

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Subcategory links from STELLA should navigate to appropriate category links, when user selects the Subcategory links.
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Stella
    When I select subcategory links
    Then I should navigate to appropriate category links

  #pre condition :need a Subcategory link which has invalid category data associated to verify this Subcategory links scenario
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Subcategory link should not be displayed in Subcategory links, when invalid category data has been associated to subcategory link.
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see subcategory links from STELLA on left nav
    And I should not see subcategory link which has invalid category data

  #pre condition :need a Subcategory link which has expired category data associated to verify this Subcategory links scenario
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Subcategory link should not be displayed in Subcategory links, when expired category data has been associated to subcategory link.
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    Then I should see subcategory links from STELLA on left nav
    And I should not see subcategory link which has expired category data

