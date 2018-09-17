# Story: SDE-533 Create List: Feature should be flagged on/off at the store level
# Date Created:
# Date Signed Off:

Feature: As a business owner I want the ability to turn on Create List functionality at the store level, so that
  I have the ability to pilot with limited store exposure.

  @bcom @domain_stores @omniclient @website @story_SDE-533
  Scenario: New Create List feature ON
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient application as Admin User
    And change user into "10000083" from Admin interface
    And I navigate on Create List tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @bcom @domain_stores @omniclient @website @story_SDE-533
  Scenario: New Create List feature OFF
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient application as Admin User
    And change user into "10000084" from Admin interface
    And I navigate on Create List tab
    Then PROFILE tab is displayed in omniclient view
    And TRANSACTIONS tab is displayed in omniclient view
    And TARGET tab is displayed in omniclient view