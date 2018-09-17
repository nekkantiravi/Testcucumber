# Author: Traci Morris
# Story: SDE-368 - OmniClient : UX Changes to the Top Section of the Loyallist Page
# Date Created: 10/31/2017
# Date Signed Off:

Feature: As a BLM b-connected user I want to see a simplified Loyallist page that contains all the Loyallist points &
  reward data I need, so I can easily leverage that data to drive sales & provide great loyalty customer service

  @domain_stores @omniclient @story_SDE-368 @website @bcom
  Scenario: Verifying UX Changes to the top section of the Loyallist page
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "KONSTANCE" in the input field
    And I enter last name "BACKET" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "KONSTANCE BACKET"
    And I navigate to BLM Loyallist tab
    Then the following information should be displayed on the Top Left Section of the loyalty page:
      | MEMBERSHIP                    |
      | ID#:                          |
      | Level:                        |
      | Spend to Upgrade: $           |
      | Last Reward Earned:           |
      | Rewards Card Data unavailable |
    And the following information should be displayed on the Top Right Section of the loyalty page:
      | POINTS SUMMARY                     |
      | Lifetime Points Earned:            |
      | Current Points:                    |
      | Pending Points:                    |
      | Deferred Points:                   |
      | Points Needed to Next Reward Card: |

  @domain_stores @omniclient @story_SDE-368 @website @bcom
  Scenario: Verifying UX Changes to the top section of the Loyallist page
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "KAREE" in the input field
    And I enter last name "BAREE" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "KAREE BAREE"
    And I navigate to BLM Loyallist tab
    Then the following information should be displayed on the Top Left Section of the loyalty page:
      | MEMBERSHIP                |
      | ID#:                      |
      | Level:                    |
      | Spend to Upgrade: $       |
      | Last Reward Earned:       |
      | UNREDEEMED REWARDS CARDS: |
    And the following information should be displayed on the Top Right Section of the loyalty page:
      | POINTS SUMMARY                     |
      | Lifetime Points Earned:            |
      | Current Points:                    |
      | Pending Points:                    |
      | Deferred Points:                   |
      | Points Needed to Next Reward Card: |