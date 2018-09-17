#####################################################################################
# Story             : B-66476 : QE::T-N::BCOM::UI::Desktop::Present CVV2 field with No Message if Kill Switch = False (TEST ONLY)
# Story             : B-66488 :: QE::T-N::BCOM::Desktop::Present/Require CVV2 field when Customer Checks Out with Cobrand Card in Wallet (TEST ONLY)
# Story             : B-66482 :: QE::T-N::BCOM::Desktop::Hide CVV2 field when Customer Checks Out with Cobrand Card in Wallet – Days 1-14 (TEST ONLY)
# Story             : B-66456 : T-N::BCOM::UI::Desktop::Checkout::Present CVV2 field and Message if Pre-Auth Unavailable
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a customer, I would like to see a message instructing me to call Customer Service so that I can complete my order when I use my new Amex card in my wallet to check out and the security code field is presented before I have received my physical card.
#Feature be wrapped in kill switch:  creditCvvSuppressionEnabled

  ##Pre-condition:
  #  kill switch: creditCvvSupressionEnabled = True
  # Customer applied for and received a cobrand card online, and added it to her wallet via the Fusion acquisition flow.
  # Account age is [14] days or less

  @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario Outline: Verify that user should not see security code field at checkout.
    Given I sign in with user having "<account_age>" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see cobrand card as a payment option with suppressed security field
    Then I place an order
  Examples:
  | account_age          |
  | amex_lessthan_14days |

 # Pre-Requisite:: Kill Switch should be false
  @use_manual @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17C
  Scenario: Display CVV field for cobrand card with age less than 14 days in checkout page when KS is false
    Given I visit the mobile site as a registered user with new cobrand card added to wallet
    When I navigate to checkout page using quick view overlay or add to bag page
    Then I should see credit card section on the payment page
    And I should see cobrand card is added in payment option
    And I should see security code field is available



  ##Pre-condition:
  #  kill switch: creditCvvSupressionEnabled = True
  # Customer applied for and received a cobrand card online, and added it to her wallet via the Fusion acquisition flow.
  # Account age is older than [14] days.

  @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario Outline: Verify that customer should enter the security code in order to save order and proceed through checkout.
    Given I sign in with user having "<account_age>" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see cobrand card as a payment option with security code field to enter
    Then I place an order
  Examples:
  | account_age          |
  | amex_morethan_14days |


  ##Pre-condition:
  #  kill switch: creditCvvSupressionEnabled = False
  # Customer applied for and received a cobrand card online, and added it to her wallet via the Fusion acquisition flow.
  # Account age is older than [14] days.

  @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario Outline: Verify that customer should enter the security code in order to save order and proceed through checkout.
    Given I sign in with user having "<account_age>" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see cobrand card as a payment option with security code field to enter
    Then I place an order
    Examples:
      | account_age          |
      | amex_morethan_14days |

  # Pre-Requisite:: Pre auth service should be down
  @use_manual @domain_customer @project_Checkout_More_than_Fourteen_Require_Security_Code_Field @release_17D
  Scenario: Show CVV field and Message if Pre-auth Unavailable
    Given I visit the web site as a registered user with cobrand card added to the wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed" user
    Then I should see CVV field is enabled
    And I should see a message instructing to call customer service to complete the order
    ## Note:: Message should display as "Don't have your card yet? To checkout with this card, call Customer Service at 1-800-289-6229."