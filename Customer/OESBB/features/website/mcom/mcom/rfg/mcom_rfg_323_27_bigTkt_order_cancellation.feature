Feature: Verify MCOM 323_27 big ticket order cancellation email content

  @mcom_rfg_323_27_bigTkt_order_cancellation @regression @mcom_rfg_323
  Scenario: Verify Email content for for template mcom_rfg_323_27_bigTkt_order_cancellation
    Given i trigger "mcom_rfg_323_27_bigTkt_order_cancellation" input through csp testemail
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
      Your order couldn't be processed and we had to cancel it. No charges were placed on your payment method listed below.Please call our Customer Protection department to resolve this issue at 1-866-282-8977 (Monday-Saturday 9AM-9PM,Sunday 9AM-7PM).
      """

    Then i should see order number
    And i should see order capture date
    Then i should see credit card type

    And i should see refund amount
    And i should see BT billing address
    And I should see the delivery address
    And I should see the delivery method:
    """
    Furniture/mattress White Glove delivery
    """

    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product image
    Then i should see product image url valid
    And i should see "Order total" in the email
    And i should see "TOTAL" in the email


