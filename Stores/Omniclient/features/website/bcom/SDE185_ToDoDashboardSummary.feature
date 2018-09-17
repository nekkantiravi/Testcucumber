# Author: Ovidiu Rucoi
# Story: SDE-185 - To Do Dashboard Summary
# Date Created:
# Date Signed Off:

Feature: As an associate, I no longer want to see pending lists or pending events displayed within the To Do Summary
  on my Dashboard

  @domain_stores @omniclient @story_SDE-185 @website @bcom
  Scenario: Associates should not see pending lists on their dashboard
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I create a TO DO from CREATE LIST page
      | TARGET GM ALL  |
    #General Manager
    And I navigate to Bloomingdales Homepage
    Then I should not see the pending todo displayed on the dashboard
    And I log out from the application
    # Corp Store Exec
    When I sign into Omniclient BLM application as Corporate Store Executive
    Then I should not see the pending todo displayed on the dashboard
    And I log out from the application
    # District Manager
#    When I sign into Omniclient BLM application as District Manager
#    Then I should not see the pending to do displayed on the dashboard


#    @manual @domain_stores @omniclient @story_SDE-185 @website @bcom
#    Scenario: Associates should not see pending events on their dashboard
#      Given I launch the bloomingdales's omniclient page
#      When I sign into Omniclient BLM application as corporate admin
#      And I create an event from CREATE EVENT page
#      #Corp Admin
#      And I navigate to Macys Homepage
#      Then I should not see the pending event displayed on the dashboard
#      And I log out from the application
#      #Corp Executive
#      When I sign into Omniclient BLM application as corporate executive
#      Then I should not see the pending event displayed on the dashboard