##########################################################################
# Story: B-98818 :: OTSP4:: BUS:: MCOM:: Date Picker functionality, restrict date choices
# Author: QE Team
############################################################################

Feature: As a user wishing to schedule my payment using Other Date, I want to see a date picker that restricts the dates I can pick to those that will be accepted by Citi.

# Pre-Requisite:1.Customer is signed in and has single card added to Fusion profile
#		2.Card should have a payment source already set up
#		3.Card should have a positive Current Balance
#		4.Card is eligible for One Time Payment
#       5.Customer has navigated to the Credit Gateway Page on Desktop.
#		6.Customer clicked on MAKE A PAYMENT link and navigated to OTP1 screen.
#		7.Feature will be wrapped under Kill Switch creditOnetimePaymentsEnabled=true


  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify Date picker is pre-populated with Today as the date as defined by Eastern Time US.
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should redirect to "one_time_payment" page
    And I should see "Payment Date Field" pre-populated with "Today" as the date as defined by Eastern Time U.S.

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify user is prevented from keying in a date using the keyboard.
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    And I should redirect to "one_time_payment" page
    When I tried to enter date into "Payment Date Field" using keyboard
    Then System should not accept the input and should display "Today" date

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify Date picker pops up in OTP1 page when clicked on 'Payment Date'
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit services" page from footer
    And I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should redirect to "one_time_payment" page
    When I tap "dp_date_picker_icon" on "one_time_payment" page
    Then Date picker popup should be displayed
    And I should see "Today" date, as defined by Eastern Time U.S., is pre-selected

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify Date picker popup UI in OTP1 page
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit gateway" page from footer
    When I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should redirect to "one_time_payment" page
    When I tap "dp_date_picker_icon" on "one_time_payment" page
    Then Date picker popup should be displayed
    And I should see the below fields in Date Picker Popup
      | Close Button            |
      | Next Month Arrow        |
      | Month Header            |
      | Year Header             |
      | Calendar                |
      | Todays Date             |
      | Greyed Out Dates        |
      | Non Greyed Out Dates    |
      | Message                 |
      | Choose This Date Button |
      | Cancel Button           |
    And I should not see below fields in Date Picker Popup
      | Previous Month Arrow |

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario Outline: Verify user is able to close the Date Picker popup using 'CANCEL' button
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit gateway" page from footer
    When I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should redirect to "one_time_payment" page
    When I tap "dp_date_picker_icon" on "one_time_payment" page
    Then Date picker popup should be displayed
    When I tap "<cancel button>" on "one_time_payment" page
    Then date picker popup should be closed
    And I should see "Payment Date Field" populated with "Today" as the date as defined by Eastern Time U.S.
    Examples:
      | cancel button    |
      | dp_close_button  |
      | dp_cancel_button |

  @wip @domain_customer @artifact_shopapp @project_otsp @release_
  Scenario: Verify message on Date Picker popup
    Given I sign in with user having "otp_eligible" added in profile
    When I navigate to the "credit gateway" page from footer
    When I tap "make_payment_button" on "credit_service_gateway_signedin" page
    Then I should redirect to "one_time_payment" page
    When I tap "dp_date_picker_icon" on "one_time_payment" page
    Then Date picker popup should be displayed
    And I should see "date_picker_popup_message" on "Date Picker" popup



