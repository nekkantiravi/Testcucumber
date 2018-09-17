Feature: Verify MCOM 245 virtual return confirmation 245 5F MCOM  email content

  @mcom_fil_245_5F_virtual_return_confirmation @regression
  Scenario: Verify Email content
    Given i trigger "mcom_fil_245_5F_virtual_return_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Thanks for your patience! Shop Now
      """
    And i should see body text for virtual return confirmation:
      """
       we value you as a customer and apologize for any inconvenience. we have processed a return for the item(s) below. if you haven't already, please return the item(s) listed below by mail or return in store by <returnExpectedDate>. if we don't receive your return by <returnExpectedDate>, we reserve the right to charge the payment method or otherwise recover payment for the item(s) and any associated charges incurred at the time of purchase. returning by ups? get your free ups label here. returning in store? find a store near you. if you've recently returned your item(s) in store, you can ignore this message.

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
    And i should see estimated refund for 245 5F templates
    And i should see reason for return for 245 5F templates
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
