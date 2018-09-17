Feature: BCOM Citi Monthly Regression

  @Acquisition02 @Citi02 @TC002 @Citi
  Scenario: Verify Acquisition flow for a guest user
    Given I visit the web site as a guest user
    And I click "apply now" button on "gateway" page
    Then I should be navigated to citi "apply now" page
    When I enter apply card details on page
    And I click "submit application" button on citi "apply now" page
    Then I should be navigated to citi "card choice" page
    And I select a card type and submit
    Then I should be navigated to citi "security question" page
    And I select the options and submit security questions
    And I verify citi "decision" page and click "return" button

  @Acquisition03 @Citi03 @TC003 @Citi
  Scenario: Verify Acquisition flow for a sign in user
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    And I create a profile for citi "03" and "apply now" test
    And I click "apply now" button on "gateway" page
    Then I should be navigated to citi "apply now" page
    When I enter apply card details on page
    And I click "submit application" button on citi "apply now" page
    Then I should be navigated to citi "card choice" page
    And I select a card type and submit
    Then I should be navigated to citi "security question" page
    And I select the options and submit security questions
    And I verify citi "decision" page and click "return" button

  @Acquisition04 @Citi04 @TC004 @Citi
  Scenario: Verify card gets added to wallet when card is approved for a guest user
    Given I visit the web site as a guest user
    And I click "apply now" button on "gateway" page
    Then I should be navigated to citi "apply now" page
    When I enter apply card details on page
    And I click "submit application" button on citi "apply now" page
    Then I should be navigated to citi "card choice" page
    And I select a card type and submit
    Then I should be navigated to citi "security question" page
    And I select the options and submit security questions
    And I verify citi "decision" page and click "return" button
    Then I should be navigated to login page with layover popup
    And I create a profile for citi "04" and "apply now"  test
    Then I verify "apply" card in My wallet for "guest" user

  @Acquisition05 @Citi05 @TC005 @Citi
  Scenario: Verify card gets added to wallet when card is approved for a sign in user
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "05" and "apply now" test
    And I click "apply now" button on "gateway" page
    Then I should be navigated to citi "apply now" page
    When I enter apply card details on page
    And I click "submit application" button on citi "apply now" page
    Then I should be navigated to citi "card choice" page
    And I select a card type and submit
    Then I should be navigated to citi "security question" page
    And I select the options and submit security questions
    And I verify citi "decision" page and click "return" button
    Then I should be navigated to credit gateway page
    And I navigate to My Wallet page
    Then I verify "apply" card in My wallet for "signin" user

  @Activation07 @Citi07 @TC007 @Citi
  Scenario: Verify activate card for a guest user
    Given I visit the web site as a guest user
    And I click "activate card" button on "gateway" page
    Then I should be navigated to citi "activate card" page
    When I enter activate card details for "guest amex" user and click verify
    Then I verify citi "activate now" page and click "activate now" button
    And I verify citi "congratulations" page and click "return" button
    Then I should be navigated to login page with layover popup
    And I create a profile for citi "07" and "activate card" test
    Then I verify "activated" card in My wallet for "guest amex" user

  @Activation08 @Citi08 @TC008 @Citi
  Scenario: Verify activate card for sign-in user
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "08" and "activate card" test
    And I click "activate card" button on "gateway" page
    Then I should be navigated to citi "activate card" page
    When I enter activate card details for "signin amex" user and click verify
    Then I verify citi "activate now" page and click "activate now" button
    And I verify citi "congratulations" page and click "return" button
    Then I verify "activated" card in My wallet for "signin amex" user

  @AddCard09 @Citi09 @TC009 @Citi
  Scenario: Verify user ability to add 16-digit card from my account page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "09" and "16-digit prop" test
    And I click "add card" button on "my account" page
    Then I should be navigated to citi "add card" page
    When I enter "16-digit prop" card details with "atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I verify "added" card in My wallet for "16-digit prop" user

  @AddCard10 @Citi10 @TC010 @Citi
  Scenario: Verify user ability to add converted prop card from my account page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "10" and "converted prop" test
    And I click "add card" button on "my account" page
    Then I should be navigated to citi "add card" page
    When I enter "converted prop" card details with "no atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I verify "added" card in My wallet for "converted prop" user

  @AddCard11 @Citi11 @TC011 @Citi
  Scenario: Verify user ability to add amex converted dual card from gateway page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "11" and "amex conv dual" test
    And I click "add card" button on "gateway" page
    Then I should be navigated to citi "add card" page
    When I enter "amex conv dual" card details with "atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I verify "added" card in My wallet for "amex conv dual" user

  @AddCard12 @Citi12 @TC012 @Citi
  Scenario: Verify user ability to add employee prop card from gateway page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "12" and "employee prop" test
    And I click "add card" button on "gateway" page
    Then I should be navigated to citi "add card" page
    When I enter "employee prop" card details with "atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I verify "added" card in My wallet for "employee prop" user

  @AddCard13 @Citi13 @TC013 @Citi
  Scenario: Verify user ability to add single line amex card from gateway page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "13" and "single line amex" test
    And I click "add card" button on "gateway" page
    Then I should be navigated to citi "add card" page
    When I enter "single line amex" card details with "atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I verify "added" card in My wallet for "single line amex" user

  @AddCard14 @Citi14 @TC014 @Citi
  Scenario: Verify user ability to add multiple cards from gateway page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "14" and "multi card01" test
    And I click "add card" button on "gateway" page
    Then I should be navigated to citi "add card" page
    When I enter "multi card01" card details with "no atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I click "add card" button on "gateway" page
    Then I should be navigated to citi "add card" page
    When I enter "multi card02" card details with "atw" option and click add card
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I verify "added" card in My wallet for "multi card02" user

  @AddCard15 @Citi15 @TC015 @Citi
  Scenario: Verify user ability to add amex new card from gateway page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "15" and "amex new" test
    And I click "add card" button on "gateway" page
    Then I should be navigated to citi "add card" page
    When I enter "amex new" card details with "atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    Then I verify "added" card in My wallet for "amex new" user

  @AddCard57 @Citi57 @TC057 @Citi
  Scenario: Verify user ability to an authorized user to new amex card from gateway page
    Given I visit the web site as a guest user
    When I click on signIn link
    Then I should be redirected to "sign_in" page
    And I login into a profile with "amex new" added
    And I click "add authorized user" link on "gateway" page
    Then I should be navigated to citi "add authorized user" page
    And I enter authorized user details for "amex new" account and submit
    Then I should be navigated to citi "auth user added" page
    And I sign out from citi add authorized user page
    Then I should be redirected to "home" page
    And I click on signIn link
    Then I should be redirected to "sign_in" page
    And I navigate to create profile page
    When I create a profile for citi "57" and "amex new" auth user test
    And I click "add card" button on "gateway" page
    Then I should be navigated to citi "add card" page
    When I enter authorized "amex new" card details with "atw" option and click add card
    Then I should be navigated to citi "security question" page
    And I enter security question and submit
    Then I should be navigated to citi "card setting" page
    And I select the options on citi settings page and submit
    And I verify citi "card added" page and click "return" button
    And I verify authrorized user indicator on gateway page
    Then I verify "added" card in My wallet for "amex new" user