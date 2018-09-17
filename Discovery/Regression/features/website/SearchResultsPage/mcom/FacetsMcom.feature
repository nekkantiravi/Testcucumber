Feature: Verifying Facets in Search Landing Page

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @discovery_daily_run
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filter products when we select any one random facet value
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      |shopping_mode|keyword    |
      |Domestic     |Jeans      |
      |Registry     |Plates     |
      |Iship        |Jeans      |
     # Notes:
     # Select facet value from any section other than Pick-up InStore(BOPS), Size(GROUPED FACET) facet sections.
     # Verify product count is updated on top of thumbnail grid.
     # Verify breadcrumb section displayed with single facet value.
     # Verify product count in thumbnail grid matched with selected facet value product count.
     # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
     # Verify 'X' icon displayed for respective facet section.
     # Verify pagination updated as per current product count and defaulted to first page

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify filtering products with single price facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet value
    And I verify that products are filtered as per selected facet value
    Examples:
    | shopping_mode | keyword |
    | Domestic      | Jeans   |
    | Registry      | plates  |
  # Notes:
  # Select facet value from price facet section.
  # Verify all products are displayed only withing selected price range only
  #  (Ex: if we select $50-$100 price facet value, then all product prices should be within this range)
  # Verify selected price facet value displayed in facet breadcrumb section.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify 'X' icon displayed for price facet section.
  # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify filtering products with multiple price facets
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | dinning  |
 # Notes:
 # Select facet values from price facet section.
 # Verify all products are displayed only withing selected price range only
 # (Ex: if we select $50-$100, $500 & Above price facet values, then product prices should be in any of two price facet value range)
 # Verify all selected price facet values displayed in facet breadcrumb section.
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify 'X' icon displayed for price facet section.
 # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify filtering products with custom price range facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | knife   |
    # Notes:
    # Enter minimum abd max price range values and select 'GO' button
    # Verify all products are displayed only withing entered price range only
    # (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
    # Verify entered price range value displayed in facet breadcrumb section.
    # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
    # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
    # Verify 'X' icon displayed for price facet section.
    # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify selected price facets are clearing upon selection of custom price facet value
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet value
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | knife   |
 # Notes:
 # Enter minimum abd max price range values and select 'GO' button
 # Verify all products are displayed only withing entered price range only
 # (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
 # Verify entered price range value displayed in facet breadcrumb section.
 # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify 'X' icon displayed for price facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify all other pre-defined price facets are in de-selected mode.


  @domain_discovery @priority_high @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify custom price facet is clearing upon selection of pre-defined price facet value
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select minimum price as "10" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet value
    And I verify that only pre-defined price facet is selected from price facet section
    Examples:
      | shopping_mode | keyword |
      | Domestic      | coats   |
      | Registry      | dinning |
 # Notes:
 # Enter minimum abd max price range values and select 'GO' button
 # Verify all products are displayed only withing entered price range only
 # (Ex: if we enter $10-$500 price range values, then product prices should be in same range)
 # Verify entered price range value displayed in facet breadcrum section.
 # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
 # Verify 'CLEAR ALL' button displaying on top of face section(beside 'filter by' header).
 # Verify 'X' icon displayed for price facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify custom price facet are not exists in price facet section.

  @domain_discovery @priority_high @mode_domestic @mode_registry @more_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filter products when we select multiple facet values
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    Examples:
      |shopping_mode|keyword    |
      |Domestic     |Jeans      |
      |Registry     |Plates     |
      |Iship        |Jeans      |
    # Notes:
    # Select facet value from any section other than Pick-up InStore(BOPS), Size(GROUPED FACET) facet sections.
    # Verify product count is updated on top of thumbnail grid.
    # Verify all selected facet values displayed in facet breadcrumb section.
    # Verify product count in thumbnail grid matched with selected item count option or total product count.
    # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
    # Verify 'X' icon displayed for all respective facet sections.
    # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry -Verify filtering products with size facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode | keyword   |
      | Domestic      | pink dress|
      | Registry      | pant      |
      | Iship         | pink dress|
 # Notes:
 # Select multiple facet values from size facet section
 # Verify 'CLEAR ALL' button displaying on top of facet
 #section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values
 # Verify 'X' icon displayed for size facet section.
 # Verify pagination updated as per current product count and
 #defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify all facet sections are displayed as per service response
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that facet section displaying as per service response in "<shopping_mode>" for "<keyword>"
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Jeans       |
      | Registry      | Plates      |
      | Iship         | Jeans       |
    # Notes:  # Verify all facet sections displayed as per below response.
 #  http://11.168.104.187:85/api/catalog/v2/categories/29391/ products? _offset=1&_limit=40&sdpGrid=primary&returnNavigationProductPool=true&r eturnFacets=true&_deviceType=DESKTOP&_shoppingMode=SITE&_regionCode=US &_application=SITE&_navigationType=BROWSE
 # Verify '<displayName>Special Offers</displayName>' attribute value as facet section name.
 # Verify each facet value and its product count under each facet section as per below sample response.
 #  "<values> <productCount>1961</productCount> <value>Sales & Discounts</value> </values>"

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify all facet sections are expanded or collapsed as per service response
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that facet section expanded/collapsed as per service response in "<shopping_mode>" for "<keyword>"
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Jeans       |
      | Iship         | Jeans       |
      | Registry      | Plates      |
  # Notes:
  # Verify facet section is expanded when we receive "<collapsed>false</collapsed>" from service.
  # Verify facet section is collapsed when we receive "<collapsed>true</collapsed>" from service.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet section 'filter by' header text
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    Then I verify that facet section displaying with 'filter by' header text
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Jeans       |
      | Registry      | Plates      |
      | Iship         | Jeans       |
  # Notes:
  # Verify facet section is displaying with 'filter by' header text

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filtering products with customer top rated facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "customer top rated" facet section
    Then I verify that products are filtered as per selected facet values
    Examples:
      |shopping_mode|keyword    |
      |Domestic     |Jeans      |
      |Registry     |Plates     |
      |Iship        |Jeans      |
 # Notes:
 # Select multiple facet values from customer top rated facet section
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values
 # Verify 'X' icon displayed for customer top rated facet section.
 # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filtering products with color facet
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    And I select multiple facet value from "Color" facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode | keyword     |
      | Domestic      | Dress       |
      | Registry      | Plates      |
      | Iship         | Dress       |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify canonical tags after multiple facet selections
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    And I verify that resulting url with all selected facet values
    And I verify that canonical tag contains facet value of same facet
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    And I verify that resulting url with all selected facet values
    And I verify that facet values in canonical tag with alpha order for "<search_text>"
    Examples:
      | shopping_mode |search_text|
      | Domestic      |jeans      |
      | Registry      |Plates     |
      | Iship         |dresses    |
      # Notes:
      # Verify canonical tags after multiple facet selections

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify unavailable facet values are removed after selecting any facet
    Given I am on SearchResultsPage for "<search_text>" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that remaining facets are updated its facet values and product count availability
    Examples:
      | shopping_mode |search_text|
      | Domestic      |jeans      |
      | Registry      |Plates     |
      | Iship         |dresses    |

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify products are filtered from each facet section
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 'single' facet value from 'each' facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |
    # Notes:
 # Select facet value from each of the facet sections.
 # Verify product count is updated on top of thumbnail grid.
 # Verify breadcrumb section displayed with single facet value.
 # Verify product count in thumbnail grid matched with selected facet value product count.
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify 'X' icon displayed for respective facet section.
 # Verify pagination updated as per current product count and defaulted to first page.


  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify products are filtered for faceted category
    Given I am on SearchResultsPage for "jeans" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I search for "anklet"
    Then I verify that facet breadcrumb are listed as per faceted url
    And I verify that products are filtered as per faceted url
    Examples:
      |shopping_mode |
      | Domestic     |
      | Iship        |
      #| Registry     |
      # Notes:
      # Verify products are filtered for faceted category

  @domain_discovery @priority_high @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify facet selection persistence with 'pagination' selection
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    When I select 'random' page number from pagination
    Then I verify that products are filtered as per selected facet values
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that previous pagination selection persist
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      | Registry      |
 # Notes:
 # Verify facet selection persists when we navigate back from PDP page

   #Testlink-MCOM-65793
  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verification for no 404 Errors and no 2nd call requests
    Given I am on SearchResultsPage for "jeans" in domestic mode
    And I verify that 404 error code is not displayed in search page
