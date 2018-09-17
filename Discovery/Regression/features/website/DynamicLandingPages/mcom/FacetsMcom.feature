Feature: Verifying Facets in DynamicLanding Page

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify filter products when we select any one random facet value
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
     # Notes:
     # Select facet value from any section other than Pick-up InStore(BOPS), Size(GROUPED FACET) facet sections.
     # Verify product count is updated on top of thumbnail grid.
     # Verify breadcrumb section displayed with single facet value.
     # Verify product count in thumbnail grid matched with selected facet value product count.
     # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
     # Verify 'X' icon displayed for respective facet section.
     # Verify pagination updated as per current product count and defaulted to first page

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify filtering products with single price facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select "single" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet value
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
  # Notes:
  # Select facet value from price facet section.
  # Verify all products are displayed only withing selected price range only
  #  (Ex: if we select $50-$100 price facet value, then all product prices should be within this range)
  # Verify selected price facet value displayed in facet breadcrumb section.
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify 'X' icon displayed for price facet section.
  # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp
  Scenario Outline: DynamicLandingPage - Domestic - Verify filtering products with multiple price facets
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode |
      | Domestic      |
 # Notes:
 # Select facet values from price facet section.
 # Verify all products are displayed only withing selected price range only
 # (Ex: if we select $50-$100, $500 & Above price facet values, then product prices should be in any of two price facet value range)
 # Verify all selected price facet values displayed in facet breadcrumb section.
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify 'X' icon displayed for price facet section.
 # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic - Verify filtering products with custom price range facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
    # Notes:
    # Enter minimum abd max price range values and select 'GO' button
    # Verify all products are displayed only withing entered price range only
    # (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
    # Verify entered price range value displayed in facet breadcrumb section.
    # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
    # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
    # Verify 'X' icon displayed for price facet section.
    # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Registry - Verify selected price facets are clearing upon selection of custom price facet value
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet value
    When I select minimum price as "150" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
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


  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @discovery_daily_run
  Scenario Outline: DynamicLandingPage - Domestic|Registry - Verify custom price facet is clearing upon selection of pre-defined price facet value
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select minimum price as "10" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I clear existing class variable data to avoid data issues
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet value
    And I verify that only pre-defined price facet is selected from price facet section
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
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

  @domain_discovery @priority_high @mode_domestic @more_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify filter products when we select multiple facet values
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    And I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
    # Notes:
    # Select facet value from any section other than Pick-up InStore(BOPS), Size(GROUPED FACET) facet sections.
    # Verify product count is updated on top of thumbnail grid.
    # Verify all selected facet values displayed in facet breadcrumb section.
    # Verify product count in thumbnail grid matched with selected item count option or total product count.
    # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
    # Verify 'X' icon displayed for all respective facet sections.
    # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify filtering products with size facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode |
      | Domestic      |
 # Notes:
 # Select multiple facet values from size facet section
 # Verify 'CLEAR ALL' button displaying on top of facet
 #section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values
 # Verify 'X' icon displayed for size facet section.
 # Verify pagination updated as per current product count and
 #defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Iship - Verify filtering products with size facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with size facet
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "Size" facet section
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode |
      | Iship         |
 # Notes:
 # Select multiple facet values from size facet section
 # Verify 'CLEAR ALL' button displaying on top of facet
 #section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values
 # Verify 'X' icon displayed for size facet section.
 # Verify pagination updated as per current product count and
 #defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify facet section 'filter by' header text
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    Then I verify that facet section displaying with 'filter by' header text
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
  # Notes:
  # Verify facet section is displaying with 'filter by' header text

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify filtering products with customer top rated facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select multiple facet value from "customer top rated" facet section
    Then I verify that products are filtered as per selected facet values
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
 # Notes:
 # Select multiple facet values from customer top rated facet section
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values
 # Verify 'X' icon displayed for customer top rated facet section.
 # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify filtering products with color facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with color facet
    And I clear existing class variable data to avoid data issues
    And I select multiple facet value from "Color" facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify canonical tags after multiple facet selections
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that products are filtered as per selected facet value
    And I verify that resulting url with all selected facet values
    And I verify that canonical tag contains facet value of same facet
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
      # Notes:
      # Verify canonical tags after multiple facet selections

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify unavailable facet values are removed after selecting any facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I select 'multiple' facet value from 'any' facet sections
    Then I verify that remaining facets are updated its facet values and product count availability
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @priority_high @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify products are filtered from each facet section
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    When I select 'single' facet value from 'each' facet section
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |
    # Notes:
 # Select facet value from each of the facet sections.
 # Verify product count is updated on top of thumbnail grid.
 # Verify breadcrumb section displayed with single facet value.
 # Verify product count in thumbnail grid matched with selected facet value product count.
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify 'X' icon displayed for respective facet section.
 # Verify pagination updated as per current product count and defaulted to first page.

  @domain_discovery @priority_medium @mode_domestic @mode_iship @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify user can expand and collapse the same facet multiple times on dlp page
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    When I verify that facets are listed on left nav
    And I click on any "expanded" facet
    Then I verify that the selected facet is "collapsed"
    When I click on the same "collapsed" facet
    Then I verify that the selected facet is "expanded"
    When I click on the same "expanded" facet
    Then I verify that the selected facet is "collapsed"
    Examples:
      | shopping_mode |
      | Domestic      |
      | Iship         |

  @domain_discovery @mode_domestic @mode_registry @mode_iship @snbc_comp @use_regression @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic|Iship - Verify special characters facet selection persistence
    Given I visit the web site as a guest user in "<mode>" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to the bookmarked url:
      | MCOM | <url> |
    When I select "<facet_value>" facet value from '<facet_name>' facet section
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    And I navigate to top of page
    Then I verify that previously selected facets persists in breadcrumb
    Examples:
      | mode     | url                               | facet_value | facet_name  |
      | iship    | /shop/featured/womens-boots       | Low 1-2     | Heel Height |
      | domestic | /shop/featured/womens-shoes       | UGG®        | Brand       |
      | registry | /shop/featured/plate?mode=wedding | Nambé       | Brand       |

