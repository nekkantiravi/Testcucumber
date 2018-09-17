# Author: Robert Vargas
# Story:
# Date Created: 12/04/2017
# Date Signed Off:

Feature: As a BLM's associate, I want to Create, Validate, and Delete Memos

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and verify Save Button is disabled
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the MEMOs button in the Communication Center
    And I click on the Create Memo button
    Then I should see the Save button is disabled

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and Create a Memo
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the MEMOs button in the Communication Center
    And I click on the Create Memo button
    And I type "Rob Test" in the MEMOS textbox
    And I click on the Memos Save button
    Then I click the memo dropdown chevron of the newly created memo
    And I see the memo with the following details "Rob Test"

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login, create Memo and verify counter memo counter increases
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the MEMOs count
    And I click on the MEMOs button in the Communication Center
    And I click on the Create Memo button
    And I type "Rob Test" in the MEMOS textbox
    And I click on the Memos Save button
    And I navigate to Bloomingdales Homepage
    Then I will validate that the MEMOs count incremented by 1

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login, and navigate from memos to the Macy's homepage
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the MEMOs button in the Communication Center
    And I click the Memos navigation X Icon
    Then I should be on the BLM homepage

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and Delete a Memo
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the MEMOs button in the Communication Center
    And I click on the checkbox to select a memo
    Then I click the memo dropdown chevron of the newly created memo
    And I see the memo with the following details "Rob Test"
    Then I click the memo dropdown chevron of the newly created memo
    Then I click the Delete Memo button
    And I click YES on the delete memo popup

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and delete and verify memo count decreased by 1
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the MEMOs count
    And I click on the MEMOs button in the Communication Center
    And I click on the checkbox to select a memo
    Then I click the memo dropdown chevron of the newly created memo
    And I see the memo with the following details "Rob Test"
    Then I click the memo dropdown chevron of the newly created memo
    Then I click the Delete Memo button
    And I click YES on the delete memo popup
    Then I navigate to Macys Homepage
    And I will validate the Memos count decremented by 1




