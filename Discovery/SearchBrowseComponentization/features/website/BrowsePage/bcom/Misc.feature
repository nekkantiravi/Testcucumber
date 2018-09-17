
Feature: MCOM :: Browse Page scenarios missing from current scope


  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify price on quick viw overlay for member/standard product
    Given I am on CategoryBrowsePage for "1000246" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I verify price on quick view overlay is same as on thumbnail grid
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  # Navigate to any browse page where we have master products in each mode
  # Get price content section data from thumbnail grid and select quick view button for that master product
  # Verify same price values are displayed on QV overlay or not

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship
  Scenario Outline: BrowsePage - Domestic|Iship|Registry - Verify price on quick viw overlay for master product
    Given I am on CategoryBrowsePage for "1000246" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    And I select "quick view" button for "master" product on page
    Then I verify that quick peek is displayed
    Then I verify price on quick view overlay is same as on thumbnail grid
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |
  # Navigate to any browse page where we have master products in each mode
  # Get price content section data from thumbnail grid and select quick view button for that master product
  # Verify same price values are displayed on QV overlay or not

  @domain_discovery @artifact_navapp @mode_domestic @mode_registry @mode_iship @adding_missing_scope @priority_medium
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify ADD TO BAG button on quick view overlay as guest user from browse page in All mode
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 1004906     |
      | Registry      | 8147        |
      | Iship         | 1004906     |

  #***********************ADD TO BAG  AS Registered User for Domestic|Iship|Registry Mode *******************************************
  @domain_discovery @artifact_navapp @mode_domestic @adding_missing_scope @priority_medium
  Scenario: SearchResultsPage - Domestic - Verify ADD TO BAG button on quick view overlay from browse page as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    And I clear existing class variables to avoid data issues
    When I navigate to the "Cutlery" browse page under "HOME"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page

  @domain_discovery @artifact_navapp @mode_registry @adding_missing_scope @priority_medium
  Scenario: SearchResultsPage -  Registry  - Verify ADD TO BAG button on quick view overlay from browse page as singed-in user in  Registry  mode
    Given I visit the web site as a registered user in "registry" mode
    And I clear existing class variables to avoid data issues
    When I navigate to the "Cutlery" browse page under "KITCHEN"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click add to bag button on QuickView page
    Then I click checkout button on QuickView page
    Then I verify that respective product is in 'Shopping Bag' page

    # ***********************ADD TO REGISTRY  As Guest User For Domestic Mode *******************************************
  @domain_discovery @artifact_navapp @mode_domestic @adding_missing_scope @priority_medium
  Scenario: SearchResultsPage - Domestic - Verify ADD TO REGISTRY button on quick view overlay from browse page as guest user in Domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    And I clear existing class variables to avoid data issues
    When I am on Browse Page for "Cutlery" under "Home"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I should be navigated to the registry sign in page

#*********************** ADD TO REGISTRY  As Register User for Domestic Mode *******************************************
  @domain_discovery @artifact_navapp @mode_domestic @adding_missing_scope @priority_medium
  Scenario: SearchResultsPage - Domestic - Verify ADD TO REGISTRY button on quick view overlay from browse page as singed-in user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    And I clear existing class variables to avoid data issues
    When I navigate to the "Cutlery" browse page under "HOME"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    Then I click "ADD TO REGISTRY" button on QuickView page
    Then I verified error message displayed as "We're unable to find your registry in your online profile."

 #*********************** ADD TO LIST as Guest User for Domestic and Registry Mode *******************************************
  @domain_discovery @artifact_navapp @mode_domestic @adding_missing_scope @priority_medium
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify ADD TO LIST button on quick view overlay from browse page as guest user in Domestic|Registry mode
    Given I am on CategoryBrowsePage for "1004906" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "Added to your temporary wishlist.Sign in to save this list permanently."
    And I take screen shot of the page
    Examples:
      | shopping_mode |
      | Domestic      |

  #*********************** ADD TO LIST as Registered User for Domestic and Registry Mode*******************************************
  @domain_discovery @artifact_navapp @mode_domestic @adding_missing_scope @priority_medium
  Scenario: SearchResultsPage - Domestic - Verify ADD TO LIST button on quick view overlay from browse page as registered user in Domestic mode
    Given I visit the web site as a registered user in "domestic" mode
    And I clear existing class variables to avoid data issues
    When I navigate to the "Cutlery" browse page under "HOME"
    Then I modify the url to get in to snbc experiment
    Then I select "quick view" button for "member" product on page
    Then I verify that quick peek is displayed
    And I take screen shot of the page
    Then I click "ADD TO LIST" button on QuickView page
    Then I should see success message as "This item has been added to your wishlist."
    And I take screen shot of the page

  @domain_discovery @artifact_navapp @mode_domestic @mode_registry @mode_iship @adding_missing_scope @priority_medium
  Scenario Outline: SearchResultPage - Domestic|Iship|Registry - Verify pagination retained to first page after making other actions on page
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    Then I verify that item count buttons in page
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    When I select random option in sort by drop down
    Then I verify that navigated to page 1 on search result page
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    When I select 'single' facet value from 'any' facet section
    Then I verify that navigated to page 1 on search result page
    Then I verify that products are filtered as per selected facet values
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    When I click 4 pagination number
    Then I verify that navigated to page 4 on search result page
    When I filter the result set to show "180" items per page
    Then I verify that navigated to page 1 on search result page
    Then I take screen shot of the page
    Examples:
      | shopping_mode | Category_id |
      | Domestic      | 1004906     |
      | Registry      | 8147        |
      | Iship         | 1004906     |

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt @use_regression
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify copy block media suppressed after facet selection
    Given I clear existing class variables to avoid data issues
    When I am on CategoryBrowsePage for "1000238" category id in <Site_Mode> mode
    Then I verify that copy block is displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that copy block is not displayed
    When I deselect the selected facet from the overlay
    Then I verify that copy block is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Iship     |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @use_regression @snbc_comp @migrated_to_sdt
  Scenario Outline: CategoryBrowsePage - Domestic|Iship|Registry - Verify special characters facet selection persistence
    Given I am on CategoryBrowsePage for "<Category_id>" category id in <shopping_mode> mode
    And I clear existing class variables to avoid data issues
    When I search for "<brand_name>" keyword in brand facet section
    And I select "<brand_name>" facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on CategoryBrowsePage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
    Examples:
      | shopping_mode | Category_id | brand_name  |
      | Domestic      | 1006690     | Estée Lauder|
      | Registry      | 8241        | Nambé       |
      | Iship         | 1000233     | Nambé       |