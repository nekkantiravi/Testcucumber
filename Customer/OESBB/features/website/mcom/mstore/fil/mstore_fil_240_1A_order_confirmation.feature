Feature: Verify MSTORE 240 order confirmation 240 1A MSTORE  email content

  @mstore_fil_240_1A_order_confirmation @regression @mstore_fil_240
  Scenario: Verify Email content
    Given i trigger "mstore_fil_240_1A_order_confirmation" input through csp testemail
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
    Then i should see reservation number
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

  @paymentlength2 @regression
  Scenario: Verify payment length2 scenario for template mstore_fil_240_1A_order_confirmation
    Given i trigger "mstore_fil_240_1A_order_confirmation_with_payment_length2" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see credit card type

  @paymentlength3 @regression
  Scenario: Verify payment length3 scenario for template mstore_fil_240_1A_order_confirmation
    Given i trigger "mstore_fil_240_1A_order_confirmation_with_payment_length3" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see credit card type
