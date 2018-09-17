Feature: Verify fedfil shipment confirmation email content

  @blcom_fil_264_14B_fedfil_shipment_confirmation 
  @regression
  @blcom_fil_264
  Scenario: Verify email content for template blcom_fil_264_14B_fedfil_shipment_confirmation
    Given i trigger "blcom_fil_264_14B_fedfil_shipment_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      Good news: Part of your order has shipped! You can view your order status and history at any time by visiting My Account.
      """
    And i should see preheader:
      """
       View tracking information and order details.
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
    
     @blcom_fil_264_14B_fedfil_shipment_confirmation_standard_shipping
     @regression
  Scenario: Verify Email content for template blcom_fil_264_14B_fedfil_shipment_confirmation_standard_shipping
    Given i trigger "blcom_fil_264_14B_fedfil_shipment_confirmation_standard_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method
    
    @blcom_fil_264_14B_fedfil_shipment_confirmation_ground_shipping
    @regression
  Scenario: Verify Email content for template blcom_fil_264_14B_fedfil_shipment_confirmation_ground_shipping
    Given i trigger "blcom_fil_264_14B_fedfil_shipment_confirmation_ground_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method
    
   @blcom_fil_264_14B_fedfil_shipment_confirmation_premium_shipping
   @regression
  Scenario: Verify Email content for template blcom_fil_264_14B_fedfil_shipment_confirmation_premium_shipping
    Given i trigger "blcom_fil_264_14B_fedfil_shipment_confirmation_premium_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method
    
   @blcom_fil_264_14B_fedfil_shipment_confirmation_express_shipping
   @regression
  Scenario: Verify Email content for template blcom_fil_264_14B_fedfil_shipment_confirmation_express_shipping
    Given i trigger "blcom_fil_264_14B_fedfil_shipment_confirmation_express_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method
    
   @blcom_fil_264_14B_fedfil_shipment_confirmation_multi_shipments
   @regression
  Scenario: Verify Email content for template blcom_fil_264_14B_fedfil_shipment_confirmation_multi_shipments
    Given i trigger "blcom_fil_264_14B_fedfil_shipment_confirmation_multi_shipments" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
   And i should see shipping method
   
    @blcom_fil_264_14B_fedfil_shipment_confirmation_email_shipping
   @regression
  Scenario: Verify Email content for template blcom_fil_264_14B_fedfil_shipment_confirmation_email_shipping
    Given i trigger "blcom_fil_264_14B_fedfil_shipment_confirmation_email_shipping" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see shipping method