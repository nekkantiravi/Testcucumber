############################################
# Author: Discovery Regression QE Team
# Date: 20th June 2017
# Date Modified: None
############################################

Feature: Verify header and footer sections dsv features in DOMESTIC, ISHIP and REGISTRY mode

  #TestLink - MCOM-96617 Vone - RT-07472
  @use_dsv @dsv_desktop_sev1 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic
  Scenario: HeaderAndFooter - Verify the Macys Logo link in header in DOMESTIC mode
    Given I am on Browse Page for "Activewear" under "MEN"
    Then I verify that Macys logo takes to Home Page
  # Click on the Macys link from browse page and make sure it navigates to Home page

  @use_dsv  @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic
  Scenario: HeaderAndFooter - Verify FOB id prefixed with 'flex' in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the id of the HTML should contain flex for FOBs

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_domestic
  Scenario: CategorySplashPage - Verify TOPNAV - FLYOUTS and new header footer elements verification in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to "WOMEN" category page
    Then I should see dynamic top navigation in "domestic" mode
    When I mouse over random category from top navigation
    Then I should see flyout menu
    And I should see new header and footer elements section

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_domestic
  Scenario: BrowsePage - Verify TOPNAV - FLYOUTS and new header footer elements verification in DOMESTIC mode
    Given I visit the web site as a guest user
    Given I am on Browse Page for "Jeans" under "MEN"
    Then I should see dynamic top navigation in "domestic" mode
    When I mouse over random category from top navigation
    Then I should see flyout menu
    And I should see new header and footer elements section

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @discovery_daily_run
  Scenario: SubSplashPage - Verify TOPNAV - FLYOUTS and new header footer elements verification in DOMESTIC mode
    Given I am on Browse Page for "Activewear" under "MEN"
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section

  @use_regression @migrated_to_sdt @domain_discovery  @dsv_desktop_sev2 @use_domestic @discovery_daily_run
  Scenario: CategorySplashPage - Verify TOPNAV - FLYOUTS on Non Secure and Secure Pages in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the non secure page category flyouts for all categories
    When I navigate to my account page
    Then I verify the secure page category flyouts for all categories

    ########################### GNA & GFA ###############################################################

   @dsv_desktop_sev2 @preview_desktop @dsv_dryrun @use_regression @migrated_to_sdt @domain_discovery @mode_domestic
  Scenario: HeaderAndFooter - Verify 3 images links under GFA - Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify the image links under GFA in domestic mode

   @dsv_desktop_sev2 @use_regression @migrated_to_sdt @domain_discovery @mode_domestic
  Scenario: HeaderAndFooter - Verify 4 GNAs are present on Home Page, Category Page, Category Browse Page & PDP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    When I navigate to "JEWELRY" category page
    Then I verify the display of GNA
    When I navigate to "JEWELRY" category page
    And I navigate to "Necklaces" browse page from cat splash page
    Then I verify the display of GNA
    When I navigate to a product having "orderable and available" attributes
    Then I verify the display of GNA

    #Test Case ID: MCOM-86814
  @use_regression @migrated_to_sdt @domain_discovery @mode_domestic @priority_medium  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify GFA Links are present on Home Page, Category Page, Category Browse Page & PDP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GFA
    When I navigate to "HANDBAGS" category page
    Then I verify the display of GFA
    And I navigate to "Backpacks" browse page from cat splash page
    Then I verify the display of GFA
    When I navigate to a product having "orderable and available" attributes
    Then I verify the display of GFA

  @use_regression @migrated_to_sdt @domain_discovery @medium @use_dsv @mode_registry @use_prod @priority_medium  @dsv_desktop_sev2 @discovery_daily_run
  Scenario Outline: HeaderAndFooter - Verify GNA and GFA should be displayed on different pages of REGISTRY mode
    Given I visit the web site as a guest user
    When I visit the registry "<page>" page
    Then I verify the display of GNA and GFA
    Examples:
      | page    |
      | home    |
      | splash  |
      | browse  |
      | product |

  @use_regression @migrated_to_sdt @domain_discovery @medium @use_dsv @mode_registry @priority_medium  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify 3 image links under GFA on Home Page in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    Then I verify the image links under GFA in registry mode

  #Test Case ID: MCOM-92110
  @use_regression @migrated_to_sdt @domain_discovery @mode_iship @medium @use_dsv @use_prod @priority_medium  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify GNA in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I navigate to random browse page
    Then I verify the display of GNA

    #################################### Header Verification ######################################

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_28 @mode_domestic  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify Header - HOME in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "HOME" category page
    Then I should verify all header links and images are functioning properly
    # Notes:
    # Do full header validation

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_28 @s4a_stable @mode_domestic  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify Header - BEAUTY in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "BEAUTY" category page
    Then I should verify all header links and images are functioning properly
    # Notes:
    # Do full header validation

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_28 @mode_domestic  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify Footer - HOME in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "HOME" category page
    Then I should verify all footer links and images are functioning properly
    # Notes:
    # Do full footer validation

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_28 @s4a_stable @mode_domestic  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: HeaderAndFooter - Verify Footer - BEAUTY in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "BEAUTY" category page
    Then I should verify all footer links and images are functioning properly
    # Notes:
    # Do full footer validation

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_15 @mode_registry  @dsv_desktop_sev2 @discovery_daily_run
  Scenario: HeaderAndFooter - Verify Header - KITCHEN - in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I visit the "KITCHEN" category page from the registry
    Then I should verify all header links and images are functioning properly
    # Notes:
    # Do full header validation

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_15 @mode_registry  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify Header - BED & BATH - in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I visit the "BED & BATH" category page from the registry
    Then I should verify all header links and images are functioning properly
    # Notes:
    # Do full header validation

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_15 @mode_registry  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify Footer - DINING & ENTERTAINING - in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I visit the "DINING" category page from the registry
    Then I should verify all footer links and images are functioning properly
    # Notes:
    # Do full footer validation

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @use_dsv @dsv_category_15 @mode_registry  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify Footer - LUGGAGE - in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I visit the "LUGGAGE" category page from the registry
    Then I should verify all footer links and images are functioning properly
    # Notes:
    # Do full footer validation

  @use_regression @migrated_to_sdt @domain_discovery @mode_registry  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify new Header footer in registry mode
    Given I visit the web site as a guest user in "registry" mode
    Then I should see new header and footer elements section
    When I navigate to "KITCHEN" category page
    Then I should see page navigated to "shop/wedding-registry" pattern url
    When I navigate to "Bakeware" browse page from cat splash page
    And I select "quick view" button for "member" product on page
    Then I verify that landed on "quick_view" Page
    When I click add to bag button on QuickView page
    And I click checkout button on QuickView page
    Then I verify that landed on "shopping_bag" Page
  # Notes:
  # Expected Results:
  # 1. macys.com home page should be displayed.
  # 2.a) Verify that macys wedding registry home page is displayed.
  # b) Verify that Bigger search box is displayed with ""Search or Enter Web Id"" Message.
  # c) Verify that Category Navigation links should have black background color.
  # d) Verify ""my bag"" link beside ""search box"" link.
  # e) Verify that, all header links should display properly
  # f) Verify that social icons should display with title ""Stay Connected"" under footer section
  # g) Verify that essential accessibility logo should display under footer section.
  # h) Verify that ""Visually impaired customers: download our new user assistance tool"" text is displayed under footer section.
  # i) Verify that Legal disclaimers: CA Privacy, Copyright, Privacy, Product Recalls and Legal Notice are displayed under the footer section.
  # 3.a) Verify that for all text based links anchor tag should have respective text.
  # b) Verify that for all images alt text should display.
  # c) Verify that anchor tag for kitchen FOB should have site promo tag ""cm_sp"".
  # Ex: http://www.macys.com/shop/wedding-registry/kitchen?id=7497&cm_sp=global_nav_reg-_-kitchen-_-n
  # d) c) Verify that anchor tag for store location & hours link should have site promo tag ""cm_sp"".
  # Ex: http://www1.macys.com/store/locator/index.ognc?cm_sp=navigation-_-bottom_nav-_-store_locations_hours
  # 4. a) Verify that user will be navigated to kitchen category page in registry mode by displaying navigation URL as ""http://www.macys.com/shop/wedding-registry/kitchen?id=7497&cm_sp=global_nav_reg-_-kitchen-_-n"".
  # b) Verify that selected category is highlighted by changing font color from white to grey.
  # 5. Verify that user is navigated to Bakeware browse page.
  # 6. Verify that ""Quick View"" Overlay is displayed.
  # 7. Verify that Add to bag confirmation layer is displayed.
  # 8. Verify that user will be returned to browse page.
  # 9. Verify that added product should display in the expandable quick bag on top of polling widget.

  @use_regression @migrated_to_sdt @domain_discovery @priority_medium @mode_domestic  @dsv_desktop_sev2
  Scenario: Home Page - Verify HFR Design in DOMESTIC
    Given I visit the web site as a guest user
    Then I should see page navigated to "https://www." pattern url
    When I navigate to "HOME" category page
    Then I should see page navigated to "https://www." pattern url
    When I navigate to "Rugs" browse page from cat splash page
    Then I should see page navigated to "https://www." pattern url

    #################################################### SiteMap links in Footer #########################################

  @use_regression @migrated_to_sdt @domain_discovery @priority_high @mode_domestic  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify the Sitemap Link in Global Footer in the nav app pages in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to "Sitemap" link in the footer panel
    Then I verify the Site Map page is displayed
    # Notes:
    #  Test case description:
    #  1.Navigate to macys.com
    #  2.Click on Sitemap link at the footer
    #  Test case expected results:
    #  1. macys.com home page should be displayed.
    #  2.It should navigate to ""Macy's Site Map"" page.

  #Test Case ID: MCOM-92112 BLCOM-80274
  #Testlink-BLCOM-84273
  #vone-RT-06320
  @use_regression @migrated_to_sdt @priority_high @domain_discovery @mode_domestic @mode_iship @mode_registry  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify all footer links in Home Page in All mode
    Given I visit the web site as a guest user
    Then I verify the footer links in "Domestic" Mode
    When I navigate to registry home page
    And I verify the footer links in "Registry" Mode
    And I navigate to international context page
    And I change country to "Australia"
    Then I verify the footer links in "Iship" Mode
      # Notes:
      #    BCOM Footer links in Domestic Mode:
      #    And in the footer I should see "Customerservice"
      #    And in the footer I should see "Orderstatus"
      #    And in the footer I should see "CreditServices"
      #    And in the footer I should see "PayBillOnline"
      #    And in the footer I should see "Loyallist"
      #    And in the footer I should see "AboutUs"
      #    And in the footer I should see "Careers"
      #    And in the footer I should see "Emailsignup"
      #    And in the Header I should see "Sign In" link
      #    BCOM Footer links in iship Mode:
      #    And in the footer I should see "Customerservice"
      #    And in the footer I should see "Orderstatus"
      #    And in the footer I should see "InternationalShopping"
      #    And in the footer I should see "AboutUs"
      #    And in the footer I should see "Careers"
      #    And in the Header I should not see "Sign In" link
      #    And in the Header I should not see "My Account link" link
      #    And in the footer I should not see "CreditServices"
      #    And in the footer I should not see "PayBillOnline"
      #    And in the footer I should not see "Loyallist"
      #    MCOM Footer links in Domestic Mode:
      #    And in the footer I should see "Macys Credit Card"
      #    And in the footer I should see "Pay bill Online"
      #    And in the footer I should see "apply now & Save"
      #    And in the footer I should see "Locations & hours"
      #    And in the Header I should see "sign in" link
      #    MCOM Footer links in Iship Mode:
      #    And in the footer I should not see "Macys Credit Card"
      #    And in the footer I should not see "Pay bill Online"
      #    And in the footer I should not see "apply now & Save"
      #    And in the footer I should not see "Locations & hours"
      #    And in the Header I should not see "sign in" link
      #    And in the Header I should not see "my account" link
      #    And in the footer I should see "frequently asked questions"
      #    And in the footer I should see "find a store"
      #    And in the footer I should see "visitors services"

  @use_regression @migrated_to_sdt @priority_high @domain_discovery @mode_domestic @mode_registry  @dsv_desktop_sev2
  Scenario Outline: HeaderAndFooter - Verify the my account header functionality in domestic and registry mode
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I hover over the myaccount link from topNav header
    Then I verify the myaccount sublinks from topNav header
    And I navigate to random sublinks from my account as a "<user_type>" user in "<mode>" mode
    Examples:
      | user_type  | mode     |
      | guest      | site     |
      | registered | site     |
      | guest      | registry |

  @use_regression @migrated_to_sdt @priority_high @domain_discovery @mode_iship @mode_registry  @dsv_desktop_sev2
  Scenario: HeaderAndFooter - Verify Wedding Registry selection landed on Customer service page in Iship Mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Australia"
    And I select "Wedding Registry" from sub nav menu
    Then I should be navigated to customer service page

  #Test Case ID: MCOM-86814
  @use_regression @domain_discovery @use_dsv @mode_domestic @priority_medium  @dsv_desktop_sev2 @migrated_to_sdt
  Scenario:  Multiple pages - Verify GFA Links are present on Home Page, Category Page, Category Browse Page & PDP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GFA
    When I navigate to "HANDBAGS" category page
    Then I verify the display of GFA
    And I navigate to "Backpacks" browse page from cat splash page
    Then I verify the display of GFA
    When I navigate to a product having "orderable and available" attributes
    Then I verify the display of GFA

  #Test Case ID: MCOM-92023, MCOM-86814
  @use_regression @domain_discovery @use_dsv @mode_domestic @priority_medium  @dsv_desktop_sev2 @migrated_to_sdt
  Scenario: Multiple pages - `Verify 4 GNAs are present on Home Page, Category Page, Category Browse Page & PDP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    When I navigate to "JEWELRY" category page
    Then I verify the display of GNA
    When I navigate to "JEWELRY" category page
    And I navigate to "Necklaces" browse page from cat splash page
    Then I verify the display of GNA
    When I navigate to a product having "orderable and available" attributes
    Then I verify the display of GNA

  @dsv_desktop_sev1 @domain_discovery @mode_registry @use_regression @migrated_to_sdt @discovery_daily_run
  Scenario: Verify links in registry home page as a guest user
    Given I visit the web site as a guest user in "registry" mode
    And I should see expected media types on registry home page as in below table:
      | find registry                |
      | create registry              |
      | manage registry              |
      | registry star reward         |
      | registry events              |
      | registry Tailgate Time       |