#Author: UFT team
#Date Created: 11/02/2017
#Date Signed Off:
#Version One Card: B-96413 and B-97242

Feature: As a product owner, I would like to change the word “access” to the word “link” on registry claim page

  @release_17V @artifact_shopapp @medium @domain_selection @project_UFT
  Scenario: Verify word access updated to link for instore registry link on Home page
    Given I visit the mobile web site as a guest user
    And I navigate to wedding registry page
    Then I should see 'link your in-store registry' on registry home page

  @release_17V @artifact_shopapp @medium @domain_selection @project_UFT
  Scenario: Verify word access updated to link in Claim registry page
    Given I visit the mobile web site as a guest user
    And I navigate to wedding registry page
    When I click on instore registry from registry home page
    Then I should see "Link " in the header and subtitle for instore registry page
