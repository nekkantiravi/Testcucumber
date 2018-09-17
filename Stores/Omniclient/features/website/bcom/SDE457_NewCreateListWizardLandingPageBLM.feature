# Author: Ovidiu Rucoi
# Story: SDE-457 New Create List Wizard - Landing Page
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to filter customers by customer/loyalty/shopping attributes, in order to create
  a customized list of my clients for personalized outreach.

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate on Create List tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @bcom @domain_stores @omniclient @website @story_SDE-457
  Scenario: List Wizard Landing Page - View
    And I should see the Back button on the New List view
    And I should see the List Title on the New List view
    And I should see the following sections on the New List view
      | LOYALLIST     |
      | TRANSACTIONS |
      | CUSTOMER     |
    And I should see the Preview Pane on the New List view


  @bcom @domain_stores @omniclient @website @story_SDE-457
  Scenario: List Wizard Landing Page - Back button
    When I click the Back button on the New List view
    Then disregard changes message is displayed
    When I select the Cancel button on the Disregard Changes error message
    Then I should see the New List view
    And I click the Back button on the New List view
    Then disregard changes message is displayed
    When I select the OK button on the Disregard Changes error message
    Then I should see the Create List dashboard


  @bcom @domain_stores @omniclient @website @story_SDE-457
  Scenario: List Wizard Landing Page - Expanding/Collapsing filter sections
    When I click the chevron of the first section on the New List view
    Then I should see the first section collapsed on the New List view
    When I click the chevron of the first section on the New List view
    Then I should see the first section expanded on the New List view

  @bcom @domain_stores @omniclient @website @story_SDE-457
  Scenario: List Wizard Landing Page - Selecting a filter criteria
    When I select a filter criteria on the New List view
    Then I should see the filter criteria selected on the New List view
    When I remove the filter criteria on the New List view
    Then I should no longer see the filter criteria selected on the New List view







