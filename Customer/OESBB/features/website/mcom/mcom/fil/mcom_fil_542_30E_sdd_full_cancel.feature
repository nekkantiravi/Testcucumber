Feature: Verify MCOM 542 SDD full cancel email content

  @mcom_fil_542_30E_sdd_full_cancel @regression
  Scenario: Verify Email content for for template mcom_fil_542_30E_sdd_full_cancel
    Given i trigger "mcom_fil_542_30E_sdd_full_cancel" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      	We were unable to fulfill your order. My Account

      """
    And i should see body text:
      """
      We're sorry, but item(s) in your order aren't available for Same-Day Delivery and we had to cancel your order. Don't worry, you weren't charged.The item(s) may still be available on macys.com or at a Macy's store. Please click on the product links below to place a new order. We apologize for the inconvenience.

      """
    Then i should see order number
    And i should see order capture date
    Then i should see billing firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see billing address
    And i should see shipping address
    And i should see gift box
    And i should see gift receipt
    Then i should see credit card type displayed in one line
    Then i should see "VIEW MY ACCOUNT" button
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see product type
    And i should see product price for sdd cancel
    And i should see recepient email address
    And i should see product image
    Then i should see product image url valid
    And i should see product name url valid

    
    @optional
  Scenario: Verify optional fields empty scenario for template mcom_fil_542_30E_sdd_full_cancel
    Given i trigger "mcom_fil_542_30E_sdd_full_cancel_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo