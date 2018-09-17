# Author: Ovidiu Rucoi
# Story: SDE-79 - OmniClient :: My Shop: Unassigned Selling Areas on the Unassigned Clients tab
# Date Created: 05/12/2017
# Date Signed Off:

Feature: As a Selling Manager in the MyShop configuration I want to see Unassigned Clients for other managers in the
  store as well as any areas that do not currently have a manager

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager

  @domain_stores @omniclient @story_SDE-79 @website @mcom
  Scenario: A list of staffing zones and Unassigned Areas/Clients is displayed in Unassigned Clients page
    And I click on My Clients from top navigation bar
    And navigate to Unassigned Clients tab
    And I should see a list of Selling Areas on the Unassigned Clients tab
    And I should see an Unassigned Area section on the Unassigned Clients tab

  @domain_stores @omniclient @story_SDE-79 @website @mcom
  Scenario: Selecting an Unassigned area from the Unassigned Clients page
    And I click on the Switch User button
    And I enter "LINDA D" credentials
    When I select the "LINDA DUDLEY" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    When I add a new Macys Client: "UNASSIGNED" "002CLIENT" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I remove the newly added Macys Client
    And I switch user back to the original login
    And I click on My Clients from top navigation bar
    And navigate to Unassigned Clients tab
    And I click on the Unassigned Area section from My Clients
    And I click on a specific Unassigned Area from My Clients
    Then a list of Unassigned Clients from that specific area is displayed
    And the following columns should be populated for the Unassigned Clients:
    | Customer                 |
    | Selling Associate        |
    | Relationship Date        |
    | Last Visit               |
    | Removed Date             |
    | Removed By               |

  @domain_stores @omniclient @story_SDE-79 @website @mcom
  Scenario: Reassigning a client from the Unassigned Clients page
    And I click on the Switch User button
    And I enter "LINDA D" credentials
    When I select the "LINDA DUDLEY" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    When I add a new Macys Client: "UNASSIGNED" "006CLIENT" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I remove the newly added Macys Client
    And I switch user back to the original login
    And I click on My Clients from top navigation bar
    And navigate to Unassigned Clients tab
    And I click on the Unassigned Area section from My Clients
    And I click on a specific Unassigned Area from My Clients
    Then a list of Unassigned Clients from that specific area is displayed
    When I select specific client from the list: "006CLIENT"
    And I select the "1" associate from Reassign to Selling Associate drop down
    And I click SAVE button from Unassigned Clients tab
    Then the previously selected client "006CLIENT" is no longer displayed in the Unassigned area

  @manual @domain_stores @omniclient @story_SDE-79 @mcom @website
  Scenario: The clients were moved to the selected Selling Associate
     When I log in with the SA to which the clients were re-assigned
     And I navigate to MY CLIENTS
     Then I should see the new-reassigned clients displayed