Feature: Verify email content for mwedd_fil_252_10_mreg_shipment_2nd_delay template

  @mwedd_fil_252_10_mreg_shipement_2nd_delay
  @regression
  Scenario: Verify Email content for for template mwedd_fil_252_10_mreg_shipment_2nd_delay
    Given i trigger "mwedd_fil_252_10_mreg_shipment_2nd_delay" input through csp testemail
	And i have an enhanced payload sent to email provider 
	When i navigate to view the email page
	Then i should see pre header:
	"""
	Your Macy's registry gift is still on backorder. View Registry
	"""
	And i should see static contents:
	"""
	We’re sorry, but a registry gift that was purchased by your guest is further delayed with the vendor. The new estimated ship date is below. We apologize for the inconvenience.	Want to make any changes? Visit your registry.
	"""
	Then i should see order number:
	"""
	Order #: <ordernumber>	
	"""
	
	Then i should see order capture date:
	
	"""
	Order date: <orderdate>	
	"""
	Then i should see firstname
	Then i should see default categories:
	"""
	FOR THE HOME,MEN,WOMEN,SHOES
	"""
	And i should see Macys logo  	
    And i should see button as "view my registry"
	Then i should see product name
    And i should see product quantity 
	And i should see product color
	And i should see product size
	And i should see product status
	And i should see product type
	And i should see product name url valid
	And i should see ship date
	And i should see estimated ship date 
	
	 @optional
   Scenario: Verify optional fields empty scenario for template mwedd_fil_252_10_mreg_shipment_2nd_delay
    Given i trigger "mwedd_fil_252_10_mreg_shipment_2nd_delay_optional" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """