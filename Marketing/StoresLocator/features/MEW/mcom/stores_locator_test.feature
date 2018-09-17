Feature: used for testing the invalid session ID error

  @mingle-21226 @use_smoke @use_mew_dsv_next @regression @use_mew_bat @mew_regression
  Scenario: Verify the store detail page
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I navigate to "Macy's Union Square" store detail page
    And I should verify the store details


  @regression @use_real_device @mew_regression
  Scenario: Verify map view
    Given I am on stores page
    # TODO: Determine how to 'varify the map view' beyond simple existance?
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I should verify the map view

  # @mingle-21660 @regression @use_new_regression @use_real_device @mew_regression
  # Scenario Outline: Verify the stores filter by services
  #   Given I am on stores page
  #   # WIP: Mismatch between stores service and displayed values
  #   When I enter "94105" in the search store field and search
  #   Then I should see stores on search results page for the given search keyword in list view
  #   And I select "<facet_value>" facet value for the filter by services
  #   And I should apply the facet value selection
  #   And I should see the filter by stores section in the list view
  #   And I should verify stores filtered by the filter by services in the list view

  #   Examples:
  #     | facet_value       |
  #     | Furniture Gallery |
  #     | Personal Shopper  |

  @mingle-21226 @use_smoke @use_mew_dsv_next @regression @use_mew_bat @mew_regression
  Scenario: Verify the store detail page
    Given I am on stores page
    When I enter "94105" in the search store field and search
    Then I should see stores on search results page for the given search keyword in list view
    And I navigate to "Macy's Union Square" store detail page
    And I should verify the store details


