Feature: Verify BCOM email change notification email content

  @regression @blcom_com_617_1A_email_change_notification
  Scenario: Verify Email content for template blcom_com_617_1A_email_change_notification
    Given i trigger "blcom_com_617_1A_email_change_notification" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
   
    And i should see preheader:
      """
      If you did not request a change, please contact us.
      """
      And i should see bloomingdales logo
      And i should see freeshipping image
      Then i should see static contents:
      """
      YOUR EMAIL ADDRESS HAS BEEN UPDATED

	  The email address associated with your bloomingdales.com account has been updated. If you did not initiate this change, or believe an unauthorized person has accessed your account, please contact Customer Service immediately at <link>1-800-777-0000<link>.
 	   """
      Then i should see static contents for com template:
      """
      Thank you, 
	  Bloomingdale's Customer Service
	  Call us at any time: 1-800-777-0000
	  We're available 24hours, 7 days a week. 
	  customerservice@bloomingdales.com 
      """
    And i should see "CUSTOMER SERVICE" link
    
