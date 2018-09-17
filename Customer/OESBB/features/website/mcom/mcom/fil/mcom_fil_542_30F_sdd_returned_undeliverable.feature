Feature: Verify MCOM 542 SDD returned undeliverable email content

  @mcom_fil_542_30F_sdd_returned_undeliverable @regression
  Scenario: Verify Email content for for template mcom_fil_542_30E_sdd_returned_undeliverable
    Given i trigger "mcom_fil_542_30F_sdd_returned_undeliverable" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      	Your order was canceled. My Account

      """
    And i should see body text:
      """
      Sorry we missed you today! Unfortunately, we were unable to deliver your order and had to cancel it. Don’t worry, we’ve issued a refund to the payment method listed below.The item(s) may still be available on macys.com or at a Macy’s store. Please click on the product links below to place a new order.
      """
    Then i should see order number
    And i should see order capture date
    Then i should see billing firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see billing address
    And i should see shipping address
    And i should see gift receipt
    Then i should see credit card type displayed in one line
    Then i should see "VIEW MY ACCOUNT" button
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price
    And i should see recepient email address
    And i should see product image with different class name
    Then i should see product image url valid with different class name
    And i should see product name url valid
    And i should see "Order total" in the email
    And i should see Same day delivery amount for sdd returned undeliverable
    And i should see sales tax in the email
    And i should see "Additional shipment fee amount" in the email
    And i should see "Gift wrap fee amount" in the email
    And i should see "TOTAL" in the email

