# Author: IA-2017 QE Team
# Date Created: 12/05/2017
Feature: Tabbed view of online and BOPS items on Browse pages

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Verify default tabbed view on category browse page
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should be in category browse page
    Then I should see tabbed view for online and BOPS items
    And I should see all items tab as selected

  @project_IA_2017 @domain_discovery
  Scenario: Verify the product count on default tabbed view of category browse page
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should be in category browse page
    Then I should see tabbed view for online and BOPS items
    And I should see all items tab as selected
    And I see the product count from UI is same as from service for given category

  @project_IA_2017 @domain_discovery @domain_purchase_and_delivery @IA_BOPS
  Scenario: Verify that Free Pick Up In Store facet section is not displaying on left navigation of category browse page
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should be in category browse page
    Then I should see tabbed view for online and BOPS items
    And I should not see free pick up in store facet section on left navigation

  @project_IA_2017 @domain_discovery
  Scenario: Verify local store tab when customer location is known and product results are available on category browse page
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should be in category browse page
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    And I see the store product count on local store tab

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Verify store selection on find a store overlay
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I click "change store" link
    And I see "choose a store" overlay
    When I click change location link on choose a store overlay
    And I see "Find a store" overlay
    When I search for zipcode "10022" in bops change store dialog
    And I select different store from "find a store" overlay and save the selection
    Then I verify that USERPC and USERLL are updated with "10022" values in MISCGCs cookie
    And I should see same store name under local store tab
    And I verify that products are filtered as per selected store

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Verify miles drop down in choose a store overlay
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I click "change store" link
    And I see "choose a store" overlay
    Then I verify that miles drop down in choose a store overlay with below option:
      | 5 miles  |
      | 10 miles |
      | 25 miles |
      | 50 miles |
      | 100 miles |

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Verify store selection on choose a store overlay
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I click "change store" link
    And I verify that stores are displayed in choose a store overlay
    When I select different store from "choose a store" overlay and save the selection
    Then I should see same store name under local store tab
    And I verify that products are filtered as per selected store

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario Outline: Iship - Verify BOPS tabview is not displayed in iship mode and it is displayed with zipcode based on cookie when switch back in DOMESTIC mode
    Given I visit the web site as a guest user
    When I <cookie_action> "MISCGCs" zipcode cookie from cookie list
    And I navigate to international context page
    And I change country to "<Country>"
    And I close the welcome mat if it's visible
    When I navigate to the "Boots" browse page under "SHOES"
    Then I should be in category browse page
    And I should not see tabbed view for online and BOPS items
    When I go to US site
    When I navigate to the "Jeans" browse page under "MEN"
    Then I should be in category browse page
    And I should see tabbed view for online and BOPS items
    Examples:
      | Country | cookie_action|
      | India   | add          |
      | India   | remove       |
      | Canada  | add          |
      | Canada  | remove       |

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Registry - Verify BOPS tabview is not displayed in registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to the "Bakeware" browse page under "KITCHEN"
    Then I should be in category browse page
    And I should not see tabbed view for online and BOPS items

  @project_IA_2017 @domain_discovery @use_regression
  Scenario: Verify BOPS tabview and zero search results message is displaying for bigticket items
    Given I visit the web site as a guest user
    When I navigate to the "Furniture" browse page under "HOME"
    And I select "Couches & Sofas" category from left nav
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    Then I should see the zero results message "Sorry, these items aren't available to pick up in stores, but we're happy to ship them to you." on search results page

  @project_IA_2017 @domain_discovery @use_regression
  Scenario: Domestic - Verify BOPS tabview in "CHANEL" Page
    Given I visit the web site as a guest user
    And I navigate to the "CHANEL" browse page under "BEAUTY"
    And I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I should not see tabbed view for online and BOPS items

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Domestic - Verify listing store location information comes from SDP store Service when the dynamic kill switch google_maps_enabled set to true
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I click "change store" link
    And I see "choose a store" overlay
    When I click change location link on choose a store overlay
    And I see "Find a store" overlay
    Then I verify that bops overlay is displayed
    When I search for zipcode "10021" in bops change store dialog
    And I verify that bops overlay with map and stores list
    And I click on save button in change pick-up in-store overlay
    Then I should see tabbed view for online and BOPS items

  @project_IA_2017 @domain_discovery @use_regression
  Scenario: Domestic - Verify error message when Call to GME from BOPS overlay fails in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I click "change store" link
    And I see "choose a store" overlay
    When I click change location link on choose a store overlay
    And I see "Find a store" overlay
    Then I verify that bops overlay is displayed
    When I search for zipcode "10022" in bops change store dialog
    And I verify that bops overlay with map and stores list
    When I search for zipcode "10481" in bops change store dialog
    Then I verify that "No stores were found near your entered location. Please increase your search area or try a different location." error message is displayed under bops change store dialog

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Domestic - Verify random facet value in breadcrumb, selected store is displayed after changing the store selection in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I select 'single' facet value from 'any' facet section
    And I click "change store" link
    And I see "choose a store" overlay
    When I select 100 miles in choose a store overlay
    Then I verify that stores are displayed in choose a store overlay
    And I select different store from "choose a store" overlay and save the selection
    Then I verify that previously selected facets persists in breadcrumb
    And I should see same store name under local store tab

  @project_IA_2017 @domain_discovery @use_regression
  Scenario: Domestic - Verify stores display within default 10 miles radius
    Given I visit the web site as a guest user
    When I navigate to the "Makeup" browse page under "BEAUTY"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I click "change store" link
    And I see "choose a store" overlay
    Then I verify that 10 miles is displayed as default in choose a store overlay
    And I verify that stores are displayed in choose a store overlay

  @project_IA_2017 @domain_discovery @use_regression @domain_purchase_and_delivery @IA_BOPS
  Scenario: Verify store selection is persisting when user switching between all items and local store tabs
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    When I add "MISCGCs" zipcode cookie from cookie list
    And I should see tabbed view for online and BOPS items
    When I select local store tab
    And I see the change store link
    When I click "change store" link
    And I verify that stores are displayed in choose a store overlay
    When I select different store from "choose a store" overlay and save the selection
    Then I should see same store name under local store tab
    And I select all items tab
    Then I select local store tab
    And I should see same store name under local store tab
