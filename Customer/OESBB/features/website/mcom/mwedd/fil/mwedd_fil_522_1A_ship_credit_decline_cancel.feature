Feature: Verify MWEDD 522 ship credit decline cancel email content

  @mwedd_fil_522_1A_ship_credit_decline_cancel @regression
  Scenario: Verify Email content for for template mwedd_fil_522_1A_ship_credit_decline_cancel
    Given i trigger "mwedd_fil_522_1A_ship_credit_decline_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      There was an issue with your Macy's registry order. My Account
      """
    And i should see body text:
      """
      Unfortunately, we're unable to ship part of your order because the payment method was declined.Please call our Consumer Protection team to authorize a new payment method, so we can ship your item(s). They can be reached at 1-866-282-8977, Monday-Saturday 9AM-9PM and Sunday 9AM-7PM (ET).
      """
    Then i should see order number
    And i should see order capture date
    And i should see button as "contact macy's"
    Then i should see billing firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see billing address
    And i should see shipping address
    And i should see shipping method
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
