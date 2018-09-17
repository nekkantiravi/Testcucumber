#Author: Samith Sandanayake
#Date Created: 06/14/2017
#Date Signed Off:

Feature: Verify My Offers

################################# Generic verification ################################

  #Test Case ID: MCOM-84613
  @use_regression @sstbacklog @shop_browse_1 @project_oc_wallet @mode_domestic @domain_marketing @use_domain_qual
  Scenario: Verify URL structure for deals & promotions page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I should see promotions page
    # Notes
    # Verify deals & promotions page loaded properly
    # Verify the URL structure(shop/coupons-deals) for deals & promotions page


  @sstbacklog @shop_browse_2 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify Left Nav in deals and promotion page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I verify Left Navigation in deals & promotion page
    # Notes:
    # Verification needed
    # All available promotions should display
    # Left navigation should be present if there are promotions
    # Left navigation should display the category based on the order of the DB

  #Test Case ID: MCOM-92026
  @use_regression @use_dsv @use_launch_call @priority_high @coremetrics @project_oc_wallet @mode_domestic @domain_marketing  @dsv_desktop_sev2
  Scenario: Check internal links of deals and promotion Page
    Given I visit the web site as a guest user
    When I visit the deals and promotions page
    Then I verify the deals and promotions links work as expected
    # Notes:
    # Verify couple of available links weather they navigate to the correct page

  ################################ Google + ####################################


  #Test Case ID: MCOM-84616
  @defect_SSTREG-50 @sstbacklog @shop_browse_1 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify whether offer information is displayed on google+ window for selected promotion
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I should see the promotions page
    When I share any "promotion" via "gplus" from promotions page
    And I login in to "gplus" with valid user credentials
    Then I should see the company logo displayed at the left side of the "gplus" window
    And I should see the offer information in "gplus" window
    And I should see the share and cancel buttons on "gplus" window
    And I click on the shared link in "gplus" profile page
    Then I should see the promotions page
    # Notes
    # signed in to a G+ profile and visit macys web page as a guest user
    # Click on share button on an available promotion
    # Then click on G+ icon in pop up
    # Verify the company logo displayed at the left side of the G+ window
    # Verify "Shop & Save at Macy's. Check out the amazing deals at Macy's " can be seen in G+ window.
    # Click on share link in twitter window and verify the window is closed
    # Navigate to G+ page, click on the promotion link to redirect to the relevant FOB

###################################### Coupon Window #######################################

   #Test Case ID: MCOM-84618
  @sstbacklog @shop_browse_2 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Coupon window and Add to passbook button on desktop application
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I verify the get savings pass functionality
    # Notes
    # click on getSavingsPass button
    # verify the popup window with coupon information
    # Verify the contents can be scrolled in the popup
    # Verify no "Add to passbook" button available on Desktop application

  @defect_SSTREG-50 @sstbacklog @shop_browse_1 @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify whether the coupon code is displayed on coupon window
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I verify coupon code on coupon window

  ########################### Email a Friend ##############################################

  #MCOM-84617
  @use_regression @sstbacklog @shop_browse_2
  Scenario: Promotion/Coupons URL redirection to email to friend
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I should see promotions page
    When I share any "promotion" via "email" from promotions page
    Then I should see "email to friend" popup window
    And I should also see cancel and send button on window


  ################################# Shop Now link #######################################


  #Test Case ID: MCOM-84619
  @sstbacklog @shop_browse_1 @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_domain_qual
  Scenario: ShopNow Link in deals and promotions page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I verify the shop now functionality
    # Notes
    # click on shop now button
    # verify user is redirect to correct promotion
    # Use the SQL query: select * from promo_attr_val where promotion_id = 19823557 and ATTR_NAME= 'PROMO_CLICK_THROUGH_URL'
    # Note: PROMO_CLICK_THROUGH_URL is nothing but "Shop Now" url


  ##################################### UI Verification #########################################

  #Test Case ID: MCOM-66710
  @use_regression @sstbacklog @use_launch_call @shop_browse_2 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify UI on Deals & Promotions page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I verify the look and feel of the Deals & Promotions page
    # Notes
    # Verify Promotions page should display some available promotions.

  ################################ Promotion Sequence Rules#######################################

  #Test Case ID: MCOM-84620
  @please_automate_backlog @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify Promotion and coupons sequence rules
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I verify the FOBs are in order
    Then I verify the promotions with coupons displayed in any FOB
    And I verify standalone coupons and promotions are displayed in any FOB
    # Notes
    # Verify the FOBs are in order as in the query result. Query: SELECT * FROM FOB
    # Verify the promotion with coupon at the top of the list under any perticular FOB.(which is having both "Shop Now" and "Get saving Pass" links).
    # Verify the promo code. if no promo code for that promtion then "no promo code" text should be displayed.
    # Verify the end date(ends).
    # Verify in standalone coupons and promotions:
    # stand alone coupons display below the promotions with coupons under that FOB.
    # The promo code. if no promo code for that promtion then "no promo code" text should be displayed.
    # The end date(ends).

  ################################# Get Texts #########################################

  #http://mingle/projects/fast_track/cards/3824
  #Test Case MCOM-91829
  @14E @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify the functionality of "Get Texts" link on Deals and promotions page in domestic mode
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I click on the Get texts details link
    Then I should see a popup with SMS information to get the promotion updates and customer service phone 877-876-2297

  #MCOM-91830
  @14E @artifact_navapp @priority_medium @project_oc_wallet @mode_iship @domain_marketing
  Scenario Outline: Verify the functionality of "Get Texts" link on Deals and promotions page in International mode
    Given I visit the website as a iship user with "<country>" country and "<currency>" currency
    When I navigate to deals and promotions page
    And I click on the Get texts details link
    Then I should see a popup with SMS information to get the promotion updates and customer service phone 877-876-2297
  Examples:
    | country   | currency          |
    | Australia | Australian Dollar |


  ################################# Dont have a macys Card #########################################

#  this scenario is no more valid as we dont have 'Dont have a macys Card' section on deals and promotions page any more.
  #MCOM-91830
#  @use_regression @14E @artifact_navapp @artifact_navapp_2 @priority_medium @uft @shop_browse_1 @project_oc_wallet @mode_domestic @domain_marketing @use_domain_qual
#  Scenario: Verify the functionality of the details link under "Don't Have a Macy's card?" section on Deals and promotions page in domestic mode
#    Given I visit the web site as a guest user
#    When I navigate to deals and promotions page
#    When I click on the details link on the "Don't Have a Macy's card?" section
#    Then I should not see blank pop up



  #################################### get Emails ##############################################

  @use_regression @sstbacklog @use_launch_call @shop_browse_1 @project_oc_wallet @mode_domestic @domain_marketing @priority_medium
  Scenario: Verify the functionality of Get Emails link on Deals and promotions page in domestic mode for normal user
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I verify Get emails link
    And I verify promotions for current date

  @sstbacklog @shop_browse_2 @project_oc_wallet @mode_iship @domain_marketing @priority_medium
  Scenario Outline: Verify the functionality of 'Get Emails' link on Deals and promotions page in domestic mode for iship user
    Given I visit the website as a iship user with "<country>" country and "<currency>" currency
    When I navigate to deals and promotions page
    And I verify Get emails link
  Examples:
    | country   | currency          |
    | Australia | Australian Dollar |

