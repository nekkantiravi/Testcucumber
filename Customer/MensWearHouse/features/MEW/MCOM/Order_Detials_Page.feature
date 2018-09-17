###############################################
# Program:  <Program name>
# Project:  Tux Mensweare House
# Story:
# Author:   Team
# Date  :   Feb 20,2017
# Reviewer:
###############################################
Feature: Verification of Order details features for MensWearHouse in MEW

  @project_menswearhousedigital @feature_order_details @use_wip @domain_customer_management @priority_medium @release_17A @B-70015
  Scenario: : Verify customer should see the Tuxedo product name for tuxedo items in OD page
    Given I visit the mobile web site as a registered user
    When I add a tuxedo product to bag
    And I navigate to shopping bag page using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page using mobile website as a "signed in" user
    And I navigate back to "my account" page using mobile website
    And I navigate to order details page using mobile website
    Then I should see below tux items in mobile website od page
      | .reservation_number |
      | .tux_quantity       |


  @project_menswearhousedigital @feature_order_details @use_wip @domain_customer_management @priority_medium @release_17A @B-70015
  Scenario: Verify customer should see the Tuxedo product name for tuxedo items, when mixed items are present in the OD page
    Given I visit the mobile web site as a registered user
    When I add a tuxedo product to bag
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page using mobile website as a "signed in" user
    And I navigate back to "my account" page using mobile website
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page using mobile website
    #Then I should see "Reservation #" below the tux details per the current site functionality
    #And I should see quantity "Qty" displayed as "1"
    Then I should see below tux items in mobile website od page
      | .reservation_number |
      | .tux_quantity       |

  @project_menswearhousedigital @feature_order_details @use_wip @domain_customer_management @priority_medium @release_17A @B-70015
  Scenario Outline: Verify customer should see the Tuxedo product name for tuxedo items in OD page
    Given I visit the mobile web site as a registered user
    When I add a tuxedo product to bag
    And I navigate to shopping bag page using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page using mobile website as a "signed in" user
    And I navigate back to "my account" page using mobile website
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page using mobile website
    And I select tuxedo "<links>" on od page
  #  Then I should redirect to reservation summary page
    Examples:
      | links         |
      | product_name  |
  @project_menswearhousedigital @feature_order_details @use_wip @domain_customer_management @priority_medium @release_17A @B-70015
  Scenario Outline: Verify customer should see the Tuxedo product name for tuxedo items, when mixed items are present in the OD page
    Given I visit the mobile web site as a registered user
    When I add a tuxedo product to bag
    And I add a "available and orderable" product to my bag
    And I navigate to shopping bag page using mobile website
    And I checkout until I reach the order review page using mobile website as an "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page using mobile website as a "signed in" user
    And I navigate back to "my account" page using mobile website
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page using mobile website
    And I select tuxedo "<links>" on od page
  #  Then I should redirect to reservation summary page
    Examples:
      | links         |
      | product_name  |