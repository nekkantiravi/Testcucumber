# Author: Nisum QE Team
# Date Created : Feb 05, 2015
# Date Modified:
# Date Signed Off: TBD

Feature: Rendering Banner image according to the contexts in row0,row101,row2,row3,slideshow and widget in sub splash page.


###########################################################################
# Story Titles: WDS:: Render HTML banners in Contextual Template
#               WDS :: MCOM :: Render Banner Machine in a Slideshow
#               WDS :: MCOM :: Render Banner Machine in a Widget
# Versionone story numbers:: B-10675,B-10647,B-11470 and B-11471

###################################SITE Mode and Desktop #################################################################

  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @domestic_mode @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in top, mid and bottom banners on desktop DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "0" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type


  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @domestic_mode @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in row 101 on desktop DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "101" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type

  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @domestic_mode @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in Slide show in 101 rows on desktop DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE_SLIDESHOW" media on the page in "101" row
    And I should see respective banner machine media in 'slideshow' as per astra data


  ###################################Registry Mode and Desktop #################################################################

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @mode_registry @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine for top, mid and bottom banners on desktop in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "0" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @mode_registry @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in row 101 on desktop in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "101" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type

  #Note : Subsplash pages are not available in registry mode. Hence added wip tag
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @mode_registry @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in Slide show in 101 rows on desktop in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "BANNER_MACHINE_SLIDESHOW" media on the page in "101" row
    And I should see respective banner machine media in 'slideshow' as per astra data

  ###################################International Mode and Desktop #################################################################

  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @mode_iship @wip @deprecated
  Scenario: CategorySubSplashPage -  ISHIP mode - Verify the rendering of Banner Machine for top, mid and bottom banners on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate to "Sub Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "0" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type

  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @mode_iship @use_creative @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in row 101 on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate to "Sub Splash" category with "BANNER_MACHINE" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "101" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type

  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_regression @use_regression_1 @feature_bm @mode_iship @use_creative @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in Slide show in 101 rows on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate to "Sub Splash" category with "BANNER_MACHINE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE_SLIDESHOW" media on the page in "101" row
    And I should see respective banner machine media in 'slideshow' as per astra data

