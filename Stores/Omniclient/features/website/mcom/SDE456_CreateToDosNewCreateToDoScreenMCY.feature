# Story: Create To Dos from the New Create To Do Screen
# Date Created: 12/28/2017
# Date Signed Off:

Feature: As an associate, I want a singular way to create To Dos from my dashboard

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the Create To Do button on HOMEPAGE

  @mcom @domain_stores @omniclient @website
  Scenario: New Create To Do Screen - View
    Then I should see the input boxes to create a to do
      | CLIENT      |
      | TITLE       |
      | DATE        |
      | DESCRIPTION |
    And the CREATE TO DO button should be displayed in the Create To Do view
    And the CANCEL button should be displayed in the Create To Do view
    And the Required Fields note should be displayed in the Create To Do view

  @mcom @domain_stores @omniclient @website
  Scenario: New Create To Do Screen - Create To Do button validation
#    NO Client added
    And I enter a title "Test Title" in the input field on CREATE TO DOS page
    And I select a valid date in Create To Dos View on CREATE TO DOS page
    Then the CREATE TO DO button should be displayed in the Create To Do view
    And the CREATE TO DO button should be disabled in the Create To Do view
#    ONE Client added
    When I input client name "KARI" in the input filed from Create ToDo page
    And I select the full name "KARI BENNET" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    Then the CREATE TO DO button should be enabled in the Create To Do view
#    AN ADDITIONAL Client is added
    When I input client name "KAREN" in the input filed from Create ToDo page
    And I select the full name "KAREN BAKERTON" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    Then the CREATE TO DOS button should be displayed in the Create To Do view

  @mcom @domain_stores @omniclient @website
  Scenario: New Create To Do Screen - Title Field Validation
#    NO Title
    When I input client name "KARI" in the input filed from Create ToDo page
    And I select the full name "KARI BENNET" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    And I select a valid date in Create To Dos View on CREATE TO DOS page
    Then the CREATE TO DO button should be disabled in the Create To Do view
#    Input max characters
    When I enter a title "abcde1ghijk@mnopqrstuvwxyz1234" in the input field on CREATE TO DOS page
    Then the CREATE TO DO button should be enabled in the Create To Do view
#    Input over max characters
    When I enter a title "12345abcdefghijklmnopqrstuvwxyz" in the input field on CREATE TO DOS page
    Then I should see the title "12345abcdefghijklmnopqrstuvwxy" on CREATE TO DOS page
    And the CREATE TO DO button should be enabled in the Create To Do view
#    Input Credit Card Details
    When I enter a title "123456789" in the input field on CREATE TO DOS page
    And I click the CREATE TO DOS button in the Create To Do view
    Then I should see the Credit Card information in Title field error message

  @mcom @domain_stores @omniclient @website
  Scenario: New Create To Do Screen - Date Validation
#    NO Date selected
    When I input client name "KARI" in the input filed from Create ToDo page
    And I select the full name "KARI BENNET" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    And I enter a title "Title123" in the input field on CREATE TO DOS page
    Then the CREATE TO DO button should be disabled in the Create To Do view

  @mcom @domain_stores @omniclient @website
  Scenario: New Create To Do Screen - Description Validation
#    NO Description
    When I input client name "KARI" in the input filed from Create ToDo page
    And I select the full name "KARI BENNET" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    And I enter a title "Title123" in the input field on CREATE TO DOS page
    And I select a valid date in Create To Dos View on CREATE TO DOS page
    Then the CREATE TO DO button should be enabled in the Create To Do view
#    More than 250 characters
    When I enter 300 characters in the Description input field on CREATE TO DOS page
    Then 250 characters should be displayed in the Description input field on CREATE TO DOS page
#    Input Credit Card Details
    When I enter a description "123456789" in the input field on CREATE TO DOS page
    And I click the CREATE TO DOS button in the Create To Do view
    Then I should see the Credit Card information in Description field error message

  @mcom @domain_stores @omniclient @website
  Scenario: New Create To Do Screen - Single ToDos should be created instead of a List
    When I input client name "KARI" in the input filed from Create ToDo page
    And I select the full name "KARI BENNET" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    And I input client name "KAREN" in the input filed from Create ToDo page
    And I select the full name "KAREN BAKERTON" from client dropdown Create ToDo page
    And user clicks on the ADD button form Create TO Dos page
    And I enter a title "TODONOTLIST" in the input field on CREATE TO DOS page
    And I select a valid date in Create To Dos View on CREATE TO DOS page
    And I enter a description "DESCRIPTION" in the input field on CREATE TO DOS page
    And I click the CREATE TO DOS button in the Create To Do view
    Then I should see the MY TO DOS page
    And the To Do "TODONOTLIST" is saved on the Associates MY TO DOS tab for client "KARI BENNET"
    And the To Do "TODONOTLIST" is saved on the Associates MY TO DOS tab for client "KAREN BAKERTON"
#    This step is just to clean up the ToDos after test
    And I mark all TO DOs as completed MY TO DOS page
