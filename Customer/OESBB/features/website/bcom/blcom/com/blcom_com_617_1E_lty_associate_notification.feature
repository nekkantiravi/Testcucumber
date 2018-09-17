Feature: Verify BCOM shipping address change notification email template

  @regression
  @blcom_com_617_1E_lty_associate_notification
  Scenario: Verify email content for template blcom_com_617_1E_lty_associate_notification
    Given i trigger "blcom_com_617_1E_lty_associate_notification" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see loyalty preheader:
      """
      Your Loyallist ID is now linked
      """
    And i should see static contents for com template:
      """
      We received a request to link a Loyallist ID with a Bloomingdales.com account. Didnt recently add a Loyallist ID to an account? Please contact us immediately at 1-800-777-0000. We're available 24 hours, 7 days a week or email at customerservice@bloomingdales.com.
	   """
    And i should see static contents for com template:
      """
      Thank you,
	  Bloomingdale's Customer Service
	  Call us at any time: 1-800-777-0000
	  We're available 24hours, 7 days a week.
	  customerservice@bloomingdales.com
      """


  @production
  Scenario: Production : Verify email template for blcom_com_617_1E_lty_associate_notification
    Given i trigger "blcom_com_617_1E_lty_associate_notification" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo