# Author: STAT Team
# Story: SDC-162 - GVP - Participating vendors only
# Date Created: 11/24/2017
# Date Signed Off:

@STAT @vendor_bonus @SPR-162 @manual
Feature: As a Gift Registry Advisor I want only participating vendors that the registry has a preference for to be
  considered when I create a Vendor Bonus request so I can quickly and efficiently create a request


  Scenario: Only designated participating vendors that the registry has preferences set for are included
    Given I am in Postman
    When I POST a request with BonusEligibleOption = "Y"
    And RequestId = 2
    Then I should see vendor bonus eligible information displayed as response

  Scenario: Error message is displyed when RequestId is not 2
    Given I am in Postman
    When I POST a request with BonusEligibleOption = "Y"
    And RequestId is not 2
    Then I should error message displayed as response

  Scenario: Verify that POST with BonusEligibleOption = "N" shows all vendor information
    Given I am in Postman
    When I POST a request with BonusEligibleOption = "N"
    And RequestId = 2
    Then I should see all vendor information displayed as response

