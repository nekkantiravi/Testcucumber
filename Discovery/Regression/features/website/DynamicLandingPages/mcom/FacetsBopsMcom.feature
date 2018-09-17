Feature: Facet BOPS verification on DynamicLanding Page

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @wip
  Scenario Outline: DynamicLandingPage - Domestic|Registry - Verify store search on bops overlay and single store selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with bops facet
    When I add "MISCGCs" zipcode cookie from cookie list
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "10021" in bops facet overlay
    And I click on save button in change pick-up in-store overlay
    Then I verify that USERPC and USERLL are updated with "10021" values in MISCGCs cookie
    And I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    And I verify that applied facet value is displayed
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
 # Notes:
 # Select multiple facet values from pick-up in-store facet section
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values  # Verify 'X' icon displayed for pick-up in-store facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify MISCGCs is updated with zipcode specific langitude and latitude values

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @wip
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify store search on bops overlay and multiple store selection
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with bops facet
    When I add "MISCGCs" zipcode cookie from cookie list
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    And I verify that bops overlay with map and stores list
    When I search for zipcode "10021" in bops facet overlay
    And I click on save button in change pick-up in-store overlay
    Then I verify that USERPC and USERLL are updated with "10021" values in MISCGCs cookie
    And I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    And I verify that applied facet value is displayed
    When I select another facet value from "Free Pick Up In Store" facet
    Then I verify that previous store facet removed and applied new store facet
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @xbrowser_dlp @wip
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify miles drop down in bops facet section
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with bops facet
    When I add "MISCGCs" zipcode cookie from cookie list
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "10021" in bops facet overlay
    And I click on save button in change pick-up in-store overlay
    Then I verify that USERPC and USERLL are updated with "10021" values in MISCGCs cookie
    And I verify that stores are displayed in pick-up in-store facet section
    And I verify that miles drop down with below option:
      | 5 miles |
      | 10 miles |
      | 25 miles |
      | 50 miles |
      | 100 miles |
    When I select random option from miles drop down
    Then I verify that store facet values are updated with selected mile range
    When I select random bops facet value
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @wip
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify filtering products with bops(pick-up in-store) facet
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with bops facet
    When I remove "MISCGCs" zipcode cookie from cookie list
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    When I search for zipcode "10021" in bops facet
    And I select "100 miles" Miles under bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @wip
  Scenario Outline: DynamicLandingPage - Domestic|Iship|Registry - Verify only single facet selection applicable for bops(pick-up instore) facet section
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with bops facet
    When I remove "MISCGCs" zipcode cookie from cookie list
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    When I search for zipcode "10021" in bops facet
    And I select "100 miles" Miles under bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    And I verify that applied facet value is displayed
    When I select another facet value from "Free Pick Up In Store" facet
    Then I verify that previous store facet removed and applied new store facet
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic      |
 # Notes:
 # Remove existing bops zipcode cookie to create cookie with new zipcode.
  # Select single facet values from pick-up in-store facet section
  # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
  # Verify products are displayed as per selected facet values
  # Verify 'X' icon displayed for pick-up in-store facet section.
  # Verify pagination updated as per current product count and defaulted to first page.
  # Select another facet values from pick-up in-store facet section
  # Verify previously selected facet value removed and applied new facet for thumbnail grid.
  # Verify facet breadcrumb, 'CLEAR ALL', 'X' icon & pagination again

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @wip
  Scenario: DynamicLandingPage - Domestic - Verify result set updated on clearing values in breadcrumb in dlp page(store display based on store cookie value)
    Given I am on DynamicLandingPage in "Domestic" mode with bops facet
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
    And I verify that the product count is updated
    When I clear store facet from breadcrumb
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count is updated

    #Testlink-MCOM-96176 Vone - RT-07513
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page @wip
  Scenario: DynamicLandingPage - Domestic - Verify result set updated on clearing values in breadcrumb in dlp page (store display based on entered zipcode)
    Given I am on DynamicLandingPage in "Domestic" mode with bops facet
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I search for zipcode "10021" in bops facet
    When I select "100 miles" Miles under bops facet
    Then I verify that store values are displayed under bops facet
    When I select random bops facet value
    Then I verify that previously selected facets persists in breadcrumb
    When I select 'single' facet value from 'any' facet section
    # for ex: color facet value10021
    Then I verify that previously selected facets persists in breadcrumb
    And I verify that the product count is updated
    When I clear store facet from breadcrumb
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count is updated
