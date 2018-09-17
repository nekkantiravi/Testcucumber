Feature: Verify MCOM 260 indefinite specified wait  email content

  @mcom_fil_260_12A_indefinite_specified_wait
  @regression
  @mcom_fil_260
  Scenario: Verify Email content for for template mcom_fil_260_12A_indefinite_specified_wait
    Given i trigger "mcom_fil_260_12A_indefinite_specified_wait" input through csp testemail
	And i have an enhanced payload sent to email provider 
	When i navigate to view the email page
	Then i should see pre header:
	"""
	Thanks for choosing to wait for your order. Shop Now
	"""
	And i should see body text:
	"""
	Thanks for choosing to wait for your backordered item(s). As you requested, we'll continue to hold the item(s) shown below. We'll let you know when your order is being processed or if there are further delays. The new estimated ship date is below. As always, you can change or cancel your order at any time, just please let us know before it ships. You may call 1-800-BUY-MACY (1-800-289-6229) 24 hours a day, 7 days a week.
	"""
	And i should see order number
	And i should see order capture date
	And i should see default categories:
	"""
	FOR THE HOME,MEN,WOMEN,SHOES
	"""
	And i should see Macys logo  
	And i should see the "SHOP MACY'S" button	
	And i should see product name
    And i should see product quantity 
	And i should see product color
	And i should see product type
	And i should see product price
	And i should see product status
	And i should see estimated ship date
	And i should see product name url valid  
	
	 @optional
   Scenario: Verify optional fields empty scenario for template mcom_fil_260_12A_indefinite_specified_wait
    Given i trigger "mcom_fil_260_12A_indefinite_specified_wait_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo