Feature: Verify MSTORE 245 virtual exchange reminder email content

  @regression
  Scenario: Verify email content for maf_245_5K_MSTORE template
    Given i trigger "mstore_maf_245_5K_virtual_exchange_reminder" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see pre header:
      """
      Please note: we reserve the right to charge your payment method if you decide to keep the item(s). Contact Us
      """
    And i should see body text for virtual exchange reminder:
      """
      We processed a replacement for you on <returnSubmittedDate> and at this time, we have no record of your item(s) being returned.If we don't receive your return by <returnExpectedBackDate>, we reserve the right to charge the original method of payment or otherwise recover payment for the item(s) and any associated charges incurred at the time of purchase.If you've recently returned your item(s) in store, you can ignore this message. If you need help locating a store near you, click below.Returning by UPS? click here to print your free UPS label.Returning in store? click here to find the closest Macy's.Still need help or have questions? Please contact us.
      """
    And i should see button as "check order status"
    And i should see replaced on date
    And i should see credit card type for 245 templates
    And i should see original payment
    And i should see reason for replacement
    And i should see order number
    Then i should see billing firstname
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
    And i should see "Sales tax" in the email
    And i should see "TOTAL" in the email
