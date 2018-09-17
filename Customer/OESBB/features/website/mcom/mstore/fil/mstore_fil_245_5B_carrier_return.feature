Feature: Verify email content for 245_carrier_return template

  @245_carrier_return
  @regression
  Scenario: Verify Email content
    Given i trigger "mstore_fil_245_5B_carrier_return" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    
     Then i should see pre header:
     """
     We attempted to deliver your order. Shop Now
     """
     Then i should see Macys logo
     Then i should see default categories:
     """
     FOR THE HOME,MEN,WOMEN,SHOES
     """
     Then i should see billing first name
     Then i should see static contents:
	 """
	 The shipping carrier wasn't able to deliver your order to the address you provided. Unfortunately, we had to cancel the order and have refunded your method of payment. To reorder the item(s) and update your shipping address, please call us at 1-800-BUY-MACY (1-800-289-6229). We're available 24 hours a day, 7 days a week. Thanks for shopping at Macy's.
	 """
	 
	 Then i should see "Order #:" text
     Then i should see reservation number
     Then i should see "Order date:" text
     And i should see order capture date
     Then i should see "Payment method:" text
     Then i should see credit card type
     Then i should see "Refunded amount:" text
     Then i should see refund amount
     
     Then i should see button as "shop macy's"
     
     Then i should see "Billing Address:" text
     Then i should see billing address
     Then i should see "Shipping Address:" text
     Then i should see shipping address
     Then i should see "Shipping Method" text 
	 Then i should see shipping method
	 
	 Then i should see "Gift options:" text
	 Then i should see gift box
	 Then i should see gift receipt
	 Then i should see gift message
	 
	 Then i should see "Item(s)" text
     Then i should see product name
     Then i should see product requested quantity
     Then i should see product color
     Then i should see product size
     Then i should see product type
     Then i should see return status description
     Then i should see product image
     Then i should see product image url valid
     Then i should see product name url valid
     
     Then i should see "Order total" in the email
     Then i should see "Shipping" in the email
     Then i should see "Additional shipment fee amount" in the email
     Then i should see "Shipment upgrade fee amount" in the email
     Then i should see "Gift wrap fee amount" in the email
     Then i should see sales tax in the email
     Then i should see "PR VAT" in the email
     Then i should see "PR Municipal" in the email
     Then i should see "TOTAL" in the email