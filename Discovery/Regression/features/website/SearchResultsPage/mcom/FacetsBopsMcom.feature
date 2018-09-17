Feature: Facet BOPS verification on Search Landing Page

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic - Verify BOPS Pick Up In-Store facet is displayed
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I "should" see BOPS store facet tab
    When I select BOPS store tab view for thumbnail grid
    Then I should see products are updated from all products tab to BOPS production tab
    Examples:
      | shopping_mode |
      | Domestic      |

  ##Removing below scenarios from regression due to TAB view change
  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify store search on bops overlay and single store selection
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
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
#      | Domestic      |
      | Registry      |
 # Notes:
 # Select multiple facet values from pick-up in-store facet section
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values  # Verify 'X' icon displayed for pick-up in-store facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify MISCGCs is updated with zipcode specific langitude and latitude values

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify store search on bops overlay and multiple store selection
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
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
#      | Domestic      |
      | Registry      |

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify miles drop down in bops facet section
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
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
#      | Domestic      |
      | Registry      |

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify filtering products with bops(pick-up in-store) facet
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
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
#      | Domestic      |
      | Registry      |

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify only single facet selection applicable for bops(pick-up instore) facet section
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
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
#      | Domestic      |
      | Registry      |
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

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS and other facets are displayed
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that other facets excluding BOPS are displayed
    When I navigate to Browse page with "Free Pick Up In Store" facet
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that other facets excluding BOPS are displayed
    Examples:
      | shopping_mode | keyword     |
#      | Domestic      | jeans       |
      | Registry      | Plates      |

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS and other facets are displayed
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that other facets excluding BOPS are displayed
    When I navigate to Browse page with "Free Pick Up In Store" facet
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that other facets excluding BOPS are displayed
    Examples:
      | shopping_mode | keyword     |
#      | Domestic      | jeans       |
      | Registry      | Plates      |


#  @domain_discovery @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @xbrowser_search
#  Scenario: SearchResultsPage - Domestic - Verify result set updated on clearing values in breadcrumb in search page (store display based on store cookie value)
#    Given I am on SearchResultsPage for "plates" in Domestic mode
#    When I add "MISCGCs" zipcode cookie from cookie list
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    And I verify that the product count is displayed
#    And I verify that zipcode is displayed based on store cookie value under bops facet
#    When I select "100 miles" Miles under bops facet
#    Then I verify that store values are displayed under bops facet
#    When I select random bops facet value
#    Then I verify that previously selected facets persists in breadcrumb
#    When I select 'single' facet value from 'any' facet section
#    # for ex: color facet value
#    Then I verify that previously selected facets persists in breadcrumb
#    And I verify that the product count is updated
#    When I clear store facet from breadcrumb
#    Then I verify that the product count is updated
#    When I remove the selected facet from the breadcrumb
#    Then I verify that the product count is updated
#
#    #Testlink-MCOM-96176 Vone - RT-07513
#  @domain_discovery @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify result set updated on clearing values in breadcrumb in search page (store display based on entered zipcode)
#    Given I am on SearchResultsPage for "plates" in Domestic mode
#    When I remove "MISCGCs" zipcode cookie from cookie list
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    And I verify that the product count is displayed
#    And I search for zipcode "10021" in bops facet
#    When I select "100 miles" Miles under bops facet
#    Then I verify that store values are displayed under bops facet
#    When I select random bops facet value
#    Then I verify that previously selected facets persists in breadcrumb
#    When I select 'single' facet value from 'any' facet section
#    # for ex: color facet value10021
#    Then I verify that previously selected facets persists in breadcrumb
#    And I verify that the product count is updated
#    When I clear store facet from breadcrumb
#    Then I verify that the product count is updated
#    When I remove the selected facet from the breadcrumb
#    Then I verify that the product count is updated

     #Testlink-MCOM-96176 Vone - RT-07513
  @domain_discovery @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario: SearchResultsPage - Registry - Verify result set updated on clearing values in breadcrumb in search page (store display based on entered zipcode)
    Given I am on SearchResultsPage for "dinning" in Registry mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I verify that "Free Pick Up In Store" facet is listed on left nav
    And I verify that the product count is displayed
    And I search for zipcode "10021" in bops facet
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

