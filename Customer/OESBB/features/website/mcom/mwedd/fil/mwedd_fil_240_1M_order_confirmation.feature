Feature: Verify MWEDD 240 order confirmation 240 1M MWWDD email content

  @mwedd_fil_240_1M_order_confirmation @regression @mwedd_fil_240
  Scenario: Verify Email content for template mwedd_fil_240_1M_order_confirmation
    Given i trigger "mwedd_fil_240_1M_order_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Stay tuned for details. My Account
      """
    And i should see body text for 240_mwedd:
      """
      Thanks for your Macy's Registry order. Once your order is processed, we'll email additional details. Remember, if you bought multiple items, they may arrive separately.
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
    And i should see registrant address
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

  @premiumshipping @regression
  Scenario: Verify premium shipping method scenario for template mwedd_fil_240_1M_order_confirmation
    Given i trigger "mwedd_fil_240_1M_order_confirmation_premium_shipping_method" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see shipping method

    
     @nogiftoption @regression
  Scenario: Verify no gift option scenario for template mwedd_fil_240_1M_order_confirmation
    Given i trigger "mwedd_fil_240_1M_order_confirmation_no_gift_option" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see Macys logo
    And i should see no gift option 
