#Mobile Lean Lab BCOM
#Created: 7/21/2017
#Author: Maria Ortega
#Version-one Story: B-85492

Feature: BCOM::browse search Wish List

  Scenario: Verify I can successfully Edit a product on Wish List as a guest user
    Given I goto Mobile Home page
    When I navigate the global navigation menu as follows:
      | MEN      |
      | Clothing |
      | Polos    |
    When I choose a random Wish List icon on browse page
    Then I select a size
    And I add to the Wish List
    And I navigate to wish list page
    Then I tap on Full Details button to Edit my product
    Then On the wish list PDP I select a random size
    When I tap on "add to wish list" button
    And I tap on "View Wish List" button on the confirmation overlay
    Then on Wish list page I verify that the size selected displays


  Scenario: Verify I can successfully Remove a product
    Given I visit the mobile web site as a guest user
    When I search for "Roxxlyn phone cases"
    When I select a random item from the results
    Then I verify that item is added
    And I navigate to wish list page
    Then I remove the product from the wishlist
    Then I verify if the product is removed

