Feature: Verify email content for 245_return_confirmation template
   @mwedd_fil_245_5A_return_confirmation
   @regression
   Scenario: Verify Email content
     Given i trigger "mwedd_fil_245_5A_return_confirmation" input through csp testemail
     And i have an enhanced payload sent to email provider
     When i navigate to view the email page
     And i should see pre header:
       """
       Thanks for returning your registry item(s). Shop Macy's
       """
       And i should see static contents:
 	
 	  """
 	 You're all set! We've received the item(s) listed below and we're refunding your account. Thanks for shopping at Macy's.
       """   
     Then i should see default categories:
       """
       FOR THE HOME,MEN,WOMEN,SHOES
       """
     And i should see Macys logo
     And i should see order number:
     """
	Order #: <ordernumber>	
	""" 
	Then i should see order capture date:
	
	"""
	Order date: <orderdate>	
	"""
     And i should see button as "shop macy's"
     Then i should see product name
     And i should see product color
     And i should see product size
     And i should see product type
     And i should see return status description
     And i should see product image
     Then i should see product image url valid
     And i should see product name url valid
     And i should see "Order total" in the email
     And i should see "Shipping" in the email
     And i should see "Shipment upgrade fee amount" in the email
     And i should see "PR VAT" in the email
     And i should see "PR Municipal" in the email
     And i should see "TOTAL" in the email