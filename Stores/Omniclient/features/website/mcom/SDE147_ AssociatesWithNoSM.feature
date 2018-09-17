# Author: Claudiu Chirila
# Story: SDE-147 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

Feature: As a Selling Manager I need to have visibility of the associates in my store who have no selling manager at the current time, in order to coach and evaluate their Clienteling performance, to drive business through Clienteling Outreach.


  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager

  @domain_stores @omniclient @story_SDE-147 @website @mcom
  Scenario: "Unassigned" Area is displayed at the bottom of Selling Manager List
    Then I should see a list of Selling managers in my store
    And the Unassigned Area is displayed at the bottom of SM list
    When I click on the Unassigned Area bar
    Then a list of Unassigned Selling Areas are displayed
    And I close the Unassigned Area bar
    Then the Unassigned Area is displayed at the bottom of SM list
    When I click on the Unassigned Area bar
    And I expand random Selling Manager name
    Then the Unassigned Area is displayed at the bottom of SM list

  @domain_stores @omniclient @story_SDE-147 @website @mcom
  Scenario: Unassigned associates are displayed under the "Unassigned" Area bar
    Then I should see a list of Selling managers in my store
    And the Unassigned Area is displayed at the bottom of SM list
    When I click on the Unassigned Area bar
    Then a list of Unassigned Selling Areas are displayed
    When I click on a Unassigned Selling Area
    Then the Selling Associates List is expanded

  @domain_stores @omniclient @story_SDE-147 @website @mcom
  Scenario: After selecting an associate from the list the SM is switched into that specific SA
    Then I should see a list of Selling managers in my store
    And the Unassigned Area is displayed at the bottom of SM list
    When I click on the Unassigned Area bar
    And I click on a Unassigned Selling Area
    And I click on a selling associate
    Then I should be switched into the selected "associate"