# Author: BOPSCxImprovements Project QE Team
# Date Created: 11/10/2016
# Version One: B-64284
Feature: Bag, Store Overlay

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: user can select an available store in a bag store overlay and the chosen store displayed in the bag
    Given I visit the web site as a <user> user
    When I add a "single_upc and available_bops and ship_available" product to my bag
    And I navigate to product PDP page
      | multiple_upc   | true |
      | available_bops | true |
      | ship_available | true |
    And I select available size of the product
    And I navigate to shopping bag page
    And I click store pickup availability link on bag page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    Then I should see "selector module store name" is present on shopping bag page

    Examples:
      | user       |
      | guest      |
      | registered |

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: bag overlay displayo
    Given I visit the web site as a guest user
    When I add a "single_upc and available_bops and ship_available" product to my bag
    And I navigate to product PDP page
      | multiple_upc   | true |
      | available_bops | true |
      | ship_available | true |
    And I select available size of the product
    And I navigate to shopping bag page
    And I click store pickup availability link on bag page
    And I enter zipcode and do search for available stores
    When I toggle item availability
    Then I should see "item availability" is present in change pickup store dialog overlay
    When I toggle map view
    Then I should see "map view" is present in change pickup store dialog overlay