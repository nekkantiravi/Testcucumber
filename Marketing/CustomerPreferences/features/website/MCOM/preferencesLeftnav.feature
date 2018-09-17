# Author: Customer Preferences Project QE Team
# Date Created: 11/21/2017
# Version One:  MCOM Story B-97745

Feature: Verification of Leftnav of preferences link

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify the preferences subpage links display on left nav when user is on preferences page
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    Then I should navigate to Preferences landing page
    And I should see the "Preferences" link in left nav
    And I should see the preferences subnav links
    |Shopping    |
    |Notifications|
    |Preferred Store |

  @domain_Marketing @project_Preferences @mcom
  Scenario: Verify the Preferencs Subnav links navigations
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    Then I should navigate to Preferences landing page
    When I select "preferences_leftnav_notification" link on preferences page
    Then I navigate to Notifications preferences sub page
    When I select "preferences_leftnav_preferredstore" link on preferences page
    Then I should get navigated to Preferred store page
    When I select "preferences_leftnav_shopping" link on preferences page
    And I navigate to Categories page from Preferences Landing page

