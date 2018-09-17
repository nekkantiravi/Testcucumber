# Author: Amol Ray
# Date Created:
# Date Signed Off: TBD

Feature: Responsive My Account add credit card to wallet Functionality check

  Background:
    Given I visit the web site as a signed-in user
    And I navigate to my account page

  @domain_customer,@project_salab,@priority_high @use_regression
    Scenario: Verify Add CC to wallet popup on my account page
      When I clicked on add payment method link
      Then I should see the add credit card popup on my account page

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: Verify Add CC to wallet popup with shipping address checkbox
    When I clicked on add payment method link
    Then I should see the add credit card popup on my account page
    And I should see and click shipping address checkbox
      | shipping_address_label | Use my shipping address |

  @domain_customer,@project_salab,@priority_high,@sa_lab @use_regression
  Scenario Outline: Add Macys CC to wallet and verify on my account page
    When I clicked on add payment method link
    And I add <cardType> credit card to wallet from my account popup

    Examples:
    | cardType |
    | Visa     |
    | Macy's   |

  @domain_customer,@project_salab,@priority_high @use_regression
  Scenario: Verify Add CC to wallet popup cancel button
    When I clicked on add payment method link
    Then I should see the add credit card popup on my account page
    And I clicked cancel button and back to My account page
    When I clicked on add payment method link
    Then I should see the add credit card popup on my account page
    And I clicked close button

  @domain_customer,@project_salab,@priority_high @use_regression
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
    And I clicked close button

