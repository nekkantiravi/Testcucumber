Feature: Verify fed fil bops order confirmation email content
 
  @bwedd_fil_240_1N_fedfil_bops_order_confirmation
  @regression
  @bwedd_fil_240
  Scenario: Verify email content for fil_240_1N_bwedd fedfil bops order confirmation template
  Given i trigger "bwedd_fil_240_1N_fedfil_bops_order_confirmation" input through csp testemail
  And i have an enhanced payload sent to email provider 
  When i navigate to view the email page
  
  Then i should see static contents:
  """
  We have begun processing your registry order. As soon as it's available for pickup, we will send you an email with details on when and where you can pick it up. You can view your order status and history at any time by visiting My Account.
  """
  And i should see static text:
  """
  Please wait to come to the store until you've received a confirmation email that your order is ready to be picked up.
  """
  And i should see preheader:
  """
   A separate email will confirm when your registry order is ready for pickup. 
  """
  And i should see header:
  """
  LOOK FOR AN EMAIL CONFIRMING YOUR PICKUP IS READY
 """
  And i should see bloomingdales registry logo
  And i should see freeshipping image
  And i should see estimated pick up date
  And i should see customer care static text
  """
  Call Customer Service at 1-800-777-0000 with questions about your order
  """
  And i should see order number
  And i should see order capture date
  And i should see billing address
  And i should see "IN-STORE PICKUP INFORMATION" text
  And i should see sms opted message text
  And i should see pickup instore items
  And i should see store details
  And i should see store bops phone number
  And i should see shipment phone number
  And i should see "ITEM DETAILS" text
  And i should see product details
  And i should see order details
  And i should see "PAYMENT METHOD" text
  And i should see payment card info
  And i should see "THIS ORDER IS A GIFT" text
  And i should see gift message
  
  
  	@production
  	Scenario: Production : Verify email template for fil_240_1N_bwedd fedfil bops order confirmation
    Given i trigger "bwedd_fil_240_1N_fedfil_bops_order_confirmation" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales registry logo