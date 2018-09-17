#Mobile Lean Lab BCOM
#Created: 8/29/2017
#Author: Maria Ortega
#Version-one Story: B-

Feature: BCOM::Wish List Page

  Scenario: Verify main image or swatch updates after I choose a random color swatch
    Given I goto Mobile Home page
    When I search for "lipsticks"
    When I select a random Wish List icon on browse page
    Then I select a random color swatch from the Wish List modal
    And I verify that the image updates to the color selected

  Scenario: Verify I can add a product to bag from wish list page
    Given I goto Mobile Home page
    When I search for "lipsticks"
    When I select a random Wish List icon on browse page
    Then I select a random color swatch from the Wish List modal
    And I add to the Wish List
    And I navigate my wish list page
    When I am on Wish List page I verify image's color name
    And I add product to my bag from Wish List page
    Then I verify product's color in shopping bag is the same as color selected on Wish List page

  Scenario: Verify that when navigated to PDP from Wish List the color in PDP matches the Wish List color
    Given I goto Mobile Home page
    When I search for "lipsticks"
    When I select a random Wish List icon on browse page
    Then I select a random color swatch from the Wish List modal
    And I add to the Wish List
    And I navigate my wish list page
    When I am on Wish List page I verify image's color name
    And I navigate to PDP from Wish List
    Then I verify that the color matches the color on Wish List

  Scenario: Verify that when selected a color swatch the image displayed matches with image on PDP
    Given I goto Mobile Home page
    When I search for "lipsticks"
    When I select a random Wish List icon on browse page
    Then I select a random color swatch from the Wish List modal
    And I check the image link
    And I add to the Wish List
    And I navigate my wish list page
    And I navigate to PDP from Wish List
    Then I verify that the image displayed matches the image on Wish List





