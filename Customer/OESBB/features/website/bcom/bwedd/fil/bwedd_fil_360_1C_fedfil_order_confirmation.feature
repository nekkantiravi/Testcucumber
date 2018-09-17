Feature: Verify fedfil order confirmation templates

  @bwedd_fil_360_1C_fedfil_order_confirmation 
  @regression
  @bwedd_fil_360
  Scenario: Verify email content for template bwedd_fil_360_1C_fedfil_order_confirmation
    Given i trigger "bwedd_fil_360_1C_fedfil_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page    
    Then i should see static contents:
      """
      We have processed your replacement for the damaged item(s) you received. The new item(s) should ship within 2-3 business days, at which time you will receive an email with tracking information. Please discard the damaged item(s) safely. We apologize for any inconvenience.Still need help or have questions? Contact Us
      
      """ 
    And i should see preheader:
      """
      Your replacement has been processed
      """
    And i should see firstname
    And i should see bloomingdales registry logo
    And i should see freeshipping image
    And i should see "DETAILS FOR THE ORIGINALLY ORDERED ITEM(S)" text
    And i should see order number
    And i should see order capture date
    And i should see original form of tender
    And i should see reason for return
    And i should see "CHECK ORDER STATUS" button
    And i should see "ITEM DETAILS" text
    And i should see product details
    And i should see "DETAILS FOR YOUR REPLACEMENT ITEM(S)" text
    And i should see replacement date
    
	   @bwedd_fil_360_1C_fedfil_order_confirmation_apple_payment  @regression
    Scenario: Verify Email content for template bwedd_fil_360_1C_fedfil_order_confirmation_apple_payment
    Given i trigger "bwedd_fil_360_1C_fedfil_order_confirmation_apple_payment" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see freeshipping image
	  And i should see apple payment info for 360 template
    
    