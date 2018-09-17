Feature: Verify BCOM shipping address change notification email template

  @regression
  @blcom_com_617_1B_shipping_add_change_notification
  Scenario: Verify email content for template blcom_com_602_1C_profileresetpassword
    Given i trigger "blcom_com_617_1B_shipping_add_change_notification" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see preheader:
      """
      YOUR SHIPPING ADDRESS HAS BEEN UPDATED
      """
    And i should see static content for ship address change:
      """
      The shipping address associated with your bloomingdales.com account has been updated. If you did not initiate this change or believe an unauthorized person has accessed your account please click here to reset your password, or contact Customer Service immediately at 1-800-777-0000.
	   """
    And i should see static contents for com template:
      """
      Thank you,
	  Bloomingdale's Customer Service
	  Call us at any time: 1-800-777-0000
	  We're available 24hours, 7 days a week.
	  customerservice@bloomingdales.com
      """
    And i should see shipping address change firstname


  @production
  Scenario: Production : Verify email template for blcom_com_617_1B_shipping_add_change_notification
    Given i trigger "blcom_com_617_1B_shipping_add_change_notification" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo