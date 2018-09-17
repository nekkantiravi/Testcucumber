# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of header TOPNAV functionality in DOMESTIC, ISHIP and REGISTRY modes

  ########################################## Top Nav Verification in Domestic mode #####################################

  #Test Case ID: MCOM-92022
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: CatSplashPage & HomePage  - Verify TOPNAV on Home Page and Category Splash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify dynamic top navigation in "domestic" mode
    When I navigate to random category splash page
    And I verify dynamic top navigation in "domestic" mode

  #Adding @wip as responsive sing-in page won't have Header FOB menu & Flyouts for this scenario validation
  #Test Case ID: MCOM-92022
  @use_regression @domain_discovery @priority_high @mode_domestic @wip
  Scenario: SignInPage - Verify TOPNAV on MyAccount Signin Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    Then I should see Ajax call to navapp for "topnav" in "SITE" mode
    And I verify dynamic top navigation in "domestic" mode

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: BagPage - Verify TOPNAV on Shopping Bag Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I add a random product to my bag and checkout
    Then I should see Ajax call to navapp for "topnav" in "SITE" mode
    And I verify dynamic top navigation in "domestic" mode

  ########################################## Top Nav Verification in Iship mode #####################################

  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario: CatSplashPage - Verify TOPNAV on Navapp Home Page and Category Splash Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    Then I verify dynamic top navigation in "iship" mode
    When I navigate to random category splash page
    Then I verify dynamic top navigation in "iship" mode

  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario: ShoppingBagPage - Verify TOPNAV on shopping bag Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I add a random product to my bag and checkout
    Then I should see Ajax call to navapp for "topnav" in "ISHIP" mode
    And I verify dynamic top navigation in "iship" mode

########################################## Top Nav Verification in Registry mode #####################################

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: CatSplashPage - Verify TOPNAV on Category Splash Page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    When I navigate to random category splash page
    Then I verify dynamic top navigation in "registry" mode

  @use_regression @domain_discovery @priority_high @mode_registry
  Scenario: FindRegistryPage - Verify TOPNAV on Wedding Registry Legacy Capture Email Page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to find registry on registry home page
    Then I should see Ajax call to navapp for "topnav" in "REGISTRY" mode
    And I verify dynamic top navigation in "registry" mode

