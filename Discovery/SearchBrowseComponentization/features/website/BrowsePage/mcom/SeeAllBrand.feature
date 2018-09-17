#Author: Discovery SNBC QE
#Date Created: 19/09/2017

Feature: Men's Shoes "See All Brands" produces error page - Permanent fix - currently production is fine with jsp fix
#http://jira/browse/ECOMSST-26353

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @use_regression_8 @snbc_comp
  Scenario: Brand page -  Verify the Shop all Brand Page in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "See All Brands" browse page under "HOME"
    And I modify the url to get in to snbc experiment
     Then I verify all basic attributes of the Designer page:
      | All Designers Banner                        |
      | A-Z Index                                   |
      | Alphabetical Order under each brand section |
      | Back to Top link under each brand section   |
      | All Brand links in left nav panel           |
    When I select random letter from brand index of the Designer page
    Then I should see designer page is anchor down to selected letter
    When I select 'Back to Top" link at selected letter of the Designer page
    Then I should see Designer page jump back to top anchor
    When I select any brand from Designer page
    Then I should navigate to Search Results page
    And I should see breadcrumb on Search Results page

  @use_regression @artifact_navapp @domain_discovery @priority_high @wip @use_regression_8 @mode_domestic @snbc_comp
  Scenario: Brand page -  Verify brand names are displayed in alphabetical order in Shop all brands page in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "See All Brands" browse page under "HOME"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    Then I verify the display of GNA in Shop all brands page
    And I verify the display of GFA in Shop all brands page
    And I verify all the brand names is in alphabetical order

  @use_regression @artifact_navapp @domain_discovery @priority_high @s4a_stable @wip @use_regression_8 @mode_domestic @snbc_comp
  Scenario: Brand page - Verify existing brand URL assigned to category has "http://www1.macys.com/shop/..." in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "See All Brands" browse page under "HOME"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    When I click on any brand FOB link in left navigation
    And I select a brand in designer index page assigned to "category"
    Then I verify navigated URL is in "/shop/" format

  @use_regression @artifact_navapp @domain_discovery @priority_high @wip @use_regression_8 @mode_domestic @snbc_comp
  Scenario: Brand page -  Verify existing brand URL assigned to DLP has "http://www1.macys.com/shop/featured/..." for normal item in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "See All Brands" browse page under "HOME"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    When I click on any brand FOB link in left navigation
    And I select a brand in designer index page assigned to "DLP"
    Then I verify navigated URL is in "/shop/featured/" format

  @use_regression @artifact_navapp @domain_discovery @priority_high @spo_5989 @release_14G @wip @mode_registry @use_regression_8 @mode_registry @snbc_comp
  Scenario: Brand page -Verify product thumbnails displayed as existing on brand dynamic landing page when navigated through browser back button after browse grid implementation in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to the "See All Brands" browse page under "KITCHEN"
    And I modify the url to get in to snbc experiment
    Then I should be navigated to brand index page
    And I click on any brand FOB link in left navigation
    When I select any brand in brand index page
    Then I verify that landed on brand results page with pagination
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that all the product thumbnails displayed properly on the Dynamic Landing page
   #   And I should see 4 product thumbnails for each row

