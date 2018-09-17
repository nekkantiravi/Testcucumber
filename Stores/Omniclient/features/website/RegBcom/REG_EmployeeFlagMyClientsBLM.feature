# Author: Chirila Claudiu
# Story:
# Date Created:
# Date Signed Off:

Feature: Next to Client Name link the Employee Flag icon is displayed.


  @RegMcom @domain_stores @omniclient @website
  Scenario: Employee Flag icon is displayed next to Client Name link
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Clients from top navigation bar
    And I navigate until a employee client is displayed


     #TODO Investigate how we can add a employee flag to a new or existing user
  @RegMcom @domain_stores @omniclient @website
  Scenario:After we create a TO DO for an employee, the Employee Flag icon is displayed next to Client Name link in MY TO DOS page
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    Then I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page

    And I navigate to MY TASKS page
    Then I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |


    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "TEST EMPLOYEE FLAG" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I enter a description "Testing Employee flag" in the input field on MY TO DOS page
    And I input client name "TESTS691 LOOKUPS691" in the input filed from MY TO DOS page
    And I select the full name "TESTS691 LOOKUPS691" from client dropdown MY TO DOS page
    And I click on the TO DO Save button
    Then the new created TODO for client "TESTS691 LOOKUPS691" is displayed first in list
    And for the first TO DO in list the employee flag is displayed next to the client name

  @RegMcom @domain_stores @omniclient @website
  Scenario: Employee flag is displayed after we access Client profile from Search Results page
    Given I launch the bloomingdales's omniclient page
    When I enter "10000083" in username field of Omniclient login page
    Then I enter "Temp$Pass83" in password field of Omniclient login page
    And I click Sign In button of Omniclient login page
    And I click on My Book radio button
    And I type the name of a customer "TESTS691" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    Then the client name "TESTS691" is displayed in Client Profile page
    And the employee flag is displayed next to the client name Client Profile