#-----------------------------------------
# Author: Eric L
# Date Created: June 29, 2017
# Last Updated:
# Updated by:
#-----------------------------------------

Feature: Verify the responsive bag features

@bag_order_summary @bag @release_17ZA @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the order summary
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to core responsive bag page
    Then I verify that the core bag is displayed
  ### The expected content includes the display of bag items and order summary.

@free_shipping_item @bag_order_summary @bag @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the sticky order summary
    Given I visit the web site as a guest user
    When I add an "prod_available and free_shipping" product to my bag
    And I add an "orderable" product to my bag
    And I navigate to core responsive bag page
    Then I verify that the core bag is displayed
    And I verify that sticky order summary exists
  ### The expected content includes the display of bag items and order summary.

@bag_order_summary @bag @update_quantity @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the update quantity functionality
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to core responsive bag page
    And I change the quantity dropdown to 2 on bag page
    Then I verify the updated quantity is equal to 2

@bag_order_summary @bag @checkout_button @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the checkout button available
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to core responsive bag page
    Then I verify "checkout" button is available


@bag_order_summary @bag @continue_shopping_button @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the continue shopping button available
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to core responsive bag page
    Then I verify "continue_shopping" button is available

@bag_order_summary @bag @checkout_button @sign_in_page @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the checkout button functionality for guest user
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to core responsive bag page
    And I click the "checkout" button on the responsive bag page
    Then I should go to signedIn page

@bag_order_summary @bag @remove_button @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the remove functionality
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to core responsive bag page
    And I click the "remove" button on the responsive bag page
    Then I verify the item is removed on responsive bag page

@free_shipping_item @bag_order_summary @bag @promo_code @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the Promo Functionality
    Given I visit the web site as a guest user
    When I add an "prod_available and free_shipping" product to my bag
    And I add an "orderable" product to my bag
    And I navigate to core responsive bag page
    And I apply promo code "valpak10" on responsive bag
    Then I verify promotion is applied

@bag_order_summary @bag @header_and_footer @release_18A @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the bag header and bag footer displayed on the shopping bag
    Given I visit the web site as a guest user
    When I add a "orderable and available" product to my bag
    And I navigate to core responsive bag page
    Then I verify header and footer on the bag

@free_shipping_item @bag_order_summary @bag @promo_code @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the Promo Functionality
    Given I visit the web site as a guest user
    When I add an "prod_available and free_shipping" product to my bag
    And I add an "orderable" product to my bag
    And I navigate to core responsive bag page
    And I apply promo code "valpak10" on responsive bag
    Then I verify promotion "pricing" is visible

@free_shipping_item @bag_order_summary @bag @promo_code @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the Promo Functionality
    Given I visit the web site as a guest user
    When I add an "prod_available and free_shipping" product to my bag
    And I add an "orderable" product to my bag
    And I navigate to core responsive bag page
    And I apply promo code "fdsjhfsdh" on responsive bag
    Then I verify promotion "error message" is visible
