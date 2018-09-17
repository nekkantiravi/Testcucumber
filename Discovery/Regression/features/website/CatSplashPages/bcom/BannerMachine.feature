# Author: Nisum QE Team
# Date Created : Feb 05, 2015
# Date Modified:
# Date Signed Off: TBD

Feature: Rendering Banner image according to the contexts in row0,row101,row2,row3,slideshow and widget in cat splash page.


###########################################################################
# Story Titles: WDS:: Render HTML banners in Contextual Template
#               WDS :: MCOM :: Render Banner Machine in a Slideshow
#               WDS :: MCOM :: Render Banner Machine in a Widget
# Versionone story numbers:: B-10675,B-10647,B-11470 and B-11471

###################################SITE Mode and Desktop #################################################################

  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplash page - Verify the rendering of Banner Machine in top, mid and bottom banners on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    #Then I should see "BANNER_MACHINE" media on the page in "0" row
    Then I should see "BANNER_MACHINE" in "0" on cat splash page
    And I should see respective banner machine media image details on the page
    And I should navigate to respective page after click on 'a random' banner machine link type


  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine in row 101 on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "BANNER_MACHINE" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "101" row
    And I should see respective banner machine media image details on the page
    And I should navigate to respective page after click on 'a random' banner machine link type

  @wip @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine in Slide show in 101 rows on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "BANNER_MACHINE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE_SLIDESHOW" media on the page in "101" row
    And I should see respective banner machine media slideshow details on the page


  ###################################Registry Mode and Desktop #################################################################

  @wip @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine for top, mid and bottom banners on desktop in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "BANNER_MACHINE" in "0" on cat splash page
    #And I should see respective banner machine media image details on the page
    #And I should navigate to respective page after click on 'a random' banner machine link type

  @wip @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine in row 101 on desktop in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "BANNER_MACHINE" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "101" row
    And I should see respective banner machine media image details on the page
    And I should navigate to respective page after click on 'a random' banner machine link type

  @wip @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine in Slide show in 101 rows on desktop in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to "Category Splash" category with "BANNER_MACHINE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
    Then I should see "BANNER_MACHINE_SLIDESHOW" media on the page in "101" row
    And I should see respective banner machine media slideshow details on the page

  ###################################International Mode and Desktop #################################################################

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine for top, mid and bottom banners on in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate to "Category Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "0" row
    And I should see respective banner machine media image details on the page
    And I should navigate to respective page after click on 'a random' banner machine link type

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine in row 101 in on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate to "Category Splash" category with "BANNER_MACHINE" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "101" row
    And I should see respective banner machine media image details on the page
    And I should navigate to respective page after click on 'a random' banner machine link type

  @wip @domain_discovery @mode_iship @use_regression @migrated_to_sdt @priority_medium
  Scenario: CategorySplashPage - Verify the rendering of Banner Machine in Slide show in 101 rows in on desktop in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate to "Category Splash" category with "BANNER_MACHINE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "BANNER_MACHINE_SLIDESHOW" media on the page in "101" row
    And I should see respective banner machine media slideshow details on the page

