Feature: Verify MSTORE payment authentication failure 233 1A MSTORE  email content

  @mstore_fil_233_1A_payment_auth_failure @regression @mstore_fil_233 @Fedfil_233
  Scenario: Verify Email content for for template mstore_fil_233_1A_payment_auth_failure
    Given i trigger "mstore_fil_233_1A_payment_auth_failure" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      There was an issue with your Macy's order. Update Payment.
      """
    And i should see body text with order cancel time:
      """      
				We're sorry, your payment method was declined. Please continue to the encrypted page linked below to safely update your payment information. Please update your payment information by <orderCanceldate> at <orderCanceltime> AM ET. If we don't hear from you by then, we'll need to cancel the order. If you need assistance, please call our Customer Service team at 1-800-BUY-MACY (1-800-289-6229). We're available 24 hours a day, 7 days a week.
      """
    Then i should see reservation number
    And i should see order capture date
		And i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see update payment information button
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
    And i should see ship date
    And i should see shipment type
    And i should see product type
    And i should see product price
    And i should see recepient email address
    And i should see estimated ship date
    Then i should see product image url valid
    And i should see product name url valid
    
    And i should see "Order total" in the email
    And i should see "Shipping" in the email
    And i should see "Additional shipment fee amount" in the email
    And i should see "Shipment upgrade fee amount" in the email
    And i should see "Gift wrap fee amount" in the email
    And i should see "TOTAL" in the email
    

          
  