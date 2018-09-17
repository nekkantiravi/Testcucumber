# Author: SEO Auto Facet
# Date Created: 02/03/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:NavApp:Tech: SLP left navigation enhancement - Display links from Astra
# Versionone story number:: B-47725
#########################################################################################################################

Feature:  As an SEO manager, I would like to display the static links of an SLP category defined in left navigation mediagroup from Astra. (IMPACTS: SITE)

################################## SLP page - Site Mode ###################################################

  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Static links from ASTRA should display on Left Nav of SLP Page
    Given I visit the web site as a guest user
    When I navigate the "Static_Browse" SLP category which is under "71233" parent category
#    Then I should see static links from ASTRA on left nav
    Then I should see the links ordered in which they were entered in Astra
    And I should see the position of the static links above sub-category links
    And I should see the position of the static links above clone category links
#    # If sub category links not present then the Static links should be displayed above the facets
#    And I should see subcategory links from STELLA on left nav below the static links
    And I should see facets listed on left nav below the static links
#   And I should see left nav struture same as browse page

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA, Facets in Left Nav and Left Nav is hidden
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Static links from ASTRA should not display on Left Nav of SLP Page, when Left Nav is hidden
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should not see static links from ASTRA on left nav
    And I should not see subcategory links from STELLA on left nav
    And I should not see facets listed on left nav
    And I should see empty left nav section
#
  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Static links from ASTRA should display on Left Nav of SLP Page, when perform the actions on SLP page
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Astra
    When I select "Price: High to Low" in sort by drop down
    Then I should see static links from ASTRA on left nav
    When I select "4" Column Grid icon
    And  I filter the result set to show "40" items
    Then I should see static links from ASTRA on left nav
    When I should be able to navigate using pagination functionality
    Then I should see static links from ASTRA on left nav
    When I select any random facet in facet panel
    Then I should see static links from ASTRA on left nav
#
  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Static links from ASTRA should navigate to appropriate static links, when user selects the static links.
    Given I visit the web site as a guest user
    When I navigate the "Static_Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Astra
    When I select static links
    Then I should navigate to appropriate static links
#
#   ################################## SLP page - INTL Mode ###################################################
#
  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Static links from ASTRA should display on Left Nav of SLP Page in INTL mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
   #    Then I should see static links from ASTRA on left nav
    Then I should see the links ordered in which they were entered in Astra
    And I should see the position of the static links above sub-category links
    And I should see the position of the static links above clone category links
#    # If sub category links not present then the Static links should be displayed above the facets
#    And I should see subcategory links from STELLA on left nav below the static links
    And I should see facets listed on left nav below the static links
#   And I should see left nav struture same as browse page


  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA, Facets in Left Nav and Left Nav is hidden
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H @use_manual
  Scenario: Verify that Static links from ASTRA should not display on Left Nav of SLP Page, when Left Nav is hidden in INTL mode
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
  Scenario: Verify that Static links from ASTRA should display on Left Nav of SLP Page, when perform the actions on SLP page in INTL mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Astra
    When I select "Price: High to Low" in sort by drop down
    Then I should see static links from ASTRA on left nav
    When I select "4" Column Grid icon
    And  I filter the result set to show "40" items
    Then I should see static links from ASTRA on left nav
    When I should be able to navigate using pagination functionality
    Then I should see static links from ASTRA on left nav
    When I select any random facet in facet panel
    Then I should see static links from ASTRA on left nav

  #pre condition :Need a SLP which has Static links from ASTRA, Subcategory links from STELLA and Facets in Left Nav
  @wip @domain_marketing @artifact_navapp @project_SEO_auto_facets @priority_high @release_17H
  Scenario: Verify that Static links from ASTRA should navigate to appropriate static links, when user selects the static links in INTL mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see the links ordered in which they were entered in Astra
    When I select static links
    Then I should navigate to appropriate static links