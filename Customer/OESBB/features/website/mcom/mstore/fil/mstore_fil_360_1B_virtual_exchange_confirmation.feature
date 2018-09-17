Feature: Verify FIL 360 virtual exchange confirmation 360 1B MSTORE email content

  @mstore_fil_360_1B_virtual_exchange_confirmation @regression
  Scenario: Verify Email content
    Given i trigger "mstore_fil_360_1B_virtual_exchange_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Thanks for your patience! My Account
      """
    And i should see body text for virtual exchange confirmation:
      """
      We value you as a customer and apologize for any inconvenience. We've processed your replacement order for the item(s) shown below. When the order ships, you'll receive an email with tracking information.If you haven't already, please return the item(s) listed below by mail or return in store by <returnExpectedDate>. If we don't receive your return by <returnExpectedDate> we reserve the right to charge the payment method used for the original purchase for the item(s) and any associated charges incurred at the time of purchase.Returning by UPS? Get your free UPS label here.Returning in store? Find a store near you 

      """
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    Then i should see billing firstname
    Then i should see order number
    And i should see replacement date for virtual exchange confirmation
    Then i should see credit card type
    And i should see note section:
      """
      NOTE: You may not be able to track your packages right away. It can take 24-48 hours for the carrier to update their records with your shipping information. learn more
      """
    And i should see button as "CHECK ORDER STATUS"
    Then i should see product name
    And i should see product quantity for return confirmation
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see return status description
    And i should see product price
    And i should see product image with different class name
    Then i should see product image url valid with different class name
    And i should see product name url valid
