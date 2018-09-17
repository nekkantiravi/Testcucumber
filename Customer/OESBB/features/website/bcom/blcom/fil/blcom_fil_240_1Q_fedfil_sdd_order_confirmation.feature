Feature: Verify fedfil sdd order confirmation email content

  @blcom_fil_240_1Q_fedfil_sdd_order_confirmation 
  @regression
  @blcom_fil_240
  Scenario: Verify email content for template blcom_fil_240_1Q_fedfil_sdd_order_confirmation
    Given i trigger "blcom_fil_240_1Q_fedfil_sdd_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see static contents:
      """
      Thank you for shopping at <link>Bloomingdale's<link>. We have begun processing your order for Same Day Delivery. Please check for a follow-up email to schedule a specific delivery time frame. You can view your order status and history at any time by visiting <link>My Account<link>.
      """
    And i should see preheader:
      """
      Thank you for shopping at Bloomingdale's.
      """
    And i should see firstname
    And i should see bloomingdales logo    
    And i should see "ORDER DETAILS" text
    And i should see order number
    And i should see order capture date
    And i should see billing address
    And i should see "CHECK ORDER DETAILS" button
    And i should see note text as:
      """
      NOTE: Changes to your order must be made by phone (<link>1-800-777-0000<link>), within 15 minutes of when the order was submitted. <link>Learn more<link>
      """
    And i should see "SHIPMENT DETAILS" text    
    And i should verify shipment details
    And i should see "ITEM DETAILS" text
    #And i should see product details
    And i should see order details
    And i should see "PAYMENT METHOD" text
    And i should see payment card info
    And i should see "THIS ORDER IS A GIFT" text
    And i should see loyalty section in the email
	  And i should see the mentioned text in the email
	|NOTE: Your point balance will be updated once your order ships. Purchase amounts paid with a Gift Card or REWARD CARD do not receive Loyallist points.|

	 @optional
   Scenario: Verify optional fields empty scenario for template blcom_fil_240_1Q_fedfil_sdd_order_confirmation
    Given i trigger "blcom_fil_240_1Q_fedfil_sdd_order_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo