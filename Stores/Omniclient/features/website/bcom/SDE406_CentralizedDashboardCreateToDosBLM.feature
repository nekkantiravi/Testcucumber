# Author: Claudiu Chirila
# Story: Centralized Dashboard Create To Dos
# Date Created: 11/29/2017
# Date Signed Off:

Feature: As an associate, I want a singular way to create To Dos from my dashboard, so that I can efficiently perform my work

  @domain_stores @omniclient @Story_SDE-406 @bcom
  Scenario: When more than one customer is selected the button label will update to "Create To-Dos"

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I validate that the Create To Do button from homepage is labeled as "Create To Do"
    And I select the checkbox of the first client from the HOMEPAGE clients list
    And I select the checkbox of the second client from the HOMEPAGE clients list
    And I select the checkbox of the third client from the HOMEPAGE clients list
    Then the Create To Do button label will update to "Create To-Dos"
#    And the Create To Do button is not present in homepage dashboard view My To Dos section
#    And the Create List button is not present in homepage dashboard view My Customers section