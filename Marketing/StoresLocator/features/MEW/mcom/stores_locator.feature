@domain_marketing @mcom
Feature: Component tests for store locator
  Stories Covered:
  21219 : WSSG:: MeW Rel 2.0 :: Product recommendations
  25286 : MM2 :: Store Locator :: Event Edge Case
  21660 : MM2 :: Store Locator :: Filter by Services
  21226 : MM2 :: Store Locator :: Store Details Page
  21652 : MM2 :: Store Locator :: View All Events & Event Details Modals
  21661 : MM2 :: Store Locator :: Favorite Stores
  25415 : BM2/MM2 :: Store Locator :: Additional Map View/List View Updates
  # As of 2017-12-20, all MEW stores functionality is switched to the new stores page at l.macys.com/stores.html
  # MEW urls such as /shop/stores/detail?locNo=nnn now throw "page no longer available"
  #
  # 21661 no longer supported on StoresLocator; removed all tests

  @regression @use_new_regression @use_real_device @mew_regression
  Scenario Outline: Verify incoming links to Stores Locator page
    Given I visit the mobile web home page
    When I tap on "<stores_link>" to visit the Stores Locator page
    Then I should be on the stores home page

    Examples:
      | stores_link  |
      | footer       |
      | menu         |

  @mingle-21219 @mingle-21657 @regression @use_new_regression @use_real_device @mew_regression
  Scenario: Verify the objects on Initial landing page.
    Given I am on stores page
    Then I should see following elements on "stores_locator" page:
      | search_input    |
      | search_radius   |
      | search_filters  |
      | search_button   |
    And I should see the correct filter dropdown options
      | Backstage                   |
      | Personal Shopper            |
      | Restaurants                 |
      | Wedding Gift & Registry     |
      | Furniture Clearance Center  |
      | Furniture Gallery           |
      | Maternity                   |
      | Mattresses                  |
      | Visitor Services            |

  @regression @use_new_regression @use_real_device @mew_regression
  Scenario Outline: Verify search by zip and address
    Given I am on stores page
    When I enter "<search_value>" in the search store field and search
    Then I should see matching store counts on search results page for the given search keyword in list view

    Examples:
      | search_value                        |
      | 151 W 34th St, New York, NY 10001   |
      | 94105                               |
      | San Jose, CA, United States         |


  @regression @use_new_regression @use_real_device @mew_regression
  Scenario: Verify search distance is closest to furthest
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And they should be in order from closest to furthest


  @regression @use_real_device @mew_regression
  Scenario: Verify map view
    Given I am on stores page
    # TODO: Determine how to 'varify the map view' beyond simple existance?
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I should verify the map view

  @mingle-21660 @regression @use_new_regression @use_real_device @mew_regression
  Scenario Outline: Verify the stores filter by services
    Given I am on stores page
    # WIP: Mismatch between stores service and displayed values
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I select "<facet_value>" facet value for the filter by services
    And I should apply the facet value selection
    And I should see the filter by stores section in the list view
    And I should verify stores filtered by the filter by services in the list view

    Examples:
      | facet_value       |
      | Furniture Gallery |
      | Personal Shopper  |

  @mingle-21226 @use_smoke @use_mew_dsv_next @regression @use_mew_bat @mew_regression
  Scenario: Verify the store detail page
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I navigate to "Macy's Union Square" store detail page
    And I should verify the store details

  @mingle-21226 @use_smoke @use_mew_dsv_next @regression @use_mew_bat @mew_regression
  Scenario: Verify the store details page events details
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I navigate to "Macy's Union Square" store detail page
    And I should verify the store events details

  @mingle-21226 @use_smoke @use_mew_dsv_next @regression @use_mew_bat @mew_regression
  Scenario: Verify the store restaurants details
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I navigate to "Macy's Union Square" store detail page
    And I should verify the store restaurants details

  @mingle-25415 @mew_regression
  Scenario Outline: Verify the stores error messages
    Given I am on stores page
    When I enter "<keyword>" in the search store field and search
    Then I should see the related "<error_message>"

    Examples:
      | keyword | error_message       |
      | adcdefg | We could not find any Macy's stores within 25 miles of your search area. Please re-enter a different city and state or zip code and try again. Or you can Browse All Macy's Stores.   |
      | 01234   | We could not find any Macy's stores within 25 miles of your search area. Please re-enter a different city and state or zip code and try again. Or you can Browse All Macy's Stores.   |

  @mingle-21226 @mew_regression
  Scenario: Verify the store detail page hours
    Given I am on stores page
    When I enter "94105" in the search store field and search
    And I navigate to "Macy's Union Square" store detail page
    Then I should verify the store detail hours

  @mingle-21652 @d-18313 @regression @use_real_device @mew_regression
  Scenario: Verify the store events details
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I navigate to "Macy's Union Square" store detail page
    # Found at https://www.macys.com/social/events/event/?id=22001
    And I should navigate to a random event from the store detail page
    And I should verify the details of the event page

    # NOTE: This scenario is valid for website but not for MEW
    # @mingle-21226 @use_smoke @use_mew_dsv_next @regression @use_mew_bat @mew_regression
    # Scenario: Verify the store services details
    #   Given I am on stores page
    #   When I enter "94105" in the search store field and search
    #   Then I should see stores on search results page for the given search keyword in list view
    #   And I navigate to "Macy's Union Square" store detail page
    #   And I should verify the store services details

    # NOTE: This scenario is valid for website but not for MEW
    # @mingle-21226 @use_smoke @use_mew_dsv_next @regression @use_mew_bat @mew_regression
    # Scenario: Verify the store departments details
    #   Given I am on stores page
    #   When I enter "94105" in the search store field and search
    #   Then I should see stores on search results page for the given search keyword in list view
    #   And I navigate to "Macy's Union Square" store detail page
    #   And I should verify the store departments details
