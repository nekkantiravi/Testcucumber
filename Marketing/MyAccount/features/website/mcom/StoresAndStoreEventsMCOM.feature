#Author: SST Automation Regression
#Date Created:  09/13/2017

Feature:  Our Stores - Store Event search results page

  #Test Case ID: MCOM-66703, MCOM-92059
  @use_regression @artifact_navapp @mustpass @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario:Store Events Page - Rendering from Footer
    Given I visit the web site as a guest user
    When I navigate to store locator page
    Then I am on the Our Stores Events Page as a guest user
    And I verify the basic attributes of the Store Events Page
    # Notes:
    # Navigation: Home Page - Footer - OUR STORES - store events
    # Basic attributes to verify in Store Events Page:
    # Page title as "Macy's In-Store & Special Events!"
    # "find a store event" header text
    # Page should have Zip text box, City text box, State selector drop down and Search button

  @use_regression @artifact_navapp @artifact_navapp_2 @myaccount_1 @googlemapsmigration @s4a_stable @domain_marketing @priority_high
  Scenario Outline:Store Events Page - Error message validation
    Given I visit the web site as a guest user
    When I navigate to store locator page
    Then I search with invalid "<ZIP_VALUE>" in "<STORES_SECTION>" and expect "<ERROR_MESSAGE>"
    Examples:
      | ZIP_VALUE | STORES_SECTION         | ERROR_MESSAGE                                    |
      | 11111     | Store Location & Hours | We could not find any Macy's stores within 25 miles of your search area. Please re-enter a different city and state or zip code and try again. Or you can Browse All Macy's Stores.|
      | 11111     | Browse Catalog         | Please enter a valid ZIP code.                   |
      | 111       | Browse Catalog         | Please enter a valid ZIP code.                   |
      | 11111     | Store Events           | Oops! We couldn't find what you're looking for.  |
#      | 111       | This Week In Stores    | Your entry is not formatted correctly. Please try again. |


  @use_regression @artifact_navapp @artifact_navapp_2 @myaccount_1 @googlemapsmigration @s4a_stable @domain_marketing @use_domain_qual
  Scenario:Our Stores - Verify sections and links under our stores page
    Given I am on the Our Stores Page as a guest user
    Then I verify the sections of Our Stores page
    And I verify that the links of our store page have rendered
#  Examples:
#    | Our_Stores_Sections_and_Links  |
#    |Find a Store                     |
#    |Browse Catalogs                  |
#    |Shopping Services                |
#    |What is Happening at Macys       |
#    |Find Store Events Near Your      |
#    | About Us                        |
#    | Macy's Event Marketing          |
#    | corporate Sales                 |
    # Notes:
    # Navigation: Home Page - Footer - OUR STORES

# Macys Milestones link is giving 404 error page defect - DS-84953
  @use_regression @artifact_navapp @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario:Our Stores - Rendering About Us
    Given I am on the our stores About Us Page as a guest user
    Then I navigate to links of About Us page and verify the pages renders
#  Examples:
#    | ABOUTUS_LINKS        |
#    | Macy's Milestones    |
#    #| Career Opportunities |
#    | Visitor Services     |
#    | Community Relations  |
#    #| Press Room           |
#    #| Macy's Inc.          |
#    #| Affiliate Program    |
#    #| Diversity            |
#    # Notes:
#    # Navigation: Home Page - Footer - OUR STORES - About As

  @use_regression @artifact_navapp @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario:Our Stores - Rendering Shopping Services
    Given I am on the Shopping Services Page as a guest user
    Then I navigate and render the links of our stores shopping services page
#  Examples:
#    | SHOPPING_SERVICE_LHN_LINKS |
#    | Personal Shopper           |
#    | Corporate Sales            |
#    | Custom Window              |
#    | Visitor Services           |
    # Notes:
    # Navigation: Home Page - Footer - OUR STORES - Shopping Services

  # commented the below scenario since it was covered in the scenario: Our Stores - Verify sections and links under our stores page
