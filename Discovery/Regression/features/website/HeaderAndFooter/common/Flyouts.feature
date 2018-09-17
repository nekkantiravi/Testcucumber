  # Author: DISCOVERY QE
  # Date Created: 06/10/2015

  Feature: Verification of BCOM & MCOM header TOPNAV - FLYOUTS functionality in DOMESTIC, ISHIP and REGISTRY modes

    ########################################## Flyout Verification in Domestic mode #####################################

    #Adding @wip as responsive sing-in page won't have Header FOB menu & Flyouts for this scenario validation
    @use_regression @priority_medium @domain_discovery @mode_domestic @wip
    Scenario: MyAccountPage - Verify TOPNAV - FLYOUTS on Myaccount Signin shopapp page in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to signin page of "SITE" mode
      Then I should see Ajax call to navapp for "flyout" in "SITE" mode
      When I mouse hover on any category
      And I should see flyout menu


    @use_regression @priority_medium @domain_discovery @mode_domestic
    Scenario: ShoppingBagPage - Verify TOPNAV - FLYOUTS on Shopping bag page in DOMESTIC mode
      Given I visit the web site as a guest user
      When I add a random product to my bag and checkout
      Then I should see Ajax call to navapp for "flyout" in "SITE" mode
      When I mouse hover on any category
      Then I should see flyout menu

    @use_regression @artifact_legacy @bat_refactored_next @s4a_stable @release_13J @use_regression_2 @mode_domestic @priority_medium @review_stability @migrated_to_sdt
    Scenario: Legacy Page - Verify TOPNAV - FLYOUTS on Stores legacy page in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to new stores page
      Then I should see Ajax call to navapp for "flyout" in "SITE" mode
      When I mouse hover on any category
      Then I should see flyout menu

    @use_regression @artifact_legacy @bat_refactored_next @s4a_stable @release_13J @use_regression_2 @mode_domestic @priority_medium @review_stability @migrated_to_sdt
    Scenario: Legacy Page - Verify TOPNAV - FLYOUTS on legacy page in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to ways to shop page
      Then I should see Ajax call to navapp for "flyout" in "SITE" mode
      When I mouse hover on any category
      Then I should see flyout menu

    ########################################## Flyout Verification in Iship mode #####################################

     @use_regression @priority_medium @domain_discovery @mode_iship
    Scenario: ShoppingBagPage - Verify TOPNAV - FLYOUTS on Shopping bag page in ISHIP mode
      Given I visit the web site as a guest user
      When I navigate to international context page
      Then I change country to "India"
      And I close the welcome mat if it's visible
      When I add a random product to my bag and checkout
      Then I should see Ajax call to navapp for "flyout" in "SITE" mode
      When I mouse hover on any category
      Then I should see flyout menu

    Scenario: Legacy Page - Verify TOPNAV - FLYOUTS on Stores legacy page in ISHIP mode
      Given I visit the web site as a guest user
      When I change country to "India"
      And I close the welcome mat if it's visible
      And I navigate to stores legacy page
      Then I should see Ajax call to navapp for "flyout" in "SITE" mode
      When I mouse hover on any category
      Then I should see flyout menu


    ########################################## Flyout Verification in Registry mode #####################################

    @use_regression @priority_medium @domain_discovery @mode_registry
    Scenario: CategorySplashPage - Verify TOPNAV - FLYOUTS on Category page in REGISTRY mode
      Given I visit the web site as a guest user
      When I navigate to registry home page
      When I navigate to random category splash page
      And I mouse hover on any category
      Then I should see flyout menu

  # This scenario is no longer valid as per registry lean lab project. Hence added @use_deprecated and @use_wip tags
    @use_regression @priority_medium @domain_discovery @mode_domestic @wip
    Scenario: Capture Email Page - Verify TOPNAV - FLYOUTS on Wedding Registry Legacy Capture Email Page in REGISTRY mode
      Given I am on the registry capture email page as a guest user
      Then I should see Ajax call to navapp for "flyout" in "REGISTRY" mode
      When I mouse hover on any category
      Then I should see flyout menu

    ########################################## Category Links in Flyouts - Domestic mode #####################################

    @use_regression @priority_medium @domain_discovery @mode_domestic
    Scenario: HomePage - Verify TOPNAV - FLYOUTS navigation of sub category flyout links in DOMESTIC mode
      Given I visit the web site as a guest user
      When I click on sub category link in flyout
      Then I should see respective category page

    ########################################## Category Links in Flyouts - Iship mode #####################################

    @use_regression @priority_medium @domain_discovery @mode_iship
    Scenario: HomePage - Verify TOPNAV - FLYOUTS navigation of sub category flyout links in ISHIP mode
      Given I visit the web site as a guest user
      When I navigate to international context page
      When I change country to "India"
      And I click on sub category link in flyout
      Then I should see respective category page

    ########################################## Category Links in Flyouts - Registry mode #####################################
    @use_regression @priority_medium @domain_discovery @mode_domestic
    Scenario: HomePage - Verify TOPNAV - FLYOUTS navigation of sub category flyout links from Registry Home Page in REGISTRY mode
      Given I visit the web site as a guest user
      When I navigate to registry home page
      When I click on sub category link in flyout
      Then I should see respective category page

    @use_regression @priority_medium @domain_discovery @mode_domestic @dsv_desktop_sev2
    Scenario: Category Page - Verify TOPNAV - FLYOUTS on Non Secure and Secure Pages in DOMESTIC mode
      Given I visit the web site as a guest user
      Then I verify the non secure page category flyouts for all categories
      When I navigate to create profile page
      Then I verify the secure page category flyouts for all categories


        ################################### Top Nav, Flyout and Header Footer Elements in DOMESTIC mode #####################################

    #Test Case ID: BLCOM-80210
    @use_regression @priority_medium @domain_discovery @mode_domestic @dsv_desktop_sev2
    Scenario: Browse Page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to "Browse" page in "SITE" mode
      Then I verify dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Domestic"

    @use_regression @priority_medium @domain_discovery @mode_domestic
    Scenario: SubSplashPage  - Verify TOPNAV - FLYOUTS and new header footer elements verification in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to "Sub Splash" page in "SITE" mode
      Then I verify dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Domestic"

    @use_regression @priority_high @domain_discovery @mode_domestic
    Scenario: My account page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in My Account page in DOMESTIC mode
      Given I visit the web site as a guest user
      When I create a new profile
      Then I verify the My Account Pages are rendered properly
        | my account                  |
        | my profile                  |
        | my preferences              |
        | my address book             |
        | my wallet                   |
        | order status                |
        | furniture & mattress status |
        | gift card balance           |
        | wish list                   |
      And I should see Ajax call to navapp for "topnav" in "SITE" mode
      And I verify dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Domestic"

    @use_regression @priority_high @domain_discovery @mode_domestic
    Scenario: My wallet page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in My wallet page in DOMESTIC mode
      Given I visit the web site as a registered user
      When I navigate to My Wallet page from My Account page
      Then I should see Ajax call to navapp for "topnav" in "SITE" mode
      And I verify dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Domestic"

    @use_regression @priority_high @domain_discovery @mode_domestic
    Scenario: My wish list page  - Verify TOPNAV - FLYOUTS and new header footer elements verification on My wish list page in DOMESTIC mode
      Given I visit the web site as a registered user
      When I click on "wishlist" link from subNav
      Then I should be navigated to "wishlist" page
      And I should see Ajax call to navapp for "topnav" in "SITE" mode
      And I verify dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Domestic"

    @use_regression @priority_high @domain_discovery @mode_domestic
    Scenario: PDP page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to a random product
      Then I verify dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Domestic"

    @use_regression @priority_high @domain_discovery @mode_domestic
    Scenario: SearchResultsPage  - Verify TOPNAV - FLYOUTS and new header footer elements verification on 2nd browse page in DOMESTIC mode
      Given I am on SearchResultsPage for "dining" in Domestic mode
      When I click on next pagination button
      Then I verify dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Domestic"

    @use_regression @artifact_navapp @domain_discovery @artifact_shopapp @priority_high @mode_domestic @use_regression_3 @migrated_to_sdt
    Scenario: My offer page  - Verify TOPNAV - FLYOUTS and new header footer elements verification on My Offer page in DOMESTIC mode
      Given I visit the web site as a guest user
      When I navigate to promotions page
      And I should see the promotions page
      Then I should see Ajax call to navapp for "topnav" in "SITE" mode
      And I should see dynamic top navigation in "domestic" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section


    ################################### Top Nav, Flyout and Header Footer Elements in REGISTRY mode ######################

    #Test Case ID: MCOM-92083
    @use_regression @priority_high @domain_discovery @mode_registry
    Scenario: BrowsePage  - Verify TOPNAV - FLYOUTS and new header footer elements verification in REGISTRY mode
      Given I visit the web site as a guest user
      When I navigate to registry home page
      And I navigate to category browse page
      Then I verify dynamic top navigation in "registry" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Registry"

    @use_regression @priority_high @domain_discovery @mode_registry
    Scenario: PDP page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in REGISTRY mode
      Given I visit the web site as a guest user
      When I navigate to registry home page
      And I navigate to a random product
      Then I verify dynamic top navigation in "registry" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Registry"

    #Test Case ID: MCOM-92080
    @use_regression @priority_high @domain_discovery @mode_registry
    Scenario: Legacy Page - Verify TOPNAV - FLYOUTS and new header footer elements verification in REGISTRY mode
      Given I visit the web site as a guest user
      When I navigate to registry home page
      And I navigate to find registry on registry home page
      Then I should see Ajax call to navapp for "topnav" in "REGISTRY" mode
      Then I verify dynamic top navigation in "registry" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Registry"

    @use_regression @priority_high @domain_discovery @mode_registry
    Scenario: SearchResultsPage - Verify TOPNAV - FLYOUTS and new header footer elements verification in REGISTRY mode
      Given I am on SearchResultsPage for "dining" in REGISTRY mode
      When I click on next pagination button
      Then I verify dynamic top navigation in "registry" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Registry"

    ################################### Top Nav, Flyout and Header Footer Elements in ISHIP mode #########################

    #Test Case ID: MCOM-92109
    @use_regression @priority_high @domain_discovery @mode_iship
    Scenario: BrowsePage - Verify TOPNAV - FLYOUTS and new header footer elements verification in ISHIP mode
      Given I visit the web site as a guest user
      When I navigate to international context page
      When I change country to "India"
      And I navigate to category browse page
      Then I verify dynamic top navigation in "iship" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Iship"

    @use_regression @priority_high @domain_discovery @mode_iship
    Scenario: SubSplashPage  - Verify TOPNAV - FLYOUTS and new header footer elements verification in ISHIP mode
      Given I visit the web site as a guest user
      When I navigate to international context page
      When I change country to "India"
      And I navigate to "Sub Splash" page in "ISHIP" mode
      Then I verify dynamic top navigation in "iship" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Iship"


    @use_regression @priority_high @domain_discovery @mode_iship
    Scenario: PDP Page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in ISHIP mode
      Given I visit the web site as a guest user
      When I navigate to international context page
      And I change country to "a random country"
      When I navigate to a random product
      Then I verify dynamic top navigation in "iship" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Iship"

    @use_regression @priority_high @domain_discovery @mode_iship
    Scenario: Legacy Page  - Verify TOPNAV - FLYOUTS and new header footer elements verification in ISHIP mode
      Given I visit the web site as a guest user
      When I navigate to international context page
      When I change country to "a random country"
      And I close the welcome mat if it's visible
      When I navigate to international context page
      Then I should see Ajax call to navapp for "topnav" in "ISHIP" mode
      Then I verify dynamic top navigation in "iship" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Iship"


    @use_regression @priority_high @domain_discovery @mode_iship
    Scenario: SearchResultsPage  - Verify TOPNAV - FLYOUTS and new header footer elements verification in ISHIP mode
      Given I am on SearchResultsPage for "dining" in ISHIP mode
      When I click on next pagination button
      Then I verify dynamic top navigation in "iship" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "Iship"

    @use_regression @priority_high @domain_discovery @mode_iship
    Scenario Outline: DLPPage  - Verify TOPNAV - FLYOUTS and new header footer elements verification in ISHIP mode
      Given I am on DynamicLandingPage in "<shopping_mode>" mode
      When I click on next pagination button
      Then I verify dynamic top navigation in "<shopping_mode>" mode
      When I mouse hover on any category
      Then I should see flyout menu
      And I should see new header and footer elements section in "<shopping_mode>"
      Examples:
        | shopping_mode |
        | Domestic      |
        | Registry      |
        | Iship         |