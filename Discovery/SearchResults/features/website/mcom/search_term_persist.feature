Feature:Verify Search term doesn't persist after clicking on product

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Home page - Domestic mode - Verify the title and Search term persist functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Search results page - Domestic mode - Verify the title and Search term persist functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to search results page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Browse page - Domestic mode - Verify the title and Search term persist functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to browse page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Sub splash page - Domestic mode - Verify the title and Search term persist functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to random category splash page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Dlp page - Domestic mode - Verify the title and Search term persist functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to dynamic landing page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: PDP page - Domestic mode - Verify the title and Search term persist functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: ATB page - Domestic mode - Verify the title and Search term persist functionality in APR panel
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
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Shopping bag page - Domestic mode - Verify the title and Search term persist functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    When I select random APR and navigate to PDP
    Then I should see the entire search term associated with the selected APR persist in search box
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |
