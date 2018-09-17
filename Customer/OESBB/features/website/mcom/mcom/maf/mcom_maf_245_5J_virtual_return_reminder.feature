Feature: Verify MCOM 245 virtual return reminder email content

  @regression
  Scenario: Verify email content for maf_245_5J_MCOM template
    Given i trigger "mcom_maf_245_5J_virtual_return_reminder" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Please note: we retain the right to charge your payment method if you decide to keep the item(s). Contact Us
      """
    And i should see body text for virtual return reminder:
      """
      we value you as a customer and apologize for any inconvenience. we processed a return for you on <returnSubmittedDate> and credited your payment method listed below. at this time, we have no records of your item(s) being returned. if we don't receive your return, we reserve the right to charge the original method of payment or otherwise recover payment for the item(s) and any associated charges incurred at the time of purchase. if you've recently returned your item(s) in store you can ignore this message. if you need help locating a store near you, click below.
      """
    Then i should see order number
    And i should see date initiated date
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
    And i should see "Shipping" in the email
    And i should see "Sales tax" in the email
    And i should see "TOTAL" in the email

  @optional123
  Scenario: Verify optional fields empty scenario for template mcom_maf_245_5J_virtual_return_reminder
    Given i trigger "mcom_maf_245_5J_virtual_return_reminder_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
    
  @production
  Scenario: Production : Verify email template for mcom_maf_245_5J_virtual_return_reminder
    Given i trigger "mcom_maf_245_5J_virtual_return_reminder" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo