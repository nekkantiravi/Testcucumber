# Author: BOPSCxImprovements Project QE Team
# Date Created: 9/29/2016
# Version One: B-54571
Feature: PDP: Availability Display

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify PDP Availability Display for single upc product which is ship and bops available
    Given I visit the web site as a <user_type> user
    When I navigate to product PDP page
      | single_upc     | true |
      | available_bops | true |
      | ship_available | true |
    And I select available size of the product
    Then I should see "ship header" is present on Product Display page
    And I should see "ship availability message" is present on Product Display page
    And I should see "bops availability header" is present on Product Display page
    And I should see "check bops availability" is present on Product Display page
    And I shouldn't see "bops availability message with store" is present on Product Display page
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify PDP Availability Display for single/multiple upc product,selected upc is available for shipping and available for BOPS, available in selected store
    Given I visit the web site as a <user_type> user
    When I navigate to product PDP page
      | <upc>          | true |
      | available_bops | true |
      | ship_available | true |
    And I select available size of the product
    And I am going to check stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    Then I should see "ship header" is present on Product Display page
    And I should see "bops availability header" is present on Product Display page
    And I verify "bops availability message with store" contains text "Available at" on Product Display page
    And I should see "check bops availability" is present on Product Display page
    Examples:
      | user_type  | upc          |
      | guest      | single_upc   |
      | registered | multiple_upc |

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: Verify pdp availability doesn't display when upc isn't selected and shows up after upc selection
    Given I visit the web site as a guest user
    When I navigate to product PDP page
      | multiple_upc   | true |
      | available_bops | true |
    Then I shouldn't see "ship header" is present on Product Display page
    And I shouldn't see "ship availability message" is present on Product Display page
    And I shouldn't see "bops availability header" is present on Product Display page
    When I select available size of the product
    Then I should see "ship header" is present on Product Display page
    And I should see "bops availability header" is present on Product Display page
    And I should see "bops available icon" is present on Product Display page
    And I should see "check bops availability" is present on Product Display page