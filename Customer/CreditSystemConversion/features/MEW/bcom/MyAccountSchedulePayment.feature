#####################################################################################################
# Story: B-92921 ::OTSP2: BUS:: BCOM:: My Account:: Display NON-Autopay + Autopay Scheduled Payment data
#      : B-92919 :: OTSP2:: BUS:: BCOM:: My Account:: "View details" link destination for Scheduled Payment use cases
# Author: QE Team
####################################################################################################

Feature: As a user with a card in profile, with a non-autopay or an autopay Scheduled Payment, I want to see that information on the BCOM My Account dashboard

# Pre-Requisite:1.Customer is signed in and has one or multiple cards added to their account
#       		2.Customer has navigated to the my account page
#       		3.Feature will be Wrapped under Kill Switch: creditSchedulePaymentEnabled=true

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User should not have any schedule payments
    Given I sign to mobile website with user having "single_card_n" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should not see message is displaying on the UI for my account page

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with single Non auto pay scheduled payment
    Given I sign to mobile website with user having "single_card_ss" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "ss_payment_message" on schedule payment section on my account page
    And I should see View details link as part of the scheduled payment message

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with multiple Non auto pay scheduled payments
    Given I sign to mobile website with user having "single_card_ms" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "ms_payment_message" on schedule payment section on my account page
    And I should see View details link as part of the scheduled payment message

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with Non-Autopay Single Processing Payment
    Given I sign to mobile website with user having "single_card_ps" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "ps_payment_message" on schedule payment section on my account page
    And I should not see view details link on the UI for my account page

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with Autopay of unknown amount
    Given I sign to mobile website with user having "single_card_sa" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "sa_other_amount_msg" on schedule payment section on my account page
    And I should see View details link as part of the scheduled payment message

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with Autopay of known amount
    Given I sign to mobile website with user having "single_card_sa" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "sa_known_amount_msg" on schedule payment section on my account page
    And I should see View details link as part of the scheduled payment message

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with Autopayment is in process
    Given I sign to mobile website with user having "single_card_pa" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "pa_payment_message" on schedule payment section on my account page
    And I should not see view details link on the UI for my account page

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with Multiple payments, of which one is an Autopayment, and the upcoming payment amount is KNOWN
    Given I sign to mobile website with user having "single_card_ma" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "ma_known_amount_msg" on schedule payment section on my account page
    And I should see View details link as part of the scheduled payment message

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with Multiple payments, of which one is an Autopayment, and the upcoming payment amount is UnKNOWN
    Given I sign to mobile website with user having "single_card_ma_unknown" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should see "ma_unknown_amount_msg" on schedule payment section on my account page
    And I should see View details link as part of the scheduled payment message

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with single scheduled payment
    Given I sign to mobile website with user having "single_card_ss" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    And I should see View details link as part of the scheduled payment message
    When I click on View details link
    Then I should redirect to manage payments page

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with multiple scheduled payments
    Given I sign to mobile website with user having "single_card_ms" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    And I should see View details link as part of the scheduled payment message
    When I click on View details link
    Then I should redirect to manage payments page

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with single auto payment
    Given I sign to mobile website with user having "single_card_sa" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    And I should see View details link as part of the scheduled payment message
    When I click on View details link
    Then I should redirect to manage autopay page

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with one scheduled payment and one auto payment
    Given I sign to mobile website with user having "single_card_ma" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    And I should see View details link as part of the scheduled payment message
    When I click on View details link
    Then I should redirect to manage payments page

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with scheduled payment processing already started
    Given I sign to mobile website with user having "single_card_ps" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    And I should not see view details link on the UI for my account page

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with auto payment processing already started
    Given I sign to mobile website with user having "single_card_pa" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    And I should not see view details link on the UI for my account page

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: View details link user not having any schedule payments
    Given I sign to mobile website with user having "single_card_n" added in profile
    When I navigate the global navigation menu as follows:
      | MY ACCOUNT |
    Then I should not see message is displaying on the UI for my account page



