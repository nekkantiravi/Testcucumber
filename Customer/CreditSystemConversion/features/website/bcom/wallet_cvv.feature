#####################################################################################
# Story             : B-66511 : T-N::UI::BCOM::Desktop::Disable Card Edit in Wallet for New Cobrand Card
#                   : B-66521 : QE:: T-N::MOCK::UI::BCOM::Desktop::Enable Card Edit in Wallet for New Cobrand Card if Account Open > 14 Business Days (TEST ONLY)
#                   : B-66515 : QE:: T-N::MOCK::UI::BCOM::Desktop::Enable Card Edit in Wallet for New Cobrand Card if Kill Switch = False (TEST ONLY)
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a customer, I would like to be prevented from editing my new cobrand card in my wallet so that I can shop
  online without entering the security code until I receive my physical card
# Pre-Requisite:: 1. Customer applied for cobrand card and received a cobrand card online, and added it to her wallet via the Fusion acquisition flow.
#                 2. Card/Account should be 14 days or less
#                 3. Feature be wrapped in kill switch( Kill switch = True ):  creditCvvSuppressionEnabled

  @wip @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17D
  Scenario Outline: Verify user with cobrand card with age less than 14 days is added to wallet, should not see Edit link
    Given I sign in with user having "<account_age>" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should not see edit link on wallet page
  Examples:
    | account_age          |
    | amex_lessthan_14days |
    | amex_equal_14days    |

  @wip @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17D
  Scenario: Verify user with cobrand card with age more than 14 days will see Edit link on wallet
    Given I sign in with user having "amex_morethan_14days" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see edit link on wallet page

  #Pre-requisite::  creditCvvSuppressionEnabled should be false
  @use_manual @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17E
  Scenario Outline: Edit link should not be supressed for accounts having age less than or equal to 14 days
    Given I sign in with user having "<account_age>" added in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see edit link on wallet page
    Examples:
      | account_age          |
      | amex_lessthan_14days |
      | amex_equal_14days    |

       # Pre-condition : If user is logged in with card in profile, regCreditCards[0].inWallet=true, and totalNumberOfOffers&gt;0,
  @use_manual @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17E
  Scenario Outline: Verify user with card in profile and added to wallet should see offers message in credit gateway page
    Given I sign in with user having "<account_age>" added in wallet
    When I navigate to the "credit services" page from footer
    Then I should see the added offers in credit gateway page
    Examples:
      | account_age             |
      | usercard_profile_wallet_offer |

  @use_manual @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17E
  Scenario Outline: Verify user with card in profile should see offers message in credit gateway page
    Given I sign in with user having "<account_age>" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see the add card to wallet in credit gateway page
    Examples:
      | account_age             |
      | usercard_profile        |

  @use_manual @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17E
  Scenario Outline: Verify user with card in profile should see offers message in credit gateway page
    Given I sign in with user having "<account_age>" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see the add offers message in credit gateway page
    Examples:
      | account_age             |
      | usercard_profile_wallet |