 Feature: Verify BWEDD fedfil price error email content
 
   @regression
   @bwedd_fil_409_28_fedfil_price_error
   @bwedd_fil_409
   Scenario: Verify Email content for template bwedd_fil_409_28_fedfil_price_error
    Given i trigger "bwedd_fil_409_28_fedfil_price_error" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
 	Then i should see body text for fedfil price error
 	And i should see bloomingdales registry logo
	And i should see freeshipping image	
 	And i should see "ORDER DETAILS" text
 	And i should see order number
 	And i should see order capture date