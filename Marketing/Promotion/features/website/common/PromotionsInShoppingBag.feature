Feature: Verify promotions

  @domain_marketing @use_regression
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

  @domain_marketing @use_regression
  Scenario: Verify See All Offers details when eligible promotion is present in bag
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I should see "See All Offers" link
    And I click on See All Offers link
    Then I verify the details in apply an offer window
    Then I should see the eligible promotion details in apply an offer window

  @domain_marketing @use_regression
  Scenario: Verify user can cancel or apply promotion using apply an offer overlay
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I should see "See All Offers" link
    And I click on See All Offers link
    Then I should see the eligible promotion details in apply an offer window
    Then I cancel out of apply an offer overlay window
    Then I click on See All Offers link
    Then I select eligible promotion and click on apply
    Then I should see promotion applied details in wallet section

  @domain_marketing @use_regression
  Scenario: Verify eligible promotion should be even displayed for guest user
    Given I visit the web site as a guest user
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I should see APPLY promotion option
    And I should see See All Offers link
    And I click on APPLY button to apply the promotion
    Then I should see promotion applied details in wallet section
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page

  @domain_marketing @use_regression
  Scenario: Verify eligible promotion can be applied using apply promotion field
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I should see APPLY promotion option
    Then I enter the "percent_off" promotion in promotion field and click on apply
    Then I should see promotion applied details in wallet section
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page

  @domain_marketing @use_regression
  Scenario: Verify quantity based promotion in shopping bag page
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    And I verify the APPLY promotion details in shopping bag page
    And I adjust qty to make the promotion unavailable
    Then I should not see APPLY promotion option
    And I should not see See All Offers link
    And I should see Go To Wallet link in wallet section

  @domain_marketing @use_regression
  Scenario: Verify eligible promo be displayed even when promo is not added to wallet
    Given I visit the web site as a registered user
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    Then I should see APPLY promotion option
    And I should see See All Offers link
    And I click on APPLY button to apply the promotion
    Then I should see promotion applied details in wallet section
    Then I should see promotion applied details in the item section
    And I checkout until I reach the shipping & payment page as a "responsive_signed in" user
    Then I should see the applied promotion in checkout page

  @domain_marketing @use_regression
  Scenario: Verify user can remove an applied promotion using remove button
    Given I visit the web site as a registered user with "percent_off" promotion in wallet
    And I add "percent_off" product to my bag
    And I select options to trigger the promotion and add to bag
    And I navigate to shopping bag page from add to bag overlay
    And I click on APPLY button to apply the promotion
    Then I should see promotion applied details in wallet section
    Then I click on Remove button to remove applied promotion
    Then I verify the applied promotion has been removed

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