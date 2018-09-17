Feature: Verify email content for 245_merchandise_credit template
  @mwedd_fil_245_5C_merchandise_credit
  @regression
  Scenario: Verify Email content
    Given i trigger "mwedd_fil_245_5C_merchandise_credit" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see pre header:
      """
      We're processing your refund. My Account
      """
      And i should see static contents:
	
	  """
	  We're processing the refund from your recent Macy's order. The refund will be returned to the payment method listed below. Thanks for shopping at Macy's.If you need assistance, please call 1-800-BUY-MACY (1-800-289-6229). We're available 24 hours a day, 7 days a week.
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
    And i should see button as "shop macy's"
    Then i should see product name
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see return status description
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
    And i should see "Order total" in the email
    And i should see "Shipping" in the email
    And i should see "Shipment upgrade fee amount" in the email
    And i should see "Gift wrap fee amount" in the email
    And i should see "PR VAT" in the email
    And i should see "PR Municipal" in the email
    And i should see "TOTAL" in the email
    