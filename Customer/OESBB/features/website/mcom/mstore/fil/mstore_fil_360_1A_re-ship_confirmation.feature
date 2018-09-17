Feature: Verify MSTORE 360 re-ship confirmation email content

  @mstore_fil_360_1A_re-ship_confirmation
  @regression
  Scenario: Verify Email content for  mcom_fil_360_1A_re-ship_confirmation template
    Given i trigger "mstore_fil_360_1A_re-ship_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      You can discard the damaged item(s). Shop Now
      """
    And i should see static contents:
      """
     We value you as a customer and apologize for any inconvenience. We've processed your replacement order for the item(s) shown below. When the order ships, you'll receive an email with tracking information. Thanks for shopping at Macy's.
      """
    Then i should see reservation number
    Then i should see shipment firstname
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