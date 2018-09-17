Feature: Verify MWEDD 241 order adjustment email content

  @mwedd_fil_241_2_order_adjustment 
  @regression
  Scenario: Verify Email content for for template mwedd_fil_241_2_order_adjustment
    Given i trigger "mwedd_fil_241_2_order_adjustment" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader as mentioned:
      """
      Open for order details. Shop Macy's
      """
    #Commented as single quote is not displaying accurately in the email. Need to fix the issue from Responsys.
    #And i should see static contents:
    #"""
    # We're sorry, but the registry item(s) listed below are out of stock. If you were charged, you’ll receive a refund in the amount listed below. Please visit macys.com to find similar items.
    #"""
    Then i should see order number:
      """
      Order #: <ordernumber>	
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    And i should see refund amount as mentioned:
      """
      Refunded amount: $<refundedamount>
      """
    And i should see the "SHOP MACY'S" button
    Then i should see billing firstname
    And i should see credit card type as mentioned:
      """
      Payment method:<cardtype><cardnumber>
      """
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """
    And i should see macys logo using alternative text
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    Then i should see product image
    Then i should see product image url valid
    Then i should see product name url valid