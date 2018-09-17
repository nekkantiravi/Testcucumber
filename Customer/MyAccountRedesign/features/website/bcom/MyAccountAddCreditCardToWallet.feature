# Author: Ulyana Opolska

Feature: Responsive My Account add credit card to wallet Functionality check

  Background:
    Given I visit the web site as a guest user
    And I navigate to create profile page
    And I create a new profile
    And I navigate to my account page

  @b-92436 @optimization_lab @priority_high @use_regression
  Scenario: Verify Add CC to wallet popup on my account page
    When I clicked on add payment method link
    Then I should see the add credit card popup on my account page

  @b-92436 @optimization_lab @priority_high @use_regression
  Scenario: Verify Add CC to wallet popup with shipping address checkbox
    When I add checkout eligible address on my address book page
    And I navigate to my account page
    And I clicked on add payment method link
    Then I should see the add credit card popup on my account page
    And I should see and click shipping address checkbox
      | shipping_address_label | Use my shipping address |

  @b-92436 @optimization_lab @priority_high @use_regression
  Scenario Outline: Add CC to wallet and verify on my account page
    When I clicked on add payment method link
    And I add <cardType> credit card to wallet from my account popup

    Examples:
      | cardType                         |
      | Visa                             |
      | MasterCard                       |
      | Discover                         |
      | American Express                 |
      | Bloomingdale's                   |
      | Bloomingdale's Employee Card     |
      | Bloomingdale's American Express  |
    
  @b-92436 @optimization_lab @priority_high @use_regression
  Scenario: Verify Add CC to wallet popup close button
    When I clicked on add payment method link
    Then I should see the add credit card popup on my account page
    And I clicked close button

  @b-92436 @optimization_lab @priority_high @use_regression
  Scenario: Verify inline errors on Add CC to wallet popup
    When I clicked on add payment method link
    Then I should see the add credit card popup on my account page
    And I clicked on save button and validated all inline error messages
      | error_credit_card_type    | Please select a card type |
      | error_card_number         | Please enter a card number  |
      | error_expiry_month        | Please select a month  |
      | error_expiry_year         | Please select a year |
      | error_first_name          | Please enter a first name  |
      | error_last_name           | Please enter a last name  |
      | error_address_line_1      | Please enter a street address  |
      | error_address_city        | Please enter a city  |
      | error_address_state       | Please enter a state  |
      | error_address_zip_code    | Please enter a ZIP code  |
      | error_card_phone_number   | Please enter a phone number |
      | error_card_email_address  | Please enter an email address |

