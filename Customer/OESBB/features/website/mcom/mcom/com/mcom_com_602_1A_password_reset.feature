Feature: Verify MCOM 602 password reset email content

  @regression
  @mcom_com_602_1A_password_reset
  @mcom_com_602
  Scenario: Verify Email content for template mcom_com_602_1A_password_reset
    Given i trigger "mcom_com_602_1A_password_reset" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Update your password now.
      """
    Then i should see static contents:
      """
      Reset your password	
      Click on the button below to reset your password. For your security, this link will expire in 24 hours. To get a new link, go to the <link>Sign In<link> page. 
      """
    And i should see Macys logo
    And i should see button as "reset password"
