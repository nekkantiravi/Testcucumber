Feature: Facet BOPS verification on Search Landing Page

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify store search on bops overlay and single store selection
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I verify that "Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    And I verify that bops overlay is displayed
    When I search for zipcode "11030" in bops change store dialog
    And I click on save button in change pick-up in-store overlay
    Then I verify that USERPC and USERLL are updated with "11030" values in MISCGCs cookie
    And I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic |
      | Registry |
 # Notes:
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values  # Verify 'X' icon displayed for pick-up in-store facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify MISCGCs is updated with zipcode specific langitude and latitude values

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify store search on bops overlay and multiple store selection
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I verify that "Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "10021" in bops change store dialog
    And I click on save button in change pick-up in-store overlay
    Then I verify that USERPC and USERLL are updated with "10021" values in MISCGCs cookie
    When I select "100 miles" Miles under bops facet
    And I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    And I verify that applied facet value is displayed
    And I select another facet value from "Pick Up In Store" facet
    Then I verify that previous store facet removed and applied new store facet
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic |
      | Registry |
 # Notes:
 # Select multiple facet values from pick-up in-store facet section
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify products are displayed as per selected facet values  # Verify 'X' icon displayed for pick-up in-store facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify MISCGCs is updated with zipcode specific langitude and latitude values

  @domain_discovery @mode_domestic @mode_registry @snbc_comp @use_regression @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify miles drop down in bops facet section
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I verify that "Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "10021" in bops change store dialog
    And I click on save button in change pick-up in-store overlay
    Then I verify that USERPC and USERLL are updated with "10021" values in MISCGCs cookie
    And I verify that stores are displayed in pick-up in-store facet section
    And I verify that miles drop down with below option:
      | 5 miles  |
      | 10 miles |
      | 25 miles |
      | 50 miles |
      | 100 miles |
    When I select "100 miles" Miles under bops facet
    And I select random bops facet value
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic |
      | Registry |

  @domain_discovery @mode_domestic @mode_registry @use_regression @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify filtering products with bops(pick-up in-store) facet
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I remove "MISCGC's" zipcode cookie from cookie list
    Then I verify that "Pick Up In Store" facet is listed on left nav
    When I search for zipcode "10021" in bops facet
    And I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic |
      | Registry |

  @domain_discovery @mode_domestic @mode_registry @use_regression @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify only single facet selection applicable for bops(pick-up instore) facet section
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    When I remove "MISCGC's" zipcode cookie from cookie list
    Then I verify that "Pick Up In Store" facet is listed on left nav
    When I search for zipcode "10021" in bops facet
    And I verify that stores are displayed in pick-up in-store facet section
    When I select random bops facet value
    When I select another facet value from "Pick Up In Store" facet
    Then I verify that previous store facet removed and applied new store facet
    And I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | Domestic |
      | Registry |

  @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @snbc_comp @use_regression @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify that the BOPS eligibility/availability rules are applied for selected location and products
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    Then I verify that "25 miles" is displayed in the radius dropdown under bops facet
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search stores for valid zipcode within "10 miles" miles in ISA overlay
    And I save my location in ISA overlay
    Then I verify that bops overlay is not displayed with stores list
    When I search for "men jeans"
    And I modify the url to get in to snbc experiment
    Then I verify that "Pick Up In-Store" facet is listed on left nav
    And I verify that zipcode is displayed based on store cookie value under bops facet
    Then I verify that "10 miles" is displayed in the radius dropdown under bops facet

  @snbc_comp @use_regression @release_14A @gmm_bopsfacet @use_regression @artifact_navapp @domain_discovery @priority_high @disable_script @mode_domestic @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify that the user selected city/state given that the products meet BOPS eligibility/availability rules in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    Then I verify that "25 miles" is displayed in the radius dropdown under bops facet
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search stores for valid city and state within "10 miles" miles in ISA overlay
    And I save my location in ISA overlay
    Then I verify that bops overlay is not displayed with stores list
    When I search for "Dresses"
    And I modify the url to get in to snbc experiment
    And I verify that "Pick Up In-Store" facet is listed on left nav
    And I verify that city name hyperlink is displayed under bops facet
    Then I verify that "10 miles" is displayed in the radius dropdown under bops facet


  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @use_regression @mode_domestic @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify result set updated on clearing values in breadcrumb (store display based on store cookie value) in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    And I verify that bops overlay is displayed
    When I search for zipcode "11030" in bops change store dialog
    And I click on save button in change pick-up in-store overlay
    And I select "100 miles" Miles under bops facet
    When I select random bops facet value
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original

    #Testlink-BLCOM-83760 Vone - RT-06555
  @domain_discovery @use_regression @mode_domestic @wip
  Scenario: SearchResultsPage - Domestic - Verify result set updated on clearing values in breadcrumb (store display based on entered zipcode)in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I search for zipcode "10021" in bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    When I select "100 miles" Miles under bops facet
    And I select random bops facet value
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I select 'single' facet value from 'any' facet section
    And I verify that previously selected facets persists in breadcrumb
    Then I verify that products are filtered as per selected facet value
    When I remove the selected facet from the breadcrumb
    Then I verify that products are filtered as per selected facet value

  @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @mode_domestic @use_regression @wip
  Scenario Outline: SearchResultsPage - Domestic - Verify the system displays store facet values between 25 to 50 miles radius based on the entered zipcode in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    Then I verify that "Pick Up In-Store" facet is listed on left nav
    When I search for zipcode "10021" in bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    When I search for zipcode "10021" in bops facet
    When I select "50 miles" Miles under bops facet
    Then I verify that "50 miles" is displayed in the radius dropdown under bops facet
    And I verify that stores are displayed in pick-up in-store facet section
    Examples:
      |keyword|shopping_mode|
      |jeans  |domestic     |
      |Plates |REGISTRY     |

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @mode_domestic @use_regression @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify the system displays error message when there is no store within 50 miles radius based on the zipcode value stored in cookie in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    And I verify that bops overlay is displayed
    When I search for zipcode "00000" in bops change store dialog
    And I select "50 miles" Miles under bops change store dialog
    And I verify that default message "Unfortunately, in-store pickup is currently unavailable in your area." under bops facet

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt
  Scenario: SearchResultsPage - Domestic - Verify the system displays error message when there is no store within 50 miles radius based on the entered zipcode in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I verify that "Pick Up In-Store" facet is listed on left nav
    And I verify that Enter Zip Code by default in search box under bops facet
    When I search for zipcode "10021" in bops facet
    And I verify that default message "Unfortunately, in-store pickup is currently unavailable near." under bops facet

  @domain_discovery @use_regression @mode_domestic @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify the system refines product count based on the selected facet type for the selected store(store display based on entered zipcode) in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I verify that "Pick Up In-Store" facet is listed on left nav
    And I verify that Enter Zip Code by default in search box under bops facet
    When I search for zipcode "10022" in bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    When I select any bops facet value
    Then I verify that products are filtered as per selected facet value
    When I select another facet value from "Pick Up In Store" facet
    Then I verify that previous store facet removed and applied new store facet
    Then I verify that products are filtered as per selected facet value
    When I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @use_regression @mode_domestic @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify the stores are displayed based on the store cookie value even if they are inactive state
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "11030" in bops change store dialog
    And I click on save button in change pick-up in-store overlay
    When I select "100 miles" Miles under bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    And I verify that products with 0 count displayed is in disabled state
    Examples:
      |keyword|shopping_mode|
      |jeans  |domestic     |
      |Plates |REGISTRY     |

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @use_regression @mode_domestic @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify different radius selection include or exclude bops enabled stores that fall within the new radius of the users zip code when zipcode cookie is not available in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "10001" in bops change store dialog
    And I click on save button in change pick-up in-store overlay
    When I select "50 miles" Miles under bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    When I select "25 miles" Miles under bops facet
    Then I verify that stores are displayed in pick-up in-store facet section

  @domain_discovery @mode_domestic @migrated_to_sdt @use_regression @wip
  Scenario: SearchResultsPage - Domestic - Verify stores display within default 25 miles radius ("SEARCH" button click) based on entered zipcode in search results page in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I verify that "Pick Up In-Store" facet is listed on left nav
    And I verify that Enter Zip Code by default in search box under bops facet
    And I verify that SEARCH button is displayed under bops facet
    When I search for zipcode "10001" in bops facet
    Then I verify that "25 miles" is displayed in the radius dropdown under bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    And I verify that Change Location hyperlink is displayed under bops facet

  @domain_discovery @priority_medium @use_regression @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify listed BOPS stores with zero product count within the selected radius are disabled and displayed with 0 product count when zipcode cookie not available in DOMESTIC mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    When I remove "MISCGCs" zipcode cookie to cookie list
    And I verify that "Pick Up In-Store" facet is listed on left nav
    And I verify that zipcode based on store cookie value under bops facet
    And I verify that Change Location hyperlink is displayed under bops facet
    When I select "100 miles" Miles under bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    And I verify that products with 0 count displayed is in disabled state

  @domain_discovery @priority_medium @use_regression @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify BOPS facet is supressed in search results page after facet selection and BOPS facet appears again on clearing the facet selection, when the zipcode is not stored in cookie in DOMESTIC mode
    Given I am on SearchResultsPage for "<keyword>" in <shopping_mode> mode
    When I remove "MISCGCs" zipcode cookie from cookie list
    And I verify that "Pick Up In-Store" facet is listed on left nav
    And I verify that Enter Zip Code by default in search box under bops facet
    When I search for zipcode "10001" in bops facet
    Then I verify that stores are displayed in pick-up in-store facet section
    And I select "single" facet value from "Designer" facet section
    And I click on clear all button
    And I verify that "Pick Up In-Store" facet is listed on left nav
    Examples:
      |keyword       | shopping_mode|
      |jeans         | Domestic     |
      |Dinner plates | Registry     |

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @priority_medium @gmm_bopsfacet @use_regression @mode_domestic @migrated_to_sdt @wip
  Scenario: SearchResultsPage - domestic - Verify error message when stores not available after user reduces the search radius of search result page in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "00000" in bops change store dialog
    And I select "5 miles" Miles under bops change store dialog
    Then I verify that default message "Unfortunately, in-store pickup is currently unavailable in your area." under bops facet
    When I click on save button in change pick-up in-store overlay
    Then I verify that Change Location hyperlink is displayed under bops facet

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @priority_medium @gmm_bopsfacet @use_regression @mode_domestic @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify modified facet store cookie value get displayed on PDP through product thumbnail in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "10001" in bops change store dialog
    And I select "100 miles" Miles under bops change store dialog
    And I save my location in ISA overlay
    Then I verify that bops overlay is not displayed
    When I select random bops facet value
    And I verify that products are filtered as per selected facet values
    And I select random product from thumbnail grid
    When I select a random UPC of the product
    Then I verify that the store name is displayed in bops messaging

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @priority_medium  @gmm_bopsfacet @use_regression @mode_domestic @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify modified facet store cookie value persists when user clicks to PDP through product thumbnail and navigates away from PDP in DOMESTIC mode
    Given I am on SearchResultsPage for "plates" in Domestic mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "10001" in bops change store dialog
    And I select "100 miles" Miles under bops change store dialog
    And I save my location in ISA overlay
    Then I verify that bops overlay is not displayed
    When I select random bops facet value
    And I verify that products are filtered as per selected facet values
    And I select random product from thumbnail grid
    Then I verify that landed on respective product display page
    When I select browse 'back' button
    And I verify that zipcode hyperlink is displayed under bops facet
    
  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @mode_registry @use_regression @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Registry - Verify the system displays store facet values within 25 miles radius based on the zipcode value stored in cookie in REGISTRY mode
    Given I am on SearchResultsPage for "Plates" in REGISTRY mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    Then I verify that "25 miles" is displayed in the radius dropdown under bops facet


  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @mode_registry @use_regression @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Registry - Verify the system displays store facet values between 25 to 50 miles radius based on the zipcode value stored in cookie in REGISTRY mode
    Given I am on SearchResultsPage for "Plates" in REGISTRY mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select "50 miles" Miles under bops facet
    Then I verify that "50 miles" is displayed in the radius dropdown under bops facet

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @gmm_bopsfacet @mode_registry @use_regression @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Registry - Verify error message displayed in overlay when there is no store for the searched zipcode/radius combination in REGISTRY mode
    Given I am on SearchResultsPage for "lenox" in REGISTRY mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "11030" in bops change store dialog
    And I save my location in ISA overlay
    Then I verify that bops overlay is not displayed
    Then I verify that "25 miles" is displayed in the radius dropdown under bops facet
    When I select "5 miles" Miles under bops facet
    Then I verify that "Currently there are no stores within 5 miles of 11030 offering in-store pickup." error message is displayed under bops facet
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    And I verify that default message "Unfortunately, in-store pickup is currently unavailable in your area." under bops facet

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @priority_medium @gmm_bopsfacet @mode_domestic @use_regression @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic - Verify preselected bops facet value included if it fall within the radius selected when zipcode cookie is available
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "11030" in bops change store dialog
    And I save my location in ISA overlay
    Then I verify that bops overlay is not displayed
    When I select "100 miles" Miles under bops facet
    And I select random bops facet value
    Then I verify that products are filtered as per selected facet values
    And I select "50 miles" Miles under bops facet
    Then I verify that the selected Pick Up In Store appears on top
  Examples:
  | shopping_mode |
  | Domestic |
  | Registry |

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @priority_medium @gmm_bopsfacet @mode_domestic @use_regression @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Domestic - Verify preselected bops facet value excluded if it doesnt fall within the radius selected when zipcode cookie is available
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    And I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "11030" in bops change store dialog
    And I save my location in ISA overlay
    Then I verify that bops overlay is not displayed
    When I select "100 miles" Miles under bops facet
    And I select random bops facet value
    Then I verify that products are filtered as per selected facet values
    And I select "5 miles" Miles under bops facet
    Then I verify that the checked value not appears on top
  Examples:
  | shopping_mode |
  | Domestic |
  | Registry |

  @snbc_comp @use_regression @use_regression @artifact_navapp @domain_discovery @saturnrules @use_preview @mode_iship @priority_medium @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Iship - Verify BOPS and other facets are displayed in ISHIP mode
    Given I am on SearchResultsPage for "dresses" in Iship mode
    And I verify that "Pick Up In Store" facet is not listed on left nav
    And I verify that other facets excluding BOPS are displayed

  @snbc_comp @use_regression @use_regression @artifact_navapp @domain_discovery @saturnrules @use_preview @mode_domestic @priority_medium @migrated_to_sdt @wip
  Scenario: SearchResultsPage - Domestic - Verify BOPS and other facets are displayed in DOMESTIC mode
    Given I am on SearchResultsPage for "dress" in Domestic mode
    And I should see "Pick Up In Store" facet listed on left nav
    And I verify that other facets excluding BOPS are displayed

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @priority_medium @gmm_bopsfacet @use_regression @mode_iship @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Iship - Verify BOPS facet is not displayed in iship mode and it is displayed with zipcode based on cookie in DOMESTIC mode
    Given I am on SearchResultsPage for "dress" in Domestic mode
    When I add "MISCGCs" zipcode cookie from cookie list
    And I navigate to international context page
    When I change country to "<Country>" and "stay" on the current page
    And I close the welcome mat if it's visible
    And I verify that "Pick Up In Store" facet is not listed on left nav
    When I go to US site
    And I should be in Search Landing page
    And I should see "Pick Up In Store" facet listed on left nav
    And I verify that zipcode hyperlink is displayed under bops facet
    Examples:
      | Country |
      | India   |
      | Canada  |

  @snbc_comp @use_regression @release_14A @artifact_navapp @domain_discovery @priority_medium @gmm_bopsfacet @use_regression @mode_iship @migrated_to_sdt @wip
  Scenario Outline: SearchResultsPage - Iship - Verify BOPS facet is not displayed in iship mode and it is displayed on switching back in DOMESTIC mode
    Given I am on SearchResultsPage for "dress" in Domestic mode
    And I should see "Pick Up In Store" facet listed on left nav
    When I navigate to international context page
    And I change country to "<Country>" and "stay" on the current page
    And I close the welcome mat if it's visible
    Then I should be in Search Landing page
    And I verify that "Pick Up In Store" facet is not listed on left nav
    When I go to US site
    Then I should be in Search Landing page
    And I should see "Pick Up In Store" facet listed on left nav
    Examples:
      | Country |
      | Canada  |