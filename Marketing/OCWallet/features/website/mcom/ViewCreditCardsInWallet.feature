# Author: OC Wallet Team
# Date Created: 12/06/2013
# Date Signed Off: 01/15/2014


Feature: View Credit Cards in My Wallet

  Mingle Link: http://mingle/projects/market/cards/272

  #################################################################
  # Story Title: MCOM UI :: ShopApp :: View Credit Cards in Wallet.
  # Mingle Link: http://mingle/projects/market/cards/1084
  # OR
  # Mingle Link: http://mingle/projects/market/cards/964
  ################################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to view all the details for stored credit cards in my wallet
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see add credit card button
    And I should see all the details for the stored credit cards
    # As part of the above step we need to verify the following details for all the stored credit cards:
    # Credit card logo, Credit card type, Masked credit card number except last 4 digits,
    # Expiration date for non-prop cards, Pay Bill for prop and co-brand cards, Edit credit card pencil icon

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to store upto 10 credit cards in my wallet
    Given I visit the web site as a registered user with 10 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see upto 10 stored credit cards

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the order of stored cards in credit cards module
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see the stored cards with primary on top and followed by recently added

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the display of primary credit card on my wallet page
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see default text at the top of the primary credit card

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the message on my wallet page when there are no credit card stored in wallet
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see no credit cards in wallet message: "You don't currently have any stored payment methods." on my wallet page

