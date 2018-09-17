Feature: Verify whether email is getting triggered with optional fields being empty

  @mcom_fil_optional_fields_empty
  @sample
  Scenario Outline: Verify whether email is getting triggered with optional fields being empty
    Given i trigger "<template_name>" input through csp testemail
	 
	When i navigate to view the email page
	Then i should see default categories:
	"""
	FOR THE HOME,MEN,WOMEN,SHOES
	"""
	
	Examples:
	|template_name|
	|mcom_fil_240_1C_order_confirmation_optionalempty|
	|mcom_fil_240_1Q_sdd_order_confirmation_optionalempty|