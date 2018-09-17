Feature: Verify MCOM 409 price error email content

  @mcom_409_price_error
  @regression
  Scenario: Verify Email content for for template mwedd_fil_409_28_price_error
    Given i trigger "mwedd_fil_409_28_price_error" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader as mentioned:
      """
      Your registry item(s) were incorrectly priced and was refunded. My Account
      """
    And i should see body text:
      """
      adhoc text for testing
      """
    Then i should see order number:
      """
      Order #: <ordernumber>	
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    Then i should see billing firstname
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """
    And i should see macys logo using alternative text
    And i should see button as "visit my account"
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product image with different class name
    Then i should see product image url valid with different class name
