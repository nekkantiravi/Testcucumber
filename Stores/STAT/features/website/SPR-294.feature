# Author: Stat Team
# Story: SPR-294  - PR - Modify - Create Pending Return Service
# Date Created: 11/29/2017
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-294
Feature: As an application I want to modify the Pending Return service to include the tracking number
  as part of the information that I insert into the PENDING_ITEMS table so I don't have to do a multi-table
  join when retrieving a Pending Return when looked up by tracking number*

  @Macy's
  Scenario: Submit multiple Pending Return create requests (POSTs) and make sure that the tracking nbr
  is correctly captured on the PENDING_ITEMS table
    Given I am in Postman
    When I POST a PendingReturn with a tracking number
    Then I can see a valid pendingTransId generated
    When I go to SQL Developer
    And I perform a Select on the pendingTransID in PENDING_ITEMS table
    Then I can see a tracking nbr for the item in PENDING_ITEMS table

  Scenario: Submit a Pending Return requests (POSTs) with multiple items and make sure that the tracking nbr
  is correctly captured on the PENDING_ITEMS table
    Given I am in Postman
    When I POST a PendingReturn with a tracking number
    And with mulitple items
    Then I can see a valid pendingTransId generated
    When I go to SQL Developer
    And I perform a Select on the pendingTransID in PENDING_ITEMS table
    Then I can see a row for each item in PENDING_ITEMS table
    And a tracking nbr for each item in PENDING_ITEMS table





