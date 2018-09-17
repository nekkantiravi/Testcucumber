# Author: Claudiu Chirila
# Story: SDE-366 - OmniClient : Macy's Loyalty: Loyalty Tier Icons on the SA Dashboard
# Date Created: 10/16/2017
# Date Signed Off:

Feature: As a MyClient User, I want to know the current Loyalty tier level status of my customer who is a member of the Macy's Star Rewards Loyalty Program,
  so that I can drive sales by leveraging this information.


  @domain_stores @omniclient @story_SDE-336 @website @mcom
  Scenario Outline: Verify the Loyalty Tier Icons are displayed on the Homepage Dashboard screen

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should see the My Clients table from Homepage Dashboard page
    #the bellow methods will validate the Loyalty icons on HOMEPAGE Dashboard - My Clients table
    And I should see the Star Rewards Tier Icons "<icon>" on Homepage Dashboard My Clients table

    Examples:
      | icon     |
      | gold     |
      | silver   |
      | platinum |

