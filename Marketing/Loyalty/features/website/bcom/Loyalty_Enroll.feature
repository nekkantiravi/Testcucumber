Feature: Loyalty Enrollment

  @use_regression @artifact_shopapp @priority_high @artifact_shopapp_2 @use_launch_call @domain_marketing @loyalty_enroll @use_browser
  Scenario: Enroll in loyalty using an existing profile
    Given I visit the web site as a registered user without loyalty
    When I enroll into the Loyalty program using existing profile
    And I navigate to the loyalty account summary page
    Then I verify the account summary page for the loyallist

  @use_regression @use_bat @artifact_shopapp @priority_high @artifact_shopapp_2 @domain_marketing @loyalty_enroll @Marketing_CBT
  Scenario: Enroll in loyalty by creating a new profile
    Given I visit the web site as a guest user
    When I enroll into the Loyalty program by creating a new profile
    And I navigate to the loyalty account summary page
    Then I verify the account summary page for the loyallist

  #Version One Card: B-20720
  @artifact_shopapp @mode_domestic @release_15G @domain_marketing @priority_medium @project_UFT
  Scenario: Verify phone number used for loyallist enrollment page should not be pre populated on my profile page
    Given I visit the web site as a guest user
    When I enroll into the Loyalty program by creating a new profile
    And I navigate to the My Profile Page
    Then I should see TEXT MESSAGE PREFERENCES section is not pre populated with any phone number