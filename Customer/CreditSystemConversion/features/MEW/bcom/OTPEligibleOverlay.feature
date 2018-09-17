##########################################################################
# Story: B-91421 :: OTSP3:: BUS:: MCOM:: UI:: Desktop/MEW:: CG:: Eligible credit card, user clicks "Make a Payment"(Single card)
# Author: QE Team
############################################################################

  Feature: As a user with a card eligible for a One Time Payment on MCOM/BCOM, when I click the "Make A Payment" button on
    Credit Gateway, I want to be taken to the new One Time Payment set up page.

# Pre-Requisite:1.Customer is signed in and credit card account in Fusion profile.
#       2.In Scheduled Payment API, payment_eligibility_flag = Y

    @wip @domain_customer @artifact_shopapp @project_otsp @release_
    Scenario: Verify OTP1 page for user with one single line card added to profile
      Given I sign to mobile website with user having "otp_eligible" added in profile
      When I navigate to credit service gateway page on MEW
      And I tap "make_payment_button" on "credit_service_gateway_signedin" page
      And I should see below elements in OTP1 page:
        | macys_credit_card_header                               |
        | card_image                                             |
        | cardType                                               |
        | payment_amount_header                                  |
        | minimum_payment_due_radio_button                       |
        | minimum_payment_due_value                              |
        | statement_balance_radio_button                         |
        | statement_balance_value                                |
        | current_balance_radio_button                           |
        | current_balance_value                                  |
        | other_amount_radio_button                              |
        | other_amount_value                                     |
        | payment_date_header                                    |
        | today_radio_button                                     |
        | payment_due_date_radio_button                          |
        | payment_due_date_value                                 |
        | other_date_radio_button                                |
        | other_date_value                                       |
        | payment_source_header                                  |
        | payment_source_message_OTP1                            |
        | tooltip_icon                                           |
        | continue_button                                        |
        | cancel_button                                          |
      And I should see below values in OTP1 page:
        | cardType                                               |
        | minimum_payment_due_value                              |
        | last_statement_balance_value                           |
        | current_balance_value                                  |
        | payment_due_date_value                                 |
      And I should see Payment Source on OTP1 page
      And I should see "OTP1_disclaimer" message


    @wip @domain_customer @artifact_shopapp @project_otsp @release_
    Scenario: Verify OTP1 page for user with multiple cards added to profile
      Given I sign to mobile website with user having "otp_eligible_multi" added in profile
      When I navigate to credit service gateway page on MEW
      Then I should see next card using right carousel
      And I tap "make_payment_button" on "credit_service_gateway_signedin" page
      And I should see below elements in OTP1 page:
        | macys_credit_card_header                               |
        | card_image                                             |
        | cardType                                               |
        | payment_amount_header                                  |
        | minimum_payment_due_radio_button                       |
        | minimum_payment_due_value                              |
        | statement_balance_radio_button                         |
        | statement_balance_value                                |
        | current_balance_radio_button                           |
        | current_balance_value                                  |
        | other_amount_radio_button                              |
        | other_amount_value                                     |
        | payment_date_header                                    |
        | today_radio_button                                     |
        | payment_due_date_radio_button                          |
        | payment_due_date_value                                 |
        | other_date_radio_button                                |
        | other_date_value                                       |
        | payment_source_header                                  |
        | payment_source_message_OTP1                            |
        #| tooltip_icon                                           |
        | continue_button                                        |
        | cancel_button                                          |
      And I should see below values in OTP1 page:
        | cardType                                               |
        | minimum_payment_due_value                              |
        | last_statement_balance_value                           |
        | current_balance_value                                  |
        | payment_due_date_value                                 |
      And I should see Payment Source on OTP1 page
      And I should see "OTP1_disclaimer" message

    @wip @domain_customer @artifact_shopapp @project_otsp @release_
    Scenario: Verify OTP2 page for user with one single line card added in Fusion profile with default values
      Given I sign to mobile website with user having "otp_eligible_multi" added in profile
      When I navigate to credit service gateway page on MEW
      And I tap "make_payment_button" on "credit_service_gateway_signedin" page
      When I continued to otp2 page with "minimum_payment_due" as payment amount and "today" as payment date
      Then I should see below elements in OTP2 page:
        | cardType_otp2                                      |
        | card_image_otp2                                    |
        | payment_amount_value_otp2                          |
        | payment_date_value_otp2                            |
        | payment_source_header_otp2                         |
        | payment_source_message_OTP2                        |
        | authorize_button                                   |
        | back_button                                        |
      And I should see below values in OTP2 page:
        | payment_amount_value_otp2                          |
        | payment_date_value_otp2                            |
      And I should see Payment Source on OTP2 page
      And I should see "OTP2_disclaimer" message


    @wip @domain_customer @artifact_shopapp @project_otsp @release_
    Scenario: Verify OTP2 page for user with one single line card (with positive(non-zero) current balance amount) added in Fusion profile.
      Given I sign to mobile website with user having "otp_eligible_multi" added in profile
      When I navigate to credit service gateway page on MEW
      And I tap "make_payment_button" on "credit_service_gateway_signedin" page
      When I continued to otp2 page with "other_amount" as payment amount and "other_date" as payment date
      Then I should see below elements in OTP2 page:
        | cardType_otp2                                      |
        | card_image_otp2                                    |
        | payment_amount_value_otp2                          |
        | payment_date_value_otp2                            |
        | payment_source_header_otp2                         |
        | payment_source_message_OTP2                        |
        | authorize_button                                   |
        | back_button                                        |
      And I should see below values in OTP2 page:
        | payment_amount_value_otp2                          |
        | payment_date_value_otp2                            |
      And I should see Payment Source on OTP2 page
      And I should see "OTP2_disclaimer" message

    @wip @domain_customer @artifact_shopapp @project_otsp @release_18A
    Scenario: Verify whether User landing on Fusion page "Manage Payments", after user clicks on More Payment Source Options link on OTP1 page
      Given I sign to mobile website with user having "otp_eligible" added in profile
      When I navigate to credit service gateway page on MEW
      When I tap on "make_payment_button"
      Then I should redirect to "one_time_payment" page
      And I tap "more_payment_source_options" on "one_time_payment" page
      Then I should redirect to manage payments page