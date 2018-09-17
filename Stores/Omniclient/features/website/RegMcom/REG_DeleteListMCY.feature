# Author: Ovidiu Rucoi
# Story: Delete List
# Date Created: 11/08/2017
# Date Signed Off:

Feature: As an associate, I want to be able to delete lists

  Background:
    Given I launch the macy's omniclient page
    And I sign into Omniclient application as associate

  @RegMcom @domain_stores @omniclient @website
  Scenario: Verify delete alert - future due date
    And I create a TO DO from CREATE TO DOS page
      | DELETE LIST |
    Then I should see the list of TO DOS
    And I navigate to Macys Homepage
    And I check the My Macys To DOs count
#    No - Verify list did NOT delete - event info page displayed
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    When I click on delete ToDo icon from Lists page
    Then I should see the delete ToDo popup
    When I click on NO on the delete ToDo popup
    And I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
#    Yes - Verify list deleted - Verify list counter decreases
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    And I click on delete ToDo icon from Lists page
    Then I should see the delete ToDo popup
    When I click on YES on the delete ToDo popup
    Then I should no longer see the deleted todo "DELETE LIST" on the MY MACYS TO DOS
    When I navigate to Macys Homepage
    Then I will validate that the My Macys ToDos count decreased by 1
    When I click on My Clients from top navigation bar
    And I click on a client from my clients list
    Then I should no longer see the deleted todo "DELETE LIST" on the CLIENT PROFILE

  @RegMcom @domain_stores @omniclient @website
  Scenario: Delete  - Complete To Do
    And I create a TO DO from CREATE TO DOS page
      | COMPLETE LIST |
    Then I should see the list of TO DOS
    And I click on the newly created My Macys To Do
    Then EVENT INFORMATION page is displayed
    When I mark all TO DOs as completed MY TO DOS page
    Then I should see the To Do marked as completed in the List
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should not see any ToDos that can be deleted
