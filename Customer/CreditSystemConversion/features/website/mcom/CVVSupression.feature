#####################################################################################
# Story             : B-65886 : QE::T-N::MCOM::UI::Desktop::Present CVV2 field with No Message if Kill Switch = False (TEST ONLY)
# Story             : B-65884 : T-N::MCOM::UI::Desktop::Present CVV2 field and Message if Pre-Auth Unavailable
# Story             : B-70391 : T-N::MCOM::UI::Desktop::Re-payment - Present CVV2 field and Message if Pre-Auth Unavailable
# Story             : B-59256 : QE::T-N::MCOM::Desktop::Hide CVV2 field when Customer Checks Out with Cobrand Card in Wallet – Days 1-14 (TEST ONLY)
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a customer, I would like to see a message instructing me to call Customer Service so that I can complete my order when I use my new Amex card in my wallet to check out and the security code field is presented before I have received my physical card.
#Feature be wrapped in kill switch:  creditCvvSuppressionEnabled


 # Pre-Requisite:: Kill Switch should be false
  @use_manual @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17C
  Scenario: Display CVV field for cobrand card with age less than 14 days in checkout page when KS is false
    Given I visit the mobile site as a registered user with new cobrand card added to wallet
    When I navigate to checkout page using quick view overlay or add to bag page
    Then I should see credit card section on the payment page
    And I should see cobrand card is added in payment option
    And I should see security code field is available

  @use_manual @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17C
  Scenario: Show CVV field and Message if Pre-auth Unavailable
    Given I visit the websit site as a registered user with new cobrand card added to wallet
    When I navigate to checkout page using quick view overlay or add to bag page
    Then I should see credit card section on the payment page
    And I should see CVV input field is displayed with Message

  @use_manual @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17C
  Scenario: User will get error message if nvalid cvv entered
    Given I visit the websit site as a registered user with new cobrand card added to wallet
    When I navigate to checkout page using quick view overlay or add to bag page
    Then I should see credit card section on the payment page
    And I should see CVV input field is displayed with Message
    When I enter invalid CVV
    And I select place order button
    Then I should see error message presented at top of the page

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario: Verify that customer should see declined card error message when wrong CVV entered
    Given I sign in with user having "amex_lessthan_14days" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see cobrand card as a payment option with security code field and message
    When I enter wrong CVV
    And I select place order
    Then I should see credit card declined error message

  #Pre-Requisite: EPS service should be down and Async set to TRUE
  @use_manual @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17D
  Scenario Outline: Verify the security code entry field and message instructing customer to call Customer Service.REPAYMENT PAGE for a user with cobrand card with age less than 14 days on Re payment page
    Given I sign in with user having "<account_age>" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "singed in" user
    When I enter declined card info on checkout
    And I enter contact info in billing address on checkout
    And I enter declined card security code on checkout
    And I place an order
    And I save order details from confirmation page
    And I visit order history page
    Then the last placed order must be present
    When I want to update payment for the last declined order
    Then I should see below fields present on repayment page
      | Security Code                                                                               |
      | Don't have your card yet?To checkout with this card,call Customer Service at 1-800-200-4229 |
    Examples:
      | account_age          |
      | amex_lessthan_14days |
      | amex_equal_14days    |