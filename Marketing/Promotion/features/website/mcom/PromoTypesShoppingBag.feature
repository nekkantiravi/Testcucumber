Feature: Verify promotions

  @wip @domain_marketing @use_regression
  Scenario: Verify applied promotion details in shopping bag and checkout page
    Given I visit the web site as a registered user with "BUY2" promotion in wallet
    And I add an "available and buy2_promotion_eligible" product to my bag
    And I navigate to shopping bag page from add to bag page
    And I adjust qty to make the promotion available
    And I verify the APPLY promotion details in shopping bag page
    And I click on APPLY button to apply the promotion
    Then I should see promotion applied details in wallet section
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page