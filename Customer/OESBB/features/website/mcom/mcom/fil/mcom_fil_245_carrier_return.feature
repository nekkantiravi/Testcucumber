Feature: Verify email content for 245_carrier_return template

  @245_carrier_return @regression
  Scenario: Verify Email content
    Given i trigger "mcom_fil_245_5B_carrier_return" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see pre header:
      """
      We attempted to deliver your order. Shop Now
      """
    And i should see static contents:
      """
      The shipping carrier wasn't able to deliver your order to the address you provided. Unfortunately, we had to cancel the order and have refunded your method of payment. To reorder the item(s) and update your shipping address, please call us at 1-800-BUY-MACY (1-800-289-6229). We're available 24 hours a day, 7 days a week. Thanks for shopping at Macy's.
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
    And i should see billing address
    And i should see shipping address
    And i should see shipping method
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
    Then i should see product requested quantity
