Feature: Verify fedfil_bops_order_notification 264_14S_BLCOM email content

  @regression
  @blcom_eod_264_14S_fedfil_bops_order_notification
  @blcom_eod_264
  Scenario: Verify email content for template blcom_eod_264_14S_fedfil_bops_order_notification
    Given i trigger "blcom_eod_264_14S_fedfil_bops_order_notification" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Print this email and bring it to the specified store location.
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see body text
    And i should see order ready text
    And i should see order number
    And i should see order capture date
    And i should see store name
    And i should see store address
    And i should see store today's hours
    And i should see store tomorrow's hours
    And i should see phone number for general questions
    And i should see phone number for in-store pickup questions
    And i should see pickup barcode
    And i should see store pickup instructions
    And i should see "IN-STORE PICKUP INFORMATION" text
    And i should see "Contact Information" text
    And i should see contact information
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see order details
    And i should see instore pickup text and option