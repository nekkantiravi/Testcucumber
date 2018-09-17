###############################################
# Program:  <Program name>
# Project:  Tux Mensweare House
# Story:
# Author:   Team
# Date  :   Jan 12,2017
# Reviewer:
###############################################
Feature: Verification of Order details features for MensWearHouse

#  @project_menswearhousedigital @feature_order_details @use_project @domain_customer_management @priority_medium @release_17C @B-65769
#  Scenario: Verify tux item details on order details page for tuxedo item only
#    Given I visit the web site as a guest user
#    And I create a new profile
#    When I add a tuxedo product to bag
#    And I navigate to shopping bag page
#    And I checkout until I reach the order review page as a "signed in" user
#    And I click on tux_terms and conditions checkbox
#    And I checkout until I reach the order confirmation page as a "signed in" user
#    And I click on the Continue Shopping button
#    And I navigate to order details page as a "signed" user
#    Then I should see the "image currently unavailable" for the tux item on "od" page
#    And  I should see below tux items in od page
#      | .reservation_number |
#      | .tux_quantity       |
#    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page
#
#  @project_menswearhousedigital @feature_order_details @use_project @domain_customer_management @priority_medium @release_17C @B-65769
#  Scenario: Verify customer should see tuxedo image, when mixed items are present in the order details page
#    Given I visit the web site as a guest user
#    And I create a new profile
#    And I add a "available and orderable" product to my bag
#    When I add a tuxedo product to bag
#    And I navigate to shopping bag page
#    And I checkout until I reach the order review page as a "signed in" user
#    And I click on tux_terms and conditions checkbox
#    And I checkout until I reach the order confirmation page as a "signed in" user
#    And I click on the Continue Shopping button
#    And I navigate to order details page as a "signed" user
#    Then I should see the "image currently unavailable" for the tux item on "od" page
#    And  I should see below tux items in od page
#      | .reservation_number |
#      | .tux_quantity       |
#    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page
#
#
#  @project_menswearhousedigital @feature_order_details @use_project @domain_customer_management @priority_medium @release_17C @B-65769
#  Scenario: Verify tux item details on order details page for tuxedo item only
#    Given I visit the web site as a guest user
#    And I create a new profile
#    When I add a tuxedo product to bag
#    And I navigate to shopping bag page
#    And I checkout until I reach the order review page as a "signed in" user
#    And I click on tux_terms and conditions checkbox
#    And I checkout until I reach the order confirmation page as a "signed in" user
#    And I click on the Continue Shopping button
#    And I navigate to order details page as a "signed" user
#    And I select return items button in "OD" page
#    And I should navigate to returns page
#    Then I should not see below buttons on returns page for tuxedo product
#      | .tuxedo_checkbox |
#      | .tuxedo_dropdown |
#      | .continue_button |
#    And I should see below tuxedo message for tux item on returns page
#      | Please return your tuxedo using the prepaid UPS packing slip included in your garment bag. Alternatively, you can return it at any Macy’s Tuxedo Shop or men’s department. |
#
#  @project_menswearhousedigital @feature_order_details @use_project @domain_customer_management @priority_medium @release_17C @B-65769
#  Scenario: Verify tux item details on order details page for tuxedo item only
#    Given I visit the web site as a guest user
#    And I create a new profile
#    And I add a "available and orderable" product to my bag
#    When I add a tuxedo product to bag
#    And I navigate to shopping bag page
#    And I checkout until I reach the order review page as a "signed in" user
#    And I click on tux_terms and conditions checkbox
#    And I checkout until I reach the order confirmation page as a "signed in" user
#    And I click on the Continue Shopping button
#    And I navigate to order details page as a "signed" user
#    And I select return items button in "OD" page
#    And I should navigate to returns page
#    Then I should not see below buttons on returns page for tuxedo product
#      | .tuxedo_checkbox |
#      | .tuxedo_dropdown |
#    And I should see below tuxedo message for tux item on returns page
#      | Please return your tuxedo using the prepaid UPS packing slip included in your garment bag. Alternatively, you can return it at any |
#    And I should see macys item enabled on returns page
#    And I should see continue button on return selections page
#
#
#  @project_menswearhousedigital @feature_order_details @use_project @domain_customer_management @priority_medium @release_17A @B-65769
#  Scenario Outline: Verify links on tux item in order details page for tuxedo only
#    Given I visit the web site as a guest user
#    And I create a new profile
#    When I add a tuxedo product to bag
#    And I navigate to shopping bag page
#    And I checkout until I reach the order review page as a "signed in" user
#    And I click on tux_terms and conditions checkbox
#    And I checkout until I reach the order confirmation page as a "signed in" user
#    And I click on the Continue Shopping button
#    And I navigate to order details page as a "signed" user
#    And I select "<links>" link for tuxedo item on "od" page
#    Then I should see below message for processing status in 'od' page
#      | Tuxedo Rentals will ship 10-14 days prior to event date |
#    And I should redirect to tux pdp page
#    Examples:
#      | links         |
#      | product_name  |
#      | product_image |
#
#  @project_menswearhousedigital @feature_order_details @use_project @domain_customer_management @priority_medium @release_17A @B-65769
#  Scenario Outline: Verify links on tux item in order details page for tuxedo only
#    Given I visit the web site as a guest user
#    And I create a new profile
#    When I add a tuxedo product to bag
#    And I add a "available and orderable" product to my bag
#    And I navigate to shopping bag page
#    And I checkout until I reach the order review page as a "signed in" user
#    And I click on tux_terms and conditions checkbox
#    And I checkout until I reach the order confirmation page as a "signed in" user
#    And I click on the Continue Shopping button
#    And I navigate to order details page as a "signed" user
#    And I select "<links>" link for tuxedo item on "od" page
#    Then I should see below message for processing status in 'od' page
#      | Tuxedo Rentals will ship 10-14 days prior to event date |
#    And I should redirect to tux pdp page
#    Examples:
#      | links         |
#      | product_name  |
#      | product_image |

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify tux item details on order details page for tux_processing status
    Given I navigate to order details page as a "guest" user using "tux_processing" order
    When I navigate to OD page
    And I should see the "image currently unavailable" for the tux item on "od" page
    Then  I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify tux item details on order details page for tux_processing status, with mixed bag
    Given I navigate to order details page as a "guest" user using "tux_processing" order
    When I navigate to OD page
    And I should see the "image currently unavailable" for the tux item on "od" page
    Then I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify tux item details on order details page for tux_delivered status
    Given I navigate to order details page as a "guest" user using "tux_delivered" order
    When I navigate to OD page
    And I should see the image currently unavailable for the tux item on "od" page
    Then I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see below message for processing status in "oh" page
      | Tuxedo Rentals will ship 10-14 days prior to event date |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify tux item details on order details page for tux_delivered status, with mixed item
    Given I navigate to order details page as a "guest" user using "tux_delivered" order
    When I navigate to OD page
    And I should see the image currently unavailable for the tux item on "od" page
    Then I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see below message for processing status in "oh" page
      | Tuxedo Rentals will ship 10-14 days prior to event date |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify the tuxedo intransit status on ode page
    Given I navigate to order details page as a "guest" user using "tux_intransit" order
    When I navigate to OD page
    And I should see the returns button on OD page
    Then I should see the image currently unavailable for the tux item on "od" page
    And I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see below message for processing status in "oh" page
      | Tuxedo Rentals will ship 10-14 days prior to event date |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify the tuxedo intransit status on ode page
    Given I navigate to order details page as a "guest" user using "tux_intransit" order
    When I navigate to OD page
    And I should see the returns button on OD page
    Then I should see the image currently unavailable for the tux item on "od" page
    And I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see below message for processing status in "oh" page
      | Tuxedo Rentals will ship 10-14 days prior to event date |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify the tuxedo intransit status on ode page
    Given I navigate to order details page as a "guest" user using "tux_intransit" order
    And I navigat to OD page
    When I select return items button in "OD" page
    And I should navigate to returns page
    Then I should not see below buttons on returns page for tuxedo product
      | .tuxedo_checkbox |
      | .tuxedo_dropdown |
      | .continue_button |
    And I should see below tuxedo message for tux item on returns page
      | Please return your tuxedo using the prepaid UPS packing slip included in your garment bag. Alternatively, you can return it at any Macy’s Tuxedo Shop or men’s department. |

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
  Scenario: Verify the tuxedo intransit status on ode page, mixed bag
    Given I navigate to order details page as a "guest" user using "tux_intransit" order
    And I add a "available and orderable" product to my bag
    And I should be navigated to OD page
    When I select return items button in "OD" page
    And I should navigate to returns page
    Then I should not see below buttons on returns page for tuxedo product
      | .tuxedo_checkbox |
      | .tuxedo_dropdown |
      | .continue_button |
    And I should see below tuxedo message for tux item on returns page
      | Please return your tuxedo using the prepaid UPS packing slip included in your garment bag. Alternatively, you can return it at any Macy’s Tuxedo Shop or men’s department. |

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
    Given I navigate to order details page as a "guest" user using "tux_canceled" order
    And I should be navigated to OD page
    When I verify the ability to cancel the order in order details page
    And I should see the "image currently unavailable" for the tux item on "od" page
    Then  I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page as zero

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16I
    Given I navigate to order details page as a "guest" user using "tux_canceled" order
    And I should be navigated to OD page
    When I verify the ability to cancel the order in order details page
    Then I should see the "image currently unavailable" for the tux item on "od" page
    And  I should see below tux items in od page
      | .reservation_number |
      | .tux_quantity       |
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on "od" page as zero
