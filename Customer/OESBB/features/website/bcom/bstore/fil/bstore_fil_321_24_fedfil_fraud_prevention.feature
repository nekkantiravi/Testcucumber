 Feature: Verify BSTORE 321 fedfil fraud prevention email content
 
   @regression
   @bstore_fil_321_24_fedfil_fraud_prevention
   @bstore_fil_321
   Scenario: Verify Email content for template bstore_fil_321_24_fedfil_fraud_prevention
    Given i trigger "bstore_fil_321_24_fedfil_fraud_prevention" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
 	Then i should see static contents:
    """
    Unfortunately, we were unable to complete your order and had to cancel it. You have not been charged. If you have any questions or would like assistance in placing a new order, please contact Customer Service at 1-800-777-0000.
    """
 	And i should see preheader:
	"""
	You have not been charged.
	"""
	And i should see firstname
 	And i should see bloomingdales logo
	And i should see freeshipping image	
 	And i should see "ORDER DETAILS" text
 	And i should see order number
 	And i should see payment method
 	And i should see order capture date
	And i should see "ITEM DETAILS" text
	And i should see product details