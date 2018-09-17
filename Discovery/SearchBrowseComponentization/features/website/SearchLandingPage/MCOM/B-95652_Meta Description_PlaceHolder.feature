Feature: Verifying SEO Meta Description Place Holder for SLP Pages

  @B-95652 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: SearchLandingPage - Domestic - Verify meta description on page load with one wildcard set in stella
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then I verify the "meta description" tag in HTML view source code
    Examples:
      | category_id |
      | 74036       |
      | 74275       |
      | 74467        |


  @B-95652 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: SearchLandingPage - Domestic - Verify meta description after faceting with one wildcard set in stella
    Given I am on SLP for "<category_id>" category id in Domestic mode
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that resulting url with all selected facet values
    Then I verify the "meta description" tag in HTML view source code
    Examples:
      | category_id |
      | 74036       |
      | 74275       |
      | 74467        |


  @B-95652 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: SearchLandingPage - Domestic - Verify meta description on page load with two wildcards set in stella
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then I verify the "meta description" tag in HTML view source code
    Examples:
      | category_id |
      | 74351       |


  @B-95652 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: SearchLandingPage - Domestic - Verify meta description after faceting with two wildcards set in stella
    Given I am on SLP for "<category_id>" category id in Domestic mode
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    Then I verify the "meta description" tag in HTML view source code
    Examples:
      | category_id |
      | 74351       |



  @B-95652 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: SearchLandingPage - Domestic - Verify meta description after applying multi values from same facet
    Given I am on SLP for "<category_id>" category id in Domestic mode
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    Then I verify the "meta description" tag in HTML view source code
    Examples:
      | category_id |
      | 74351       |

    # Need to update the step for excluding  the balcklisted facet once that story is ready - B-96635 