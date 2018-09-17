@mingle-19402 @p1 @component_test @store_locator @bcom @domain_marketing
Feature: As a customer, I want to be able to navigate to store locator and search for stores around me
  Story Covered - 19042 :: http://mingle/projects/mobile_tech/cards/19042
  Story Description: BM2 :: Stores & Events :: Initial Landing Page + List View


  @mew_regression
  Scenario: Verify the objects on Initial landing page.
    Given I am on stores page
    And I should see "Single Entry search" field
    And I should see "Find a store" as header text
    Then I should see header with "BCOM Logo", "GN" and "BAG" icon
    And I should see the magnifying glass icon left-justified
    And I should see the "Near me" store locator button to right of "Single Entry search" field
    And I should see footer section
    And I should see suggestion text pre-populated
    ### This is HTML5/browser code; not able to test
    # When I tap on the "Single Entry search" field
    # Then I should see Suggestion text disappeared


    @mew_regression
  Scenario: Verify the functionality of 'Single Entry search' field.
    Given I am on stores page
    When I enter valid "n" in search bar
    Then I should see 'X' icon right-justified on "Single Entry search" field
    When I tap on 'X' icon
    Then I should see the input cleared

  @use_new_regression
  @mew_regression
  Scenario Outline: Verify the search behavior by typing valid zip code.
    # NOTE: entering '94106' yields "Huastusco, Ver.", a city in Mexido
    Given I am on stores page
    When I enter valid "<input1>" in search bar
    And I tap on enter/go on the keyboard
    Then I should navigate to new search results page with stores near to "<input1>"
    When I enter valid "<input2>" in search bar
    And I tap on enter/go on the keyboard
    Then I should navigate to new search results page with stores near to "<input2>"
    Examples:
      | input1  | input2  |
      | 10003   | 10022   |
      # 94106 gives error right now
      | 94106   | 94105   |
      | SF      | New York  |
      | CA      | NY      |


    @mew_regression
  Scenario: Verify the list view of Stores Landing page.
    Given I am on stores page
    When I enter valid "10022" in search bar
    And I tap on enter/go on the keyboard
    Then I should navigate to new search results page
    And I should see header with "BCOM Logo", "GN" and "BAG" icon
    And I should see "Find a store" as header text
    And I should see "Single Entry search" field
    And I should see the "Near me" store locator button to right of "Single Entry search" field
    # Only filter by "Retail stores" or "Outlet stores"
    # And I should see the "Filter stores by services" store locator button
    # No map view at all?!
    # And I should see toggle between "list view" & "map view"
    And I should see footer section
    And I should see any store within 250 miles to input searched
    And Every store result should have following details
      | Store name                    |
      | Open or close status          |
      | Address                       |
      | Distant from the search point |
      | Phone number                  |
      | Directions                    |
      | Website link                  |
