Feature: Verify MWEDD 540 split shipment notification email content

  @regression
  @mwedd_fil_540_1M_split_shipment_notification
  @mwedd_fil_540
  Scenario: Verify Email content for template mwedd_fil_540_1M_split_shipment_notification
	Given i trigger "mwedd_fil_540_1M_split_shipment_notification" input through csp testemail
	And i have an enhanced payload sent to email provider 
	When i navigate to view the email page
	Then i should see order number
	And i should see order capture date
	Then i should see billing first name
	Then i should see default categories:
	"""
	FOR THE HOME,MEN,WOMEN,SHOES
	"""
	And i should see Macys logo  	
	And i should see billing address
	And i should see registrant address
	And i should see shipping method
	And i should see gift box
	And i should see gift receipt
	And i should see gift message
	Then i should see credit card type
	Then i should see product name
	And i should see product quantity
	And i should see product color
	And i should see product size
	And i should see product type 
	And i should see product price
	And i should see product image
	Then i should see product image url valid 
	And i should see product name url valid    
 	And i should see "Order total" in the email
 	And i should see "Shipping" in the email
 	And i should see "Additional shipment fee amount" in the email
 	And i should see "Shipment upgrade fee amount" in the email
 	And i should see "Gift wrap fee amount" in the email
 	And i should see "TOTAL" in the email
 	And i should see static contents in note section:
 	"""
Please note: Your tracking number may be delayed as it can take our carrier 24-48 hours to update their records with your shipping info. Learn More."""
	And i should see estimated ship dates