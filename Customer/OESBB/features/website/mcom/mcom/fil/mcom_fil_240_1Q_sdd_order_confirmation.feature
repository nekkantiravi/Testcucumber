Feature: Verify MCOM 240 sdd order confirmation 240 1Q MCOM email content

  @regression @mcom_fil_240 @mcom_fil_240_1Q_sdd_order_confirmation
  Scenario: Verify Email content for template mcom_fil_240_1Q_sdd_order_confirmation
    Given i trigger "mcom_fil_240_1Q_sdd_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Stay tuned for details. My Account
      """
    And i should see body text:
      """
      Thanks for your Macy's order. Stay tuned for an email from our Same-Day Delivery partners at Deliv with instructions on scheduling your delivery. Remember, if you bought multiple items, they may arrive separately.
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
    And i should see same day delivery text:
      """
      Arrives today (for orders placed by 1pm, or 11am on Sundays)
      """
    Then i should see credit card type
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
    And i should see note section:
      """
      Please note: You can make changes to most orders up to 30 minutes after they've been placed. See Details
      """
    And i should see "Order total" in the email
    And i should see "Same day delivery amount" in the email
    And i should see "TOTAL" in the email
    And i should see loyalty section in the email

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_fil_240_1Q_sdd_order_confirmation
    Given i trigger "mcom_fil_240_1Q_sdd_order_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo