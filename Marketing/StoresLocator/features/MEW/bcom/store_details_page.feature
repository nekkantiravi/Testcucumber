Feature: Feature :: As a customer, I want to be able to see more details about a store I have searched for
  Story Covered :: http://mingle/projects/mobile_tech/cards/21402
  Story Description: BM2 :: Store Locator :: Store Details Page

  @mingle-21402 @domain_marketing @mew_regression
  Scenario: Verify the objects on store details page.
    Given I am on stores page
    When I enter "10022" input in store search
    And I submit the search on store search
    Then I should see a list of stores on stores search page
    When I tap on "59th Street" store in search results page
    Then I should be on Store details page
    And I should see header with "BCOM Logo", "GN" and "BAG" icon
    And I should see "Stores & Events" page header
    And I should see following objects on stores details page
      | store name       |
      | address          |
      | phone no.        |      # Only on a call link; not displayed
      | Events           |
      | Services section |
      # | Restaurants      |    # No longer displayed on BCOM store
    # And I should see 'Make this my default store' button
    And I should see footer section


  @mingle-21402 @domain_marketing @mew_regression
  Scenario: Verify the functionality of back button on header on Stores details page.
    Given I am on stores page
    When I enter "10022" input in store search
    And I submit the search on store search
    Then I should see a list of stores on stores search page
    When I tap on "59th Street" store in search results page
    Then I should be on Store details page
    When I tap on browser "back" button
    Then I should be navigated back to search results page

  # this test will not run if store hours are not set up
  @mingle-21402 @manual @domain_marketing @mew_regression
  Scenario: Verify the functionality when hours section is tapped on Stores details page.
    Given I am on stores page
    When I enter "10022" input in store search
    And I submit the search on store search
    Then I should see a list of stores on stores search page
    When I tap on "59th Street" store in search results page
    Then I should be on Store details page
    When I click on hours section
    Then I should see hours section expands to show 7 days
    When I click on hours section
    # Now shows only today's hours or all 7 days, with highlight on today's date
    Then I should see hours section collapses to show 0 days

    # This test is currently manual as hours are env specific
  @mingle-21402 @domain_marketing @manual @mew_regression
  Scenario: Verify Events sections gives the summary of events
    Given I am on stores page
    When I enter "10022" input in store search
    And I submit the search on store search
    Then I should see a list of stores on stores search page
    When I tap on "59th Street" store in search results page
    Then I should be on Store details page
    Then I should see the summary of date, name and hours
    And I should also see 'View All Events' link
    When I tap on View All Events link
    # Looks like no more modal; & no linke - can only see event details on the store page
    # Then I should be directed to Events modal


  @mingle-21402 @domain_marketing @mew_regression
  Scenario: Verify the services details on Stores details page.
    Given I am on stores page
    When I enter "10022" input in store search
    And I submit the search on store search
    Then I should see a list of stores on stores search page
    When I tap on "59th Street" store in search results page
    Then I should be on Store details page
    And I should see Services section
    And I should see all the services coming from WSSG
    # Must expand variable lists
    And I should see following service details
      | Service name |
      | description  |
