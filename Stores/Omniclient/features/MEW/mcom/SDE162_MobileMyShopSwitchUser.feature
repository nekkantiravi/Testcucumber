#Author: Traci Morris
#Story: SDE-162 - MOBILE - My Shop Switch User
#Date Created: 05/02/2017
#Date Signed Off:

Feature: As a Selling Manager in the My Shop Configuration, I want to be able to switch user into any associate
  or Selling Manager in the store, so that i can help coach and evaluate Clienteling and drive business through
  Clienteling outreach.

  @domain_stores @omniclient @Story_SDE-162 @MEW
  Scenario Outline: Selling Manager will be able to switch user into any associate level user or Selling Manager
  in the store, regardless of the area they work in
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    And I should see the Switch user input box
    When I select the switch user input box
    And I enter "<partial>" credentials
    Then I should see a list with "<full>" credentials in the dropdown
    When I select the "<full>" credentials from the dropdown
    Then I should see switch user overlay
    And click the Switch button
    Then I should be switched into the selected "<role>"

    Examples:
      | partial      | full            | role         |
      | FIFTY O      | FIFTY ONE      | associate    |
      | FIFTY S      | FIFTY SEVEN     |sales manager |

  @domain_stores @omniclient @Story_SDE-162 @MEW
  Scenario: Selling Manager will be able to switch user into associate from selling area
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    And I should see the Switch user input box
    When I expand a selling area from My Selling Associates section
    And I click on an associate
    Then I should see switch user overlay
    When click the Switch button
    Then I should be switched into the selected "associate"


 @domain_stores @omniclient @Story_SDE-162 @MEW
  Scenario: Selling Manager will be able to Cancel switching into associate
  Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    And I should see the Switch user input box
    When I expand a selling area from My Selling Associates section
    And I click on an associate
    Then I should see switch user overlay
    When I click on the Cancel button on the Switch User overlay
    Then I should see Selling Manager HomePage