#  @domain_discovery @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify error message display when blank data ("GO" button click) entered in search results page
#    Given I am on SearchResultsPage for "electrics" in Domestic mode
#    When I remove "MISCGCs" zipcode cookie from cookie list
#    Then I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I search for zipcode "" in Pick Up In Store facet
#    Then I verify that "You must enter a valid Zip Code" error message is displayed under bops facet
#
#  @domain_discovery @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario Outline: SearchResultsPage - Domestic - Verify User enters invalid data in search field
#    Given I am on SearchResultsPage for "electrics" in Domestic mode
#    When I remove "MISCGCs" zipcode cookie from cookie list
#    Then I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I search for zipcode "<Zip_code>" in Pick Up In Store facet
#    Then I verify that "You must enter a valid Zip Code" error message is displayed under bops facet
#    Examples:
#      |Zip_code |
#      | 0b0s0   |
      | 1b1s1   |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS facet is displayed (store not known), if there are 5000 or less than 5000 products in search results page. Search for keyword black that has more than 5000 products in DOMESTIC|REGISTRY mode
    Given I am on SearchResultsPage for "black" in <shopping_mode> mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    When I verify that the product count is "more" than "5000"
    Then I verify that "Free Pick Up In Store" facet is not listed on left nav
    Examples:
      | shopping_mode |
#      | Domestic      |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS facet is displayed (store known), if there are 5000 or less than 5000 products in search results page. Search for keyword black that has more than 5000 products in DOMESTIC|REGISTRY mode
    Given I am on SearchResultsPage for "black" in <shopping_mode> mode
    When I add "MISCGCs" zipcode cookie from cookie list
    When I verify that the product count is "more" than "5000"
    Then I verify that "Free Pick Up In Store" facet is not listed on left nav
    Examples:
      | shopping_mode |
#      | Domestic      |
      | Registry      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS facet is displayed (store not known), if there are more than 5000 products  products after filter selected any facet in search results page. Search for keyword black that has more than 5000 products in REGISTRY mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    Then I should be in Search Landing page
    And I verify that the product count is "more" than "5000"
    And I verify that "Free Pick Up In Store" facet is not listed on left nav
    When I select 'single' facet value from 'any' facet section
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    Examples:
      | keyword | shopping_mode |
      | dinning | registry      |
#      | dress   | domestic      |

  @domain_discovery @priority_medium @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS facet is displayed (store known), if there are 5000 or less than 5000 products  products after filter selected any facet in search results page
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I add "MISCGCs" zipcode cookie from cookie list
    Then I should be in Search Landing page
    And I verify that the product count is "more" than "5000"
    And I verify that "Free Pick Up In Store" facet is not listed on left nav
    When I select 'single' facet value from 'any' facet section
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    Examples:
      | keyword | shopping_mode |
      | dinning | registry      |
#      | black   | domestic      |

