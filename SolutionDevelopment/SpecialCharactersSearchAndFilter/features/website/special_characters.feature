#1/10/2018

Feature: Verify special characters on the site do not result in broken pages

# below three scenarios will take characters from charVar environment or run variable
# or if this variable is not present - scenario will go through full list of special characters to verify
#| .   |
#| *   |
#| &   |
#| -   |
#| ?   |
#| +   |
#| '   |
#| !   |
#| ®   |
#| ô   |
#| ó   |
#| é   |
#| /   |
#| \   |
#| ,   |
#| _   |
#| (   |
#| )   |
#| è   |
#| ë   |
#| ™   |
#| ·   |
#| <   |
#| >   |
#| @   |
#| $   |
#| %   |
#| :   |
#| ;   |


  @special_character
  Scenario: Verify that user can navigate to faceted browse page by clicking on brand name with special character from brand index page
    Given I visit the web site as a guest user
    When I navigate to a designer index page
    Then I select a brand with the special character and verify landing on designer landing page


  @special_character
  Scenario: Verify that user can search for the brand name with special character
    Given I visit the web site as a guest user
    When I search for brand name with the following special character and verify landing on search page


#    This scenario will hover over all FOBs in order and click on a
#     random link with each of the special characters
  @special_character
  Scenario: Validate all links with special characters in flyouts
    Given I visit the web site as a guest user
    And I validate all links with special characters by clicking on them and verifying they navigate to correct pages


  @special_character @wip
  Scenario: Verify that user can filter by designer name with special character on browse pages
    Given I visit the web site as a guest user
    When I navigate to the following category under the fob listed:
      | women   | all dresses |
      | beauty  | all lips    |
      | shoes   | all shoes   |
      | home    | dinnerware  |
    Then I verify user can filter using facet by the brand name with the following special character:
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


  @special_character @wip
  Scenario Outline: Verify that user can filter by attribute with special character on browse pages
    Given I visit the web site as a guest user
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


  @special_character
  Scenario: Verify user can search for an attribute with special characters
    Given I visit the web site as a guest user
    And I search for the following attribute with special character:
      | heel heigh <2"          |
      | V-neck                  |
      | cashmere/cashmere blend |
      | cashmere + wool         |


  @special_character
  Scenario: Verify user can filter on search page when searched for brand with % sign
    Given I visit the web site as a guest user
    When I search for "100%"
    Then I verify that products displayed have 100% in the product name


    # This scenario covers functionality that will not always be on the site
    # If browser is not present - Scenario will fail logging that banner was not present
  @special_character @wip
  Scenario: Verify user can navigate to one day sale page using the banner
    Given I visit the web site as a guest user
    And I navigate to "home" category page
    When I click on "one day home sale" banner if present
    Then I should land on browse page with sale items


# This will get list of designers from the "designer" run variable or the fixed list like below
#      | M·A·C                                     |
#      | Bickley + Mitchell                        |
#      | UGG®                                      |
#      | SPANX®                                    |
#      | BECCA® by Rebecca Virtue                  |
#      | HERMÈS                                    |
#      | L*Space                                   |
#      | Bric's                                    |
#      | Lancôme                                   |
#      | Re:named                                  |
#      | C/MEO Collective                          |
#      | Nambé                                     |
#      | University of Today, Dreamers of Tomorrow |
#      | 2(X)IST                                   |
#      | Sub_Urban Riot                            |
#      | SAM.                                      |
#      | b.tempt'd by Wacoal                       |
#      | Zoë Chicco                                |
#      | Lipault - Paris                           |
#      | All-Clad                                  |
#      | For Love & Lemons                         |
#      | GRIT&ground                               |
#      | Supergoop!                                |
  @special_character
  Scenario: Verify that user can navigate to faceted browse page by clicking on brand name from the list with special character from brand index page
    Given I visit the web site as a guest user
    When I navigate to a designer index page
    Then I verify user lands on designer browse page when clicking on the designer name is present


