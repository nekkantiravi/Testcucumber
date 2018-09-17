# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of Ad Pools functionality in DOMESTIC, ISHIP and REGISTRY modes


  ################################ Ad pools verification for header and footer in Domestic Mode ########################

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: CategorySplashPage - Verify the header footer adpools are displaying on category page in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: DLP page - Verify the header footer adpools are displaying on DLP page in DOMESTIC mode
    Given I am on DynamicLandingPage in "domestic" mode
    Then I should see header footer ad pools in "SITE" mode

  ################################ Ad pools verification for header and footer in Registry Mode ########################

  #Test Case ID: MCOM-92083
  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: CategorySplashPage - Verify the header footer adpools are displaying on category page in REGISTRY mode
    Given I am on CatSplash Page for "KITCHEN" on "registry" mode
    Then I should see header footer ad pools in "REGISTRY" mode

  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: DLP Page - Verify ad pools on DLP page in REGISTRY mode
    Given I am on DynamicLandingPage in "registry" mode
    Then I should see header footer ad pools in "REGISTRY" mode

  ################################ Ad pools verification for header and footer in Iship Mode ###########################

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: CategorySplashPage - Verify the header footer adpools are displaying on category page in ISHIP mode
    Given I am on CatSplash Page for "SHOES" on "iship" mode
    Then I should see header footer ad pools in "ISHIP" mode

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: DLP Page - Verify the header footer adpools are displaying on DLP page in ISHIP Mode
    Given I am on DynamicLandingPage in "iship" mode
    Then I should see header footer ad pools in "ISHIP" mode

################################ Ad pools verification for header and footer in Iship Mode ############################

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify ad pools on Category Browse Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I close the welcome mat if it's visible
    When I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should see header footer ad pools in "ISHIP" mode

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: PDP Page - Verify ad pools on PDP Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to a random product
    Then I should see header footer ad pools in "ISHIP" mode

    ## Below scenarios are moved from Common folder as these are MCOM only scenarios

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: Legacy page - Verify ad pools on legacy page in Site mode
    Given I visit the web site as a guest user
    When I navigate to new stores page
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: Promotions Page - Verify ad pools on My Offer page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I visit the deals and promotions page
    And I should see the promotions page
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: SubSplashPage - Verify ad pools on Subsplash page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Activewear" browse page under "WOMEN"
    Then I should see header footer ad pools in "SITE" mode

    #In BCOM order confirmation page adpools will not display so adding tag mcom_only
  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: Confirmation Page - Verify ad pools on Order in DOMESTIC mode
    Given I visit the web site as a registered user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as an "guest" user
    Then I should see header footer ad pools in "SITE" mode

     ################################ Ad pools verification for header and footer in Iship Mode ##################################
## Below scenarios are only mcom for BCOM header and footer adpools are not displaying
  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: SubSplashPage - Verify ad pools on Subsplash page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I navigate to the "Activewear" browse page under "WOMEN"
    Then I should see header footer ad pools in "ISHIP" mode

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: CampaignPage - Verify ad pools on My Offer page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I navigate to "/social?campaign_id=" pattern url link from footer
    Then I should see header footer ad pools in "ISHIP" mode

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: CEPage - Verify ad pools on My Offer page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "India"
    And I navigate to "/m/campaign" pattern url link from footer
    Then I should see header footer ad pools in "ISHIP" mode

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: Legacy Page - Verify ad pools on legacy page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I close the welcome mat if it's visible
    Then I should see header footer ad pools in "ISHIP" mode

  @use_regression @priority_medium @domain_discovery @mode_iship
  Scenario: BrowsePage -  Verify ad pools on 2nd browse page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    Then I change country to "India"
    And I close the welcome mat if it's visible
    And I navigate to the "Dress Shirts" browse page under "MEN"
    When I click on next pagination button
    Then I should see header footer ad pools in "ISHIP" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: CEPage - Verify ad pools on legacy page in Site mode
    Given I visit the web site as a guest user
    And I navigate to "/m/campaign" pattern url link from footer
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: SubSplashPage - Verify ad pools on Subsplash page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to the "Anolon" browse page under "KITCHEN"
    Then I should see header footer ad pools in "REGISTRY" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: CampaignPage - Verify ad pools on legacy page in Site mode
    Given I visit the web site as a guest user
    And I navigate to "/social?campaign_id=" pattern url link from footer
    Then I should see header footer ad pools in "SITE" mode
