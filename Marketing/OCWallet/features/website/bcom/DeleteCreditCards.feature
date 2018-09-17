# Author: OC Wallet Team
# Date Created: 03/06/2013
# Date Signed Off: TBD


Feature: Delete Credit Card in My Wallet

  Mingle Link: http://mingle/projects/market/cards/268

  ########################################################################
  # Story Title: BCOM UI :: ShopApp :: Remove CC from My Wallet
  # Mingle Link: http://mingle/projects/market/cards/1245
  # OR
  # Story Title: BCOM:: Remove Credit Card - Include CC number in message.
  # Mingle Link: http://mingle/projects/market/cards/1221
  ########################################################################

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify user is able to remove credit card from my wallet page
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I delete any credit card
    Then I should see credit card is deleted from my wallet page

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify user can cancel deleting of a credit card on my wallet page
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I cancel out of deleting the credit card
    Then I should see credit card is not deleted from my wallet page

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify if the removed credit card was default credit card prior to deletion the next credit card on the list (and in the database) becomes default card
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I delete default credit card
    Then I should see next credit card as default credit card on my wallet page and in database