#  @use_regression @artifact_navapp @artifact_navapp_2 @myaccount_1 @googlemapsmigration @s4a_stable @domain_marketing
#  Scenario:Stores Page - Rendering from Header
#    Given I am on the Our Stores Page as a guest user
#    Then I verify the basic attributes of the Our Stores Page
    # Notes:
    # Navigation: Home Page - Footer - OUR STORES - store events
    # basic attributes need to verify in the Our Stores Page:
    # "store locations & hours" header text
    # under the "store locations & hours" header should display LocatorZipCode, City text box controls, State drop down control and LOCATOR search button
    # "browse catalogs" header text
    # under the "browse catalogs" header should display CatalogZipCode text box control and CATALOG search button
    # "this week in stores" header text
    # under the "this week in stores" header should display InStoreZipCode text box control and INSTORE search button

  @artifact_navapp @release_16J @priority_medium @domain_selection @project_UFT @myaccount_1
  Scenario: Verify email address is encrypted in bright tag data dictionary object when signed in from Stores Page
    Given I visit the web site as a guest user
    When I navigate to stores legacy page
    And I navigate to sign in page
    And I enter valid credentials and click Sign In button
    Then I should see hashed email address hE tag in bright tag
    And I should see hashed email address hE2 tag in bright tag
    # Notes:
    # hE: email address is hashed via SHA256
    # hE2: email address is double hashed via MD5 first and then via SHA256

  @wip @please_automate @domain_marketing
  Scenario Outline: Stores Page - Browse Catalog
    Given I am on the Our Stores Page as a guest user
    When I enter valid "<ZIP_VALUE>"
    And I search in Browse Catalog section
    Then I should navigate to the Online Catalogs page
    Examples:
      | ZIP_VALUE |
      | 93167     |
    # Notes:
    # Navigation: Home Page - Footer - OUR STORES

  @wip @please_automate @domain_marketing
  Scenario Outline: Stores Page - This Week In Stores
    Given I am on the Our Stores Page as a guest user
    When I enter valid "<ZIP_VALUE>"
    And I search in This Week In Stores section
    Then I should navigate to the Online Catalogs page
    Examples:
      | ZIP_VALUE |
      | 93167     |
    # Notes:
    # Navigation: Home Page - Footer - OUR STORES

  #MCOM-92058
  @use_regression @use_dsv @domain_marketing @dsv_sev1
  Scenario: Verify Find a Store
    Given I navigated to the Our Stores Page as a guest user
    When I navigate to the "Store Locations & Hours" in our stores page
    Then I verify that the "Store Locations & Hours" our stores page has rendered
    And I verify the functionality in "Store Locations & Hours" page
    # Notes:
    # Enter the Zip Code / select the city and State and Click on "Search"
    #
    # button then All the stores matching with the criteria (Zip Code, City, and State) should display the results
    # On stores result page click on  link "See more store hours" under any store then Popup should open & details for the next 7 days of "STORE HOURS" should be present
    # Description: Verify Find a Store
    # Steps:
    # 1. Navigate to macys.com
    # 2. On Home page click on "Stores".
    # 3. Enter the Zip Code / select the city and State and Click on "Search" button.
    # 4. On stores result page click on  link "See more store hours" under any store.
    #
    # Expected Results:
    # 1. macys.com home page should be displayed and  "Stores" Link should be present on the H.P.
    # 2. "Our Stores" page should display.
    # 3. All the stores matching with the criteria (Zip Code, City, and State) should display the results.
    # 4. Popup should open & details for the next 7 days of "STORE HOURS" should be present.

  @use_regression @use_dsv @domain_marketing @akamai_waf
  Scenario: Verify store events timing should be in sequential pattern
    Given I navigated to the Our Stores Page as a guest user
    When I enter a zip code and search on our Stores Events Page
    Then I verify date and timing of the event should be displayed in sequential pattern
    # Description: Store events > timing should be in sequential pattern.
    # Steps:
    # 1. Navigate to macys.com
    # 2. Click on footer link "Store events"
    # 3. Enter zip code and search
    #
    # Expected Results:
    # 1. Macys.com home page should display
    # 2. find a store event page should display
    # 3. date and timing of the event should be displayed in sequential pattern

  # GMM
  # Story Title: MCOM :: Filter by Store Services
  # Mingle Link: http://mingle/projects/site_stability/cards/1447

  # @14D
  #Test Case ID: MCOM-66707 , MCOM-66702
  @use_regression @googlemapsmigration_1447 @priority_medium @artifact_navapp @artifact_navapp_2 @myaccount_1 @use_launch_call @domain_marketing @use_domain_qual
  Scenario Outline: Verifying stores locations and hours search by ZIP code, address, city, and/or state.

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    When I select "search now" links in stores page
    Then I should be redirected to "store_locations_hours" page
    And I should see single input field displayed to enter location in store locations page
    When I search using a zipcode "10002" in store locations page
    Then I should see Filter by link in store locations page
    And I should be able to expand the advance search option
    And I should see below advanced search features
