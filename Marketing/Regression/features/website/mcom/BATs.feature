Feature: BATs for Marketing MCOM


  @domain_marketing @project_SLP_Phase2 @priority_high @use_bat @artifact_navapp
  Scenario: As a user, I should see expected media types in all rows(row 0 to row 106) on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate the "Browse" SLP category which is under "71233" parent category
    Then I should see all contextual content media data on slp page as per astra data with below contexts
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | US          | SITE          | LANDING         |

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery @use_domain_qual @Marketing_CBT
  Scenario: Home Page - Verify FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify FOBS are displayed and return a 200 OK
      | FOBS                  |
      | ACTIVE                |
      | BRANDS                |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY               |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | WATCHES               |
      | JUNIORS               |
      | BED & BATH            |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

  # Note:
# Pre-Condition: In order to get displayed with store receipt preferences section, we need to set up data in DB
# The above scenario will verifies email receipts option is enabled as well as both and paper receipts options are disabled on my preference page
  @upi_656_mcom @artifact_shopapp @priority_high @use_regression @myaccount_4 @s4a_stable @domain_marketing
  Scenario:Verify Store Receipt preference options are enabled for a registered user on My Preference Page
    Given I visit the web site as a guest user
    When I create a new profile
    And I enable store receipts option to "P" for the profile user in DB
    And I navigate to the My Preferences Page
    Then I should see basic attributes of store receipt preferences section
    And I should see paper receipts option is selected on my preference page

    # Note:
# Pre-Condition: In order to get displayed with store receipt preferences section, we need to set up data in DB.
# If preference ID = 30 and value is 2, then the checkbox against paper receipts should be checked
  @upi_656_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_4 @s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify both Email & Paper Receipts can be changed on My Preference Page
    Given I visit the web site as a guest user
    When I create a new profile
    And I enable store receipts option to "E" for the profile user in DB
    And I navigate to the My Preferences Page
    Then I should see basic attributes of store receipt preferences section
    When I update store receipts section with both receipts option on My Preferences Page
    Then I verify the confirmation message "Your changes have been saved!" on the page
    And I should see both receipts option is selected on my preference page
    And I verify my preference store receipts "Receipt Preference" value should be displayed as "2" in DB

  @domain_marketing @use_bat @journey @mustpass @sv_mcom_refactored @sv_bcom_refactored @s4a_stable @ifs
  Scenario: A registered user can add a store credit card to their wallet.
    Given I visit the web site as a guest user
    When I create a new profile
    And I add a store credit card to my wallet
   # Then my credit card information should be saved successfully

  @domain_marketing @use_bat @journey @mustpass @sv_mcom_refactored @sv_bcom_refactored @Marketing_CBT
  @s4a_stable @ifs
  Scenario: A registered user can add a credit card to My Wallet in their wallet.
    Given I visit the web site as a guest user
    When I create a new profile
    And I add a credit card to my wallet
   # Then my credit card information should be saved successfully

  @upi_631_mcom @artifact_shopapp @priority_high @use_regression @health_check @myaccount_4 @use_launch_call @s4a_stable @domain_marketing @use_bat @Marketing_CBT
  Scenario: Verify MyAccount Pages and links
   # Given I visit the production web site as an existing user
    Given I visit the web site as a guest user
    When I create a new profile
    Then I verify the basic attributes of the My Account Page
    And I verify the My Account Pages are rendered properly
      #| Links                       |
      | My Profile                    |
      | my preferences                |
      | My Address Book               |
      | My Wallet                     |
      | Order Status                  |
      | Furniture & Mattress Status   |
      | My bloomingdale's credit card |
      | Gift Card Balance             |
      | Wish List                     |

  @priority_high  @use_bat @Marketing_CBT
  Scenario: Plenti - Successful lookups from my account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I perform all valid Plenti lookups on the page

  @use_regression @priority_high @artifact_navapp @domain_marketing @use_bat @iq_bat @saucelab @Marketing_CBT
  Scenario:The home page should be displayed without errors and with all relevant information present.
    Given I visit the web site as a guest user
    Then I verify basic attributes of the home page

  @domain_marketing @trigger_jobs
  Scenario:Trigger Marketing Scripts in EE Server
    Given I am on the EE page
    And I retrieve the appropriate job and place for execution
    #| Jobs                         |
     # | M744004.Marketing-HomePage-BCOM    |
     # | M744004.Marketing-HomePage-MCOM    |
     # | M744004.Marketing-OCWallet-BCOM    |
     # | M744004.Marketing-OCWallet-MCOM    |
     # | M744004.Marketing-Plenti-MCOM      |
     # | M744004.Marketing-MyAccount-BCOM   |
     # | M744004.Marketing-MyAccount-MCOM   |
     # | M744004.Marketing-SEO-MCOM         |
      | M744004.Marketing-Loyalty-BCOM     |
      | M744004.Marketing-Preferences-BCOM |
      | M744004.Marketing-Preferences-MCOM |
     # | M744004.Marketing-UFT-MCOM         |

