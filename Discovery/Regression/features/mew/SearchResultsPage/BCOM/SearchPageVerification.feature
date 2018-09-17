Feature: Search results page all modes

  @dsv_mew_dryrun_bcom_sev1 @domain_discovery
  Scenario: Verify sort by functionality on search results page in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    And I verify there are products on the page
    And I verify search page data is displayed correctly for "jeans" keyword

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify the category re-direct for bcom
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be navigated to the corresponding "<page>" in <mode> mode
    Examples:
      | mode     | keyword    | page         |
      | domestic | 1429574    | pdp          |
      | domestic | Contact Us | absolute url |
      | domestic | chanel     | category     |
      | domestic | lancome    | category     |
      | iship    | 1429574    | pdp          |
      | iship    | Contact Us | absolute url |
      | registry | 798392     | pdp          |
      | registry | Contact Us | absolute url |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify custom message on search landing page for bcom
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    And I should see keyword "<keyword>" on search header
    And I should see all product images loaded properly
    Examples:
      | mode     | keyword |
      | domestic | jeans   |
      | iship    | pants   |
      | registry | Plates  |