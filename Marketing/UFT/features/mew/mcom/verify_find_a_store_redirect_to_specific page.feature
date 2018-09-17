Feature: As a product owner, I would like to ensure all store links are redirecting to YEXT page.

  @Mew_UFT @release_17R @domain_marketing @project_UFT @mew_regression
  Scenario: verify that the home page find a store button redirects to specific url
    Given I visit the mobile web site as a guest user
    When I click on "goto_find_a_store" on "home" page
    Then URL should contains "l.macys.com/stores.html" in store page URL