# Author: Traci Morris
# Story: Create To Dos BLM
# Date Created: 10/16/2017
# Date Signed Off:

Feature: As an associate, I want to Create Self To Dos and Client To Dos from various locations in the application.

   @RegBcom @domain_stores @omniclient @website
  Scenario: Mark all todos as completed
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I navigate to MY TASKS page
    Then I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |

  @RegBcom @domain_stores @omniclient @website
  Scenario: Creating a SELF TO DO on the MY TO DOS tab as an associate
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I navigate to MY TASKS page
    Then I should see the MY TO DOS page
    When I click on the Create To Do button on MY TO DOS page
    Then I should see the input boxes to create a to do
      | TITLE       |
      | DATE        |
      | CLIENT      |
      | DESCRIPTION |
    And I enter a title "REGRESSION TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I enter a description "This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    Then the To Do "REGRESSION TO DO" is saved on the Associates MY TASKS tab
    When I navigate to Bloomingdales Homepage
    Then I will validate that the My ToDos count incremented by 1
    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |

   @RegBcom @domain_stores @omniclient @website
  Scenario: Error handling when creating a SELF TO DO
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate to MY TASKS page
    Then I should see the MY TO DOS page
    When I click on the Create To Do button on MY TO DOS page
    Then I should see the input boxes to create a to do
      |TITLE      |
      |DATE       |
      |CLIENT     |
      |DESCRIPTION|
    And I enter a title "2nd REG TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I enter a description "2587412589" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    Then I should see the Credit Card information in Description field error message
#    Credit Card information may not be entered into the Description field. Please try again.
    When I select the OK button on the error message
    Then I should see the MY TO DOS page
    When I remove the Title in the input field on MY TO DOS page
    Then the Save button becomes disabled
    And I enter a title "2587412589" in the input field on MY TO DOS page
    And I enter a description "UPDATE DESCRIPTION" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    Then I should see the Credit Card information in Title field error message
#    Credit Card information may not be entered into the Title field. Please try again.
    When I select the OK button on the error message
    Then I should see the MY TO DOS page
    When I remove the Title in the input field on MY TO DOS page
    And I enter a title "2nd REG TO DO" in the input field on MY TO DOS page
    And I click the X button located on the top right corner of the To Do page
    Then I should see the Disregard Changes message
#    Disregard Changes? Are you sure you want to navigate away from this page? Your changes have not been saved.
#    If you leave this page without saving, all changes will be lost. Press OK to continue, or Cancel to stay on the current page.
    When I select the Cancel button on the Disregard Changes error message
    Then I should see the MY TO DOS page
    And I click the X button located on the top right corner of the To Do page
    Then I should see the Disregard Changes message
#    Disregard Changes? Are you sure you want to navigate away from this page? Your changes have not been saved.
#    If you leave this page without saving, all changes will be lost. Press OK to continue, or Cancel to stay on the current page.
    When I select the OK button on the Disregard Changes error message
    Then I will see the associates HOMEPAGE


  @RegBcom @domain_stores @omniclient @website
  Scenario: Creating and Editing a CLIENT TO DO on the MY TO DOS tab as an associate
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I navigate to MY TASKS page
    Then I should see the MY TO DOS page
    When I click on the Create To Do button on MY TO DOS page
    Then I should see the input boxes to create a to do
      |TITLE      |
      |DATE       |
      |CLIENT     |
      |DESCRIPTION|
    And I enter a title "3RD TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "TRACI" in the input filed from MY TO DOS page
    And I select the full name "TRACI TEST" from client dropdown MY TO DOS page
    And I enter a description "This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    Then the To Do "3RD TO DO" is saved on the Associates MY TO DOS tab
    When I navigate to Bloomingdales Homepage
    Then I will validate that the My ToDos count incremented by 1
    And I navigate to ALL TO DOS page
    Then I should see the MY TO DOS page
    When I click on the Chevron on the Associates MY TO DOS page
    And I click on the Edit button from the TO DO
    And I enter a title "EDIT A TO DO" in the input field on edit MY TO DOS page
    And I select a valid date in Edit To Dos View on MY TO DOS page
#    And I enter a description "NEW DESCRIPTION" in the input field on edit MY TO DOS page
    And I click on the EDIT TO DO Save button
    Then the To Do "EDIT A TO DO" is saved on the Associates MY TO DOS tab
    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |

  @RegBcom @domain_stores @omniclient @website
  Scenario: Creating a CLIENT TO DO from the Homepage as an associate
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I select the checkbox of the first client from the HOMEPAGE clients list
    When I click on the Create To Do button on HOMEPAGE
    Then I should see the input boxes to create a to do
      |TITLE      |
      |DATE       |
      |DESCRIPTION|
    And I enter a title "4TH TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I enter a description "This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    Then the To Do "4TH TO DO" is saved on the Associates MY TO DOS tab
    When I navigate to Bloomingdales Homepage
    Then I will validate that the My ToDos count incremented by 1
    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |


   @RegBcom @domain_stores @omniclient @website
  Scenario: Creating a CLIENT TO DO from the Clients Profile page as an associate
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I select a Bloomingdales client from OCL website Homepage
    When I click on the Create To Do button on Client PROFILE page
    Then I should see the input boxes to create a to do on Clients MY TO DOS tab
      |TITLE      |
      |DATE       |
      |DESCRIPTION|
    And I enter a title "5TH TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on Client MY TO DOS page
    And I enter a description "This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button on Clients MY TO DOS tab
    Then the To Do "5TH TO DO" is saved on the Clients MY TO DOS tab
    When I navigate to Bloomingdales Homepage
    Then I will validate that the My ToDos count incremented by 1
    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |


    @RegBcom @domain_stores @omniclient @website
  Scenario: Creating a CLIENT TO DO from the Manage Client page as an associate
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I select a Bloomingdales client from OCL website Homepage
    When I click on the Create To Do button on Client PROFILE page
    Then I should see the input boxes to create a to do
      |TITLE      |
      |DATE       |
      |DESCRIPTION|
    And I enter a title "6TH REG TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on Client MY TO DOS page
    And I enter a description "This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button on Clients MY TO DOS tab
    Then the To Do "6TH REG TO DO" is saved on the Clients MY TO DOS tab
    When I navigate to Bloomingdales Homepage
    Then I will validate that the My ToDos count incremented by 1
    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
  #      | EMAIL          |
  #      | MAIL           |
  #      | TEXT           |
  #      | IN PERSON      |
  #      | PHONE LEFT MSG |
  #      | NO ACTION      |
        | PHONE COMPLETE |


  @RegBcom @domain_stores @omniclient @website
  Scenario: Creating a CLIENT TO DO on the MY CLIENTS tab as an associate
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I click on My Clients from top navigation bar
    And I select the checkbox of the first client from the MY CLIENTS page
    When I click on the Create To Do button on MY CLIENTS page
    Then I should see the input boxes to create a to do
      |TITLE      |
      |DATE       |
      |CLIENT     |
      |DESCRIPTION|
    And I enter a title "7TH REG TO DO" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I enter a description "This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button on Clients MY TO DOS tab
    Then the To Do "7TH REG TO DO" is saved on the Associates MY TO DOS tab
    When I navigate to Bloomingdales Homepage
    Then I will validate that the My ToDos count incremented by 1