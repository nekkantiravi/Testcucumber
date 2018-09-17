# Author: DISCOVERY QE
# Date Created: 04/26/2017

Feature: Verification of Search & Category Rail in Domestic , Registry and Iship modes

#As Clean will be scaled to 100% as of 08/23/2017, ignoring Radical, Control and Holdout scenarios

#Treatment - B

#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify Shop by Department dropdown menu in Domestic and IShip mode
#    Given I visit the web site as a guest user in "<mode_name>" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I Should see Shop by Department dropdown menu on the left side
#    Examples:
#      | mode_name |
#      | domestic  |
#      | iship     |
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Verify Shop Registry dropdown menu in Registry mode
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I Should see Shop Registry dropdown menu on the left side
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify Search box and Bag to the right of Search & Category Rail in Domestic, IShip and Registry modes
#    Given I visit the web site as a guest user in "<mode_name>" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I Should see bag icon on top right corner of the search rail on header
#    And I Should see Search box to the right of the Search & Category Rail
#    Examples:
#      | mode_name |
#      | domestic  |
#      | registry  |
#      | iship     |
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Verify Search box has default text in Domestic, IShip and Registry modes
#    Given I visit the web site as a guest user in "<mode_name>" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I verify that "Search or enter web ID" default message is displayed in new search box
#    Examples:
#      | mode_name |
#      | domestic  |
#      | registry  |
#      | iship     |
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify FLYOUT for shop by deparment dropdown is displayed in DOMESTIC mode
#    Given I visit the web site as a guest user in "domestic" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify all main categories are displayed for shop by department in domestic mode
#    Given I visit the web site as a guest user in "domestic" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "domestic" mode
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify FLYOUT for shop by deparment dropdown is displayed in IShip mode
#    Given I visit the web site as a guest user in "iship" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify all main categories are displayed for shop by department in IShip mode
#    Given I visit the web site as a guest user in "iship" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "iship" mode
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify FLYOUT for shop by deparment dropdown is displayed in Registry mode
#    Given I visit the web site as a guest user in "registry" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify all main categories are displayed for shop by department in Registry mode
#    Given I visit the web site as a guest user in "registry" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "registry" mode
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify page navigated to category splash page from shop by department in Domestic mode
#    Given I visit the web site as a guest user in "domestic" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "domestic" mode
#    When I navigate to random category splash page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see the "category splash" page
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify page navigated tocategory splash page from shop by department in iship mode
#    Given I visit the web site as a guest user in "iship" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "iship" mode
#    When I navigate to random category splash page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see the "category splash" page
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario: Search By Department - Verify page navigated to category splash page from shop by registry in iship mode
#    Given I visit the web site as a guest user in "registry" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "registry" mode
#    When I navigate to random category splash page
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see the "category splash" page
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Search By Department - Verify page navigated to category sub splash page from  shop by department in Domestic mode
#    Given I visit the web site as a guest user in "domestic" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "domestic" mode
#    When I navigate to "<subcategory>" subsplash page from "<category>"
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see the "category sub splash" page
#    Examples:
#      | subcategory | category |
#      | Activewear  | WOMEN    |
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Search By Department - Verify page navigated to category sub splash page from shop by department in iship mode
#    Given I visit the web site as a guest user in "iship" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "iship" mode
#    When I navigate to "<subcategory>" subsplash page from "<category>"
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see the "category sub splash" page
#    Examples:
#      | subcategory | category |
#      | Activewear  | MEN      |
#
#  @use_regression @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
#  Scenario Outline: Search By Department - Verify page navigated to category sub splash page from shop by registry in iship mode
#    Given I visit the web site as a guest user in "registry" mode
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    When I hover over on shop by department dropdown
#    Then I verify that flyout menu is displayed for shop by department
#    And I verify dynamic top navigation in "registry" mode
#    When I navigate to "<subcategory>" subsplash page from "<category>"
#    And Navigate to "HnF Radical" viewtype in new header footer experience
#    Then I should see the "category sub splash" page
#    Examples:
#      | subcategory | category |
#      | Anolon      | KITCHEN  |
