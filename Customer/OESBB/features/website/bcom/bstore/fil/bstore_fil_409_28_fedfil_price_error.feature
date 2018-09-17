 Feature: Verify BSTORE fedfil price error email content
 
   @regression
   @bstore_fil_409_28_fedfil_price_error
   @bstore_fil_409
   Scenario: Verify Email content for template bstore_fil_409_28_fedfil_price_error
    Given i trigger "bstore_fil_409_28_fedfil_price_error" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
 	Then i should see body text for fedfil price error
 	And i should see bloomingdales logo
	And i should see freeshipping image	
 	And i should see "ORDER DETAILS" text
 	And i should see order number
 	And i should see order capture date