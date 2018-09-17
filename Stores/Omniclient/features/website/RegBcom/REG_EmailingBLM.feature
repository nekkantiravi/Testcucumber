# Author: Robert Vargas
# Story:
# Date Created: 12/18/2017
# Date Signed Off:

Feature: As a BLM’s associate, I want to email my customers from different screens.

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send an email from the MY TASKS tab
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate (83)
    And I click on the MY TASKS tab
    And I click on the email icon next to Customer name
    And I type "Test" in the email SUBJECT textbox
    And I type “Hello” in the message textbox
    And I click the email send button
    Then I should see that the email has been sent to “customer name”

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel an email from the MY TASKS tab
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate (83)
    And I click on the MY TASKS tab
    And I click on the email icon next to Customer name
    And I type "Test" in the email SUBJECT textbox
    And I type “Hello” in the Message textbox
    And I click on the X icon on the email screen
    And I click yes on the email warning
    Then I should be on the MY TASKS page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from the MY CUSTOMERS tab
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate (83)
    And I click on the MY CUSTOMERS tab
    And I click on the text message icon next to customer name
    And I type “Hello” in the text message textbox
    And I click the texting send button
    Then I should see that the text message has sent to “client name”

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the MY CUSTOMERS tab
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate (83)
    And I click on the My CUSTOMERS tab
    And I click on the text message icon next to Customer name
    And I type “Hello” in the text message textbox
    And I click on the X icon on the texting screen
    Then I should be on the MY CUSTOMERS page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Send a text from DASHBOARD tab
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate (83)
    And I click on the text message icon next to Customer name
    And I type “Hello” in the text message textbox
    And I click the texting send button
    Then I should see that the text message has sent to “customer name”

  @RegMcom @domain_stores @omniclient @website
  Scenario: Cancel a text from the DASHBOARD
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate (83)
    And I click on the text message icon next to customer name
    And I type “Hello” in the text message textbox
    And I click on the X icon on the texting screen
    Then I should be on the DASHBOARD page
