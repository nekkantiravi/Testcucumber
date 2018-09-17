#Author: UFT team
#Date Created: 03/27/2017
#Date Signed Off:
#Version One Card: B-74793

Feature: Remove HC servers from portal

  @sst
  Scenario: Verify Holiday Capacity servers are removed from RTP environment drop down
    Given I login into mass portal as a valid user
    Then I should not see following HC Servers in environment drop down:
      | ORDER\|RTP\|order_HC_CellA  |
      | ORDER\|RTP\|order_HC_CellB  |
      | SDP\|RTP\|customer_HC_CellA |
      | SDP\|RTP\|customer_HC_CellB |