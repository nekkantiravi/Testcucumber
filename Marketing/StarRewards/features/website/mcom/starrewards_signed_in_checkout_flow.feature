# Author: Star Rewards Project QE Team
# Date Created: 10/13/2017
# Version One: MCOM Story B-75237

Feature: Star Rewards functionality in Signed In Checkout Flow

  @wip @domain_marketing @artifact_shopapp @project_star_rewards
  Scenario: Verify that as a signed in user with prop/co-brand card as default card in wallet and star money available to redeem, user is able to see Star Rewards section in checkout page
    Given I visit the web site as a signed in user
    When I add a prop or co-brand card as default card in wallet having PLATINUM tier and star money available
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see star rewards section on shipping & payment page

  @wip @domain_marketing @artifact_shopapp @project_star_rewards
  Scenario: Verify that as a signed in user with prop/co-brand card as default card in wallet and star money not available to redeem, user is not able to see Star Rewards section in checkout page
    Given I visit the web site as a signed in user
    When I add a prop or co-brand card as default card in wallet having PLATINUM tier and star money not available
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should not see star rewards section on shipping & payment page

  @wip @domain_marketing @artifact_shopapp @project_star_rewards
  Scenario: Place an order as a signed in user with only Star Money reward card(s)
    Given I visit the web site as a signed in user
    When I add a prop or co-brand card as default card in wallet having PLATINUM tier and star money available
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    And I apply star money reward card such that the order total is satisfied
    And I place an order

  @wip @domain_marketing @artifact_shopapp @project_star_rewards
  Scenario Outline: Verify that in signed in checkout flow user is not able to see Star Rewards section and earn Star Reward points with VGC or EGC products
    Given I visit the web site as a signed in user
    When I add a prop or co-brand card as default card in wallet having PLATINUM tier and star money available
    And I add an "<product_type> and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should not see star rewards section on shipping & payment page

    Examples:
      | product_type         |
      | virtual_gift_card    |
      | electronic_gift_card |

  @wip @domain_marketing @artifact_shopapp @project_star_rewards
  Scenario: Verify that in signed in checkout flow user is not able to see Star Rewards section and earn Star Reward points with BT products
    Given I visit the web site as a signed in user
    When I add a prop or co-brand card as default card in wallet having PLATINUM tier and star money available
    And I add a big ticket "BT_indoor_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    And I continue checkout until I reach the shipping & payment page as a "BT signed in" user
    Then I should not see star rewards section on shipping & payment page

  @wip @domain_marketing @artifact_shopapp @project_star_rewards
  Scenario: Verify that in signed in checkout flow user is not able to see Star Rewards section and earn Star Reward points with BT and ST products
    Given I visit the web site as a signed in user
    When I add a prop or co-brand card as default card in wallet having PLATINUM tier and star money available
    And I add a big ticket "BT_indoor_furniture and ONHAND" product to bag
    And I add a "available and orderable" product to my bag
    And I navigate to shopping bag page
    And I continue checkout until I reach the shipping & payment page as a "BT signed in" user
    Then I should not see star rewards section on shipping & payment page

  @wip @domain_marketing @artifact_shopapp @project_star_rewards
  Scenario: Verify that signed in user is able earn Star Reward points with Prop/Co-Brand Card on placing an order
    Given I visit the web site as a signed in user
    When I add a prop or co-brand card as default card in wallet having PLATINUM tier and star money available
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see star rewards section on order confirmation page