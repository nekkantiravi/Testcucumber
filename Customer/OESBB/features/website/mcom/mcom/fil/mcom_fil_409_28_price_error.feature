Feature: Verify MCOM 409 price error email content

  @mcom_409_price_error 
  @mcom_fil_409_28_price_error @regression
  Scenario: Verify Email content for for template mcom_fil_409_28_price_error
    Given i trigger "mcom_fil_409_28_price_error" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader as mentioned:
      """
      Your item(s) were incorrectly priced and was refunded. My Account
      """
    Then i should see macys logo using alternative text
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """
    Then i should see billing firstname hi
    Then i should see body text for mcom price error
    Then i should see order number:
      """
      Order #: <ordernumber>
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>
      """
    Then i should see button as "visit my account"

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_fil_409_28_price_error
    Given i trigger "mcom_fil_409_28_price_error_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see macys logo using alternative text
