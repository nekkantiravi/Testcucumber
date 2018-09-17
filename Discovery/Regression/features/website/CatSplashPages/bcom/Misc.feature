# Author: DISCOVERY QE
# Date Created: 11/24/2016

Feature: BCOM :: Verify CatSplash Pages - Miscellaneous

  @wip @priority_medium @domain_discovery @mode_domestic @mode_iship @mode_registry @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage - Domestic|Iship|Registry - Verify Lookbooks & Guides links navigation from left navigation panel
    #Given I am on CatSplashPage in <shopping_mode> mode
    Given I am on CatSplash Page for "EDITORIAL" on "domestic" mode
    And I navigate to "Explore Now" from Left Nav links
    Then I verify all gift guide links are not resulting error page

  # Ex: Home(catid: 3865) cat splash page, in left nav panel, identify all Lookbooks & Guides categories
  # Then verify all those 'non-shop' links are resulting '200' response code with 'Mechanize' agent request.

  @wip @priority_medium @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: CategorySplashPage  - Verify media content in GIFTS catsplash page
    Given I am on CatSplash Page for "GIFTS" on "domestic" mode
    Then I verify all media links and popups are not resulting error page
    # Notes:
    # Do full validation of all rows - should not display any broken image or link via http party
