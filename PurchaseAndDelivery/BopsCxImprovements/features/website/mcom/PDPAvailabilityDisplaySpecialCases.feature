# Author: BOPSCxImprovements Project QE Team
# Date Created: 9/27/2016
# Version One: B-55618
Feature: PDP: Availability Display Special Cases

  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario Outline: Verify pdp Availability display for special/drop product
    Given I visit the web site as a <user_type> user
    When I navigate to product PDP page
      | member_product | false          |
      | order_method   | <order_method> |
    Then I should see "ship header" is present on Product Display page
    And I verify "ship availability message" contains text "<ship_message>" on Product Display page
    And I <should_or_not> see "bops availability header" is present on Product Display page

    Examples:
      | user_type  | order_method | ship_message                                                              | should_or_not |
      | guest      | SPEC         | special order: usually ships within                                       | should        |
      | registered | DROP         | Direct from vendor                                                        | shouldn't     |
      | guest      | CALLF        | Please call 1-800-BUY-MACY to confirm availability and schedule delivery. | shouldn't     |
      | registered | CALLM        | Please call to schedule a delivery.                                       | shouldn't     |


  @project_bops_cx_improvements @domain_purchase_and_delivery
  Scenario: Verify bops availability display correctly for unavailable product when bops store is chosen before
  for product that is available for bops
    Given I visit the web site as a registered user
    When I navigate to product PDP page
      | single_upc     | false |
      | available_bops | false |
    And I select available size of the product
    And I am going to check stores on Radical pdp page
    And I enter zipcode and do search for available stores
    And I select the 1st available bops store and save details
    And I navigate to product PDP page
      | master_product | false |
      | available_bops | false |
    Then I should see "ship header" is present on Product Display page
    And I should see "bops availability header" is present on Product Display page
    Then I should see "bops not available icon" is present on Product Display page
