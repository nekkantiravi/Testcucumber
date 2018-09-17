# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of footer functionality in domestic, iship and registry modes


 ########################################## Twitter Icon in Footer #####################################################

  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify that twitter icon redirects to correct page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I select twitter icon in the footer
    Then I should be navigated to macys twitter page

  @use_regression @domain_discovery @priority_high @mode_registry @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify that twitter icon redirects to correct page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I select twitter icon in the footer
    Then I should be navigated to macys twitter page

  @use_regression @domain_discovery @priority_high @mode_iship @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify that twitter icon redirects to correct page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    And I select twitter icon in the footer
    Then I should be navigated to macys twitter page

  ################################################## Catalogs link in Footer ###########################################

  #Test Case ID: MCOM-66726
  #Testlink-MCOM-66726
  #Vone  - RT-07344
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Footer - Verify the Catalogs link in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify basic attributes in online catelogs page

#    When I navigate to the online catalogs page
#    And I enter a zipcode and select a store
#    Then I should navigate to the catalog page
#    And I verify the basic attributes of the catalog page
  # As the page is navigating to 3rd party site writing all the verification code in single step
    # Notes:
    # Enter a zip code and access the catalog
    # Browse through the catalog and add a product to shopping list
    # Add to shopping list button should change to remover from shopping list
    # And I should not see "ADD ITEM TO LIST" button
    # "catalogs" link display in the footer section under the OUR STORES link section
    # clicking on "catalogs" link should display dynamic pop up layer
    # enter "Zip code" in to the zip code text box and click Go button
    # second dynamic pop up layer display with available stores for selected zip code
    # from the second dynamic pop up select any available store
    # then user can see the online catalogs page
    # attributes need to be verified in the online catalogs page:
    # online catalogs page should contain available catalogs associate for given zip code (as thumbnail images)
    # clicking any catalog thumbnail should navigate to the correct catalog page - "Macy's - <catalog name>" page
    # attributes need to be verified in the catalog page:
    # mouse hover the cover of the catalog page and then user should see pop up box which display catalog details
    # clicking on any product image on catalog page should display "Add to shopping list" dynamic layer
    # in the "Add to shopping list" dynamic layer i should verify:
    # image of the clicked product
    # product name , web id, original and current price
    # valid time period , "SHOP NOW" button  , "ADD ITEM TO LIST" button
    # social icons , product description , available colors
    # available sizes, Product Features
    # clicking "ADD ITEM TO LIST" button should display same dynamic layer with "REMOVE FROM LIST" button

  #################################################### Customer Service links in Footer ################################

  #Test Case ID: MCOM-86868
  #Testlink-MCOM-86868
  #Vone - RT-07347
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run
  Scenario: Footer - Verify Customer Service Footer links in Legacy Page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify all links for Customer Service in the Footer
    And I verify the basic attributes of Customer Service links in the Footer
    # Notes:
    # Legacy page: From For the Home - Social Shops - click on any social campaign links
    # Customer service Links -order tracking, shipping & delivery, returns, contact us, para ayuda
    # Basic attributes to verify
    # clicking on customer service link should navigate to the "customer service" page
    # attributes need to be verified in the "customer service" page
    # should display "customer service" page properly
    # should see the page with title "customer service"

  @use_regression @domain_discovery @priority_high @mode_registry @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify Customer Service Footer links in Navapp page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I verify all links for Customer Service in the Footer
    And I verify the basic attributes of Customer Service links in the Footer
    # Notes:
    # Nav app page in registry mode: Navigate to Wedding Registry and click on any FOB e.g Dining and Entertainment
    # Customer service Links -order tracking, shipping & delivery, returns, contact us, para ayuda
    # Basic attributes to verify
    # clicking on customer service link should navigate to the "customer service" page
    # attributes need to be verified in the "customer service" page
    # should display "customer service" page properly
    # should see the page with title "customer service"

  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario: Footer - Verify Customer Service Footer links in My Account Shopapp Page in DOMESTIC mode
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I verify all links for Customer Service in the Footer
    And I verify the basic attributes of Customer Service links in the Footer
    # Notes:
    # Shop app page:   My account page   where URL contains /account
    # Customer service Links -order tracking, shipping & delivery, returns, contact us, para ayuda
    # Basic attributes to verify
    # clicking on customer service link should navigate to the "customer service" page
    # attributes need to be verified in the "customer service" page
    # should display "customer service" page properly
    # should see the page with title "customer service"


  #################################################### Macys Credit Card links in Footer ###############################

  #Test Case ID: MCOM-86868
  #Testlink-MCOM-86868
  #Vone - RT-07347
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run
  Scenario: Footer - Verify Macys Credit card Footer links in Legacy Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify all links for Credit Services in the Footer
    And I verify the basic attributes of Credit Services links in the Footer
    # Notes:
    # Basic attributes to verify
    # clicking on MACY'S CREDIT CARD link should navigate to the "MACY'S CREDIT CARD" page
    # attributes need to be verified in the "MACY'S CREDIT CARD" page
    # should display "MACY'S CREDIT CARD" page properly
    # should see the page with title MACY'S CREDIT CARD

  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify Macys Credit card Footer links in Navapp Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then I verify all links for Credit Services in the Footer
    And I verify the basic attributes of Credit Services links in the Footer
    # Notes:
    # Basic attributes to verify
    # clicking on MACY'S CREDIT CARD link should navigate to the "MACY'S CREDIT CARD" page
    # attributes need to be verified in the "MACY'S CREDIT CARD" page
    # should display "MACY'S CREDIT CARD" page properly
    # should see the page with title MACY'S CREDIT CARD

  #Adding @wip as responsive sing-in page won't have Header FOB menu & Flyouts for this scenario validation
  #TestLink - MCOM-96625
  # Vone - RT-07464
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @wip
  Scenario: Footer - Verify Macys Credit card Footer links in Shopapp Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    Then I verify all links for Credit Services in the Footer
    And I verify the basic attributes of Credit Services links in the Footer
    # Notes:
    # Basic attributes to verify
    # clicking on MACY'S CREDIT CARD link should navigate to the "MACY'S CREDIT CARD" page
    # attributes need to be verified in the "MACY'S CREDIT CARD" page
    # should display "MACY'S CREDIT CARD" page properly
    # should see the page with title MACY'S CREDIT CARD

  #################################################### Our Stores links in Footer ######################################

  #Test Case ID: MCOM-86868
  #Testlink-MCOM-86868
  #Vone - RT-07474 - RT-07347
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify Our Store Footer links in Legacy Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify all links for Our Stores in the Footer
    And I verify the basic attributes of Our Stores links in the Footer
    # Notes:
    # Legacy page: From For the Home - Social Shops - click on any social campaign links
    # Basic attributes to verify
    # clicking on OUR STORES link should navigate to the "OUR STORES" page
    # attributes need to be verified in the "OUR STORES" page
    # should display "OUR STORES" page properly
    # should see the page with title "OUR STORES"

  #################################################### Macys Inc links in Footer #######################################

  #Testlink-MCOM-86868
  #Test Case ID: MCOM-86868 TestLink - MCOM-96616
  #Vone - RT-07471 - RT-07347
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify MACY'S INC Footer links in Navapp page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I verify all links for MACY'S INC. in the Footer
    And I verify the basic attributes of MACY'S INC. links in the Footer
    # Notes:
    # Basic attributes to verify
    # clicking on macysJOBS link should navigate to the "macysJOBS" page
    # attributes need to be verified in the "macysJOBS" page
    # should display "macysJOBS" page properly
    # should see the page with title "macysJOBS"

  ################################################# Stay Connected links in Footer ###################################

  #Test Case ID: MCOM-86868
  #Testlink-MCOM-86868
  #Vone - RT-07347
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify Stay Connected Footer links in Home page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify all links for Stay Connected in the Footer
    And I verify the stay connected pages are rendered properly
      # Notes:
      # Basic attributes to verify
      # clicking on Facebook link should navigate to the "Facebook" page

  ####################################################  WHAT'S HAPPENING AT MACY'S links in Footer #####################

  #Test Case ID: MCOM-86868
  #Testlink-MCOM-86868
  #Testlink-MCOM-96636
  #Vone - RT-07347 - RT-07350
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario: Footer - Verify  WHAT'S HAPPENING AT MACY'S links in Home page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the WHAT'S HAPPENING AT MACY'S link in the Footer
    And I verify the  WHAT'S HAPPENING AT MACY'S page is rendered properly

