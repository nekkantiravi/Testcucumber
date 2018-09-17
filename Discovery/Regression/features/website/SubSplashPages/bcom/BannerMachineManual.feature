 # Author: Nisum QE Team
# Date Created : Feb 05, 2015
# Date Modified:
# Date Signed Off: TBD

Feature: Rendering Banner image according to the contexts in row0,row101,row2,row3,slideshow and widget in cat splash page manual scenarios.

###########################################################################
# Story Titles: WDS:: Render HTML banners in Contextual Template
#               WDS :: MCOM :: Render Banner Machine in a Slideshow
#               WDS :: MCOM :: Render Banner Machine in a Widget
#               WDS:: BCOM :: Render HTML banners
# Versionone story numbers:: B-10675,B-10647,B-11470, B-11471, B-10673
################################### SITE, REGISTRY & INTERNATIONAL Mode on Desktop & TABLET #################################################################

  #Note: Cascading feature is not feasible to automate, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_manual @mode_registry @mode_iship @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the cascading of Banner Machine in row 0 on desktop and tablet in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE             | BROWSE          |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
      | DESKTOP     | INTL        | SITE             | BROWSE          |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "BANNER_MACHINE" on the page in "0" row
    And I should see respective banner machine media as per astra data
    When I click any child category
    Then I should see cascaded "BANNER_MACHINE" on the page in "0" row
    And I should see one of the below link_types in banner machine
      | Category |
      | Product  |
      | Pop Up   |
      | Facet    |
      | Static   |
    When I click the banner machine
    Then I should navigate to proper page

  #Note: Below is scenario related to UI verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_galen @please_automate @mode_registry @mode_iship @mode_domestic @wip @deprecated
  Scenario Outline: CategorySubSplashPage -  Verify Banner machine specifications for rows(row0,row101 and row102) desktop and tablet in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to "<page_type>" category with "BANNER_MACHINE" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE             | BROWSE          |
      | DESKTOP     | US          | WEDDING_REGISTRY | BROWSE          |
      | DESKTOP     | INTL        | SITE             | BROWSE          |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "BANNER_MACHINE" on the page in "<row_type>"" row
    And I should see respective banner machine media as per astra data
    And I should see Banner image according to the below specifications
      | Banner Dimensions | 742px X 444px   |
      | Image Height      | 373px           |
      | File type         | JPG/layered PSD |
    And I should see Banner text according to below specifications
      | Black bar containing type | 70px                              |
      | HTML copy                 | Arial/12pt/leading 17/tracking 15 |
      | Color                     | white/black                       |
      | MAX Characters            | 250                               |
    And I should see Banner links according to below specifications
      | Type      | All CAPS and underlined |
      | MAX Links | 4                       |
    And I should see background color according to below specifications
      | Background colors | white/black |
  Examples:
    | page_type  | row_type |
    | Sub Splash | 0        |
    | Sub Splash | 101      |
    | Sub Splash | 102      |

  #Note: Below is scenario related to core-metrics verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium  @release_15E @use_coremetrics @please_automate @mode_registry @iship_mode @domestic_mode @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify element tag for banner machine on desktop in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" page
    And I click on any of the links in banner machine
    Then I should see following tags in element tags
      | Element Category | BANNER_MACHINE                                       |
      | Element ID       | link1/link2/link3/link4                              |
      | Attribute 2      | page on which the interaction occurs aka browse page |

  ################################### Tablet Scenarios #################################################################

  ################################### SITE Mode and Tablet #################################################################

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_manual @mode_registry @mode_iship @dmode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in top, mid and bottom banners on tablet in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "0" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "0" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_manual @mode_registry @mode_iship @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in row 101 on tablet in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "BANNER_MACHINE" media on the page in "101" row
    And I should see respective banner machine media as per astra data
    And I should navigate to respective page after click on 'a random' banner machine link type

  #Note: Below is scenario related to Tablet verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_manual @mode_registry @mode_iship @mode_domstic @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify the rendering of Banner Machine in Slide show in 101 rows on tablet in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I navigate to "Sub Splash" category with "BANNER_MACHINE_SLIDESHOW" in "101" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE    | NAVIGATION_TYPE |
      | TABLET      | US          | SITE             | BROWSE          |
      | TABLET      | US          | WEDDING_REGISTRY | BROWSE          |
      | TABLET      | INTL        | SITE             | BROWSE          |
    Then I should see "BANNER_MACHINE_SLIDESHOW" media on the page in "101" row
    And I should see respective banner machine media in 'slideshow' as per astra data

  ################################### Banner Machine KS scenarios ####################################################################

  #Note: Below is scenario related to Kill Switch verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_manual @mode_registry @mode_iship @mode_domestic @wip @deprecated
  Scenario: CategorySubSplashPage -  Verify as a guest user, I should see the banner machine in slideshow with KS ON on desktop and tablet in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I visit MASS home page and click on Kill Switch Features ON option
    And I navigate to sub splash page
    Then I should see banner machine in slideshow

  #Note: Below is scenario related to Kill Switch verification, hence we put it as manual.
  @artifact_navapp @domain_discovery @priority_medium @release_15E @use_manual @mode_registry @mode_iship @mode_domestic
  Scenario: CategorySubSplashPage -  Verify as a guest user, I should not see the banner machine in slideshow with KS OFF ON on desktop and tablet in DOMESTIC & REGISTRY & ISHIP Mode
    Given I visit the web site as a guest user
    When I visit MASS home page and click on Kill Switch Features OFF option
    And I navigate to sub splash page
    Then  I should not see banner machine in slideshow