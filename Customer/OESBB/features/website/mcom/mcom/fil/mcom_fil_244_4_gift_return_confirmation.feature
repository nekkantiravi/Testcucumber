Feature: Verify MCOM 244 gift return confirmation email content

  @mcom_fil_244_4_gift_return_confirmation @regression
  Scenario: Verify Email content for for template mcom_fil_244_4_gift_return_confirmation
    Given i trigger "mcom_fil_244_4_gift_return_confirmation" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader as mentioned:
      """
      We canceled your E-Gift Card. My Account
      """
    And i should see body text:
      """
      hi oes, as you requested, your e-gift card is now canceled. no charges will be placed on your selected method of payment.
      """
    Then i should see order number:
      """
      Order #: <ordernumber>	
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    Then i should see billing firstname hi
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """
    And i should see macys logo using alternative text
    Then i should see credit card type displayed in one line
    Then i should see product name
    And i should see product quantity
    And i should see product price
    And i should see product image with different class name
    Then i should see product image url valid with different class name
    And i should see product name url valid
    
    @optional
   Scenario: Verify optional fields empty scenario for template mcom_fil_244_4_gift_return_confirmation
    Given i trigger "mcom_fil_244_4_gift_return_confirmation_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see macys logo using alternative text