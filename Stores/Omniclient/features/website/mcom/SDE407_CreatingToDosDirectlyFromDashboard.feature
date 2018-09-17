# Author: Claudiu Chirila
# Story: New Creating To Dos Screen
# Date Created: 11/28/2017
# Date Signed Off:

Feature: As an associate, I want a singular way to create To Dos from my dashboard, so that I can efficiently perform my work.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate

  @mcom @domain_stores @omniclient @website @SDE-407
  Scenario: If no customers were selected (via checkbox on the dashboard),the To Dos page should display TITLE, DATE and DESCRIPTION
    And I click on the Create To Do button on HOMEPAGE
    Then I should see the input boxes to create a to do
      | CLIENT      |
      | TITLE       |
      | DATE        |
      | DESCRIPTION |
    And the CANCEL button should be displayed in the Create To Do view

  @mcom @domain_stores @omniclient @website @SDE-407
  Scenario: If more than one customer was selected (via checkbox on the dashboard),  the selected customers name will be displayed next to the Customer field
    And I select the checkbox of the first client from the HOMEPAGE clients list
    And I select the checkbox of the second client from the HOMEPAGE clients list
    And I select the checkbox of the third client from the HOMEPAGE clients list
    And I click on the Create To Do button on HOMEPAGE
    Then I should see the input boxes to create a to do
      | CLIENT      |
      | TITLE       |
      | DATE        |
      | DESCRIPTION |
    And the names of the selected customers are displayed next to the customer field "KRISPEN BAKER" "KARI BENNET" "KATIA BURLAKOVA"
#    And the CLIENT field should be prepopulated with the selected client from homepage create to do
    And I enter a title "NEW TO DO" in the input field on CREATE TO DOS page
    And I select a valid date in Create To Dos View on CREATE TO DOS page
    And I enter a description "This is a regression test" in the input field on CREATE TO DOS page

  @mcom @domain_stores @omniclient @website @SDE-407
  Scenario: Disregard changes message is displayed when user clicks on CANCEL button
    And I select the checkbox of the first client from the HOMEPAGE clients list
    And I select the checkbox of the second client from the HOMEPAGE clients list
    And I select the checkbox of the third client from the HOMEPAGE clients list
    And I click on the Create To Do button on HOMEPAGE
    And I enter a title "NEW TO DO" in the input field on CREATE TO DOS page
    And I select a valid date in Create To Dos View on CREATE TO DOS page
    And I enter a description "This is a regression test" in the input field on CREATE TO DOS page
    And I click on CANCEL button from Create To Dos page
    Then disregard changes message is displayed

  @mcom @domain_stores @omniclient @website @SDE-407
  Scenario: Homepage is displayed after user clicks on the OK button from disregard changes popup CREATE TO DOS page
    And I select the checkbox of the first client from the HOMEPAGE clients list
    And I select the checkbox of the second client from the HOMEPAGE clients list
    And I select the checkbox of the third client from the HOMEPAGE clients list
    And I click on the Create To Do button on HOMEPAGE
    And I click on CANCEL button from Create To Dos page
    And I click OK on the disregard changes popup message Create To Dos page
    Then the Macys homepage is displayed website


  @mcom @domain_stores @omniclient @website @SDE-407
  Scenario: If the "Multiple Customers" state is canceled out, the customer should no longer be displayed
    And I select the checkbox of the first client from the HOMEPAGE clients list
    And I select the checkbox of the second client from the HOMEPAGE clients list
    And I select the checkbox of the third client from the HOMEPAGE clients list
    And I click on the Create To Do button on HOMEPAGE
    And I click on X buttons next to the CUSTOMER field create to do from homepage
    Then the removed clients are no longer displayed in Create To Dos page
