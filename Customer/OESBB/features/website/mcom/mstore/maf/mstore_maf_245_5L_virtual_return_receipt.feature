Feature: Verify MSTORE 245 virtual return receipt email content

 @mstore_maf_245_5L_virtual_return_receipt @regression
  Scenario: Verify email content of maf_245_5L_mstore template
    Given i trigger "mstore_maf_245_5L_virtual_return_receipt" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Thanks for returning your item(s). Shop Now
      """
    And i should see body text:
      """
      We received your item(s) that we credited you for on 04/20/2016 and your return is complete. Thanks for shopping at Macy's!
      """
    Then i should see reservation number
    And i should see return initiated date
    And i should see return completed date
    And i should see return to
    And i should see estimated refund
    And i should see reason for return
    Then i should see billing firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
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
    And i should see shipping amount in the email for virtual return receipt
    And i should see "Sales tax" in the email
    And i should see "TOTAL" in the email
