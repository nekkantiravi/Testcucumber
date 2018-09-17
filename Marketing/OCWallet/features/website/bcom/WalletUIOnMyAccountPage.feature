# Author: OC Wallet Team
# Date Created: 07/10/2017
# Date Signed Off: TBD

Feature: BCOM:: Update My Account Page for My Wallet Functionality

  Mingle Link: http://mingle/projects/market/cards/253

############################################################################
# Story Title: BCOM:: My Account Page Update
# Mingle Link: http://mingle/projects/market/cards/1629
############################################################################

  @use_regression @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_bat
  Scenario: Verify that loyallist with his loyallist account associated to his profile, is able to view updated right tile with saved offers on My Account Page
    Given I visit the web site as a registered user with manually 1 stored offers in wallet
    And I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "toptier_loyallist_details"
    When I navigate to 'My Account' page
    Then I should see title as my bWallet
    And I should see the number of offers in my bWallet
    And I should see link 'View my bWallet' that takes me to bWallet page
#    comment above line and uncomment below line from 17Z
#    And I should see link 'My bWallet' that takes me to bWallet page
    And I remove loyallist ID association as part of clean up

  @use_regression @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that loyallist with his loyallist account associated to his profile, is able to view updated right tile with correct reward card balance on My Account Page
    Given I visit the web site as a registered user
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "rewards"
    And I capture the reward card balance from wallet page
    When I navigate to 'My Account' page
    Then It should match the total reward card balance
    And I remove loyallist ID association as part of clean up

  @use_regression @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that loyallist with his loyallist account associated to his profile, is able to view updated right tile with correct points to next to reward card on My Account Page
    Given I visit the web site as a registered user
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "rewards"
    And I capture the points to next reward card from wallet page
    When I navigate to 'My Account' page
    Then I should see total points I need to go to the next reward card
    And I remove loyallist ID association as part of clean up

  @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that loyallist number and "Top of the list" is displayed for user with his top tier loyallist account associated to his profile on My Account Page
    Given I visit the web site as a registered user
    And I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "toptier_loyallist_details"
    When I navigate to 'My Account' page
    Then I should see my loyallist account number with "TOP OF THE LIST" text
    And I remove loyallist ID association as part of clean up

  @use_regression @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that loyallist with his loyallist account associated to his profile, is able to view navigate to loyallist account summary page by clicking 'View My Loyallist Account' link on My Account Page
    Given I visit the web site as a registered user
    And I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "toptier_loyallist_details"
    When I navigate to 'My Account' page
    Then I should see 'View My Loyallist Account' link is taking me to loyallist account summary page
    And I remove loyallist ID association as part of clean up

  @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that "Top of the list" is not displayed for user with his base tier loyallist account associated to his profile on My Account Page
    Given I visit the web site as a registered user
    And I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "basetier_loyallist_details"
    When I navigate to 'My Account' page
    Then I should see my loyallist account number without 'TOP OF THE LIST' text
    And I remove loyallist ID association as part of clean up

  @use_regression @deprecated_15GA @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that loyallist with his loyallist account not associated to his profile but prop card associated to his profile, is able to view updated right tile with saved offers on My Account Page
  #Pre-requisite: User should be a loyallist with his loyallist account not associated to his profile but prop card should have associated to his profile
    Given I visit the web site as a registered user with manually 1 stored offers in wallet
    And I add a "PROP" credit card to my profile
    When I navigate to 'My Account' page
    Then I should see title as my bWallet
    And I should see the number of offers in my bWallet
    And I should see link 'View my bWallet' that takes me to bWallet page
#    comment above line and uncomment below line from 17Z
#    And I should see link 'My bWallet' that takes me to bWallet page

  @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that loyallist with his loyallist account not associated to his profile but prop card associated to his profile, is able to view correct loyalty information and 'Add My Loyallist Account' link functionality on My Account Page
  #Pre-requisite: User should be a loyallist with his loyallist account not associated to his profile but prop card should have associated to his profile
    Given I visit the web site as a registered user
    And I add a "PROP" credit cards to my profile
    When I navigate to 'My Account' page
    Then I should see "ALREADY A LOYALLIST?" header text under wallet section
    And I should see "LINK YOUR LOYALLIST ACCOUNT AND GET POINTS AUTOMATICALLY ANYTIME YOU USE YOUR bWALLET.*" text under wallet section
    And I should see 'Add My Loyallist Account' link is taking me to loyalty account association page

  @use_regression @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that non-loyallist with his loyallist account not associated to his profile and prop card is not associated to his profile, is able to view updated right tile with saved offers on My Account Page
    #Pre-requisite: User should be a non-loyallist with his loyallist account not associated to his profile but and prop card should not associated to his profile
    Given I visit the web site as a registered user with manually 1 stored offers in wallet
    When I navigate to 'My Account' page
    Then I should see title as my bWallet
    And I should see the number of offers in my bWallet
    And I should see link 'View my bWallet' that takes me to bWallet page
#    comment above line and uncomment below line from 17Z
#    And I should see link 'My bWallet' that takes me to bWallet page

  @use_regression @14K @artifact_shopapp @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that non-loyallist with his loyallist account not associated to his profile and prop card is not associated to his profile, is able to view correct loyalty header and text on My Account Page
  #Pre-requisite: User should be a non-loyallist with his loyallist account not associated to his profile but and prop card should not associated to his profile
    Given I visit the web site as a registered user
    When I navigate to 'My Account' page
    Then I should see 'NOT A LOYALLIST?' header text under wallet section
    And I should see "Earn rewards when you shop online, in store and at our outlets." text under wallet section
#   comment the above 2 lines and uncomment below 2 lines from 17Z
#    Then I should see 'Loyallist' header in the Loyallist tile
#    And I should see 'Not A Loyallist, Earn rewards when you shop!, Become a Loyallist link, It's easy It's free! text' in the loyallist tile section

  @use_regression @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that non-loyallist with his loyallist account not associated to his profile and prop card is not associated to his profile, is able to view 'Become a Loyallist' link functionality on My Account Page
  #Pre-requisite: User should be a non-loyallist with his loyallist account not associated to his profile but and prop card should not associated to his profile
    Given I visit the web site as a registered user
    When I navigate to 'My Account' page
    Then I should see 'Become a Loyallist' link is taking me to loyalty create account page

#  Scenario: Verify that non-loyallist with his loyallist account not associated to his profile and prop card is not associated to his profile, is able to view 'SAVE YOUR LOYALLIST NUMBER TO YOUR ONLINE ACCOUNT' link functionality on My Account Page
  @use_regression @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that non-loyallist with his loyallist account not associated to his profile and prop card is not associated to his profile, is able to view 'Add My Loyallist Account' link functionality on My Account Page
  #Pre-requisite: User should  be a non-loyallist with his loyallist account not associated to his profile but and prop card should not associated to his profile
    Given I visit the web site as a registered user
    When I navigate to 'My Account' page
    Then I should see 'Add My Loyallist Account' link is taking me to loyalty account association page
  #   comment the above line and uncomment below line from 17Z
#    Then I should see 'SAVE YOUR LOYALLIST NUMBER TO YOUR ONLINE ACCOUNT' link is taking me to loyalty account association page