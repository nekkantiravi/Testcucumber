# Author: Traci Morris
# Story: SDE-345 - OmniClient : Macy's Loyalty: Loyalty Tier Icons on the My Client Top 100
# Date Created: 10/16/2017
# Date Signed Off:

Feature: As a MyClient User, I want to know the current Loyalty tier level status of my customer who is a member of the
  Macy's Star Rewards Loyalty Program, so that I can drive sales by leveraging this information.

  @domain_stores @omniclient @story_SDE-345 @website @mcom
  Scenario Outline: Verify the Loyalty Tier Icons are displayed on the My Client Top 100 screen

    Given I launch the macy's omniclient page
    When I enter "10000060" in username field of Omniclient login page
    And I enter "Temp$Pass10" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    And I navigate to Top100 tab
    Then I should see the Top100 screen
    And I should see the Star Rewards Level title on Top100 screen
    And I should see the Star Rewards Tier Icons "<icon>" on the Top100 screen

    Examples:
      | icon        |
      | gold        |
      | silver      |
      | platinum    |
      | non-loyalty |


