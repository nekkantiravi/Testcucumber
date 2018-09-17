# Author: OC Wallet Team
# Date Created: 07/12/2017
# Date Signed Off:


Feature: BCOM:: Update Site Navigation

  Mingle Link: http://mingle/projects/market/cards/254

  ###################################################################################
  # Story Title: BCOM UI:: Sales & Offers page rename to Sales & Promotions - Desktop
  # Mingle Link: http://mingle/projects/shopping_experience_2/cards/1476
  ###################################################################################

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify sales and offer text is changed to sales and promotions for header and copy text on sales & offers page, left navigation and link text on wallet page for signed in user
    Given I visit the web site as a registered user
    When I navigate to sales and promotions page
    Then I should see sales and promotions title
    And I should see left navigation header text "SALES & PROMOTIONS"
    And I should see copy text "Browse our latest sales and promotions all in one place." under Sales & Promotions header
    When I navigate to My Wallet page from My Account page
    Then I should see the 'SEE SALES & PROMOTIONS PAGE' link under My Offers section

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify sales and offer text is changed to sales and promotions for header and copy text on sales & offers page, left navigation and link text on wallet page for guest user
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    Then I should see sales and promotions title
    And I should see left navigation header text "SALES & PROMOTIONS"
    And I should see copy text "Browse our latest sales and promotions all in one place." under Sales & Promotions header

  #######################################################################
  # Story Title: BCOM UI:: Update copy on My profile page to "my bWallet"
  # Mingle Link: http://mingle/projects/market/cards/1461
  #######################################################################
#  2017-07-12 YC03673: Its not a valid scenario in 17M while automating in SDT framework
#  @use_regression @deprecated_15GA @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
#  Scenario:Verify the title for wallet page is displaying as My bWallet in left nav
#    Given I visit the web site as a guest user
#    When I create a new profile
#    And I navigate to my profile
#    Then I should see the "bWallet" beside the checkbox for adding card to profile in ADD YOUR BLOOMINGDALE'S CARD TO YOUR PROFILE section
    # Note : As part of above step, we will verify that 'bloomingdales's wallet' is updated to 'bWallet' in  "Add Your Bloomingdale's Card to Your Profile" section.

  #################################################################
  # Story Title: BCOM UI:: My bWallet in My Account left navigation
  # Mingle Link: http://mingle/projects/market/cards/1459
  #################################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the title for wallet page is displaying as My bWallet in left nav
    Given I visit the web site as a guest user
    When I create a new profile
    And I navigate to my profile
    Then I should see the "My bWallet" navigation title on profile page

   ####################################################################################
  # Story Title: BCOM UI:: Suppress Reward Card Balance link in My Account navigation
  # Mingle Link: http://mingle/projects/market/cards/2169
  ####################################################################################

  @use_regression @14I @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify that Reward Card Balance link is displaying in left navigation of all pages in My Account section
    Given I visit the web site as a registered user
    When I navigate to the "<MY_ACCOUNT_LHN_LINKS>" link in My Account page
    Then I verify that the "<PAGE_TITLE>" page is rendered
    And I should see Reward Card Balance link in left nav section

  Examples:
    | MY_ACCOUNT_LHN_LINKS          | PAGE_TITLE                      |
    | my profile                    |My Profile - My Account - Bloomingdale's|
    | My Address Book               |My Address Book - My Account - Bloomingdale's|
    | My Wallet                     |My bWallet - My Account - Bloomingdale's     |
    | My Order Status & History     |Check My Order Status & Order History - Bloomingdale's|
    #comment the below 3 from 17Z
    | my account                    |My Account - Bloomingdale's      |
    | My Points                     |Loyallist: Account Association \| Bloomingdale's|
    | My Perks                      |Bloomingdale’s Loyallists Member Benefits & Perks|
  #Note: As per story http://mingle/projects/selection/cards/2027 left nav will no longer availale in wish list page

  @14I @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that Reward Card Balance link is not displaying in left navigation of My Account signed out page
    Given I visit the web site as a guest user
    When I navigate to sign in page
    Then I should not see Reward Card Balance link in left nav section

  #######################################################################
  # Story Title: BCOM::UI:: Update page title of My Wallet page
  # Mingle Link: http://mingle/projects/market/cards/2311
  #######################################################################

  @use_regression @14I @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify updated page title of My Wallet Page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see my wallet page title as "My bWallet - My Account - Bloomingdale's"
