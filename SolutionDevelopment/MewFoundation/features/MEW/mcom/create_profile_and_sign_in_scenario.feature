Feature: Add to bag scenarios

  @mew_foundation @ifs
  Scenario: Verify that customer is able to create a new profile
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site
    Then I should see user logged in to account successfully

  @mew_foundation @ifs
  Scenario: Verify that customer is successfully logged in to his account
    Given I visit the mobile web site as a registered user without add CC
    And I sign out from my current mobile site profile
    When I sign in to my same profile using mobile website
    And I navigate the global navigation menu as follows:
      | My Account |
    Then I should be navigated to My Account Page
