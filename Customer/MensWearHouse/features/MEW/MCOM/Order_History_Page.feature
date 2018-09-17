###############################################
# Program:  <Program name>
# Project:  Tux Mensweare House
# Story:
# Author:   Team
# Date  :   Feb 20,2017
# Reviewer:
###############################################
Feature: Verification of Order History features for MensWearHouse in MEW

  @project_menswearhousedigital @feature_order_details @use_wip @domain_customer_management @priority_medium @release_17A @B-70014
  Scenario: Verify customer should see the shipping verbiage for tuxedo item during "Processing" status.
    Given I visit the mobile web homepage as a signed in user
    When I add an "Tuxedo" product to bag
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I navigate to myAccount page
    And I navigate to the order detials page as a signed in user
    Then I verify below shipping verbiage in OD page for "processing" status
      | "Tuxedo Rentals will ship 10-14 days prior to event date" |

  @project_menswearhousedigital @feature_order_details @use_wip @domain_customer_management @priority_medium @release_17A @B-70014
  Scenario: Verify customer should see the shipping verbiage for tuxedo item during "Processing" status. with mixed bag items
    Given I visit the mobile web homepage as a signed in user
    When I add an "Tuxedo" product to bag
    And I add a "available and orderable" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    And I click on tux_terms and conditions checkbox
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I navigate to myAccount page
    And I navigate to the order detials page as a signed in user
    Then I verify below shipping verbiage in OD page for "processing" status
      | "Tuxedo Rentals will ship 10-14 days prior to event date" |