# Author: BOPSCxImprovements Project QE Team
# Date Created: 10/18/2016
# Version One: B-54582
Feature: PDP, Store Overlay

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify PDP store overlay by navigating through Check store availability
    Given I visit the web site as a <user_type> user
    And I navigate to product PDP page
      | multiple_upc   | true |
      | available_bops | true |
    And I select available size of the product
    And I am going to check local stores on Radical pdp page
    Then I should see "search field" is present in change pickup store dialog window
    And I should see "NEAR ME" is present in change pickup store dialog window
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify PDP store overlay by navigating through check other stores
    Given I visit the web site as a <user_type> user
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
    And I select available size of the product
    And I am going to check local stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    And I navigate to product PDP page
      | multiple_upc   | true |
      | available_bops | true |
    And I select available size of the product
    And I am going to check local stores on Radical pdp page
    Then I should see "search field" is present in change pickup store dialog window
    And I should see "NEAR ME" is present in change pickup store dialog window
    Examples:
      | user_type  |
      | guest      |
      | registered |