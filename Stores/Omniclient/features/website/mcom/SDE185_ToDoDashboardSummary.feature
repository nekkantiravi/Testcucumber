# Author: Ovidiu Rucoi
# Story: SDE-185 - To Do Dashboard Summary
# Date Created:
# Date Signed Off:

@manual @SKIPPED
  Feature: As an associate, I no longer want to see pending lists or pending events displayed within the To Do Summary
    on my Dashboard

    @manual @domain_stores @omniclient @story_SDE-185 @website @mcom
    Scenario: Associates should not see pending lists on their dashboard
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as general manager
      And I create a TO DO from CREATE TO DOS page
        | TARGET GM ALL  |
      #General Manager
      And I navigate to Macys Homepage
      Then I should not see the pending todo displayed on the dashboard
      And I log out from the application
      # Corp Store Exec
      When I sign into Omniclient application as corporate store executive
      Then I should not see the pending todo displayed on the dashboard
      And I log out from the application
      # District Manager
      When I sign into Omniclient application as District Manager
      Then I should not see the pending todo displayed on the dashboard


    @manual @domain_stores @omniclient @story_SDE-185 @website @mcom
    Scenario: Associates should not see pending events on their dashboard
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as corporate admin
      And I create an event from CREATE EVENT page
      #Corp Admin
      And I navigate to Macys Homepage
      Then I should not see the pending event displayed on the dashboard
      And I log out from the application
      #Corp Executive
      When I sign into Omniclient application as corporate executive
      Then I should not see the pending event displayed on the dashboard

    Scenario: Delete Created ToDos
      Given I launch the macy's omniclient page
      When I sign into Omniclient application as Admin User
      And change user into "10000059" from Admin interface
      And I navigate to ALL TO DOS page
      And I click on MY MACYS TO DOS tab
      Then I should see the list of TO DOS
      When I delete all created ToDos
      Then I should not see any ToDos that can be deleted