# Author: Ovidiu Rucoi
# Story: SDE-168 - OmniClient :: Enhanced Switch User Capabilities
# Date Created: 05/02/2017
# Date Signed Off:

Feature: As a District Manager, I want the ability to 'Switch User' into any associate in any store within my district (regardless of who the General Manager reports to)

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as District Manager

  @domain_stores @omniclient @mcom @website @story_SDE-168
  Scenario Outline: District Manager switches into an Associate, Sales Manager and General Manager from the 'Switch User' module
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I select search by "<searchOption>"
    And I enter "<partial>" credentials
    Then I should see a list with "<full>" credentials in the dropdown
    When I select the "<full>" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "<role>"

    Examples:
      | searchOption | partial   | full                      | role                     |
      | name         | GENESIS   | GENESIS ATANACIO FIGUEROA | associate                |
      | id           | 1067861   | 10678615                  | sales manager myshop off |
      | name         | AMBAR     | AMBAR GAY                 | general manager          |


  @domain_stores @omniclient @mcom @website @story_SDE-168
  Scenario: District Manager searches for an Associate which is not in their district
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I select search by "name"
    And I enter "SIX ZERO" credentials
    Then I should see the 'No Results Found' message