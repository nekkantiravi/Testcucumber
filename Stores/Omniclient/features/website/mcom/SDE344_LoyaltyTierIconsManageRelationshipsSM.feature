# Author: Ovidiu Rucoi
# Story: SDE344 - Macy's Loyalty: Loyalty Tier Icons on the SM Manage Relationships page
# Date Created: 10/24/2017
# Date Signed Off:

Feature: As a MyClient User, I want to know the current Loyalty tier level status of my customer who
  is a member of the Macy's Star Rewards Loyalty Program, so that I can drive sales by leveraging this information.

  @domain_stores @omniclient @story_SDE-344 @website @mcom
  Scenario: Loyalty Tier Icons are displayed on the My Client screen - Manage Relationship
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    And I click on My Clients from top navigation bar
    Then Manage Relationships page is displayed
    And I should see the Star Rewards Level title on Manage Relationships screen
    And I should see the Star Rewards Tier Icons on Manage Relationships screen
      | gold        | TEST1 TEST ACCOUNT  |
      | silver      | PITACCTEMV YY MACYS |
      | platinum    | TEST1 TEST ACCOUNT  |
      | non-loyalty | KRISPEN BAKER       |


  @domain_stores @omniclient @story_SDE-344 @website @mcom
  Scenario: Loyalty Tier Icons sorting - Manage Relationships
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    And I click on My Clients from top navigation bar
    Then Manage Relationships page is displayed
    And I should see the Star Rewards Level title on Manage Relationships screen
    When I expand the first Selling Area from Manage Relationships screen
    And I click on the Star Rewards Level title
    Then I should see the Loyalty icons sorted "descending"
    When I click on the Star Rewards Level title
    Then I should see the Loyalty icons sorted "ascending"