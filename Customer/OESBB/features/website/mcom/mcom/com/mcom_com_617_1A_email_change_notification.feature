Feature: Verify MCOM 617 email change notification email content

  @regression
  Scenario: Verify Email content for template mcom_com_617_1A_email_change_notification
    Given i trigger "mcom_com_617_1A_email_change_notification" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Didn’t change your email address? Contact Us
      """
    Then i should see static content for email change notification as:
      """
      Your email address was updated. 
      	
      We’re confirming you changed your Macy’s email address to: <website.profileEmailAddress> 	
      
      If you didn't make this change, please call Customer Service immediately at 1-800-289-6229. We’re available 24 hours a day, 7 days a week. 	
      
      Have a question? Contact Us
      """
    And i should see Macys logo
    And i should see button as "shop macy's"

   
   @production
   Scenario: Production : Verify email template for mcom_com_617_1A_email_change_notification
    Given i trigger "mcom_com_617_1A_email_change_notification" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo