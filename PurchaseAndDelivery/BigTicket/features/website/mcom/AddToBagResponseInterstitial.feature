# Author: Big Ticket Project QE Team
# Date Created: 02/22/2017
# Version One: B-66707

Feature: As a customer having just added one or more BigTicket items to my bag I want to see confirmation that the items were added as intended.

  @project_BT @domain_purchase_and_delivery @scenario1
  Scenario Outline: Verify user is able to see "Add to Bag confirmation" for BT standard product
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    And I add the product to bag
    Then I should be on ATB page with go back and checkout button
    And I should see following elements on "add_to_bag" page:
      | header                 |
      | product_name           |
      | line_item_price        |
      | productImage           |
      | sub_total_price        |
      | item_quantity          |
      | bag_product_color_size |
      | atb_baginfo            |
      | keep_shopping_button   |
      | checkout               |
    And I should be on product details page on clicking go back button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario2
  Scenario Outline: Verify user is able to "go back" or "checkout" from "Add to Bag confirmation" page
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_furniture and ONHAND" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    And I add the product to bag
    Then I should be on ATB page with go back and checkout button
    And I should be on shopping bag page on clicking checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario3
  Scenario Outline: Verify user is able to see "Add to Bag confirmation" in BT master product page
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_collection_master and orderable" product PDP page
    And I enter a zipcode on BT PDP page
    And I click on "bt_zipcode_submit_btn" on "product_display" page
    And I add random member product from PDP
    Then I should see the confirmation popup is closed on clicking add more button
    And I should see the shopping bag page on clicking checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario4
  Scenario Outline: Verify user is able to see "Add to Bag confirmation" in BT master product page with collections (pieces, sets)
    Given I visit the web site as a <user_type> user
    When I navigate to "BT_collection_master and orderable" product PDP page
    And I add random member product from PDP
    Then I should see the confirmation popup is closed on clicking add more button
    When I add random member product from PDP
    Then I should see the shopping bag page on clicking checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario5
  Scenario Outline: Verify user is able to see "Add to Bag confirmation" on Quick View overlay for BT products
    Given I visit the web site as a <user_type> user
    When I navigate to the "Furniture" browse page under "Home"
    And I quick view a random product on browse page
    And I add the item to the bag from quick view
    Then I should see add to Bag confirmation message on quickview overlay

    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario6 @manual
  Scenario Outline: Verify user is able to see "Add to Bag confirmation" in wish list page for BT products
    Given I visit the web site as a <user_type> user
    When I navigate to "furniture and orderable" product PDP page
    And I add the item to the List and navigate to wish list page
    When I click on ADD TO BAG button for available|on-order product from wish list
    Then I should see "Add to Bag confirmation" overlay for BT standard product
    And I verify the item name, qty, color and price
    And I should see a "CONTINUE SHOPPING" button
    And I should see a "CHECKOUT" button
    And I should see a "close" button
    When I click on "CONTINUE SHOPPING" button
    Then I should see the confirmation popup is closed
    When I click on "CHECKOUT" button
    Then I should be landing on shopping bag page
    When I click on "close" button
    Then I should see the confirmation popup is closed

    Examples:
      | user_type  |
      | guest      |
      | registered |


#  Note: Verify the "Add to Bag confirmation" on Quick View overlay in search, browse, member products in master PDP page, Shopping bag & PDP product recommendations