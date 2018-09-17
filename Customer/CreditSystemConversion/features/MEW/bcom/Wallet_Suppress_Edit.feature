#####################################################################################
# Story             : B-66524: QE:: T-N::MOCK::UI::BCOM::MEW::Enable Card Edit in Wallet for New Cobrand Card if Account Open > 14 Days (TEST ONLY)
#                   : B-66510 : T-N::UI::BCOM::MEW::Disable Card Edit in Wallet for New Cobrand Card
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a customer, I would like to be prevented from editing my new cobrand card in my wallet so that I can shop
  online without entering the security code until I receive my physical card
# Feature be wrapped in kill switch( Kill switch = True ):  creditCvvSuppressionEnabled

  @wip @mew @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17E
  Scenario Outline: Verify user with cobrand card with age less than 14 days will not see Edit link on wallet
    Given I sign to mobile website with user having "<account_age>" added in wallet
    When I navigate the global navigation menu as follows:
      | MY bWALLET |
    Then I should not see edit link on mobile wallet page
  Examples:
    | account_age          |
    | amex_lessthan_14days |
    | amex_equal_14days    |

  @wip @mew @domain_customer @project_Checkout_First_Fourteen_Suppress_Security_Code_Field @release_17E
  Scenario: Verify user with cobrand card with age more than 14 days will see Edit link on wallet
    Given I sign to mobile website with user having "amex_morethan_14days" added in wallet
    When I navigate the global navigation menu as follows:
      | MY bWALLET |
    Then I should see edit link on mobile wallet page