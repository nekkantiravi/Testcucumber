Feature: Verify MCOM 264 single shipment confirmation email content

  @regression
  @mcom_fil_264_14P_single_shipment_confirmation
  @mcom_fil_264
  Scenario: Verify Email content for template mcom_fil_264_14P_single_shipment_confirmation
	Given i trigger "mcom_fil_264_14P_single_shipment_confirmation" input through csp testemail
	And i have an enhanced payload sent to email provider
	When i navigate to view the email page
	Then i should see order number
	And i should see order capture date
	Then i should see firstname
	Then i should see default categories:
	"""
	FOR THE HOME,MEN,WOMEN,SHOES
	"""
	And i should see Macys logo
	And i should see billing address
	And i should see shipping address
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
	

  @optional
   Scenario: Verify optional fields empty scenario for template mcom_fil_264_14P_single_shipment_confirmation
    Given i trigger "mcom_fil_264_14P_single_shipment_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo