Feature: As a product owner, I would like to ensure all store links are redirecting to YEXT page.

  @Mew_UFT @release_17R @domain_discovery @project_UFT
  Scenario: verify that the footer link find a store redirects to specific url
    Given I visit the mobile web site as a guest user
    When I navigate to "find a store" footer links using mobile website
    Then URL should contains "l.macys.com/stores.html" in store page URL