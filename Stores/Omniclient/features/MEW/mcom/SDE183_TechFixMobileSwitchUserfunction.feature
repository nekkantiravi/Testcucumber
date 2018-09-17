#Author: Ovidiu Rucoi
#Story: SDE-183 - TECH: Fix Mobile Switch User function
#Date Created:
#Date Signed Off:

Feature: As a developer, I want to fix how the switch user function works on mobile so that a user can switch back
  to the logged on user correctly.

  @domain_stores @omniclient @Story_SDE-183 @MEW
  Scenario: Switch into user and back - Switch User search
  in the store, regardless of the area they work in
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    And I should see the Switch user input box
    When I select the switch user input box
    And I enter "FIFTY O" credentials
    Then I should see a list with "FIFTY ONE" credentials in the dropdown
    When I select the "FIFTY ONE" credentials from the dropdown
    Then I should see switch user overlay
    And click the Switch button
    Then I should be switched into the selected "associate"
    When I select the hamburger icon
    And I click the switch back button on mobile
    Then I should see Selling Manager HomePage
    When I select the hamburger icon
    And I navigate to My Clients on mobile
    Then I should see the My Clients page on mobile
    And I click the Home button on mobile
    Then I should see Selling Manager HomePage


  @domain_stores @omniclient @Story_SDE-183 @MEW
  Scenario: Switch into user and back - Selling Area
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    Then I should see Selling Manager HomePage
    And I should see the Switch user input box
    When I expand a selling area from My Selling Associates section
    And I click on an associate
    Then I should see switch user overlay
    When click the Switch button
    Then I should be switched into the selected "associate"
    When I select the hamburger icon
    And I click the switch back button on mobile
    Then I should see Selling Manager HomePage