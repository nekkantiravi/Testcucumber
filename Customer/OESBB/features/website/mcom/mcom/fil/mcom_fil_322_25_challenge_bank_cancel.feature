Feature: Verify MCOM 322 challenge bank cancel email content
 
   @mcom_fil_322_25_challenge_bank_cancel @regression
   Scenario: Verify Email content for for template mcom_fil_322_25_challenge_bank_cancel
     Given i trigger "mcom_fil_322_25_challenge_bank_cancel" input through csp testemail
     And i have an enhanced payload sent to email provider
     When i navigate to view the email page
     Then i should see pre header:
       """
       There was an issue with your Macy's order. My Account
       """
     And i should see body text:
       """
       Your order couldn't be processed and was canceled. No charges were placed on your account.Please call our Consumer Protection team at 1-866-282-8977 (Monday-Saturday 9AM-9PM, Sunday 9AM-7PM) to resolve this issue.
       """
     Then i should see order number
     And i should see order capture date
     Then i should see billing firstname
     Then i should see default categories:
       """
       FOR THE HOME,MEN,WOMEN,SHOES
       """
     And i should see Macys logo
     And i should see button as "SHOP MACY'S"
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
     And i should see sales tax in the email
     And i should see "TOTAL" in the email
     
   @optional
   Scenario: Verify optional fields empty scenario for template mcom_fil_322_25_challenge_bank_cancel
    Given i trigger "mcom_fil_322_25_challenge_bank_cancel_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo