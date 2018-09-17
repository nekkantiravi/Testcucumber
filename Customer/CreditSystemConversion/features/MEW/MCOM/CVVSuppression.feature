#####################################################################################
# Story : B-66490 : QE::T-N::MCOM::MEW::Present/Require CVV2 field when Customer Checks Out with Cobrand Card in Wallet(TEST ONLY)
# Story : B-66481 : QE::T-N::MCOM::MEW::Hide CVV2 field when Customer Checks Out with Cobrand Card in Wallet – Days 1-14 (TEST ONLY)
# Story : B-66455 : QE::T-N::MCOM::UI::MEW::Present CVV2 field and Message if Pre-Auth Unavailable : checkout Page (TEST ONLY)
# Story : B-66478 : QE::T-N::MCOM::UI::MEW::Present CVV2 field with No Message if Kill Switch = False (TEST ONLY)
# Author : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a customer, I should see CVV field for new cobrand cards will be enabled of disabled based on account age.
#Feature be wrapped in kill switch: creditCvvSuppressionEnabled


# Pre-Requisite:: Customer applied for cobrand card and received a cobrand card online, and added it to her wallet via the Fusion acquisition flow.
  @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17C
  Scenario: Verify user should see CVV field for cobrand card with age more than 14 days in checkout page
    Given I sign to mobile website with user having "amex_morethan_14days" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see CVV field is enabled

  @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17D
  Scenario: Verify user should not see CVV field for cobrand card with age less than 14 days in checkout page
    Given I sign to mobile website with user having "amex_lessthan_14days" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see CVV field is disabled

  # B-66478
  @use_manual @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17D
  Scenario: Display CVV field for cobrand card with age less than 14 days in checkout page when KS is false
    Given I visit the mobile site as a registered user with new cobrand card added to wallet
    When I navigate to checkout page using quick view overlay or add to bag page
    Then I should see credit card section on the payment page
    And I should see cobrand card is added in payment option
    And I should see security code field is available

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario: Verify that customer should see security code field and a message instructing to call customer service at checkout page
    Given I sign to mobile website with user having "amex_lessthan_14days" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see cobrand card as a payment option with security code field to enter
    And I should see a message instructing to call customer service to complete the order
    ## Note:: Message should display as "Don't have your card yet? To checkout with this card, call Customer Service at 1-800-289-6229."

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario: Verify that customer should see security code field and a message when user has card age of 14days
    Given I sign to mobile website with user having "amex_equal_14days" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see cobrand card as a payment option with security code field to enter
    And I should see a message instructing to call customer service to complete the order
    ## Note:: Message should display as "Don't have your card yet? To checkout with this card, call Customer Service at 1-800-289-6229."

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario: Verify that customer should see declined card error message when wrong CVV entered
    Given I sign to mobile website with user having "amex_lessthan_14days" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see cobrand card as a payment option with security code field and message
    When I enter wrong CVV
    And I select place order
    Then I should see credit card declined error message