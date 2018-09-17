# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of BCOM & MCOM footer elements in DOMESTIC, ISHIP and REGISTRY modes


 ########################################## Header and Footer elements in Domestic mode ################################
  #Testlink-BLCOM-58648
  #vone-RT-06313
  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: CatSplashPage & HomePage - Verification of new header and footer elements in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I should see new header and footer elements section in "Domestic"
    When I navigate to random category splash page
    Then I should see new header and footer elements section in "Domestic"

  #Testlink-BLCOM-58648
  #vone-RT-06313
  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verification of new header and footer elements in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I should see new header and footer elements section in "Domestic"
    When I navigate to category browse page
    Then I should see new header and footer elements section in "Domestic"


  ########################################## Header and Footer elements in Iship mode ##################################

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: CatSplashPage & HomePage - Verification of new header and footer elements in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    Then I should see new header and footer elements section in "Iship"
    When I navigate to random category splash page
    Then I should see new header and footer elements section in "Iship"


  ########################################## Header and Footer elements in Registry mode ##############################

  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: CatSplashPage &Home page - Verification of new header and footer elements in registry mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I should see new header and footer elements section in "Registry"
    And I navigate to random category splash page
    Then I should see new header and footer elements section in "Registry"

  #Vone - RT-06261
  #Test Case ID: MCOM-85391 BLCOM-58654, MCOM-81138
  #Testlink-MCOM-81138
  #Testlink-MCOM-96575
  #Testlink-BLCOM-58654
  #Vone  - RT-07345 - RT-07346
  @use_regression @priority_high @domain_discovery @mode_domestic
  Scenario: HeaderAndFooter - Verify Header & Footer sections in registry Home page, Registry Manager Page, BVR, GVR and Browse Page in REGISTRY mode
    Given I visit the web site as a registry user
    Then I should see new header and footer elements section in "Registry"
    When I navigate to registry BVR page
    Then I should see new header and footer elements section in "Registry"
    When I navigate to registry browse page
    Then I should see new header and footer elements section in "Registry"
    When I navigate to registry GVR page
    Then I should see new header and footer elements section in "Registry"
    # Notes:
    # Update the existing step def to verify the following
    # MCOM: Customer Service, Macys Credit card, Our Stores, Macys Inc, Social icons links are displayed in footer
    # BCOM: Customer Service, My Account, Credit Service, Way to Shop, Company, Social Icons links are displayed in footer
    # c) Verify that "Easy web browser for visually impaired customers" text is displayed under footer section.
    # d) Verify that Legal disclaimers: CA Privacy, Copyright, Privacy, Product Recalls and Legal Notice are displayed under the footer section.

