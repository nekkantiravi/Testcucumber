#Author: UFT team
#Date Created: 04/06/2017
#Date Signed Off:
#Version One: B-37931

Feature: As a producer, I would like to ensure that the Coremetrics tag bar should be installed.

  #Coremetrics Scenario
  @artifact_navapp @mode_iship @release_17G @priority_medium @domain_purchaseanddelivery @project_UFT @project_UFT_COREMETRICS
  Scenario: Verify the Page View tag should displayed for empty shopping bag in IShip mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to empty shopping bag page