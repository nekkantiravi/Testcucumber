# Author: Regression Team
# Date Created: 06/20/2017
# Date Signed Off:


Feature: View Credit Cards in My Wallet

  Mingle Link: http://mingle/projects/market/cards/272

  ################################################################################
  # Story Title: BCOM UI :: ShopApp :: Access Credit and Debit cards in the Wallet
  # Mingle Link: http://mingle/projects/market/cards/1102
  ################################################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to view all the details for stored credit or debit cards in my wallet
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see add a credit or debit card button
    And I should see "MY PAYMENT OPTIONS" header on My Wallet page
    And I should see "CREDIT / DEBIT CARDS" sub header on My Wallet page
    And I should see all the details for the stored credit cards

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the order of stored credit cards on my wallet page
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see the stored cards with primary on top and followed by recently added

  #######################################################################
  # Story Title: BCOM UI :: ShopApp :: No Cards in the Wallet
  # Mingle Link: http://mingle/projects/market/cards/1131
  #######################################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify the copy text and add a credit or debit card button display on my wallet page when user do not have any stored credit cards
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see no credit cards in wallet message: "Keep all your payment options in one place and make checkout easier and faster by adding your Bloomingdale's card and any other credit or debit card to your bWallet." in MY PAYMENT OPTIONS section
    And I should see add a credit or debit card button
    # Note: The UI should match with the comps attached to story in mingle

  #######################################################
  # Story Title: BCOM UI:: Co-brand and Prop Card Display
  # Mingle Link: http://mingle/projects/market/cards/1379
  #######################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that titles of co-brand and prop cards on my wallet page matches with the comps
    Given I visit the web site as a registered user with no stored credit cards
#    Given I visit the web site as a guest user
#    When I create a new profile
    When I navigate to My Wallet page from My Account page
    And I add a credit card with all possible card_type to my wallet
      | card_type                       |
      | Bloomingdale's                  |
      | Bloomingdale's American Express |
      | Bloomingdale's Employee Card    |
    Then I should see the following titles for the prop and co-brand cards:
      | Bloomingdale's Card                   |
      | Bloomingdale's American Express® Card |
      | Bloomingdale's Employee Card          |

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the disclaimer text display in bottom of  my wallet page when user have Bloomingdale's American Express Card
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    And I add a credit or debit card with following card type to my wallet:
      | card_type                       |
      | Bloomingdale's American Express |
    Then I should see disclaimer text "American Express is a federally registered service mark of American Express and is used by Department Stores National Bank pursuant to a license.The Bloomingdale's American Express Card program is issued and administered by Department Stores National Bank." at the bottom of my wallet page

  @use_regression @deprecated_15GA @use_launch_call @domain_marketing @project_oc_wallet @mode_domestic
  Scenario: Verify that user is able to view all the details for stored credit cards in my wallet for an existing user
#    Given I visit the production web site as an existing user
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see add a credit or debit card button
    And I should see "MY PAYMENT OPTIONS" header on My Wallet page
    And I should see "CREDIT / DEBIT CARDS" sub header on My Wallet page
    And I should see all the details for the stored credit cards