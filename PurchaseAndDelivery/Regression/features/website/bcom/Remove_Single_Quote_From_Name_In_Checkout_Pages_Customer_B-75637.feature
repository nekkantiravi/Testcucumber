#Author: UFT team
#Date Created: 04/07/2017
#Date Signed Off:
#Version One: B-75637

Feature: As a producer, I would like to ensure that the user can see single quote displayed
  in first name and last name fields in the check out pages for guest users.

  @artifact_shopapp @domain_purchaseanddelivery @release_17G @mode_domestic @project_UFT
  Scenario: Verify that first name or last name in payment section does not contain single quote when
      single quote is added to names in checkout page billing section for guest user
    Given I visit the web site as a guest user
    When I add an "orderable and available and loyalty_pppc and iship_eligible" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    And I add a credit card during checkout
    And I uncheck use my shipping address checkbox on payment page
    And I enter billing details on payment page
    And I update the first and last name with single quotes in payment section
    Then I should not see single quotes in billing address first name and last name