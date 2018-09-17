@p1 @store_locator @component_test @r2_only @bcom @domain_marketing
Feature: Component tests for store locator
  Stories Covered:
  23526 : BM2 :: Stores & Events :: Filter by services
  25415 : BM2/MM2 :: Store Locator :: Additional Map View/List View Updates
  22162 : BM2 :: Store Locator :: View All Events & Event Details Modals

  @mingle-21402 @domain_marketing @mew_regression
  Scenario Outline: Verify the objects on store details page.
    Given I am on stores page
    When I enter "<search_term>" in the search store field and search
    Then I should see matching store counts on search results page for the given search keyword in list view
    Examples:
      | search_term         |
      | 10022               |
      | New York, NY        |
      | 59th street and Lexington avenue  |

  @regression @use_new_regression @use_real_device @mew_regression
  Scenario Outline: Verify incoming links to Stores Locator page
    Given I visit the mobile web home page
    When I tap on "<stores_link>" to visit the Stores Locator page
    Then I should be on the stores home page

    Examples:
      | stores_link  |
      | footer       |
      | menu         |

  @mingle-21219 @mingle-21657 @regression @mew_regression
  Scenario: Verify the objects on Initial landing page.
    Given I am on stores page
    Then I should see following elements on "stores_locator" page:
      | search_title    |
      | search_input    |
      | search_near_me  |
      | search_filters  |
      | search_button   |
      | search_all      |
    And I should see the correct filter dropdown options
      | All Stores                  |
      | Retail Stores               |
      | Outlet Stores               |


  @mingle-25415 @mew_regression
  Scenario Outline: Verify the stores error messages
    Given I am on stores page
    When I enter "<keyword>" in the search store field and search
    # Message contains quotes, so must use another delimiter
    Then I should see the related |<error_message>|
  Examples:
    | keyword | error_message  |
    | abcdefg | Sorry, we could not find any Bloomingdale's stores within 50 miles of "abcdefg". Please refine your search, or search by City and State or Zip. |
    | 19999   | Sorry, we could not find any Bloomingdale's stores within 50 miles of "19999". Please refine your search, or search by City and State or Zip.   |

  @mingle-21660 @regression @use_new_regression @use_real_device @mew_regression
  Scenario Outline: Verify the stores filter by store type
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I select "<facet_value>" facet value for the filter by services
    And I should apply the facet value selection
    And I should see the filter by stores section in the list view
    And I should verify stores filtered by the filter by services in the list view
    Examples:
      | facet_value       |
      | Retail Stores     |
      | Outlet Stores     |

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
    When I enter "10022" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I should verify the map view

  @mingle-22162 @mew_regression
  Scenario: Verify the store all events details
    Given I am on deeplink stores page
    Then I should navigate to a random event from the store detail page
    And I should verify the details of the event
    And I return to the store details page from event details modal
    And I should navigate to the view all events section
    And I navigate to first event from the store events page
    And I should verify the availability of the add to calender button
