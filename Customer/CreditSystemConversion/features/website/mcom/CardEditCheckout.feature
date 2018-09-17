#####################################################################################
# Story             : B-65888 : T-N::UI::MCOM::Desktop::Disable Card Edit in Checkout for New Cobrand Card
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a customer, I would like to be prevented from editing my new cobrand card in Checkout so that I can shop online without entering the security code until I receive my physical card.

# Pre-Requisite:: 1. Customer applied for cobrand card and received a cobrand card online, and added it to her wallet via the Fusion acquisition flow.
#                 2. Card/Account should be 14 days or less
#                 3. Feature be wrapped in kill switch( Kill switch = True ):  creditCvvSuppressionEnabled

  @wip @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_16T
  Scenario Outline: Verify user with cobrand card with age less than 14 days is added to wallet and navigated to checkout page, Edit link for the cobrand card is removed
    Given I sign in with user having "<account_age>" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "registered" user
    Then edit button should be disabled onclick change in credit card section
  Examples:
  | account_age          |
  | amex_lessthan_14days |

#####################################################################################
# Story             : B-65891 : T-N::UI::MCOM::Desktop::Enable Card Edit in Checkout for New Cobrand Card if Account > 14 days old (TEST ONLY)
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

  @wip @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_16T
  Scenario Outline: Verify user with cobrand card with age more than 14 days is added to wallet and navigated to checkout page, Edit link for the cobrand card is enabled
    Given I sign in with user having "<account_age>" added in wallet
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "registered" user
    Then edit button should be enabled onclick change in credit card section
  Examples:
  | account_age          |
  | amex_morethan_14days |










