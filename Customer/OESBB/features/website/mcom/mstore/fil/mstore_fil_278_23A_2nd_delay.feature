Feature: Verify MCOM 278 2nd delay email content

  @mstore_fil_278_23A_2nd_delay @regression
  Scenario: Verify Email content for for template mstore_fil_278_23A_2nd_delay
    Given i trigger "mstore_fil_278_23A_2nd_delay" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader as mentioned:
      """
      Your item(s) are still on backorder. My Account
      """
    And i should see body text:
      """
       Unfortunately, your item is still on backorder with the vendor and is further delayed.If this delay is not acceptable, you may cancel your order any time before it ships by calling 1-800-289-6229 (Monday - Friday: 9am - 9pm, Saturday: 9am - 7pm, Sunday: 11am - 7pm).
      """
    Then i should see order number for mstore:
      """
      Order #: <reservationnumber>
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see macys logo using alternative text
    And i should see button as "view my account"
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product status
    And i should see original ship date
    And i should see estimated ship date as "Further delay"
    And i should see product image with different class name
    Then i should see product image url valid with different class name
    And i should see Order total in the email
    And i should see Shipping in the email
    And i should see Additional shipment fee amount in the email
    And i should see Shipment upgrade fee amount in the email
    And i should see Gift wrap fee amount in the email
    And i should see sales tax in the email
    And i should see Total in the email
