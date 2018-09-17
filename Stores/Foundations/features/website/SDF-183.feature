# Author: Stores Domain Foundations Team
# Story: SDF-183 - Foundations :: Input field validation - alphanumeric
# Date Created: 10/13/2016
# Date Signed Off: 10/14/2016
Feature: As a developer I want the ability to validate the text in an input field to be alpha numeric.

  @domain_stores @project_foundations @release_17 @story_SDF-183 @wip
  Scenario: Input Validation for alphanumeric
    Given I launch the macy's store domain CSG
    Then I should be able to view the validation module text box
    When I enter "test1234" in the validation module text box
    Then I should see no error message
    And I should see "test1234" added to the text box

  @domain_stores @project_foundations @release_17 @story_SDF-183 @wip
  Scenario: Input Valadation for special characters
    Given I launch the macy's store domain CSG
    Then I should be able to view the validation module text box
    When I enter "%*#" in the validation module text box
    Then The characters are not added to the text box

  @domain_stores @project_foundations @release_17 @story_SDF-183 @wip
  Scenario: Input Validation for max length
    Given I launch the macy's store domain CSG
    Then I should be able to view the validation module text box
    When I enter "1234567890123" in the validation module text box
    Then I should see "123456789012" added to the text box

  @domain_stores @project_foundations @release_17 @story_SDF-183 @wip
  Scenario: Input Validation for min length
    Given I launch the macy's store domain CSG
    Then I should be able to view the validation module text box
    When I enter "123456" in the validation module text box
    Then I should see error message
