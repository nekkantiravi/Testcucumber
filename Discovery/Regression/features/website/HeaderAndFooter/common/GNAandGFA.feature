# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of BCOM & MCOM GNA & GFA elements in DOMESTIC mode



############################################# DOMESTIC Mode #####################################################
  #Testlink-BLCOM-84106
  #vone-RT-06325
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: HomePage - Verify GNA and GFA links in Home Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify GNA consistency
    And I verify GFA consistency
    # Notes:
    # Click on the GNA link and verify no error page. Should be 200- OK
    # Click on the GFA link and verify no error page. Should be 200- OK
    # If it opens a popup, verify the pop does not load any error page
    # Refer the step def for similar verification in 14J
    # Then(/^I verify that Sub Ads of (.*) should be displayed on cat splash page$/) do |fob|


  #Testlink-BLCOM-84106
  #vone-RT-06325
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: HomePage - Verify GNA and GFA links in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    Then I change country to "India"
    And I close the welcome mat if it's visible
    Then I verify GNA consistency
    And I verify GFA consistency
    # Notes:
    # Click on the GNA link and verify no error page. Should be 200- OK
    # Click on the GFA link and verify no error page. Should be 200- OK
    # If it opens a popup, verify the pop does not load any error page
    # Refer the step def for similar verification in 14J
    # Then(/^I verify that Sub Ads of (.*) should be displayed on cat splash page$/) do |fob|
    #
    # In ISHIP mode GNA and GFA will not display. Hence, Verified that adpools are not displaying

  #Testlink-BLCOM-84106
  #vone-RT-06325
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: HomePage - Verify GNA and GFA links in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify GNA consistency
    And I verify GFA consistency
    # Notes:
    # Click on the GNA link and verify no error page. Should be 200- OK
    # Click on the GFA link and verify no error page. Should be 200- OK
    # If it opens a popup, verify the pop does not load any error page
    # Refer the step def for similar verification in 14J
    # Then(/^I verify that Sub Ads of (.*) should be displayed on cat splash page$/) do |fob|

#  Duplicate scenario of above
#  #Testlink-BLCOM-84106
#  #vone-RT-06325
#  @use_regression @domain_discovery @priority_high @mode_domestic
#  Scenario: HomePage - Verify GNA and GFA links in REGISTRY mode
#    Given I visit the web site as a guest user
#    When I navigate to registry home page
#    Then I verify GNA consistency
#    And I verify GFA consistency
#    # Notes:
#    # Click on the GNA link and verify no error page. Should be 200- OK
#    # Click on the GFA link and verify no error page. Should be 200- OK
#    # If it opens a popup, verify the pop does not load any error page
#    # Refer the step def for similar verification in 14J
#    # Then(/^I verify that Sub Ads of (.*) should be displayed on cat splash page$/) do |fob|
