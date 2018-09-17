#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify Category Browse Page functionality in DOMESTIC, ISHIP and REGISTRY mode

############################### DOMESTIC MODE ##########################################################

#We dont have specific element for checkbox in bcom facet item. So will not be able to validate moving it to manual
#Testlink-BLCOM-84216 Vone-RT-06508
  @use_manual @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify facet value checkbox in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I verify checkbox of each unselected facet value in leftnav

  #Below scenario is for thumbnail carousal functionality which cant be automated. So moved to manual
  # Test case number: BLCOM-57562 Vone - RT-06476
  @use_manual @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify up to 4 alternate images will display on the thumbnails in rotation in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    And I hover the mouse on a product with 4 alternate Image
    Then I verify up to 4 alternate images rotate in the category browse page
    When I hover away then the rotation stops and transitions back to the primary image
  # Notes:
  # things need to verify:
  # Verify that when user hovers the thumbnail images up to 4 alternate images will display on the thumbnails in rotation.
  # Verify when hoovering away, the rotation stops and transitions back to the primary image

  ############################### ISHIP MODE ##########################################################

  #TestLink-BLCOM-84309
  #Vone - RT-06467
  @use_manual @priority_high @artifact_navapp @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify Badge Text with Tiered Pricing promotion in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Canada"
    Then I verify Badge Text "Buy More, Save More" with Tiered Pricing promotion
    And I verify Promotional discounts displays in the selected country currency

  ############################### REGISTRY MODE ##########################################################

  #For Below Scenario we need to disable JS . So moving it to manual scenarios
  # TestLink -BLCOM-69236 Vone - RT-06481
  @use_manual @priority_high @artifact_navapp @domain_discovery @mode_registry
  Scenario: BrowsePage - Verify view all products link when JS is disabled - in REGISTRY mode
    Given I am on the registry home page
    When I navigate to category browse page
    And I disable Java Script of the browser
    And I verify "View All Products" link
    Then I verify all products are displayed without pagination
    And I verify basic attributes of product thumbnails

  #Note:All the products should be displayed in a single page, without pagination
  #Verify All product thumbnails should be displayed
  #Product names should be displayed below the thumbnails
  #Product price and promotional information, if any should be displayed below the thumbnails

