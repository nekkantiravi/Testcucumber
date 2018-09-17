#Author: Discovery QE
#Date Created: 10/1/2015


Feature: Verify Recently Viewed Functionality on CatSplash in DOMESTIC, ISHIP and REGISTRY mode

  #As per current functionality we are not seeing RVI panel in splash, subsplash and browse page.
#  So commented below scenarios
#  #Vone - RT-06293 - RT-06292
#  #Testlink-BLCOM-84298, Testlink-BLCOM-84294,
#  # Vone-RT-06293
#  @priority_high @artifact_navapp @domain_discovery @mode_domestic
#  Scenario: Category Splash page - Verify Edit button functionality of RVI Panel in DOMESTIC mode
#    Given I visit the web site as a guest user
#    When I view 4 random products
#    And I navigate to category splash page
#    Then I should see edit option inside Recently Viewed panel
#    And I verify edit button functionality of RVI panel
#  #Notes:
#  #Remove button should be highlighted on each product in RV items panel
#  #Number of items in the RVI panel is displayed
#  #Right arrow and left arrows are displayed
#  #"done" link should be displayed
#  #No Duplicates product items in RVI panal
#
#  #Vone - RT-06291
#  #Testlink-BLCOM-84291
#  # Vone-RT-06291
#  @priority_high @artifact_navapp @domain_discovery @mode_domestic
#  Scenario: Category Splash page - Verify Remove & Done button functionality of RVI Panel in DOMESTIC mode
#    Given I visit the web site as a guest user
#    When I view 4 random products
#    And I navigate to category splash page
#    Then I should see edit option inside Recently Viewed panel
#    And I verify remove button functionality of RVI panel
#    When I click on Done button of RVI panel
#    Then I should see product should be permanently removed from RVI panel
#    #Notes:
#    #Click on EDIT button
#    #Click on remove of any product
#    #Product should be temporarily removed from the list in RV items panel and remaining products should move in forward sequence.
#    #And Edit button should be changed as Done

