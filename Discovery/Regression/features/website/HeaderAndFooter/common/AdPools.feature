# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of Ad Pools functionality in DOMESTIC, ISHIP and REGISTRY modes

  ################################ Ad pools verification for header and footer in Domestic Mode ##################################

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify ad pools on Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: HomePage - Verify ad pools in My Account page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I create a new profile
    And I should see "My Account" page is rendered
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: My account page - Verify ad pools in My wallet page in DOMESTIC mode
    Given I visit the web site as a registered user
    And I navigate to my account page
    When I navigate to My Wallet page from My Account page
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: WishList Page - Verify ad pools on My wish list page in DOMESTIC mode
    Given I visit the web site as a registered user
    When I navigate to the my wish list page
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: PDPPage - Verify ad pools on PDP page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then I should see header footer ad pools in "SITE" mode

  @use_regression @priority_medium @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify ad pools on 2nd browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    When I click on next pagination button
    Then I should see header footer ad pools in "SITE" mode

  ################################ Ad pools verification for header and footer in Registry Mode #######################

  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: Browse Page - Verify ad pools on Browse page in REGISTRY mode
    Given I visit the web site as a guest user
    When I visit the registry "browse" page
    Then I should see header footer ad pools in "REGISTRY" mode

  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: PDP page - Verify ad pools on PDP page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to a random product
    Then I should see header footer ad pools in "REGISTRY" mode

  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: Legacy Page -Verify ad pools on legacy page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to find registry on registry home page
    Then I should see header footer ad pools in "REGISTRY" mode

  @use_regression @priority_medium @domain_discovery @mode_registry
  Scenario: BrowsePage - Verify ad pools on 2nd browse page in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to the "Electrics" browse page under "KITCHEN"
    And I click on next pagination button
    Then I should see header footer ad pools in "REGISTRY" mode

    ################################ Ad pools verification for header and footer in Iship Mode ##################################

  @priority_medium @artifact_navapp @domain_discovery @artifact_shopapp @mode_iship @iship_1 @use_prod @defect-ECOMSST-51738 @wip
  Scenario: Promoations Page - Verify ad pools on My Offer page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I close the welcome mat if it's visible
    And I navigate to deals and promotions page
    And I should see the promotions page
    Then I should see header footer ad pools in "ISHIP" mode

  @priority_medium @artifact_legacy @mode_iship @iship_1 @use_prod @defect-ECOMSST-51738 @wip
  Scenario: Legacy Page - Verify ad pools on legacy page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I close the welcome mat if it's visible
    When I navigate to a random "iship" legacy page
    Then I should see header footer ad pools in "ISHIP" mode

  @priority_medium @artifact_navapp @domain_discovery @mode_iship @iship_1 @use_prod @defect-ECOMSST-51738 @wip
  Scenario: BrowsePage -  Verify ad pools on 2nd browse page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    Then I change country to "India"
    And I close the welcome mat if it's visible
    And I navigate to "MEN" category page
    And I navigate to "Dress Shirts" browse page from cat splash page
    When I click on next pagination button
    Then I should see header footer ad pools in "ISHIP" mode