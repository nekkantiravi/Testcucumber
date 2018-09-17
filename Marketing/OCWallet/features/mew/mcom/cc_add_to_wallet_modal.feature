
Feature: MM2 :: Wallet :: Add Credit Card :: As a mobile web customer, I can manually add a credit card to my wallet.


  @mew_regression @v1-b-02512 @domain_marketing @use_mew @15f @1509 @use_smoke @use_mew_bat
  Scenario: Verify user should be able to select the card type by selecting the card type via picker wheel.
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    Then I should be able to see and select the following card types
      | Card Type        |
      | Macy's                  |
      | Macy's American Express |
      | American Express        |
      | Visa                    |
      | MasterCard              |
      | Discover                |
      | Macy's Employee Card    |

  @v1-b-02512 @domain_marketing @use_mew @15f @1509
  Scenario: Verify The credit card type drop down menu should contain the following options, and must be in the following order:
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    Then I should be able to see the following card types in the order
      | card_type               |
      | Macy's                  |
      | Macy's American Express |
      | American Express        |
      | Visa                    |
      | MasterCard              |
      | Discover                |
      | Macy's Employee Card    |


  @v1-b-02512 @domain_marketing @use_mew @15f @1509
  Scenario Outline: Verify user should not be able to enter a new expiration date for proprietary cards
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    And I select a "<credit_card_type>" in the card type field
    Then expiration date should be hidden
    Examples:
      | credit_card_type        |
      | Macy's                  |
      | Macy's Employee Card    |


  @v1-b-02512 @domain_marketing @use_mew @15f @1509
  Scenario Outline: Verify user should be able to enter a new expiration date for non-proprietary cards
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    And I select a "<credit_card_type>" in the card type field
    Then expiration date should be enabled
    And I should see all 12 months in the format "<index> - <month>" in the month drop down
    And I should see current year plus 9 more years in the year dropdown
    And I should be able to enter a new expiration date
    Examples:
      | credit_card_type        |
      | American Express        |
      | Visa                    |
      | MasterCard              |
      | Discover                |
      | Macy's American Express |


  @mew_regression @v1-b-02512 @domain_marketing @use_mew @15f @1509 @use_new_regression
  Scenario: Verify If the customer has no other saved cards then the "select as my default card" switch should be set to "on" and disabled.
    Given I visit the mobile web site as a registered user with no credit cards
    When I navigate the global navigation menu as follows:
      | WALLET |
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then the 'select as my default card' switch should be set to 'on' and 'disabled'


  @mew_regression @v1-b-02512 @domain_marketing @use_mew @15f @1509 @use_new_regression
  Scenario: Verify If the customer is adding a second (third, fourth, etc.) credit card then the "select as my default card" should be set to "off" and enabled.
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on 'Add A Credit Card' button in wallet landing page
    Then the 'select as my default card' switch should be set to 'off' and 'enabled'


  @mew_regression @v1-b-02512 @domain_marketing @use_mew @15f  @fix
  Scenario Outline: Verify My Credit cards screen with the newly added card on the list (including citi cards only)
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    When I add the "<credit_card_type>" card from add credit card modal
#    Then the toast message should read "Your Wallet entry was successfully saved"
    Then I should see the credit card "<credit_card_type>" saved in the wallet
    Examples:
      |credit_card_type         |
      | Macy's                  |
      | Macy's American Express |
      | Macy's Employee Card    |

  @mew_regression @v1-b-02512 @domain_marketing @use_mew @15f @use_smoke @use_mew_bat @use_new_regression
  Scenario Outline: Verify My Credit cards screen with the newly added card on the list (excluding citi cards)
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    When I add the "<credit_card_type>" card from add credit card modal
#    Then the toast message should read "Your Wallet entry was successfully saved"
    Then I should see the credit card "<credit_card_type>" saved in the wallet
    Examples:
      |credit_card_type         |
      | American Express        |
      | Visa                    |
      | MasterCard              |
      | Discover                |


  @mew_regression @v1-b-02512 @domain_marketing @use_mew @15f @use_new_regresssion
  Scenario: Verify The customer can select state by tapping on State drop down menu.
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    And the State drop down field should contain the following additional states in alpahbetical order:
      | State                           |
      | American Samoa                  |
      | Armed Forces Americas           |
      | Armed Forces Europe             |
      | Armed Forces Pacific            |
      | Federated States of Micronesia  |
      | Guam                            |
      | Marshall Islands                |
      | Northern Mariana                |
      | Palau                           |
      | Puerto Rico                     |
      | U.S. Virgin Islands             |


  @mew_regression @v1-b-02512 @domain_marketing @use_mew @15f @1509 @use_new_regression
  Scenario: verify that the credit card details will not be saved while tapping on back button
    Given I am on wallet landing page
    When I tap on 'Add A Credit Card' button in wallet landing page
    Then I should be navigated to the "add credit card" modal
    When I enter the 'Visa' card details in the add credit card modal
    And I tap on 'back' button in the add credit card modal
    Then I should not see the credit card saved in the wallet


  @v1-b-02512 @domain_marketing @use_mew @p3 @15f @1509 @manual
  Scenario: Verify The customer can tap into Zip Code field and enter their zip code.
    And the following are tested manually:
    """
    Given I am on the Wallet add credit card page
    When I tap into zip code field
    Then the keyboard should display only numerical digits
    """
  @v1-b-02512 @domain_marketing @use_mew @p3 @15f @1509 @manual
  Scenario: Verify The customer can tap into Phone Number field to enter their phone number.
    And the following are tested manually:
    """
    Given I am on the Wallet add credit card page
    When I tap on the phone number field
    Then the keyboard should display only numerical digits
    And only US phone numbers can be added
    When I enter the first 3 digits
    Then I should see those 3 digits formatted within parenthesis and followed by a space [(XXX)] - no input mask
    When I enter the next 3 digits
    Then I should see a dash automatically added after the 6th digit [(xxx) XXX-]
    When I enter the next 4 digits
    Then they should be added after the dash [(xxx) xxx-xxxx]
    When I delete one digit
    Then the last digit entered should be removed
    When I delete 3 more digits
    Then those digits should be deleted and the dash should be removed
    When I delete 3 more digits
    Then those digits should be delete and the dash should be removed
    When I delete 1 more digit
    Then that digit should be deleted and the parentheses and space should be removed
    """

  @v1-b-02512 @domain_marketing @use_mew @p3 @15f @1509 @manual
  Scenario: Verify The customer should be able to enter their email address in the Email Address field.
    And the following are tested manually:
    """
    Given I am on the Wallet add credit card page
    When I tap on the email address field
    Then the keyboard should include the '@' and '.com' keys

    """

