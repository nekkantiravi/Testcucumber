Feature: Verifying Functional :: SLP :: Facet Type Threshold on Page Title

  @B-95651 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: Verify PageTitle for one Facet
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then I verify page title should be "<title>"
    Examples:
      | category_id | title |
      | 80361       | Ralph Lauren Safari: Find Ralph Lauren Safari at Macy's - Macy's |
      | 76591       | Anais Anais Perfume: Shop Anais Anais Perfume - Macy's           |

  @B-95651 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: Verify PageTitle for one Facet
    Given I am on SLP for "<category_id>" category id in Domestic mode
    When I select the first color in the Color facet
    Then I verify page title should be appended with selected FacetName
    Examples:
      | category_id |
      | 73253       |
      | 72615       |

  @B-95651 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: Verify PageTitle for one Facet
    Given I am on SLP for "<category_id>" category id in Domestic mode
    And  I select multiple facet value from Brand facet section
    Then I verify page title should be appended with one selected FacetName from same Facet Section
    Examples:
      | category_id |
      | 73253       |
      | 72615       |

  @B-95651 @domain_discovery @feature_slp @mode_domestic @priority_high @migrated_to_sdt
  Scenario Outline: Verify PageTitle for one Facet
    Given I am on SLP for "<category_id>" category id in Domestic mode
    Then I select 'single' facet value from 'each' facet section
    Then I verify that page Title has only 2 facet values
    Examples:
      | category_id |
      | 73253       |
      | 72615       |
