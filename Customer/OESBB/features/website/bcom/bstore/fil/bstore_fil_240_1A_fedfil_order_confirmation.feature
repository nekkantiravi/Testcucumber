Feature: Verify fedfil order confirmation email content

  @bstore_fil_240_1A_fedfil_order_confirmation @regression @bstore_fil_240
  Scenario: Verify email content for template bstore_fil_240_1A_fedfil_order_confirmation
    Given i trigger "bstore_fil_240_1A_fedfil_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      Thank you for shopping at Bloomingdale's. We have begun processing your order. You can view your order status and history at any time by visiting <link>My Account<link>.
      """
    And i should see preheader:
      """
      We've received your order.
      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see "CHECK ORDER STATUS" button
    And i should see billing address
    And i should see "SHIPMENT DETAILS" text
    And i should see delivery method
    And i should verify shipment details
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see product name
    And i should see "YOUR ORDER CONFIRMATION" text
    And i should see order details
    And i should see "PAYMENT METHOD" text
    And i should see payment card info
    And i should see "THIS ORDER IS A GIFT" text
    And i should see gift message

  @bstore_fil_240_1A_fedfil_order_confirmation_premium_shipping @regression
  Scenario: Verify Email content for template bstore_fil_240_1A_fedfil_order_confirmation_premium_shipping
    Given i trigger "bstore_fil_240_1A_fedfil_order_confirmation_premium_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see freeshipping image
    And i should see shipping method
