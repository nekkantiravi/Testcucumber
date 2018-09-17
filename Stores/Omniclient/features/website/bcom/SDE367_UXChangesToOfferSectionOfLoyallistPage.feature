# Author: Traci Morris
# Story: SDE-367 - OmniClient : UX Changes to the Offer Section of the Loyallist Page
# Date Created: 10/31/2017
# Date Signed Off:

Feature: As a BLM b-connected user I want to simplify the Loyallist page & add content to represent the current status
  of my customer's PYOTPD selections, so that I can drive sales by leveraging these Loyallist features.

  @domain_stores @omniclient @story_SDE-367 @website @bcom
  Scenario: Verifying UX Changes to the Offer Section of the Loyallist page
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "KAREE" in the input field
    And I enter last name "BAREE" in the input field
    And I enter zip code "44055" in the input field
    And I click on the omniclient search button
    When I click on the searched client "KAREE BAREE"
    And I navigate to BLM Loyallist tab
    Then the following information should be displayed on the Bottom Section of the loyalty page:
      | OFFERS ()       |
      | TRIPLE POINTS   |
      | EFFECTIVE       |
      | EXPIRES         |
    And I will validate that the Offers are displayed with the nearest expiration date first