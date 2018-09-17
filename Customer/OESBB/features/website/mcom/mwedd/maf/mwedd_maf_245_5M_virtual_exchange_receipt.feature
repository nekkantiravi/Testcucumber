Feature: Verify MWEDD 245 virtual exchange receipt email content

  @regression
  Scenario: Verify email content for maf_245_5M_MWEDD template
    Given i trigger "mwedd_maf_245_5M_virtual_exchange_receipt" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Thanks for returning your item(s). Shop Now 
      """
    Then i should see billing firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see body text for virtual return reminder:
      """
      we received the item(s) that we replaced for you on <returnSubmittedDate> and your return is complete. thanks for shopping at macy's!
      """
    Then i should see "Return details:" text
    And i should see order number
    And i should see replacement date
    And i should see return completed date
    And i should see "Original form of tender" text
    And i should see credit card type for 245 templates
    And i should see original payment
    And i should see reason for replacement
    And i should see the "SHOP MACY'S" button
    Then i should see "Returned item(s)" text
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price
    And i should see "Order total" in the email
    And i should see "Shipping" in the email
    And i should see "Sales tax" in the email
    And i should see "TOTAL" in the email
