Feature: Verifying SEO tags for SLP Pages

  @B-95643 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: SearchLandingPage - Domestic - Verify canonical tag
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then I verify the "canonical" tag in HTML view source code
    Examples:
      | category_id |
      | 73010       |
      | 76591       |


  @B-95643 @domain_discovery @priority_high @mode_domestic @mode_domestic  @migrated_to_sdt @feature_slp_page @feature_slp
  Scenario Outline: SearchLandingPage - Domestic - Verify canonical tags after multiple facet selections
    Given I am on SLP for "<category_id>" category id in Domestic mode
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    And I verify that resulting url with all selected facet values
    And I verify that canonical tag contains facet value of same facet
    Examples:
      | category_id |
      | 83480       |