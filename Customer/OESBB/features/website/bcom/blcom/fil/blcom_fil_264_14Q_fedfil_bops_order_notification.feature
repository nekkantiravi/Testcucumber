Feature: Verify bops order notification 264_14Q_BCOM email content

  @regression
  @blcom_fil_264_14Q_fedfil_bops_order_notification
  @blcom_fil_264
  Scenario: Verify email content for template blcom_fil_264_14Q_fedfil_bops_order_notification
    Given i trigger "blcom_fil_264_14Q_fedfil_bops_order_notification" input through csp testemail
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
    And i should see order number
    And i should see order capture date
    And i should see order number
    And i should see store name
    And i should see store address
    And i should see store today's hours
    And i should see store tomorrow's hours
    And i should see phone number for general questions
    And i should see phone number for in-store pickup questions
    And i should see pickup barcode
    And i should see store pickup instructions
    And i should see billing address
    And i should see "IN-STORE PICKUP INFORMATION" text
    And i should see "Contact Information" text
    And i should see contact information
    And i should see "PAYMENT METHOD" text
    And i should see payment card info
    And i should see "THIS ORDER IS A GIFT" text
    And i should see gift message
    And i should see gift receipt option
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see order details
    And i should see instore pickup text and option
    
     @optional
    Scenario: Verify optional fields empty scenario for template blcom_fil_264_14Q_fedfil_bops_order_notification_optional
    Given i trigger "blcom_fil_264_14Q_fedfil_bops_order_notification_optional" input through csp testemail
    When i navigate to view the email page
    And i should see bloomingdales logo