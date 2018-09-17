Feature: Verify fedfil shipment confirmation email content

  @blcom_fil_264_14G_fedfil_shipment_confirmation @regression @blcom_fil_264
  Scenario: Verify email content for template blcom_fil_264_14G_fedfil_shipment_confirmation
    Given i trigger "blcom_fil_264_14G_fedfil_shipment_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      Good news: Your Bloomingdale´s E-Gift Card has been sent to the recipient via email.
      """
    And i should see preheader:
      """
       Thank you for shopping at Bloomingdale´s
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see billing address
    And i should see "CHECK ORDER STATUS" button
    And i should see note text as:
      """
      NOTE: Changes to your order must be made by phone (<link>1-800-777-0000<link>), within 30 minutes of when the order was submitted. <link>Learn more<link>
      """
    And i should see "SHIPMENT DETAILS" text
    And i should verify shipment details
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see order details
    And i should see "PAYMENT METHOD" text
    And i should see payment card info
    And i should see "THIS ORDER IS A GIFT" text
    And i should see gift message

  @blcom_fil_264_14G_fedfil_shipment_confirmation_email_shipping 
  @regression
  Scenario: Verify Email content for template blcom_fil_264_14G_fedfil_shipment_confirmation_email_shipping
    Given i trigger "blcom_fil_264_14G_fedfil_shipment_confirmation_email_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method