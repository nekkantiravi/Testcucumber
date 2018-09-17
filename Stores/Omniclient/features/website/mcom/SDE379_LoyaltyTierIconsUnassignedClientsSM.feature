# Author: Ovidiu Rucoi
# Story: SDE379 - Macy's Loyalty: Loyalty Tier Icons on the SM Unassigned Clients page
# Date Created: 10/24/2017
# Date Signed Off:

Feature: As a MyClient User, I want to know the current Loyalty tier level status of my customer who
  is a member of the Macy's Star Rewards Loyalty Program, so that I can drive sales by leveraging this information.

  @domain_stores @omniclient @story_SDE-379 @website @mcom
  Scenario Outline: Add clients to book
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I enter "<phoneNbr>" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    And the searched client is displayed in the search results page "<name>"
    When I click the Add to Book button from search results
    Then the Create new Client page is displayed
    When I enter "TEST" in the hint Textbox
    And I select "MOBILE" from the phone type dropdown on Create Client page
    And  I select the phone radio button as the preferred contact method
    And  I click save on the create new client page
    Then I click ok on the popup screen

    Examples:
      | phoneNbr   | name                |
      | 6784746548 | TEST1 TEST ACCOUNT  |
      | 4409888541 | TEST1 TEST ACCOUNT  |


  @domain_stores @omniclient @story_SDE-379 @website @mcom
  Scenario Outline: Loyalty Tier Icons are displayed on the My Client screen - Unassigned Clients
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I select search by "name"
    And I enter "FIFTY ON" credentials
    And I select the "FIFTY ONE" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    And I enter "<phoneNbr>" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the searched client is displayed in the search results page "<name>"
    When I click on the searched client "<name>"
    And I remove the newly added Macys Client
    And I switch user back to the original login
    And I click on My Clients from top navigation bar
    And navigate to Unassigned Clients tab
    And I should see the Star Rewards Level title on Unassigned Clients screen
    And I should see the Star Rewards Tier Icons on Unassigned Clients screen
      | <loyalTier> | <name> |

    Examples:
      | phoneNbr   | loyalTier | name                |
      | 6784746548 | gold      | TEST1 TEST ACCOUNT  |
      | 4409888541 | platinum  | TEST1 TEST ACCOUNT  |
      | 4402339874 | silver    | PITACCTEMV YY MACYS |

#  @domain_stores @omniclient @story_SDE-379 @website @mcom
#  Scenario: Loyalty Tier Icons sorting - Unassigned Clients
#    Given I launch the macy's omniclient page
#    When I sign into Omniclient application as Selling Manager
#    And I click on My Clients from top navigation bar
#    Then Manage Relationships page is displayed
#    And I should see the Star Rewards Level title on Unassigned Clients screen
#    When I expand the first Selling Area from Unassigned Clients screen
#    And I click on the Star Rewards Level title
#    Then I should see the Loyalty icons sorted "descending"
#    When I click on the Star Rewards Level title
#    Then I should see the Loyalty icons sorted "ascending"


  @domain_stores @omniclient @story_SDE-379 @website @mcom
  Scenario Outline: Add clients back to book
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I enter "<phoneNbr>" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    And the searched client is displayed in the search results page "<name>"
    When I click the Add to Book button from search results
    Then the Create new Client page is displayed
    When I enter "TEST" in the hint Textbox
    And I select "MOBILE" from the phone type dropdown on Create Client page
    And  I select the phone radio button as the preferred contact method
    And  I click save on the create new client page
    Then I click ok on the popup screen

    Examples:
      | phoneNbr   | name                |
      | 4402339874 | PITACCTEMV YY MACYS |