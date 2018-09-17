Feature: Verify MCOM 246 gift return email content

  @246_gift_return
  @regression
  Scenario: Verify Email content for for template mstore_fil_246_6_gift_return
    Given i trigger "mstore_fil_246_6_gift_return" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    
    Then i should see preheader as mentioned:
      """
      We're processing your credit request. Shop Macy's
      """
    Then i should see Macys logo
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """
    Then i should see billing firstname
    Then i should see body text:
    """
    We're processing your credit for the returned item(s). You'll receive a Macy's Gift Card by mail in the amount listed below. Please note: the Macy's Gift Card can be used to purchase merchandise online and in store; it isn't redeemable for cash. Thanks for shopping at Macy's!
    """
    Then i should see order number for mstore:
      """
      Order #: <reservationnumber>
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    Then i should see refund amount
    Then i should see the "SHOP MACY'S" button

    Then i should see product name
    Then i should see product quantity
    Then i should see product price
    Then i should see product color for gift return
    Then i should see product price
    Then i should see product image with different class name
    Then i should see product image url valid with different class name
    Then i should see product name url valid
    Then i should see "Order total" in the email
    Then i should see "Shipping" in the email
    Then i should see sales tax for 246_6 in the email
    Then i should see "PR VAT" in the email
    Then i should see "PR Municipal" in the email
    Then i should see "TOTAL" in the email