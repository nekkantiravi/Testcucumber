# Author: Traci Morris
# Story: SDE-337 - OmniClient : Macy's Loyalty: Loyalty Tier Icons on the Black Customer Info Bar
# Date Created: 10/16/2017
# Date Signed Off:

Feature: As a MyClient User, I want to know the current loyalty tier level status of my customer who is a member of the
  Macy's Star Rewards Loyalty Program, so that I can drive sales by leveraging this information.

  @domain_stores @omniclient @story_SDE-337 @website @mcom
  Scenario Outline: Verify the Loyalty Tier Icons are displayed on the Customer Info Bar on the Clients Profile screen.

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate

    And I enter "<phoneNbr>" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the searched client is displayed in the search results page "<name>"
    When I click on the searched client "<name>"
    Then I should see the Star Rewards Tier Icons on the Customer Info Bar
      | <loyalTier> | <name> |


    Examples:
      | phoneNbr   | loyalTier | name                |
      | 6784746548 | gold      | TEST1 TEST ACCOUNT  |
      | 4409888541 | platinum  | TEST1 TEST ACCOUNT  |
      | 4402339874 | silver    | PITACCTEMV YY MACYS |




