 Feature: Verify email content for 245_post_ship_credit template
   @mwedd_fil_245_5D_post_ship_credit
   @regression
   Scenario: Verify Email content
     Given i trigger "mwedd_fil_245_5D_post_ship_credit" input through csp testemail
     And i have an enhanced payload sent to email provider
     When i navigate to view the email page
     And i should see pre header:
       """
      We're processing your credit. Shop Registry
       """
       And i should see static contents:
 	
 	  """
 	 We're crediting the payment method for the amount listed below. Thanks for shopping at Macy's.
 	 
       """   
     Then i should see default categories:
       """
       FOR THE HOME,MEN,WOMEN,SHOES
       """
     And i should see Macys logo
     And i should see order number
     And i should see order capture date
     Then i should see credit card type
     And i should see refund amount
     
   