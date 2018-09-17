Feature: Verify fed fil split ship email content
	
  @bwedd_fil_540_1L_fedfil_split_ship_confirmation
  @regression
  @bwedd_fil_360
  Scenario: Verify email content for template bwedd_fil_540_1L_fedfil_split_ship_confirmation
    Given i trigger "bwedd_fil_540_1L_fedfil_split_ship_confirmation" input through csp testemail
	And i have an enhanced payload sent to email provider 
  	When i navigate to view the email page
  	#Then i sould see subject as
  	Then i should see static contents:
  	"""
  	This order will be delivered in multiple shipments. You will receive a separate shipping confirmation for each item in your order when it ships.
  	""" 
  	And i should see shipping policy as:
	"""
	You will not be charged additional shipping fees. <link>Shipping Policy<link>
	"""
  	And i should see preheader:
	"""
	Important information about your order.
	"""
	And i should see firstname
	And i should see bloomingdales registry logo
	And i should see freeshipping image
	And i should see "ORDER DETAILS" text
  	And i should see order number
  	And i should see order capture date without camel case label
    And i should see billing address
    And i should see "CHECK ORDER DETAILS" button
    And i should see note text as:
	"""
	NOTE: Changes to your order must be made by phone (<link>1-800-777-0000<link>), within 30 minutes of when the order was submitted. <link>Learn more<link>
	""" 
    And i should see "SHIPMENT DETAILS" text
 	And i should see delivery method
	And i should see shipping address as:
    """
    This order will be shipped to the address we have on file.
    """
    And i should see "ITEM DETAILS" text
	And i should see product details
	And i should see order details
	And i should see "PAYMENT METHOD" text
	And i should see payment card info