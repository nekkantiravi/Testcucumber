Feature: Verify MWEDD 227 cancellation  email content

  @mwedd_fil_227_30_cancellation
  @regression
  @mwedd_fil_227
  Scenario: Verify Email content for for template mwedd_fil_227_30_cancellation
    Given i trigger "mwedd_fil_227_30_cancellation" input through csp testemail
	And i have an enhanced payload sent to email provider 
	When i navigate to view the email page
	Then i should see pre header:
	"""
	Your account was credited. Shop Macy's
	"""
	And i should see body text:
	"""
	You're all set! Your order was canceled and we're crediting your payment method listed below. Thanks for shopping at Macy's.
	"""
	And i should see order number
	And i should see order capture date
	And i should see "Payment method:" text
	And i should see credit card type
	And i should see "Order cancelation date:" text
	And i should see order cancelation date
	And i should see default categories:
	"""
	FOR THE HOME,MEN,WOMEN,SHOES
	"""
	And i should see Macys logo  	
	And i should see product name
    And i should see product quantity 
	And i should see product color
	And i should see product size
	And i should see product type
	And i should see product price
	And i should see product name url valid  
   