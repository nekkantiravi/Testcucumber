Feature: MM2 :: Wallet :: Edit Credit Cards in My Wallet


  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1508 @use_new_regression @mew_foundation @securem_pipeline
  Scenario Outline: When user taps on a Credit Card "cell" on the Wallet page and is brought to Edit a Credit Card modal page
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with "<card_type>" credit card in the wallet
    And I tap on the "<card_type>" credit card cell
    Then I should be navigated to the "edit credit card" modal
    And I should see the credit card fields populated with my data
    Examples:
      |card_type               |
      |Macy's American Express |
      |Visa                    |
      |Discover                |
      |MasterCard              |
      |Macy's                  |
      |Macy's Employee Card    |


  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1508
  Scenario: I can tap on the Back button and be taken back to Wallet Landing page without saving the information
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I enter "9999999999" into the phone number field
    And I tap on 'back' button in the edit credit card modal
    And I tap on the "Visa" credit card cell
    Then I should not see '999 999-9999' in the phone number field



  @v1-b-02328 @domain_marketing @use_mew @15f @1508 @use_new_regression
  Scenario: Verify When the customer makes the first edit to any of the editable fields, the "apply" and "apply changes" buttons will become enabled.
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the Visa credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I enter "9999999999" into the phone number field
    Then the 'apply' and 'apply changes' buttons should be enabled



  @v1-b-02328 @domain_marketing @use_mew @15f @1508 @use_new_regression
  Scenario: I can update the fields and save Credit / Debit Card information by clicking on apply button
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the Visa credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I update the card details with 'Discover' in the edit credit card modal
    And I update billing address details in the edit credit card modal
    And I tap the 'apply' button in the edit credit card modal
    Then I should see wallet landing page
    And I tap on the Discover credit card cell
    Then I should be navigated to the "edit credit card" modal
    Then I should see the credit card data displayed that I previously updated


  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1509
  Scenario: I can update the fields and save Credit / Debit Card information by clicking on apply changes button
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I successfully update the card details with "Discover" in the edit credit card modal
    Then I should see wallet landing page
    And I tap on the "Discover" credit card cell
    Then I should be navigated to the "edit credit card" modal
    And I should see the credit card data displayed that I previously updated


  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1509 @use_new_regression
  Scenario Outline: Verify If the customer switches credit card type from non-prop card to prop card the expiration date fields will be hidden.
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I edited the card details with "<prop_card>" in the edit credit card modal
    Then expiration date should be hidden
    Examples:
      |prop_card              |
      |Macy's                 |
      |Macy's Employee Card   |


  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1509
  Scenario: Verify The "set as my default" switch should be set to "on" and disabled when editing the default credit card.
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then the 'select as my default card' switch should be set to 'on' and 'disabled'


  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1509 @use_new_regression
  Scenario: Verify If the customer is editing credit card that is not the default credit card, then the "save as default" will be switched to off and enabled.
    Given I visit the mobile web home page as a signed in user
#    When I sign in through services
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    When I add "Discover" card to the wallet
    And I tap on the "Discover" credit card cell
    Then the "select as my default card" switch should be set to "off" and "enabled"


  @v1-b-03448 @coremetrics @1508
  Scenario: Verify If the customer is editing credit card coremetrics
    Given I visit the mobile web home page
    When I sign in through services
    And I add "Visa" credit card to the wallet
    And I add "Discover" credit card to the wallet
    And I navigate to my wallet landing page
    And I tap on the Visa credit card cell
    And I set "select as my default card" to "on"
    And I tap the 'apply' button in the edit credit card modal


  @v1-b-02328 @domain_marketing @use_mew @15f @1508
  Scenario: removing a card from the wallet and tapping the 'yes' button in the confirmation
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the Visa credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I tap the 'delete card' button in the edit credit card modal
    Then the confirmation message is as follows
    """
    Are you sure you want to delete this credit card?
    """
    When I click on the 'yes' button on the delete credit card confirmation message
    Then I should see wallet landing page
    And I should see "no" credit cards on the wallet landing page

  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1508
  Scenario: removing a card from the wallet and tapping the 'no' button in the confirmation
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I tap the 'delete card' button in the edit credit card modal
    Then I see the confirmation message as 'Are you sure you want to delete this credit card?'
    When I click on the 'no' button on the delete credit card confirmation message
    Then I should be navigated to the "edit credit card" modal


  @mew_regression @v1-b-02328 @domain_marketing @use_mew @15f @1508
  Scenario: removing a default card from a list of card and checking the recenlty added card to be default
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I add "Discover" card to the wallet
    And I tap on the "Visa" credit card cell
    Then I should be navigated to the "edit credit card" modal
    When I tap the 'delete card' button in the edit credit card modal
    When I click on the 'yes' button on the delete credit card confirmation message
    Then I should see wallet landing page
    And I should see 'Discover' card labelled as my default card

# Manual cases
  @v1-b-02328 @p3 @domain_marketing @use_mew @15f @1509 @manual
  Scenario: Verify On phone number field, the keyboard on the device updates to digits keys.
    And the following are tested manually:
    """
    Given I am on wallet edit credit card page after adding a new "non-prop" credit card
    And I tap into the Phone number field
    Then the keyboard should update to show digits only
    """

  @v1-b-02328 @p3 @domain_marketing @use_mew @15f @1509 @manual
  Scenario: Verify On Email field the keyboard on the device updates to include @ and .com keys.
    And the following are tested manually:
    """
    Given I am on the Wallet edit credit card page
    When I tap on the email address field
    Then the keyboard should include the '@' and '.com' keys
    """