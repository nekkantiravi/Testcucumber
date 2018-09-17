Feature: Verify MWEDD 264 multiship confirmation email content
	
	@regression
	@mwedd_eod_264_14B_multi_ship_confirmation
	@mwedd_eod_264
	Scenario: Verify Email content for template mwedd_eod_264_14B_multi_ship_confirmation
		Given i trigger "mwedd_eod_264_14B_multi_ship_confirmation" input through csp testemail
		And i have an enhanced payload sent to email provider 
		When i navigate to view the email page
		Then i should see pre header:
		"""
		Macy's Magic is on the way. Order Status
		"""
		And i should see body text:
		"""
		We're happy to let you know one or more of your items has shipped. If you bought multiple items, they may ship separately. Please note, you may not be able to track your package(s) right away. It can take 24-48 hours for the carrier to update their records with your shipping information. Thanks for shopping at Macy's.
		"""
		Then i should see order number
		And i should see order capture date
		Then i should see billing firstname
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
		And i should see ship date
		And i should see shipment type
		And i should see tracking number
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