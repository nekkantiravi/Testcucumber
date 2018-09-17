Feature: Verify the Search Improvements functionality

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Home page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Search results page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to search results page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Browse page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to browse page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Sub splash page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to random category splash page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Dlp page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to dynamic landing page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: PDP page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I navigate to PDP of that product
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: ATB page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
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
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Shopping bag page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
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
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Home page - Domestic mode - Verify the title and Your choice text in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see Your Choice is displayed in UI when isMasterNonRanged is true
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Browse page - Domestic mode - Verify the title and SELECT ITEM ONSALE text in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to browse page in "domestic" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see SELECT ITEMS ON SALE is displayed in UI when upcOnSale or memberProductOnSale set to True in service
    Examples:
      | Keyword_to_search | segment_value | products_count |

      | Pants             | 2132          | 4              |


Feature: Verify the Search Improvements functionality in ShopApp pages

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Sign page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to signin page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Dresses           | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: My Account page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as an registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to My Account page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Jeans             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: My Wallet page - Domestic mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as an registered user
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to My Wallet page from My Account page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Socks             | 2132          | 4              |


    #Negative Scenarios

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Home page - Iship mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user in "iship" mode
    And I have "<segment_value>" for SEGMENT cookie
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Browse page - Iship mode - Verify the title review ratings and pricing functionality in APR panel
    Given I visit the web site as a guest user in "iship" mode
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to browse page in "iship" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same as in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count |
      | Pants             | 2132          | 4              |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Home page - Domestic|Iship - Verify APR functionality when autocomplete suggestions not populated
    Given I visit the web site as a guest user in "<mode>" mode
    And I have "<segment_value>" for SEGMENT cookie
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is not populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count | mode     |
      | p#$##             | 2132          | 4              | domestic |
      | p#$##             | 2132          | 4              | iship    |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Search results page - Domestic|Iship - Verify APR functionality when autocomplete suggestions not populated
    Given I visit the web site as a guest user in "<mode>" mode
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to search results page in "<mode>" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is not populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count | mode     |
      | p#$##             | 2132          | 4              | domestic |
      | p#$##             | 2132          | 4              | iship    |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Browse page - Domestic|Iship - Verify APR functionality when autocomplete suggestions not populated
    Given I visit the web site as a guest user in "<mode>" mode
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to browse page in "<mode>" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is not populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count | mode     |
      | p#$##             | 2132          | 4              | domestic |
      | p#$##             | 2132          | 4              | iship    |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Sub splash page - Domestic|Iship - Verify APR functionality when autocomplete suggestions not populated
    Given I visit the web site as a guest user in "<mode>" mode
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to random category splash page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is not populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count | mode     |
      | p#$##             | 2132          | 4              | domestic |
      | p#$##             | 2132          | 4              | iship    |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Dlp page - Domestic|Iship - Verify APR functionality when autocomplete suggestions not populated
    Given I visit the web site as a guest user in "<mode>" mode
    And I have "<segment_value>" for SEGMENT cookie
    And I navigate to dynamic landing page in "<mode>" mode
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is not populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count | mode     |
      | p#$##             | 2132          | 4              | domestic |
      | p#$##             | 2132          | 4              | iship    |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: ATB page - Domestic|Iship - Verify APR functionality when autocomplete suggestions not populated
    Given I visit the web site as a guest user in "<mode>" mode
    And I have "<segment_value>" for SEGMENT cookie
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is not populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count | mode     |
      | p#$##             | 2132          | 4              | domestic |
      | p#$##             | 2132          | 4              | iship    |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @release_17A
  Scenario Outline: Shopping bag page - Domestic|Iship - Verify APR functionality when autocomplete suggestions not populated
    Given I visit the web site as a guest user in "<mode>" mode
    And I have "<segment_value>" for SEGMENT cookie
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page
    When I navigate to shopping bag page from add to bag page
    And I enter "<Keyword_to_search>" keyword in search field
    Then I verify that autocomplete suggestions list is not populated
    And I should not see APR panel with "Best Sellers" header and "<products_count>" APR at max
    And I should not see review ratings and pricing in APR panel same in UI and Service
    Examples:
      | Keyword_to_search | segment_value | products_count | mode     |
      | p#$##             | 2132          | 4              | domestic |
      | p#$##             | 2132          | 4              | iship    |