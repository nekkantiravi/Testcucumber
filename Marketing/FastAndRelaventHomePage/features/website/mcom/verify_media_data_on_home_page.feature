Feature: Verify all row media data on home page

  Scenario: Verify all media data on home page with database
    Given I visit the web site as a guest user
    When I fetch all media details for home page
    Then I verify all media data displayed on home page

  Scenario: As a signed in user, Verify all media data on home page with database
    Given I visit the mobile web site as a registered user without add CC
    And I update user profile to get home page media
    When I fetch all media details for home page
    Then I verify all media data displayed on home page

  Scenario: As a signed in user, Verify all media data on home page with database
    Given I visit the mobile web site as a registered user without add CC
    And I update user profile to get home page media
    And I sign out and sign back in
    When I fetch all media details for home page
    Then I verify all media data displayed on home page
