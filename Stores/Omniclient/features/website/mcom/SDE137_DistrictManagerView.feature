# Author: Claudiu Chirila
# Story: SDE-137 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

Feature: As a District Manager, I want to view all associates who directly report to me within my district so that I can easily view associate/client status and information

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as District Manager

  @domain_stores @omniclient @story_SDE-137 @website @mcom
  Scenario: My Stores and My Direct Reports tabs are displayed
    Then I should see the name of the district
    And My Stores tab is displayed in dashboard section
    And My Direct Reports is displayed in dashboard section

  @domain_stores @omniclient @story_SDE-137 @website @mcom
  Scenario: List of stores are displayed in My Stores tab & Associates are displayed in My Direct Reports tab
    And I navigate to My Direct Reports tab
    Then I should see a list of associates who report to me
    When I navigate to My Stores tab
    Then I should see the store names displayed in dashboard

  @domain_stores @omniclient @story_SDE-137 @website @mcom
  Scenario: I can 'switch user' into my direct reports by selecting their name
    And I navigate to My Direct Reports tab
    When I click on the GM from My Direct Reports tab
    Then I should be switched into the selected "general manager"