Feature: Verify MCOM 240 order confirmation 240 1I MCOM confirmation email content

  @mcom_fil_240_1I_order_confirmation @regression @mcom_fil_240
  Scenario: Verify Email content for for template mcom_fil_240_1I_order_confirmation
    Given i trigger "mcom_fil_240_1I_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Stay tuned for details. My Account
      """
    And i should see body text:
      """
      Thanks for your Macy's order. Once your order is processed, we'll email additional details. Remember, if you bought multiple items, they may arrive separately.
      """
    Then i should see order number
    And i should see order capture date
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see billing address
    And i should see shipping address
    And i should see shipping method
    And i should see gift box
    And i should see gift receipt
    And i should see gift message
    Then i should see credit card type
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see ship date
    And i should see shipment type
    And i should see product type
    And i should see product price
    And i should see recepient email address
    And i should see estimated ship date
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
    And i should see note section:
      """
      Please note: You can make changes to most orders up to 30 minutes after they've been placed. See Details
      """
    And i should see "Order total" in the email
    And i should see "Shipping" in the email
    And i should see "Additional shipment fee amount" in the email
    And i should see "Shipment upgrade fee amount" in the email
    And i should see "Gift wrap fee amount" in the email
    And i should see "Order total" in the email
    And i should see the loyalty error code text in the email:
      """
      Your account is not available at this time due to system issues. Your account will be updated at a later time. Questions? Call us at 1-888-950-0132.
      """

  @standardshipping @regression
  Scenario: Verify standard shipping method scenario for template mcom_fil_240_1I_order_confirmation
    Given i trigger "mcom_fil_240_1I_order_confirmation_standard_shipping_method" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see shipping method

  @groundshipping @regression
  Scenario: Verify ground shipping method scenario for template mcom_fil_240_1I_order_confirmation
    Given i trigger "mcom_fil_240_1I_order_confirmation_ground_shipping_method" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo