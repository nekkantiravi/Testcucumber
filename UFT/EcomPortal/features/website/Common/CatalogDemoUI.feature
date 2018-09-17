#Author: UFT team
#Date Created: 11/03/2016
#Date Signed Off:
#Version One Card: B-61148
#Automation Story: B-63505

Feature: Catalog Demo UI

  @sst
  Scenario: Verify Catalog demo UI Page
    Given I login into mass portal as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Catalog Demo UI" page under Discovery Preview Services section
    And I select random FOB on Catalog Demo UI Page
    And I select random left nav link on on Catalog Demo UI Page
    Then I should see product thumbnails are displaying on Catalog Demo UI Page
