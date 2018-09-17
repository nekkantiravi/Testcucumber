Feature: Add To Bag Functionality

  Scenario Outline: To verify select size
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    And I select the size of the product
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify click on add to bag button
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    And I select the size of the product
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |


  Scenario Outline: Verify user is able to see "Add to Bag"
    Given I visit the web site as a <user_type> user
    When  I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    And I select the size of the product
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And I should see following elements on "add_to_bag" page:
      | header                 |
      | product_name           |
      | color                  |
      | size                   |
      | productImage           |
      | sub_total_price        |
      | item_quantity          |
      | go back                |
      | checkout               |
    Examples:
      | user_type  |
      | guest      |
      | registered |


  Scenario Outline: Verify user is able to clicking "go back" button from "Add to Bag"
    Given I visit the web site as a <user_type> user
    When  I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    And I select the size of the product
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And I should be on product details page on clicking go back button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: Verify user is able to  "checkout" from "Add to Bag" page
    Given I visit the web site as a <user_type> user
    When  I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    And I select the size of the product
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And I should be on shopping bag page on clicking checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |



