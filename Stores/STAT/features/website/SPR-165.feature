# Author: Stat Team
# Story: SPR-165  - GVB - GiftProductServices - viewBonusRequest
# Date Created: 11/17/2017
# Date Signed Off:

@STAT @Manual @VendorBonus @SPR-165
Feature: As an application I want to modify the GiftProductServices so I can support the need to
  view current vendor bonus requests on the Vendor Bonus Program page. Data to return includes submit date,
  request number, registry number,registrant name, co-registrant name, occasion date, vendor name, status


  Scenario: QE to verify applicationId service returns correct information
    Given I am in Postman
    When I input a applicationId
    Then I should see the list of bonusRequestData (there are currently two in the database)


  Scenario: QE to verify responselimit 1
    Given I am in Postman
    When I input a responseLimit 1
    Then I should the bonusReqestData
    And I should see return status as submit date
    And I should see request number
    And I should see registry number
    And I should see registrants first and last name
    And I should see co-registrants name
    And I should see occasion date
    And I should see vendor name
    And I should see associate first and last name
    And I should see associate number
    And I should see store number
    And I should see status

  Scenario: QE to verify responselimit 0
    Given I am in Postman
    When I input a responseLimit 0
    Then I should the bonusReqestData
    And I should see return status as submit date
    And I should see request number
    And I should see registry number
    And I should see registrants first and last name
    And I should see co-registrants name
    And I should see occasion date
    And I should see vendor name
    And I should see associate first and last name
    And I should see associate number
    And I should see store number
    And I should see status

  Scenario: QE to verify missing responselimit
    Given I am in Postman
    When I input nothing for the responseLimit
    Then I should the bonusReqestData
    And I should see return status as submit date
    And I should see request number
    And I should see registry number
    And I should see registrants first and last name
    And I should see co-registrants name
    And I should see occasion date
    And I should see vendor name
    And I should see associate first and last name
    And I should see associate number
    And I should see store number
    And I should see status