#    And I should not see second call request
# Need to identify how to get all the network elements to verify whether 2nd call is not being made or not

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario: SearchResultsPage - Domestic - Verify Content in Domestic mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that Search Results contents are displayed
    And I verify that 404 error code is not displayed in search page
    #Notes:
    #Verify you see results that match the keyword
    #Verify all images and links all return 200 OK on http party
    #Verify leftnav is displayed, sort by and next pages are displayed

    # @13g
  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
  Scenario Outline: SearchResultsPage - Domestic - Verify Three levels of pricing on thumbnail & Badge/Offer text display for product with respective price type for Member / Master product
    Given I am on SearchResultsPage for "<Keyword>" in Domestic mode
    And I verify that "<type>" products are displayed with two levels of pricing information
    And I verify that "<type>" products are displayed with price type with badge text
    Examples:
      | Keyword              | type   |
      | slim leg             | Member |
      | close out collection | Master |


  @domain_discovery @priority_high @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario: SearchResultsPage - Registry - Verify bops facet selection persistence with browser back button
    Given I am on SearchResultsPage for "electrics" in Registry mode
    When I add "MISCGCs" zipcode cookie from cookie list
    And I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I verify that zipcode is displayed based on store cookie value under bops facet
    When I select "100 miles" Miles under bops facet
    Then I verify that store values are displayed under bops facet
    When I select random bops facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I select 'single' facet value from 'any' facet section
    # for ex: color facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    Then I verify that landed on SearchResultsPage on same product grid point
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
  # Notes:
  # Select single facet value from pick-up in-store facet section
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for pick-up in-store facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Verify MISCGCs is updated with zipcode specific langitude and latitude values.
  # Select any product and verify its navigated to respective PDP page or not.
  # Verify selected facets persistence when we navigate back from PDP.

  @domain_discovery @priority_high @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario: SearchResultsPage - Registry - Verify bops facet selection persistence after making any other action on page
    Given I am on SearchResultsPage for "electrics" in Registry mode
    When I add "MISCGCs" zipcode cookie from cookie list
    And I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I verify that zipcode is displayed based on store cookie value under bops facet
    When I select "100 miles" Miles under bops facet
    Then I verify that store values are displayed under bops facet
    When I select random bops facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I select "single" facet value from "Color" facet section
    # for ex: color facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I select "New Arrivals" in sort by drop down
    Then I verify that previously selected facets persists in breadcrumb
  # Notes:
  # Select single facet value from pick-up in-store facet section
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for pick-up in-store facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Verify MISCGCs is updated with zipcode specific langitude and latitude values.
  # Select any other facet and select any sort by option.
  # Verify selected bops facet persistence after making facet and sort by actions.

  @domain_discovery @priority_high @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario: SearchResultsPage - Registry - Verify single store facet filter only possible on page
    Given I am on SearchResultsPage for "electrics" in Registry mode
    When I add "MISCGCs" zipcode cookie from cookie list
    And I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I verify that zipcode is displayed based on store cookie value under bops facet
    When I select "100 miles" Miles under bops facet
    Then I verify that store values are displayed under bops facet
    When I select random bops facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I select another facet value from "Free Pick Up In Store" facet
    Then I verify that previous store facet removed and applied new store facet
    Then I verify that previously selected facets persists in breadcrumb
  # Notes:
  # Select single facet values from pick-up in-store facet section
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for pick-up in-store facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Select another facet values from pick-up in-store facet section
  # Verify previously selected facet value removed and applied new facet for thumbnail grid.
  # Verify facet breadcrumb, 'CLEAR ALL', 'X' icon & pagination again
  # Verify MISCGCs is updated with zipcode specific langitude and latitude values