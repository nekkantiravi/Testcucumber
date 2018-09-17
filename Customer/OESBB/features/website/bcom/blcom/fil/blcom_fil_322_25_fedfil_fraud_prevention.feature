 Feature: Verify BCOM 322 fedfil fraud prevention email content
 
   @regression
   @blcom_fil_322_25_fedfil_fraud_prevention
   @blcom_fil_322
   Scenario: Verify Email content for template blcom_fil_322_25_fedfil_fraud_prevention
    Given i trigger "blcom_fil_322_25_fedfil_fraud_prevention" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
 	Then i should see static contents:
    """
    To proceed with your order, please verify your billing and/or shipping information by contacting our Consumer Protection department as soon as possible at 1-800-537-0197 (Monday-Saturday 9AM-9PM, Sunday 9AM-7PM).
    """
 	And i should see preheader:
	"""
	Please contact Customer Service.
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