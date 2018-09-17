# Author: Stores Domain Foundations Team
# Story: SDF-143 - Foundations :: Pattern Library - Carousels
# Date Created: 11/07/2016
# Date Signed Off: 11/15/2016
Feature: As a user, I want the ability to use carousels within my application.

#Background:
#	Given I launch the macy's store domain CSG

  @domain_stores @project_foundations @release_17 @story_SDF-143 @wip
  Scenario: Verify the carousel is displayed in the CSG
    Given I launch the macy's store domain CSG
    Then I should be able to view the carousel
 
 
  @domain_stores @project_foundations @release_17 @story_SDF-143 @wip
  Scenario: Verify when the right arrow is clicked the carousel scrolls to the right
    Given I launch the macy's store domain CSG
    Then I should be able to view the carousel
    When I click on the "right" arrow 
    Then I should see the carousel scrolls to the "right"
    

  @domain_stores @project_foundations @release_17 @story_SDF-143 @wip
  Scenario: Verify when the left arrow is clicked the carousel scrolls to the left
    Given I launch the macy's store domain CSG
    Then I should be able to view the carousel
    When I click on the "left" arrow
    Then I should see the carousel scrolls to the "left"
    
    
  @domain_stores @project_foundations @release_17 @story_SDF-143 @wip
  Scenario: Verify when the a dot under the carousel is clicked the appropriate image is displayed
    Given I launch the macy's store domain CSG
    Then I should be able to view the carousel
    And I should be able to view dot under the carousel
    When I click a dot under the carousel
    Then I should see the image has changed
