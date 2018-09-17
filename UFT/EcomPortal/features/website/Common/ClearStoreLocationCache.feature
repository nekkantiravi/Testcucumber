#Author: UFT team
#Date Created: 12/01/2015
#Date Signed Off:
#Version One Card: D-21698

Feature: NFR - Platform: Enhance portal to clear a Store from the location cache.

  @sst
  Scenario: Verify clear nearest store cache functionality is working in Mass
    Given I login into mass portal as a valid user
    And I select "fccCellA" from SDP URL
    When I navigate to the "Geo Store Cache" page under Troubleshooting section
    And I search for "94105" zip code in geo store cache search page
    And I clear nearest stores data in cache
    Then I should see following output statement displayed
      | The cached distances have been cached |