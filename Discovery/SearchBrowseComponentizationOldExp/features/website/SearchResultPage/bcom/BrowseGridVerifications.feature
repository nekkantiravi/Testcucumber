# Author: BGV QE Team

Feature:C2 :: P2 :: BCOM :: Browse Grid Variations automation scenarios Search Result page - for Search Results Page


  @use_regression @feature_bgv @artifact_navapp @domain_discovery @release_15F @release_15G @mode_domestic @mode_registry @mode_iship @priority_medium @add_missing_scope @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify breadcrumb, static quick peek icon, alt image
    Given I am on SearchResultsPage for "jeans" in <shopping_mode> mode
    Then I verify that the breadcrumb is not displayed in search result page
    And I verify that the quick peek icon on product image without hovering on product image
    And I verify that the default product thumbnail and alt image
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |

  @artifact_navapp @domain_discovery @use_regression @priority_medium @feature_new_quickview @mode_domestic @mode_registry @mode_iship @qv_use_smoke @add_missing_scope @project_snb
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify Quick Peek button selection and overlay close button selection
    Given I am on SearchResultsPage for "jeans" in <shopping_mode> mode
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    And I verify that default attributes on new quick peek overlay
    When I select X button on new quick peek overlay
    Then I verify that quick peek is not displayed
    When I select 'multiple' facet value from 'any' facet sections
    And I select the quick peek of random product
    Then I verify that quick peek is displayed
    And I verify that default attributes on new quick peek overlay
    When I select anywhere outside of new quick peek overlay
    Then I verify that quick peek is not displayed
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
