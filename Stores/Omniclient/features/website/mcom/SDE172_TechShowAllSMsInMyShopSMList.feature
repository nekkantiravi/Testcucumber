# Author: Traci Morris
# Story: SDE-172 - TECH: Show All SMs in My Shop SM list
# Date Created: 05/17/2017
# Date Signed Off:

Feature: As a selling manager (SM) in the my shop configuration, I want the list of all selling managers that
  appears on my dashboard to be all active SMs in my store so that I have visibility into all areas of the store

 @domain_stores @omniclient @story_SDE-172 @website @mcom
  Scenario: All active SMs in the store are displayed in the list of SMs that appear on the dashboard for SMs
  in a My Shop configured store
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    Then I should see a list of Selling managers in my store
    And I should see my name at the top
    And the list of my selling areas is expanded under my name

  @domain_stores @omniclient @story_SDE-172 @website @mcom
  Scenario: If I click the + button the staffing zone list is expanded
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    When I expand random Selling Manager name
    Then I should see a list of staffing zones of the random Selling Manager

