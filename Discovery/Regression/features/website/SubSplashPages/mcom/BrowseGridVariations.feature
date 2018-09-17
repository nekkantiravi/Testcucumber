# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/04/2017

Feature: C2 :: P2 :: MCOM :: Browse Grid Variations automation scenarios for Sub Splash Page

##############################################################################################################
# Story B-13915: C2 P2:: Browse :: CAP:: Back to top [Browse and Sub Splash Pages]
# VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-13915
##############################################################################################################

  @use_regression @domain_discovery @priority_medium @mode_domestic
  Scenario: SubSplashPage - Domestic - Verify Back to Top button
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I navigate to top of page
    Then I verify that back to top button is not displayed on page

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: SubSplashPage - Iship - Verify Back to Top button
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS & ACCESSORIES"
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I navigate to top of page
    Then I verify that back to top button is not displayed on page

  @use_regression @domain_discovery @priority_medium @mode_registry
  Scenario: SubSplashPage - Registry - Verify Back to Top button
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Anolon" sub splash page under "KITCHEN"
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I navigate to top of page
    Then I verify that back to top button is not displayed on page

  @use_regression @domain_discovery @priority_medium @mode_domestic
  Scenario: SubSplashPage - Domestic - Verify Back to Top button selection is taken to top of the page
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I select back to top button
    Then I verify that sub splash page navigated to top of the page

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: SubSplashPage - Iship - Verify Back to Top button selection is taken to top of the page
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS & ACCESSORIES"
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I select back to top button
    Then I verify that sub splash page navigated to top of the page

  @use_regression @domain_discovery @priority_medium @mode_registry
  Scenario: CategorySubSplashPage - Registry - Verify Back to Top button selection is taken to top of the page
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Anolon" sub splash page under "KITCHEN"
    And I navigate to bottom of page
    Then I verify that back to top button is displayed on page
    And I select back to top button
    Then I verify that sub splash page navigated to top of the page

  ##############################################################################################################
  # Story B-13920 : C2 P2 :: Browse :: MCOM :: CAP :: Reposition filters above dropdowns [Browse and Sub Splash Pages]
  # VersionOne Link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-13920
  ##############################################################################################################

  @use_regression @domain_discovery @priority_medium @mode_domestic @xbrowser_subsplash
  Scenario: SubSplashPage - Domestic - Verify reposition filters(selected facet values) persistance
    Given I visit the web site as a guest user in "domestic" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    When I filter the result set to show "120" items per page
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist

  @use_regression @domain_discovery @priority_medium @mode_iship @xbrowser_subsplash
  Scenario: SubSplashPage - Iship - Verify reposition filters(selected facet values) persistance
    Given I visit the web site as a guest user in "iship" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS & ACCESSORIES"
    When I filter the result set to show "120" items per page
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist

  @use_regression @domain_discovery @priority_medium @mode_registry @xbrowser_subsplash
  Scenario: SubSplashPage - Registry - Verify reposition filters(selected facet values) persistance
    Given I visit the web site as a guest user in "registry" mode
    And I clear existing class variable data to avoid data issues
    And I navigate to the "Anolon" sub splash page under "KITCHEN"
    When I filter the result set to show "120" items per page
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    When I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous item count selection persist
