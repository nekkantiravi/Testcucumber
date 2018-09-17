#Author: UFT team
#Date Created: 11/02/2017
#Date Signed Off:
#Version One Card: B-95779

Feature: As a product owner, I would like to change the word “access” to the word “link” on registry claim page

  @release_17V @artifact_shopapp @medium @domain_selection @project_UFT
  Scenario: Verify word access updated to link on registry claim page
    Given I visit the website as a guest user
    When I navigate to registry claim Page
    Then I should see 'link your in-store registry' header on registry claim page
    And I should see 'Enter your email address & registry ID to link and start managing your registry online' text below to header
