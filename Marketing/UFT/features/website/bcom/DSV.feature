Feature: DSV scenarios

  @B-85871 @dsv_sev2_dryrun
  Scenario:Verify International Home Page served from Nav app server
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Sri Lanka"
    And I close the welcome mat if it's visible
    Then I verify the basic attributes of Iship Home page
    Then I should see navapp server clone name in view source

  @B-85871 @dsv_sev2_dryrun
  Scenario: Verify all Home Page links lead to valid destinations
    Given I visit the web site as a guest user
    Then I verify that every link on the home page leads to a valid destination
