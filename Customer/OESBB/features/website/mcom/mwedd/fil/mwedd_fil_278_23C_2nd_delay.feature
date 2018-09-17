Feature: Verify MCOM 278 2nd delay email content

  @mwedd_fil_278_23C_2nd_delay @regression
  Scenario: Verify Email content for for template mwedd_fil_278_23C_2nd_delay
    Given i trigger "mwedd_fil_278_23C_2nd_delay" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader as mentioned:
      """
      Your item(s) are still on backorder. My Account
      """
    
    #And i should see body text:
    #  """
    #We're sorry, but item(s) from order #<ordernumber> are further delayed with the vendor. At this time, the vendor is unable to provide a new ship date. Note:We need to hear from you to confirm you're willing to wait for the item(s). Simply reply to this email or call us at 1-800-BUY-MACY (1-800-289-6229). Otherwise, we'll have to cancel your backorder after 30 days. As always, you can cancel your backorder at any time before it's shipped and you won't be charged until it ships. We appreciate your patience.
    #  """
    Then i should see order number:
      """
      Order #: <ordernumber>	
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