#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify stores display within 10 miles radius ("Enter" key press in keyboard) based on entered zipcode in search results page
#    Given I am on SearchResultsPage for "jeans" in domestic mode
#    And I remove "MISCGCs" zipcode cookie from cookie list
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I search for zipcode "10021" in bops facet
#    Then I verify that "10 miles" is displayed in the radius dropdown under bops facet
#    And I verify that stores are displayed in pick-up in-store facet section
#    And I verify that zipcode hyperlink is displayed under bops facet
#
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify error message display when blank data ("GO" button click) entered in search results page
#    Given I am on SearchResultsPage for "electrics" in Domestic mode
#    When I remove "MISCGCs" zipcode cookie from cookie list
#    Then I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I search for zipcode "" in Pick Up In Store facet
#    Then I verify that "You must enter a valid Zip Code" error message is displayed under bops facet
#
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario:  SearchResultsPage - Domestic - Verify store overlay displays list of stores on clicking city name link
#    Given I am on SearchResultsPage for "jeans" in Domestic mode
#    And I add "MISCGCs" zipcode cookie from cookie list
#    Then I verify that "Free Pick Up In Store" facet is listed on left nav
#    And I verify that zipcode based on store cookie value under bops facet
#    When I select zipcode link in pick-up in-store facet section
#    Then I verify that bops overlay is displayed
#    And I verify that bops overlay with map and stores list
#    When I search stores for valid city and state within "100 miles" miles in ISA overlay
#    And I select x icon on bops change store dialog
#    Then I verify that bops overlay is not displayed with stores list
#    When I verify that city name hyperlink is displayed under bops facet
#    And I select city name link in pick-up in-store facet section
#    Then I verify that bops overlay is displayed
#    And I verify that bops overlay with map and stores list
#
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario:  SearchResultsPage - Domestic - Verify store overlay displays error message for no participating BOPS stores on clicking city name link
#    Given I am on SearchResultsPage for "jeans" in Domestic mode
#    And I add "MISCGCs" zipcode cookie from cookie list
#    Then I verify that "Free Pick Up In Store" facet is listed on left nav
#    And I verify that zipcode based on store cookie value under bops facet
#    When I select zipcode link in pick-up in-store facet section
#    Then I verify that bops overlay is displayed
#    And I verify that bops overlay with map and stores list
#    When I search stores for valid city and state within "100 miles" miles in ISA overlay
#    And I click on save button in change pick-up in-store overlay
#    Then I verify that bops overlay is not displayed with stores list
#    When I verify that city name hyperlink is displayed under bops facet
#    When I select city name link in pick-up in-store facet section
#    Then I verify that bops overlay is displayed
#    When I search for zipcode "840375" in bops facet overlay
#    Then I verify that "No stores were found near your entered location. Please increase your search area or try a different location." error message is displayed under bops change store dialog
#
#     #Testlink-MCOM-96176 Vone - RT-07513
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify the system refines product count based on the selected facet type for the selected store(store display based on entered zipcode)
#    Given I am on SearchResultsPage for "plates" in Domestic mode
#    When I remove "MISCGCs" zipcode cookie from cookie list
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    And I verify that Enter Zip Code by default in search box under bops facet
#    When I search for zipcode "10021" in bops facet
#    And I select "100 miles" Miles under bops facet
#    Then I verify that stores are displayed in pick-up in-store facet section
#    When I select any random bops facet value
#    Then I verify that applied facet value is displayed
#    When I select another facet value from "Free Pick Up In Store" facet
#    Then I verify that previous store facet removed and applied new store facet
#    Then I verify that applied facet value is displayed
#    When I select 'single' facet value from 'any' facet section
#    # for ex: color facet value
#    Then I verify that products are filtered as per selected facet values
#
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify listing store location information comes from SDP store Service when the dynamic kill switch google_maps_enabled set to true
#    Given I am on SearchResultsPage for "jeans" in Domestic mode
#    And I add "MISCGCs" zipcode cookie from cookie list
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I select zipcode link in pick-up in-store facet section
#    Then I verify that bops overlay is displayed
#    And I verify that bops overlay with map and stores list
#    When I search for zipcode "10021" in bops facet overlay
#    And I click on save button in change pick-up in-store overlay
#    Then I verify that "Pick Up In Store" facet is listed on left nav
#
#      # Prerequisite: Enable google_maps_enabled killswitch and simulate situation that Call to GME from BOPS overlay fails
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario:  SearchResultsPage - Domestic - Verify error message when Call to GME from BOPS overlay fails
#    Given I am on SearchResultsPage for "jeans" in Domestic mode
#    And I add "MISCGCs" zipcode cookie from cookie list
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I select zipcode link in pick-up in-store facet section
#    Then I verify that bops overlay is displayed
#    And I verify that bops overlay with map and stores list
#    When I search for zipcode "10481" in bops facet overlay
#    Then I verify that "No stores were found near your entered location. Please increase your search area or try a different location." error message is displayed under bops change store dialog
#    When I search for city "canvass" in bops facet overlay
#    Then I verify that "No stores were found near your entered location. Please increase your search area or try a different location." error message is displayed under bops change store dialog

  @domain_discovery @priority_medium @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline:  SearchResultsPage - Iship - Verify BOPS facet is not displayed in iship mode and it is displayed with zipcode based on cookie when switch back in DOMESTIC mode
    Given I visit the web site as a guest user
    When I add "MISCGCs" zipcode cookie from cookie list
    And I navigate to international context page
    And I change country to "<Country>"
    And I close the welcome mat if it's visible
    When I search for "jeans"
    Then I should be in Search Landing page
    Then I "should not" see BOPS store facet tab
    When I go to US site
    And I search for "jeans"
    Then I should be in Search Landing page
    Then I "should" see BOPS store facet tab
    Examples:
      | Country |
      | India   |
      | Canada  |

  @domain_discovery @priority_medium @mode_iship @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page @wip @retired_bops
  Scenario Outline: SearchResultsPage - Iship - Verify BOPS facet is not displayed in iship mode and it is displayed with zip text box (when cookie not set) when switch back
    Given I visit the web site as a guest user
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I navigate to international context page
    And I change country to "<Country>"
    And I close the welcome mat if it's visible
    And I search for "jeans"
    Then I should be in Search Landing page
    Then I "should not" see BOPS store facet tab
    When I go to US site
    And I search for "jeans"
    Then I should be in Search Landing page
    Then I "should" see BOPS store facet tab
    Examples:
      | Country |
      | India   |
      | Canada  |

