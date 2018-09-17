Feature: Verify email content for 260 indefinite specified wait template

  @mwedd_fil_260_12B_indefinite_specified_wait
  @regression
  
  Scenario: Verify Email content  for template mwedd_fil_260_12B_indefinite_specified_wait
    Given i trigger "mwedd_fil_260_12B_indefinite_specified_wait" input through csp testemail
	And i have an enhanced payload sent to email provider 
	When i navigate to view the email page
	Then i should see pre header:
	"""
	Thanks for choosing to wait for your registry order. Shop Now
	"""
	Then i should see static contents:
	"""
	Thanks for choosing to wait for your backordered registry item(s). As you requested, we'll continue to hold the registry item(s) shown below indefinitely. We'll let you know when your order is being processed or if there are further delays. 	As always, you can change or cancel your order at any time, just please let us know before it ships. You may call 1-800-BUY-MACY (1-800-289-6229) 24 hours a day, 7 days a week
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
   