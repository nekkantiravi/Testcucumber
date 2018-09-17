Feature: DSV Iship Scenarios

  @dsv_desktop_sev2 @Marketing_CBT
  Scenario: Verify International Home Page served from Nav app server
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Sri Lanka"
    And I close the welcome mat if it's visible
    Then I should see dynamic top navigation in "iship" mode
    And I should see navapp server clone name in view source