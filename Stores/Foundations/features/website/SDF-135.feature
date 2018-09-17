# Author: Stores Domain Foundations Team
# Story: SDF-135 - Foundations :: Input field validation - alphanumeric
# Date Created: 10/21/2016
# Date Signed Off: 10/25/16
Feature: As a User, I want the button loader animations section of the Pattern Library implemented via spin.js on a button component.

  @domain_stores @project_foundations @release_17 @story_SDF-135 @wip
  Scenario: Input Validation for alphanumeric
    Given I launch the macy's store domain CSG
    Then I should be able to view the Tap Me animation button
    When I click the Tap Me animation button
    Then I should see the spinner animation
    #When I click on stop button
    #Then I should see the animation stop
