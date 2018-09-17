Feature: Verify BCOM 240 order confirmation email content

  @regression @blcom_fil_240_1A_fedfil_order_confirmation
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      Thank you for shopping at <link>Bloomingdale's<link>. We have begun processing your order. You can view your order status and history at any time by visiting <link>My Account<link>.
      """
    And i should see preheader:
      """
      We've received your order.
      

      """
    And i should see firstname
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "ORDER DETAILS" text
    Then i should see order number
    And i should see order capture date
    And i should see billing address
    And i should see "CHECK ORDER STATUS" button
    And i should see note text as:
      """
      Note: Changes to your order must be made by phone. Call Customer Service within 30 minutes of when your order was submitted at <link>1-800-777-0000<link>. <link>Learn more<link>
      """
    And i should see "SHIPMENT DETAILS" text
    And i should verify shipment details
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see order details
    And i should see "PAYMENT METHOD" text
    And i should see payment card info
    And i should see "THIS ORDER IS A GIFT" text
    ##Require test data to excute below commented methods
    #And i should see zone name in the email
    #And i should see pros product images
    #And i should see pros product names
    And i should see loyalty section in the email
    And i should see the mentioned text in the email
      | NOTE: Your point balance will be updated once your order ships. Purchase amounts paid with a Gift Card or REWARD CARD do not receive Loyallist points. |

  @optional
  Scenario: Verify optional fields empty scenario for template blcom_fil_240_1A_fedfil_order_confirmation
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo

  @blcom_fil_240_1A_fedfil_order_confirmation_standard_shipping @regression
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation_standard_shipping
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_standard_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method

  @blcom_fil_240_1A_fedfil_order_confirmation_ground_shipping @regression
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation_ground_shipping
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_ground_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method

  @blcom_fil_240_1A_fedfil_order_confirmation_premium_shipping @regression
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation_premium_shipping
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_premium_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method

  @blcom_fil_240_1A_fedfil_order_confirmation_express_shipping @regression
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation_express_shipping
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_express_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method

  @blcom_fil_240_1A_fedfil_order_confirmation_paypal_payment @regression
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation_paypal_payment
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_paypal_payment" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "PAYMENT METHOD" text
    And i should see payment card info

  @blcom_fil_240_1A_fedfil_order_confirmation_EGC65_Visa_payments @regression
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation_EGC65_Visa_payments
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_EGC65_Visa_payments" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see "PAYMENT METHOD" text
    And i should see payment card info

  @blcom_fil_240_1A_fedfil_order_confirmation_multi_shipments @regression
  Scenario: Verify Email content for template blcom_fil_240_1A_fedfil_order_confirmation_multi_shipments
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation_multi_shipments" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method
    
    
  @production
  Scenario: Production : Verify email template for blcom_fil_240_1A_fedfil_order confirmation
    Given i trigger "blcom_fil_240_1A_fedfil_order_confirmation" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo
