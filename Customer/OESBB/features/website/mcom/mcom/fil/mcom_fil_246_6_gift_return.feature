Feature: Verify MCOM 246 gift return email content

  @246_gift_return 
  @mcom_fil_246_6_gift_return @regression
  Scenario: Verify Email content for for template mcom_fil_246_6_gift_return
    Given i trigger "mcom_fil_246_6_gift_return" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader as mentioned:
      """
      We're processing your credit request. Shop Macy's
      """
    And i should see body text:
      """
      hi 246_gift_return, we're processing your credit for the returned item(s). you'll receive a macy's gift card by mail in the amount listed below. please note: the macy's gift card can be used to purchase merchandise online and in store; it isn't redeemable for cash. thanks for shopping at macy's! 
      """
    Then i should see order number:
      """
      Order #: <ordernumber>	
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    And i should see refund amount
    And i should see the "SHOP MACY'S" button
    Then i should see billing firstname hi
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """
    And i should see Macys logo
    Then i should see product name
    And i should see product quantity
    And i should see product price
    And i should see product image with different class name
    Then i should see product image url valid with different class name
    And i should see product name url valid
    And i should see "Order total" in the email
    And i should see sales tax for 246_6 in the email
    And i should see "PR VAT" in the email
    And i should see "PR Municipal" in the email
    And i should see "TOTAL" in the email

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_fil_246_6_gift_return
    Given i trigger "mcom_fil_246_6_gift_return_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
