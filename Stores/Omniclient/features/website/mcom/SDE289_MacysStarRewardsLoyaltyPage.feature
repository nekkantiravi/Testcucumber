# Author: Claudiu Chirila
# Story: SDE-289 - Create a page for Macy's Star Rewards Loyalty
# Date Created:
# Date Signed Off:


Feature: As a Clienteling associate I want to be able to see the Loyalty program data about my customer,
  so that I can make more complete decisions about outreach, and offer better customer service in order to drive sales.


  @domain_stores @omniclient @story_SDE-289 @website @mcom
  Scenario: "Star Rewards" Tab will exist in the tab navigation at the top of the customer profile page

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST1" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "30097" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST1 TEST ACCOUNT"
    Then the Star Rewards tab is displayed in tab navigation

  @domain_stores @omniclient @story_SDE-289 @website @mcom
  Scenario: "Star Rewards" page is accessible in the customer profile and will display loyalty information

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "TEST1" in the input field
    And I enter last name "TEST ACCOUNT" in the input field
    And I enter zip code "30097" in the input field
    And I click on the omniclient search button
    When I click on the searched client "TEST1 TEST ACCOUNT"
    Then the Star Rewards tab is displayed in tab navigation
    When I click on the Star Rewards tab
    Then the following information should be displayed on the Bottom Section of the loyalty page MCY:
      | OFFERS () |
#      | TRIPLE POINTS   |
      | EFFECTIVE |
      | EXPIRES   |

    And the following information should be displayed on the Top Left Section of the loyalty page MCY:
      | MEMBERSHIP                  |
      | ID#:                        |
      | Level:                      |
      | Spend to Upgrade: $         |
      | Last Reward Earned:         |
      | Star Money Data unavailable |


    And the following information should be displayed on the Top Right Section of the loyalty page MCY:
      | POINTS SUMMARY          |
      | Lifetime Points Earned: |
      | Current Points:         |
      | Pending Points:         |
      | Deferred Points:        |
      | Points to Next Reward:  |
    And I will validate that the Offers are displayed with the nearest expiration date first MCY

  @domain_stores @omniclient @story_SDE-289 @website @mcom
  Scenario: Positive Result for customer that does not exist in OCL database : takes user to Customer's Loyalty page

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the SearchByID radio button
# ID of a customer that doesn't exists in OCL database
    And I input "L920000161111" SearchByID in the search box
    And I click on the omniclient search button
    Then the Star Rewards page is displayed
