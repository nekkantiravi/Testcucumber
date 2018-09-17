Feature: As a product owner, I would like to ensure that CHANEL brand has requested to expose customer reviews on Browse Page

  @Mew_UFT @release_17L @domain_discovery @project_UFT @mew_regression
  Scenario: verify that the customer reviews for chanel are displayed on browse page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "Chanel" in mew search box
    And I click on search go button
    Then I should see rating & reviews available on the products of SRP
