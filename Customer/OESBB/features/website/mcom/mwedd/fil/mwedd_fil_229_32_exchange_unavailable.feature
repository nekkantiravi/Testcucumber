Feature: Verify MWEDD 229_Exchange_Unavailable email content

  @mwedd_fil_229_32_exchange_unavailable
  @regression
  Scenario: Verify email content for tempalte mwedd_fil_229_32_exchange_unavailable
    Given i trigger "mwedd_fil_229_32_exchange_unavailable" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    
    Then i should see pre header:
    
	"""
	We can't exchange your registry item(s). Shop Macy's
	"""
	
	And i should see static contents:
	
	  """
	  Thanks for your return. Unfortunately, we can't make the exchange because the item(s) you requested are out of stock.We refunded your account for the amount listed below. We apologize for the inconvenience.

      """   
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see order number
    And i should see order capture date
    And i should see firstname
    And i should see credit card type
    And i should see refund amount
    And i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product type
    And i should see product name url valid
   
