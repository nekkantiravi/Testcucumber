# Author: DISCOVERY QE
# Date Created: 06/10/2015


Feature: Verification GNA and GFA in domestic, iship and registry modes

  ################################################### Domestic Mode  #################################################


  #Test Case ID: MCOM-92023
  @use_regression @domain_discovery @priority_medium @mode_domestic
  Scenario:  Multiple pages - Verify 3 images links under GFA - Domestic mode
    Given I visit the web site as a guest user
    Then I verify the image links under GFA

  ###################################################### Iship Mode  ###################################################

   #This test scenarios is obsolete refer D-12034
  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: Home Page - Verify 3 images links under GFA in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify the image links under GFA

    #Test Case ID: BLCOM-76341 MCOM-83563 BLCOM-76313 MCOM-81134
  #this is obsolete test case ECOMSST-43372
  @use_regression @domain_discovery @priority_medium @mode_domestic
  Scenario: Home page - Verify GNA and GFA in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    And I verify the display of GFA
    # Notes:
    # Test Case Description: Home Page verification's in International shipping
    # 3.Check GNA/GFA displayed
    # 4.Click on displayed GNA/GFA and verify it navigates to appropriate page


  #Test Case ID: BLCOM-76341 MCOM-83563 BLCOM-76313 MCOM-81134
  #this is obsolete test case ECOMSST-43372
  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: Home page - Verify GNA and GFA in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify the display of GNA
    And I verify the display of GFA
    # Notes:
    # Test Case Description: Home Page verification's in International shipping
    # 3.Check GNA/GFA displayed
    # 4.Click on displayed GNA/GFA and verify it navigates to appropriate page
