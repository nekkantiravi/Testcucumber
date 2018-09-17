##########################################################################
# Story: B-90353 :: OTSP2: BUS:: MCOM:: MEW/Desktop:: CG:: Display Autopay instances of Scheduled Payment
#        B-92636 :: OTSP2: BUS:: MCOM:: EDIT link destination for various Scheduled Payment use cases (Only for Single card)
# Author: QE Team
############################################################################

Feature: As a user with a card in profile, for which one or more non-autopay scheduled payment(s) exist, I want to see that data on the MCOM Credit Gateway page.

# Pre-Requisite:1.Customer is signed in and has one or multiple cards added to their account.
#       2.Customer has navigated to the Credit Gateway Page on Desktop.
#       3.Feature will be Wrapped under Kill Switch: creditSchedulePaymentEnabled=true

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single auto pay scheduled payment for full statement balance
    Given I sign in with user having "single_sa_sb" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "sa_statement_full_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single auto pay scheduled payment for minimum payment due
    Given I sign in with user having "single_sa_mpd" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "sa_min_pay_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single auto pay scheduled payment for minimum payment due, plus fixed amount
    Given I sign in with user having "single_sa_mpd_fixed" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "sa_min_pay_fixed_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single auto pay scheduled payment for other amount
    Given I sign in with user having "single_sa_oa" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "sa_other_amount_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having single auto pay of KNOWN amount
    Given I sign in with user having "single_sa_known" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "sa_known_amount_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple payments with known upcoming payment
    Given I sign in with user having "single_ma_known" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "ma_known_amount_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple payments with unknown upcoming payment and payment description is null
    Given I sign in with user having "single_ma_known_desc_null" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "ma_known_amount_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple payments with unknown upcoming payment
    Given I sign in with user having "single_ma_unknown" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "ma_unknown_amount_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having multiple payments with known upcoming payment and payment description is null
    Given I sign in with user having "single_ma_unknown_desc_null" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "ma_unknown_amount_msg" on schedule payment section
    And I should see below sections on gateway page
      |edit_in_schedule_payment|

  @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: User having scheduled autopayment which is in progress
    Given I sign in with user having "single_pa" added in profile
    When I navigate to the "credit services" page from footer
    Then I should see "pa_payment_message" on schedule payment section
    And I should not see below elements in credit service gateway page:
      |edit_in_schedule_payment|

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario Outline: Edit link navigation for profile having single card with autopay schedule payments
    Given I sign in with user having "<user_type>" added in profile
    When I navigate to the "credit services" page from footer
    And I tap on "edit_in_schedule_payment"
    Then I should redirect to "<target>" page
    And I should see "<expected_process_indicator>" process indicator
    Examples:
      | user_type                   | target      | expected_process_indicator |
      | single_sa_sb                | citi_fusion | autopay                    |
      | single_sa_mpd               | citi_fusion | autopay                    |
      | single_sa_mpd_fixed         | citi_fusion | autopay                    |
      | single_sa_oa                | citi_fusion | autopay                    |
      | single_sa_known             | citi_fusion | autopay                    |
      | single_ma_known             | citi_fusion | payments                   |
      | single_ma_known_desc_null   | citi_fusion | payments                   |
      | single_ma_unknown           | citi_fusion | payments                   |
      | single_ma_unknown_desc_null | citi_fusion | payments                   |