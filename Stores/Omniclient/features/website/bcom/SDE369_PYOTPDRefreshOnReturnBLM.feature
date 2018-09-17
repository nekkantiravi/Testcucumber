# Author: Traci Morris
# Story: PYOTPD: Refresh b-connected on return from POS PYOTPD Scheduling Tool
# Date Created: 12/13/2017
# Date Signed Off:

Feature: As a BLM b-connected USer I want the application to refresh when I return to b-connected from using the PYOTPD
  Scheduling Tool, so I can see the scheduling modifications that I have made and have the freshest data for the customer.

  @manual @domain_stores @omniclient @story_SDE-369 @website @bcom
  Scenario: Validate when returning from POS PYOTPD Scheduling Tool the changes are being updated and user is returned to
  the PYOTPD section on the Loyalty page
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "KONSTANCE" in the input field
    And I enter last name "BACKET" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    And I click on the searched client "KONSTANCE BACKET"
    And I navigate to BLM Loyallist tab
    And I click on TRIPLE POINTS link from the BLM Loyallist tab
    And I toggle to POS PYOTPD screen
    And I change data on the PYOTPD Scheduling Tool
    And I close the POS PYOTPD Scheduling tool
    Then I will be returned to b-connected PYOTPD page
    And I will see the PYOTPD updates displayed on the PYOTPD page