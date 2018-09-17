#Mobile Lean Lab BCOM
#Created: 7/21/2017
#Author: Maria Ortega
#Version-one Story: B-85492

Feature: BCOM::browse search Wish List

  Scenario: As a user, I verify_page should be able to add to Wish List from  a browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | MEN          |
      | All Clothing |
      | Polos        |
    When I select a random Wish List icon on browse page
    Then I select a size
    And I add to the Wish List
    Then I verify that Wish List icons are filled
    And I verify Wish List has product recently added

  Scenario: Verify an item without size and color is added to Wish List without opening Wish List modal
    Given I visit the mobile web site as a guest user
    When I search for "Roxxlyn phone cases"
    When I select a random item from the results
    Then I verify that item is added
    Then Wish List header icon should contain the product just added

  Scenario: Verify I can tap out of the Wish List modal
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | MEN          |
      | All Clothing |
      | Polos        |
    When I select a random Wish List icon on browse page
    When I tap outside of the modal
    Then Wish List modal should close
    When I select a random Wish List icon on browse page
    And I tap on the X
    Then Wish List modal should close

  Scenario: Verify tool tip display upon page load
    Given I goto Mobile Home page
    Then the star on the header should display a tool tip for 5 seconds


