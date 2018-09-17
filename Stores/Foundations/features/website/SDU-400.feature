# Author: Stores Domain Foundations Team
# Story: SDU-400 - Foundations :: CSG change to field labels
# Date Created: 05/15/2017
# Date Signed Off:
Feature: As a User, We would like to use floating labels on form fields instead of the traditional Field label above the field.

  @domain_stores @project_foundations @release_17 @story_SDF-135 @wip
  Scenario: Floating Label Validation
    Given I launch the macy's store domain CSG
    Then I should be able to view the floating label text box
    When I enter "Mike" into the text box
    Then I should see the div class change