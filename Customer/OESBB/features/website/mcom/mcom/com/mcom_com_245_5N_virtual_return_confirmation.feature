Feature: Verify MCOM 245 virtual return confirmation 245 5N MCOM  email content

  @mcom_com_245_5N_virtual_return_confirmation @regression
  Scenario: Verify Email content
    Given i trigger "mcom_com_245_5N_virtual_return_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Thanks for your patience! Shop Now
      """
    And i should see body text for 245 5N virtual return confirmation:
      """
    	We value you as a customer and apologize for any inconvenience. We have processed a return for the item(s) below. If you haven't already, please return the item(s) listed below by mail or return in store. If we don't receive your return we reserve the right to charge the payment method or otherwise recover payment for the item(s) and any associated charges incurred at the time of purchase. Returning by UPS? Get your free UPS label Here. Returning in store? Find A Store near you. If you've recently returned your item(s) in store, you can ignore this message. 
			"""
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    Then i should see order number
    Then i should see shipment firstname
    Then i should see date initiated date
    Then i should see return to
    And i should see estimated refund
    And i should see reason for return
    And i should see "*please allow 24-72 hours for the credit to appear on your account" text
    And i should see button as "VIEW MY ACCOUNT"
    Then i should see product name
    And i should see product quantity for return confirmation
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see return status description
    And i should see product price
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
    And i should see "Order total" in the email
    And i should see "Shipping" in the email
    And i should see "TOTAL" in the email

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_245_5N_virtual_return_confirmation
    Given i trigger "mcom_com_245_5N_virtual_return_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
