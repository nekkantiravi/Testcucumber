Feature: As a product owner, I would like to ensure when a signed-in user visits a PDP, the page view tag TID1 fires, but it does not include attribute pv_a27

  @coremetrics @Mew_UFT @release_17J @domain_selection @project_UFT
  Scenario: Verify explore attribute 27 fired when user signed in from PDP page
    Given I visit the mobile web site as a registered user
    When I search for "Dresses"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
