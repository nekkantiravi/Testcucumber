#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify Left Nav - refine my browse results using BOPS enabled stores

  #TestLink-BLCOM-84311
  #Vone - RT-06466
  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify redirect to same browse page in ISHIP mode
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I navigate to the "Electrics" browse page under "HOME"
    When I navigate to international context page
    And I change country to "a random country"
    When I select browse 'back' button
    Then I should be navigated to Electrics browse page
  #Notes: After navigating to Iship mode the redirected browse page should be the same as in DOMESTIC mode

  # TestLink -BLCOM-84133
  # Vone - RT-06478
  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify currency in quick bag in ISHIP mode
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I navigate to international context page
    And  I change country to "India"
    Then I close the welcome mat if it's visible
    When I add a random product to bag
    And I navigate back to "Home" page
    Then I verify price details for selected currency on Quickbag

  #Vone - RT-06283
  #Testlink-BLCOM-84139
  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify that iship ineligible subcategory is suppressed in ISHIP mode
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I navigate to the "Electrics" browse page under "HOME"
    Then I should see subcategory link in Left Nav
    When I navigate to international context page
    And I change country to "a random country"
    When I select browse 'back' button
    And I should be navigated to Electrics browse page
    Then I should see subcategory link are supressed in Left Nav which are not eligible for iship

  #Vone - RT-06277 - RT-06270
  #Testlink-BLCOM-76303, Testlink-BLCOM-64732
  @use_regression @priority_low @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verification for no 404 Errors and no 2nd call requests in DOMESTIC mode
    Given I am on CategoryBrowsePage for "Jeans" under "Women" in Domestic mode
    And I clear existing class variables to avoid data issues
    Then I verify that 404 error code is not displayed in browse page
  # And I should not see second call request
  # For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step

  #Notes: #Verify no 404 Error status codes should be displayed in http watch
  #Verify that no second call request should be sent

  #Vone - RT-06277 - RT-06270
  #Testlink-BLCOM-76303, Testlink-BLCOM-64732
  @use_regression @priority_low @domain_discovery @mode_iship
  Scenario: BrowsePage - Verification for no 404 Errors and no 2nd call requests in ISHIP mode
    Given I am on CategoryBrowsePage for "Jeans" under "Women" in Iship mode
    And I clear existing class variables to avoid data issues
    When I close the welcome mat if it's visible
    Then I verify that 404 error code is not displayed in browse page
  #  And I should not see second call request
  # For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step

  #TestLink-BLCOM-84166
  @use_regression @priority_high @domain_discovery @mode_registry
  Scenario: BrowsePage - Verify the display of star ratings and promotion bubble in REGISTRY mode
    Given I am on CategoryBrowsePage for "1004713" category id in Registry mode
    And I clear existing class variables to avoid data issues
    Then I verify the promotions are displayed

  #Vone - RT-06266
  #Testlink-BLCOM-84118
  @use_regression @priority_low @artifact_navapp @domain_discovery @mode_iship @use_manual
  Scenario: BrowsePage - Verify Non Suppressed Master non suppressed member product display in ISHIP mode
    Given I visit the web site as a guest user
    And I find a master product that has an unsuppressed member product in ISHIP mode
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I navigate to the Browser Page that displays the product in ISHIP mode
    Then I should see the product in the Browser Page

  #Testlink-BLCOM-84118
  #Vone - RT-06266
  @please_automate @priority_low @artifact_navapp @domain_discovery @mode_iship @use_manual
  Scenario: BrowsePage - Verify Non Suppressed Master suppressed member products display in ISHIP mode
    Given I visit the web site as a guest user
    And I find a master product that has all the member products suppressed in ISHIP mode
    When I change country to "India"
    And I close the welcome mat if it's visible
    And I navigate to the Browser Category that displays the product
    Then I should not see the product in the Browser Category

  ############################### REGISTRY MODE ##########################################################

  #Testlink-BLCOM-84252,Testlink-BLCOM-84244 Vone-RT-06510
  @use_regression @priority_high @domain_discovery @mode_registry
  Scenario: BrowsePage - Verify Quick View overlay details for standard product in REGISTRY mode
    Given I am on CategoryBrowsePage for "8149" category id in Registry mode
    And I clear existing class variables to avoid data issues
    When  I select "quick view" button for "member" product on page
    Then  I verify that quick peek is displayed
    And   I should see add to wishlist in quick view overlay
    And   I verify basic attributes of quick view dialog in "registry" mode
  # Note : overly same for member and standard product

  #Notes: a) Verify basic attributes of quick view for standard product
  #  See full product details,product description,
  #  Image of the item, Alternate images, Item Name, Item price,  Product description
  #  Color (If applicable), Availability & Shipping details, Quantity Drop down , Add to bag button
  # b) Add to registry option not displayed in Quick view



  ###########################################################################################################


  @spo_4711 @release_14G @use_regression @wip @use_regression_1 @domain_discovery @artifact_navapp @mode_domestic @priority_medium
  Scenario: BrowsePage - Verify that product thumbnails displayed as existing  when navigated through browser back button after browse grid implementation in DOMESTIC mode
    Given I am on Browse Page for "Burberry" under "HANDBAGS"
    And I clear existing class variables to avoid data issues
    When I select random product from thumbnail grid
    Then I should be redirected to PDP page
    When I select browse 'back' button
    Then I verify that all the product thumbnails displayed properly on the Category Browse page


  #Vone - RT-06267
  #Testlink-BLCOM-84118
  @priority_low @artifact_navapp @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify General URL re-direction rule in DOMESTIC mode
    Given I am on Browse Page for "Burberry" under "HANDBAGS"
    And I clear existing class variables to avoid data issues
    When I select Sign In link from header and sign in from the current page
   # Then I should redirect to the previous browse page
    Then I should be in Search Landing or redirected Browse Page page

  #Testlink-BLCOM-84246 Vone - RT-06511
  @priority_low @artifact_navapp @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify the display of "See Full Product Details" link in QV layer in DOMESTIC mode
    Given I am on Browse Page for "Burberry" under "HANDBAGS"
    And I clear existing class variables to avoid data issues
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    When I select 'see full product details' link from the quickview dialog
    Then I should be redirected to PDP page


  #Note: below functionality is not applicable on BCOM. Hence commented the step
  #    Then I verify on mouse hover product description should underline properly
  @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify price display for standard product in DOMESTIC mode
    Given I am on Browse Page for "Burberry" under "HANDBAGS"
    And I clear existing class variables to avoid data issues
    And I verify that products are displayed with price


