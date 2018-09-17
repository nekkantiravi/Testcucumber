#Author: UFT team
#Date Created: 10/10/2017
#Date Signed Off:
#Version One: B-88173

Feature: Validate if Registrant's Address Line2 field in edit registry page is populated.

  @mode_registry @release_17P @priority_medium @domain_selection @project_UFT
  Scenario: Verify if Registrant Address Line2 field in edit registry page is populated.
    Given I visit the website as a bvr user in registry mode
    And I navigate to registry manager page
    When I click on edit profile link on registry manager page
    Then I should see Address line2 field value populated in Edit profile page




