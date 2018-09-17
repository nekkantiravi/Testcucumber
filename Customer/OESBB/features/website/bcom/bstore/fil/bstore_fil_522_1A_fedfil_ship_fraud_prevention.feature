 Feature: Verify BCOM 522 fedfil ship fraud prevention email content
 
   @regression
   @bstore_fil_522_1A_fedfil_ship_fraud_prevention
   @bcom_fil_522
   Scenario: Verify Email content for template bstore_fil_522_1A_fedfil_ship_fraud_prevention
    Given i trigger "bstore_fil_522_1A_fedfil_ship_fraud_prevention" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
  	And i should see preheader:
	"""
	Important information about your order
	"""
	And i should see bloomingdales logo
	And i should see freeshipping image	
	And i should see header:
	"""
	ACTION NEEDED: CONTACT CUSTOMER SERVICE
	"""
	And i should see firstname
	Then i should see static contents:
	"""
	Unfortunately, we are unable to ship part of your order because your payment was declined. Please contact Customer Service at 1-800-777-0000 to authorize a new payment method or the item(s) below will be canceled.
	"""
	And i should see "ORDER DETAILS" text
	And i should see order number
	And i should see order capture date
	And i should see "ITEM DETAILS" text
	And i should see product details
	Then i should see static contents for com template:
      """
      Thank you, 
	  Bloomingdale's Customer Service 
	   Call us at any time: 1-800-777-0000 

	   We're available 24hours, 7 days a week. 
	   customerservice@bloomingdales.com 
      """
      And i should see "CUSTOMER SERVICE" link