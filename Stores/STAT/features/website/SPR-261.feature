# Author: Stat Team
# Story: SPR-261  - PR - Retrieve by PT ID Non-Stores - Modify PR Service
# Date Created: 11/15/2017
# Date Signed Off:

@STAT @Manual @pending_returns @SPR-261
Feature: As an application I want to retrieve a pending request with PT ID non-stores
@Macy's
   Scenario: QE to verify I can retrieve pending request with X-Macys-ClientId = 03
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see Pending return information in the response

  Scenario: QE to verify I can retrieve pending request with UPC and X-Macys-ClientId = 03
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see Pending return information in the response

  Scenario: QE to verify I cannot retrieve pending request with a X-Macys-ClientId that is
    something other than 03
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 01
    And  PTID in the URL
    Then I can see a validation error returned for ClientID

  Scenario: QE to verify I cannot retrieve pending request with UPC and X-Macys-ClientId that is
  something other than 03
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 01
    And  PTID in the URL
    And  UPC in the URL
    Then I can see a validation error returned for ClientID

  Scenario: QE to verify I cannot retrieve pending request with a X-Macys-ClientId that is
  something other than 03
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 02
    And  PTID in the URL
    Then I can see a validation error returned for ClientID

  Scenario: QE to verify I cannot retrieve pending request with UPC and X-Macys-ClientId that is
  something other than 03
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 02
    And  PTID in the URL
    Then I can see a validation error returned for ClientID

  Scenario: QE to verify I cannot retrieve pending request with invalid pending id
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL that is invalid
    Then I can see a validation error returned for PT ID

  Scenario: QE to verify I cannot retrieve pending request with invalid pending id
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL that is not 23 characters
    Then I can see a validation error returned for PT ID

  Scenario: QE to verify I cannot retrieve pending request with a valid UPC and invalid pending id
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL that is invalid
    And  UPC in the URL
    Then I can see a validation error returned for PT ID

  Scenario: QE to verify I cannot retrieve pending request with a valid UPC and invalid pending id
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL that is not 23 characters
    And UPC in the URL
    Then I can see a validation error returned for PT ID

  Scenario: QE to verify I cannot retrieve pending request with a invalid UPC and valid pending id
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And UPC in the URL is greater than 18 characters
    Then I can see a validation error returned for UPC

  Scenario: QE to verify I cannot retrieve pending request with valid pending id that is
      not present in DCR
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see a validation error returned for PT ID not found

  Scenario: QE to verify I cannot retrieve pending request with valid UPC and pending id that is
  not present in DCR
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see a validation error returned for PT ID and UPC not found

  Scenario: QE to verify I cannot retrieve pending request with valid pending id and UPC but UPC is
  not present in pending return
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see a validation error returned for UPC not found

  Scenario: QE to verify I can retrieve pending request with customer info in both pickup and
    pending returns
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see customer info returned in both pickup and pending returns in the response

  Scenario: QE to verify I can retrieve pending request with UPC with customer info in
    both pickup and pending returns
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see customer info returned in both pickup and pending returns in the response

  Scenario: QE to verify I can retrieve pending request with address info in both pickup and
  pending returns
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see address info returned in both pickup and pending returns in the response

  Scenario: QE to verify I can retrieve pending request with UPC with address info in
  both pickup and pending returns
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see address info returned in both pickup and pending returns in the response

  Scenario: QE to verify I can retrieve pending request without pickup info
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see Pending return information in the response

  Scenario: QE to verify I can retrieve pending request with UPC without pickup info
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see Pending return information in the response

  Scenario: QE to verify I can retrieve pending request with pickup info
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the appropriate pickup info in the Pending return response

  Scenario: QE to verify I can retrieve pending request with UPC with pickup info
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the appropriate pickup info in the Pending return response

  Scenario: QE to verify I can retrieve pending request with more than 1 item
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the appropriate lineitemlist in the Pending return response

  Scenario: QE to verify I can retrieve pending request with UPC with more than 1 item
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the appropriate lineitemlist in the Pending return response

  Scenario: QE to verify I can retrieve pending request with Pending_trans_id
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the Pending_trans_id matches the Pending_trans_id in the response

  Scenario: QE to verify I can retrieve pending request with UPC and Pending_trans_id
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the Pending_trans_id matches the Pending_trans_id in the response

  Scenario: QE to verify I can retrieve pending request with Pending_trans
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the Pending_trans matches the Pending_trans in the response

  Scenario: QE to verify I can retrieve pending request with UPC and Pending_trans
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the Pending_trans matches the Pending_trans in the response

  Scenario: QE to verify I can retrieve pending request with carrier_info
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the carrier_info matches the carrier_info in the response

  Scenario: QE to verify I can retrieve pending request with UPC and carrier_info
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the carrier_info matches the carrier_info in the response

  Scenario: QE to verify I can retrieve pending request with decimal fields
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the decimal field format matches the decimal field format in the response

  Scenario: QE to verify I can retrieve pending request with UPC and decimal field
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the decimal field format matches the decimal field format in the response

  Scenario: QE to verify I can retrieve pending request with timestamp fields
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the timestamp field matches the timestamp field in the response

  Scenario: QE to verify I can retrieve pending request with UPC and timestamp field
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the timestamp field matches the timestamp field in the response

  Scenario: QE to verify I can retrieve pending request with schedDeliveryDate
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see the schedDeliveryDate matches the schedDeliveryDate in the response

  Scenario: QE to verify I can retrieve pending request with UPC and schedDeliveryDate
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see the schedDeliveryDate matches the schedDeliveryDate in the response

  Scenario: QE to verify I can retrieve pending request with return qty greater than 0
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    Then I can see return qty on line item in the response

  Scenario: QE to verify I can retrieve pending request with UPC and return qty greater than 0
    Given I am in Postman
    When I GET a PendingReturn with X-Macys-ClientId = 03
    And  PTID in the URL
    And  UPC in the URL
    Then I can see return qty on line item in the response