#################################################### Legal notice links in Footer ######################################

  #Testlink-MCOM-96620
  #Vone - RT-07348
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run
  Scenario Outline: Footer - Verify links from copyright section in Home page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify all legal notice links in the Footer
    And I verify the legal notice pages "<Interest Based Ads>" are rendered properly
    Examples:
      | Interest Based Ads                   |
      | Legal Notice                         |
      | Pricing Policy                       |
      | Privacy Practices                    |
      | Interest Based Ads                   |
      | Customer Bill of Rights              |
      | California Privacy Rights            |
      | CA Transparency in Supply Chains Act |
      | Product Recalls                      |
      # Notes:
      # Basic attributes to verify
      # clicking on Legal Policy link should navigate to the "Legal policy" in customer services page

  ################################################## Signup for Emails in Footer #######################################

  #Testlink-MCOM-96629
  #Vone - RT-07349
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Footer - Verify the Signup for emails in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify signup for emails functionality and navigate back to homepage
  #Notes:
  #In signup page click on the Shop macys.com and verify its navigate dto home page
  #QE Comments: signup for emails link is navigating to 3rd party page so did all the verifications in single step
  # and navigating back to homepage

  #################################################### Ad Spots in Footer ##############################################

  #Test Case ID: MCOM-72946
  @use_regression @domain_discovery @priority_high @mode_domestic @discovery_daily_run @xbrowser_hfr
  Scenario Outline: Footer - Verify the updated site footer Ad Spot in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I verify the "<Ad_Spot_links>" are rendered properly
  Examples:
    | Ad_Spot_links        |
    | macys video channel  |
    | green living         |
    | the magic of giving  |
    # Notes:
    # Verify the display of "TURN OVER A NEW LEAF", "MACYS BACKSTAGE"  , "the magic of giving"
    # Click each icon and verify the correct page is displayed
    # Verify ALT text for each icon

  #################################################### Easy Web Browser link in Footer #################################

  #Test Case ID: MCOM-72946, MCOM-56949
  #Vone - RT-07473
  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: Footer - Verify the Easy web Browser link in footer in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category browse page
    Then I verify the Easy Web browser link is rendered properly
    # eEssential Accesability - easy web browser
