Feature: Verify MCOM 500 star rewardcard redeem email content 

@mlcomm_lty_500_1A_star_rewardcard_silver_tier @regression 
Scenario: Verify Email content 
	Given i trigger "mlty_lty_500_1A_star_rewardcard_silver_tier" input through csp testemail 
	And i have an enhanced payload sent to email provider 
	When i navigate to view the email page 
	Then i should see pre header: 
		"""
      Use in the next 30 days before it expires! Shop Now 
     """
	Then i should see default categories: 
		"""
      FOR THE HOME,MEN,WOMEN,SHOES
      """
	And i should see Macys logo 
    And i should see congrats message 
	And i should see reward points silver 
	And i should see starmoney creditcard text
	And i should see starmoney expire date
	And i should see first and last name
	And i should see star card silver member
	And i should see star rewards text
	