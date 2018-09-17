# Author: Claudiu Chirila
# Story: SDE-149 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

Feature: As a Selling Manager in the MyShop configuration I want to Manage Relationships for any associate in the store,
  so that I can help maintain and manage the relationships between my sellers and their customers

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager

  @domain_stores @omniclient @story_SDE-149 @website @mcom
  Scenario: Unassigned area is displayed in Manage Relationships page
    And I click on My Clients from top navigation bar
    Then Manage Relationships page is displayed
    And Unassigned area section is displayed

  @domain_stores @omniclient @story_SDE-149 @website @mcom
  Scenario: Upon clicking on the Unassigned area a list of unassigned selling areas are displayed
    And I click on My Clients from top navigation bar
    Then Manage Relationships page is displayed
    And Unassigned area section is displayed
    And I click on the Unassigned Area section from Manage Relationships
    Then a list of unassigned selling areas is displayed

  @domain_stores @omniclient @story_SDE-149 @website @mcom
  Scenario: When user clicks on an Unassigned area, a drop down containing all associates who are in that Unassigned Selling area are displayed
    And I click on My Clients from top navigation bar
    Then Manage Relationships page is displayed
    And Unassigned area section is displayed
    And I click on the Unassigned Area section from Manage Relationships
    And I click on a specific Unassigned Area from Manage Relationships
    Then a drop down with associates who are in that Unassigned Selling area is displayed

  @domain_stores @omniclient @story_SDE-149 @website @mcom
  Scenario: The previously selected clients are no longer displayed in the Unassigned area
    And I click on the Switch User button
    And I enter "LINDA D" credentials
    When I select the "LINDA DUDLEY" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "associate"
    # parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "UNASSIGNED" "0005TESTUSER" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I switch user back to the original login
    And I click on My Clients from top navigation bar
    Then Manage Relationships page is displayed
    And Unassigned area section is displayed
    And I click on the Unassigned Area section from Manage Relationships
    And I click on a specific Unassigned Area from Manage Relationships
    And I select an associate "LINDA DUDLEY" from Select Selling Associate drop down
    Then a list of Unassigned Clients from that specific area is displayed in Manage Relationships page
    When I select specific client from the list in Manage Relationships view: "0005TESTUSER"
    And I select the "3" associate from Reassign to Selling Associate drop down
    And I click SAVE button from Manage Relationships page
   #And I click Continue button from attention popup
    Then the previously selected client "0005TESTUSER" is no longer displayed in the Unassigned area Manage Relationships

  @manual @domain_stores @omniclient @story_SDE-149 @mcom @website
  Scenario: The clients were moved to the selected Selling Associate
     When I log in with the SA to which the clients were re-assigned
     And I navigate to MY CLIENTS view
     Then I should see the new-reassigned clients displayed
