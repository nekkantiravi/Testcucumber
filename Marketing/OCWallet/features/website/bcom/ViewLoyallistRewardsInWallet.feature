# Author: OC Wallet Team
# Date Created: 04/03/2014
# Date Signed Off:


Feature: View Loyallist rewards in Wallet

  Mingle Link: http://mingle/projects/market/cards/263

  #######################################################
  # Story Title: BCOM:: Access Reward Cards in the Wallet
  # Mingle Link: http://mingle/projects/market/cards/1313
  #######################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @coremetrics
  Scenario: Verify the reward cards section title and message in the reward cards section when there are no reward cards with balance and unexpired cards in the wallet
  # Test is duplicate of LTY scenarios in /Loyalty/BCOM/Loyalty_Rewards.feature
  # Pre-requisite: User should have associated loyallist  account
    Given I visit the web site as a loyallist using "less_than_2500_points"
    And I navigate to My Wallet page from My Account page
    Then I should see the rewards card section is displayed properly for the loyallist with zero rewards
    And I remove loyallist ID association as part of clean up

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_domain_qual
  Scenario: Verify the reward cards section when user has earned at least one loyallist reward card that is unexpired and has a balance > $0
  # Test is duplicate of LTY scenarios in /Loyalty/BCOM/Loyalty_Rewards.feature
  # Pre-requisite: User should have associated loyallist  account and user should have ">0" balance.
    Given I visit the web site as a loyallist using "rewards"
    And I navigate to My Wallet page from My Account page
    Then I should see the rewards card section is displayed properly for the loyallist with rewards more than zero
    And I remove loyallist ID association as part of clean up
