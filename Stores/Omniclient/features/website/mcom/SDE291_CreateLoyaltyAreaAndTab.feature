# Author: Claudiu Chirila
# Story: SDE-291 - Create Loyalty area and tab on main customer profile page
# Date Created:
# Date Signed Off:

Feature: As a Macy's Clienteling User I want to be able to see my customer's loyalty Information Summary,
  so I can offer more complete customer service and be aware of my customer's Loyalty status.

  @domain_stores @omniclient @story_SDE-291 @website @mcom
  Scenario: "Star Rewards" Tab will exist in the tab navigation at the top of the customer profile pages,
  and "Star Rewards" section is displayed at the bottom center of main customer page
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "291" in the input field
    And I enter last name "TEST" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "291 TEST"
#    Then the Star Rewards tab is displayed in tab navigation
    Then Star Rewards section is displayed in main customer profile page
    And the plus sign is displayed in the left of Star Rewards section
    And Star Rewards section contains the following components
      | Loyalty Tier                        |
      | Loyalty ID                          |
      | Current Points                      |
      | Pending Points                      |
      | Points needed to get to next Reward |

