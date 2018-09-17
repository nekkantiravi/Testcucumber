#Author: UFT team
#Date Created: 04/06/2017
#Date Signed Off:
#Version One: B-67015

Feature: As a product owner, I would like to update the Sign Up for Emails asset on the iShip page to reflect the new
  offer (15% to 25%) and align with email.

  @artifact_navapp @mode_iship @release_17G @priority_medium @domain_purchaseanddelivery @project_UFT
  Scenario: Verify the Sign Up for Emails New Banner on International Context Page
    Given I visit the web site as a guest user
    When I navigate to international context page
    Then I should see new banner for Sign Up for Emails on International Context Page
    And I select Exclusions Apply link on International Context Page
    Then I should navigate to exclusions apply page from the link on International Context Page