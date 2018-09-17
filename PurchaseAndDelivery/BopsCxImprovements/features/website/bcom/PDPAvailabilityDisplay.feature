# Author: BOPSCxImprovements Project QE Team
# Date Created: 30/09/2016
# Version One: B-54574, B-68322
Feature: PDP: Availability Display

  #Scenarios related to single UPC
  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify PDP Availability display for single upc product, upc is available for shipping and BOPS
    Given I visit the web site as a <user_type> user
    When I navigate to product PDP page
      | available_bops | true |
      | single_upc     | true |
    Then I shouldn't see "Select a color and size above to view availability" is present on Product Display page
    And I should see "PICK UP IN STORE HEADER" is present on product display page
    And I should see "Check local stores" is present on product display page
    Examples:
      | user_type  |
      | guest      |
      | registered |

  #Scenarios related to multiple UPC
  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify pdp Availability display for multiple upc product,upc not selected
    Given I visit the web site as a <user_type> user
    When I navigate to product PDP page
      | available_bops | true |
      | multiple_upc   | true |
    # adaptation to story B-68322 (improved PDP disabled by default)
    Then I shouldn't see "Select a color and size above to view availability" is present on Product Display page
    When I select available size of the product
    Then I shouldn't see "Select a color and size above to view availability" is present on Product Display page
    Examples:
      | user_type  |
      | guest      |
      | registered |

  #Scenarios related to multiple UPC
  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify PDP Availability display for mutiple upc product, upc is available for shipping and BOPS
    Given I visit the web site as a <user_type> user
    When I navigate to product PDP page
      | available_bops | true |
      | multiple_upc   | true |
    And  I select available size of the product
    And I am going to check local stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    Then I should see "PICK UP IN STORE HEADER" is present on Product Display page
    And I verify "bops availability message with store" contains text "Available at" on Product Display page
    And I should see "check another store" is present on product display page
    Examples:
      | user_type  |
      | guest      |
      | registered |