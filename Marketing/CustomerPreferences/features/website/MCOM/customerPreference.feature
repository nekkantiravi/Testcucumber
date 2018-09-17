# Author: Customer Preferences Project QE Team
# Date Created: 4/10/2017
# Version One:  MCOM Story B-74847

Feature: Customer Preference BAT

  @domain_Marketing @project_Preferences @mcom
  Scenario: Validate the display on preferences landing page
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    Then I should see the below three preferences cards display on landing page
      | .goto_categories      |
      | .goto_notifications   |
      | .goto_preferred_store |

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify the email/text preference display on notification page
    Given I visit the web site as a registered user
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see the below preferences on notification page
      | email_label     |
      | text_label      |


  @domain_Marketing @project_Preferences @mcom @use_regression
  Scenario: Verify the Preferences card in My Account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I should see Preferences card
    When I click on View Preference option
    Then I should navigate to Preferences landing page
