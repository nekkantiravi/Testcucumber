# Author: Ovidiu Rucoi
# Story: SDE-80 - OmniClient :: My Shop Switch User
# Date Created: 05/02/2017
# Date Signed Off:

Feature: As a Selling manager in the My Shop Configuration, I want to be able to switch user into any associate or Selling Manager in the store

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager


  @domain_stores @omniclient @mcom @website @story_SDE-80
  Scenario Outline: Selling Manager switches into an Associate or Sales Manager from the 'Switch User' module
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I select search by "<searchOption>"
    And I enter "<partial>" credentials
    Then I should see a list with "<full>" credentials in the dropdown
    When I select the "<full>" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "<role>"

    Examples:
      | searchOption | partial  | full      | role          |
      | name         | FIFTY ON | FIFTY ONE | associate     |
      | id           | 7131704  | 71317043  | sales manager |


  @domain_stores @omniclient @mcom @website @story_SDE-80
  Scenario: Selling Manager switches into an Associate from the drill down (click-through) method
    And I click the plus sign next to a Selling Manager name
    And I click on the staffing zone
    Then I should see the assigned Selling Associate
    When I click on the Selling Associate
    Then I should be switched into the selected "associate"

  @domain_stores @omniclient @mcom @website @story_SDE-80
  Scenario: Selling Manager switches into a Selling Manager from the drill down (click-through) method
    Then I should see a list of Selling managers in my store
    When I click on the Selling Manager
    Then I should be switched into the selected "sales manager"

  @domain_stores @omniclient @mcom @website @story_SDE-80
  Scenario: Selling Manager searches for an Associate/Selling Manager which is not in their store
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I select search by "name"
    And I enter "FIFTY EIGHT" credentials
    Then I should see the 'No Results Found' message