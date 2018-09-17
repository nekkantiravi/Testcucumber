# Author: BOPSCxImprovements Project QE Team
# Date Created: 3/10/2016
# Version One: B-63554, B-66353, B-63554

Feature: Browse facet

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
  Scenario: store selected in bops overlay transfers to bops facet
    When I search for products
      | available_bops | true |
    And I set bops facet search distance as 10 miles
    And I initiate store change for bops facet
    And I enter zipcode and do search for available stores in 50 miles
    And I close bops overlay
    Then the 1st store is not selected in bops facet
    And bops facet search distance is 10 miles

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: bops store should be taken from cookie if exists
    When I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
    And I select available size of the product
    And I am going to check stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details

    When I search for products
      | available_bops | true |
    Then the 1st store is not selected in bops facet
    And bops facet search distance is 10 miles
    And bops facet store location populated by "last_overlay_search" zipcode