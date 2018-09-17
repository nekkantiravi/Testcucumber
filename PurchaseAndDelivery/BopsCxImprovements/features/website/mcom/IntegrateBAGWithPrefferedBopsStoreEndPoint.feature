# Author: BOPSCxImprovements Project QE Team
# Date Created: 3/10/2016
# Version One: B-52261

Feature: BAG Page Availability Display

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: verify previous session cookie value display functionality in zip code determination for guest/registered user
    Given I visit the web site as a <user> user
    When I add a "single_upc and available_bops and ship_available" product to my bag
    And I navigate to product PDP page
      | multiple_upc   | true |
      | available_bops | true |
      | ship_available | true |
    And I select available size of the product
    And I am going to check stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    And I navigate to shopping bag page
    Then I should see "selector module store name" is present on shopping bag page
    When I click store pickup availability link on bag page
    Then I should see zip code search field is auto populated with "last_overlay_search" value
    Examples:
      | user       |
      | guest      |
      | registered |

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: verify preferred store functionality in zip code determination
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I enter my preferred store zipcode as "94102"
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
      | ship_available | true |
    Then I should see "selector module store name" is present on shopping bag page

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: verify change preffered store functionality in zip code determination
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I enter my preferred store zipcode as "94102"
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
      | ship_available | true |
    Then I should see "selector module store name" is present on shopping bag page
    When I navigate to my account page
    And I enter my preferred store zipcode as "22102"
    When I goto Home page
    And I navigate to shopping bag page
    Then I should see "selector module store name" is present on shopping bag page

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: verify preferred store and previous session functionality in zip code determination
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I enter my preferred store zipcode as "94102"
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
      | ship_available | true |
    And I navigate to "multiple_upc and available_bops and ship_available" product PDP page
    And I select available size of the product
    And I am going to check stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    And I navigate to shopping bag page
    Then I should see "selector module store name" is present on shopping bag page
    When I click store pickup availability link on bag page
    Then I should see zip code search field is auto populated with "last_overlay_search" value