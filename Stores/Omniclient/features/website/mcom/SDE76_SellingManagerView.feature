# Author: Claudiu Chirila & Ovidiu Rucoi
# Story: SDE-76 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

Feature: As a Macy's Selling Manager, I want to see my dashboard list of areas in the store, referenced by the name of the Selling Manager of that area

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager

  @domain_stores @omniclient @story_SDE-76 @website @mcom
  Scenario: My Store and My Customers tabs are displayed
    Then My Store tab is displayed in dashboard section
    And My Customers tab is displayed in dashboard section
    And the logged in sales manager name is displayed on top of dashboard section

  @domain_stores @omniclient @story_SDE-76 @website @mcom
  Scenario: List of Selling Managers including their own name are displayed
    Then I should see a list of Selling managers in my store
    And I should see my name at the top
    And the list of my selling areas is expanded under my name

  @domain_stores @omniclient @story_SDE-76 @website @mcom
  Scenario: If I click the + button the staffing zone list is expanded
    When I click the plus sign next to a Selling Manager name
    Then I should see a list of staffing zones of the Selling Manager

  @domain_stores @omniclient @story_SDE-76 @website @mcom
  Scenario: Name of the SM logging in will show in upper right hand corner as the user
    And I click the plus sign next to a Selling Manager name
    And I click on the staffing zone
    Then I should see the assigned Selling Associate
    When I click on the Selling Associate
    Then I should be switched into the selected "associate"