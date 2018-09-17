# Author: OC Wallet Team
# Date Created: 03/19/2014
# Date Signed Off: TBD


Feature: View Manually Added Offers in Wallet

  Mingle Link: http://mingle/projects/market/cards/264

  #######################################################
  # Story Title: BCOM:: No Offer View
  # Mingle Link: http://mingle/projects/market/cards/1445
  #######################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to see no offer view and add an offer button when there are no stored offers in wallet
    Given I visit the web site as a registered user with no stored offers
    When I navigate to My Wallet page from My Account page
    Then I should see "There are no valid offers available in your bWallet.Offers that you saved previously may have expired." message in MY OFFERS section
    And I should see add an offer button
    # Note: UI should match with the comps attached to the story in mingle

  #############################################################
  # Story Title: BCOM:: View Manually Added Offers in My Wallet
  # Mingle Link: http://mingle/projects/market/cards/1278
  #############################################################

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario: Verify that user is able to access the learn more link under My Offers section in wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see "LEARN MORE about bWallet and how to use it online and in store" on wallet page
    #Note: As of now "wallet-faq page" is not developed, hence commented below steps, once it developed we will uncomment below steps
    When I select 'LEARN MORE' link in wallet page
    Then I should navigate to 'FAQ' page with url containing 'bwallet-faq' along with base url
    # Note: Ex: www.bloomingdales.com/bwallet-faq
    #As of now "faq" page is not developed, when it navigated to faq page it showing 404 error, hence kept @use_wip


  @use_regression @use_bat_next @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario: Verify that user is able to access the sales and offers page from wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see the 'SEE SALES & PROMOTIONS PAGE' link under My Offers section
    When I select 'SEE SALES & PROMOTIONS PAGE' link in wallet page
    Then I should see the promotions page
    # Note: As part of the above step we will verify that the user is redirected to sales & offers page with URL Ex:  http://www1.bloomingdales.com/shop/sales-offers/?

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that just added flag is displayed when user adds an offer manually in same session
    Given I visit the web site as a registered user with no stored offers
    When I navigate to My Wallet page from My Account page
    And I added wallet eligible offer manually on wallet page
    Then I should see added offer in my wallet page with "JUST ADDED" flag

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that just added flag is displayed when user adds multiple offers manually in the same session
    Given I visit the web site as a registered user with no stored offers
    When I navigate to My Wallet page from My Account page
    And I added first wallet eligible offer manually on wallet page
    Then I should see added offer in my wallet page with "JUST ADDED" flag
    When I add any other valid offer to wallet
    Then I should see added offer in my wallet page with "JUST ADDED" flag for the newly added offer
    And I should see "JUST ADDED" flag is not displayed already added offer before

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that just added flag is displayed when user adds an offer manually and log in when session is timed out or in another session
    GivenI visit the web site as a registered user with no stored offers
    When I navigate to My Wallet page from My Account page
    And I added wallet eligible offer manually on wallet page
    Then I should see added offer in my wallet page with "JUST ADDED" flag
    And I navigate to My Wallet page from My Account page
    Then I should see "JUST ADDED" flag is not displayed for previously added offer

  #########################################################
  # Story Title: BCOM UI:: My bWallet header and navigation
  # Mingle Link: http://mingle/projects/market/cards/1240
  #########################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the navigation title for My Wallet in My wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see the "My bWallet" navigation title on wallet page

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that welcome message is displaying in the My Wallet page
    Given I visit the web site as a guest user
    And I create a new profile
    When I navigate to My Wallet page from My Account page
    Then I should see the welcome message displays under the My bWallet header

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that My bWallet header is displaying in the My Wallet page
    Given I visit the web site as a guest user
    And I create a new profile
    When I navigate to My Wallet page from My Account page
    Then I should see the MY bWALLET header at the top of the Wallet page

