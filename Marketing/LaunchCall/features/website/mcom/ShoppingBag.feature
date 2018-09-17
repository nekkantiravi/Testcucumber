Feature: Verify promotions

  @domain_marketing @use_regression @launch_call
  Scenario: Verify applied promotion details in shopping bag and checkout page
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    And I verify the APPLY promotion details in shopping bag page
    And I click on APPLY button to apply the promotion
    Then I should see promotion applied details in wallet section
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page