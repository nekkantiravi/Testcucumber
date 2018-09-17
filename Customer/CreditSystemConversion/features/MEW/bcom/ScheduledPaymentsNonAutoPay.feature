##########################################################################
# Story: B-88840 :: OTSP2:: BUS:: BCOM:: UI:: MEW/Desktop:: CG:: Display NON-Autopay instances of Scheduled Payment data
#        B-92163 :: OTSP2: BUS:: BCOM:: EDIT link destination for various Scheduled Payment use cases (Single card)
# Author: QE Team
############################################################################

Feature: As a user with a card in profile, for which one or more non-autopay scheduled payment(s) exist, I want to see that data on the MCOM Credit Gateway page.

# Pre-Requisite:1.Customer is signed in and has one or multiple cards added to their account.
#       2.Customer has navigated to the Credit Gateway Page on Desktop.
#       3.Feature will be Wrapped under Kill Switch: creditSchedulePaymentEnabled=true

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User should not have any schedule payments
    Given I sign to mobile website with user having "single_card_n" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should not see below elements in credit service gateway page:
    |schedule_payment_message|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with single scheduled payment
    Given I sign to mobile website with user having "single_card_ss" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ss_payment_message" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with multiple scheduled payments
    Given I sign to mobile website with user having "single_card_ms" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ms_payment_message" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single card with scheduled payment processing already started
    Given I sign to mobile website with user having "single_card_ps" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ps_payment_message" on schedule payment section
    And I should not see below elements in credit service gateway page:
      |edit_in_schedule_payment|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User should not see scheduled payment message when card not added to profile
    Given I visit the web site as a registered user
    When I navigate to credit service gateway page on MEW
    Then I should not see below elements in credit service gateway page:
      |schedule_payment_message|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple cards and multi scheduled payments for all cards
    Given I sign to mobile website with user having "multi_card_ms_all" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ms_payment_message" on schedule payment section
    When I should see next card using right carousel
    Then I should see "ms_payment_message" on schedule payment section

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple cards and single scheduled payments for all cards
    Given I sign to mobile website with user having "multi_card_ss_all" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ss_payment_message" on schedule payment section
    When I should see next card using right carousel
    Then I should see "ss_payment_message" on schedule payment section

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple cards with different schedule payment for each card
    Given I sign to mobile website with user having "multi_ss_ms_mixed" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ss_payment_message" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|
    When I should see next card using right carousel
    Then I should see "ms_payment_message" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple cards and scheduled payments for default card only
    Given I sign to mobile website with user having "multi_default_ss" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ss_payment_message" on schedule payment section
    And I should see next card using right carousel
    Then  I should not see below elements in credit service gateway page:
      |schedule_payment_message|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User dual card having scheduled payments on only one line of card
    Given I sign to mobile website with user having "dual_child_ss" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ss_payment_message" on schedule payment section

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User dual card having diffent scheduled payments on two lines
    Given I sign to mobile website with user having "dual_ss_ms_mixed" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ss_payment_message" on schedule payment section
    And I tap on "out_store_account"
    Then I should see "ms_payment_message" on schedule payment section

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User dual card having single scheduled payments on both lines
    Given I sign to mobile website with user having "dual_ss_both" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ss_payment_message" on schedule payment section
    And I tap on "out_store_account"
    Then I should see "ss_payment_message" on schedule payment section

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User dual card having multiple scheduled payments on both lines
    Given I sign to mobile website with user having "dual_ms_both" added in profile
    When I navigate to credit service gateway page on MEW
    Then I should see "ms_payment_message" on schedule payment section
    And I tap on "out_store_account"
    Then I should see "ms_payment_message" on schedule payment section

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario Outline: Edit link navigation for single card profile
    Given I sign in with user having "<user_type>" added in profile
    When I navigate to the "credit services" page from footer
    And I tap on "edit_in_schedule_payment"
    Then I should redirect to "<target>" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | user_type      | target      | expected_process_indicator |
      | single_card_ss | citi_fusion | payments                   |
      | single_card_ms | citi_fusion | payments                   |