#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario Outline: SearchResultsPage - Domestic - Verify user enters invalid data in search field
#    Given I am on SearchResultsPage for "jeans" in Domestic mode
#    And I remove "MISCGCs" zipcode cookie from cookie list
#    When I search for zipcode "<zipcode>" in bops facet
#    Then I verify that "You must enter a valid Zip Code" error message is displayed under bops facet
#    Examples:
#      | zipcode |
#      | 0b0s0   |
#      | 1b1s1   |
#
#    #Testlink-MCOM-96176 Vone - RT-07513
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify bops facet value (store display based on store cookie value) in breadcrumb is displayed in order of when the facet was selected among other facet selection in search page
#    Given I am on SearchResultsPage for "jeans" in Domestic mode
#    When I add "MISCGCs" zipcode cookie from cookie list
#    Then I should be in Search Landing page
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I select "100 miles" Miles under bops facet
#    Then I verify that store values are displayed under bops facet
#    When I select any random bops facet value
#    When I select 'single' facet value from 'any' facet section
#  # other than bops facet items. for ex: color facet value
#    Then I verify that previously selected facets persists in breadcrumb
#    When I select another facet value from "Free Pick Up In Store" facet
#    Then I verify that previously selected facets persists in breadcrumb
#
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify store overlay displays list of stores on clicking zipcode hyperlink which is displayed based on store cookie value
#    Given I am on SearchResultsPage for "jeans" in Domestic mode
#    And I add "MISCGCs" zipcode cookie from cookie list
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    And I verify that zipcode based on store cookie value under bops facet
#    When I select zipcode link in pick-up in-store facet section
#    Then I verify that bops overlay is displayed
#    And I verify that bops overlay with map and stores list
#
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify BOPS Facet in "CHANEL" Page
#    Given I visit the web site as a guest user
#    And I add "MISCGCs" zipcode cookie from cookie list
#    And I navigate to the "CHANEL" browse page under "BEAUTY"
#    And I select "MAKEUP" in the subsplash page
#    And I select "LIPS" facet listed on left nav
#    Then I verify that "Free Pick Up In Store" facet is not listed on left nav
#
#  @domain_discovery @priority_medium @mode_domestic @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario: SearchResultsPage - Domestic - Verify USERPC and USERLL are updated based on the zipcode in MISCGCs cookie from BOPS facet overlay
#    Given I am on SearchResultsPage for "flats" in Domestic mode
#    And I remove "MISCGCs" zipcode cookie from cookie list
#    Then I should be in Search Landing page
#    And I verify that "Free Pick Up In Store" facet is listed on left nav
#    When I search for zipcode "10022" in bops facet
#    Then I verify that USERPC and USERLL are updated with "10022" values in MISCGCs cookie
#    And I verify that stores are displayed in pick-up in-store facet section
#
#  @domain_discovery @priority_medium @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS facet is not displayed (store known), if there are more than 5000 products in search results page.
#    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
#    When I add "MISCGCs" zipcode cookie from cookie list
#    When I verify that the product count is "less" than "5000"
#    Then I verify that "Free Pick Up In Store" facet is listed on left nav
#    Examples:
#      | keyword | shopping_mode |
#      | jeans   | domestic      |
#      | plates  | registry      |
#
#  @domain_discovery @priority_medium @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @feature_search_results_page
#  Scenario Outline: SearchResultsPage - Domestic|Registry - Verify BOPS facet is not displayed (store not known), if there are more than 5000 products in search results page.
#    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
#    When I remove "MISCGCs" zipcode cookie from cookie list
#    When I verify that the product count is "less" than "5000"
#    Then I verify that "Free Pick Up In Store" facet is listed on left nav
#    Examples:
#      | keyword | shopping_mode |
#      | jeans   | domestic      |
#      | plates  | registry      |
