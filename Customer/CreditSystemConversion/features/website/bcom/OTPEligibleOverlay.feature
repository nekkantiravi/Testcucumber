##########################################################################
# Story: B-91421 :: OTSP3:: BUS:: MCOM:: UI:: Desktop/MEW:: CG:: Eligible credit card, user clicks "Make a Payment"(Single card)
#        B-101391::OTSP4:: BUS:: BCOM:: Final cosmetic changes to OTP1
#        B-98945::OTSP4:: BUS:: BCOM:: For "Other Amount" value - Force only non-zero, numeric value, insure two-character decimal
#        B-89601::OTSP4:: BUS:: BCOM:: Radio button functionality for OTP1 Payment Amount choices, including grey-outs, default
# Author: QE Team
############################################################################

Feature: As a user with a card eligible for a One Time Payment on MCOM/BCOM, when I click the "Make A Payment" button on
  Credit Gateway, I want to be taken to the new One Time Payment set up page.

# Pre-Requisite:
#       1.Customer is signed in and has single card added to Fusion profile
#		2.Card should have a payment source already set up
#		3.Card should have a positive Current Balance
#		4.Card is eligible for One Time Payment
#       5.Customer has navigated to the Credit Gateway Page on Desktop
#		6.Customer clicked on MAKE A PAYMENT link and navigated to OTP1 screen
#		7.Feature will be wrapped under Kill Switch creditOnetimePaymentsEnabled=true

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify OTP1 page for user with one single line card added to profile
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    And I should see below elements in OTP1 page:
      | macys_credit_card_header         |
      | card_image                       |
      | cardType                         |
      | payment_amount_header            |
      | minimum_payment_due_radio_button |
      | minimum_payment_due_value        |
      | statement_balance_radio_button   |
      | statement_balance_value          |
      | current_balance_radio_button     |
      | current_balance_value            |
      | other_amount_radio_button        |
      | other_amount_value               |
      | payment_date_header              |
      | today_radio_button               |
      | payment_due_date_radio_button    |
      | payment_due_date_value           |
      | other_date_radio_button          |
      | other_date_value                 |
      | payment_source_header            |
      | payment_source_message_OTP1      |
      #| tooltip_icon                                           |
      | continue_button                  |
      | cancel_button                    |
    And I should see below values in OTP1 page:
      | cardType                     |
      | minimum_payment_due_value    |
      | last_statement_balance_value |
      | current_balance_value        |
      | payment_due_date_value       |
    And I should see Payment Source on OTP1 page
    And I should see "OTP1_disclaimer" message

  @wip @domain_customer @artifact_shopapp @project_otsp @release_17ZA
  Scenario: Verify whether User has entered only numeric characters, and an optional decimal point in the Other Amount box. 
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    And I click on "other amount" radio button
    When I enter only numeric characters in the text box
    And I click on the continue button
    Then I should be navigated otp2 page

  @wip @domain_customer @artifact_shopapp @project_otsp @release_17ZA
  Scenario: Verify error message when user has not entered any amount in other amount box and selects continue button
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    And I click on "other amount" radio button
    And I click on the continue button
    Then I should see error message below other amount box

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify payment due date on OTP1 page for user with one single line card added to profile
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should see payment due date

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify the tooltip functionality on OTP1 page for user with one single line card added to profile
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should see tooltip on the right of Current Balance dollar amount
    When I click on the tooltip
    Then callout box should open with copy text
    And I click on 'X' button on callout box
    Then I should not see callout box

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify the disclosure paragraph on OTP1 page for user with one single line card added to profile
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should see disclosure paragraph above continue and cancel button
    And I should see " Terms & Conditions" link in disclosure paragraph

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify the inline copy on OTP1 page for user with one single line card added to profile
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should see the inline copy below the payment source
    And I should see "More payment source options" link

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify OTP2 page for user with one single line card added in Fusion profile with default values
    Given I sign in with user having "otp_eligible_multi" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    When I continued to otp2 page with "minimum_payment_due" as payment amount and "today" as payment date
    Then I should see below elements in OTP2 page:
      | cardType_otp2               |
      | card_image_otp2             |
      | payment_amount_value_otp2   |
      | payment_date_value_otp2     |
      | payment_source_header_otp2  |
      | payment_source_message_OTP2 |
      | authorize_button            |
      | back_button                 |
    And I should see below values in OTP2 page:
      | payment_amount_value_otp2 |
      | payment_date_value_otp2   |
    And I should see Payment Source on OTP2 page
    And I should see "OTP2_disclaimer" message

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify OTP2 page for user with one single line card (with positive(non-zero) current balance amount) added in Fusion profile.
    Given I sign in with user having "otp_eligible_multi" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    When I continued to otp2 page with "other_amount" as payment amount and "other_date" as payment date
    Then I should see below elements in OTP2 page:
      | cardType_otp2               |
      | card_image_otp2             |
      | payment_amount_value_otp2   |
      | payment_date_value_otp2     |
      | payment_source_header_otp2  |
      | payment_source_message_OTP2 |
      | authorize_button            |
      | back_button                 |
    And I should see below values in OTP2 page:
      | payment_amount_value_otp2 |
      | payment_date_value_otp2   |
    And I should see Payment Source on OTP2 page
    And I should see "OTP2_disclaimer" message


  @wip @domain_customer @artifact_shopapp @project_otsp @release_18A
  Scenario: Verify whether User landing on Fusion page "Manage Payments", after user clicks on More Payment Source Options link on OTP1 page
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    When I tap on "make_payment_button"
    Then I should redirect to "one_time_payment" page
    And I tap "more_payment_source_options" on "one_time_payment" page
    Then I should redirect to manage payments page

  @wip @domain_customer @artifact_shopapp @project_otsp @release_Z
  Scenario: Verify all Radio button choices are available and Minimum Payment Due is the default radio button selected when statement balance and Minimum Payment Due value is >$0
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should be navigated to otp1 page
    And I should see all radio button choices are available
    And I should see Minimum Payment Due is the default radio button selected

  @wip @domain_customer @artifact_shopapp @project_otsp @release_Z
  Scenario: Verify Statement Balance is the default radio button selected and all Radio button choices are available expept Minimum Payment Due which is grayed out when Card has $0 Minimum Payment Due, but non-zero Statement Balance
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should be navigated to otp1 page
    And I should see all radio button choices are available except for Minimum Payment Due
    And I should see Raido button for Minimum Payment Due is grayed out
    And I should see Statement Balance is the default radio button selected

  @wip @domain_customer @artifact_shopapp @project_otsp @release_Z
  Scenario: Verify Minimum Payment Due is the default radio button selected and all Radio button choices are available expept Statement Balance which is grayed out when Card has $0 (or negative) Statement Balance, but non-zero Minimum Payment Due
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should be navigated to otp1 page
    And I should see Statement Balance is grayed out
    And I should see Minimum Payment Due is the default radio button selected

  @wip @domain_customer @artifact_shopapp @project_otsp @release_Z
  Scenario: Verify Current Balance is the default radio button selected and radio buttons for Minimum Payment Due AND Statement Balance cannot be chosen and grayed out Card has $0 for BOTH Minimum Payment Due and Statement Balance
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should be navigated to otp1 page
    And I should see below radio buttons are available:
      | Current Balance                          |
      | Other Amount                             |
    And I should see Raido button for Minimum Payment Due is grayed out
    And I should see Statement Balance is grayed out
    And I should see Current Balance is the default radio button selected

