Feature: Verify BCOM 613 security event reset password Email content

  @regression
  @blcom_com_613_1A_security_event_reset_password
  Scenario: Verify email content for template blcom_com_613_1A_security_event_reset_password
    Given i trigger "blcom_com_613_1A_security_event_reset_password" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see security firstname 
    And i should see header:
      """
      RESET YOUR PASSWORD
      """
    Then i should see static contents for security password:
      """
      Your Bloomingdales.com account has been locked and you must reset your password in order to access it. Please click on the link below and follow the instructions to reset your password. Visit <url> NOTE: This link will expire after 24 hours. Questions? Contact Customer Service. Thank you,Bloomingdale's Site Security Team
      """
      
    
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_com_613_1A_security_event_reset_password_optional
    Given i trigger "blcom_com_613_1A_security_event_reset_password_optional" input through csp testemail
    When i navigate to view the email page
    And i should see freeshipping image

