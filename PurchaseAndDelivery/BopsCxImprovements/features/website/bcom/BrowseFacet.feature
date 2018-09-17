# Author: BOPSCxImprovements Project QE Team
# Date Created: 11/7/2016
# Version One: B-64278, B-67587, B-65684, B-65684, B-63716
Feature: Facet, Store Overlay

  Background:
    Given I visit the web site as a guest user
    And bops user pc zip code set as "10001"

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: bops overlay pre-populated by zipcode from facet
    When I search for products
      | available_bops | true |
    And I initiate store change for bops facet
    Then I should see zip code search field is auto populated with "last_facet_search" value

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: store selected in bops overlay transfers to bops facet
    When I search for products
      | available_bops | true |
    And I change location to the 1st store with zipcode entered in 50 miles
    Then the 1st store is selected in bops facet
    And bops facet search distance is 50 miles

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: store selected in bops overlay doesn't transfer to bops facet if overlay was closed by cross icon
    When I search for products
      | available_bops | true |
    And I initiate store change for bops facet
    And I enter zipcode and do search for available stores in 50 miles
    And I close bops overlay
    Then the 1st store is not selected in bops facet
    And bops facet search distance is 25 miles
#
  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: bops store should be taken from cookie if exists
    When I navigate to product PDP page
      | available_bops | true |
      | single_upc     | true |
    And I select available size of the product
    And I am going to check local stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details

    When I search for products
      | available_bops | true |
    Then the 1st store is not selected in bops facet
    And bops facet search distance is 25 miles
    And bops facet store location populated by "last_overlay_search" zipcode

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: error states
    When I search for products
      | available_bops | true |
    And I initiate store change for bops facet

    When I enter zipcode "<zipcode>" and do search for available stores
    Then "<error>" overlay error pops up

    Examples:
      | zipcode | error                                                                 |
      |         | Please enter a valid zip code or city and state.                      |
#      | wrong   | We did not recognize the location you've entered. Please enter a valid zip code or city & state |
      | 12345   | Unfortunately, in-store pickup is currently unavailable in your area. |