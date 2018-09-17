# Story: SDE-536 Create List Wizard: Save, cancel, Delete, Back to My Lists buttons on Wizard page
# Date Created:
# Date Signed Off:

Feature: As a Create List Wizard user, I want to have a way to save the list criteria that I create, to My Lists,
  so that I can use it again at another time, without having to re-enter all the criteria.
  This will save me time and remove friction around the efficiency of the feature.


  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @mcom @domain_stores @omniclient @website @story_SDE-536
  Scenario: Verify the input fields are displayed as expected
    When I should see the Back button on the New List view
    And I should see the List Title on the New List view
    And I should see the Delete Button on the New List view
    And I should see the Cancel Button on the New List view
    And I should see the Save Button on the New List view

  @mcom @domain_stores @omniclient @website @story_SDE-536
  Scenario: Verify the List Name input field
#    enter max character - 50
    When I enter a title "rrrrrrrrr tttttttt yyyyyyyyy uuuuuuuuu iiiiiiiiiii" in the List Name input field on the New List view
    Then I should see the title "rrrrrrrrr tttttttt yyyyyyyyy uuuuuuuuu iiiiiiiiiii" in the List Name input field on the New List view
