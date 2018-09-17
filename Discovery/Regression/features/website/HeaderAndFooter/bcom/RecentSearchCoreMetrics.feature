###########################################################################################################################
# B-59071 :: Bcom :: Desktop :: Coremetrics for Recent Searches
#############################################################################################################################

Feature: Verify Coremetrics tagging for recent searches functionality in DOMESTIC & ISHIP modes

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - MultiplePages - Verify element tag is fired when a user clicks on search box and RECENT SEARCH panel is displayed in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I clear existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I should see element tag is fired when recent searches drop down is displayed
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name              | Expected Value                           |
      | 1              | Page ID (pi)                | search_results                           |
      | 1              | Category ID (cg)            | Recent_Searches                          |
      | 1              | Explore Attribute 2 (pr_a2) | Page ID where user clicks the search box |

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - MultiplePages - Verify Page view tag is fired when a user TAP ON RECENT SEARCHES AND navigates to search result and non Global Brand Shop (GBS) in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I clear existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name           | Expected Value                    |
      | 1              | Page ID (pi)             | search_results_recent             |
      | 1              | Category ID (cg)         | onsite_search_recent              |
      | 1              | Onsite Search Word (se)  | {recent_search}                   |
      | 1              | Search Result Count (sr) | {No. of search results displayed} |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - MultiplePages - Verify Page view tag is fired when a user TAP ON RECENT SEARCHES AND navigates to search result and Global Brand Shop (GBS) in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I clear existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name           | Expected Value                                       |
      | 1              | Page ID (pi)             | {insert GBS page ID}, (For example: Browse_burberry) |
      | 1              | Category ID (cg)         | onsite_search_recent                                 |
      | 1              | Onsite Search Word (se)  | search key word                                      |
      | 1              | Search Result Count (sr) | {No. of search results displayed}                    |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - PDP -Verify product view tag is fired when a user TAP ON RECENT SEARCH and navigate to PDP in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
    When I click and deleting existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    And I select a random member product
    Then I should be redirected to PDP page
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value       |
      | 5              | Category ID (cg)                | onsite_search_recent |
      | 5              | Attribute 14 (Explore) (pr_a14) | search results count |
      | 5              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - PDP -Verify  product view tag is fired when a user TAP ON RECENT SEARCH and navigate to Quick view outlay in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
      | pants   |
    When I click and deleting existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    And I select Quick View for a random product
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value       |
      | 5              | Category ID (cg)                | onsite_search_recent |
      | 5              | Attribute 14 (Explore) (pr_a14) | search results count |
      | 5              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - AddToBagPage - Verify Shop 5 Tag is fired when a user TAP ON RECENT SEARCH and navigate to ADD TO BAG through normal PDP in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I click and deleting existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    And I select a random product
    And I select available size of the product
    And I select on "Add to Bag" button
    Then I should be navigated to add to bag page
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value       |
      | 4              | Category ID (cg)                | onsite_search_recent |
      | 4              | Attribute 14 (Explore) (pr_a14) | search results count |
      | 4              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - AddToBagPage - Verify Shop 5 Tag is fired when a user TAP ON RECENT SEARCH and navigate to ADD TO BAG through QV PDP in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I click and deleting existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    When I select Quick View for a random product
    And I select 'ADD TO BAG' button on 'quick view' overlay
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value       |
      | 4              | Category ID (cg)                | onsite_search_recent |
      | 4              | Attribute 14 (Explore) (pr_a14) | search results count |
      | 4              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - ShoppingBagPage - Verify Shop 5 Tag is fired when a user TAP ON RECENT SEARCH and navigate to Shopping Bag through PDP in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I click and deleting existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    And I select a random product
    And I select available size of the product
    And I select on "Add to Bag" button
    Then I should be navigated to add to bag page
    When I navigate to shopping bag page from add to bag page
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value       |
      | 4              | Category ID (cg)                | onsite_search_recent |
      | 4              | Attribute 14 (Explore) (pr_a14) | search results count |
      | 4              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O
  Scenario: HeaderAndFooter - ShoppingBagPage - Verify Shop 5 Tag is fired when a user TAP ON RECENT SEARCH and navigate to Shopping Bag through QV PDP in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I click and deleting existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    When I select Quick View for a random product
    And I select 'ADD TO BAG' button on 'quick view' overlay
    And I navigate to checkout page using quick view overlay or add to bag page
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value       |
      | 4              | Category ID (cg)                | onsite_search_recent |
      | 4              | Attribute 14 (Explore) (pr_a14) | search results count |
      | 4              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16O @manual
Scenario: HeaderAndFooter - OrderConfirmationPage - Verify Shop 9 Tag is fired when a user TAP ON RECENT SEARCH and navigate to Order Conformation page through PDP in DOMESTIC & ISHIP modes
  Given I visit the web site as a guest user
  And I search with different keywords:
    | dresses |
    | jeans   |
    | shorts  |
  When I click and deleting existing persist text on the search box
  Then I should see "Recent Searches" text in recent search panel
  And I click on any random recent search
  And I select a random product
  And I select available size of the product
  And I select on "Add to Bag" button
  Then I should be navigated to add to bag page
  When I navigate to shopping bag page from add to bag page
  And I continue checking out until I reach the "order confirmation" page as coremetrics guest user
  And I verify Digital Analytics tags:
    | Tag Type (tid) | Parameter Name                  | Expected Value       |
    | 8              | Category ID (cg)                | onsite_search_recent |
    | 8              | Attribute 14 (Explore) (pr_a14) | search results count |
    | 8              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
  #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY

  @domain_discovery @artifact_navapp @feature_search_improvements_2016 @use_coremetrics @use_manual @wip @release_16Ol
  Scenario: HeaderAndFooter - OrderConfirmationPage - Verify Shop 9 Tag is fired when a user TAP ON RECENT SEARCH and navigate to Order Conformation page through PDP in DOMESTIC & ISHIP modes
    Given I visit the web site as a guest user
    And I search with different keywords:
      | dresses |
      | jeans   |
      | shorts  |
    When I click and deleting existing persist text on the search box
    Then I should see "Recent Searches" text in recent search panel
    And I click on any random recent search
    And I select Quick View for a random product
    And I select 'ADD TO BAG' button on 'quick view' overlay
    And I navigate to checkout page using quick view overlay or add to bag page
    And I continue checking out until I reach the "order confirmation" page as coremetrics guest user
    And I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value       |
      | 8              | Category ID (cg)                | onsite_search_recent |
      | 8              | Attribute 14 (Explore) (pr_a14) | search results count |
      | 8              | Attribute 29 (Explore) (pr_a29) | onsite search word   |
   #MAINTAIN ALL OTHER EXPLORE ATTRIBUTES THAT APPLY