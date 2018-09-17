#############################################################
# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/04/2017
# Mingle Path: http://mingle/projects/discovery/cards/19410
#############################################################

Feature: WDS :: MCOM :: Render Contextual Template for Sub Splash

  ################################## DOMESTIC ###################################

  @domain_discovery @priority_medium @use_regression @mode_domestic @discovery_daily_run
  Scenario Outline: SubSplashPage - Verify contextual media on HANDBAGS -> MICHAEL Michael Kors in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    Then I should see "<media>" in "<row_type>" on cat splash page
  Examples:
    | row_type | media           |
    | 101      | AD              |
    | 101      | COPY_BLOCK      |
    | 101      | THUMBNAIL_GRID  |
    | 101      | IMAGE_MAP       |
    | 101      | HORIZONTAL_RULE |
    | 101      | TEXT            |
    | 106      | CATEGORY_ICON   |

  @domain_discovery @priority_medium @use_regression @mode_domestic
  Scenario Outline: SubSplashPage - Verify contextual media on MEN -> Activewear in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Activewear" sub splash page under "MEN"
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 101      | FLEXIBLE_POOL |
      | 104      | CATEGORY_ICON |
      | 105      | CATEGORY_ICON |


    ############################ ISHIP MODE #####################################

  @domain_discovery @priority_medium @use_regression @mode_iship @discovery_daily_run
  Scenario Outline: SubSplashPage - Verify contextual media on HANDBAGS -> MICHAEL Michael Kors in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS & ACCESSORIES"
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media           |
      | 101      | AD              |
      | 101      | COPY_BLOCK      |
      | 101      | THUMBNAIL_GRID  |
      | 101      | HORIZONTAL_RULE |
      | 101      | TEXT            |
      | 106      | CATEGORY_ICON   |

  @domain_discovery @priority_medium @use_regression @mode_iship
  Scenario Outline: SubSplashPage - Verify contextual media on MEN -> Activewear in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to the "Activewear" sub splash page under "MEN"
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media         |
      | 101      | FLEXIBLE_POOL |
      | 104      | CATEGORY_ICON |
      | 105      | CATEGORY_ICON |

    ############################ REGISTRY MODE #####################################

  @domain_discovery @priority_medium @use_regression @mode_iship @discovery_daily_run
  Scenario Outline: SubSplashPage - Verify contextual media on KITCHEN -> Anolon in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to the "Anolon" sub splash page under "KITCHEN"
    Then I should see "<media>" in "<row_type>" on cat splash page
    Examples:
      | row_type | media          |
      | 101      | AD             |
      | 101      | SLIDESHOW      |
      | 106      | CATEGORY_ICON  |
      | 101      | THUMBNAIL_GRID |

  @domain_discovery @priority_high @use_regression @mode_domestic @mode_registry @mode_iship @discovery_daily_run
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify media links and popup links are not resulting any error page
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to the "<browse>" browse page under "<catsplash>"
    Then I verify all media links and popups are not resulting error page
    Examples:
      | mode     | catsplash              | browse               |
      | domestic | WOMEN                  | Activewear           |
      | domestic | HANDBAGS               | MICHAEL Michael Kors |
      | registry | KITCHEN                | Anolon               |
      | iship    | HANDBAGS & ACCESSORIES | MICHAEL Michael Kors |
  # Navigate to any sub splash page in each mode
  # Identify all links from media content section on page.
  # Then verify all media links are resulting '200' response code with 'Mechanize' agent request.