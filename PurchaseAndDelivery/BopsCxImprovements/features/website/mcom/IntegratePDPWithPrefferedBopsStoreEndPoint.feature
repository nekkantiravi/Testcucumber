# Author: BOPSCxImprovements Project QE Team
# Date Created: 3/10/2016
# Version One: B-56167
Feature: PDP: Availability Display

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: verify previous session cookie value display functionality in zip code determination for guest/registered user
    Given I visit the web site as a <user> user
    When I navigate to product PDP page
      | multiple_upc   | true |
      | available_bops | true |
    And I select available size of the product
    And I am going to check stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
    And I am going to check stores on Radical pdp page
    Then I should see zip code search field is auto populated with "last_overlay_search" value
    Examples:
      | user       |
      | guest      |
      | registered |

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: verify preffered store functionality in zip code determination
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I enter my preferred store zipcode as "94102"
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
      | ship_available | true |
    And I am going to check stores on Radical Pdp page
    Then I should see zip code search field is auto populated with "94102" value

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: verify change preferred store functionality in zip code determination
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I enter my preferred store zipcode as "94102"
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
      | ship_available | true |
    And I am going to check stores on Radical Pdp page
    Then I should see zip code search field is auto populated with "94102" value
    When I goto Home page
    And I navigate to my account page
    And I enter my preferred store zipcode as "22102"
    And I navigate to "single_upc and available_bops and ship_available" product PDP page
    And I am going to check stores on Radical Pdp page
    Then I should see zip code search field is auto populated with "22102" value

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: verify preferred store and previous session functionality in zip code determination
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I enter my preferred store zipcode as "94102"
    And I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
      | ship_available | true |
    And I am going to check stores on Radical Pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    And I navigate to "multiple_upc and available_bops and ship_available" product PDP page
    And I select available size of the product
    And I am going to check stores on Radical Pdp page
    Then I should see zip code search field is auto populated with "last_overlay_search" value