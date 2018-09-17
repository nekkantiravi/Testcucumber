Feature: As a mobile user i want to browse products in easy views with limited products on each page

  @dsv_mew_sev1 @domain_discovery
  Scenario: As a mobile user I should see not more than 48 products in category browse view per page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    Then I should see not more than 48 products per page