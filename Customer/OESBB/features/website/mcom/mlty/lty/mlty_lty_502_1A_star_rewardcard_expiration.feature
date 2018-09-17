Feature: Verify MCOM 502 star rewardcard  email content 

@mlcomm_lty_502_1A_star_rewardcard_expiration @regression 
Scenario: Verify Email content 
	Given i trigger "mlty_lty_502_1A_star_rewardcard_expiration" input through csp testemail 
	And i have an enhanced payload sent to email provider 
	When i navigate to view the email page 
	Then i should see pre header: 
		"""
      Use it or lose it - make the most of your reward now! Shop Now 
     """
	Then i should see default categories: 
		"""
      FOR THE HOME,MEN,WOMEN,SHOES
      """
	And i should see Macys logo 
    And i should see loyalty first name 
	And i should see reward points expiration 
	And i should see starmoney creditcard text
	And i should see use it macys store text
	And i should see star money expire date
	And i should see first name and last name
	And i should see member text
	And i should see star rewards text
	