#      | FilterNames             |
      | Wedding & Gift Registry |
      | Visitor Services        |
      | Personal Shopper        |
      | Furniture Gallery       |
      | Mattresses              |
      | Restaurants             |
      | The Tuxedo Shop         |
    When I select "<FilterNames>" filter from the advanced search list
    And I select search button in store locations page
    Then I should see search results in store locations page with "<FilterNames>"
    And I should be able to collapse the advance search option

    Examples:
      | FilterNames      |
      | Visitor Services |
      | Personal Shopper |
      | Restaurants      |

  @14D @googlemapsmigration_1447 @manual @wip @domain_marketing
  Scenario: Verifying error message on stores locations and hours search by ZIP code, address, city, and/or state.

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    When I select "search now" links in stores page
    Then I should be redirected to "store_locations_hours" page
    And I should see single input field displayed to enter location in store locations page
    Then I should see Filter by link in store locations page
    And I should be able to expand the advance search option
    And I should see below advanced search features
#      | FilterNames             |
      | Wedding & Gift Registry |
      | Visitor Services        |
      | Personal Shopper        |
      | Furniture Gallery       |
      | Mattresses              |
      | Restaurants             |
      | The Tuxedo Shop         |
    When I search using a zipcode "000000" in store locations page
    Then I should see "No locations match your search, please try again" error message in store locations page

# @14B
  @use_regression @googlemapsmigration_1450 @priority_high @artifact_navapp @bat_refactored @mustpass @artifact_navapp_2 @myaccount_1 @use_launch_call @domain_marketing
  Scenario: Verify display of store hours for the week & list of store features section, when clicked on more

    Given I visit the web site as a guest user
    When I navigate to the store locations page
    When I search using a zipcode "10003" in store locations page
    Then I should see search results in store locations page
    When I click "more arrow" link in a store from store results
    Then I should see "hours" tab selected by default
    And I should see store hours for the week
    And I should see "less" arrow
    When I click on "features" tab
    Then I should see list of store features displayed
    And I should see "less" arrow
    When I click "less arrow" in a store from store results
    Then I should see more arrow link is collapsed


# @14B
  @use_regression @googlemapsmigration_1445 @priority_high @artifact_navapp @artifact_navapp_2 @myaccount_1 @domain_marketing
  Scenario Outline: Verifying stores locations and hours search by ZIP code, address, city, and state.

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    When I select "search now" links in stores page
    Then I should be redirected to "store_locations_hours" page
    And the URL should include "/shop"
    And I should see single input field displayed to enter location in store locations page
    And I should see greyed out text "Find locations near..." in the input field
    When I place cursor on the input field
    Then the input field should be blank
    When I search using "<SearchInput>" in "store locations" page
    Then I should see search results for "<SearchCriteria>" in store locations page

    Examples:
      | SearchInput                                 | SearchCriteria         |
      | 300 Stanford Shopping Center                | Address                |
      | Palo Alto                                   | City                   |
      | 94304                                       | Zip Code               |
      | 300 Stanford Shopping Center, Palo Alto     | Address & City         |
      | 300 Stanford Shopping Center, Palo Alto, CA | Address, City & State  |
      | 3650 Mandela Pkwy, CA                       | Address & State        |
      | 300 Stanford Shopping Center, CA 94304      | Address, State & Zip   |
      | 300 Stanford Shopping Center, 94304         | Address & Zip          |
      | Palo Alto, CA                               | City & State           |
      | Palo Alto,CA,94304                          | City, State & Zip Code |
      | Palo Alto,94304                             | City & Zip             |


# @14B
  @use_regression @googlemapsmigration_1445 @priority_high @artifact_navapp @bat_refactored @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario:Verifying advanced search option in store locations page

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    When I select "search now" links in stores page
    Then I should be redirected to "store_locations_hours" page
    And I should see Filter by link in store locations page
    And I should be able to expand the advance search option


###########################
    # UFT Team update: #Version One: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A77090
    # As part of above story, maximum events count is increased from 5 to 15. Please update the featurefile.

   # @14B
  @use_regression @googlemapsmigration_1657 @priority_high @artifact_navapp @bat_refactored @artifact_navapp_2 @myaccount_1 @domain_marketing
  Scenario: Verify rendering of Store Events Search Results Page for its contents

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    When I select "find an event" links in stores page
    Then I should be redirected to "store_events" page
    When I search for stores with "McLean" as state in the "events search" page
    Then I verify various sections on Event Search Results page
#    Then I should see "available_store" list according to built-in radius logic
##    And I should see maximum of "5" events per store in the events results page
#      | Store_Details    |
#      | faux image       |
#      | Store address    |
#      | Directions link  |
#      | Catalogs link    |
#      | List of events   |
#    And I should see following details in List of events displayed in store section
#      | Event_info  |
#      | Date        |
#      | Time        |
#      | Information |
#    And I should see "See details" link if there is further information about the event
#    And I should see "click here" link to return to events search page
#    And I "should" see breadcrumb in "Events result page"
#    And I should see sidebar links displayed in Events result page


  # Story Title: MCOM :: Store Events Search Page
  # Mingle Link: http://11.120.103.7/projects/sf/cards/1658

  # Below scenario is obsolete as of 17P. On event search page, there is no text filed for zip and city separately.
  # There is no side bar and breadcrumb. So commenting out. So I commented out and created new scenario with latest page validations
# @14B
#  @use_regression @googlemapsmigration_1658 @priority_high @artifact_navapp @artifact_navapp_2 @myaccount_1 @domain_marketing @saucelab_M @saucelab_M_F1 @saucelab_M_S1 @use_domain_qual
#  Scenario: Verifying searching for local store events by specific stores via zip code
#
#    Given I visit the web site as a guest user
#    When I select "stores" link in home page
#    And I select "find an event" links in stores page
#    Then I should be redirected to "store_events" page
#    And I "should" see breadcrumb in "Events page"
#    And I should see Marketing image in events search page
#    And I should see sidebar displayed in events search page
#    And I should see "ZipCode" input field in events search page
#    And I should see "City" input field in events search page
#    And I should see state drop down option in events search page
  @use_regression
  Scenario: Verifying Store Events page for various sections
    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    And I select "find an event" links in stores page
    Then I should be redirected to "store_events" page
    And I verify various sections displayed on Store Events page.

# @14B
  @use_regression @googlemapsmigration_1658 @priority_high @artifact_navapp @bat_refactored @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario:Verifying error message if no events are detected

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    And I select "find an event" links in stores page
    Then I should be redirected to "store_events" page
    When I search using "94743" as "zipcode" in "events search" page
    Then I should see error message "Oops! We couldn't find what you're looking for." displayed in the events search page


  # Story Title: MCOM :: Clean Landing Page
  # Mingle Link: http://mingle/projects/site_stability/cards/1448
  # Mingle Link: http://mingle/projects/site_stability/cards/2292

# @14B
  @use_regression @googlemapsmigration_1448 @priority_medium @artifact_navapp @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario Outline:Verifying stores landing page options

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    And the URL should be in "/shop/" format
    And I should see "<Label>" label in Stores Page
    When I select "<IconLink>" links in stores page
    Then I should be able to redirect to "<LandingPage>" with store "<URL>" URL
    And I should see breadcrumb in "<LandingPage>"

    Examples:
      | Label             | IconLink      | LandingPage            | URL                |
      | Find a Store      | search now    | Store locator page     | /shop/store/search |
      | Shopping Services | see services  | Shopping services page | /store/service     |
      | Events Near You   | find an event | Events page            | social/events/     |


