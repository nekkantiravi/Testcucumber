#Author: UFT Team
#Date Created: 02/02/2016
#Date Signed Off:
#Version One: B-32366

Feature: As a member of the support team, I would like to make sure that the Promotion Id can be captured from SUPC.

  @sst
  Scenario: Verify Promo Code Id is displayed for the given valid SUPC on site admin portal
    Given I login into site admin portal as a valid user
    When I navigate to "SUPC Promotion" page under data validator framework section
    And I submit the form with "valid" SUPC on SUPC Promotion Page
    Then I should see Promo Code Data is displayed for valid SUPC on SUPC Promotion Page

  @sst
  Scenario: Verify error message is displayed for invalid SUPC on site admin portal
    Given I login into site admin portal as a valid user
    When I navigate to "SUPC Promotion" page under data validator framework section
    And I submit the form with "invalid" SUPC on SUPC Promotion Page
    Then I should see error message for invalid SUPC on SUPC Promotion Page