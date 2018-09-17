Feature: Verify BCOM profile reset password Email content

  @regression
  @blcom_com_602_1C_profileresetpassword
  Scenario: Verify email content for template blcom_com_602_1C_profileresetpassword
    Given i trigger "blcom_com_602_1C_profileresetpassword" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      Creating a new password is easy.
      """
      Then i should see static content for profile reset password as:
      """
      RESET YOUR PASSWORD
      
      We received a request to reset your bloomingdales.com password.
      
      To reset your password, click this link (only valid for 24 hours):
      <website.profileEmailAddress>
      
	  If you didn't request a password reset, please contact us immediately at the number below. 
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
      
  @production
  Scenario: Production : Verify email template for blcom_com_602_1C_profileresetpassword
    Given i trigger "blcom_com_602_1C_profileresetpassword" input through csp testemail
    When i navigate to view the email page
    Then i should see bloomingdales logo