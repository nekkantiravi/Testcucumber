Feature: As a product owner, I would like to ensure that  "Attribute 27 (Explore) (pv_a27)" tag fires under page view tags for secure-m.

  @coremetrics @Mew_UFT @release_17K @domain_selection @project_UFT
  Scenario: Verify explore attribute 27 fired when user navigate to secure-m registry page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate to wedding registry page
