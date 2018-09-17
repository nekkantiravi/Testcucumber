###############################################
# Program:  <Program name>
# Project:  Tux Mensweare House
# Story:    B-65438, B-64874, B-65044, B-65667
# Author:   Team
# Date  :   06th Jan 2017
# Reviewer:
###############################################

Feature: Verify Order History page changes related to MenswearHouse project.

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16T
  Scenario:  Verify tux item details on order hisory page for tux item only in order
    Given I visit the web site as a guest user
    When I associate "tux_processing" order in db
    And I select order status link from left navigation
    Then I should be navigated to OH page
    And I should see the image currently unavailable for the tux item on "OH" page
    And I should see the "reservation number" for the tux item on "OH" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16T
  Scenario Outline: Verify links on Tux item in order history page for tux item only in order
    Given I visit the web site as a guest user
    When I associate "tux_processing" order in db
    And I select order status link from left navigation
    Then I should be navigated to OH page
    Then I should see below message for processing status in "oh" page
      | Tuxedo Rentals will ship 10-14 days prior to event date |
    When I select "<links>" link for tuxedo item on "oh" page
    Then I should redirect to tux pdp page
    Examples:
      | links        |
      | product_image|
      | product_name |

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16T
  Scenario:  Verify tux item details on order hisory page for tux and macys item only in order
    Given I visit the web site as a guest user
    When I associate "tux_processing" order in db
    And I select order status link from left navigation
    Then I should be navigated to OH page
    Then I should see the "image currently unavailable" for the tux item on "oh" page
    And I should see the "reservation number" for the tux item on "OH" page
    And I should see quantity Qty as one for the tux item on "oh" page

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16T
  Scenario Outline: Verify links on Tux item in order history page for tux and macys item only in order
    Given I visit the web site as a guest user
    When I associate "tux_processing" order in db
    And I select order status link from left navigation
    Then I should be navigated to OH page
    Then I should see below message for processing status in "oh" page
      | Tuxedo Rentals will ship 10-14 days prior to event date |
    When I select "<links>" link for tuxedo item on "oh" page
    Then I should redirect to tux pdp page
    Examples:
      | links        |
      | product_image|
      | product_name |

  @project_menswearhousedigital @feature_ordermgmt @use_wip @domain_customer_management @priority_medium @release_16T
  Scenario Outline:  Verify shipping message information on order history page tux intransit order
    Given I visit the web site as a guest user
    When I associate "tux_processing" order in db
    And I select order status link from left navigation
    Then I should be navigated to OH page
    Then I should see below message for processing status in "oh" page
      | Tuxedo Rentals will ship 10-14 days prior to event date |
    Examples:
      |order_status|
      |tux_intransit|
      |tux_delivered|
  #    |processing   |