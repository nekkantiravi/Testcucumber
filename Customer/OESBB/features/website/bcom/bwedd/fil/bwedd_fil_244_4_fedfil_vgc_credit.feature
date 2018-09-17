 Feature: Verify BWEDD email content for fedfil_vgc_credit template
   
   @regression
   @bwedd_fil_244_4_fedfil_vgc_credit 
  Scenario: Verify Email content for template bwedd_fil_244_4_fedfil_vgc_credit 
  Given i trigger "bwedd_fil_244_4_fedfil_vgc_credit" input through csp testemail
 	And i have an enhanced payload sent to email provider 
 	When i navigate to view the email page
 	And i should see preheader:
	"""
	A credit or refund will be issued.
	"""
	And i should see bloomingdales registry logo
	And i should see freeshipping image
	Then i should see header:
	"""
	YOUR E-GIFT CARD RETURN IS BEING PROCESSED
	"""
	And i should see firstname for return confirmation
 	Then i should see static contents:
    """
    We are processing the return of your E-Gift Card. A credit or refund will be issued to the card that you used.
    """
 	And i should see "ORDER DETAILS" text
 	And i should see order number
 	And i should see order capture date
 	And i should see refund amount
 	#Additional space is displaying after card number. Not implemented as code will be duplicated
  #And i should see refunded to card info
	And i should see "ITEM DETAILS" text
	And i should see egift card product details
	And i should see product requested quantity