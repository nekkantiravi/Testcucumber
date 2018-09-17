Feature: Verify promotions

  @domain_marketing @use_regression @Marketing_CBT
  Scenario: Verify applied promotion details in shopping bag and checkout page
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion in pdp page
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I verify the eligible offers section in shopping bag page
    And I select the eligible promotion from eligible offer section
    Then I should see promotion applied details in order summary setion
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page

  @domain_marketing @use_regression
  Scenario: Verify user can remove an applied promotion using remove button
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion in pdp page
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I select the eligible promotion from eligible offer section
    Then I click on Remove button to remove applied promotion
    Then I verify the applied promotion has been removed

  @domain_marketing @use_regression
  Scenario: Verify user can remove promotion by DO NOT USE ANY OFFER option
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion in pdp page
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I select the eligible promotion from eligible offer section
    Then I should see promotion applied details in order summary setion
    And I select do not use any offer option
    Then I verify the applied promotion has been removed


  @domain_marketing @use_regression
  Scenario: Verify eligible promo option not visible in shopping bag when promo not added to wallet
    Given I visit the web site as a registered user with no stored offers in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I should not see the eligible offer option in eligible offer section
    And I should see no offer in wallet text in eligible offers section
    Then I enter the "percent_off" promotion in promotion field and click on apply
    Then I should see promotion applied details in order summary setion

  @domain_marketing @use_regression @Marketing_CBT
  Scenario: Verify eligible promotion can be applied using apply promotion field
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion in pdp page
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    Then I enter the "percent_off" promotion in promotion field and click on apply
    Then I should see promotion applied details in order summary setion
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page

  @domain_marketing @use_regression
  Scenario: Verify quantity based promotion in shopping bag page
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion in pdp page
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    Then I enter the "percent_off" promotion in promotion field and click on apply
    Then I should see promotion applied details in order summary setion
    And I adjust qty to make the promotion unavailable
    Then I verify the applied promotion has been removed
    And I should not see the eligible offer option in eligible offer section

  @domain_marketing @use_regression
  Scenario: Verify eligible promotion option should not be visible for guest in shopping bag
    Given I visit the web site as a guest user
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion in pdp page
    And I add product to my bag from standard PDP Page
    When I navigate to shopping bag page from add to bag page
    And I should not see the eligible offer option in eligible offer section

  @domain_marketing @use_regression
  Scenario: Verify percent off non global promotion gets applied to multiple upcs in bag
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add 2 different UPCs "percent_off" eligible product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    And I click on APPLY button to apply the promotion
    Then I should see promotion getting applied to both UPCs
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page