#Author: Ulyana Opolska

Feature: Create profile while guest checkout

  Background:
    Given I visit the web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user

  @b-83872 @optimization_lab
  Scenario: Verify guest user can create account on checkout page and confirmation message is displayed with Sign In link
    When I enter payment details on payment page
    And I enter unique email in contact info section
    And I enter all required data on create profile form
    Then I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    And I see account created confirmation message on Order Confirmation page with sign in link
      |account_created_confirm_msg| Your Bloomingdales.com account has been created. Be on the lookout for a welcome email.|
      |sign_in_link               | SIGN IN NOW                                                                            |
    And Account creation fields are not displayed on order confirmation page
    When I click on sign in link on order confirmation page
    Then I am navigated to sign in page

  @b-83872 @optimization_lab
  Scenario: Verify that account was not created on Checkout page because the email exists
    Given I enter payment details on payment page
    And I enter unique email in contact info section
    And I enter all required data on create profile form
    And I update email with already exist email bcom
    Then I select continue button on guest payment page
    When I place an order
    Then I see email already exists message and sign in link on OCP
      |email_exist_err_msg|Your email address is already associated with a bloomingdales.com account. Click here to sign in|
    When I click on sign in now link on order confirmation page
    Then I am navigated to sign in page

  @b-83872 @optimization_lab
  Scenario: Verify that account was not created on Checkout page because the password is invalid
    Given I enter payment details on payment page
    And I enter unique email in contact info section
    And I enter invalid password in the password field
      |invalid_password| abc123|
    And I enter valid date of birth on Checkout page
    Then I select continue button on guest payment page
    When I place an order
    Then I see invalid password message and Try again link on OCP
      |invalid_pwd_msg|Your bloomingdales.com account could not be created because the password you entered wasn’t valid. Please enter a new password that is between 7–16 characters and does not contain spaces or .,-\|/=_ . Please try again.|
      |please_try_again_link| Please try again.                                                                                                                                                                      |

  @b-83872 @optimization_lab
  Scenario: Verify that account was not created on Checkout page because date of birth is invalid
    Given I enter payment details on payment page
    And I enter unique email in contact info section
    And I enter valid password in the password field
    And I enter invalid date of birth on Checkout page
    Then I select continue button on guest payment page
    When I place an order
    Then I see invalid date of birth message and Try again link on OCP
      |invalid_dob_msg|Your bloomingdales.com account could not be created. Please enter your date of birth to continue. Please try again.|
      |please_try_again_link| Please try again.                                                                                           |

  @b-83872 @optimization_lab
  Scenario: Verify guest user can create account on order confirmation page and confirmation overlay is displayed
    Given I fill in payment information on guest payment page
    And I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    And I enter all required data on order confirmation create profile form
    Then I click on create profile button on Order Confirmation page
    And Account created confirmation overlay is displayed with Sign In, Continue Shopping and close buttons
      |create_profile_msg|Your bloomingdales.com account has been created.|
    When I click on sign in button on the overlay on order confirmation page
    Then I am navigated to sign in page

  @b-83872 @optimization_lab
  Scenario: Verify Continue Shopping button on 'account has been created' overlay on order confirmation page
    Given I fill in payment information on guest payment page
    And I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    And I enter all required data on order confirmation create profile form
    When I click on create profile button on Order Confirmation page
    And I click continue shopping button from account created overlay on order confirmation page
    Then I am navigated to home page

  @b-83872 @optimization_lab
  Scenario: Verify create profile 'email already exists' inline error on order confirmation page
    Given I fill in payment information on guest payment page
    And I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    And I update email with already exist email bcom
    When I click on create profile button on Order Confirmation page
    And I verify inline error message for existing email while creating profile on order confirmation page
      | email_exist_err_msg|An account with this email already exists.|

  @b-83872 @optimization_lab
  Scenario: Verify create profile 'invalid password' and 'invalid DOB' inline error on order confirmation page
    Given I fill in payment information on guest payment page
    And I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    When I click on create profile button on Order Confirmation page
    And I verify inline error message for required password and dob while creating profile on order confirmation page
      | error_dob   |The Birth Date you entered is invalid.|

