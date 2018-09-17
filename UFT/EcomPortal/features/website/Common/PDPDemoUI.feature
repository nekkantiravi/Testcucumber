#Author: UFT team
#Date Created: 04/14/2015
#Date Signed Off:
#Version One Card: B-15025

Feature: As a member of the support team, I would like to develop Preview Tool for FCC for PDP
  page

  @sst
  Scenario: Verify PDP demo UI Page
    Given I login into mass portal as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "PDP Demo UI" page under Omni Channel Preview Services section
    And I search with valid product id
    Then I should see Product Information on PDP Demo UI Page
    And I verify Product Details tab is displayed with all the necessary data
    And I verify Review's tab is displayed with all the necessary data
    And I verify stores related information is displayed with the option to select stores
    And I verify "Product Q&A" data is displayed

  @sst
  Scenario: Verify Star Ratings are visible on PDP demo UI Page
    Given I login into mass portal as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "PDP Demo UI" page under Omni Channel Preview Services section
    And I search with valid product with the following options:
      | registrable    | true  |
      | iship_eligible | true  |
      | orderable      | true  |
      | master_product | false |
    Then I should see the star ratings is displayed in the demo page
