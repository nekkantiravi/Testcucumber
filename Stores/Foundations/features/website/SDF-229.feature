# Author: Stores Domain Foundations Team
# Story: SDF-229 - Foundations :: Accordion Enhancements
# Date Created: 10/24/2016
# Date Signed Off: 10/26/16
Feature: As a User, I want the ability to keep multiple accordion options open at the same time.

  @domain_stores @project_foundations @release_17 @story_SDF-229 @wip
  Scenario: Multiple accordions can be kept open at the same time
    Given I launch the macy's store domain CSG
    Then I should be able to view the accordian controls
    And I click the "reviews" accordian menu
    And I click the "availability" accordian menu
    Then I should see all 3 accordian menus open