# Author: Ovidiu Rucoi
# Story: SDE-4 - OmniClient :: Switch User Capabilities
# Date Created: 04/18/2017
# Date Signed Off:

Feature: As a District Manager, I want the ability to 'Switch User' into any associate in any store in my district

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as District Manager

  @domain_stores @omniclient @release_17 @story_SDE-4 @website @mcom
  Scenario Outline: District Manager switches into an Associate or Sales Manager from the 'Switch User' module
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I select search by "<searchOption>"
    And I enter "<partial>" credentials
    Then I should see a list with "<full>" credentials in the dropdown
    When I select the "<full>" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "<role>"

    Examples:
      | searchOption | partial   | full        | role          |
      | name         | FIFTY     | FIFTY ONE   | associate     |
      | id           | 1000005   | 10000057    | sales manager |


  @domain_stores @omniclient @release_17 @story_SDE-4 @website @mcom
  Scenario: District Manager switches into a General Manager from the drill down (click-through) method
    And I expand a Store
    Then I should see the assigned General Manager
    When I click on the General Manager
    Then I should be switched into the selected "general manager"