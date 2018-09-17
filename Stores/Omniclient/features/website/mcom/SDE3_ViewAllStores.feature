# Author: Claudiu Chirila
# Story: SDE-3 - OmniClient : Launch Application
# Date Created:
# Date Signed Off:

Feature: As a District Manager, I want to view all stores within the district on my dashboard so that I can freely select any store to view clienteling metrics

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as District Manager

  @domain_stores @omniclient @story_SDE-3 @website @mcom
  Scenario: District Manager sees the district name and list of stores in dashboard section
    Then I should see the name of the district
    And I should see the store names displayed in dashboard
    And I should see the list of stores displayed in numerical order
    And I should see a store number next to each store

  @domain_stores @omniclient @story_SDE-3 @website @mcom
  Scenario: District Manager is not able to select the 'My Clients' or 'Create To Dos' on the top navigation bar
    And I click on My Clients from top navigation bar
    Then feature not available error is displayed
    When I click ok button on the error message
    Then I should see omniclient landing page
    When I click on Create To Dos from navigation bar
    Then feature not available error is displayed
    When I click ok button on the error message
    Then I should see omniclient landing page