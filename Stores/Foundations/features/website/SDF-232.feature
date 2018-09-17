# Author: Stores Domain Foundations Team
# Story: SDF-232 - Foundations :: Overlay Updates
# Date Created: 10/27/2016
# Date Signed Off: 10/31/16
Feature: As a User, I want the overlay and modal overlay sections of the CSG to work properly.

  @domain_stores @project_foundations @release_17 @story_SDF-232 @wip
  Scenario: Use X button to close Short Modal Dialog
    Given I launch the macy's store domain CSG
    Then I should be able to view the "short modal dialog" button
    When I click the "short modal dialog" button
    Then I should see the "short modal dialog"
    When I click the "x" in csg screen
    Then the "short modal dialog" is removed
    
  @domain_stores @project_foundations @release_17 @story_SDF-232 @wip    
  Scenario: Use start button to close Short Modal Dialog
    Given I launch the macy's store domain CSG
    Then I should be able to view the "short modal dialog" button
    When I click the "short modal dialog" button
    Then I should see the "short modal dialog"
    When I click the "start button" in csg screen
    Then the "short modal dialog" is removed
 
   @domain_stores @project_foundations @release_17 @story_SDF-232 @wip   
  Scenario: Use X button to close Long Modal Dialog
    Given I launch the macy's store domain CSG
    Then I should be able to view the "long modal dialog" button
    When I click the "long modal dialog" button
    Then I should see the "long modal dialog"
    When I click the "x" in csg screen
    Then the "long modal dialog" is removed
    
   @domain_stores @project_foundations @release_17 @story_SDF-232 @wip   
  Scenario: Use continue button to close Long Modal Dialog
    Given I launch the macy's store domain CSG
    Then I should be able to view the "long modal dialog" button
    When I click the "long modal dialog" button
    Then I should see the "long modal dialog"
    When I click the "continue button" in csg screen
    Then the "long modal dialog" is removed
    
  @domain_stores @project_foundations @release_17 @story_SDF-232 @wip    
  Scenario: Use X button to close Modal Dialog with Input
    Given I launch the macy's store domain CSG
    Then I should be able to view the "modal dialog with input" button
    When I click the "modal dialog with input" button
    Then I should see the "modal dialog with input"
    When I click the "x" in csg screen
    Then the "modal dialog with input" is removed
    
  @domain_stores @project_foundations @release_17 @story_SDF-232 @wip    
  Scenario: Use Cancel button to close Modal Dialog with Input
    Given I launch the macy's store domain CSG
    Then I should be able to view the "modal dialog with input" button
    When I click the "modal dialog with input" button
    Then I should see the "modal dialog with input"
    When I click the "cancel button" in csg screen
    Then the "modal dialog with input" is removed
    
  @domain_stores @project_foundations @release_17 @story_SDF-232 @wip    
  Scenario: Use Continue button to close Modal Dialog with Input
    Given I launch the macy's store domain CSG
    Then I should be able to view the "modal dialog with input" button
    When I click the "modal dialog with input" button
    Then I should see the "modal dialog with input"
    When I click the "continue button" in csg screen
    Then the "modal dialog with input" is removed