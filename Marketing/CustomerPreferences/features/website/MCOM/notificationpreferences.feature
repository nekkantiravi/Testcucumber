# Author: Customer Preferences Project QE Team
# Date Created: 5/19/2017
# Version One:  MCOM Story B-74847

Feature: Verification of notification preference page

  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario: Verify the email notification preferences Edit state
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see the "Email" text
    And I should see the "Enabled" text with user email address
    When I click on 'EDIT' link next to email icon
    Then I should see edit mode for email preference
    And I should see the email address
    And I should see the placeholder static text "Would you like to receive emails from Macy's?" display
    And I should see the three radial buttons
      | Yes, I don't want to miss a thing!    |
      | Sure, but I only want the highlights. |
      | No, thanks! I'm good for now.         |
    And I should see the 'Save' and 'Cancel' button


  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify email preference in notification preference sub-page
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    And I click on 'EDIT' link next to email icon
    And I select the 'Sure, but I only want the highlights' radio option
    And I click on "Save" button
    Then I should see the "Email" text
    And I should see the "Enabled" text with user email address
    When I click on 'EDIT' link next to email icon
    And I select the 'Yes, I don't want to miss a thing!' radio option
    And I click on "Save" button
    Then I should see the "Email" text
    And I should see the "Enabled" text with user email address
    When I click on 'EDIT' link next to email icon
    And I select the 'No, thanks! I'm good for now' radio option
    And I click on "Save" button
    Then I should see the "Email" text
    And I should see the 'Disabled' text without user email address


  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario:  Verify the text notification preference in saved and edit states
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    When I click on edit link next to text preference
    Then I should see the text notification edit state
    And I should see a 'Yes, text me' radio button with 'Phone number' text box
    And I should see 'Maybe later I'd prefer not to receive texts at this time' radio button
    And I should see the 'Save' and 'Cancel' button
