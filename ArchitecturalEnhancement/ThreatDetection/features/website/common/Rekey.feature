Feature: Verify rekey scenarios on checkout page

  @domain_ae @use_regression
  Scenario: Verify the rekey is shown to the user whose user id is listed in the fraud_user table
    Given my online uid is available in fraud_user table
    And I sign in with fraud_user
    And I add 1 shipping address and credit card added in profile
    And I sign out
    And I visit the site as signed in user with same uid
    And I add normal item to the bag and navigate to checkout page
    Then I should see the rekey on payment section
    When I enter the valid card number again
    And I should see the card number is verified

  @domain_ae @use_regression
  Scenario: Verify the rekey is not shown to the user who created the profile
    Given I visit the web site as a guest user
    And I create a new profile
    And I add 1 shipping address and credit card added in profile
    And I add normal item to the bag and navigate to checkout page
    Then I should not see the rekey on payment section


  @domain_ae @use_regression
  Scenario: Verify the rekey functionality for malicious user
    Given my online uid is available in fraud_user table
    And I sign in with fraud_user
    And I add 1 shipping address and credit card added in profile
    And I add normal item to the bag and navigate to checkout page
    Then I should not see the rekey on payment section
    When I sign out
    And I visit the site as signed in user with same uid
    And I add "MasterCard" credit card to wallet
    And I navigate directly to checkout page
    Then I should not see the rekey on payment section for "MasterCard"
    When I select the other card
    Then I should see the rekey on payment section

  @domain_ae @use_regression
    Scenario: Verify the rekey is not shown to the user who created profile and checkout with VGC item
    Given I visit the web site as a guest user
    And I create a new profile
    And I add 1 shipping address and credit card added in profile
    When I add VGC item to bag
    And I navigate directly to checkout page
    Then I should not see the rekey on payment section

  @domain_ae @use_regression
  Scenario: Verify the rekey is shown to the user who adds an VGC item and do a checkout for existing profile
    Given I visit the web site as a guest user
    And I create a new profile
    And I add 1 shipping address and credit card added in profile
    When I add VGC item to bag
    And I navigate directly to checkout page
    When I sign out
    And I sign in with same profile
    And I navigate directly to checkout page
    Then I should see the rekey on payment section

  @domain_ae @use_regression
  Scenario: Verify the rekey is not shown to the user who created the profile with new shipping address
    Given I visit the web site as a guest user
    And I create a new profile
    And I add 2 shipping address and credit card added in profile
    And I add normal item to the bag and navigate to checkout page
    Then I should not see the rekey on payment section


  @domain_ae @use_regression
  Scenario: Verify the rekey is shown to the user who added the shipping address with in less than 24hr
    Given I visit the web site as a guest user
    And I create a new profile
    And I add 2 shipping address and credit card added in profile
    And I add normal item to the bag and navigate to checkout page
    Then I should not see the rekey on payment section
    When I sign out
    And I sign in with same profile
    And I navigate directly to checkout page
    Then I should see the rekey on payment section for the all shipping address added on previous session

  @domain_ae @use_regression @wip @1
  Scenario: Verify the rekey is shown only to the shipping address which is added less than 24hr
    Given I visit the web site as a registered user
    And I add 2 shipping address and credit card added in profile
    And I add normal item to the bag and navigate to checkout page
    When I sign out
    And I sign in with same profile
    And I navigate directly to checkout page
    Then I should see the rekey on payment section for the all shipping address added on previous session
    When I update the timestamp day -1 for any shipping address
    And I should not see rekey for the cards on selecting the shipping address which was modified