#Testlink-BLCOM-84279 Vone-RT-06518
  @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify E-Gift card browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I navigate to "egift" category browse page
    Then I verify that all the product thumbnails displayed properly on the Category Browse page

  @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify Gift card browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I navigate to "gift" category browse page
    Then I verify that all the product thumbnails displayed properly on the Category Browse page
  # Notes:
  # All the thumbnails should be properly display with price range, image, description
  # Facets should be displayed
  # Verify all the images are loaded properly
  # Verify SORT BY option is displayed
  # Verify in - store Pickup facet is displayed

  #######################################################################################

   #Testlink-BLCOM-84289
  #vone-RT-06314
  @priority_high @artifact_navapp @domain_discovery @mode_iship @use_manual
  Scenario: BrowsePage - Verify error message for unavailable category ad pools in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I am on CatSplash Page for "WHAT'S NEW"
    When I navigate to the "Chanel" browse page under "Beauty"
    Then I should see the "The selected category cannot be shipped to Australia." page

  #Vone - RT-06264
  #Testlink-BLCOM-73738
  @priority_low @artifact_navapp @domain_discovery @mode_iship @use_manual
  Scenario: BrowsePage - Verify Suppressed Master non suppressed member products display in ISHIP mode
    Given I visit the web site as a guest user
    And I find a member product that has its master product suppressed in ISHIP mode
    When I change country to "Australia"
    Then I navigate to the "Jeans" browse page under "Women"
    Then I verify that all the product thumbnails displayed properly on the Category Browse page
    #And I should not see the master product in the Browser Category

  @spo_4711 @release_14G @use_regression @wip @use_regression_1 @domain_discovery @artifact_navapp @mode_domestic @priority_medium @use_manual
  Scenario Outline: BrowsePage - Verify that product thumbnails displayed as existing  when navigated through browser back button after browse grid implementation on ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "<Country>"
    And I close the welcome mat if it's visible
    Then I should see ISHIP Header or Footer with "<Country_Code>" flag
    When I navigate to category browse page
    When I select a random product
    Then I should be redirected to PDP page
    When I click on the browser back button
    Then I should see all the product thumbnails displayed properly on the browse page
    Examples:
      | Country | Country_Code |
      | India   | IN           |
      | Canada  | CA           |

  # TestLink -BLCOM-84133
  # Vone - RT-06478
  @priority_high @artifact_navapp @domain_discovery @mode_iship
  Scenario: BrowsePage - Verify currency in quick bag from Legacy page in ISHIP mode
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I change country to "India"
    And I close the welcome mat if it's visible
    And I add a random item to bag from quick view
    Then I verify price on quick view overlay is same as on thumbnail grid

  #Vone - RT-06277 - RT-06270
  #Testlink-BLCOM-76303, Testlink-BLCOM-64732
  @priority_low @artifact_navapp @domain_discovery @mode_domestic @use_manual
  Scenario: BrowsePage - Verification for no 404 Errors and no 2nd call requests in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to any category browse page
    Then I verify that 404 error code is not displayed in browse page
  # And I should not see second call request
  # For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step

  #Notes: #Verify no 404 Error status codes should be displayed in http watch
  #Verify that no second call request should be sent

  #Vone - RT-06277 - RT-06270
  #Testlink-BLCOM-76303, Testlink-BLCOM-64732
  @priority_low @artifact_navapp @domain_discovery @mode_iship @use_manual
  Scenario: BrowsePage - Verification for no 404 Errors and no 2nd call requests in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I navigate to category browse page
    Then I verify that 404 error code is not displayed in browse page
  #  And I should not see second call request
  # For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step

  #TestLink-BLCOM-84166
  @priority_high @artifact_navapp @domain_discovery @mode_registry @use_manual
  Scenario: BrowsePage - Verify the display of star ratings and promotion bubble in REGISTRY mode
    Given I am on the registry home page
    When I navigate to "Bed & Bath" category page
    And I navigate to "Duvets & Shams" browse page from cat splash page
    And I select a product having badge text
    Then I verify that promo text and promo popup is displayed under product thumbnail


    #Vone - RT-06283
  #Testlink-BLCOM-84139
  @not_automated @priority_high @artifact_navapp @domain_discovery @mode_iship @use_manual
  Scenario: BrowsePage - Verify that iship ineligible subcategory is suppressed in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to a category that has subcategory excluded from country other than US
    Then I should see subcategory link in Left Pane
    When I change country to "the country"
    Then I should not see subcategory link in Left Pane

    #TestLink-BLCOM-84311
  #Vone - RT-06466
  @priority_high @artifact_navapp @domain_discovery @mode_iship @not_automated @use_manual
  Scenario: BrowsePage - Verify redirect to same browse page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to category browse page with iship unsuppressed category
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    Then I should be navigated to same category browse page
  #Notes: After navigating to Iship mode the redirected browse page should be the same as in DOMESTIC mode