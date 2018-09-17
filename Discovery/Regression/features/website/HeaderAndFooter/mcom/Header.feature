Feature: Verify the header related functionality in domestic, iship and registry

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the TopNav elements are present in domestic, iship, and registry mode
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I verify the TopNav elements are present in the UI for "<user_type>" user in "<mode>" mode
    Examples:
      | user_type  | mode     |
      | guest      | site     |
      | guest      | iship    |
      | registered | site     |
      | guest      | registry |
      | registered | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the stores functionality in domestic, iship and registry
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I click "stores" from topnav elements
    Then I should be navigated to "stores" page
    Examples:
      | user_type  | mode     |
      | guest      | site     |
      | registered | site     |
      | guest      | registry |
      | registered | registry |


  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario: Verify clicking the shipping link from header in iship mode
    Given I visit the web site as a "guest" user in "iship" mode
    When I click "shipping country" from topnav elements
    Then I should be navigated to "international context" page

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario: Verify the user navigates to my account page for registered user
    Given I visit the web site as a guest user
    Then I verify the FORWARDPAGE_KEY is generated in the cookie
    When I visit the web site as a "registered" user in "site" mode
    Then I should be navigated back to my account page

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the macys logo related functionality in all three modes
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    And I verify the logo available in website
    When I navigate to browse page in "<mode>"
    And I click on logo in "<mode>" mode
    Then I should be navigated to "homepage" page
    Examples:
      | user_type  | mode     |
      | guest      | site     |
      | guest      | iship    |
      | registered | site     |
      | guest      | registry |
      | registered | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the auto suggestions and suggester call for the text in the global search field in domestic, iship and registry
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I enter "<search>" keyword in global search field
    Then I "should see" autocomplete suggestions list
    Examples:
      | user_type  | mode  | search |
      | guest      | site  | jeans  |
      | guest      | iship | coats  |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the global search field related functionality in domestic, iship and registry
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    And I "should see" the default text displayed for "<user_type>" in global search field
    When I enter "<search>" keyword in global search field
    Then I "should not see" the default text displayed for "<user_type>" in global search field
    And I should see clear link in the global search field
    When I click any clear link icon
    Then I should see the search field is empty
    And I navigate to search result page through "<search>" keyword
    Examples:
      | user_type  | mode  | search |
      | guest      | site  | jeans  |
      | guest      | iship | coats  |
      | registered | site  | pants  |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high @discovery_daily_run
  Scenario: Verify the search field and auto suggestion lists not displayed for invalid keyword in Registry mode
    Given I visit the web site as a "guest" user in "registry" mode
    When I enter "pla" keyword in global search field
    Then I "should not see" autocomplete suggestions list
  #And I verify the suggester ajax call

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify view functionality in quickbag for normal product
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I navigate to PDP page of an "normal" product
    Then I verify the product details of an "normal" product in PDP page
    Then I should see empty quickbag message
    When I add the product to the bag
    Then I navigate to home page
    Then I "should" see the quickbag overlay
    When I click quickbag container
    Then I should be navigated to "shopping bag" page
    Then I "should" see the quickbag overlay
    When I navigate to create profile page
    Then I "should" see the quickbag overlay
    When I navigate to registry home page
    And I "should" see the quickbag overlay
    And I verify the product details in quickbag
    Examples:
      | user_type  | mode  |
      | guest      | site  |
      | guest      | iship |
      | registered | site  |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify Remove functionality in quickbag for normal product
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I navigate to PDP page of an "normal" product
    Then I verify the product details of an "normal" product in PDP page
    When I add the product to the bag
    Then I navigate to home page
    When I hover over the quick bag
    Then I "should" see the quickbag overlay
    And I verify the product details in quickbag
    And I should see remove button in Item container of quickbag overlay for the product
    When I click on "remove" button for the product in Item container
    Then I should see the item removed from bag
    Examples:
      | user_type  | mode  |
      | guest      | site  |
      | guest      | iship |
      | registered | site  |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the checkout in quickbag for normal product
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I navigate to PDP page of an "normal" product
    Then I verify the product details of an "normal" product in PDP page
    When I add the product to the bag
    Then I navigate to home page
    When I hover over the quick bag
    Then I "should" see the quickbag overlay
    And I verify the product details in quickbag
    When I click on "checkout" button for the product in Item container
    Then I should be navigated to "shopping bag" page
    Examples:
      | user_type  | mode  |
      | guest      | site  |
      | guest      | iship |
      | registered | site  |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario: Verify the view functionality in quickbag for REGISTRY product
    Given I visit the web site as a "registered" user in "registry" mode
    When I navigate to PDP page of an "REGISTRY" product
    Then I add the product to a registry and continue shopping
    When I sign out from my current profile
    Then I navigate to find registry page
    When I search for the existing couple's registry
    Then I click view registry in GVR page
    When I add a registry product to the shopping bag
    Then I should be navigated to "shopping bag" page
    When I hover over the quick bag
    Then I "should" see the quickbag overlay
    And I verify the product details in quickbag

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the error message displayed when qualified GWP Item from quickbag
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I navigate to PDP page of an "GWP" product
    Then I verify the product details of an "GWP" product in PDP page
    When I add the product to the bag
    When I navigate to home page
    Then I hover over the quick bag
    And I "should" see the quickbag overlay
    And I verify the product details in quickbag
    And I should see remove button in Item container of quickbag overlay for the product
    When I click on "remove" button for the product in Item container
    Then I should see the item removed from bag
    Examples:
      | user_type  | mode  |
      | guest      | site  |
      | registered | site  |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the view functionality for "VGC" product in quickbag
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I navigate to PDP page of an "VGC" product
    Then I verify the product details of an "VGC" product in PDP page
    When I add the product to the bag
    Then I navigate to home page
    Then I hover over the quick bag
    And I "should" see the quickbag overlay
    And I verify the product details in quickbag
    Examples:
      | user_type  | mode |
      | guest      | site |
      #| registered | site |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the view functionality for "EGC" product in quickbag
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I navigate to PDP page of an "EGC" product
    Then I verify the product details of an "EGC" product in PDP page
    When I add the product to the bag
    Then I navigate to home page
    When I hover over the quick bag
    Then I "should" see the quickbag overlay
    And I verify the product details in quickbag
    Examples:
      | user_type  | mode |
      | guest      | site |
      | registered | site |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify the navigation functionality from quickbag to shopping bag after clicking product thumbnail and quickbag container
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I click quickbag container
    Then I should be navigated to "shopping bag" page
    When I navigate to PDP page of an "normal" product
    Then I verify the product details of an "normal" product in PDP page
    When I add the product to the bag
    Then I navigate to home page
    When I click quickbag container
    Then I should be navigated to "shopping bag" page
    When I hover over the quick bag
    Then I "should" see the quickbag overlay
    When I click product thumbnail from quickbag
    Then I should be redirected to PDP page
    Examples:
      | user_type  | mode     |
      | guest      | site     |
      | guest      | iship    |
      | registered | site     |
      | guest      | registry |
      | registered | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify whether all subnav headers are displaying in macys home page in domestic mode
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
    Then I verify all the subnav headers in "<mode>"
    When I navigate to any random subnav headers page in "<mode>" mode
    Then I verify all the subnav headers in "<mode>"
    Examples:
      | user-type  | mode |
      | guest      | site |
      | registered | site |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high @discovery_daily_run
  Scenario Outline: Verify wishlist, deals & promotion and gift card functionality in macys HomePage in domestic mode
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
    When I click on "wishlist" link from subNav
    Then I should be navigated to "wishlist" page
    And I navigate to home page
    When I click on "deals & promotions" link from subNav
    Then I should be navigated to "deals & promotions" page
    And I navigate to home page
    When I click on "gift cards" link from subNav
    Then I should be navigated to "gift cards" page
    Examples:
      | user-type  | mode |
      | guest      | site |
      | registered | site |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high @discovery_daily_run
  Scenario Outline: Verify registry checklist ,reward program and help links functionalities as guest user
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
    When I click on "BENEFITS" link in Link rail for registry
    Then I should be navigated to "benefits" page
    When I click on "HELP" link in Link rail for registry
    Then I should be navigated to "help" page
    Examples:
      | user-type  | mode     |
      | guest      | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify registry checklist ,reward program and help links functionalities as guest and registered user
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
#    When I click on "registry checklist" link from subNav
#    Then I should be navigated to "registry checklist" page
    When I click on "BENEFITS" link in Link rail for registry
    Then I should be navigated to "benefits" page
    When I click on "HELP" link in Link rail for registry
    Then I should be navigated to "help" page
    Examples:
      | user-type  | mode     |
      | guest      | registry |
      | registered | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify back to macys link in registry home page
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
    When I click on "back to macys" link from subNav
    Then I should be navigated to "homepage" page
    Examples:
      | user-type  | mode     |
      | guest      | registry |
      | registered | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify flyout menu for FOBs in macys home page
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I hover over any random category
    Then I should see the flyout menu
    Examples:
      | user_type  | mode     |
      | registered | site     |
      | guest      | registry |
      | registered | registry |


  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify flyout menu when hover any FOB in browse page
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
    When I navigate to browse page in "<mode>"
    And I hover over any random category
    Then I should see the flyout menu
    Examples:
      | user-type  | mode     |
      | guest      | site     |
      | registered | site     |
      | guest      | iship    |
      | guest      | registry |
      | registered | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify flyout menu when hover any FOB in search landing page
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
    When I navigate to search result page through "<search>" keyword
    And I hover over any random category
    Then I should see the flyout menu
    Examples:
      | user-type  | mode     | search   |
      | guest      | site     | jeans    |
      | registered | site     | pants    |
      | guest      | iship    | shirts   |
      | guest      | registry | flatware |
      | registered | registry | dining   |


  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high
  Scenario Outline: Verify flyout menu when hover any FOB in DLP page
    Given I visit the web site as a "<user-type>" user in "<mode>" mode
    When I am on DynamicLandingPage in "<mode>" mode
    And I hover over any random category
    Then I should see the flyout menu
    Examples:
      | user-type  | mode     |
      | guest      | site     |
      | registered | site     |
      | guest      | iship    |
      | guest      | registry |
      | registered | registry |

  @use_regression @artifact_navapp @project_HNFComp @release_17A @feature_hnf_comp @migrated_to_sdt @priority_high @test
    Scenario Outline: Verify fob functionalities for navigation to catSplash
      Given I visit the web site as a "<user-type>" user in "<mode>" mode
      When I click on any random FOB
      Then I should be navigated to "catsplash" page
      And I hover over any random category and verify links present in subcategory
    Examples:
      | user-type  | mode     |
      | guest      | site     |
      | guest      | iship    |
      | guest      | registry |
      | registered | site     |
      | registered | registry |

