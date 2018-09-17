# Author: Customer Preferences Project QE Team
# Date Created: 9/12/2017
# Version One:  BCOM Story B-69744

Feature: Customer Preferences BAT

  @domain_Marketing @project_Preferences @bcom @use_regression @Marketing_CBT
  Scenario: Validate the display on preferences landing page
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    Then I should see "My Preferences" page title
    And I should see "Communication Preferences" card
    And I should see the "Preferred Store" card

  @domain_Marketing @project_Preferences @bcom
  Scenario: Validate the display on Communication Preferences card
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    Then I should see "My Preferences" page title
    And I should see "Communication Preferences" card
    And I should see "Email" label on the Communication Preferences card
    And I should see the "Text" label on the Communication Preferences card
    And I should see "Enabled" status for the Email Preference
    And I should see the "Enabled" status for the Text Preference

  @domain_Marketing @project_Preferences @bcom @Marketing_CBT
  Scenario: Validate the display on Preferred Store card
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    Then I should see "My Preferences" page title
    And I should see the "Preferred Store" card
    And I should see the 'Add a Store' option

  @domain_Marketing @project_Preferences @bcom @use_regression
  Scenario: Validate that the User navigation using My Preferences left navigation
    Given I visit the web site as a registered user
    When I click on 'My Preferences' option from the left navigation menu
    Then I should see "My Preferences" page title

  @domain_Marketing @project_Preferences  @bcom
  Scenario: Verify the preferences card display on myaccount page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I should see Preferences card
    And I should see "Preferences" as card title
    And I should see "Email" label on the Preferences card
    And I should see "Text" label on Preferences card
    And I should see the Email status as "Enabled" Preferences card
    And I should see the Text status as "Enabled" Preferences card

  @domain_Marketing @project_Preferences  @bcom
  Scenario: Verify the Add a store link feature on preferences card
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I should see Preferences card
    And I should see the "Add a Store" link on preferences card
    When i click on Add a Store link on preferences card
    Then I should be navigated to the Preferred store page
    When I enter the ZIP Code into the text field
    And I click on 'Search' button
    And I select any store from the search results of Preferred store
    Then I should be navigated to the summary state of Preferred store
    When I navigate to my account page
    Then I should see store 'change' option on preferences card

