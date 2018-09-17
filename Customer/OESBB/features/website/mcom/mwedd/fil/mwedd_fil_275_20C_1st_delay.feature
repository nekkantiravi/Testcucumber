Feature: Verify email content for 275_1st_delay template

  @mwedd_fil_275_20C_1st_delay @regression
  Scenario: Verify Email content for template mwedd_fil_275_20C_1st_delay
    Given i trigger "mwedd_fil_275_20C_1st_delay" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Attention needed: important update to your recent order. My Account
      """
    #And i should see body text having system cancel date:
    #"""
    #We're sorry, the item(s) listed below are on backorder with the vendor. The new estimated ship date is 11/28/2017. You may cancel your backorder at any time before it ships and you won't be charged. Simply reply to this email or call us at 1-800-BUY-MACY (1-800-289-6229). We appreciate your patience.
    #"""
    Then i should see order number:
      """
      Order #: <ordernumber>	
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    And i should see button as "VIEW MY ACCOUNT"
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see original ship date
    And i should see new ship date
    And i should see product type
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid
    And i should see Order total in the email
    And i should see Shipping in the email
    And i should see Additional shipment fee amount in the email
    And i should see Shipment upgrade fee amount in the email
    And i should see Gift wrap fee amount in the email
    And i should see sales tax in the email
    And i should see Total in the email
