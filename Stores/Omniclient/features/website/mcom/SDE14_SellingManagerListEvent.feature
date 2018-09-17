# Author: Claudiu Chirila
# Story: SDE-14 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

@manual @SKIPPED
Feature: As a Selling Manager, I want to see all Lists and Events created for the Selling Associates who I oversee so that I can monitor status and avoid creating duplicate Lists.

  @domain_stores @omniclient @story_SDE-14 @website @mcom
  Scenario: Selling Manager is able to view Lists created by another Selling Manager
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    And I create a TO DO from CREATE TO DOS page
      | TARGET SM SPECIFIC |
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I enter "LONDY SANCH" credentials
    And I select the "LONDY SANCHEZ" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "sales manager"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the list information
      | Created by       |
      | List Title       |
      | Uncalled clients |
      | Due by           |
    And I should see the new TO DO "TARGET SPECIFIC" created by the selling manager
    When I click to expand the chevron
    Then List description will be displayed


  @domain_stores @omniclient @story_SDE-14 @website @mcom
  Scenario: Selling Manager is able to view Lists created by a General Manager
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as general manager
    And I create a TO DO from CREATE TO DOS page
      | TARGET GM |
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I enter "FIFTY SEV" credentials
    And I select the "FIFTY SEVEN" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "sales manager"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the list information
      | Created by       |
      | List Title       |
      | Uncalled clients |
      | Due by           |
    And I should see the new TO DO "TARGET TODO2 GM" created by the General Manager
    When I click to expand the chevron
    Then List description will be displayed

    ## TODO: INVESTIGATE THE SWITCH USER ISSUES ON CORP STORE EXEC

  @domain_stores @omniclient @story_SDE-14 @website @mcom
  Scenario: Selling Manager is able to view Lists created by a Corporate Store Executive
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate store executive
    And I create a TO DO from CREATE TO DOS page
      | TARGET CORP STORE EXEC |
    And I click on the Switch User button
    Then I should see the Switch User search popup
    When I enter "SUSANA ESP" credentials
    And I select the "SUSANA ESPINOSA" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "sales manager"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the list information
      | Created by       |
      | List Title       |
      | Uncalled clients |
      | Due by           |
    And I should see the new TO DO "TARGET TODO1" created by the Corporate Store Executive
    When I click to expand the chevron
    Then List description will be displayed


  @manual @domain_stores @omniclient @story_SDE-14 @website @mcom
  Scenario: Selling Manager is able to view Events created by a Corporate Admin
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate admin
    And I create an event from CREATE EVENT page
    And I click on the Switch User button
    And I enter "SUSANA ESP" credentials
    And I select the "SUSANA ESPINOSA" credentials from the dropdown
    And click the Switch button
    Then I should be switched into the selected "sales manager"
    When I navigate to ALL TO DOS page
    And I click on MY MACYS TO DOS tab
    Then I should see the list of TO DOS
    And I should see the list information
  #this method will include validations for: Created By' Associate, Title, Number of Uncalled Customers, and 'Due By' date
    And I should see the new EVENT created by the Corporate Admin
    When I click to expand the chevron
    Then Event description will be displayed
