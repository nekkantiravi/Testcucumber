Feature: Loyalty Rewards

  @artifact_shopapp @priority_low @artifact_shopapp_2 @domain_marketing
  Scenario: Verify the Rewards card balance page with No Active reward cards
    Given I visit the web site as a loyallist
    When I navigate to the My Wallet page
    Then I should see the rewards card section is displayed with no active reward cards

  @use_regression @artifact_shopapp @priority_medium @artifact_shopapp_2 @domain_marketing
  Scenario: As a loyallist user, I want to see the display of active reward(ERC/VRC) cards on Rewards Card Page
    Given I visit the web site as a loyallist using "reward"
    When I navigate to the My Wallet page
    Then I should see the rewards card section is displayed properly for the loyallist
