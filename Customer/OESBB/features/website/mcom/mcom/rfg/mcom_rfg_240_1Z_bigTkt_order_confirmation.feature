Feature: Verify MCOM 240 1Z big ticket order confirmation email content

  @mcom_rfg_240_1Z_bigTkt_order_confirmation @regression @mcom_rfg_240
  Scenario: Verify Email content for for template mcom_rfg_240_1Z_bigTkt_order_confirmation
    Given i trigger "mcom_rfg_240_1Z_bigTkt_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Open for order details. my account
      """
    And i should see Macys logo
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see firstname
    And i should see body text:
      """
      Thanks for shopping at Macy's! Once your order is processed, we'll email you additional details, including information regarding White Glove delivery for any furniture or mattress items.
      """
    And i should see order number
    And i should see order capture date
    And i should see credit card type
    And i should see BT contact information
    And i should see BT billing address
    And i should see BT Delivery summary text
    And I should see the delivery address
    And I should see the delivery method:
    """
    Furniture/mattress White Glove delivery
    """
    And i should see shipment type
    And i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product image
    And i should see product image url valid
    And i should see note section:
      """
      Questions about furniture or mattress returns? Call us at 1-888-822-6229.
      """

    And i should see "Order total" in the email
    And i should see "TOTAL" in the email


