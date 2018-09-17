
Feature: Verify special characters on the mobile site do not result in broken pages

  Scenario: Verify that user can navigate to faceted browse page by clicking on brand name with special character from brand index page
    Given I visit the mobile web site as a guest user
    And I navigate to a designer index page
    When I click on brand name with the following special character:
      | .   |
      | *   |
      | @   |
      | $   |
      | %   |
      | &   |
      | -   |
      | ?   |
      | +   |
      | '   |
      | !   |
      | ®   |
      | ô   |
      | ó   |
      | é   |
      | (x) |
      | /   |
      | \   |
      | ,   |
      | _   |
      | (   |
      | )   |
      | è   |
      | ™   |
      | ·   |
      | <   |
      | >   |
    Then I should land on browse page faceted by corresponding brand name
    And I should see the site promotion tag contains the special character


  Scenario: Verify that user can search for the brand name with special character
    Given I visit the mobile web site as a guest user
    When I search for brand name with the following special character:
      | .   |
      | *   |
      | @   |
      | $   |
      | %   |
      | &   |
      | -   |
      | ?   |
      | +   |
      | '   |
      | !   |
      | ®   |
      | ô   |
      | ó   |
      | é   |
      | (x) |
      | /   |
      | \   |
      | ,   |
      | _   |
      | (   |
      | )   |
      | è   |
      | ™   |
      | ·   |
      | <   |
      | >   |
    Then I should land on search page faceted by corresponding brand name

  Scenario: Verify that user can navigate to category page that has % sign in the category name
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | women | 100% exclusives                      |
      | shoes | Buy More, Save More: Take 20-30% Off |

  Scenario: Verify that user can filter by designer name with special character on browse pages
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | women   | all dresses |
      | beauty  | all lips    |
      | shoes   | all shoes   |
      | home    | dinnerware  |
    And I filter using facet by the brand name with the following special character:
      | .   |
      | *   |
      | @   |
      | $   |
      | %   |
      | &   |
      | -   |
      | ?   |
      | +   |
      | '   |
      | !   |
      | ®   |
      | ô   |
      | ó   |
      | é   |
      | (x) |
      | /   |
      | \   |
      | ,   |
      | _   |
      | (   |
      | )   |
      | è   |
      | ™   |
      | ·   |
      | <   |
      | >   |
    Then I should see results filtered by corresponding brand name


  Scenario Outline: Verify that user can filter by attribute with special character on browse pages
    Given I visit the mobile web site as a guest user
    When I navigate to "all shoes" category under "shoes" fob
    And I filter using facet by the <attribute> with the <special_character>
    Then I should see results filtered by corresponding attribute
    Examples:
      | attribute     | special_character |
      | item type     | /                 |
      | heel height   | -                 |
      | heel height   | "                 |
      | heel height   | >                 |
      | heel height   | <                 |


  Scenario: Verify user can search for an attribute with special characters
    Given I visit the mobile web site as a guest user
    And I search for the following attribute with special character:
      | heel heigh <2"          |
      | V-neck                  |
      | cashmere/cashmere blend |
      | cashmere + wool         |
    Then I should land on corresponding search page

  # This scenario covers functionality that will not always be on the site
  # If browser is not present - Scenario will fail logging that banner was not present
  Scenario: Verify user can navigate to one day sale page using the banner
    Given I visit the mobile web site as a guest user
    And I navigate to "home" category page
    When I click on "one day home sale" banner if present
    Then I should land on browse page with sale items



