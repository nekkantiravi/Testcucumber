# Author: Ovidiu Rucoi
# Story: SDE-161 - My Shop: Unassigned Areas on the SM Dashboard
# Date Created:
# Date Signed Off:

  Feature: As a My Shop Selling Manager, I want the Unassigned Area bar on my dashboard to be hidden
    if there are no Unassigned Selling areas that should show under it

    @domain_stores @omniclient @story_SDE-161 @website @mcom
    Scenario: If Unassigned areas are present, then "Unassigned Areas" bar will be presented to user
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as Admin User
      And change user into "10000057" from Admin interface
      Then I should see a list of Selling managers in my store
      And the Unassigned Area is displayed at the bottom of SM list

    @domain_stores @omniclient @story_SDE-161 @website @mcom
    Scenario: If NO Unnasigned Areas are present then "Unassigned Areas" bar will not be presented
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as Admin User
      # Assoc ID of SM with MyShop on and no Unassigned Areas needs to be determined
      And change user into "10084803" from Admin interface
      Then I should see a list of Selling managers in my store
      And I should see NO Unassigned Area bar at the bottom of SM list