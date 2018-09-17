# Author: OC Wallet Team
# Date Created: 06/09/2017
# Date Signed Off: TBD


Feature: Delete Credit Card in My Wallet

  Mingle Link: http://mingle/projects/market/cards/268

  ########################################################
  # Story Title: MCOM UI :: ShopApp :: Delete Credit Cards
  # Mingle Link: http://mingle/projects/market/cards/1239
  ########################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the message display in delete confirmation overlay
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see delete confirmation overlay with "Are you sure you want to delete this credit card?" message when deleting a credit card

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify the user is able to successfully delete a credit card from my wallet
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I delete any credit card
    Then I should see "Changes saved to your wallet." on top of the wallet page
    And I should see credit card is deleted from my wallet page
    And I should see the credit card is deleted from database

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is able to cancel deleting of a credit card on my wallet page
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I cancel out of deleting the credit card
    Then I should not see delete confirmation overlay
    And I should see edit credit card overlay

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify if the removed credit card was default credit card prior to deletion the next credit card on the list (and in the database) becomes default card
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I delete default credit card
    Then I should see next credit card as default credit card on my wallet page and in database

