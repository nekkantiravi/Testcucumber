 Feature: Verify BCOM 240 order confirmation email content
 
   @regression
   @blcom_fil_240_1B_fedfil_order_confirmation
   @blcom_fil_240
   Scenario: Verify Email content for tempalte blcom_fil_240_1B_fedfil_order_confirmation
    Given i trigger "blcom_fil_240_1B_fedfil_order_confirmation" input through csp testemail
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
 	And i should see bloomingdales logo
	And i should see freeshipping image
 	And i should see firstname
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
	
	
	   @blcom_fil_240_1B_fedfil_order_confirmation_apple_payment  @regression
    Scenario: Verify Email content for template blcom_fil_240_1B_fedfil_order_confirmation_apple_payment
    Given i trigger "blcom_fil_240_1B_fedfil_order_confirmation_apple_payment" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
	  And i should see "PAYMENT METHOD" text
	  And i should see apple payment info
	  
  
  @production
  Scenario: Production : Verify email template for blcom_fil_240_1B_fedfil_order confirmation
    Given i trigger "blcom_fil_240_1B_fedfil_order_confirmation" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo