# Author: Discovery Regression QE Team
# Created Date: 10/10/2017

Feature: MCOM :: Sub Splash Page scenarios missing from current scope

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify products are filtered with single price facet range
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select the first price in the Price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Select facet value from price facet section.
  # Verify all products are displayed only withing selected price range only
  #  (Ex: if we select $50-$100 price facet value, then all product prices should be within this range)
  # Verify selected price facet value displayed in facet breadcrumb section.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify 'X' icon displayed for price facet section.
  # Verify pagination updated as per current product count and defaulted to first page.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page @discovery_daily_run
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify products are filtered with multiple price facet range
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select the first two price in the Price facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Select multiple facet values from price facet section.
  # Verify all products are displayed only withing selected price range only
  #  (Ex: if we select $50-$100, $500 & Above price facet values, then product prices should be in any of two price facet value range)
  # Verify all selected price facet values displayed in facet breadcrumb section.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify 'X' icon displayed for price facet section.
  # Verify pagination updated as per current product count and defaulted to first page.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page @xbrowser_subsplash
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify products are filtered with custom price facet range
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Enter minimum and max price range values and select 'GO' button
  # Verify all products are displayed only withing entered price range only
  #  (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
  # Verify entered price range value displayed in facet breadcrumb section.
  # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify 'X' icon displayed for price facet section.
  # Verify pagination updated as per current product count and defaulted to first page.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify pre-selected price facets are de-selecting upon custom price facet selection
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Select single/multiple price range facet values
  # Now enter minimum and max price range values and select 'GO' button
  # Verify all products are displayed only withing entered price range only
  #  (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
  # Verify entered price range value displayed in facet breadcrumb section.
  # Verify previously selected price range facets are removed from URL, facet breadcrumb and de-selected in price facet section
  # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify 'X' icon displayed for price facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Verify all other pre-defined price facets are in de-selected mode.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify custom price range facet is de-selecting upon defined price facet selection
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that custom price facet is removed from price facet section
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Enter minimum and max price range values and select 'GO' button
  # Now select single/multiple price range facet values
  # Verify all products are displayed only withing entered price range only
  #  (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
  # Verify selected price range facet displayed in facet breadcrumb section.
  # Verify previously entered custom price range facet is removed from URL, facet breadcrumb and price facet section.
  # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify 'X' icon displayed for price facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Verify all other pre-defined price facets are in de-selected mode.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify filtering products with grouped size facet
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "multiple" size value from Size facet section
    Then I verify that the product count is updated
    And I select a random member product
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
  # Notes:
  # Select multiple facet values from grouped size facet section. Ex: Size facet section on Men's -> Shirts
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for size facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Select any product and verify its navigated to respective PDP page or not.
  # Verify selected grouped facets persistence when we navigate back from PDP

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify filtering products with single color facet
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select the first color in the Color facet
    Then I verify that the product count is updated
    Then I verify that the product thumbnails are displayed with the selected color
    Then I verify that the selected color in the color swatch is highlighted
    And I select a random member product
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
  # Notes:
  # Select single facet value from color facet section
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for color facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Verify all product thumbnails are displayed with respective to selected color facet.
  # If thumbnail not available with selected color facet then verify jumbo swatch displayed on product image.
  # Select any product and verify its navigated to respective PDP page or not.
  # Verify selected facets persistence when we navigate back from PDP.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify filtering products with multiple color facet
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I select "multiple" facet value from "Color" facet section
    Then I verify that the product count is updated
    And I select a random member product
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
#      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Select multiple facet value from color facet section
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for color facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Verify all product thumbnails are displayed with respective to selected color facet.
  # If thumbnail not available with selected color facet then verify jumbo swatch displayed on product image.
  # Select any product and verify its navigated to respective PDP page or not.
  # Verify selected facets persistence when we navigate back from PDP.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify special characters facet selection persistence
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that the product count is displayed
    When I search for "<brand_name>" keyword in brand facet section
    And I select "<brand_name>" facet value from Brand facet section
    Then I verify that products are filtered as per selected facet value
    And I select a random member product
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode     | subcategory | category | brand_name |
      | domestic | Activewear  | MEN      | O'Neill    |
      | iship    | Activewear  | MEN      | G-Star Raw |
  # Notes:
  # Verify facet selection persistence with special characters facet
  # Select any special characters facets from BRAND facet Ex: Estee Lauder or Lancome
  # Verify that specal character facet persist in facet breadcrumb and URL.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for respective facet section.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify facet selection persistence with 'sort by' option
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And  I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    When I select "New Arrivals" in sort by drop down
    And I select a random member product
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that previously selected facets persists in breadcrumb
    And I verify that selected sort by "New Arrivals" option persist
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Verify facet selection persists when we change sort by action.
  # Verify facets and sort by persistence when we navigating back from PDP.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for respective facet sections.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify facet selection persistence with 'pagination' option
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    And  I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    When  I select 'random' page number from pagination
    And I select a random member product
    Then I should be redirected to PDP page
    When I select browse 'back' button
    And I verify that previously selected facets persists in breadcrumb
    And I verify that selected page number persist
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Verify facet selection persists when we change sort by action.
  # Verify facets and pagination persistence when we navigating back from PDP.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for respective facet sections.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify bookmarked faceted URL loading without any error
    Given I visit the web site as a guest user in "<mode>" mode
    When I navigate to the bookmarked url:
      | MCOM | <url> |
    Then I should be navigated to "category sub splash" page
    Examples:
      | mode     | url                                                                                                                                                   |
      | domestic | /shop/womens-clothing/womens-activewear/Brand,Special_offers/Material Girl,Clearance%2FCloseout?id=29891&cm_sp=us_hdr-_-women-_-29891_activewear_COL1 |
      | domestic | /shop/womens-clothing/womens-activewear/Special_offers,Pageindex,Sortby/Clearance%2FCloseout,2,PRICE_HIGH_TO_LOW?id=29891                             |
      | registry | /shop/wedding-registry/kitchen/anolon/Cookware_type,Price/Cookware Set,1%257C563?id=11354                                         |
      | iship    | /shop/womens-clothing/womens-activewear/Special_offers,Pageindex,Sortby/Clearance%2FCloseout,2,PRICE_HIGH_TO_LOW?id=29891                             |
  # Notes:
  # Open any subsplash page and select multiple facet vlaues.
  # Read current page URL and open same URL in another TAB.
  # Now verify facet breadcrumb, product results and page url not changes when compare to current page.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify copy block media suppressed after facet selection
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify that copy block is displayed
    When I select 'single' facet value from 'any' facet section
    Then I verify that copy block is not displayed
    When I click on clear all button
    Then I verify that copy block is displayed
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | MEN      |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Verify copy block media displayed below thumbnail grid before making any facet selection.
  # Verify copy block media suppressed below thumbnail grid after making any facet selection.
  # Verify copy block media retained again below thumbnail grid after removing selected facet.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify non-shop(/shop, /ce/splash, /campaign) url pattern links navigation from left navigation panel
    Given I visit the web site as a guest user in "<mode>" mode
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then I verify non-shop pattern url links are not resulting error page
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
  # In left nav panel, identify all 'non-shop' pattern url's.
  # It means identify all left nav links without containing '/shop/xx', '/ce/splash', '/campaign/social?campaign_id=XXX'
  # Then verify all those 'non-shop' links are resulting '200' response code with 'Mechanize' agent request.

  @use_regression @domain_discovery @priority_medium @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship|Registry - Verify pagination retained to first page after making other actions on page
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    When I select 'random' page number from pagination
    When I select "New Arrivals" in sort by drop down
    And I verify that navigated to page 1 on sub splash page
    When I select 'random' page number from pagination
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that navigated to page 1 on sub splash page
    When I select 'random' page number from pagination
    And I filter the result set to show "120" products from "bottom"
    And I verify that navigated to page 1 on sub splash page
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |
  # Notes:
  # Navigate to sub splash page.
  # Pagination will retained to first page products after changing
  #  'Sort By' or 'Item Count' or 'Facet Values'.
  # So need to make random pagination and verify again pagination retained
  #  to first page or not in pagination section and also in URL.

  @use_regression @domain_discovery @priority_high @mode_domestic @mode_iship @mode_registry @feature_subsplash_page
  Scenario Outline: SubSplashPage - Domestic|Iship - Verify product count is updated for Special Offers facet selection in ALL modes
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When  I navigate to the "<subcategory>" sub splash page under "<category>"
    Then  I verify that the product count is displayed
    And   I verify that "Special Offers" facet is listed on left nav
    Then  I select the random value in the "Special Offers" facet
    And   I verify that the product count is updated
    When  I remove the selected facet from the breadcrumb
    Then  I verify that the product count returns to original
    Examples:
      | mode     | subcategory | category |
      | domestic | Activewear  | WOMEN    |
      | iship    | Activewear  | MEN      |
      | registry | Anolon      | KITCHEN  |


