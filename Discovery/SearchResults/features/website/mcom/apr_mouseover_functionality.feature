Feature: Verify the Search Improvements functionality

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Home page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel when mouse hover on any autosuggestion
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I mouse over on the random auto suggestion text
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Search results page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel when mouse hover on any autosuggestion
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to search results page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I mouse over on the random auto suggestion text
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Shirts            | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Browse page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel when mouse hover on any autosuggestion
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to browse page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I mouse over on the random auto suggestion text
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Jeans             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: ATB page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel when mouse hover on any autosuggestion
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I mouse over on the random auto suggestion text
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Shoes             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: My Account page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel when mouse hover on any autosuggestion
    Given I visit the web site as an registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to My Account page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    When I mouse over on the random auto suggestion text
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Shirts            | 2132          | 4              |