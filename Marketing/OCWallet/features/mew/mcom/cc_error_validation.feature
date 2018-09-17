# encoding: utf-8
Feature: As a mobile web customer, I can see error validation in the card page when I miss some thing

  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509 @use_new_regression
  Scenario: Verify the error message in card data
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page
    And I tap on 'Add A Credit Card' button in wallet landing page
    Then I expect CC error message after I enter details, changing one value:
      | message                                            | detail      | value                 |
      | Please select the type of card.                    | Card Type   |                       |
      | Please enter the expiration date for your card.    | month       |                       |
      | Please enter the expiration date for your card.    | year        |                       |
      | Please enter a valid credit card number.           | Card Number |                       |
      | Please enter a valid credit card number.           | Card Number |4523687                |
      | Please enter a valid credit card number.           | Card Number |4125w458               |
      | Hmm…the card number and card type do not match.    | Card Number |12341234123412345678   |


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509 @use_new_regression
  Scenario: Verify the error message in billing name
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page
    And I tap on 'Add A Credit Card' button in wallet landing page
    Then I expect CC error message after I enter details, changing one value:
      | message                                                        | detail       | value   |
      | Please enter the first name as it appears on your credit card. | First Name   |         |
      | Please remove any special characters.                          | First Name   | wa&aq   |
      | Please enter the last name as it appears on your credit card.  | Last Name    |         |
      | Please remove any special characters.                          | Last Name    | des#w   |


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509 @use_new_regression
  Scenario: Verify the error message in billing address is displayed
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page
    And I tap on 'Add A Credit Card' button in wallet landing page
    Then I expect CC error message after I enter details, changing one value:
    | message                                             | detail        | value       |
    | Please enter the Address Line 1 field for the billing address associated with your card. | Address 1   |  |
    | Please remove any special characters.               | Address 1     |   des+w     |
    | The address can't contain "c/o" – please try again. | Address 1     |   c/o       |
    | Please enter a city.                                | City          |             |
    | Please remove any special characters.               | City          |   now&here  |
    | Please enter a state.                               | State         |      |
    | Please enter a 5-digit zip code.                    | Zip Code      |             |
    | Please enter a valid 5-digit zip code.              | Zip Code      |   123       |
    | Please enter a valid 5-digit zip code.              | Zip Code      |   123456    |



  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509 @use_new_regression
  Scenario: Verify the error message in billing phone and email
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page
    And I tap on 'Add A Credit Card' button in wallet landing page
    Then I expect CC error message after I enter details, changing one value:
    | message                               | detail       | value |
    | Please enter a phone number.          | Phone        |       |
    | Please enter a 10-digit phone number. | Phone        |   r5  |
    | Please enter a 10-digit phone number. | Phone        |   i5  |
    | Please provide a contact email address for this order. | Email Address |      |
    | Please enter a contact email address in the following format: jane@email.com. | Email Address | 534whsdfny |


  @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message for CC not matching card type
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user
    And I tap on 'Add A Credit Card' button in wallet landing page
    When I enter details, changing one value: "Card Number" to "12341234123412345678"
    Then I tap on 'save' button in the add credit card modal, expecting the general error message "Hmm...the card number and card type do not match."


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message for expired CC
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page
    And I tap on 'Add A Credit Card' button in wallet landing page
    When I enter details, changing two values: "month" to "1" and "year" to "2016"
    Then I tap on 'save' button in the add credit card modal, expecting the general error message "Please enter a valid expiration date."


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message for 2 fields missing
    Given I visit the mobile web home page as a signed in user
    When I navigate to wallet page
    And I tap on 'Add A Credit Card' button in wallet landing page
    When I enter details, changing two values: "Card Number" to "" and "First Name" to ""
    Then I tap on 'save' button in the add credit card modal, expecting the general error message "Please correct the highlighted areas to proceed."


  @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the credit card field remains masked if I receive an error message after clicking on 'Save' button
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user
    And I tap on 'Add A Credit Card' button in wallet landing page
    When I enter details, changing two values: "month" to "1" and "year" to "2016"
    And I tap on 'save' button in the add credit card modal, expecting the general error message "Please enter a valid expiration date."
    Then I should see the value in the credit card number as masked


  @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the credit card remains masked and the user manually clear the field to enter a new value when the user had to reenter the card number field after receiving error message
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user
    And I tap on 'Add A Credit Card' button in wallet landing page
    When I enter details, changing two values: "First Name" to "" and "Last Name" to ""
    And I select the credit card field
    Then I should see the credit card numbers
    And I tap on 'save' button in the add credit card modal, expecting the general error message "Please correct the highlighted areas to proceed."
    Then I should see the value in the credit card number as masked
    And I select the credit card field
    Then I should see the value in the credit card number as masked

  # edit CC

  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message in card data for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I expect CC error message after I modify details, changing one value:
    | message                                                | detail      | value       |
    | Please select the type of card.                        | Card Type   | Card Type   |
    | Please enter the expiration date for your card.        | month       |             |
    | Please enter the expiration date for your card.        | year        |             |
    | Please enter a valid credit card number.               | Card Number |             |
    | Card Number : Please enter a valid credit card number. | Card Number |4523687      |
    | Please enter a valid credit card number.               | Card Number |4125w458     |


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message in billing name for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I expect CC error message after I modify details, changing one value:
    |  message                                                        | detail        | value       |
    |  Please enter the first name as it appears on your credit card. | First Name    |             |
    |  Please remove any special characters.                          | First Name    |   wa&aq     |
    |  Please enter the last name as it appears on your credit card.  | Last Name     |             |
    |  Please remove any special characters.                          | Last Name     |   des#w     |


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message in billing address is displayed for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I expect CC error message after I modify details, changing one value:
    |  message                                             | detail        | value       |
    |  Please enter the Address Line 1 field for the billing address associated with your card. | Address 1     |             |
    |  Please remove any special characters.               | Address 1     |   des+w     |
    |  The address can't contain "c/o" – please try again. | Address 1     |   c/o       |
    |  Please enter a city.                                | City          |             |
    |  Please remove any special characters.               | City          |   now&here  |
    |  Please enter a state.                               | State         |             |
    |  Please enter a 5-digit zip code.                    | Zip Code      |             |
    |  Please enter a valid 5-digit zip code.              | Zip Code      |   123       |
    |  Please enter a valid 5-digit zip code.              | Zip Code      |   123456    |


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message for expired CC for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    When I edit details, changing two values: "month" to "1" and "year" to "2016"
    Then I tap on 'apply' button in the add credit card modal, expecting the cc error message "Please enter a valid expiration date."


  @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message for 2 fields missing for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the Visa credit card cell
    When I edit details, changing two values: "Card Number" to "" and "First Name" to ""
    Then I tap on 'apply' button in the add credit card modal, expecting the general error message "Please verify and complete all required fields."


  @mew_regression @v1-b-02822 @wallet @domain_marketing @use_mew @15F @1509
  Scenario: Verify the error message in billing phone and email for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    Then I expect CC error message after I modify details, changing one value:
    | message                                                | detail        | value |
    | Please enter a phone number.                           | Phone         |       |
    | Please enter a 10-digit phone number.                  | Phone         |   r5  |
    | Please enter a 10-digit phone number.                  | Phone         |   i5  |
    | Please provide a contact email address for this order. | Email Address |       |
    | Please enter a contact email address in the following format: jane@email.com. | Email Address | 534whsdfny |


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the cc field remains masked if I receive an error message after clicking on 'Save' button for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    When I edit details, changing two values: "month" to "1" and "year" to "2016"
    Then I tap on 'apply' button in the add credit card modal, expecting the cc error message "Please enter a valid expiration date."
    Then I should see the value in the credit card number as masked


  @mew_regression @v1-b-02822 @domain_marketing @use_mew @15F @1509
  Scenario: Verify the credit card remains masked upon entering new cc after receiving error message for edit CC
    Given I visit the mobile web home page
    When I navigate to wallet page as a signed in user with 'Visa' credit card in the wallet
    And I tap on the "Visa" credit card cell
    When I edit details, changing two values: "month" to "1" and "year" to "2016"
    And I select the credit card field
    Then I should see the credit card number as masked
    Then I tap on 'apply' button in the add credit card modal, expecting the cc error message "Please enter a valid expiration date."
    Then I should see the value in the credit card number as masked
    And I select the credit card field
    Then I should see the value in the credit card number as masked