Feature: Search results page all modes

  @dsv_mew_dryrun_bcom_sev1 @domain_discovery
  Scenario: Verify sort by functionality on search results page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    And I verify there are products on the page
    And I verify search page data is displayed correctly for "jeans" keyword