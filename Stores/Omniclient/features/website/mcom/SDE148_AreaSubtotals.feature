# Author: Ovidiu Rucoi
# Story: SDE-148 - OmniClient :: Area Subtotals
# Date Created: 05/03/2017
# Date Signed Off:

  # TODO: AUTOMATE ONCE FEATURE IS ENABLED IN MACYS
@manual
Feature: As a Selling Manager in the MyShop configuration, I need to see the AREA SUBTOTALS presented for the specific area

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager

  @domain_stores @omniclient @mcom @website @story_SDE-148
  Scenario: Selling Manager clicks into a staffing zone, the Area Subtotals will display for that selected area
    And I click the plus sign next to a Selling Manager name
    Then I should see the Area Subtotals for all staffing zones under the SM
    When I click on the staffing zone
    Then I should see the Area Subtotals

  @domain_stores @omniclient @mcom @website @story_SDE-148
  Scenario: Selling Manager clicks into a staffing zone under the Unassigned Bar, the Area Subtotals will display for that selected area
    And I click on the Unassigned Area bar
    Then a list of Unassigned Selling Areas are displayed
    When I click on a Unassigned Selling Area
    Then I should see the Area Subtotals