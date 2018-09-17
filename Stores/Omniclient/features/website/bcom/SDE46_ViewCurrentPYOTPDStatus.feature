# Author: Ovidiu Rucoi
# Story: SDE-46 - PYOTPD: View Current PYOTPD Status
# Date Created: 11/13/2017
# Date Signed Off:

Feature: As an associate I want to see my customer's PYOTPD current status so that I can help her evaluate and
  manage her selections to drive more sales through this Loyalty benefit.

  @domain_stores @omniclient @story_SDE-46 @website @bcom
  Scenario: Verify that the TRIPLE POINTS section contains all required fields
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the SearchByID radio button
    And I input "L920000174250" SearchByID in the search box
    And I click on the omniclient search button
    When I click on the searched client "TESTINGBLM LOYALTYBLM"
    And I navigate to BLM Loyallist tab
    Then the following information should be displayed on the Bottom Section of the loyalty page:
      | OFFERS ()     |
      | TRIPLE POINTS |
    When I click on TRIPLE POINTS link from the BLM Loyallist tab
    Then the following information should be displayed on the TRIPLE POINTS Section:
      | Eligible Days Remaining |
      | Scheduled Days          |
      | Used Days               |
    When I click on OFFERS link from the BLM Loyallist tab
    Then the following information should be displayed on the Bottom Section of the loyalty page:
      | EFFECTIVE       |
      | EXPIRES         |