# @14B
  @use_regression @googlemapsmigration_1448 @priority_medium @artifact_navapp @artifact_shopapp @artifact_navapp_2 @myaccount_1 @artifact_shopapp_2 @s4a_stable @domain_marketing
  Scenario:Verifying Whats Happening in macys option from Stores Page

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    And the URL should be in "/shop/" format
    And I should see label "What's Happening at Macy's" in Stores Page
    When I select "Join The Fun" links in stores page
    Then I should be redirected to "What's Happening at Macy's page" with store "/splash/corporate/campaigns" URL


  @14B @googlemapsmigration_1448 @manual @wip @domain_marketing
  Scenario: Verifying stores landing page Catalog option

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    And the URL should be in "/shop/" format
    And I should see label "Browse Catalogs" in Stores Page
    When I select "go now" links in stores page
    Then I should be redirected to "Catalogs page" with store "http://macys.circularhub.com/flyer_selector/macys" URL

# @14B
  @use_regression @googlemapsmigration_1448 @priority_medium @artifact_navapp @artifact_navapp_2 @myaccount_1  @s4a_stable @domain_marketing @saucelab_M
  Scenario Outline:Verify Corporate sales link from Store Page

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    When I select "<IconLink>" links in stores page
    Then I should be redirected to "<IconLink>" with store "<URL>" URL and breadcrumb
    Examples:
      | IconLink               | URL                  |
      | About Us               | /store/about         |
      | Macy's Event Marketing | /store/marketing.jsp |


# @14B
  @use_regression @googlemapsmigration_1448 @priority_high @artifact_navapp @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario:Verify Corporate sales url in Store landing page

    Given I visit the web site as a guest user
    When I select "stores" link in home page
    Then I should be redirected to "our_stores" page
    When I select link "Corporate Sales" in stores page
    Then I should be redirected to "Corporate Sales" with store "/splash/corporate" URL

# @14B
  @use_regression @googlemapsmigration_1448 @priority_high @artifact_navapp @artifact_navapp_2 @myaccount_1 @s4a_stable @domain_marketing
  Scenario:Verifying stores landing page, when clicked on stores in home page

    Given I visit the web site as a guest user
    When I select "OUR STORES" link in home page
    Then I should be redirected to "our_stores" page
    And the URL should be in "/shop/" format



  #http://jira/browse/ECOMSST-21267
  @release_14D @priority_medium @domain_marketing
  Scenario: Verify that correct page is displayed when user navigates to map/directoins from my account page
    Given I visit my account page as a signed user
    And I open "map/directions" link from my store section
    Then I should get a new pop-up window in Google maps with the address populated with 5 digit zip code

  @release_14D @priority_medium @domain_marketing
  Scenario: Verify that correct page is displayed when user navigates to store locator from my account page
    Given I visit my account page as a signed user
    And I open store locator link from my store section
    Then I should be navigated to store locator page in a new tab with the results

  @release_14D @priority_medium @domain_marketing
  Scenario: Verify that correct page is displayed when user navigates to store events from my account page
    Given I visit my account page as a signed user
    And I open store events link from my store section
    Then I should be land on events page in a new tab relevant to zip code automatically

  @release_14D @priority_medium @domain_marketing
  Scenario: Verify that correct page is displayed when user navigates to current catalogs from my account page
    Given I visit my account page as a signed user
    And I open current catalogs link from my store section
    Then I should be navigated to catalog page in a new window without zip code pre-populated

  # Version One: B-46723
  @artifact_navapp @release_16E @priority_medium @domain_marketing @project_UFT @mode_domestic @myaccount_1
  Scenario: Verify he tag is not populated in bright tag data dictionary object on Stores Page
    Given I visit the web site as a guest user
    When I navigate to store locator page
    Then I should not see hE tag in bright tag data dictionary object
