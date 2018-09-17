Feature: Verify MCOM 613 security event password reset email content

  @regression @mcom_com_613_1A_security_event_password_reset @mcom_com_613
  Scenario: Verify Email content for template mcom_com_613_1A_security_event_password_reset
    Given i trigger "mcom_com_613_1A_security_event_password_reset" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Follow these steps to unlock your account.
      """
    Then i should see static contents:
      """
      Reset your Macy's password
      Your Macy's account was locked. You'll need to create a new password for security reasons.
      Please click the button below and follow the instructions to reset your password. Please note, this link will expire in 24 hours. To get a new link, go to the Sign In page. 
      """
    And i should see Macys logo
    And i should see one "RESET PASSWORD" button

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_613_1A_security_event_password_reset
    Given i trigger "mcom_com_613_1A_security_event_password_reset_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo