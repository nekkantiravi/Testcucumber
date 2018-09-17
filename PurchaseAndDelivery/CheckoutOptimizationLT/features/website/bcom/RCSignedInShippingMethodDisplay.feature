Feature: Checkout Optimization LT Checkout SignedIn Shipping Method display scenarios

  @b-88053 @optimization_lab @domain_purchase_and_delivery
  Scenario: verify user is able to see the shipping Methods section in active state
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    Then I should see the shipping Methods section in active state

  @b-88053 @optimization_lab @domain_purchase_and_delivery
  Scenario: verify user is not able to see the save and cancel button
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    Then I should not see the Change button in the shipping Method section
    Then I should not see the Save button in the shipping Method section