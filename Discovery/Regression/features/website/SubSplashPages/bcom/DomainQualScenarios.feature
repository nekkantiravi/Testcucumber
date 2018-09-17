# Author: DISCOVERY QE
# Date Created: 06/30/2015

Feature: Verify Sub Splash Page functionality

  @use_domain_qual @domain_discovery @artifact_navapp @high @bcom
  Scenario: CategorySubSplashPage - Verify header is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I verify that logo is displayed and returns a 200 OK
    Then I verify that the header elements are displayed
  # Notes: Verifies that the logo and the header elements are displayed in Sub Splash Page

  @use_domain_qual @domain_discovery @artifact_navapp @high @bcom
  Scenario: CategorySubSplashPage - Verify footer is displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I verify the footer links in "Domestic" Mode
  # Notes: Verifies that footer links are all present in Sub Splash Page

  @use_bat @use_domain_qual @domain_discovery @artifact_navapp @high @bcom
  Scenario: CategorySubSplashPage - Verify COACH HANDBAGS in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "COACH" sub splash page under "HANDBAGS"
    Then I verify the basic attributes of COACH browse page
  # Notes: Verifies the basic attributes in the Coach Handbag page


