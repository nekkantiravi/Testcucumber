# Author: OC Wallet Team
# Date Created: 06/01/2017
# Date Signed Off:


Feature: Manually Add Offers on Wallet page

  Mingle Link: http://mingle/projects/market/cards/265

  ####################################################################
  # Story Title: BCOM:: Add Offer manually to Wallet on My Wallet page
  # Mingle Link: http://mingle/projects/market/cards/1291
  ####################################################################

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that user is able to manually add a valid online only offer promo code to wallet
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I saved "online only" offer promo code in wallet
    Then I should see added offer in my offers section
    And I should see added offer promo code as an ONLINE ONLY and no IN STORE CODE displayed for this offer

  @use_regression @use_bat_next @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @coremetrics @use_domain_qual
  Scenario: Verify that user is able to manually add a valid omnichannel offer promo code to wallet
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I saved "omnichannel" offer promo code in wallet
    Then I should see added offer in my offers section
    And I should see added offer promo code as an ONLINE & IN STORE and corresponding in-store code in offer info

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the server side offer validation in add an offer overlay on my wallet page with missing data
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see the error message "You did not enter a promo code. Please try again." on the add offer overlay after saving with empty value

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is able to close the add an offer overlay by clicking outside of the overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I click "outside of the overlay" after entering promo code in add offer overlay
    Then I should see that the offer is not saved

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is able to close the add an offer overlay by clicking on close
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I select "X" after entering promo code in add offer overlay
    And I should see that the offer is not saved

  ###############################################################
  # Story Title: BCOM:: Registry Completion Code-specific Message
  # Mingle Link: http://mingle/projects/market/cards/1546
  ###############################################################

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that error message is displayed in the wallet page, when Customer tries to add a registry completion promo code to her Wallet
  #Prerequisite: Customer is signed in and has a valid registry completion promo code
    Given I visit the web site as a guest user
    When I create a new wedding registry with event date within 180 days
    And I capture the completion promo code from registry manager page
    When I navigate to My Wallet page from My Account page
    Then I should see "Sorry, registry completion codes cannot be added to bWallet. If you need assistance, please call Customer Service at 1 800 777 0000." error message after saving valid registry completion promo code on wallet page

  # promotion code is not always available so removed @use_dsv tag
  @domain_marketing @priority_medium @mode_domestic @project_oc_wallet @use_browser
  Scenario: Verify that user is able to manually add a valid online only offer promo code to wallet for DSV
    Given I visit the web site as a guest user
    When I sign-in to my existing profile if in prod else I create a new profile
    And I pick a promo code from deals and promotion page
    And I navigate to My Wallet page from My Account page
    And I added wallet eligible offer manually on wallet page
    Then I should see added offer in my offers section
    And I should see added offer promo code as an "ONLINE ONLY" and no IN STORE CODE displayed for this offer
