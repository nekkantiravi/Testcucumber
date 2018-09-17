Feature: To verify create profile functionality on mobile site

  @dsv_mew_sev1
  Scenario: Create And Update Profile
    Given I visit the mobile web site as a guest user
    And I create a new profile in mobile site
    When I navigate to my profile page using mobile website
    Then I update profile details on my profile