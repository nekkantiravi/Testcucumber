Feature: As a producer, I would like to see wording getting updated on sign-in page.

  @Mew_UFT @release_17H @domain_selection @project_UFT
  Scenario: verify that the wording is updated on m-secure sign-in page via registry
    Given I visit the mobile web site as a guest user
    And I navigate to wedding registry page
    When I navigate to the sign-in page
    Then I should see the expected word on sign in page
      | Expected Text  |
      | account        |
      | Practices      |