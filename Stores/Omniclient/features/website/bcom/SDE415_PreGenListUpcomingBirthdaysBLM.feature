# Story: Create List - Pre-Generated List - Upcoming Birthdays
# Date Created: 12/28/2017
# Date Signed Off:

Feature: As a Selling Associate, I want the ability to select/view a ‘Pre-Generated List’ from the new
  Create List Dashboard, so that I can quickly and efficiently create my list for outreach.

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate on Create List tab
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-415 @website @bcom
  Scenario: Verify List Title and Subtitles
    And I should see the title Upcoming Birthdays on the Create List dashboard
    And I should see the subtitle "Next 30 Days" for Upcoming Birthdays
    When I expand the subtitle Next 30 Days for Upcoming Birthdays
    Then I should see the following values in the Birthdays dropdown on the Create List dashboard
      | Next 14 Days |
      | Next 30 Days |
      | Next 60 Days |

  @domain_stores @omniclient @story_SDE-415 @website @bcom
  Scenario: Verify Pre-generated list is displayed when selected
    When I select the title Upcoming Birthdays on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    And the customer count will display on the Upcoming Birthday Create List dashboard
    And the list should be sorted by BIRTHDATE
    And the following columns should be displayed on the Create List dashboard
      | Birthday |
      | Visits   |
      | $/All    |
      | $/Me     |
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed