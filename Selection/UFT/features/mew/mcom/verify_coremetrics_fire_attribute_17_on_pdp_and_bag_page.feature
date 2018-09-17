Feature: As a product owner, I would like to ensure that application fires  "Attribute 17" Under "Product view Tag"

  @coremetrics @Mew_UFT @release_17M @domain_selection @project_UFT
  Scenario: Verify explore attribute 17 fired when user navigate to PDP and bag page
    Given I visit the mobile web site as a guest user
    And I directly add an available and orderable product "2112827" to my bag in mobile site
