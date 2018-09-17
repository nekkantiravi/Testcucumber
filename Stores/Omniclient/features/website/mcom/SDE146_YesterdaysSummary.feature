# Author: Ovidiu Rucoi
# Story: SDE-146 - OmniClient :: Yesterday's Summary and Recent Customer List
# Date Created: 05/03/2017
# Date Signed Off:

Feature: As a Selling Manager in the MyShop configuration, I need to see the Yesterday's Summary and the most recent customer list presented for the specific area

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager

  @domain_stores @omniclient @mcom @website @story_SDE-146
  Scenario: Selling Manager clicks into a staffing zone, Yesterday's Summary is displayed for selected area
    And I click the plus sign next to a Selling Manager name
    And I click on the staffing zone
    Then I should see the Yesterday's Summary header
    And I should see the Client Sales field
    And I should see the Clients Shopped field
    And I should see the Potentials to Clients field
    And I should see the New to Book field

  @domain_stores @omniclient @mcom @website @story_SDE-146
  Scenario: Selling Manager clicks into a staffing zone, Recent Customer List is displayed for selected area
    And I click the plus sign next to a Selling Manager name
    And I click on the staffing zone
    Then I should see the Recent Customer List
    And I should see the Customer Name field
    And I should see the SA field
    And I should see the Last Visit field
    And I should see the Visits field
    And I should see the dollars per SA field

  @domain_stores @omniclient @mcom @website @story_SDE-146
  Scenario: Selling Manager clicks into a staffing zone under the Unassigned Bar, Yesterday's Summary is displayed for selected area
    And I click on the Unassigned Area bar
    And I click on a Unassigned Selling Area
    Then I should see the Yesterday's Summary header
    And I should see the Client Sales field
    And I should see the Clients Shopped field
    And I should see the Potentials to Clients field
    And I should see the New to Book field

  @domain_stores @omniclient @mcom @website @story_SDE-146
  Scenario: Selling Manager clicks into a staffing zone under the Unassigned Bar, Recent Customer List is displayed for selected area
    And I click on the Unassigned Area bar
    And I click on a Unassigned Selling Area
    Then I should see the Recent Customer List
    And I should see the Customer Name field
    And I should see the SA field
    And I should see the Last Visit field
    And I should see the Visits field
    And I should see the dollars per SA field

    @manual @domain_stores @omniclient @mcom @website @story_SDE-146
    Scenario: Verify that the Yesterday's Summary reflects the totals that are specific to an area
    And I click the plus sign next to a Selling Manager name
    And I click on the staffing zone
    Then I should see the Yesterday's Summary for that specific area
    And the values should reflect the totals specific for that area