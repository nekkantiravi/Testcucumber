# Author: Discovery Regression QE Team
# Created Date: 11/17/2016

Feature: MCOM :: Header anf Footer scenarios missing from current scope

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: CategorySplashPage - Domestic|Iship|Registry - Verify accessibility label on global search field
    Given I am on CatSplashPage in <shopping_mode> mode
    Then The "search_field" field should have a label with the text "Search or enter web ID"
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify accessibility label on global search field
    Given I am on SubSplashPage in <shopping_mode> mode
    Then The "search_field" field should have a label with the text "Search or enter web ID"
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify accessibility label on global search field
    Given I am on BrowsePage in <shopping_mode> mode
    Then The "search_field" field should have a label with the text "Search or enter web ID"
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: CampaignPage - Domestic|Iship|Registry - Verify accessibility label on global search field
    Given I am on CampaignPage in <shopping_mode> mode
    Then The "search_field" field should have a label with the text "Search or enter web ID"
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |
  # Ex: '/campaign/social?campaign_id=XXX' pattern url's

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: LegacyPage - Domestic|Iship|Registry - Verify accessibility label on global search field
    Given I am on LegacyPage in <shopping_mode> mode
    Then The "search_field" field should have a label with the text "Search or enter web ID"
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |
  # Ex: '/ce/splash' pattern url's

  ################################ Ad pools verification for header and footer in Domestic Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Sub Splash page - Verify the header footer adpools are displaying on sub splash page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to any Sub Splash page
    Then I should see header footer ad pools in "SITE" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Browse page - Verify the header footer adpools are displaying on browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to any Browse page
    Then I should see header footer ad pools in "SITE" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search page - Verify the header footer adpools are displaying on search results page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to any Search page
    Then I should see header footer ad pools in "SITE" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Campaign page - Verify the header footer adpools are displaying on campaign page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to any Campaign page
    Then I should see header footer ad pools in "SITE" mode
  # Ex: '/campaign/social?campaign_id=XXX' pattern url's

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Legacy page - Verify the header footer adpools are displaying on legacy page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to any Legacy page
    Then I should see header footer ad pools in "SITE" mode
  # Ex: '/ce/splash' pattern url's

  ################################ Ad pools verification for header and footer in Registry Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Browse page - Verify the header footer adpools are displaying on browse page in REGISTRY mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Browse" page in "REGISTRY" mode
    Then I should see header footer ad pools in "REGISTRY" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search page - Verify the header footer adpools are displaying on search results page in REGISTRY mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Search" page in "REGISTRY" mode
    Then I should see header footer ad pools in "REGISTRY" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: DLP page - Verify the header footer adpools are displaying on DLP page in REGISTRY mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "DLP" page in "REGISTRY" mode
    Then I should see header footer ad pools in "REGISTRY" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Campaign page - Verify the header footer adpools are displaying on campaign page in REGISTRY mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Campaign" page in "REGISTRY" mode
    Then I should see header footer ad pools in "REGISTRY" mode
  # Ex: '/campaign/social?campaign_id=XXX' patterm url's

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Legacy page - Verify the header footer adpools are displaying on legacy page in REGISTRY mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Legacy" page in "REGISTRY" mode
    Then I should see header footer ad pools in "REGISTRY" mode
  # Ex: '/ce/splash' pattern url's

  ################################ Ad pools verification for header and footer in Iship Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Sub Splash page - Verify the header footer adpools are displaying on subsplash page in ISHIP mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Sub Splash" page in "ISHIP" mode
    Then I should see header footer ad pools in "ISHIP" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search page - Verify the header footer adpools are displaying on search results page in ISHIP mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Search" page in "ISHIP" mode
    Then I should see header footer ad pools in "ISHIP" mode

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Campaign page - Verify the header footer adpools are displaying on campaign page in ISHIP mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Campaign" page in "ISHIP" mode
    Then I should see header footer ad pools in "ISHIP" mode
  # Ex: '/campaign/social?campaign_id=XXX' patterm url's

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Legacy page - Verify the header footer adpools are displaying on legacy page in ISHIP mode
    Given I visit the web site as a guest user
    When I ensure to navigate to "Legacy" page in "ISHIP" mode
    Then I should see header footer ad pools in "ISHIP" mode
  # Ex: '/ce/splash' pattern url's

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: CategorySplashPage - Domestic|Iship|Registry - Verify "footer" links are not navigating to any error page
    Given I am on CatSplashPage in <shopping_mode> mode
    And I navigate to footer section of the page
    When I select "footer" link from footer section
    Then I verify any footer link is not resulting error page
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |

  ################################ GNA verification in Domestic Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search Page - Verify GNA consistency for search page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I note the GNA count on Home Page
    When I am on Search Results Page for "jeans" on "DOMESTIC" mode
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Search Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: DLP Page - Verify GNA consistency for DLP page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I note the GNA count on Home Page
    When I ensure to navigate to "DLP" page in "DOMESTIC" mode
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # DLP Page

  ################################ GNA verification in Registry Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search Page - Verify GNA consistency for search page in REGISTRY mode
    Given I visit the web site as a guest user
    Then I note the GNA count on Home Page
    When I visit the registry home page
    And I am on Search Results Page for "plates" on "REGISTRY" mode
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Search Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: DLP Page - Verify GNA consistency for DLP page in REGISTRY mode
    Given I visit the web site as a guest user
    Then I note the GNA count on Home Page
    When I ensure to navigate to "DLP" page in "REGISTRY" mode
    Then I verify same GNA count on current page
    When I visit the registry home page
    And I ensure to navigate to "DLP" page in "REGISTRY" mode
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # DLP Page

  ################################ GNA verification in Iship Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - HOME in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - JUNIORS in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "JUNIORS" category page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - HOME - Dining & Entertaining in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Dining & Entertaining" browse page from cat splash page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - HOME - Furniture in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Furniture" browse page from cat splash page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - HOME - Mattresses in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Mattresses" browse page from cat splash page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - HOME - Rugs in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "HOME" category page
    And I navigate to "Rugs" browse page from cat splash page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - MEN - Big & Tall in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "MEN" category page
    And I navigate to "Big & Tall" browse page from cat splash page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Category Page - Verify GNA consistency - KIDS - All Baby in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I navigate to "KIDS" category page
    And I navigate to "All Baby" browse page from cat splash page
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Category Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search Page - Verify GNA consistency for search page in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I am on Search Results Page for "jeans" on "Iship" mode
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # Search Page

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: DLP Page - Verify GNA consistency for DLP page in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I note the GNA count on Home Page
    When I ensure to navigate to "DLP" page in "Iship" mode
    Then I verify same GNA count on current page
  # Notes:
  # Do full validation -
  # There could be more than 1 GNA on Home Page. If 4 GNA's were displayed on HP, then 4 should be displayed on the
  # DLP Page

  ################################ GNA & GFA verification in Domestic Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Subspalsh page - Verify GNA & GFA Links are present on Subsplash Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    And I verify the display of GFA
    When I navigate to "Subsplash" page
    Then I verify the display of GNA
    And I verify the display of GFA

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search page - Verify GNA & GFA Links are present on Search Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    And I verify the display of GFA
    When I am on Search Results Page for "jeans" on "Domestic" mode
    Then I verify the display of GNA
    And I verify the display of GFA

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: DLP page - Verify GNA & GFA Links are present on DLP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    And I verify the display of GFA
    When I ensure to navigate to "DLP" page in "Domestic" mode
    Then I verify the display of GNA
    And I verify the display of GFA

  ################################ GNA & GFA verification in Registry Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Subspalsh page - Verify GNA & GFA Links are present on Subsplash Page in REGISTRY mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    And I verify the display of GFA
    When I visit the registry home page
    And I navigate to "Subsplash" page
    Then I verify the display of GNA
    And I verify the display of GFA

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search page - Verify GNA & GFA Links are present on Search Page in REGISTRY mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    And I verify the display of GFA
    When I visit the registry home page
    And I am on Search Results Page for "plates" on "REGISTRY" mode
    Then I verify the display of GNA
    And I verify the display of GFA

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: DLP page - Verify GNA & GFA Links are present on DLP Page in REGISTRY mode
    Given I visit the web site as a guest user
    Then I verify the display of GNA
    And I verify the display of GFA
    When I visit the registry home page
    And I ensure to navigate to "DLP" page in "REGISTRY" mode
    Then I verify the display of GNA
    And I verify the display of GFA

  ################################ GNA & GFA verification in Iship Mode ############################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Subspalsh page - Verify GNA & GFA Links are present on Subsplash Page in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify the display of GNA
    And I verify the display of GFA
    When I navigate to "Subsplash" page
    Then I verify the display of GNA
    And I verify the display of GFA

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: Search page - Verify GNA & GFA Links are present on Search Page in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify the display of GNA
    And I verify the display of GFA
    When I am on Search Results Page for "jeans" on "ISHIP" mode
    Then I verify the display of GNA
    And I verify the display of GFA

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario: DLP page - Verify GNA & GFA Links are present on DLP Page in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify the display of GNA
    And I verify the display of GFA
    When I ensure to navigate to "DLP" page in "ISHIP" mode
    Then I verify the display of GNA
    And I verify the display of GFA

  ################################################ Flyout Menu - All modes #########################################################

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS in DLP Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to DLP page from "<Site_mode>" page
    And I mouse hover on any category
    Then I should see flyout menu
  Examples:
    | Site_mode |
    | Domestic  |
    | Registry  |
    | Iship     |

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS in Catsplash Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to Catsplash page from "<Site_mode>" page
    And I mouse hover on any category
    Then I should see flyout menu
  Examples:
    | Site_mode |
    | Domestic  |
    | Registry  |
    | Iship     |

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: Header - Verify TOPNAV - FLYOUTS in Subsplash Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to Subsplash page from "<Site_mode>" page
    And I mouse hover on any category
    Then I should see flyout menu
  Examples:
    | Site_mode |
    | Domestic  |
    | Registry  |
    | Iship     |

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: Header - Verify media in TOPNAV - FLYOUTS in Browse Page in DOMESTIC, ISHIP and REGISTRY modes
    Given I visit the web site as a guest user
    When I navigate to Browse page from "<Site_mode>" page
    And I mouse hover on any category
    Then I should see flyout menu
    And I verify media in flyout menu displaying as per service response
  Examples:
    | Site_mode |
    | Domestic  |
    | Registry  |
    | Iship     |
  # Notes:
  # Verify media in flyout menu displaying as per content service response.

  @domain_discovery @priority_medium @artifact_navapp @mode_domestic @mode_registry @mode_iship @add_missing_scope @please_automate @wip
  Scenario Outline: CategorySplashPage - Domestic|Iship|Registry - Verify "sub top navigation" links are not navigating to any error page
    Given I am on CatSplashPage in <shopping_mode> mode
    When I select "FOB" link from sub top navigation
    Then I verify any sub top navigation link is not resulting error page
  Examples:
    | shopping_mode |
    | Domestic      |
    | Iship         |
    | Registry      |
  # Select FOB's from sub top navigation
  # Ex: Holiday Gift Guide, Lists, Deals & Promotions, Gift Cards etc.

  # Test Case ID: RT-07349
  @artifact_navapp @domain_discovery @priority_medium @mode_domestic @use_manual @wip
  Scenario: Footer - Domestic - Verify the Signup for emails
    Given I visit the web site as a guest user
    When I select 'Signup for email' link form footer
    Then I should be landed on signup page
    And I verify singup with new cstomer details
    And I verify thank you message displayed after singup
  # Notes:
  #1.Home page should be displayed
  #2. Should navigate to "Sign up for emails" page  (https://prefcenter.email.macys.com/Macyscom/subscribe/Registration.asp?rdn=1000&aff_src=60)
  #3.Need to verify:
  #  1. header - "Sign up for emails" with promotional message
  #  2. email filed
  #  3. zip code field
  #  4. birth date filed
  #  5. details & exclusions (http://www.macys.com/cms/slp/3/100614emailquickregexcl) link
  #  6. SIGN UP FOR TEXTS, TOO! section
  #  7. mobile number field
  #  8. Send me texts checkbox
  #  9. Terms & Conditions (http://www.macys.com/service/mobile.jsp), Privacy Policy (https://customerservice.macys.com/app/answers/detail/a_id/40/) links
  #  10. submit button
  #  11. click on the Shop macys.com and verify its navigate dto home page
  #4.Verify that Thank You message should be displayed after signup
  # Test Case ID: RT-07349

 #Stores Page in BCOM is still using old HNF so moving to mcom folder
  @use_regression @domain_discovery @priority_high @mode_iship
  Scenario: StoresPage - Verify TOPNAV on Stores legacy Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I navigate to new stores page
    Then I should see Ajax call to navapp for "topnav" in "ISHIP" mode
    And I verify dynamic top navigation in "iship" mode

  @use_regression @domain_discovery @priority_high @mode_domestic
  Scenario: LegacyPage - Verify TOPNAV on stores Legacy Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to new stores page
    Then I should see Ajax call to navapp for "topnav" in "SITE" mode
    And I verify dynamic top navigation in "domestic" mode