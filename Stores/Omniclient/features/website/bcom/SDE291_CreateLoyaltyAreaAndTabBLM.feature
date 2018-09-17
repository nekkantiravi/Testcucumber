# Author: Claudiu Chirila
# Story: SDE-291 - Create Loyalty area and tab on main customer profile page
# Date Created:
# Date Signed Off:


Feature: As a Macy's Clienteling User I want to be able to see my customer's loyalty Information Summary,
  so I can offer more complete customer service and be aware of my customer's Loyalty status.

  @domain_stores @omniclient @story_SDE-291 @website @mcom
  Scenario: "LOYALLIST INFORMATION" Tab will exist in the tab navigation at the top of the customer profile pages,
  and "LOYALLIST INFORMATION" section is displayed at the bottom center of main customer page
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "KONSTANCE" in the input field
    And I enter last name "BACKET" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
#    When I click on the searched client from the customers results list
    When I click on the searched client "KONSTANCE BACKET"
#    Then the Star Rewards tab is displayed in tab navigation
    Then Star Rewards section is displayed in main customer profile page BLM
    And the plus sign is displayed in the left of Star Rewards section BLM
    And Star Rewards section contains the following components BLM

      | Loyalty ID                          |
      | Current Points                      |
      | Pending Points                      |
      | Deferred Points                        |
      | Points needed to get to next Reward |